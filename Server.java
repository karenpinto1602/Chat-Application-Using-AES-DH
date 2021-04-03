import java.nio.file.*;
//import javax.crypto.Mac;

//Server side of the chat
public class Server {
      // reads users input and listens on the specific port.
      public static void main(String[] args) throws Exception {
            int portNumber = 8080;
            Security security = null;
            if (args.length != 1) {
                  System.out.println("Usage: java server <port>");
                  return;
            }
            security = new Security();

            // If they want authentication, verify their password.
            if (security.authentication) {
                  PasswordTools.verifyPassword(Paths.get("server_private", "pass"));
            }

      }
}
