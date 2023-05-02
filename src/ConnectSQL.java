import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

public class ConnectSQL {
  static final String connectionUrl =
      "jdbc:sqlserver://sql.bsite.net\\MSSQL2016;databaseName=congbang0711_;user=congbang0711_;password=mindfulness;encrypt=true;trustServerCertificate=true;";

  public static void closeConnect(Connection con) {
    if (con != null) {
      try {
        con.close();
      } catch (SQLException e) {
        System.out.println("Error closing connection");
      }
    }
  }

  public static void showAvailableHealingQuery(JTable resultTable) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    SELECT H.HealingInformation_ID AS ID, H.Date, H.Place, CAST(H.Fee AS INT) AS Fee, S.FullName, S.Sex
                    FROM [Booking].[HealingInformation] H
                    INNER JOIN [Account].[Specialist] S
                    ON S.Specialist_ID = H.SpecialistID
                    WHERE H.HealingInformation_ID NOT IN (SELECT DISTINCT B.HealingInformationID
                    FROM [Booking].[Booking] B) AND H.Date >= CAST(GETDATE() AS DATE ) ORDER BY H.Date""";
      stmt = con.prepareStatement(preparedQuery);
      rs = stmt.executeQuery();
      if (!rs.next()) {
        JOptionPane.showMessageDialog(
            null,
            "No available healing found! Please try again later",
            "Message",
            JOptionPane.WARNING_MESSAGE);
      } else {
        resultTable.setModel(Objects.requireNonNull(resultSetToTableModel(rs)));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
  }

  private static TableModel resultSetToTableModel(ResultSet rs) {
    try {
      ResultSetMetaData metadata = rs.getMetaData();
      int numberOfColumn = metadata.getColumnCount();
      Vector<String> headline = new Vector<>();
      for (int i = 0; i < numberOfColumn; ++i) {
        headline.addElement(metadata.getColumnLabel(i + 1));
      }
      Vector<Vector<Object>> content = new Vector<>();
      while (rs.next()) {
        Vector<Object> row = new Vector<>();
        for (int i = 1; i <= numberOfColumn; ++i) {
          row.addElement(rs.getObject(i));
        }
        content.addElement(row);
      }
      return new DefaultTableModel(content, headline) {
        @Override
        public boolean isCellEditable(int row, int column) {
          // all cells false
          return false;
        }
      };
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static boolean submitPatientHealingUpdate(String patientTxt, String healingTxt) {
    Connection con = null;
    PreparedStatement stmt;
    int rs;
    boolean isUpdated = false;
    try {
      con = DriverManager.getConnection(connectionUrl);
      con.setAutoCommit(false);
      String updateString =
          """
                    INSERT INTO [Booking].[Booking] ([PatientID], [HealingInformationID])
                    VALUES (?,?)
                    """;
      stmt = con.prepareStatement(updateString);
      stmt.setString(1, patientTxt);
      stmt.setString(2, healingTxt);
      rs = stmt.executeUpdate();
      if (rs > 0) {
        isUpdated = true;
      }
      con.commit();
    } catch (SQLException e) {
      return isUpdated;
    } finally {
      closeConnect(con);
    }
    return isUpdated;
  }

  public static void showQuery(String queryTxt, JTable resultTable) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    try {
      con = DriverManager.getConnection(connectionUrl);
      stmt = con.prepareStatement(queryTxt);
      rs = stmt.executeQuery();
      if (!rs.next()) {
        JOptionPane.showMessageDialog(
            null,
            "Query return nothing! Please press clear all and do again!",
            "Message",
            JOptionPane.WARNING_MESSAGE);
      } else {
        resultTable.setModel(DbUtils.resultSetToTableModel(rs));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
  }

  public static String[] showAuthenticateQuery(String accountTxt, String pwdTxt) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String[] results = new String[2];
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    DECLARE @UserName AS VARCHAR(50) = ?
                    DECLARE @Pwd AS VARCHAR(30) = ?

                    SELECT
                           CASE
                               WHEN A.User_ID = P.UserID AND P.UserID IS NOT NULL THEN 'Patient'
                               WHEN A.User_ID = S.UserID AND S.UserID IS NOT NULL THEN 'Specialist'
                               ELSE NULL
                               END AS Role,
                           CASE
                               WHEN A.User_ID = P.UserID AND P.UserID IS NOT NULL THEN P.Patient_ID
                               WHEN A.User_ID = S.UserID AND S.UserID IS NOT NULL THEN S.Specialist_ID
                               ELSE NULL
                               END AS ID
                    FROM [Account].[Account] A
                             FULL JOIN [Account].[Patient] P ON A.User_ID = P.UserID
                             FULL JOIN [Account].[Specialist] S ON A.User_ID = S.UserID
                    WHERE A.User_name = @UserName AND A.Password = HASHBYTES('SHA1', CONCAT(@Pwd, (SELECT A.Salt FROM [Account].[Account] A WHERE A.User_name = @UserName)))
                    """;
      stmt = con.prepareStatement(preparedQuery);
      stmt.setString(1, accountTxt);
      stmt.setString(2, pwdTxt);
      rs = stmt.executeQuery();
      while (rs.next()) {
        results[0] = rs.getString("ID");
        results[1] = rs.getString("Role");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return results;
  }

