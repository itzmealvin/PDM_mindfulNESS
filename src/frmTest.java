import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton answerEButton;

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


    private boolean[][] buttonStates = { {true, true, true, true, true},
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
                                         {true, true, true, true, true} };
    private static int totalWeight = 0;
    private static int countAnswer = 0 ;
    ArrayList<String> answers = new ArrayList<String>();
    ArrayList<Integer> weightIntList = new ArrayList<Integer>();
    ArrayList<String> weights = new ArrayList<String>();
    private static int weightPercentage = 0;
    private static int maxScoreInt;
    private static int weightA = 0;
    private static int weightB = 0;
    private static int weightC = 0;
    private static int weightD = 0;
    private static int weightE = 0;
    private static int numberOfQuestionInt = 0;
    private static frmTest instance;


    public frmTest() {
        setContentPane(panel);
        setTitle("mindfulNESS - Testing in progress");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        testCombo.setModel(new DefaultComboBoxModel(ConnectSQL.showTestQuery().toArray()) {
        });

        testCombo.addActionListener(e -> {
            String maxScore = ConnectSQL.getMaxScoreQuery(testCombo.getSelectedItem().toString());
            maxScoreInt = Integer.parseInt(maxScore);
            System.out.println("Max score = " + maxScoreInt);
            String numberOfQuestion = ConnectSQL.getNumberOfQuestionQuery(testCombo.getSelectedItem().toString());
            numberOfQuestionInt = Integer.parseInt(numberOfQuestion);
            System.out.println("Number of question: " + numberOfQuestionInt);

            weightIntList = ConnectSQL.getWeightQuery(testCombo.getSelectedItem().toString(),questionCombo.getSelectedItem().toString());
            weightA = weightIntList.get(0);
            weightB = weightIntList.get(1);
            if(weightIntList.size() >= 3) {
                weightC = weightIntList.get(2);
                weightD = weightIntList.get(3);
            }
            if (weightIntList.size() >=5) {
                weightE = weightIntList.get(4);
            }
        });

        answerAButton.addActionListener(e -> {
            System.out.print('A');
            totalWeight += weightA;
            countAnswer++;
            System.out.print("Count = " + countAnswer);
            System.out.print("Weight = " + totalWeight);
            System.out.print("\nPercentage = " + (totalWeight * 100 / maxScoreInt));


            if (countAnswer == numberOfQuestionInt) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Notification", JOptionPane.INFORMATION_MESSAGE);
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your disease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if(totalWeight*100/maxScoreInt  <= 70) {
                            JOptionPane.showMessageDialog(null, "Your mental health is good !!!", "Notification", JOptionPane.INFORMATION_MESSAGE);
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Good");

                            frmPatientDashboard homepage = new frmPatientDashboard();
                            homepage.setVisible(true);
                            setVisible(false);
                        }
                        else if(totalWeight*100/maxScoreInt  > 70 && totalWeight*100/maxScoreInt < 90) {
                            String weight = "80";
                            String resultID = ConnectSQL.showResultQuery(testCombo.getSelectedItem().toString(),weight);
                            System.out.print("resultID = " + resultID);
                            JOptionPane.showMessageDialog(null, "Your mental health is quite not good", "Notification", JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, ConnectSQL.showSolutionQuery(resultID,weight), "Solution", JOptionPane.INFORMATION_MESSAGE);
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Quite not good");

                            frmPatientDashboard homepage = new frmPatientDashboard();
                            homepage.setVisible(true);
                            setVisible(false);
                        }
                        else {
                            String weight = "100";
                            String resultID = ConnectSQL.showResultQuery(testCombo.getSelectedItem().toString(),weight);
                            System.out.print("resultID = " + resultID);
                            JOptionPane.showMessageDialog(null, "Your mental health is too bad !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                            int choice = JOptionPane.showConfirmDialog(null, "Do you want to make an appointment with specialists ?", "Recommendation", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                frmBooking booking = new frmBooking();
                                booking.setVisible(true);
                                setVisible(false);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, ConnectSQL.showSolutionQuery(resultID,weight), "Contemporary solution", JOptionPane.INFORMATION_MESSAGE);
                                frmPatientDashboard homepage = new frmPatientDashboard();
                                homepage.setVisible(true);
                                setVisible(false);
                            }
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Too bad");
                        }
                    }
                }
                countAnswer = 0;
                totalWeight = 0;
            }

            answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][1] = false);
            answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
            answerEButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][4] = false);
            answerAButton.setEnabled(false);

            int currentIndex = questionCombo.getSelectedIndex();
            int nextIndex = (currentIndex + 1) % questionCombo.getItemCount();
            questionCombo.setSelectedIndex(nextIndex);
            ActionListener actionListener = questionCombo.getActionListeners()[0]; // assuming only one ActionListener is added
            actionListener.actionPerformed(new ActionEvent(questionCombo, ActionEvent.ACTION_PERFORMED, null)); // trigger the ActionListener
        });

        answerBButton.addActionListener(e -> {
            System.out.print('B');
            totalWeight += weightB;
            countAnswer++;
            System.out.print("Count = " + countAnswer);
            System.out.print("Weight = " + totalWeight);
            System.out.print("\nPercentage = " + (totalWeight * 100 / maxScoreInt));


            if (countAnswer == numberOfQuestionInt) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Notification", JOptionPane.INFORMATION_MESSAGE);
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your disease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if(totalWeight*100/maxScoreInt  <= 70) {
                            JOptionPane.showMessageDialog(null, "Your mental health is good !!!", "Notification", JOptionPane.INFORMATION_MESSAGE);
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Good");

                            frmPatientDashboard homepage = new frmPatientDashboard();
                            homepage.setVisible(true);
                            setVisible(false);
                        }
                        else if(totalWeight*100/maxScoreInt  > 70 && totalWeight*100/maxScoreInt < 90) {
                            String weight = "80";
                            String resultID = ConnectSQL.showResultQuery(testCombo.getSelectedItem().toString(),weight);
                            System.out.print("resultID = " + resultID);
                            JOptionPane.showMessageDialog(null, "Your mental health is quite not good", "Notification", JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, ConnectSQL.showSolutionQuery(resultID,weight), "Solution", JOptionPane.INFORMATION_MESSAGE);
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Quite not good");

                            frmPatientDashboard homepage = new frmPatientDashboard();
                            homepage.setVisible(true);
                            setVisible(false);
                        }
                        else {
                            String weight = "100";
                            String resultID = ConnectSQL.showResultQuery(testCombo.getSelectedItem().toString(),weight);
                            System.out.print("resultID = " + resultID);
                            JOptionPane.showMessageDialog(null, "Your mental health is too bad !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                            int choice = JOptionPane.showConfirmDialog(null, "Do you want to make an appointment with specialists ?", "Recommendation", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                frmBooking booking = new frmBooking();
                                booking.setVisible(true);
                                setVisible(false);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, ConnectSQL.showSolutionQuery(resultID,weight), "Contemporary solution", JOptionPane.INFORMATION_MESSAGE);
                                frmPatientDashboard homepage = new frmPatientDashboard();
                                homepage.setVisible(true);
                                setVisible(false);
                            }
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Too bad");
                        }
                    }
                }
                countAnswer = 0;
                totalWeight = 0;
            }

            answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0] = false);
            answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
            answerEButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][4] = false);
            answerBButton.setEnabled(false);

            int currentIndex = questionCombo.getSelectedIndex();
            int nextIndex = (currentIndex + 1) % questionCombo.getItemCount();
            questionCombo.setSelectedIndex(nextIndex);
            ActionListener actionListener = questionCombo.getActionListeners()[0]; // assuming only one ActionListener is added
            actionListener.actionPerformed(new ActionEvent(questionCombo, ActionEvent.ACTION_PERFORMED, null)); // trigger the ActionListener
        });

        answerCButton.addActionListener(e -> {
            System.out.print('C');
            totalWeight += weightC;
            countAnswer++;
            System.out.print("Count = " + countAnswer);
            System.out.print("Weight = " + totalWeight);
            System.out.print("\nPercentage = " + (totalWeight * 100 / maxScoreInt));


            if (countAnswer == numberOfQuestionInt) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Notification", JOptionPane.INFORMATION_MESSAGE);
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your disease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if(totalWeight*100/maxScoreInt  <= 70) {
                            JOptionPane.showMessageDialog(null, "Your mental health is good !!!", "Notification", JOptionPane.INFORMATION_MESSAGE);
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Good");

                            frmPatientDashboard homepage = new frmPatientDashboard();
                            homepage.setVisible(true);
                            setVisible(false);
                        }
                        else if(totalWeight*100/maxScoreInt  > 70 && totalWeight*100/maxScoreInt < 90) {
                            String weight = "80";
                            String resultID = ConnectSQL.showResultQuery(testCombo.getSelectedItem().toString(),weight);
                            System.out.print("resultID = " + resultID);
                            JOptionPane.showMessageDialog(null, "Your mental health is quite not good", "Notification", JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, ConnectSQL.showSolutionQuery(resultID,weight), "Solution", JOptionPane.INFORMATION_MESSAGE);
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Quite not good");

                            frmPatientDashboard homepage = new frmPatientDashboard();
                            homepage.setVisible(true);
                            setVisible(false);
                        }
                        else {
                            String weight = "100";
                            String resultID = ConnectSQL.showResultQuery(testCombo.getSelectedItem().toString(),weight);
                            System.out.print("resultID = " + resultID);
                            JOptionPane.showMessageDialog(null, "Your mental health is too bad !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                            int choice = JOptionPane.showConfirmDialog(null, "Do you want to make an appointment with specialists ?", "Recommendation", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                frmBooking booking = new frmBooking();
                                booking.setVisible(true);
                                setVisible(false);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, ConnectSQL.showSolutionQuery(resultID,weight), "Contemporary solution", JOptionPane.INFORMATION_MESSAGE);
                                frmPatientDashboard homepage = new frmPatientDashboard();
                                homepage.setVisible(true);
                                setVisible(false);
                            }
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Too bad");
                        }
                    }
                }
                countAnswer = 0;
                totalWeight = 0;
            }

            answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0] = false);
            answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
            answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
            answerEButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][4] = false);
            answerCButton.setEnabled(false);

            int currentIndex = questionCombo.getSelectedIndex();
            int nextIndex = (currentIndex + 1) % questionCombo.getItemCount();
            questionCombo.setSelectedIndex(nextIndex);
            ActionListener actionListener = questionCombo.getActionListeners()[0]; // assuming only one ActionListener is added
            actionListener.actionPerformed(new ActionEvent(questionCombo, ActionEvent.ACTION_PERFORMED, null)); // trigger the ActionListener
        });

        answerDButton.addActionListener(e -> {
            System.out.print('D');
            totalWeight += weightD;
            countAnswer++;
            System.out.print("Count = " + countAnswer);
            System.out.print("Weight = " + totalWeight);
            System.out.print("\nPercentage = " + (totalWeight * 100 / maxScoreInt));


            if (countAnswer == numberOfQuestionInt) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Notification", JOptionPane.INFORMATION_MESSAGE);
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your disease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if(totalWeight*100/maxScoreInt  <= 70) {
                            JOptionPane.showMessageDialog(null, "Your mental health is good !!!", "Notification", JOptionPane.INFORMATION_MESSAGE);
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Good");

                            frmPatientDashboard homepage = new frmPatientDashboard();
                            homepage.setVisible(true);
                            setVisible(false);
                        }
                        else if(totalWeight*100/maxScoreInt  > 70 && totalWeight*100/maxScoreInt < 90) {
                            String weight = "80";
                            String resultID = ConnectSQL.showResultQuery(testCombo.getSelectedItem().toString(),weight);
                            System.out.print("resultID = " + resultID);
                            JOptionPane.showMessageDialog(null, "Your mental health is quite not good", "Notification", JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, ConnectSQL.showSolutionQuery(resultID,weight), "Solution", JOptionPane.INFORMATION_MESSAGE);
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Quite not good");

                            frmPatientDashboard homepage = new frmPatientDashboard();
                            homepage.setVisible(true);
                            setVisible(false);
                        }
                        else {
                            String weight = "100";
                            String resultID = ConnectSQL.showResultQuery(testCombo.getSelectedItem().toString(),weight);
                            System.out.print("resultID = " + resultID);
                            JOptionPane.showMessageDialog(null, "Your mental health is too bad !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                            int choice = JOptionPane.showConfirmDialog(null, "Do you want to make an appointment with specialists ?", "Recommendation", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                frmBooking booking = new frmBooking();
                                booking.setVisible(true);
                                setVisible(false);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, ConnectSQL.showSolutionQuery(resultID,weight), "Contemporary solution", JOptionPane.INFORMATION_MESSAGE);
                                frmPatientDashboard homepage = new frmPatientDashboard();
                                homepage.setVisible(true);
                                setVisible(false);
                            }
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Too bad");
                        }
                    }
                }
                countAnswer = 0;
                totalWeight = 0;
            }

            answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0] = false);
            answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
            answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
            answerEButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][4] = false);
            answerDButton.setEnabled(false);

            int currentIndex = questionCombo.getSelectedIndex();
            int nextIndex = (currentIndex + 1) % questionCombo.getItemCount();
            questionCombo.setSelectedIndex(nextIndex);
            ActionListener actionListener = questionCombo.getActionListeners()[0]; // assuming only one ActionListener is added
            actionListener.actionPerformed(new ActionEvent(questionCombo, ActionEvent.ACTION_PERFORMED, null)); // trigger the ActionListener
        });

        answerEButton.addActionListener(e -> {
            System.out.print('E');
            totalWeight += weightE;
            countAnswer++;
            System.out.print("Count = " + countAnswer);
            System.out.print("Weight = " + totalWeight);
            System.out.print("\nPercentage = " + (totalWeight * 100 / maxScoreInt));


            if (countAnswer == numberOfQuestionInt) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to submit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Your score is: " + totalWeight, "Notification", JOptionPane.INFORMATION_MESSAGE);
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to see your disease level ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        if(totalWeight*100/maxScoreInt  <= 70) {
                            JOptionPane.showMessageDialog(null, "Your mental health is good !!!", "Notification", JOptionPane.INFORMATION_MESSAGE);
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Good");

                            frmPatientDashboard homepage = new frmPatientDashboard();
                            homepage.setVisible(true);
                            setVisible(false);
                        }
                        else if(totalWeight*100/maxScoreInt  > 70 && totalWeight*100/maxScoreInt < 90) {
                            String weight = "80";
                            String resultID = ConnectSQL.showResultQuery(testCombo.getSelectedItem().toString(),weight);
                            System.out.print("resultID = " + resultID);
                            JOptionPane.showMessageDialog(null, "Your mental health is quite not good", "Notification", JOptionPane.INFORMATION_MESSAGE);
                            JOptionPane.showMessageDialog(null, ConnectSQL.showSolutionQuery(resultID,weight), "Solution", JOptionPane.INFORMATION_MESSAGE);
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Quite not good");

                            frmPatientDashboard homepage = new frmPatientDashboard();
                            homepage.setVisible(true);
                            setVisible(false);
                        }
                        else {
                            String weight = "100";
                            String resultID = ConnectSQL.showResultQuery(testCombo.getSelectedItem().toString(),weight);
                            System.out.print("resultID = " + resultID);
                            JOptionPane.showMessageDialog(null, "Your mental health is too bad !!!", "Warning", JOptionPane.WARNING_MESSAGE);
                            int choice = JOptionPane.showConfirmDialog(null, "Do you want to make an appointment with specialists ?", "Recommendation", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                frmBooking booking = new frmBooking();
                                booking.setVisible(true);
                                setVisible(false);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, ConnectSQL.showSolutionQuery(resultID,weight), "Contemporary solution", JOptionPane.INFORMATION_MESSAGE);
                                frmPatientDashboard homepage = new frmPatientDashboard();
                                homepage.setVisible(true);
                                setVisible(false);
                            }
                            frmIndex frmIndex = new frmIndex();
                            ConnectSQL.submitRecordUpdate(frmIndex.getInstance().getID(), String.valueOf(totalWeight) + " points - Too bad");
                        }
                    }
                }
                countAnswer = 0;
                totalWeight = 0;
            }
            answerAButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][0] = false);
            answerBButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][2] = false);
            answerCButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][3] = false);
            answerDButton.setVisible(buttonStates[(int) questionCombo.getSelectedIndex()][4] = false);
            answerEButton.setEnabled(false);

            int currentIndex = questionCombo.getSelectedIndex();
            int nextIndex = (currentIndex + 1) % questionCombo.getItemCount();
            questionCombo.setSelectedIndex(nextIndex);
            ActionListener actionListener = questionCombo.getActionListeners()[0]; // assuming only one ActionListener is added
            actionListener.actionPerformed(new ActionEvent(questionCombo, ActionEvent.ACTION_PERFORMED, null)); // trigger the ActionListener
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
                boolean[][] newButtonStates = { {true, true, true, true, true},
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
                                                {true, true, true, true, true} };

                buttonStates = Arrays.copyOf(newButtonStates, newButtonStates.length);
                answerAButton.setVisible(true);
                answerBButton.setVisible(true);
                answerCButton.setVisible(true);
                answerDButton.setVisible(true);
                answerEButton.setVisible(true);

                totalWeight = 0;
                countAnswer = 0;
            }
        });


        questionCombo.addActionListener(e ->
        {
            System.out.println(Objects.requireNonNull(questionCombo.getSelectedItem()));
            questionArea.setText(ConnectSQL.showQuestionContentQuery(Objects.requireNonNull(testCombo.getSelectedItem()).toString(), questionCombo.getSelectedItem().toString()));

            answerAButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][0]);
            answerBButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][1]);
            if(weightIntList.size() >=3) {
                answerCButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][2]);
                answerDButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][3]);
            }
            if (weightIntList.size() >= 5) {
                answerEButton.setVisible(buttonStates[questionCombo.getSelectedIndex()][4]);
            }

            answers = ConnectSQL.showAnswerContentQuery(questionCombo.getSelectedItem().toString());
            answerAButton.setText(answers.get(0));
            answerBButton.setText(answers.get(1));
            if(weightIntList.size() >=3) {
                answerCButton.setText(answers.get(2));
                answerDButton.setText(answers.get(3));
            }
            if (weightIntList.size() >= 5) {
                answerEButton.setText(answers.get(4));
            }

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
        });
        testCombo.addActionListener(a -> questionCombo.setModel(new DefaultComboBoxModel(ConnectSQL.showQuestionQuery(testCombo.getSelectedItem().toString()).toArray()) {}));
    }
    public static synchronized frmTest getInstance() {
        if (instance == null) {
            instance = new frmTest();
        }
        return instance;
    };
}
