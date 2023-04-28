import javax.swing.*;
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
    private static frmIndex instance;
    public static synchronized frmIndex getInstance(){
        if(instance == null){
            instance = new frmIndex();
        }
        return instance;
    }

    private frmIndex() {
        setContentPane(panel);
        setTitle("mindfulNESS - Homepage");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        signUpButton.addActionListener(e -> {
            frmRoles.getInstance().setVisible(true);
            setVisible(false);
        });

        logInButton.addActionListener(e -> {
            frmPatientDashboard.getInstance().setVisible(true);
            setVisible(false);
        });

    }

}
