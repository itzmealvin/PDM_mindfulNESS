import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmIndex extends JFrame {
    private JButton logInButton;
    private JPasswordField pwdField;
    private JButton signUpButton;
    private JPanel panel;
    private JLabel accLabel;
    private JLabel pwdLabel;
    private JTextField accField;
    private JLabel titleLabel;
    private JLabel insLabel;

    public frmIndex() {
        setContentPane(panel);
        setTitle("mindfulNESS - Homepage");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmRoles roles = new frmRoles();
                roles.setVisible(true);
                setVisible(false);
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPatientDashboard patientDashboard = new frmPatientDashboard();
                patientDashboard.setVisible(true);
                setVisible(false);
            }
        });

    }

    public String getAccField() {
        return accField.getText();
    }

    public String getPwdField() {
        return String.valueOf(pwdField.getPassword());
    }
}
