import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmSpecialistDashboard extends JFrame {
    private JButton logOutButton;
    private JTextField searchFIeld;
    private JButton searchButton;
    private JTextArea resultArea;
    private JTextArea textArea2;
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
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
