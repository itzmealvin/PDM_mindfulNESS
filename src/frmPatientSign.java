import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmPatientSign extends JFrame {
    private JButton goBackButton;
    private JButton continueButton;
    private JTextField firstNameField;
    private JTextField dobField;
    private JTextField lastNameField;
    private JLabel titleLabel;
    private JLabel lastNameLabel;
    private JLabel genderLabel;
    private JLabel insLabel;
    private JLabel firstNameLabel;
    private JLabel dobLabel;
    private JPanel panel;
    private JTextField genderField;

    public frmPatientSign() {
        setContentPane(panel);
        setTitle("mindfulNESS - Patient signup");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goBackButton.addActionListener(e -> {
            frmRoles roles = new frmRoles();
            roles.setVisible(true);
            setVisible(false);
        });
        continueButton.addActionListener(e -> {
            frmSignDone signDone = new frmSignDone();
            signDone.setVisible(true);
            setVisible(false);
        });
    }
}
