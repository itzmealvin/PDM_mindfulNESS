import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmSpecialistDashboard extends JFrame {
    private JButton logOutButton;
    private JTextField searchFIeld;
    private JButton searchButton;
    private JTextArea resultArea;
    private JTextArea recentArea;
    private JButton postButton;
    private JLabel titleLabel;
    private JPanel panel;
    private JLabel insLabel;
    private JPanel searchPanel;
    private JLabel upcomingLabel;

    public frmSpecialistDashboard() {
        setContentPane(panel);
        setTitle("mindfulNESS - Dashboard");
        setSize(800, 800);
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

        });
        postButton.addActionListener(e -> {

        });

        recentArea.selectAll();
        recentArea.replaceSelection("");
        recentArea.setText(ConnectSQL.showSpecialistBooking("1"));
    }
}
