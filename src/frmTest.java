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
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JPanel questionnairePanel;
    private JButton goBackButton;
    private JButton clearAllButton;
    private JComboBox questionCombo;
    private JLabel questionLabel;

    public frmTest() {
        setContentPane(panel);
        setTitle("mindfulNESS - Testing in progress");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        answerAButton.addActionListener(e -> {

        });
        answerBButton.addActionListener(e -> {

        });
        answerCButton.addActionListener(e -> {

        });
        answerDButton.addActionListener(e -> {

        });
        logOutButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                frmIndex indexPage = new frmIndex();
                indexPage.setVisible(true);
                setVisible(false);
                JOptionPane.showMessageDialog(null, "Logged out!", "Success!", JOptionPane.WARNING_MESSAGE);
            }
        });
        goBackButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                frmSpecialistDashboard specialistDashboard= new frmSpecialistDashboard();
                specialistDashboard.setVisible(true);
                setVisible(false);
            }
        });
        clearAllButton.addActionListener(e -> {

        });
        questionCombo.addActionListener(e -> {
            questionArea.selectAll();
            questionArea.replaceSelection("");
            questionArea.setText(ConnectSQL.showQuestionQuery(questionCombo.getSelectedItem().toString()));
        });
    }
}
