import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {
    JFrame frame = new JFrame();
    Color colorOrange = new Color(255, 152, 0);
    JLabel title = new JLabel("FixTrack");
    JLabel title2 = new JLabel("Pro");
    Icon menuIcon = new ImageIcon("hamburger32rev.png");
    JButton menuBtn = new JButton();
    JLabel txt = new JLabel("Login");
    Font font1 = new Font("SansSerif", Font.BOLD, 34);
    Font font3 = new Font("SansSerif", Font.BOLD, 20);
    JPasswordField passwordText;
    private JTextField userText;
    private JLabel labelUser, labelPass;
    private JButton loginBtn;

    private static HashMap<String, String> loadUsers(String filePath) {
        HashMap<String, String> userMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    userMap.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the user database: " + e.getMessage());
        }
        return userMap;
    }

    LoginPage() {
        String filePath = "users.txt";

        // Load users from the text file
        HashMap<String, String> userDatabase = loadUsers(filePath);


        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 50));
        header.setBackground(Color.BLACK);

        title.setFont(font1);
        title.setForeground(Color.WHITE);
        title2.setFont(font1);
        title2.setForeground(colorOrange);

        panel.add(title2, BorderLayout.LINE_START);
        header.add(title, BorderLayout.LINE_START);

        panel.setBackground(Color.BLACK);
        JPopupMenu dropdownMenu = new JPopupMenu();
      
        JMenuItem option1 = new JMenuItem("DashBoard");
        dropdownMenu.add(option1);
 
        option1.addActionListener(e -> {
            Dashboard d = new Dashboard();
            d.setVisible(true);
            frame.dispose();
        });
   
      
        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropdownMenu.show(menuBtn, 0, menuBtn.getHeight());
            }
        });
        panel.add(menuBtn);
        header.add(panel);

        menuBtn.setIcon(menuIcon);
        menuBtn.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        menuBtn.setBackground(Color.BLACK);
        menuBtn.setFocusPainted(false);
        menuBtn.addActionListener(this);
        panel.add(title2, BorderLayout.LINE_START);
        header.add(menuBtn, BorderLayout.LINE_END);

        frame.add(header, BorderLayout.PAGE_START);

        JPanel body = new JPanel();
        body.setBackground(colorOrange);
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        body.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txt.setForeground(Color.BLACK);
        txt.setFont(font1);
        txt.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelUser = new JLabel("Username");
        labelUser.setForeground(Color.black);
        labelUser.setFont(font3);
        labelUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        userText = new JTextField(20);
        userText.setFont(font3);
        userText.setMaximumSize(new Dimension(250, 30));

        labelPass = new JLabel("Password");
        labelPass.setFont(font3);
        labelPass.setForeground(Color.black);
        labelPass.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordText = new JPasswordField(20);
        passwordText.setFont(font3);
        passwordText.setMaximumSize(new Dimension(250, 30));

        loginBtn = new JButton("Login");
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(colorOrange);
        loginBtn.setFont(font3);
        loginBtn.setFocusable(false);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.addActionListener(this);

        body.add(txt);
        body.add(Box.createRigidArea(new Dimension(0, 30))); 
        body.add(labelUser);
        body.add(userText);
        body.add(Box.createRigidArea(new Dimension(0, 20))); 
        body.add(labelPass);
        body.add(passwordText);
        body.add(Box.createRigidArea(new Dimension(0, 50))); 
        body.add(loginBtn);

        frame.add(body, BorderLayout.CENTER);


        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(0, 50));
        footer.setBackground(Color.BLACK);
        frame.add(footer, BorderLayout.PAGE_END);

        frame.setTitle("FixTrackPro");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
        
                if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                    AdminDashboard d = new AdminDashboard();
                    d.setVisible(true);
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + username + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}



