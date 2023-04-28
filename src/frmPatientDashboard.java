import javax.swing.*;

public class frmPatientDashboard extends JFrame {
    private JButton logOutButton;
    private JButton startSelfDiagButton;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JPanel panel;
    private JPanel searchPanel;
    private JTextArea recentArea;
    private JLabel insLabel;
    private JLabel titleLabel;
    private JLabel recentLabel;
    private JButton bookButton;
    private JScrollPane recentPane;
    private JLabel copyrightLabel;
    private static frmPatientDashboard instance;
    public static synchronized frmPatientDashboard getInstance(){
        if(instance== null){
            instance = new frmPatientDashboard();
        }
        return instance;
    }

    private frmPatientDashboard() {
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

        searchButton.addActionListener(e -> {
            if (searchField.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Please input any disease name!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            resultArea.selectAll();
            resultArea.replaceSelection("");
            resultArea.setText(ConnectSQL.showSearchQuery(searchField.getText()));
            resultArea.setEditable(false);

            if (ConnectSQL.showSearchQuery(searchField.getText()).length() == 0) {
                JOptionPane.showMessageDialog(null, "Cannot find the disease with name: " + searchField.getText() + " . Please search again!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        startSelfDiagButton.addActionListener(e -> {
            frmTest.getInstance().setVisible(true);
            setVisible(false);
        });

        bookButton.addActionListener(e -> {
            frmBooking.getInstance().setVisible(true);
            setVisible(false);
        });

        recentArea.selectAll();
        recentArea.replaceSelection("");
        recentArea.setText(ConnectSQL.showPatientBooking("1"));
        recentArea.setEditable(false);
    }
}
