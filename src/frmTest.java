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
    private static int countAnswer = 0 ;

    public frmTest() {
        setContentPane(panel);
        setTitle("mindfulNESS - Testing in progress");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        testCombo.setModel(new DefaultComboBoxModel(ConnectSQL.showTestQuery().toArray()) {
        });


        answerAButton.addActionListener(e -> {
            System.out.print('A');
            totalWeight += 0;
            countAnswer++;
            System.out.print("Count = " + countAnswer);

            if (countAnswer == 9) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Notification", JOptionPane.INFORMATION_MESSAGE);
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your desease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "You get stressful desease of level ...", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                frmIndex indexPage = new frmIndex();
                indexPage.setVisible(true);
                setVisible(false);
                countAnswer = 0;
                totalWeight = 0;
            }
            answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][1] = false);
            answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
            answerAButton.setEnabled(false);
        });

        answerBButton.addActionListener(e -> {
            System.out.print('B');
            totalWeight += 1;
            countAnswer++;
            System.out.print("Count = " + countAnswer);

            if (countAnswer == 9) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Notification", JOptionPane.INFORMATION_MESSAGE);
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your desease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "You get stressful desease of level ...", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                frmIndex indexPage = new frmIndex();
                indexPage.setVisible(true);
                setVisible(false);
                countAnswer = 0;
                totalWeight = 0;
            }
            answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0] = false);
            answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
            answerBButton.setEnabled(false);
        });

        answerCButton.addActionListener(e -> {
            System.out.print('C');
            totalWeight += 2;
            countAnswer++;
            System.out.print("Count = " + countAnswer);

            if (countAnswer == 9) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Notification", JOptionPane.INFORMATION_MESSAGE);
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your desease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "You get stressful desease of level ...", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                frmIndex indexPage = new frmIndex();
                indexPage.setVisible(true);
                setVisible(false);
                countAnswer = 0;
                totalWeight = 0;
            }
            answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0] = false);
            answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
            answerCButton.setEnabled(false);
        });

        answerDButton.addActionListener(e -> {
            System.out.print('D');
            totalWeight += 3;
            countAnswer++;
            System.out.print("Count = " + countAnswer);

            if (countAnswer == 9) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Notification", JOptionPane.INFORMATION_MESSAGE);
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your desease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "You get stressful desease of level ...", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                frmIndex indexPage = new frmIndex();
                indexPage.setVisible(true);
                setVisible(false);
                countAnswer = 0;
                totalWeight = 0;
            }
            answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0] = false);
            answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
            answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
            answerDButton.setEnabled(false);
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
                countAnswer = 0;
            }
        });

        questionCombo.addActionListener(e -> {
            answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0]);
            answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][1]);
            answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2]);
            answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3]);

            boolean answered = false;
            for(int i = 0; i < 4; i++)
                answered = answered || !buttonStates[(int) questionCombo.getSelectedIndex()][i];

            if(answered) {
                answerAButton.setEnabled(!buttonStates[(int) questionCombo.getSelectedIndex()][0]);
                answerBButton.setEnabled(!buttonStates[(int) questionCombo.getSelectedIndex()][1]);
                answerCButton.setEnabled(!buttonStates[(int) questionCombo.getSelectedIndex()][2]);
                answerDButton.setEnabled(!buttonStates[(int) questionCombo.getSelectedIndex()][3]);
            }
            else {
                answerAButton.setEnabled(true);
                answerBButton.setEnabled(true);
                answerCButton.setEnabled(true);
                answerDButton.setEnabled(true);
            }
        });
    }
}
