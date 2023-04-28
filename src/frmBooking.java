import javax.swing.*;

public class frmBooking extends JFrame {
    private JButton logOutButton;
    private JButton submitBookingButton;
    private JButton goBackButton;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JLabel idLabel;
    private JTextField idField;
    private JLabel clinicLabel;
    private JButton refreshButton;
    private JTable resultTable;
    private static frmBooking instance;
    public static synchronized frmBooking getInstance(){
        if(instance == null){
            instance = new frmBooking();
        }
        return instance;
    }

    private frmBooking() {
        setContentPane(panel);
        setTitle("mindfulNESS - Make a booking");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ConnectSQL.showAvailableBooking(resultTable);

        logOutButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Logged out! See you again", "Success!", JOptionPane.WARNING_MESSAGE);
                    Thread.sleep(3000);
                    System.exit(0);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        goBackButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                frmPatientDashboard.getInstance().setVisible(true);
                setVisible(false);
            }
        });
        submitBookingButton.addActionListener(e -> {
            if (idField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Please input any possible ID!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int option = JOptionPane.showConfirmDialog(null, "Confirm booking with ID " + idField.getText() + " ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                if (ConnectSQL.submitBooking("1", idField.getText())) {
                    JOptionPane.showMessageDialog(null, "Booked! Please come on time.", "Warning", JOptionPane.WARNING_MESSAGE);
                    ConnectSQL.showAvailableBooking(resultTable);
                } else {
                    JOptionPane.showMessageDialog(null, idField.getText() + " is already booked! Please try again", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        refreshButton.addActionListener(e -> ConnectSQL.showAvailableBooking(resultTable));
    }
}
