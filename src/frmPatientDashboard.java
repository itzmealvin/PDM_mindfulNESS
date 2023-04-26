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
    private JTextArea recentArea;
    private JLabel insLabel;
    private JLabel titleLabel;
    private JLabel recentLabel;
    private JButton bookButton;

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
                if (searchField.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Please input any disease name!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                resultArea.selectAll();
                resultArea.replaceSelection("");
                resultArea.setText(ConnectSQL.showSearchQuery(searchField));
                if (ConnectSQL.showSearchQuery(searchField).length() == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid disease!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
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
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmBooking booking = new frmBooking();
                booking.setVisible(true);
                setVisible(false);
            }
        });

        recentArea.selectAll();
        recentArea.replaceSelection("");
        recentArea.setText(ConnectSQL.showPatientBooking("1"));
        ;
    }
}
