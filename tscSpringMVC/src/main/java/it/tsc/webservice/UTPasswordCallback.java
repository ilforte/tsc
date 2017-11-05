package it.tsc.webservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.beans.factory.annotation.Autowired;

import it.tsc.service.UserService;

public class UTPasswordCallback implements CallbackHandler {
  @Autowired
  private UserService userService;

  private Map<String, String> passwords = new HashMap<String, String>();

  public UTPasswordCallback() {
    passwords.put("cxf", "cxf");
  }

  @Override
  public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    for (Callback callback : callbacks) {
      WSPasswordCallback pc = (WSPasswordCallback) callback;

      String pass = passwords.get(pc.getIdentifier());
      if (pass != null) {
        pc.setPassword(pass);
        return;
      }
    }

  }

}
