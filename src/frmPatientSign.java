import javax.swing.*;

public class frmPatientSign extends JFrame {
    private static frmPatientSign instance;
    private JButton goBackButton;
    private JButton confirmButton;
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
    private JButton clearAllButton;
    private JLabel copyrightLabel;
    private JComboBox<String> genderField;

    private frmPatientSign() {
        setContentPane(panel);
        setTitle("mindfulNESS - Patient sign-up");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    clearAllButton.addActionListener(
        e -> {
          int option =
              JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to clear all field(s)?",
                  "Confirmation",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.WARNING_MESSAGE);
          if (option == JOptionPane.YES_OPTION) {
            clearAllButton.setEnabled(false);
            SwingWorker<Void, Void> worker =
                new SwingWorker<>() {
                  @Override
                  protected Void doInBackground() {
                    fullNameField.setText("");
                    emailField.setText("");
                    dobField.setText("");
                    genderField.setSelectedItem("<please choose>");
                    return null;
                  }

                  @Override
                  protected void done() {
                    clearAllButton.setEnabled(true);
                  }
                };
            worker.execute();
          }
        });
        confirmButton.addActionListener(e -> {
            if (fullNameField.getText().isEmpty() || emailField.getText().isEmpty() || dobField.getText().isEmpty() || String.valueOf(genderField.getSelectedItem()).equals("<please choose>")) {
                JOptionPane.showMessageDialog(null, "Field(s) are empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                confirmButton.setEnabled(true);
                return;
            }
            int option = JOptionPane.showConfirmDialog(null, "Please check the information carefully!", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                confirmButton.setEnabled(false);
                SwingWorker<Void, Void> worker = new SwingWorker<>() {
                    @Override
                    protected Void doInBackground() {
                        if (ConnectSQL.submitPatientUser(frmIndex.getInstance().getCredentials()[0], frmIndex.getInstance().getCredentials()[1], fullNameField.getText(), dobField.getText(), String.valueOf(genderField.getSelectedItem()), emailField.getText())) {
                            JOptionPane.showMessageDialog(null, "Account: " + frmIndex.getInstance().getCredentials()[0] + " registered successfully. Thank you!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            frmSignDone.getInstance().setVisible(true);
                            setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Something went wrong. Check all the field(s) and try again!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        confirmButton.setEnabled(true);
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
                        frmRoles.getInstance().setVisible(true);
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
            genderField.setSelectedItem("<please choose>");
        }
    }
}
