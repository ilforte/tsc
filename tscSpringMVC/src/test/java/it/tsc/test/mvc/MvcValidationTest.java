package it.tsc.test.mvc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import it.tsc.config.ServiceConfig;
import it.tsc.config.WebAppConfig;
import it.tsc.domain.Allarm;
import it.tsc.domain.PortalUser;

/**
 * @author astraservice
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({@ContextConfiguration(value = "classpath:spring-security.xml"),
    @ContextConfiguration(classes = WebAppConfig.class),
    @ContextConfiguration(classes = ServiceConfig.class)})
@WebAppConfiguration
public class MvcValidationTest {
  private static Logger logger = LoggerFactory.getLogger(MvcValidationTest.class);

  @Autowired
  private WebApplicationContext webApplicationContext;
  @Autowired
  private FilterChainProxy filterChain;
  private MockMvc mvc;
  private Gson gson = new Gson();

  /**
   * 
   */
  public MvcValidationTest() {
    // TODO Auto-generated constructor stub
  }


  @Before
  public void setup() throws Exception {
    logger.debug("context {}", webApplicationContext);
    mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(SecurityMockMvcConfigurers.springSecurity()).addFilter(filterChain).build();
  }

  @Test
  @WithMockUser(roles = "ADMIN", username = "matteo")
  public void testUpdateAllarm() throws Exception {
    Allarm allarm = new Allarm();
    allarm.setSerial_uuid("123");
    // omit email
    ResultMatcher ok = MockMvcResultMatchers.status().isOk();
    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.post("/user/allarmService/updateAllarm");
    builder.content(gson.toJson(allarm));
    builder.header("Accept", "application/json");
    builder.header("Content-Type", "application/json");
    builder.with(SecurityMockMvcRequestPostProcessors.csrf());

    MvcResult result = mvc.perform(builder).andExpect(ok).andReturn();
    String content = result.getResponse().getContentAsString();
    logger.debug("content {}", content);
  }

  @Test
  @WithMockUser(roles = "ADMIN", username = "matteo")
  public void testJsonInsertUser() throws Exception {
    PortalUser user = new PortalUser();
    user.setEmail("matteo@telesoccorso-infamiglia.it");
    user.setRole("ROLE_USER");
    user.setUsername("matteo");
    user.setPassword("matteo");

    // omit email
    ResultMatcher ok = MockMvcResultMatchers.status().isOk();
    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.post("/admin/userService/jsonAddUser");
    builder.content(gson.toJson(user));
    builder.header("Accept", "application/json");
    builder.header("Content-Type", "application/json");
    builder.with(SecurityMockMvcRequestPostProcessors.csrf());

    MvcResult result = mvc.perform(builder).andExpect(ok).andReturn();
    String content = result.getResponse().getContentAsString();
    logger.debug("content {}", content);
  }

  @Test
  @WithMockUser(roles = "ADMIN", username = "matteo")
  public void testRemoveUser() throws Exception {
    PortalUser user = new PortalUser();
    user.setUsername("testUser");

    // omit email
    ResultMatcher ok = MockMvcResultMatchers.status().isOk();
    MockHttpServletRequestBuilder builder =
        MockMvcRequestBuilders.post("/admin/userService/jsonRemoveUser");
    builder.content(gson.toJson(user));
    builder.header("Accept", "application/json");
    builder.header("Content-Type", "application/json");
    builder.with(SecurityMockMvcRequestPostProcessors.csrf());

    MvcResult result = mvc.perform(builder).andExpect(ok).andReturn();
    String content = result.getResponse().getContentAsString();
    logger.debug("content {}", content);
  }

}
