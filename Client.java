import java.net.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import javax.crypto.Mac;

//Class that handles client side communication
public class Client {

      // Define needed objects/variables
      static byte[] key = null;
      static Security security;

      // Reads from the command line and starts the client.
      public static void main(String args[]) {
            if (args.length != 2) {
                  System.out.println("Usage: java Client <host> <port>");
                  return;
            }
            security = new Security();

            // If they want authentication, verify their password.
            if (security.authentication) {
                  PasswordTools.verifyPassword(Paths.get("client_private", "pass"));
            }

      }
}