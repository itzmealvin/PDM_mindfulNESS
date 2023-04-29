import javax.swing.*;

public class frmBooking extends JFrame {
  private JButton logOutButton;
  private JButton submitBookingButton;
  private JButton goBackButton;
  private JPanel panel;
  private JLabel titleLabel;
  private JLabel insLabel;
  private JLabel idLabel;
  private JTextField idField;
  private JLabel clinicLabel;
  private JButton refreshButton;
  private JTable resultTable;

  public frmBooking() {
    setContentPane(panel);
    setTitle("mindfulNESS - Make a booking");
    setSize(1200, 800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    ConnectSQL.showAvailableBooking(resultTable);

    logOutButton.addActionListener(
        e -> {
          int option =
              JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to log out?",
                  "Confirmation",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE);
          if (option == JOptionPane.YES_OPTION) {
            logOutButton.setEnabled(false);
            SwingWorker<Void, Void> worker =
                new SwingWorker<Void, Void>() {
                  @Override
                  protected Void doInBackground() throws Exception {
                    try {
                      setVisible(false);
                      JOptionPane.showMessageDialog(
                          null,
                          "Logged out! See you again",
                          "Success!",
                          JOptionPane.WARNING_MESSAGE);
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
    goBackButton.addActionListener(
        e -> {
          int option =
              JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to go back?",
                  "Confirmation",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE);
          if (option == JOptionPane.YES_OPTION) {
            frmPatientDashboard.getInstance().setVisible(true);
            setVisible(false);
          }
        });
    submitBookingButton.addActionListener(
        e -> {
          if (idField.getText().length() == 0) {
            JOptionPane.showMessageDialog(
                null, "Please input any possible ID!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
          }
          int option =
              JOptionPane.showConfirmDialog(
                  null,
                  "Confirm booking with ID " + idField.getText() + " ?",
                  "Confirmation",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE);
          if (option == JOptionPane.YES_OPTION) {
            submitBookingButton.setEnabled(false);
            SwingWorker<Void, Void> worker =
                new SwingWorker<Void, Void>() {
                  @Override
                  protected Void doInBackground() throws Exception {
                    if (ConnectSQL.submitBooking("1", idField.getText())) {
                      JOptionPane.showMessageDialog(
                          null,
                          "Booked! Please come on time.",
                          "Warning",
                          JOptionPane.WARNING_MESSAGE);
                      ConnectSQL.showAvailableBooking(resultTable);
                    } else {
                      JOptionPane.showMessageDialog(
                          null,
                          idField.getText() + " is already booked! Please try again",
                          "Warning",
                          JOptionPane.WARNING_MESSAGE);
                    }
                    return null;
                  }

                  @Override
                  protected void done() {
                    submitBookingButton.setEnabled(true);
                  }
                };
            worker.execute();
          }
        });
    refreshButton.addActionListener(
        e -> {
          refreshButton.setEnabled(false);
          SwingWorker<Void, Void> worker =
              new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                  ConnectSQL.showAvailableBooking(resultTable);
                  return null;
                }

                @Override
                protected void done() {
                  refreshButton.setEnabled(true);
                }
              };
          worker.execute();
        });
  }
}