  public static String showNameQuery(String userTxt, String roleTxt) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String result = "";
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    DECLARE @UserID AS INT = ?
                    DECLARE @Role AS VARCHAR(30) = ?
                    SELECT FullName
                    FROM (
                             SELECT
                                 CASE
                                     WHEN @UserID = P.Patient_ID AND @Role = 'Patient' THEN P.FullName
                                     WHEN @UserID = S.Specialist_ID AND @Role = 'Specialist' THEN S.FullName
                                     END AS FullName
                             FROM [Account].[Account] A
                                      FULL JOIN [Account].[Patient] P ON A.User_ID = P.UserID
                                      FULL JOIN [Account].[Specialist] S ON A.User_ID = S.UserID
                         ) AS SUBQUERY
                    WHERE FullName IS NOT NULL
                                        """;
      stmt = con.prepareStatement(preparedQuery);
      stmt.setInt(1, Integer.parseInt(userTxt));
      stmt.setString(2, roleTxt);
      rs = stmt.executeQuery();
      while (rs.next()) {
        result = rs.getString("fullname");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return result;
  }

  public static boolean submitPasswordUpdate(String userTxt, String oldPwdTxt, String newPwdTxt) {
    Connection con = null;
    PreparedStatement stmt;
    int rs;
    boolean isUpdated = false;
    try {
      con = DriverManager.getConnection(connectionUrl);
      con.setAutoCommit(false);
      String updateString =
          """
                    DECLARE @UserName AS VARCHAR(50) = ?
                    DECLARE @oldPwd AS VARCHAR(30) = ?
                    DECLARE @newPwd AS VARCHAR(30) = ?
                    DECLARE @Salt AS VARCHAR(5)
                    SET @Salt
                        = CONCAT(
                                    CHAR(FLOOR(RAND() * 10) + 48),
                                    CHAR(FLOOR(RAND() * 26) + 65),
                                    CHAR(FLOOR(RAND() * 26) + 97),
                                    CHAR(FLOOR(RAND() * 15) + 33),
                                    CHAR(FLOOR(RAND() * 10) + 48))
                    UPDATE [Account].[Account]
                    SET Password = HASHBYTES('SHA1', CONCAT(@newPwd, @Salt)),
                        Salt = @Salt
                    WHERE User_name = @UserName
                      AND Password  = HASHBYTES(
                            'SHA1', CONCAT(@oldPwd, (SELECT A.Salt FROM [Account].[Account] A WHERE A.User_name = @UserName)))
                                                            """;
      stmt = con.prepareStatement(updateString);
      stmt.setString(1, userTxt);
      stmt.setString(2, oldPwdTxt);
      stmt.setString(3, newPwdTxt);
      rs = stmt.executeUpdate();
      if (rs > 0) {
        isUpdated = true;
      }
      con.commit();
    } catch (SQLException e) {
      return isUpdated;
    } finally {
      closeConnect(con);
    }
    return isUpdated;
  }

  public static String showSearchQuery(String queryTxt) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    StringBuilder result = new StringBuilder();
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    SELECT S.Name AS symptom, So.Name AS solution, So.Platform, So.Description FROM [Disease].[Disease] D INNER JOIN [Disease].[DiseaseSymptom] DS ON D.Disease_ID = DS.DiseaseID
                    INNER JOIN [Disease].[Symptom] S ON DS.SymptomID = S.Symptom_ID
                    INNER JOIN [Solution].[CureOneByOne] C ON S.Symptom_ID = C.SymptomID
                    INNER JOIN [Solution].[Solution] So ON C.SolutionID = So.Solution_ID  WHERE D.Name = ?""";
      stmt = con.prepareStatement(preparedQuery);
      stmt.setString(1, queryTxt);
      rs = stmt.executeQuery();
      while (rs.next()) {
        result
            .append("Possible symptom: ")
            .append(rs.getString("symptom"))
            .append(" has the solution of: ")
            .append(rs.getString("solution"))
            .append(". Please see at: ")
            .append(rs.getString("platform"))
            .append(", more details: ")
            .append(rs.getString("description"))
            .append("\n\n");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return result.toString();
  }

