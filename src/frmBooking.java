import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextArea bookingArea;


    public frmBooking() {
        setContentPane(panel);
        setTitle("mindfulNESS - Make a booking");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        if (ConnectSQL.showAvailableBooking().length() == 0) {
            JOptionPane.showMessageDialog(null, "No available booking found! Please try again later", "Message", JOptionPane.WARNING_MESSAGE);
            return;
        }
        bookingArea.selectAll();
        bookingArea.replaceSelection("");
        bookingArea.setText(ConnectSQL.showAvailableBooking());

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmIndex indexPage = new frmIndex();
                indexPage.setVisible(true);
                setVisible(false);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPatientDashboard patientDashboard = new frmPatientDashboard();
                patientDashboard.setVisible(true);
                setVisible(false);
            }
        });
        submitBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ConnectSQL.showAvailableBooking().length() == 0) {
                    JOptionPane.showMessageDialog(null, "No available booking found! Please try again later", "Warning!", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                bookingArea.selectAll();
                bookingArea.replaceSelection("");
                bookingArea.setText(ConnectSQL.showAvailableBooking());

            }
        });
    }
}
