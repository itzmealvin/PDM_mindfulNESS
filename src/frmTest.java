import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class frmTest extends JFrame {
    private JButton logOutButton;
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
    private JComboBox testCombo;
    private JLabel testLabel;
    private boolean[][] buttonStates = {{true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true}};
    private static int totalWeight = 0;

    public frmTest() {
        setContentPane(panel);
        setTitle("mindfulNESS - Testing in progress");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        testCombo.setModel(new DefaultComboBoxModel(ConnectSQL.showTestQuery().toArray()) {
        });

        answerAButton.addActionListener(e -> {
            totalWeight += 0;
        });
        answerBButton.addActionListener(e -> {
            totalWeight += 1;
        });
        answerCButton.addActionListener(e -> {
            totalWeight += 2;
        });
        answerDButton.addActionListener(e -> {
            totalWeight += 3;
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
                frmSpecialistDashboard specialistDashboard = new frmSpecialistDashboard();
                specialistDashboard.setVisible(true);
                setVisible(false);
            }
        });
        clearAllButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                boolean[][] newButtonStates = {{true, true, true, true},
                        {true, true, true, true},
                        {true, true, true, true},
                        {true, true, true, true},
                        {true, true, true, true},
                        {true, true, true, true},
                        {true, true, true, true},
                        {true, true, true, true},
                        {true, true, true, true},
                        {true, true, true, true}};
                buttonStates = Arrays.copyOf(newButtonStates, newButtonStates.length);
                answerAButton.setVisible(true);
                answerBButton.setVisible(true);
                answerCButton.setVisible(true);
                answerDButton.setVisible(true);
                totalWeight = 0;
            }
        });
        questionCombo.addActionListener(e -> {
            questionArea.selectAll();
            questionArea.replaceSelection("");
            questionArea.setText(ConnectSQL.showQuestionQuery(questionCombo.getSelectedItem().toString()));
            answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0]);
            answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][1]);
            answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2]);
            answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3]);

            answerAButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][1] = false);
                    answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
                    answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
                }
            });

            answerBButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0] = false);
                    answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
                    answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
                }
            });

            answerCButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][1] = false);
                    answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0] = false);
                    answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
                }
            });

            answerDButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][1] = false);
                    answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
                    answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0] = false);
                }
            });

        });

    }
}
