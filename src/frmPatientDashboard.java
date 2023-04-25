import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPatientDashboard extends JFrame {
    private JButton logOutButton;
    private JButton startSelfDiagButton;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JButton accountSettingButton;
    private JPanel panel;
    private JPanel searchPanel;
    private JPanel recentPanel;
    private JTextArea recent1Area;
    private JTextArea recent2Area;
    private JLabel insLabel;
    private JLabel titleLabel;
    private JLabel recentLabel;
    private JButton BOOKButton;

    public frmPatientDashboard() {
        setContentPane(panel);
        setTitle("mindfulNESS - Dashboard");
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

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        startSelfDiagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmTest test = new frmTest();
                test.setVisible(true);
                setVisible(false);
            }
        });
        BOOKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmBooking booking = new frmBooking();
                booking.setVisible(true);
                setVisible(false);
            }
        });
    }
}
