import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPEchoClient {

    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change this to the server's IP address
        int portNumber = 5555; // Change this to the server's port

        try {
            Socket socket = new Socket(serverAddress, portNumber);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter message to send to server (type 'exit' to quit): ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                // Send the message to the server
                writer.println(message);

                // Receive and print the echoed message from the server
                String echoedMessage = reader.readLine();
                System.out.println("Received from server: " + echoedMessage);
            }

            // Close resources
            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

