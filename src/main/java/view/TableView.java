package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TableView extends JFrame {

    public TableView(String[] tableHeader, Object[][] tableData, String title) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle(title);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setBackground(Color.cyan);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        JTable table = new JTable(tableData, tableHeader);
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getTableHeader().setBackground(Color.cyan);
        table.getTableHeader().setFont(new Font("Times New Roman", Font.BOLD, 12));
        table.setBackground(Color.cyan);
        table.setEnabled(false);

        scrollPane.setViewportView(table);
    }
}