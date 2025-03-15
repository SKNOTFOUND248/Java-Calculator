import java.awt.*;
import javax.swing.*;

class CALCprocess {

    static {
        System.loadLibrary("Algebraic_expression_handling");
    }

    public native String Process(String exp);

    public CALCprocess() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        CALCprocess p = new CALCprocess();
        frame.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("cal.png");
        frame.setIconImage(icon.getImage());
        JTextField textfield = new JTextField();
        textfield.setFont(new Font("Arial", Font.PLAIN, 26));
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setBackground(Color.DARK_GRAY);
        textfield.setForeground(Color.WHITE);
        textfield.setEditable(false);
        frame.add(textfield, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 10));
        buttonPanel.setBounds(400, 500, 300, 100);
        buttonPanel.setBackground(Color.GRAY);
        String[] buttonLabels = { "<-", "(", ")", "^", "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+" };

        JButton[] button = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            button[i] = new JButton(buttonLabels[i]);
            button[i].setFont(new Font("Arial", Font.BOLD, 26));
            button[i].setFocusable(false);
            switch (i) {
                case 0 -> {
                    button[i].setBackground(Color.DARK_GRAY);
                    button[i].setForeground(Color.WHITE);
                }
                case 18 -> {
                    button[i].setBackground(Color.green);
                    button[i].setForeground(Color.BLACK);
                }
                case 16 -> {
                    button[i].setBackground(Color.green);
                    button[i].setForeground(Color.BLACK);
                }
                case 3 -> {
                    button[i].setBackground(Color.DARK_GRAY);
                    button[i].setForeground(Color.WHITE);
                }
                case 7 -> {
                    button[i].setBackground(Color.DARK_GRAY);
                    button[i].setForeground(Color.WHITE);
                }
                case 11 -> {
                    button[i].setBackground(Color.DARK_GRAY);
                    button[i].setForeground(Color.WHITE);
                }
                case 15 -> {
                    button[i].setBackground(Color.DARK_GRAY);
                    button[i].setForeground(Color.WHITE);
                }
                case 19 -> {
                    button[i].setBackground(Color.DARK_GRAY);
                    button[i].setForeground(Color.WHITE);
                }
                default -> {
                    button[i].setBackground(Color.GRAY);
                }
            }
            button[i].addActionListener(e -> {
                String s = textfield.getText();
                JButton buttonClicked = (JButton) e.getSource();
                if (null == buttonClicked.getText()) {
                    textfield.setText(s + "" + buttonClicked.getText());
                } else
                    switch (buttonClicked.getText()) {
                        case "=" -> textfield.setText(p.Process((textfield.getText())));
                        case "C" -> textfield.setText("");
                        case "<-" -> textfield.setText(s.substring(0, s.length() - 1));
                        default -> textfield.setText(s + "" + buttonClicked.getText());
                    }
            });
            buttonPanel.add(button[i]);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CALCprocess();
    }
}
