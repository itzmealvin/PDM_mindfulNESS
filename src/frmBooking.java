import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmBooking extends JFrame {
    private JButton logOutButton;
    private JButton accountSettingButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dateField;
    private JButton autoSelectButton;
    private JTextField clinicField;
    private JTextField diagField;
    private JButton takeATestButton;
    private JTextField symptomField;
    private JButton resetAllButton;
    private JButton submitBookingButton;
    private JButton goBackButton;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JLabel firstNameLabel;
    private JLabel pronounLabel;
    private JTextField pronounField;
    private JLabel specialistLabel;
    private JLabel diagLabel;
    private JLabel symptomLabel;
    private JLabel lastNameLabel;
    private JLabel dateLabel;
    private JTextField specialistField;
    private JLabel clinicLabel;

    public frmBooking() {
        setContentPane(panel);
        setTitle("mindfulNESS - Make a booking");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmIndex indexPage = new frmIndex();
                indexPage.setVisible(true);
                setVisible(false);
            }
        });

        resetAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        autoSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        takeATestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmTest test = new frmTest();
                test.setVisible(true);
                setVisible(false);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        submitBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
