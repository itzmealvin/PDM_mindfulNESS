import javax.swing.*;

public class frmPatientDashboard extends JFrame {
    private static frmPatientDashboard instance;
    private JButton logOutButton;
    private JButton startSelfDiagButton;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JPanel panel;
    private JTextArea recentArea;
    private JButton healButton;
    private JButton resetPwdButton;
    private JPanel searchPanel;
    private JLabel recentLabel;
    private JLabel titleLabel;
    private JScrollPane recentPane;
    private JLabel copyrightLabel;
    private JLabel insLabel;
    private JButton cancelHealingButton;

    private frmPatientDashboard() {
        setContentPane(panel);
        setTitle("mindfulNESS - Dashboard");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        logOutButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
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
        resetPwdButton.addActionListener(e -> {
            JPasswordField oldPwd = new JPasswordField();
            JPasswordField newPwd = new JPasswordField();
            Object[] message = {"Old password: ", oldPwd, "New password: ", newPwd};
            int option = JOptionPane.showConfirmDialog(null, message, "Changing Password!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                resetPwdButton.setEnabled(false);
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        if (ConnectSQL.submitPasswordUpdate(frmIndex.getInstance().getCredentials()[0], String.valueOf(oldPwd.getPassword()), String.valueOf(newPwd.getPassword()))) {
                            JOptionPane.showMessageDialog(null, "Password changed successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            oldPwd.setText("");
                            newPwd.setText("");

                        } else {
                            JOptionPane.showMessageDialog(null, "The old password is incorrect!", "Warning", JOptionPane.WARNING_MESSAGE);
                            oldPwd.setText("");
                            newPwd.setText("");
                            resetPwdButton.setEnabled(true);
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        resetPwdButton.setEnabled(true);
                    }
                };
                worker.execute();
            }
        });
        searchButton.addActionListener(e -> {
            searchButton.setEnabled(false);
            resultArea.setText("");
            if (searchField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Disease name is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                searchButton.setEnabled(true);
                return;
            }
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    if (ConnectSQL.showSearchQuery(searchField.getText()).length() != 0) {
                        resultArea.selectAll();
                        resultArea.replaceSelection("");
                        resultArea.setText(ConnectSQL.showSearchQuery(searchField.getText()));
                        resultArea.setEditable(false);

                    } else {
                        JOptionPane.showMessageDialog(null, "Cannot find the disease with name: " + searchField.getText() + ". Please check your keyword again!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    return null;
                }

                @Override
                protected void done() {
                    searchButton.setEnabled(true);
                }
            };
            worker.execute();
        });
        startSelfDiagButton.addActionListener(e -> {
            startSelfDiagButton.setEnabled(false);
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    frmTest frmTest = new frmTest();
                    frmTest.setVisible(true);
                    setVisible(false);
                    return null;
                }

                @Override
                protected void done() {
                    startSelfDiagButton.setEnabled(true);
                }
            };
            worker.execute();
        });
        healButton.addActionListener(e -> {
            healButton.setEnabled(false);
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    frmBooking frmBooking = new frmBooking();
                    frmBooking.setVisible(true);
                    setVisible(false);
                    return null;
                }

                @Override
                protected void done() {
                    healButton.setEnabled(true);
                }
            };
            worker.execute();
        });
        cancelHealingButton.addActionListener(e -> {
            cancelHealingButton.setEnabled(false);
            String idHeal = JOptionPane.showInputDialog(null, "Enter the healing ID you want to cancel: ", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
            if (!idHeal.isEmpty()) {
                int option = JOptionPane.showConfirmDialog(null, "Confirm cancel healing with ID: " + idHeal + " ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    SwingWorker<Void, Void> worker = new SwingWorker<>() {
                        @Override
                        protected Void doInBackground() {
                            if (ConnectSQL.cancelHealingUpdate(frmIndex.getInstance().getID(), idHeal)) {
                                JOptionPane.showMessageDialog(null, "Healing with ID: " + idHeal + " cancelled!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                                ConnectSQL.showPatientBookingQuery(frmIndex.getInstance().getID());
                            } else {
                                JOptionPane.showMessageDialog(null, "Cannot cancel healing with ID: " + idHeal + " . Please try again later!", "Warning", JOptionPane.WARNING_MESSAGE);
                            }
                            return null;
                        }

                        @Override
                        protected void done() {
                            cancelHealingButton.setEnabled(true);
                        }
                    };
                    worker.execute();
                }
            }
        });
    }

    public static synchronized frmPatientDashboard getInstance() {
        if (instance == null) {
            instance = new frmPatientDashboard();
        }
        return instance;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (isVisible()) {
            insLabel.setText("Welcome back, " + ConnectSQL.showNameQuery(frmIndex.getInstance().getID()) + "!");
            recentArea.selectAll();
            recentArea.replaceSelection("");
            recentArea.setText(ConnectSQL.showPatientBookingQuery(frmIndex.getInstance().getID()));
            recentArea.setEditable(false);
        }
    }
}
