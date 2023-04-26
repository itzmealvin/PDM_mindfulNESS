import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frmRoles extends JFrame {
    private JButton patientButton;
    private JButton specialistButton;
    private JLabel insLabel;
    private JLabel titleLabel;
    private JPanel panel;
    private JButton goBackButton;

    public frmRoles() {
        setContentPane(panel);
        setTitle("mindfulNESS - Choose your role");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        patientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPatientSign patientSign = new frmPatientSign();
                patientSign.setVisible(true);
                setVisible(false);

            }
        });
        specialistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmSpecialistSign specialistSign = new frmSpecialistSign();
                specialistSign.setVisible(true);
                setVisible(false);
            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmIndex indexPage = new frmIndex();
                indexPage.setVisible(true);
                setVisible(false);
            }
        });
    }

}
