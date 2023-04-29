import javax.swing.*;

public class frmPatientSign extends JFrame {
  private static frmPatientSign instance;
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

  private frmPatientSign() {
    setContentPane(panel);
    setTitle("mindfulNESS - Patient signup");
    setSize(700, 700);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    goBackButton.addActionListener(
        e -> {
          frmRoles.getInstance().setVisible(true);
          setVisible(false);
        });
    continueButton.addActionListener(
        e -> {
          frmSignDone.getInstance().setVisible(true);
          setVisible(false);
        });
  }

  public static synchronized frmPatientSign getInstance() {
    if (instance == null) {
      instance = new frmPatientSign();
    }
    return instance;
  }
}
