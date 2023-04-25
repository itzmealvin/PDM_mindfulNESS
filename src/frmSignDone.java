import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmSignDone extends JFrame {
    private JButton goToDashboardButton;
    private JButton takeASelfDiagButton;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JPanel panel;

    public frmSignDone() {
        setContentPane(panel);
        setTitle("mindfulNESS - Sign-up completion");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        goToDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmIndex indexPage = new frmIndex();
                indexPage.setVisible(true);
                setVisible(false);
            }
        });
        takeASelfDiagButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmTest test = new frmTest();
                test.setVisible(true);
                setVisible(false);
            }
        });
    }
}