  public static String showPatientBookingQuery(String patientTxt) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    StringBuilder result = new StringBuilder();
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    SELECT H.HealingInformation_ID AS ID, CONCAT(DAY(H.Date), ' / ' , MONTH(H.Date)) AS DATE , H.Place, S.FullName, S.Phone
                    FROM [Account].[Specialist] S
                    INNER JOIN [Booking].[HealingInformation] H
                    ON S.Specialist_ID = H.SpecialistID
                    INNER JOIN [Booking].[Booking] B
                    ON H.HealingInformation_ID = B.HealingInformationID
                    INNER JOIN [Account].[Patient] P
                    ON B.PatientID = P.Patient_ID
                    WHERE P.Patient_ID = ? AND H.Date >= CAST( GETDATE() AS DATE ) ORDER BY H.Date""";
      stmt = con.prepareStatement(preparedQuery);
      stmt.setString(1, patientTxt);
      rs = stmt.executeQuery();
      while (rs.next()) {
        result
            .append("ID: ")
            .append(rs.getString("id"))
            .append("\n")
            .append("Date: ")
            .append(rs.getString("date"))
            .append("\n")
            .append("Place: ")
            .append(rs.getString("place"))
            .append("\n")
            .append("Specialist name: ")
            .append(rs.getString("fullname"))
            .append("\n")
            .append("Phone: ")
            .append(rs.getString("phone"))
            .append("\n\n");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return result.toString();
  }

  public static boolean cancelHealingUpdate(String patientTxt, String healingTxt) {
    Connection con = null;
    PreparedStatement stmt;
    int rs;
    boolean isUpdated = false;
    try {
      con = DriverManager.getConnection(connectionUrl);
      con.setAutoCommit(false);
      String updateString =
          """
                    DELETE FROM [Booking].[Booking]
                    WHERE PatientID = ? AND HealingInformationID = ?
                    """;
      stmt = con.prepareStatement(updateString);
      stmt.setString(1, patientTxt);
      stmt.setString(2, healingTxt);
      rs = stmt.executeUpdate();
      if (rs > 0) {
        isUpdated = true;
      }
      con.commit();
    } catch (SQLException e) {
      return isUpdated;
    } finally {
      closeConnect(con);
    }
    return isUpdated;
  }

  public static boolean submitPatientUser(
      String accountTxt,
      String pwdTxt,
      String nameTxt,
      String dobTxt,
      String sexTxt,
      String emailTxt) {
    Connection con = null;
    PreparedStatement stmt1;
    PreparedStatement stmt2;
    int rs1;
    int rs2;
    boolean isUpdated = false;
    try {
      con = DriverManager.getConnection(connectionUrl);
      con.setAutoCommit(false);
      String updateString1 =
          """
                    DECLARE @Username AS VARCHAR(50) = ?
                    DECLARE @Pwd AS VARCHAR(30) = ?
                    DECLARE @Salt AS VARCHAR(5)
                    SET @Salt
                        = CONCAT(
                                    CHAR(FLOOR(RAND() * 10) + 48),
                                    CHAR(FLOOR(RAND() * 26) + 65),
                                    CHAR(FLOOR(RAND() * 26) + 97),
                                    CHAR(FLOOR(RAND() * 15) + 33),
                                    CHAR(FLOOR(RAND() * 10) + 48))
                    INSERT INTO [Account].[Account] ([User_name],
                                                     [Password],
                                                     [Salt])
                    VALUES (@Username, HASHBYTES('SHA1', CONCAT(@Pwd, @Salt)), @Salt)
                    """;
      stmt1 = con.prepareStatement(updateString1);
      stmt1.setString(1, accountTxt);
      stmt1.setString(2, pwdTxt);
      rs1 = stmt1.executeUpdate();
      String updateString2 =
          """
                    INSERT INTO [Account].[Patient] ([UserID],
                                                     [FullName],
                                                     [DoB],
                                                     [Sex],
                                                     [Email])
                    VALUES ((SELECT User_ID FROM [Account].[Account] A WHERE A.User_name = ?), ?, ?, ?, ?)
                    """;
      stmt2 = con.prepareStatement(updateString2);
      stmt2.setString(1, accountTxt);
      stmt2.setString(2, nameTxt);
      stmt2.setDate(3, java.sql.Date.valueOf(dobTxt));
      stmt2.setString(4, sexTxt);
      stmt2.setString(5, emailTxt);
      rs2 = stmt2.executeUpdate();
      if (rs1 > 0 && rs2 > 0) {
        isUpdated = true;
      }
      con.commit();
    } catch (SQLException e) {
      return isUpdated;
    } finally {
      closeConnect(con);
    }
    return isUpdated;
  }

  public static boolean submitHealingUpdate(
      String specialistTxt,
      String placeTxt,
      String dateTxt,
      String feeTxt,
      String descTxt,
      String extraTxt) {
    Connection con = null;
    PreparedStatement stmt;
    int rs;
    boolean isUpdated = false;
    try {
      con = DriverManager.getConnection(connectionUrl);
      con.setAutoCommit(false);
      String updateString =
          """
                    INSERT INTO [Booking].[HealingInformation] ([SpecialistID], [Place], [Date], [Fee], [Description], [Extra_Information])
                    VALUES (?,?,?,?,?,?)""";
      stmt = con.prepareStatement(updateString);
      stmt.setString(1, specialistTxt);
      stmt.setString(2, placeTxt);
      stmt.setDate(3, java.sql.Date.valueOf(dateTxt));
      stmt.setString(4, feeTxt);
      stmt.setString(5, descTxt);
      stmt.setString(6, extraTxt);
      rs = stmt.executeUpdate();
      if (rs > 0) {
        isUpdated = true;
      }
      con.commit();
    } catch (SQLException e) {
      return isUpdated;
    } finally {
      closeConnect(con);
    }
    return isUpdated;
  }

  public static String showSpecialistBookingQuery(String specialistTxt) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    StringBuilder result = new StringBuilder();
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    SELECT H.HealingInformation_ID AS ID, CONCAT(DAY(H.Date), ' / ' , MONTH(H.Date)) AS DATE, H.Place, H.Fee, P.FullName, P.Sex, P.Email,
                    CASE
                    	WHEN H.HealingInformation_ID = B.HealingInformationID THEN 'RESERVED'
                    	ELSE 'VACANT'
                    END AS State
                    FROM [Account].[Specialist] S
                    FULL JOIN [Booking].[HealingInformation] H ON S.Specialist_ID = H.SpecialistID
                    FULL JOIN [Booking].[Booking] B ON H.HealingInformation_ID = B.HealingInformationID
                    FULL JOIN [Account].[Patient] P ON B.PatientID = P.Patient_ID
                    WHERE S.[Specialist_ID] = ? AND H.Date >= CAST( GETDATE() AS DATE ) ORDER BY H.Date""";
      stmt = con.prepareStatement(preparedQuery);
      stmt.setString(1, specialistTxt);
      rs = stmt.executeQuery();
      while (rs.next()) {
        result
            .append("ID: ")
            .append(rs.getString("id"))
            .append("\n")
            .append("State: ")
            .append(rs.getString("state"))
            .append("\n")
            .append("Date: ")
            .append(rs.getString("date"))
            .append("\n")
            .append("Place: ")
            .append(rs.getString("place"))
            .append("\n")
            .append("Fee: ")
            .append(rs.getString("fee"))
            .append("\n")
            .append("Patient name: ")
            .append(rs.getString("fullname"))
            .append("\n")
            .append("Sex: ")
            .append(rs.getString("sex"))
            .append("\n")
            .append("Email: ")
            .append(rs.getString("email"))
            .append("\n")
            .append("\n");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return result.toString();
  }

