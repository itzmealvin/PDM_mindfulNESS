import javax.swing.*;

public class frmIndex extends JFrame {
    private static frmIndex instance;
    private JButton logInButton;
    private JPasswordField pwdField;
    private JButton signUpButton;
    private JPanel panel;
    private JLabel accLabel;
    private JLabel pwdLabel;
    private JTextField accField;
    private JLabel titleLabel;
    private JLabel insLabel;

    private frmIndex() {
        setContentPane(panel);
        setTitle("mindfulNESS - Homepage");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        signUpButton.addActionListener(e -> {

        });

        signUpButton.addActionListener(e -> {
            signUpButton.setEnabled(false);
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
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

        logInButton.addActionListener(e -> {
            logInButton.setEnabled(false);
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    frmPatientDashboard.getInstance().setVisible(true);
                    setVisible(false);
                    return null;
                }

                @Override
                protected void done() {
                    logInButton.setEnabled(true);
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

}
