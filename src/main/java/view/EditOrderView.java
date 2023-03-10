package view;

import model.ClientOrder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EditOrderView extends JFrame {

    private final JButton submitBtn;
    private final JTextField quantityFld;
    private final JComboBox<Integer> clientIdComboBox;
    private final JComboBox<Integer> productIdComboBox;

    public EditOrderView(ArrayList<Integer> clientIds, ArrayList<Integer> productIds, ClientOrder order) {
        int idOrder = order.getIdOrder();
        int idProduct = order.getIdProduct();
        int idClient = order.getIdClient();
        int quantity = order.getQuantity();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Edit comanda");
        setBounds(100, 100, 250, 250);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(Color.cyan);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Edit comanda cu id " + idOrder);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        titleLabel.setBounds(45, 20, 150, 20);
        contentPane.add(titleLabel);

        JLabel clientIdLabel = new JLabel("Client ID:");
        clientIdLabel.setBounds(20, 65, 60, 13);
        contentPane.add(clientIdLabel);

        JLabel productIdLabel = new JLabel("Produs ID:");
        productIdLabel.setBounds(20, 100, 80, 13);
        contentPane.add(productIdLabel);

        JLabel quantityLabel = new JLabel("Cantitate:");
        quantityLabel.setBounds(20, 135, 55, 13);
        contentPane.add(quantityLabel);

        quantityFld = new JTextField("" + quantity);
        quantityFld.setColumns(10);
        quantityFld.setBounds(75, 133, 35, 20);
        contentPane.add(quantityFld);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(75, 175, 85, 21);
        submitBtn.setBackground(Color.blue);
        submitBtn.setForeground(Color.white);
        contentPane.add(submitBtn);

        clientIdComboBox = new JComboBox(clientIds.toArray());
        clientIdComboBox.setBounds(77, 61, 50, 20);
        clientIdComboBox.setSelectedItem(idClient);
        contentPane.add(clientIdComboBox);

        productIdComboBox = new JComboBox(productIds.toArray());
        productIdComboBox.setBounds(87, 96, 50, 20);
        productIdComboBox.setSelectedItem(idProduct);
        contentPane.add(productIdComboBox);
    }

    public void addSubmitListener(ActionListener s) {
        submitBtn.addActionListener(s);
    }

    public int getClientId() {
        return (Integer) clientIdComboBox.getSelectedItem();
    }

    public int getProductId() {
        return (Integer) productIdComboBox.getSelectedItem();
    }

    public String getQuantity() {
        return quantityFld.getText();
    }

    public void showError(String s) {
        JOptionPane.showMessageDialog(this, s, "Eroare date de intrare", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String s) {
        JOptionPane.showMessageDialog(this, s, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
