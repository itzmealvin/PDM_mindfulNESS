import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class frmTest extends JFrame {
    private static int totalWeight = 0;
    private static int countAnswer = 0;
    private static int maxScoreInt;
    private static int numberOfQuestionInt = 0;
    ArrayList<String> answers = new ArrayList<>();
    ArrayList<Integer> weightIntList = new ArrayList<>();
    private JButton logOutButton;
    private JTextArea questionArea;
    private JButton answerAButton;
    private JButton answerCButton;
    private JButton answerBButton;
    private JButton answerDButton;
    private JButton answerEButton;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JPanel questionnairePanel;
    private JButton goBackButton;
    private JButton resetAllButton;
    private JComboBox<Object> questionCombo;
    private JLabel questionLabel;
    private JComboBox<Object> testCombo;
    private JLabel testLabel;
    private int weightA;
    private int weightB;
    private int weightC;
    private int weightD;
    private int weightE;
    private boolean[][] buttonStates = {{true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}};

    public frmTest() {
        setContentPane(panel);
        setTitle("mindfulNESS - Testing in progress");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        logOutButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                logOutButton.setEnabled(false);
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        try {
                            setVisible(false);
                            JOptionPane.showMessageDialog(null, "Logged out! See you again", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            Thread.sleep(1000);
                            System.exit(0);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        logOutButton.setEnabled(true);
                    }
                };
                worker.execute();
            }
        });
        goBackButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                goBackButton.setEnabled(false);
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        frmPatientDashboard.getInstance().setVisible(true);
                        setVisible(false);
                        return null;
                    }

                    @Override
                    protected void done() {
                        goBackButton.setEnabled(true);
                    }
                };
                worker.execute();

            }
        });
        resetAllButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset all the answer(s)?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                resetAllButton.setEnabled(false);
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        boolean[][] newButtonStates = {{true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}, {true, true, true, true, true}};
                        buttonStates = Arrays.copyOf(newButtonStates, newButtonStates.length);
                        answerAButton.setVisible(true);
                        answerBButton.setVisible(true);
                        answerCButton.setVisible(true);
                        answerDButton.setVisible(true);
                        answerEButton.setVisible(true);
                        totalWeight = 0;
                        countAnswer = 0;
                        return null;
                    }

                    @Override
                    protected void done() {
                        resetAllButton.setEnabled(true);
                    }
                };
                worker.execute();
            }
        });
        testCombo.addActionListener(e -> {
            testCombo.setEnabled(false);
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    questionCombo.setEnabled(false);
                    maxScoreInt = ConnectSQL.showTotalScoreQuery(Objects.requireNonNull(testCombo.getSelectedItem()).toString());
                    Object[] questions = ConnectSQL.showQuestionQuery(Objects.requireNonNull(testCombo.getSelectedItem()).toString()).toArray();
                    numberOfQuestionInt = questions.length;
                    questionCombo.setModel(new DefaultComboBoxModel<>(questions));
                    questionCombo.setEnabled(true);
                    weightIntList = ConnectSQL.showWeightQuery(Objects.requireNonNull(questionCombo.getSelectedItem()).toString());
                    weightA = weightIntList.get(0);
                    weightB = weightIntList.get(1);
                    weightC = weightIntList.get(2);
                    weightD = weightIntList.get(3);
                    weightE = weightIntList.get(4);
                    return null;
                }

                @Override
                protected void done() {
                    testCombo.setEnabled(true);
                }
            };
            worker.execute();
        });
        questionCombo.addActionListener(e -> {
            questionCombo.setEnabled(false);
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    questionArea.setText(ConnectSQL.showQuestionContentQuery(Objects.requireNonNull(testCombo.getSelectedItem()).toString(), Objects.requireNonNull(questionCombo.getSelectedItem()).toString()));
                    answers = ConnectSQL.showAnswerContentQuery(questionCombo.getSelectedItem().toString());
                    answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0]);
                    answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1]);
                    answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2]);
                    answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3]);
                    answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4]);
                    answerAButton.setText(answers.get(0));
                    answerBButton.setText(answers.get(1));
                    answerCButton.setText(answers.get(2));
                    answerDButton.setText(answers.get(3));
                    answerEButton.setText(answers.get(4));
                    boolean answered = false;
                    for (int i = 0; i < 4; i++)
                        answered = answered || !buttonStates[questionCombo.getSelectedIndex()][i];
                    if (answered) {
                        answerAButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][0]);
                        answerBButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][1]);
                        answerCButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][2]);
                        answerDButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][3]);
                        answerEButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][4]);
                    } else {
                        answerAButton.setEnabled(true);
                        answerBButton.setEnabled(true);
                        answerCButton.setEnabled(true);
                        answerDButton.setEnabled(true);
                        answerEButton.setEnabled(true);
                    }
                    return null;
                }

                @Override
                protected void done() {
                    questionCombo.setEnabled(true); // Re-enable the button after the background process is done
                }
            };
            worker.execute(); // Start the background process
            answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0]);
            answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1]);
            answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2]);
            answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3]);
            answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4]);
            answerAButton.setEnabled(buttonStates[questionCombo.getSelectedIndex()][0]);
            answerBButton.setEnabled(buttonStates[questionCombo.getSelectedIndex()][1]);
            answerCButton.setEnabled(buttonStates[questionCombo.getSelectedIndex()][2]);
            answerDButton.setEnabled(buttonStates[questionCombo.getSelectedIndex()][3]);
            answerEButton.setEnabled(buttonStates[questionCombo.getSelectedIndex()][4]);
        });
        answerAButton.addActionListener(e -> {
            buttonEntered(answerAButton);
            answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1] = false);
            answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
            answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4] = false);
        });
        answerBButton.addActionListener(e -> {
            buttonEntered(answerBButton);
            answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
            answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
            answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4] = false);
        });
        answerCButton.addActionListener(e -> {
            buttonEntered(answerCButton);
            answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
            answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
            answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4] = false);
        });
        answerDButton.addActionListener(e -> {
            buttonEntered(answerDButton);
            answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
            answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
            answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
            answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4] = false);
            answerDButton.setEnabled(false);
        });
        answerEButton.addActionListener(e -> {
            buttonEntered(answerEButton);
            answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
            answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
            answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
            answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4] = false);
        });
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (isVisible()) {
            testCombo.setModel(new DefaultComboBoxModel<>(ConnectSQL.showTestQuery().toArray()));
        }
    }

    private void buttonEntered(JButton main) {
        if (main == answerAButton) {
            totalWeight += weightA;
        } else if (main == answerBButton) {
            totalWeight += weightB;
        } else if (main == answerCButton) {
            totalWeight += weightC;
        } else if (main == answerDButton) {
            totalWeight += weightD;
        } else if (main == answerEButton) {
            totalWeight += weightE;
        }
        main.setEnabled(false);
        countAnswer++;
        if (countAnswer == numberOfQuestionInt) {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Message", JOptionPane.INFORMATION_MESSAGE);
                int threshold;
                threshold = (totalWeight * 100) / maxScoreInt;
                if (threshold <= 70) {
                    JOptionPane.showMessageDialog(null, "Your mental health is normal! No need to worry.", "Message", JOptionPane.INFORMATION_MESSAGE);
                    frmPatientDashboard.getInstance().setVisible(true);
                    setVisible(false);
                } else if (threshold < 90) {
                    String[] results = ConnectSQL.showSolutionQuery(Objects.requireNonNull(testCombo.getSelectedItem()).toString(), 80);
                    JOptionPane.showMessageDialog(null, "Your mental health is quite bad", "Message", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, results[1], "Solution", JOptionPane.INFORMATION_MESSAGE);
                    ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID()[0], results[0]);
                    setVisible(false);
                    frmPatientDashboard.getInstance().setVisible(true);

                } else {
                    String[] results = ConnectSQL.showSolutionQuery(Objects.requireNonNull(testCombo.getSelectedItem()).toString(), 80);
                    int choice = JOptionPane.showConfirmDialog(null, "Your result is extremely bad! Do you want to make an appointment with specialists?", "Recommendation", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        frmBooking booking = new frmBooking();
                        setVisible(false);
                        booking.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, results[1], "Temporary solution", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        frmPatientDashboard.getInstance().setVisible(true);

                    }
                    ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID()[0], results[0]);
                }
            }
            countAnswer = 0;
            totalWeight = 0;
        }
        
    }
}
