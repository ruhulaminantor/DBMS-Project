package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewBookings extends JFrame {

    JTable table;
    JTextField tfSearch;
    JButton btnSearch, btnPrint;

    public ViewBookings() {
        setTitle("View All Booked Flights");
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // 🔹 Top panel for heading and search
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel heading = new JLabel("Booked Flight Details", SwingConstants.CENTER);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        topPanel.add(heading, BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(new JLabel("Search by Name:"));

        tfSearch = new JTextField(20);
        searchPanel.add(tfSearch);

        btnSearch = new JButton("Search");
        searchPanel.add(btnSearch);

        btnPrint = new JButton("Print");
        searchPanel.add(btnPrint);

        topPanel.add(searchPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // 🔹 Table setup
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load all bookings initially
        loadBookingData("");

        // 🔹 Search action
        btnSearch.addActionListener(e -> {
            String searchText = tfSearch.getText().trim();
            loadBookingData(searchText);
        });

        // 🔹 Print action
        btnPrint.addActionListener(e -> {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        setSize(1000, 600);
        setLocation(250, 150);
        setVisible(true);
    }

    private void loadBookingData(String nameFilter) {
        try {
            Conn conn = new Conn();
            String query = "SELECT r.PNR, r.name, r.aadhar, r.ddate, " +
                           "f.f_name AS flight_name, f.source, f.destination " +
                           "FROM reservation r " +
                           "INNER JOIN flight f ON r.flightcode = f.f_code";

            if (!nameFilter.isEmpty()) {
                query += " WHERE r.name LIKE '%" + nameFilter + "%'";
            }

            ResultSet rs = conn.s.executeQuery(query);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("PNR");
            model.addColumn("Name");
            model.addColumn("Aadhar");
            model.addColumn("Travel Date");
            model.addColumn("Flight Name");
            model.addColumn("Source");
            model.addColumn("Destination");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("PNR"),
                        rs.getString("name"),
                        rs.getString("aadhar"),
                        rs.getString("ddate"),
                        rs.getString("flight_name"),
                        rs.getString("source"),
                        rs.getString("destination")
                });
            }

            table.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading booking data");
        }
    }

    public static void main(String[] args) {
        new ViewBookings();
    }
}