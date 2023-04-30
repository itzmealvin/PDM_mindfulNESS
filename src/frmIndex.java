import javax.swing.*;
import java.util.Objects;

public class frmIndex extends JFrame {
    private static frmIndex instance;
    private JPasswordField pwdField;
    private JTextField accField;
    private JButton logInButton;
    private JButton signUpButton;
    private JPanel panel;
    private JButton explorerModeButton;
    private String[] results;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JLabel accLabel;
    private JLabel pwdLabel;


    private frmIndex() {
        setContentPane(panel);
        setTitle("mindfulNESS - Homepage");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        signUpButton.addActionListener(
                e -> {
                    signUpButton.setEnabled(false);
                    if (accField.getText().isEmpty() || String.valueOf(pwdField.getPassword()).isEmpty() || ConnectSQL.authenticateQuery(accField.getText(), String.valueOf(pwdField.getPassword()))[1] != null) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Either account has existed or field(s) are empty!",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        signUpButton.setEnabled(true);
                        return;
                    }
                    SwingWorker<Void, Void> worker =
                            new SwingWorker<>() {
                                @Override
                                protected Void doInBackground() {
                                    frmRoles.getInstance().setVisible(true);
                                    setVisible(false);
                                    return null;
                                }

                                @Override
                                protected void done() {
                                    signUpButton.setEnabled(true);
                                }
                            };
                    worker.execute();
                });
        logInButton.addActionListener(
                e -> {
                    logInButton.setEnabled(false);
                    if (accField.getText().isEmpty() || String.valueOf(pwdField.getPassword()).isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Either account or password is empty!",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        logInButton.setEnabled(true);
                        return;
                    }
                    SwingWorker<Void, Void> worker =
                            new SwingWorker<>() {
                                @Override
                                protected Void doInBackground() {
                                    results = ConnectSQL.authenticateQuery(accField.getText(), String.valueOf(pwdField.getPassword()));
                                    if (Objects.equals(results[1], "Patient")) {
                                        frmPatientDashboard.getInstance().setVisible(true);
                                        setVisible(false);
                                    } else if (Objects.equals(results[1], "Specialist")) {
                                        frmSpecialistDashboard.getInstance().setVisible(true);
                                        setVisible(false);
                                    } else {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "Either account not exists or credentials not correct!",
                                                "Warning",
                                                JOptionPane.WARNING_MESSAGE);
                                    }
                                    return null;
                                }

                                @Override
                                protected void done() {
                                    logInButton.setEnabled(true);
                                }
                            };
                    worker.execute();

                });
        explorerModeButton.addActionListener(
                e -> {
                    explorerModeButton.setEnabled(false);
                    SwingWorker<Void, Void> worker =
                            new SwingWorker<>() {
                                @Override
                                protected Void doInBackground() {
                                    String masterPassword = JOptionPane.showInputDialog(null, "Enter the master password to continue: ", "Master Password Required", JOptionPane.WARNING_MESSAGE);
                                    if (Objects.equals(masterPassword, "mindfulness")) {
                                        frmExplorer.getInstance().setVisible(true);
                                        setVisible(false);
                                    } else {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "Master Password not correct!",
                                                "Access Denied",
                                                JOptionPane.WARNING_MESSAGE);
                                    }
                                    return null;
                                }

                                @Override
                                protected void done() {
                                    explorerModeButton.setEnabled(true);
                                }
                            };
                    worker.execute();
                });
    }

    public static synchronized frmIndex getInstance() {
        if (instance == null) {
            instance = new frmIndex();
        }
        return instance;
    }

    public String getID() {
        return results[0];
    }

    public String[] getCredentials() {
        return new String[]{accField.getText(), String.valueOf(pwdField.getPassword())};
    }

    @Override
    public void addNotify() {
        super.addNotify();
        pwdField.setText("");
    }
}
