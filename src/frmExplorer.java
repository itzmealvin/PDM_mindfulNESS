import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class frmExplorer extends JFrame {
  private static frmExplorer instance;
  private JButton goBackToLoginButton;
  private JTextField queryField;
  private JTable resultTable;
  private JButton runQueryButton;
  private JButton clearAllButton;
  private JPanel panel;
  private JLabel titleLabel;
  private JLabel insLabel;
  private JLabel queryLabel;
  private JLabel resultLabel;
  private JLabel copyrightLabel;

  private frmExplorer() {
    setContentPane(panel);
    setTitle("mindfulNESS - Explorer mode");
    setSize(1200, 800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    goBackToLoginButton.addActionListener(
        e -> {
          int option =
              JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to return to login page?",
                  "Confirmation",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.QUESTION_MESSAGE);
          if (option == JOptionPane.YES_OPTION) {
            goBackToLoginButton.setEnabled(false);
            SwingWorker<Void, Void> worker =
                new SwingWorker<>() {
                  @Override
                  protected Void doInBackground() {
                    frmIndex.getInstance().setVisible(true);
                    setVisible(false);
                    return null;
                  }

                  @Override
                  protected void done() {
                    goBackToLoginButton.setEnabled(true);
                  }
                };
            worker.execute();
          }
        });
    runQueryButton.addActionListener(
        e -> {
          runQueryButton.setEnabled(false);
          if (queryField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(
                null, "Please do not left query blank!", "Warning", JOptionPane.WARNING_MESSAGE);
            runQueryButton.setEnabled(true);
            return;
          }
          SwingWorker<Void, Void> worker =
              new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                  ConnectSQL.showQuery(queryField.getText(), resultTable);
                  if (resultTable.getColumnCount() == 0 || resultTable.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(
                        null,
                        "No result found, check your query again!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                  } else {
                    JOptionPane.showMessageDialog(
                        null,
                        "Run query successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                  }
                  return null;
                }

                @Override
                protected void done() {
                  runQueryButton.setEnabled(true);
                }
              };
          worker.execute();
        });
    clearAllButton.addActionListener(
        e -> {
          int option =
              JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to clear all content?",
                  "Confirmation",
                  JOptionPane.YES_NO_OPTION,
                  JOptionPane.WARNING_MESSAGE);
          if (option == JOptionPane.YES_OPTION) {
            clearAllButton.setEnabled(false);
            SwingWorker<Void, Void> worker =
                new SwingWorker<>() {
                  @Override
                  protected Void doInBackground() {
                    queryField.setText("");
                    resultTable.setModel(new DefaultTableModel());
                    return null;
                  }

                  @Override
                  protected void done() {
                    clearAllButton.setEnabled(true);
                  }
                };
            worker.execute();
          }
        });
  }

  public static synchronized frmExplorer getInstance() {
    if (instance == null) {
      instance = new frmExplorer();
    }
    return instance;
  }
}
