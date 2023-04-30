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

    private frmPatientSign() {
        setContentPane(panel);
        setTitle("mindfulNESS - Patient signup");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goBackButton.addActionListener(
                e -> {
                    frmRoles.getInstance().setVisible(true);
                    setVisible(false);
                });
        continueButton.addActionListener(
                e -> {
                    continueButton.setEnabled(false);
                    if (fullNameField.getText().isEmpty() || emailField.getText().isEmpty() || dobField.getText().isEmpty() || genderField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Field(s) are empty!",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        continueButton.setEnabled(true);
                        return;
                    }
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            frmSignDone.getInstance().setVisible(true);
                            setVisible(false);
                            return null;
                        }

                        @Override
                        protected void done() {
                            continueButton.setEnabled(true);
                        }
                    };

                });
    }

    public static synchronized frmPatientSign getInstance() {
        if (instance == null) {
            instance = new frmPatientSign();
        }
        return instance;
    }
}
