import javax.swing.*;

public class frmRoles extends JFrame {
    private static frmRoles instance;
    private JButton patientButton;
    private JButton specialistButton;
    private JLabel insLabel;
    private JLabel titleLabel;
    private JPanel panel;
    private JButton goBackButton;
    private JLabel copyrightLabel;

    private frmRoles() {
        setContentPane(panel);
        setTitle("mindfulNESS - Choose your role");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        patientButton.addActionListener(e -> {
            frmPatientSign.getInstance().setVisible(true);
            setVisible(false);
        });
        specialistButton.addActionListener(e -> {
            frmSpecialistSign.getInstance().setVisible(true);
            setVisible(false);
        });
        goBackButton.addActionListener(e -> {
            frmIndex.getInstance().setVisible(true);
            setVisible(false);
        });
    }

    public static synchronized frmRoles getInstance() {
        if (instance == null) {
            instance = new frmRoles();
        }
        return instance;
    }
}
