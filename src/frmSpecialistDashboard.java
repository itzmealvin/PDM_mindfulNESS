import javax.swing.*;

public class frmSpecialistDashboard extends JFrame {
    private static frmSpecialistDashboard instance;
    private JButton logOutButton;
    private JTextArea recentArea;
    private JButton postButton;
    private JLabel titleLabel;
    private JPanel panel;
    private JLabel insLabel;
    private JLabel upcomingLabel;
    private JScrollPane recentPane;
    private JTextField placeField;
    private JTextField feeField;
    private JTextField dateField;
    private JTextField descField;
    private JTextField extraField;
    private JLabel fillLabel;
    private JLabel placeLabel;
    private JLabel dateLabel;
    private JLabel feeLabel;
    private JLabel descLabel;
    private JLabel noteLabel;
    private JLabel copyrightLabel;
    private frmSpecialistDashboard() {
        setContentPane(panel);
        setTitle("mindfulNESS - Dashboard");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        logOutButton.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "Logged out! See you again", "Success!", JOptionPane.WARNING_MESSAGE);
                    Thread.sleep(3000);
                    System.exit(0);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        postButton.addActionListener(e -> {
            if (placeField.getText().length() == 0 && dateField.getText().length() == 0 && feeField.getText().length() == 0 && descField.getText().length() == 0 && extraField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Please input all the field(s)!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int option = JOptionPane.showConfirmDialog(null, "Confirm posting healing information?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                if (ConnectSQL.submitHealing("1", placeField.getText(), dateField.getText(), feeField.getText(), descField.getText(), extraField.getText())) {
                    JOptionPane.showMessageDialog(null, "Healing information posted! Please check the nearby box for confirmation", "Warning", JOptionPane.WARNING_MESSAGE);
                    recentArea.selectAll();
                    recentArea.replaceSelection("");
                    recentArea.setText(ConnectSQL.showSpecialistBooking("1"));
                }
                else {
                    JOptionPane.showMessageDialog(null, "Something went wrong! Please try again", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        recentArea.selectAll();
        recentArea.replaceSelection("");
        recentArea.setText(ConnectSQL.showSpecialistBooking("1"));
    }

    public static synchronized frmSpecialistDashboard getInstance(){
        if(instance == null){
            instance = new frmSpecialistDashboard();
        }
        return instance;
    }
}
