package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    JButton btnFlight, btnAddCustomer, btnBookFlight, btnBoardingPass;
    JButton btnJourney, btnViewBooking, btnPassengerLog, btnCancel;

    public Home() {
        setTitle("BANGLADESH AIRLINE");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("BANGLADESH AIRLINE");
        heading.setBounds(450, 30, 600, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 32));
        heading.setForeground(new Color(0, 102, 204));
        add(heading);

        // Row 1
        btnFlight = new JButton("Flight Details");
        btnFlight.setBounds(200, 100, 180, 40);
        btnFlight.addActionListener(this);
        add(btnFlight);

        btnAddCustomer = new JButton("Add Customer");
        btnAddCustomer.setBounds(400, 100, 180, 40);
        btnAddCustomer.addActionListener(this);
        add(btnAddCustomer);

        btnBookFlight = new JButton("Book Flight");
        btnBookFlight.setBounds(600, 100, 180, 40);
        btnBookFlight.addActionListener(this);
        add(btnBookFlight);

        btnBoardingPass = new JButton("Boarding Pass");
        btnBoardingPass.setBounds(800, 100, 180, 40);
        btnBoardingPass.addActionListener(this);
        add(btnBoardingPass);

        // Row 2
        btnJourney = new JButton("Journey Details");
        btnJourney.setBounds(250, 180, 180, 40);
        btnJourney.addActionListener(this);
        add(btnJourney);

        btnViewBooking = new JButton("View Booking");
        btnViewBooking.setBounds(450, 180, 180, 40);
        btnViewBooking.addActionListener(this);
        add(btnViewBooking);

        btnPassengerLog = new JButton("Passenger Log");
        btnPassengerLog.setBounds(650, 180, 180, 40);
        btnPassengerLog.addActionListener(this);
        add(btnPassengerLog);

        // Row 3
        btnCancel = new JButton("Cancel Tickets");
        btnCancel.setBounds(400, 260, 200, 40);
        btnCancel.addActionListener(this);
        add(btnCancel);

        JLabel welcome = new JLabel("\"Welcome\"");
        welcome.setBounds(500, 320, 200, 30);
        welcome.setFont(new Font("Tahoma", Font.ITALIC, 20));
        add(welcome);

        setSize(1200, 450);
        setLocation(200, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == btnFlight) {
            new FlightInfo();
        } else if (source == btnAddCustomer) {
            new AddCustomer();
        } else if (source == btnBookFlight) {
            new BookFlight();
        } else if (source == btnBoardingPass) {
            new BoardingPass();
        } else if (source == btnJourney) {
            new JourneyDetails();
        } else if (source == btnViewBooking) {
            new ViewBookings();
        } else if (source == btnPassengerLog) {
            new ViewPassengerLog();
        } else if (source == btnCancel) {
            new Cancel();
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}