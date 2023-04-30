import javax.swing.*;

public class frmSpecialistSign extends JFrame {
    private static frmSpecialistSign instance;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField idField;
    private JTextField graduateField;
    private JButton goBackButton;
    private JButton continueButton;
    private JPanel panel;
    private JLabel titleLabel;
    private JLabel insLabel;
    private JLabel fullNameLabel;
    private JLabel dobLabel;
    private JTextField dobField;
    private JLabel emailLabel;
    private JLabel genderLabel;
    private JLabel idLabel;
    private JLabel graduateLabel;
    private JTextField genderField;

    private frmSpecialistSign() {
        setContentPane(panel);
        setTitle("mindfulNESS - Specialist signup");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        goBackButton.addActionListener(
                e -> {
                    frmRoles.getInstance().setVisible(true);
                    setVisible(false);
                });
        continueButton.addActionListener(
                e -> {
                    continueButton.setEnabled(false);
                    if (fullNameField.getText().isEmpty() || emailField.getText().isEmpty() || dobField.getText().isEmpty() || genderField.getText().isEmpty() || idField.getText().isEmpty() || graduateField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Field(s) are empty!",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                        continueButton.setEnabled(true);
                        return;
                    }
                    frmSignDone.getInstance().setVisible(true);
                    setVisible(false);
                });
    }

    public static synchronized frmSpecialistSign getInstance() {
        if (instance == null) {
            instance = new frmSpecialistSign();
        }
        return instance;
    }
}
