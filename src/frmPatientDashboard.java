import javax.swing.*;

public class frmPatientDashboard extends JFrame {
    private static frmPatientDashboard instance;
    private JButton logOutButton;
    private JButton startSelfDiagButton;
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JPanel panel;
    private JTextArea recentArea;
    private JButton bookButton;
    private JButton resetPwdButton;
    private JPanel searchPanel;
    private JLabel recentLabel;
    private JLabel titleLabel;
    private JScrollPane recentPane;
    private JLabel copyrightLabel;
    private JLabel insLabel;

    private frmPatientDashboard() {
        setContentPane(panel);
        setTitle("mindfulNESS - Dashboard");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        logOutButton.addActionListener(
                e -> {
                    int option =
                            JOptionPane.showConfirmDialog(
                                    null,
                                    "Are you sure you want to log out?",
                                    "Confirmation",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        logOutButton.setEnabled(false);
                        SwingWorker<Void, Void> worker =
                                new SwingWorker<>() {
                                    @Override
                                    protected Void doInBackground() {
                                        try {
                                            setVisible(false);
                                            JOptionPane.showMessageDialog(
                                                    null,
                                                    "Logged out! See you again",
                                                    "Success!",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            Thread.sleep(1000);
                                            System.exit(0);
                                        } catch (InterruptedException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                        return null;
                                    }

                                    @Override
                                    protected void done() {
                                        logOutButton.setEnabled(true);
                                    }
                                };
                        worker.execute();
                    }
                });
        searchButton.addActionListener(
                e -> {
                    searchButton.setEnabled(false);
                    if (searchField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(
                                null, "Disease name is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                        searchButton.setEnabled(true);
                        return;
                    }
                    SwingWorker<Void, Void> worker =
                            new SwingWorker<>() {
                                @Override
                                protected Void doInBackground() {
                                    resultArea.selectAll();
                                    resultArea.replaceSelection("");
                                    resultArea.setText(ConnectSQL.showSearchQuery(searchField.getText()));
                                    resultArea.setEditable(false);
                                    if (ConnectSQL.showSearchQuery(searchField.getText()).length() == 0) {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "Cannot find the disease with name: "
                                                        + searchField.getText()
                                                        + ". Please search again!",
                                                "Warning",
                                                JOptionPane.WARNING_MESSAGE);
                                    }
                                    return null;
                                }

                                @Override
                                protected void done() {
                                    searchButton.setEnabled(true);
                                }
                            };
                    worker.execute();
                });
        startSelfDiagButton.addActionListener(
                e -> {
                    startSelfDiagButton.setEnabled(false);
                    SwingWorker<Void, Void> worker =
                            new SwingWorker<>() {
                                @Override
                                protected Void doInBackground() {
                                    frmTest frmTest = new frmTest();
                                    frmTest.setVisible(true);
                                    setVisible(false);
                                    return null;
                                }

                                @Override
                                protected void done() {
                                    startSelfDiagButton.setEnabled(true);
                                }
                            };
                    worker.execute();
                });
        bookButton.addActionListener(
                e -> {
                    bookButton.setEnabled(false);
                    SwingWorker<Void, Void> worker =
                            new SwingWorker<>() {
                                @Override
                                protected Void doInBackground() {
                                    frmBooking frmBooking = new frmBooking();
                                    frmBooking.setVisible(true);
                                    setVisible(false);
                                    return null;
                                }

                                @Override
                                protected void done() {
                                    bookButton.setEnabled(true);
                                }
                            };
                    worker.execute();
                });
        resetPwdButton.addActionListener(
                e -> {
                });
    }

    public static synchronized frmPatientDashboard getInstance() {
        if (instance == null) {
            instance = new frmPatientDashboard();
        }
        return instance;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        recentArea.selectAll();
        recentArea.replaceSelection("");
        recentArea.setText(ConnectSQL.showPatientBooking(frmIndex.getInstance().getID()));
        recentArea.setEditable(false);
    }
}
