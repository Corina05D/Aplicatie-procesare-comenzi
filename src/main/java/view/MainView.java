package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    private final JButton clientsBtn;
    private final JButton productsBtn;
    private final JButton ordersBtn;

    public MainView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Managementul Comenzilor");
        setBounds(100, 100, 400, 200);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(Color.cyan);
        contentPane.setLayout(null);

        clientsBtn = new JButton("Clienti");
        clientsBtn.setBounds(30, 80, 85, 20);
        clientsBtn.setBackground(Color.blue);
        clientsBtn.setForeground(Color.white);
        contentPane.add(clientsBtn);

        productsBtn = new JButton("Produse");
        productsBtn.setBounds(140, 80, 85, 20);
        productsBtn.setBackground(Color.blue);
        productsBtn.setForeground(Color.white);
        contentPane.add(productsBtn);

        ordersBtn = new JButton("Comenzi");
        ordersBtn.setBounds(250, 80, 85, 20);
        ordersBtn.setBackground(Color.blue);
        ordersBtn.setForeground(Color.white);
        contentPane.add(ordersBtn);

        JLabel titleLabel = new JLabel("Managementul Comenzilor");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(90, 30, 200, 20);
        contentPane.add(titleLabel);
    }

    public void addClientListener(ActionListener c) {
        clientsBtn.addActionListener(c);
    }

    public void addProductListener(ActionListener p) {
        productsBtn.addActionListener(p);
    }

    public void addOrderListener(ActionListener o) {
        ordersBtn.addActionListener(o);
    }
}
