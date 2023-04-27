import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmSignDone extends JFrame {
    private JButton goToDashboardButton;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JPanel panel;

    public frmSignDone() {
        setContentPane(panel);
        setTitle("mindfulNESS - Sign-up completion");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goToDashboardButton.addActionListener(e -> {
            frmIndex indexPage = new frmIndex();
            indexPage.setVisible(true);
            setVisible(false);
        });

    }
}
