package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ViewPassengerLog extends JFrame {

    JTable table;

    public ViewPassengerLog() {
        setTitle("Passenger Log Viewer");
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Passenger Log Entries", SwingConstants.CENTER);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(heading, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        showLogData();

        setSize(800, 500);
        setLocation(350, 200);
        setVisible(true);
    }

    private void showLogData() {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM passenger_log ORDER BY log_time DESC";
            ResultSet rs = conn.s.executeQuery(query);

            // Create a table model
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Nationality");
            model.addColumn("Log Time");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("nationality"),
                    rs.getTimestamp("log_time")
                });
            }

            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading passenger log");
        }
    }

    public static void main(String[] args) {
        new ViewPassengerLog();
    }
}