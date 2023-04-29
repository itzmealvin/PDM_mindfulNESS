import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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
    private JComboBox testCombo;
    private JLabel questionLabel;
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
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true}};
    private static int totalWeight = 0;
    private static int countAnswer = 0;
    private static frmTest instance;
    ArrayList<String> answers = new ArrayList<String>();

    public static synchronized frmTest getInstance() {
        if (instance == null) {
            instance = new frmTest();
        }
        return instance;
    }

    private frmTest() {
        setContentPane(panel);
        setTitle("mindfulNESS - Testing in progress");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        testCombo.setModel(new DefaultComboBoxModel(ConnectSQL.showTestQuery().toArray()) {
        });


        answerAButton.addActionListener(e -> {
            System.out.print('A');
            countAnswer++;
            System.out.print("Count = " + countAnswer);

            if (countAnswer == 9) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Notification", JOptionPane.INFORMATION_MESSAGE);
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your disease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "You get stressful disease of level ...", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                frmPatientDashboard.getInstance().setVisible(true);
                setVisible(false);
                countAnswer = 0;
                totalWeight = 0;
            }
            answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1] = false);
            answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
            answerAButton.setEnabled(false);
            testCombo.setEnabled(false);
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
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your disease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "You get stressful disease of level ...", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                frmPatientDashboard.getInstance().setVisible(true);
                setVisible(false);
                countAnswer = 0;
                totalWeight = 0;
            }
            answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
            answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
            answerBButton.setEnabled(false);
            testCombo.setEnabled(false);
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
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your disease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "You get stressful disease of level ...", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                frmPatientDashboard.getInstance().setVisible(true);
                setVisible(false);
                countAnswer = 0;
                totalWeight = 0;
            }
            answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
            answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
            answerCButton.setEnabled(false);
            testCombo.setEnabled(false);
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
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your disease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(null, "You get stressful disease of level ...", "Notification", JOptionPane.INFORMATION_MESSAGE);
                    }

                }
                frmPatientDashboard.getInstance().setVisible(true);
                setVisible(false);
                countAnswer = 0;
                totalWeight = 0;
            }
            answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
            answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
            answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
            answerDButton.setEnabled(false);
            testCombo.setEnabled(false);
        });

        logOutButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Logged out! See you again", "Success!", JOptionPane.WARNING_MESSAGE);
                    Thread.sleep(3000);
                    System.exit(0);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        goBackButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                frmSpecialistDashboard.getInstance().setVisible(true);
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
                        {true, true, true, true},
                        {true, true, true, true},
                        {true, true, true, true}};

                buttonStates = Arrays.copyOf(newButtonStates, newButtonStates.length);
                answerAButton.setVisible(true);
                answerBButton.setVisible(true);
                answerCButton.setVisible(true);
                answerDButton.setVisible(true);
                answerAButton.setEnabled(true);
                answerBButton.setEnabled(true);
                answerCButton.setEnabled(true);
                answerDButton.setEnabled(true);
                totalWeight = 0;
                countAnswer = 0;
            }
            testCombo.setEnabled(true);
        });
        testCombo.addActionListener(a -> questionCombo.setModel(new DefaultComboBoxModel(ConnectSQL.showQuestionQuery(testCombo.getSelectedItem().toString()).toArray()) {
        }));
        questionCombo.addActionListener(e -> {
            System.out.println(Objects.requireNonNull(questionCombo.getSelectedItem()).toString());
            questionArea.setText(ConnectSQL.showQuestionContentQuery(Objects.requireNonNull(testCombo.getSelectedItem()).toString(), questionCombo.getSelectedItem().toString()));
            answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0]);
            answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1]);
            answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2]);
            answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3]);

            answers = ConnectSQL.showAnswerContentQuery(questionCombo.getSelectedItem().toString());
            answerAButton.setText(answers.get(0));
            answerBButton.setText(answers.get(1));
            answerCButton.setText(answers.get(2));
            answerDButton.setText(answers.get(3));

            boolean answered = false;
            for (int i = 0; i < 4; i++)
                answered = answered || !buttonStates[questionCombo.getSelectedIndex()][i];

            if (answered) {
                answerAButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][0]);
                answerBButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][1]);
                answerCButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][2]);
                answerDButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][3]);
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
