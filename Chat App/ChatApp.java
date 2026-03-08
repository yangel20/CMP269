import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ChatApp extends JFrame {

    private static final String DEFAULT_HOST = "localhost";
    private static final int PORT = 59001;

    // Connection state
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    // UI components
    private JTextArea  chatArea;
    private JTextField messageField;
    private JButton    sendButton;
    private JTextField ipField;
    private JTextField usernameField;
    private JButton    connectButton;
    private JLabel     statusLabel;

    private class IncomingMessageHandler extends Thread {

        private BufferedReader reader;

        public IncomingMessageHandler(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    final String line = message;

                    SwingUtilities.invokeLater(() -> appendToChat(line + "\n"));

                    if (line.equals("SERVER: Enter your username:")) {
                        writer.println(usernameField.getText().trim());
                    }
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> {
                    appendToChat("\n[Disconnected from server]\n");
                    setConnectedState(false);
                    statusLabel.setText("Disconnected");
                    statusLabel.setForeground(Color.RED);
                });
            }
        }
    }

    // Constructor — build the UI
    public ChatApp() {
        super("Chat Client");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(660, 480);
        setMinimumSize(new Dimension(450, 350));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cleanUp();
                dispose();
                System.exit(0);
            }
        });

        buildUI();
    }

    // Build all UI panels
    private void buildUI() {
        setLayout(new BorderLayout());

        ipField = new JTextField(DEFAULT_HOST, 10);
        usernameField = new JTextField(10);
        usernameField.setToolTipText("Enter your username");

        connectButton = new JButton("Connect");
        connectButton.setBackground(new Color(76, 175, 80));
        connectButton.setForeground(Color.WHITE);
        connectButton.setFocusPainted(false);
        connectButton.addActionListener(e -> handleConnect());

        statusLabel = new JLabel("Not connected");
        statusLabel.setForeground(Color.GRAY);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        topPanel.setBackground(new Color(240, 240, 240));
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        topPanel.add(new JLabel("IP:"));
        topPanel.add(ipField);
        topPanel.add(new JLabel("Username:"));
        topPanel.add(usernameField);
        topPanel.add(connectButton);
        topPanel.add(statusLabel);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        chatArea.setMargin(new Insets(5, 5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        messageField = new JTextField();
        messageField.setEnabled(false);
        messageField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));

        messageField.addActionListener(e -> handleSend());

        sendButton = new JButton("Send");
        sendButton.setEnabled(false);
        sendButton.setBackground(new Color(33, 150, 243));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.addActionListener(e -> handleSend());

        JPanel bottomPanel = new JPanel(new BorderLayout(8, 0));
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton,   BorderLayout.EAST);

        add(topPanel,    BorderLayout.NORTH);
        add(scrollPane,  BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Connect button handler
    private void handleConnect() {
        String host     = ipField.getText().trim();
        String username = usernameField.getText().trim();

        if (host.isEmpty() || username.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both a server IP and a username.",
                "Missing Info", JOptionPane.WARNING_MESSAGE);
            return;
        }

        new Thread(() -> {
            try {
                socket = new Socket(host, PORT);
                writer = new PrintWriter(socket.getOutputStream(), true);
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                SwingUtilities.invokeLater(() -> {
                    setConnectedState(true);
                    statusLabel.setText("Connected to " + host + " ✓");
                    statusLabel.setForeground(new Color(0, 150, 0));
                });

                // Start the background listener
                IncomingMessageHandler handler = new IncomingMessageHandler(reader);
                handler.setDaemon(true);
                handler.start();

            } catch (IOException e) {
                SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(ChatApp.this,
                        "Could not connect to " + host + ":" + PORT
                        + "\nIs the server running?",
                        "Connection Error", JOptionPane.ERROR_MESSAGE));
            }
        }).start();
    }

    // Send button / Enter key handler
    private void handleSend() {
        if (writer == null) return;

        String text = messageField.getText().trim();
        if (text.isEmpty()) return;

        writer.println(text);
        messageField.setText("");
        messageField.requestFocus();
    }

    // Helpers
    private void appendToChat(String text) {
        chatArea.append(text);
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    private void setConnectedState(boolean connected) {
        connectButton.setEnabled(!connected);
        ipField.setEnabled(!connected);
        usernameField.setEnabled(!connected);
        messageField.setEnabled(connected);
        sendButton.setEnabled(connected);
        if (connected) messageField.requestFocus();
    }

    private void cleanUp() {
        try {
            if (writer != null) writer.close();
            if (reader != null) reader.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatApp app = new ChatApp();
            app.setVisible(true);
        });
    }
}