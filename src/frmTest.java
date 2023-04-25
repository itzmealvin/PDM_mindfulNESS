import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmTest extends JFrame {
    private JButton logOutButton;
    private JProgressBar progressBar;
    private JTextArea questionArea;
    private JButton answerAButton;
    private JButton answerCButton;
    private JButton answerBButton;
    private JButton answerDButton;
    private JComboBox questionBox;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JPanel questionnairePanel;
    private JButton goBackButton;
    private JButton clearAllButton;

    public frmTest() {
        setContentPane(panel);
        setTitle("mindfulNESS - Testing in progress");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        answerAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        answerBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        answerCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        answerDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
                frmSpecialistDashboard specialistDashboard= new frmSpecialistDashboard();
                specialistDashboard.setVisible(true);
                setVisible(false);

            }
        });
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
