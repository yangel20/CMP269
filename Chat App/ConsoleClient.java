import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

class ConsoleClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 59001;

    private static class IncomingMessageHandler extends Thread {

        private BufferedReader reader;

        public IncomingMessageHandler(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Disconnected from server: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        System.out.printf("Connecting to chat server at %s:%d...\n", SERVER_ADDRESS, PORT);

        Socket socket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;

        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread incomingMessageHandler = new IncomingMessageHandler(reader);
            incomingMessageHandler.setDaemon(true);
            incomingMessageHandler.start();

            System.out.println("Connected! Type your username when prompted.\n");

            // try-with-resources ensures scanner is always closed
            try (Scanner scanner = new Scanner(System.in)) {
                while (scanner.hasNextLine()) {
                    String message = scanner.nextLine();

                    if (message.equalsIgnoreCase("QUIT")) {
                        System.out.println("Disconnecting...");
                        break;
                    }

                    writer.println(message);
                }
            }

        } catch (IOException e) {
            System.out.println("Error connecting to the server: " + e.getMessage());
        } finally {
            if (reader != null) {
                try { reader.close(); } catch (IOException e) {
                    System.out.println("Error closing reader: " + e.getMessage());
                }
            }
            if (writer != null) writer.close();
            if (socket != null) {
                try { socket.close(); } catch (IOException e) {
                    System.out.println("Error closing socket: " + e.getMessage());
                }
            }
        }
    }
}