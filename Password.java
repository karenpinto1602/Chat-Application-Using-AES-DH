
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.nio.file.*;
import java.security.MessageDigest;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

//A helper file for comparing password entered with hashed password stored in secure file.
class PasswordTools {

    public static void main(String args[]) {
        byte bytes[] = null;
        byte temp[] = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update("Karen".getBytes());
            bytes = md.digest();

            Files.write(Paths.get("server_private", "pass"), bytes, StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            /*
             * Files.write(Paths.get("client_private", "pass"), bytes,
             * StandardOpenOption.CREATE, StandardOpenOption.APPEND);
             */
        } catch (Exception e) {
            System.out.println("Failed to write file");
        }
        System.out.print(Arrays.toString(bytes));
        System.out.println();
        try {
            temp = Files.readAllBytes(Paths.get("server_private", "pass"));
            // temp = Files.readAllBytes(Paths.get("client", "pass"));
        } catch (Exception e) {
            System.out.println("Failed to read file");
        }
        System.out.print(Arrays.toString(temp));

    }

    // Hash the password and compare it to the hash stored in the file.
    public static boolean validPassword(String password, Path path) {
        byte byteData[] = null;
        byte toCompare[] = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byteData = md.digest();
            toCompare = Files.readAllBytes(path);
        } catch (Exception e) {
            System.out.println("Failed to read file");
        }
        if (Arrays.equals(toCompare, byteData)) {
            return true;
        }
        return false;
    }

    // Prompts the user to enter their password and then verifies it.
    static void verifyPassword(Path path) {
        Scanner reader = new Scanner(System.in);
        System.out.println("What is your password?");
        while (true) {
            if (validPassword(reader.nextLine(), path)) {
                System.out.println("Authenticated!");
                break;
            } else {
                System.out.println("Incorrect password, try again.");
            }
        }
    }
}
