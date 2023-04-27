import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPatientDashboard extends JFrame {
    private JButton logOutButton;
    private JButton startSelfDiagButton;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
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
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        logOutButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                frmIndex indexPage = new frmIndex();
                indexPage.setVisible(true);
                setVisible(false);
                JOptionPane.showMessageDialog(null, "Logged out!", "Success!", JOptionPane.WARNING_MESSAGE);
            }
        });

        searchButton.addActionListener(e -> {
            if (searchField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Please input any disease name!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            resultArea.selectAll();
            resultArea.replaceSelection("");
            resultArea.setText(ConnectSQL.showSearchQuery(searchField.getText()));
            if (ConnectSQL.showSearchQuery(searchField.getText()).length() == 0) {
                JOptionPane.showMessageDialog(null, "Invalid disease!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        });
        startSelfDiagButton.addActionListener(e -> {
            frmTest test = new frmTest();
            test.setVisible(true);
            setVisible(false);
        });
        bookButton.addActionListener(e -> {
            frmBooking booking = new frmBooking();
            booking.setVisible(true);
            setVisible(false);
        });

        recentArea.selectAll();
        recentArea.replaceSelection("");
        recentArea.setText(ConnectSQL.showPatientBooking("1"));
    }
}
