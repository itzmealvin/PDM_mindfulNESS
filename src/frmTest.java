import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.*;

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
  private JLabel copyrightLabel;
  private int weightA;
  private int weightB;
  private int weightC;
  private int weightD;
  private int weightE;
  private boolean[][] buttonStates = {
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true},
    {true, true, true, true, true}
  };

  public frmTest() {
    setContentPane(panel);
    setTitle("mindfulNESS - Self-diagnosis test");
    setSize(1200, 800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    questionArea.setEditable(false);

    logOutButton.addActionListener(
        e -> {
          int option =
              JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to log out?",
                  "Confirmation",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE);
          if (option == JOptionPane.YES_OPTION) {
            logOutButton.setEnabled(false);
            SwingWorker<Void, Void> worker =
                new SwingWorker<>() {
                  @Override
                  protected Void doInBackground() {
                    try {
                      setVisible(false);
                      JOptionPane.showMessageDialog(
                          null,
                          "Logged out! See you again",
                          "Success",
                          JOptionPane.INFORMATION_MESSAGE);
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
    goBackButton.addActionListener(
        e -> {
          int option =
              JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to go back?",
                  "Confirmation",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE);
          if (option == JOptionPane.YES_OPTION) {
            goBackButton.setEnabled(false);
            SwingWorker<Void, Void> worker =
                new SwingWorker<>() {
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
    resetAllButton.addActionListener(
        e -> {
          int option =
              JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to reset all the answer(s)?",
                  "Confirmation",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.WARNING_MESSAGE);
          if (option == JOptionPane.YES_OPTION) {
            resetAllButton.setEnabled(false);
            SwingWorker<Void, Void> worker =
                new SwingWorker<>() {
                  @Override
                  protected Void doInBackground() {
                    boolean[][] newButtonStates = {
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true},
                      {true, true, true, true, true}
                    };
                    buttonStates = Arrays.copyOf(newButtonStates, newButtonStates.length);

                    answerAButton.setVisible(true);
                    answerBButton.setVisible(true);
                    answerCButton.setVisible(true);
                    answerDButton.setVisible(true);
                    answerEButton.setVisible(true);

                    answerAButton.setEnabled(true);
                    answerBButton.setEnabled(true);
                    answerCButton.setEnabled(true);
                    answerDButton.setEnabled(true);
                    answerEButton.setEnabled(true);

                    answerAButton.setText("");
                    answerBButton.setText("");
                    answerCButton.setText("");
                    answerDButton.setText("");
                    answerEButton.setText("");

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
            JOptionPane.showMessageDialog(
                null, "The test has been reset!", "Success", JOptionPane.INFORMATION_MESSAGE);
          }
        });
    testCombo.addActionListener(
        e -> {
          JOptionPane.showMessageDialog(
              null,
              "Detected change in Test Package. Be sure to press RESET ALL to avoid error!",
              "Warning",
              JOptionPane.WARNING_MESSAGE);
          testCombo.setEnabled(false);
          answerAButton.setEnabled(false);
          answerBButton.setEnabled(false);
          answerCButton.setEnabled(false);
          answerDButton.setEnabled(false);
          answerEButton.setEnabled(false);

          SwingWorker<Void, Void> worker =
              new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                  questionCombo.setEnabled(false);
                  maxScoreInt =
                      ConnectSQL.showTotalScoreQuery(
                          Objects.requireNonNull(testCombo.getSelectedItem()).toString());
                  Object[] questions =
                      ConnectSQL.showQuestionQuery(
                              Objects.requireNonNull(testCombo.getSelectedItem()).toString())
                          .toArray();
                  numberOfQuestionInt = questions.length;
                  questionCombo.setModel(new DefaultComboBoxModel<>(questions));
                  questionCombo.setEnabled(true);
                  weightIntList = ConnectSQL.showWeightQuery(Objects.requireNonNull(questionCombo.getSelectedItem()).toString());
                  weightA = weightIntList.get(0);
                  weightB = weightIntList.get(1);
                  weightC = weightIntList.get(2);
                  weightD = weightIntList.get(3);
                  weightE = weightIntList.get(4);

                  if(weightIntList.size() <=2) {
                      answerAButton.setEnabled(true);
                      answerBButton.setEnabled(true);

                      answerCButton.setVisible(false);
                      answerDButton.setVisible(false);
                      answerEButton.setVisible(false);
                  }
                  else if(weightIntList.size() >=3) {
                      answerAButton.setEnabled(true);
                      answerBButton.setEnabled(true);
                      answerCButton.setEnabled(true);
                      answerDButton.setEnabled(true);

                      answerEButton.setVisible(false);
                  }
                  else if(weightIntList.size() >=5) {
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
                  testCombo.setEnabled(true);
                  answerAButton.setEnabled(true);
                  answerBButton.setEnabled(true);
                  answerCButton.setEnabled(true);
                  answerDButton.setEnabled(true);
                  answerEButton.setEnabled(true);

                }
              };
          worker.execute();
        });
    questionCombo.addActionListener(
        e -> {
          questionCombo.setEnabled(false);
          answerAButton.setEnabled(false);
          answerBButton.setEnabled(false);
          answerCButton.setEnabled(false);
          answerDButton.setEnabled(false);
          answerEButton.setEnabled(false);
          SwingWorker<Void, Void> worker =
              new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                  questionArea.setText(
                      ConnectSQL.showQuestionContentQuery(
                          Objects.requireNonNull(testCombo.getSelectedItem()).toString(),
                          Objects.requireNonNull(questionCombo.getSelectedItem()).toString())
                      );


                  answers = ConnectSQL.showAnswerContentQuery(questionCombo.getSelectedItem().toString());
                  answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0]);
                  answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1]);
                  answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2]);
                  answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3]);
                  answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4]);

                  boolean answered = false;
                  for (int i = 0; i < 5; i++)
                    answered = answered || !buttonStates[questionCombo.getSelectedIndex()][i];
                  if (answered) {
                    answerAButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][0]);
                    answerBButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][1]);
                    answerCButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][2]);
                    answerDButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][3]);
                    answerEButton.setEnabled(!buttonStates[questionCombo.getSelectedIndex()][4]);
                  }
                  else {
                    answerAButton.setEnabled(true);
                    answerBButton.setEnabled(true);
                    answerCButton.setEnabled(true);
                    answerDButton.setEnabled(true);
                    answerEButton.setEnabled(true);
                  }
                  answerAButton.setText(answers.get(0));
                  answerBButton.setText(answers.get(1));
                  answerCButton.setText(answers.get(2));
                  answerDButton.setText(answers.get(3));
                  answerEButton.setText(answers.get(4));
                  return null;
                }

                @Override
                protected void done() {
                  questionCombo.setEnabled(true);
                }
              };
          worker.execute(); // Start the background process
        });
    answerAButton.addActionListener(
        e -> {
          buttonEntered(answerAButton);
          answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1] = false);
          answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
          answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
          answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4] = false);
        });
    answerBButton.addActionListener(
        e -> {
          buttonEntered(answerBButton);
          answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
          answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
          answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
          answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4] = false);
        });
    answerCButton.addActionListener(
        e -> {
          buttonEntered(answerCButton);
          answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
          answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1] = false);
          answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
          answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4] = false);
        });
    answerDButton.addActionListener(
        e -> {
          buttonEntered(answerDButton);
          answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
          answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1] = false);
          answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
          answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4] = false);
        });
    answerEButton.addActionListener(
        e -> {
          buttonEntered(answerEButton);
          answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0] = false);
          answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1] = false);
          answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2] = false);
          answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3] = false);
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
      int option =
          JOptionPane.showConfirmDialog(
              null,
              "Are you sure you want to submit?",
              "Confirmation",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE);
      if (option == JOptionPane.YES_OPTION) {
        JOptionPane.showMessageDialog(
            null, "Your score is: " + totalWeight + "!", "Result", JOptionPane.INFORMATION_MESSAGE);
        int threshold;
        threshold = (totalWeight * 100) / maxScoreInt;
        if (threshold <= 70) {
          JOptionPane.showMessageDialog(
              null,
              "Your mental health is normal! No need to worry.",
              "Result",
              JOptionPane.INFORMATION_MESSAGE);
          setVisible(false);
          frmPatientDashboard.getInstance().setVisible(true);
        } else if (threshold < 90) {
          String[] results =
              ConnectSQL.showSolutionQuery(
                  Objects.requireNonNull(testCombo.getSelectedItem()).toString(), 80);
          JOptionPane.showMessageDialog(
              null, "Your mental health is quite bad!", "Result", JOptionPane.INFORMATION_MESSAGE);
          JOptionPane.showMessageDialog(
              null, results[1], "Solution", JOptionPane.INFORMATION_MESSAGE);
          ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID()[0], results[0]);
          setVisible(false);
          frmPatientDashboard.getInstance().setVisible(true);
        } else {
          String[] results =
              ConnectSQL.showSolutionQuery(
                  Objects.requireNonNull(testCombo.getSelectedItem()).toString(), 80);
          int choice =
              JOptionPane.showConfirmDialog(
                  null,
                  "Your result is extremely bad! Do you want to consult with a specialists?",
                  "Result",
                  JOptionPane.YES_NO_OPTION);
          if (choice == JOptionPane.YES_OPTION) {
            frmBooking booking = new frmBooking();
            booking.setVisible(true);
            setVisible(false);
          } else {
            JOptionPane.showMessageDialog(
                null, results[1], "TEMPORARY SOLUTION ONLY", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            frmPatientDashboard.getInstance().setVisible(true);
          }
          ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID()[0], results[0]);
        }
      }
    }
  }
}
