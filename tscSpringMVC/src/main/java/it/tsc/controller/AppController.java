package it.tsc.controller;

import java.security.GeneralSecurityException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;

import it.tsc.domain.Role;

@Controller
@RequestMapping("/")
public class AppController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(AppController.class);

  /**
   * home access page
   * 
   * @param ab_codi
   * @param request
   * @return
   */
  @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
  public ModelAndView homePage(@RequestParam(value = "ab_codi", required = false) String ab_codi,
      HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    model.addObject("ab_codi", ab_codi);
    model.setViewName("home");
    return model;
  }

  /**
   * admin access page
   * 
   * @param ab_codi
   * @param mfaCode
   * @param user
   * @param request
   * @return
   */
  @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
  public ModelAndView adminPage(@RequestParam(value = "ab_codi", required = false) String ab_codi,
      @RequestParam(value = "mfaCode", required = false) String mfaCode,
      @AuthenticationPrincipal Principal user, HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    request.getSession().setAttribute("ab_codi", ab_codi);

    model.addObject("ab_codi", ab_codi);
    model.addObject("roles", roles());
    model = getReturnView(model, ACTION_ADMIN, request, user);
    return model;
  }

  @RequestMapping(value = { "/user" }, method = RequestMethod.GET)
  public ModelAndView userPage(@RequestParam(value = "ab_codi", required = false) String ab_codi,
      @RequestParam(value = "mfaCode", required = false) String mfaCode,
      @AuthenticationPrincipal Principal user, HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    request.getSession().setAttribute("ab_codi", ab_codi);
    model.addObject("ab_codi", ab_codi);
    model.addObject("roles", roles());
    model = getReturnView(model, ACTION_USER, request, user);
    return model;
  }

  /**
   * add MFA code
   * 
   * @param user
   * @param request
   * @param mfaCode
   * @param keyId
   * @param generatedBase32Secret
   * @param login
   * @return
   * @throws GeneralSecurityException
   */
  @RequestMapping(value = { "/addMfaSecurityCode" }, method = RequestMethod.GET)
  public ModelAndView addMfaSecurityCode(@AuthenticationPrincipal Principal user,
      HttpServletRequest request, @RequestParam(value = "mfaCode", required = true) String mfaCode,
      @RequestParam(value = "keyId", required = true) String keyId,
      @RequestParam(value = "generatedBase32Secret", required = true) String generatedBase32Secret,
      @RequestParam(value = "login", required = true) String login)
      throws GeneralSecurityException {
    ModelAndView model = new ModelAndView();
    boolean mfaCodeValid = false;
    /**
     * insert in database if mfa is valid
     */
    model.addObject("message", "MFA code succesfully reset");
    try {
      mfaCodeValid = isMfaCodeValid(mfaCode, generatedBase32Secret);
    } catch (Exception e) {
      model.addObject("error", "MFA code raising exception");
      logger.error(e.getMessage());
    }
    if (mfaCodeValid) {
      getUserService().updateMfaUserKey(user.getName(), keyId, generatedBase32Secret);
      model.addObject("message", "MFA correctly registered");
      model.setViewName(ACTION_CHECK_MFA);
    } else {
      model.addObject("error", "MFA invalid doesn't check with code");
      model.addObject("generatedBase32Secret", generatedBase32Secret);
      model.addObject("keyId", keyId);
      model.addObject("qrcode_url",
          TimeBasedOneTimePasswordUtil.qrImageUrl(keyId, generatedBase32Secret));
      model.setViewName(ACTION_INSERT_MFA);
    }

    return model;
  }

  /**
   * check MFA access code
   * 
   * @param user
   * @param request
   * @param mfaCode
   * @return
   * @throws GeneralSecurityException
   */
  @RequestMapping(value = { "/checkMfaSecurityCode" }, method = RequestMethod.GET)
  public ModelAndView checkMfaSecurityCode(@AuthenticationPrincipal Principal user,
      HttpServletRequest request, @RequestParam(value = "mfaCode", required = true) String mfaCode)
      throws GeneralSecurityException {
    ModelAndView model = new ModelAndView();
    boolean mfaCodeValid = false;
    /**
     * check if mfa is valid
     */
    String base32Secret = getBase32Secret(user);

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    logger.debug("auth: {}", auth.getAuthorities().toString());

    try {
      mfaCodeValid = isMfaCodeValid(mfaCode, base32Secret);
    } catch (Exception e) {
      model.addObject("error", "MFA code raising exception");
      logger.error(e.getMessage());
    }
    if (mfaCodeValid) {
      setMfaAuthenticated(request, "true");
      if (getUserRole() != null
          && (getUserRole().equals(Role.ROLE_ADMIN) || getUserRole().equals(Role.ROLE_SADMIN))) {
        logger.debug("auth admin step");
        model.setViewName("redirect:/" + ACTION_ADMIN);
      } else if (getUserRole() != null && getUserRole().equals(Role.ROLE_USER)) {
        model.setViewName("redirect:/" + ACTION_USER);
        logger.debug("auth user step");
      } else {
        throw new IllegalArgumentException("Unexpected user role");
      }
    } else {
      model.addObject("error", "MFA invalid doesn't check with code");
      setMfaAuthenticated(request, "false");
      model.setViewName(ACTION_CHECK_MFA);
    }
    return model;
  }

  /**
   * reset MFA
   * 
   * @param user
   * @param request
   * @return
   */
  @RequestMapping(value = { "/resetmfa" }, method = RequestMethod.POST)
  public ModelAndView resetMfa(@AuthenticationPrincipal Principal user,
      HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    setMfaAuthenticated(request, "false");
    /**
     * reset mfa
     */
    getUserService().updateMfaUserKey(user.getName(), "", "");
    model.addObject("message", "MFA code succesfully reset");
    model.setViewName(ACTION_CHECK_MFA);
    return model;
  }



  /**
   * check if mfa is valid
   * 
   * @param mfaCode
   * @param base32Secret
   * @return
   * @throws GeneralSecurityException
   */
  private boolean isMfaCodeValid(String mfaCode, String base32Secret)
      throws GeneralSecurityException {
    return mfaCode.equals(TimeBasedOneTimePasswordUtil.generateCurrentNumberString(base32Secret));
  }

  /**
   * retrieve Base32Secret from db
   * 
   * @param user
   * @return
   */
  private String getBase32Secret(Principal user) {
    String base32Secret = null;
    if (user != null) {
      base32Secret = getUserService().getUser(user.getName()).getBase32Secret();
      logger.debug("base32Secret {}", base32Secret);
    }
    return base32Secret;
  }

  /**
   * get return view with MFA Check
   * 
   * @param model
   * @param viewName
   * @param request
   * @param base32Secret
   */
  private ModelAndView getReturnView(ModelAndView model, String viewName,
      HttpServletRequest request, Principal user) {
    model.addObject("action", viewName);

    if (isMfaAuthenticated(request)) {
      model.setViewName(viewName);
    } else if (!isMfaAuthenticated(request)) {
      String base32Secret = getBase32Secret(user);
      if (base32Secret == null || "".equals(base32Secret.trim())) {
        /**
         * insert QR code
         */
        String generatedBase32Secret = TimeBasedOneTimePasswordUtil.generateBase32Secret();
        String keyId = user.getName() + "@teleassistenzainfamiglia.it";
        model.addObject("generatedBase32Secret", generatedBase32Secret);
        model.addObject("keyId", keyId);
        model.addObject("qrcode_url",
            TimeBasedOneTimePasswordUtil.qrImageUrl(keyId, generatedBase32Secret));

        /**
         * set isMfaAuthenticated = false
         */
        request.getSession().setAttribute("isMfaAuthenticated", false);
        model.setViewName(ACTION_INSERT_MFA);
      } else {
        /**
         * check authentication
         */
        model.setViewName(ACTION_CHECK_MFA);
      }
    }
    return model;
  }

}
