package AIChatBot;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AIChatBot extends JFrame implements ActionListener {
    JTextArea chatArea;
    JTextField inputField;
    JButton sendButton;

    HashMap<String, String> responses;

    public AIChatBot() {
        setTitle("AI Chatbot");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        sendButton = new JButton("Send");

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(this);
        inputField.addActionListener(this);

        setVisible(true);

        setupResponses();
        showMessage("Bot: Hello! I'm your Java ChatBot. Ask me anything!");
    }

    void setupResponses() {
        responses = new HashMap<>();
        responses.put("hi", "Hello there!");
        responses.put("how are you", "I'm doing great, thank you!");
        responses.put("what is your name", "I'm JavaBot, your assistant.");
        responses.put("what is java", "Java is a high-level, class-based, object-oriented programming language.");
        responses.put("bye", "Goodbye! Have a great day.");
        responses.put("what can you do", "I can answer your questions and help with basic Java info.");
    }

    void showMessage(String message) {
        chatArea.append(message + "\n");
    }

    String getBotResponse(String userInput) {
        userInput = userInput.toLowerCase().trim();

        for (String key : responses.keySet()) {
            if (userInput.contains(key)) {
                return responses.get(key);
            }
        }
        return "I'm sorry, I don't understand that yet.";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = inputField.getText();
        if (!userText.isEmpty()) {
            showMessage("You: " + userText);
            String botReply = getBotResponse(userText);
            showMessage("Bot: " + botReply);
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        new AIChatBot();
    }
}
