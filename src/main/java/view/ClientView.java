package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {

    private final JButton addBtn;
    private final JButton editBtn;
    private final JButton deleteBtn;
    private final JButton viewBtn;
    private final JTextField editFld;
    private final JTextField deleteFld;

    public ClientView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Client");
        setBounds(100, 100, 330, 370);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setBackground(Color.cyan);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Operatii Clienti");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        titleLabel.setBounds(60, 20, 200, 25);
        contentPane.add(titleLabel);

        JLabel addLabel = new JLabel("Adauga Client");
        addLabel.setHorizontalAlignment(SwingConstants.CENTER);
        addLabel.setBounds(90, 65, 120, 15);
        contentPane.add(addLabel);

        addBtn = new JButton("Adauga");
        addBtn.setBounds(110, 90, 80, 20);
        addBtn.setBackground(Color.blue);
        addBtn.setForeground(Color.white);
        contentPane.add(addBtn);

        JLabel editLabel = new JLabel("Edit clientul cu id:");
        editLabel.setHorizontalAlignment(SwingConstants.CENTER);
        editLabel.setBounds(90, 130, 120, 15);
        contentPane.add(editLabel);

        editFld = new JTextField();
        editFld.setBounds(205, 129, 35, 20);
        contentPane.add(editFld);
        editFld.setColumns(10);

        editBtn = new JButton("Edit");
        editBtn.setBounds(110, 155, 80, 20);
        editBtn.setBackground(Color.blue);
        editBtn.setForeground(Color.white);
        contentPane.add(editBtn);

        JLabel deleteLabel = new JLabel("Sterge clientul cu id:");
        deleteLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deleteLabel.setBounds(90, 195, 120, 15);
        contentPane.add(deleteLabel);

        deleteFld = new JTextField();
        deleteFld.setColumns(10);
        deleteFld.setBounds(212, 194, 35, 20);
        contentPane.add(deleteFld);

        deleteBtn = new JButton("Sterge");
        deleteBtn.setBounds(110, 220, 80, 20);
        deleteBtn.setBackground(Color.blue);
        deleteBtn.setForeground(Color.white);
        contentPane.add(deleteBtn);

        JLabel viewLabel = new JLabel("Vizualizare clienti");
        viewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        viewLabel.setBounds(90, 260, 120, 15);
        contentPane.add(viewLabel);

        viewBtn = new JButton("View");
        viewBtn.setBounds(110, 285, 80, 20);
        viewBtn.setBackground(Color.blue);
        viewBtn.setForeground(Color.white);
        contentPane.add(viewBtn);
    }

    public void addAddListener(ActionListener a) {
        addBtn.addActionListener(a);
    }

    public void addEditListener(ActionListener e) {
        editBtn.addActionListener(e);
    }

    public void addDeleteListener(ActionListener d) {
        deleteBtn.addActionListener(d);
    }

    public void addViewListener(ActionListener v) {
        viewBtn.addActionListener(v);
    }

    public String getEditClientId() {
        return editFld.getText();
    }

    public String getDeleteClientId() {
        return deleteFld.getText();
    }

    public void showError(String s) {
        JOptionPane.showMessageDialog(this, s, "Eroare date introduse", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String s) {
        JOptionPane.showMessageDialog(this, s, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
