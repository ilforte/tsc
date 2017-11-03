package it.tsc.webservice.handler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SOAPHeaderHandler implements SOAPHandler<SOAPMessageContext> {
  private static final Logger LOGGER = LoggerFactory.getLogger(SOAPHeaderHandler.class);
  private String username;
  private String password;

  /**
   * Handles SOAP message. If SOAP header does not already exist, then method will created new SOAP
   * header. The username and password is added to the header as the credentials to authenticate
   * user. If no user credentials is specified every call to web service will fail.
   *
   * @param context SOAP message context to get SOAP message from
   * @return true
   */
  @Override
  public boolean handleMessage(SOAPMessageContext context) {
    try {
      SOAPMessage message = context.getMessage();
      SOAPHeader header = message.getSOAPHeader();
      SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
      if (header == null) {
        header = envelope.addHeader();
      }
      QName qNameUserCredentials = new QName("https://your.target.namespace/", "UserCredentials");
      SOAPHeaderElement userCredentials = header.addHeaderElement(qNameUserCredentials);

      QName qNameUsername = new QName("https://your.target.namespace/", "Username");
      SOAPHeaderElement username = header.addHeaderElement(qNameUsername);
      username.addTextNode(this.username);
      QName qNamePassword = new QName("https://your.target.namespace/", "Password");
      SOAPHeaderElement password = header.addHeaderElement(qNamePassword);
      password.addTextNode(this.password);

      userCredentials.addChildElement(username);
      userCredentials.addChildElement(password);

      message.saveChanges();
      // TODO: remove this writer when the testing is finished
      StringWriter writer = new StringWriter();
      message.writeTo(new StringOutputStream(writer));
      LOGGER.debug("SOAP message: \n" + writer.toString());
    } catch (SOAPException e) {
      LOGGER.error("Error occurred while adding credentials to SOAP header.", e);
    } catch (IOException e) {
      LOGGER.error("Error occurred while writing message to output stream.", e);
    }
    return true;
  }

  // TODO: remove this class after testing is finished
  private static class StringOutputStream extends OutputStream {
    private StringWriter writer;

    public StringOutputStream(StringWriter writer) {
      this.writer = writer;
    }

    @Override
    public void write(int b) throws IOException {
      writer.write(b);
    }
  }

  @Override
  public boolean handleFault(SOAPMessageContext context) {
    LOGGER.debug("handleFault has been invoked.");
    return true;
  }

  @Override
  public void close(MessageContext context) {
    LOGGER.debug("close has been invoked.");
  }

  @Override
  public Set<QName> getHeaders() {
    LOGGER.debug("getHeaders has been invoked.");
    return null;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
