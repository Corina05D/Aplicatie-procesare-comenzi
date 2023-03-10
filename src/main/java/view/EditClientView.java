package view;

import model.Client;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EditClientView extends JFrame {

    private final JButton submitBtn;
    private final JTextField nameFld;
    private final JTextField emailFld;
    private final JTextField addressFld;

    public EditClientView(Client c) {
        int id = c.getIdClient();
        String name = c.getName();
        String email = c.getEmailAddress();
        String address = c.getAddress();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 250, 250);
        setTitle("Edit Client");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(Color.cyan);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Edit client cu id " + id);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        titleLabel.setBounds(45, 20, 150, 20);
        contentPane.add(titleLabel);

        JLabel nameLabel = new JLabel("Nume:");
        nameLabel.setBounds(20, 65, 45, 13);
        contentPane.add(nameLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 100, 45, 13);
        contentPane.add(emailLabel);

        JLabel addressLabel = new JLabel("Adresa:");
        addressLabel.setBounds(20, 135, 55, 13);
        contentPane.add(addressLabel);

        nameFld = new JTextField(name);
        nameFld.setBounds(60, 63, 165, 20);
        contentPane.add(nameFld);
        nameFld.setColumns(10);

        emailFld = new JTextField(email);
        emailFld.setColumns(10);
        emailFld.setBounds(57, 98, 168, 20);
        contentPane.add(emailFld);

        addressFld = new JTextField(address);
        addressFld.setColumns(10);
        addressFld.setBounds(73, 133, 152, 20);
        contentPane.add(addressFld);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(75, 175, 85, 21);
        submitBtn.setBackground(Color.blue);
        submitBtn.setForeground(Color.white);
        contentPane.add(submitBtn);
    }

    public void addSubmitListener(ActionListener s) {
        submitBtn.addActionListener(s);
    }

    public String getClientName() {
        return nameFld.getText();
    }

    public String getEmail() {
        return emailFld.getText();
    }

    public String getAddress() {
        return addressFld.getText();
    }

    public void showError(String s) {
        JOptionPane.showMessageDialog(this, s, "Eroare date de intrare", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String s) {
        JOptionPane.showMessageDialog(this, s, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