  public static boolean delistHealingUpdate(String specialistTxt, String healingTxt) {
    Connection con = null;
    PreparedStatement stmt;
    int rs;
    boolean isUpdated = false;
    try {
      con = DriverManager.getConnection(connectionUrl);
      con.setAutoCommit(false);
      String updateString =
          """
                    DELETE FROM [Booking].[HealingInformation]
                    WHERE SpecialistID = ? AND HealingInformation_ID = ?
                    """;
      stmt = con.prepareStatement(updateString);
      stmt.setString(1, specialistTxt);
      stmt.setString(2, healingTxt);
      rs = stmt.executeUpdate();
      if (rs > 0) {
        isUpdated = true;
      }
      con.commit();
    } catch (SQLException e) {
      return isUpdated;
    } finally {
      closeConnect(con);
    }
    return isUpdated;
  }

  public static boolean submitSpecialistUser(
      String accountTxt,
      String pwdTxt,
      String nameTxt,
      String dobTxt,
      String sexTxt,
      String emailTxt,
      String phoneTxt,
      String idTxt,
      String graduateTxt) {
    Connection con = null;
    PreparedStatement stmt1;
    PreparedStatement stmt2;
    int rs1;
    int rs2;
    boolean isUpdated = false;
    try {
      con = DriverManager.getConnection(connectionUrl);
      con.setAutoCommit(false);
      String updateString1 =
          """
                    DECLARE @Username AS VARCHAR(50) = ?
                    DECLARE @Pwd AS VARCHAR(30) = ?
                    DECLARE @Salt AS VARCHAR(5)
                    SET @Salt
                        = CONCAT(
                                    CHAR(FLOOR(RAND() * 10) + 48),
                                    CHAR(FLOOR(RAND() * 26) + 65),
                                    CHAR(FLOOR(RAND() * 26) + 97),
                                    CHAR(FLOOR(RAND() * 15) + 33),
                                    CHAR(FLOOR(RAND() * 10) + 48))
                    INSERT INTO [Account].[Account] ([User_name],
                                                     [Password],
                                                     [Salt])
                    VALUES (@Username, HASHBYTES('SHA1', CONCAT(@Pwd, @Salt)), @Salt)
                    """;
      stmt1 = con.prepareStatement(updateString1);
      stmt1.setString(1, accountTxt);
      stmt1.setString(2, pwdTxt);
      rs1 = stmt1.executeUpdate();
      String updateString2 =
          """
                    INSERT INTO [Account].[Specialist] ([UserID],
                                                        [FullName],
                                                        [DoB],
                                                        [Sex],
                                                        [Email],
                                                        [Phone],
                                                        [IdentifyNumber],
                                                        [GraduationUniversity])
                    VALUES ((SELECT User_ID FROM [Account].[Account] A WHERE A.User_name = ?), ?, ?, ?, ?, ?, ?, ?)
                    """;
      stmt2 = con.prepareStatement(updateString2);
      stmt2.setString(1, accountTxt);
      stmt2.setString(2, nameTxt);
      stmt2.setDate(3, java.sql.Date.valueOf(dobTxt));
      stmt2.setString(4, sexTxt);
      stmt2.setString(5, emailTxt);
      stmt2.setString(6, phoneTxt);
      stmt2.setString(7, idTxt);
      stmt2.setString(8, graduateTxt);
      rs2 = stmt2.executeUpdate();
      if (rs1 > 0 && rs2 > 0) {
        isUpdated = true;
      }
      con.commit();
    } catch (SQLException e) {
      return isUpdated;
    } finally {
      closeConnect(con);
    }
    return isUpdated;
  }

