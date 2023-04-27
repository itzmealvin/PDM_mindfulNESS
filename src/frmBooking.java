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
    private JTable resultTable;


    public frmBooking() {
        setContentPane(panel);
        setTitle("mindfulNESS - Make a booking");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        ConnectSQL.showAvailableBooking(resultTable);

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
                if (idField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Please input any possible ID!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int option = JOptionPane.showConfirmDialog(null, "Confirm booking with ID " + idField.getText() + " ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    if (ConnectSQL.submitBooking("1", idField.getText()) == true) {
                        JOptionPane.showMessageDialog(null, "Booked! Please come on time.", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Something went wrong! Please try again", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectSQL.showAvailableBooking(resultTable);
            }
        });
    }
}
