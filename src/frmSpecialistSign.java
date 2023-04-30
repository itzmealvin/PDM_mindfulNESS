import javax.swing.*;

public class frmSpecialistSign extends JFrame {
    private static frmSpecialistSign instance;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField idField;
    private JTextField graduateField;
    private JButton goBackButton;
    private JButton continueButton;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JLabel fullNameLabel;
    private JLabel dobLabel;
    private JTextField dobField;
    private JLabel emailLabel;
    private JLabel genderLabel;
    private JLabel idLabel;
    private JLabel graduateLabel;
    private JTextField genderField;
    private JButton clearAllButton;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JLabel copyrightLabel;

    private frmSpecialistSign() {
        setContentPane(panel);
        setTitle("mindfulNESS - Specialist signup");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goBackButton.addActionListener(e -> {
            frmRoles.getInstance().setVisible(true);
            setVisible(false);
        });
        continueButton.addActionListener(e -> {
            continueButton.setEnabled(false);
            if (fullNameField.getText().isEmpty() || emailField.getText().isEmpty() || dobField.getText().isEmpty() || genderField.getText().isEmpty() || idField.getText().isEmpty() || graduateField.getText().isEmpty() || phoneField.getText().isEmpty()) {
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
                        if (ConnectSQL.submitSpecialistUser(frmIndex.getInstance().getCredentials()[0], frmIndex.getInstance().getCredentials()[1], fullNameField.getText(), dobField.getText(), genderField.getText(), emailField.getText(), phoneField.getText(), idField.getText(), graduateField.getText())) {
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
            idField.setText("");
            graduateField.setText("");
            phoneField.setText("");
        });
    }

    public static synchronized frmSpecialistSign getInstance() {
        if (instance == null) {
            instance = new frmSpecialistSign();
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
            idField.setText("");
            graduateField.setText("");
            phoneField.setText("");
        }
    }
}