  public static ArrayList<Integer> showTestQuery() {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    ArrayList<Integer> results = new ArrayList<>();
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    SELECT Test_ID AS ID
                    FROM [Test].[Test]""";
      stmt = con.prepareStatement(preparedQuery);
      rs = stmt.executeQuery();
      while (rs.next()) {
        results.add(rs.getInt("id"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return results;
  }

  public static ArrayList<Integer> showQuestionQuery(String testTxt) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    ArrayList<Integer> results = new ArrayList<>();
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    SELECT Question_ID AS questionID
                    FROM [Test].[Question]
                    WHERE TestID = ?""";
      stmt = con.prepareStatement(preparedQuery);
      stmt.setString(1, testTxt);
      rs = stmt.executeQuery();
      while (rs.next()) {
        results.add(rs.getInt("questionID"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return results;
  }

  public static String showQuestionContentQuery(String testTxt, String questionTxt) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    String result = "";
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    SELECT Title
                    FROM [Test].[Question]\s
                    WHERE TestID = ? AND Question_ID = ?""";
      stmt = con.prepareStatement(preparedQuery);
      stmt.setString(1, testTxt);
      stmt.setString(2, questionTxt);
      rs = stmt.executeQuery();
      while (rs.next()) {
        result = rs.getString("title");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return result;
  }

  public static ArrayList<String> showAnswerContentQuery(String questionTxt) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    ArrayList<String> results = new ArrayList<>();
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    SELECT A.Title
                    FROM [Test].[Question] Q
                             INNER JOIN [Test].[AnswerSet] ASET
                                        ON Q.AnswerSetID = ASET.AnswerSet_ID
                             INNER JOIN [Test].[AnswerSetContent] ASCon
                                        ON ASET.AnswerSet_ID = ASCon.AnswerSetID
                             INNER JOIN [Test].[Answer] A
                                        ON ASCon.AnswerID = A.Answer_ID
                    WHERE Q.Question_ID = ?""";
      stmt = con.prepareStatement(preparedQuery);
      stmt.setString(1, questionTxt);
      rs = stmt.executeQuery();
      while (rs.next()) {
        results.add(rs.getString("title"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return results;
  }

  public static int showTotalScoreQuery(String testTxt) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    int result = 0;
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    SELECT Total
                    FROM [Test].[Test]
                    WHERE Test_ID = ?""";
      stmt = con.prepareStatement(preparedQuery);
      stmt.setString(1, testTxt);
      rs = stmt.executeQuery();
      while (rs.next()) {
        result = rs.getInt("total");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return result;
  }

  public static ArrayList<Integer> showWeightQuery(String questionID) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    ArrayList<Integer> results = new ArrayList<>();
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    SELECT A.Weight
                    FROM [Test].[Question] Q
                    INNER JOIN [Test].[AnswerSet] ASET ON Q.AnswerSetID = ASET.AnswerSet_ID
                    INNER JOIN [Test].[AnswerSetContent] ASCon ON ASCon.AnswerSetID = ASET.AnswerSet_ID
                    INNER JOIN [Test].[Answer] A ON ASCon.AnswerID = A.Answer_ID
                    WHERE Q.Question_ID = ?
                    """;
      stmt = con.prepareStatement(preparedQuery);
      stmt.setString(1, questionID);
      rs = stmt.executeQuery();
      while (rs.next()) {
        results.add(rs.getInt("weight"));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return results;
  }

  public static String[] showSolutionQuery(String testTxt, int weight) {
    Connection con = null;
    PreparedStatement stmt;
    ResultSet rs;
    StringBuilder result = new StringBuilder();
    String[] results = new String[2];
    try {
      con = DriverManager.getConnection(connectionUrl);
      String preparedQuery =
          """
                    DECLARE @TestID AS INT = ?
                    DECLARE @Weight AS INT = ?
                    SELECT R.Result_ID AS ID, S.Name, S.Benefit, S.Platform, S.Description
                    FROM [Test].[Result] R
                             INNER JOIN [Solution].[Recommendation] Re
                                        ON R.Result_ID = Re.ResultID
                             INNER JOIN [Solution].[Solution] S
                                        ON Re.SolutionID = S.Solution_ID
                    WHERE R.TestID = @TestID AND R.Weight= @Weight AND R.Result_ID IN (SELECT R.Result_ID
                                                                           FROM [Test].[Result] R
                                                                           WHERE TestID = @TestID AND Weight = @Weight)""";
      stmt = con.prepareStatement(preparedQuery);
      stmt.setString(1, testTxt);
      stmt.setInt(2, weight);
      rs = stmt.executeQuery();
      while (rs.next()) {
        result
            .append("Possible solution: ")
            .append(rs.getString("name"))
            .append(" has the benefit of: ")
            .append(rs.getString("benefit"))
            .append(". Please see at: ")
            .append(rs.getString("platform"))
            .append(", more details: ")
            .append(rs.getString("description"))
            .append("\n\n");
        results[0] = rs.getString("id");
      }
      results[1] = result.toString();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
    return results;
  }

  public static void submitRecordUpdate(String patientTxt, String resultTxt) {
    Connection con = null;
    PreparedStatement stmt;
    try {
      con = DriverManager.getConnection(connectionUrl);
      con.setAutoCommit(false);
      String updateString =
          """
                    INSERT INTO [Account].[PatientRecord]
                    VALUES (?, ?)
                    """;
      stmt = con.prepareStatement(updateString);
      stmt.setString(1, patientTxt);
      stmt.setString(2, resultTxt);
      stmt.executeUpdate();
      con.commit();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      closeConnect(con);
    }
  }
}
