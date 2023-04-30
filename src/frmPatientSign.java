import javax.swing.*;

public class frmPatientSign extends JFrame {
    private static frmPatientSign instance;
    private JButton goBackButton;
    private JButton continueButton;
    private JTextField fullNameField;
    private JTextField dobField;
    private JTextField emailField;
    private JLabel titleLabel;
    private JLabel emailLabel;
    private JLabel genderLabel;
    private JLabel insLabel;
    private JLabel fullNameLabel;
    private JLabel dobLabel;
    private JPanel panel;
    private JTextField genderField;
    private JButton clearAllButton;
    private JLabel copyrightLabel;

    private frmPatientSign() {
        setContentPane(panel);
        setTitle("mindfulNESS - Patient signup");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goBackButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to go back?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                frmRoles.getInstance().setVisible(true);
                setVisible(false);
            }
        });
        continueButton.addActionListener(e -> {
            if (fullNameField.getText().isEmpty() || emailField.getText().isEmpty() || dobField.getText().isEmpty() || genderField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field(s) are empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                continueButton.setEnabled(true);
                return;
            }
            int option = JOptionPane.showConfirmDialog(null, "Please check the information carefully!", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                continueButton.setEnabled(false);
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        if (ConnectSQL.submitPatientUser(frmIndex.getInstance().getCredentials()[0], frmIndex.getInstance().getCredentials()[1], fullNameField.getText(), dobField.getText(), genderField.getText(), emailField.getText())) {
                            JOptionPane.showMessageDialog(null, "Account: " + frmIndex.getInstance().getCredentials()[0] + " registered successfully. Thank you!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            frmSignDone.getInstance().setVisible(true);
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Something went wrong. Check all the field(s) and try again!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        continueButton.setEnabled(true);
                    }
                };
                worker.execute();
            }
        });
        clearAllButton.addActionListener(e -> {
            fullNameField.setText("");
            emailField.setText("");
            dobField.setText("");
            genderField.setText("");
        });
    }

    public static synchronized frmPatientSign getInstance() {
        if (instance == null) {
            instance = new frmPatientSign();
        }
        return instance;
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (isVisible()) {
            fullNameField.setText("");
            emailField.setText("");
            dobField.setText("");
            genderField.setText("");
        }
    }
}
