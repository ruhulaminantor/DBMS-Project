package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FlightInfo extends JFrame {

    public FlightInfo() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JTable table = new JTable();

        try {
            Conn conn = new Conn();

            String query = "SELECT " +
                           "f.f_code AS 'Flight Code', " +
                           "f.f_name AS 'Flight Name', " +
                           "f.source AS 'Source', " +
                           "f.destination AS 'Destination', " +
                           "COUNT(r.PNR) AS 'Total Bookings' " +
                           "FROM flight f " +
                           "LEFT JOIN reservation r ON f.f_code = r.flightcode " +
                           "GROUP BY f.f_code, f.f_name, f.source, f.destination";

            ResultSet rs = conn.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 800, 500);
        add(jsp);

        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FlightInfo();
    }
}