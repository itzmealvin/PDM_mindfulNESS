import javax.swing.*;

public class frmSignDone extends JFrame {
    private static frmSignDone instance;
    private JButton goToLogInButton;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JPanel panel;
    private JLabel copyrightLabel;

    private frmSignDone() {
        setContentPane(panel);
        setTitle("mindfulNESS - Sign-up completion");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goToLogInButton.addActionListener(e -> {
            frmIndex.getInstance().setVisible(true);
            setVisible(false);
        });
    }

    public static synchronized frmSignDone getInstance() {
        if (instance == null) {
            instance = new frmSignDone();
        }
        return instance;
    }
}
