import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmSpecialistSign extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField idField;
    private JTextField certiField;
    private JTextField graduateField;
    private JButton goBackButton;
    private JButton continueButton;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JLabel firstNameLabel;
    private JLabel dobLabel;
    private JTextField dobField;
    private JLabel lastNameLabel;
    private JLabel genderLabel;
    private JLabel idLabel;
    private JLabel certiLabel;
    private JLabel graduateLabel;
    private JTextField genderField;

    public frmSpecialistSign() {
        setContentPane(panel);
        setTitle("mindfulNESS - Specialist signup");
        setSize(800, 800);
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
