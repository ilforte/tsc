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

@Controller
@RequestMapping("/")
public class AppController extends BaseController {
  private static Logger logger = LoggerFactory.getLogger(AppController.class);
  private String ACTION_ADMIN = "admin";
  private String ACTION_USER = "user";
  private String ACTION_INSERT_MFA = "addMfaSecurityCode";
  private String ACTION_CHECK_MFA = "checkMfaSecurityCode";

  @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
  public ModelAndView homePage(@RequestParam(value = "ab_codi", required = false) String ab_codi,
      HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    model.addObject("ab_codi", ab_codi);
    model.setViewName("home");
    return model;
  }

  @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
  public ModelAndView adminPage(@RequestParam(value = "ab_codi", required = false) String ab_codi,
      @AuthenticationPrincipal Principal user, HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    request.getSession().setAttribute("ab_codi", ab_codi);

    model.addObject("ab_codi", ab_codi);
    model.addObject("roles", roles());
    model = setReturnView(model, ACTION_ADMIN, request, user);
    return model;
  }

  @RequestMapping(value = { "/user" }, method = RequestMethod.GET)
  public ModelAndView userPage(@RequestParam(value = "ab_codi", required = false) String ab_codi,
      @AuthenticationPrincipal Principal user, HttpServletRequest request) {
    ModelAndView model = new ModelAndView();
    request.getSession().setAttribute("ab_codi", ab_codi);
    model.addObject("ab_codi", ab_codi);
    model.addObject("roles", roles());
    model = setReturnView(model, ACTION_USER, request, user);
    return model;
  }

  @RequestMapping(value = { "/addMfaSecurityCode" }, method = RequestMethod.GET)
  public ModelAndView addMfaSecurityCode(@AuthenticationPrincipal Principal user,
      HttpServletRequest request, @RequestParam(value = "mfaCode", required = true) String mfaCode,
      @RequestParam(value = "keyId", required = true) String keyId,
      @RequestParam(value = "generatedBase32Secret", required = true) String generatedBase32Secret,
      @RequestParam(value = "login", required = true) String login)
      throws GeneralSecurityException {
    ModelAndView model = new ModelAndView();
    /**
     * insert in database if mfa is valid
     */
    if (isMfaCodeValid(mfaCode, generatedBase32Secret)) {
      getUserService().updateMfaUserKey(user.getName(), keyId, generatedBase32Secret);
      model.addObject("message", "MFA correctly registered");
      model.setViewName(ACTION_CHECK_MFA);
    } else {
      model.addObject("error", "MFA invalid doesn't check with code");
      model.setViewName(ACTION_INSERT_MFA);
    }

    return model;
  }

  @RequestMapping(value = { "/checkMfaSecurityCode" }, method = RequestMethod.GET)
  public ModelAndView checkMfaSecurityCode(@AuthenticationPrincipal Principal user,
      HttpServletRequest request, @RequestParam(value = "mfaCode", required = true) String mfaCode)
      throws GeneralSecurityException {
    ModelAndView model = new ModelAndView();
    /**
     * check if mfa is valid
     */
    String base32Secret = getBase32Secret(user);

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    logger.debug("auth: {}", auth.getAuthorities().toString());
    if (isMfaCodeValid(mfaCode, base32Secret)) {
      setMfaAuthenticated(request, "true");
      // if (Role.valueOf(auth.getAuthorities().toString()).equals(Role.ROLE_ADMIN)) {
      model.setViewName(ACTION_ADMIN);
      // } else if (Role.valueOf(auth.getAuthorities().toString()).equals(Role.ROLE_USER)) {
      // model.setViewName(ACTION_USER);
      // }

    } else {
      model.addObject("error", "MFA invalid doesn't check with code");
      setMfaAuthenticated(request, "false");
      model.setViewName(ACTION_CHECK_MFA);
    }
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
   * set return view with MFA Check
   * 
   * @param model
   * @param viewName
   * @param request
   * @param base32Secret
   */
  private ModelAndView setReturnView(ModelAndView model, String viewName,
      HttpServletRequest request, Principal user) {
    model.addObject("action", viewName);
    request.setAttribute("page", viewName);

    if (isMfaAuthenticated(request)) {
      /**
       * generate QR code
       */
      model.addObject("generateQrCode", false);
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
