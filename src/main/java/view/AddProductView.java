package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AddProductView extends JFrame {
    private final JTextField nameFld;
    private final JTextField quantityFld;
    private final JTextField priceFld;
    private final JButton submitBtn;

    public AddProductView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 250, 250);
        setTitle("Adauga produs");
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.cyan);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Adauga produs");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        titleLabel.setBounds(50, 20, 150, 20);
        contentPane.add(titleLabel);

        JLabel nameLabel = new JLabel("Nume:");
        nameLabel.setBounds(20, 65, 45, 13);
        contentPane.add(nameLabel);

        JLabel quantityLabel = new JLabel("Cantitate:");
        quantityLabel.setBounds(20, 100, 60, 13);
        contentPane.add(quantityLabel);

        JLabel priceLabel = new JLabel("Pret:");
        priceLabel.setBounds(20, 135, 55, 13);
        contentPane.add(priceLabel);

        nameFld = new JTextField();
        nameFld.setBounds(60, 63, 165, 20);
        contentPane.add(nameFld);
        nameFld.setColumns(10);

        quantityFld = new JTextField();
        quantityFld.setColumns(10);
        quantityFld.setBounds(75, 98, 35, 20);
        contentPane.add(quantityFld);

        priceFld = new JTextField();
        priceFld.setColumns(10);
        priceFld.setBounds(57, 133, 35, 20);
        contentPane.add(priceFld);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(75, 175, 85, 21);
        submitBtn.setBackground(Color.blue);
        submitBtn.setForeground(Color.white);
        contentPane.add(submitBtn);
    }

    public void addSubmitListener(ActionListener s) {
        submitBtn.addActionListener(s);
    }

    public String getProductName() {
        return nameFld.getText();
    }

    public String getQuantity() {
        return quantityFld.getText();
    }

    public String getPrice() {
        return priceFld.getText();
    }

    public void showError(String s) {
        JOptionPane.showMessageDialog(this, s, "Eroare date de intrare", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String s) {
        JOptionPane.showMessageDialog(this, s, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}