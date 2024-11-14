/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tugas.pkg1;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tugas1AplikasiCekNomor extends JFrame {
    private JTextField inputField;
    private JButton checkButton;
    private JLabel resultLabel;

    public Tugas1AplikasiCekNomor() {
        // Set up frame
        setTitle("Cek Nomor Genap/Ganjil");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        inputField = new JTextField(10);
        checkButton = new JButton("Cek");
        resultLabel = new JLabel("Hasil: ");

        // Set input placeholder behavior
        inputField.setForeground(Color.GRAY);
        inputField.setText("Masukkan angka...");
        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Masukkan angka...")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setForeground(Color.GRAY);
                    inputField.setText("Masukkan angka...");
                }
            }
        });

        // Add KeyAdapter to input field to allow only numbers
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume(); // ignore event
                }
            }
        });

        // Set up panel and layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Masukkan angka: "), gbc);

        gbc.gridx = 1;
        panel.add(inputField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(checkButton, gbc);

        gbc.gridy = 2;
        panel.add(resultLabel, gbc);

        // Add action listener to the button
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateAndCheckNumber();
            }
        });

        // Add panel to frame
        add(panel);
    }

    private void validateAndCheckNumber() {
        String input = inputField.getText().trim();
        if (input.isEmpty() || input.equals("Masukkan angka...")) {
            JOptionPane.showMessageDialog(this, "Masukkan angka terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int number = Integer.parseInt(input);
            String evenOdd = (number % 2 == 0) ? "Genap" : "Ganjil";
            boolean isPrime = isPrime(number);
            String primeResult = isPrime ? " dan adalah bilangan prima." : " dan bukan bilangan prima.";
            resultLabel.setText("Hasil: " + evenOdd + primeResult);
            JOptionPane.showMessageDialog(this, "Hasil: " + evenOdd + primeResult);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid! Harap masukkan angka.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true; // 2 is prime
        }
        if (number % 2 == 0) {
            return false; // eliminate even numbers
        }
        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tugas1AplikasiCekNomor app = new Tugas1AplikasiCekNomor();
            app.setVisible(true);
        });
    }
}
