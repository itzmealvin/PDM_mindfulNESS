import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class ConnectSQL {
    static final String connectionUrl = "jdbc:sqlserver://sql.bsite.net\\MSSQL2016;databaseName=congbang0711_;user=congbang0711_;password=mindfulness;encrypt=true;trustServerCertificate=true;";

    public static void closeConnect(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection");
            }
        }
    }

    public static void showQuery(String queryTxt, JTable resultTable) {
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            stmt = con.prepareStatement(queryTxt);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Query return nothing! Please press clear all and do again!", "Message", JOptionPane.WARNING_MESSAGE);
            } else {
                resultTable.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
    }

    public static String[] authenticateQuery(String accountTxt, String pwdTxt) {
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        String[] results = new String[2];
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = """ 
                     DECLARE @UserName AS VARCHAR(50)= ?
                     DECLARE @Pwd AS VARCHAR(30)= ?
                     
                     SELECT A.User_ID AS ID,
                                     CASE
                                         WHEN A.User_ID = P.UserID THEN 'Patient'
                                         WHEN A.User_ID = S.UserID THEN 'Specialist'
                                         END AS Role
                     FROM [Account].[Account] A
                              FULL JOIN [Account].[Patient] P ON A.User_ID = P.UserID
                              FULL JOIN [Account].[Specialist] S ON A.User_ID = S.UserID
                     WHERE A.User_name = @UserName AND A.Password = HASHBYTES('SHA1', CONCAT(@Pwd,(SELECT A.Salt FROM [Account].[Account] A WHERE A.User_name = @UserName)))
                    """;
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, accountTxt);
            stmt.setString(2, pwdTxt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                results[0] = rs.getString(1);
                results[1] = rs.getString(2);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
        return results;
    }

    public static boolean submitPatientUser(String accountTxt, String pwdTxt, String nameTxt, String dobTxt, String sexTxt, String emailTxt) {
        Connection con = null;
        PreparedStatement stmt;
        int rs;
        boolean isUpdated = false;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            con.setAutoCommit(false);
            String updateString = """
                    DECLARE @Username AS VARCHAR(50) = ?
                    DECLARE @Pwd AS VARCHAR(30) = ?
                    SET @Salt
                        = CONCAT(
                                    CHAR(FLOOR(RAND() * 10) + 48),
                                    CHAR(FLOOR(RAND() * 26) + 65),
                                    CHAR(FLOOR(RAND() * 26) + 97),
                                    CHAR(FLOOR(RAND() * 15) + 33),
                                    CHAR(FLOOR(RAND() * 10) + 48))
                    INSERT INTO [Account].[Account] ([User_name],
                                                     [Hash],
                                                     [Salt])
                    VALUES (@UserName, HASHBYTES('SHA1', CONCAT(@Pwd, @Salt)), @Salt)
                    DECLARE @ID AS BIGINT
                    SET @ID = (SELECT User_ID FROM [Account].[Account] A WHERE A.User_name = @UserName)
                    INSERT INTO [Account].[Patient] ([UserID],
                                                     [FullName],
                                                     [DoB],
                                                     [Sex],
                                                     [Email])
                    VALUES (@ID, ?, ?, ?, ?)
                                                            """;
            stmt = con.prepareStatement(updateString);
            stmt.setString(1, accountTxt);
            stmt.setString(2, pwdTxt);
            stmt.setString(3, nameTxt);
            stmt.setString(4, dobTxt);
            stmt.setString(5, sexTxt);
            stmt.setString(6, emailTxt);
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

    public static boolean submitSpecialistUser(String accountTxt, String pwdTxt, String nameTxt, String dobTxt, String sexTxt, String emailTxt, String phoneTxt, String idTxt, String graduateTxt) {
        Connection con = null;
        PreparedStatement stmt;
        int rs;
        boolean isUpdated = false;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            con.setAutoCommit(false);
            String updateString = """
                    DECLARE @Username AS VARCHAR(50) = ?
                    DECLARE @Pwd AS VARCHAR(30) = ?
                    SET @Salt
                        = CONCAT(
                                    CHAR(FLOOR(RAND() * 10) + 48),
                                    CHAR(FLOOR(RAND() * 26) + 65),
                                    CHAR(FLOOR(RAND() * 26) + 97),
                                    CHAR(FLOOR(RAND() * 15) + 33),
                                    CHAR(FLOOR(RAND() * 10) + 48))
                    INSERT INTO [Account].[Account] ([User_name],
                                                     [Hash],
                                                     [Salt])
                    VALUES (@UserName, HASHBYTES('SHA1', CONCAT(@Pwd, @Salt)), @Salt)
                    DECLARE @ID AS BIGINT
                    SET @ID = (SELECT User_ID FROM [Account].[Account] A WHERE A.User_name = @UserName)
                    INSERT INTO [Account].[Specialist] ([UserID],
                                                        [FullName],
                                                        [DoB],
                                                        [Sex],
                                                        [Email],
                                                        [Phone],
                                                        [IdentifyNumber],
                                                        [GraduationUniversity])
                    VALUES (@ID, ?, ?, ?, ?, ?, ?, ?)
                                """;
            stmt = con.prepareStatement(updateString);
            stmt.setString(1, accountTxt);
            stmt.setString(2, pwdTxt);
            stmt.setString(3, nameTxt);
            stmt.setString(4, dobTxt);
            stmt.setString(5, sexTxt);
            stmt.setString(6, emailTxt);
            stmt.setString(7, phoneTxt);
            stmt.setString(8, idTxt);
            stmt.setString(9, graduateTxt);
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

    public static boolean updatePwd(String userTxt, String oldPwdTxt, String newPwdTxt) {
        Connection con = null;
        PreparedStatement stmt;
        int rs;
        boolean isUpdated = false;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            con.setAutoCommit(false);
            String updateString = """
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

    public static String showNameQuery(String userTxt) {
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        String result = "";
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = """ 
                     DECLARE @UserID AS INT = ?
                     SELECT P.FullName
                     FROM [Account].[Account] A
                              INNER JOIN [Account].[Patient] P ON A.User_ID = P.UserID
                     WHERE A.User_ID = @UserID
                     UNION
                     SELECT S.FullName
                     FROM [Account].[Account] A
                              INNER JOIN [Account].[Specialist] S ON A.User_ID = S.UserID
                     WHERE A.User_ID = @UserID
                    """;
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, userTxt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
        return result;
    }

    public static String showSearchQuery(String queryTxt) {
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        StringBuilder result = new StringBuilder();
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = """
                    SELECT S.Name AS symptom, So.Name AS solution, So.Platform, So.Description FROM [Disease].[Disease] D INNER JOIN [Disease].[DiseaseSymptom] DS ON D.Disease_ID = DS.DiseaseID
                    INNER JOIN [Disease].[Symptom] S ON DS.SymptomID = S.Symptom_ID
                    INNER JOIN [Solution].[CureOneByOne] C ON S.Symptom_ID = C.SymptomID
                    INNER JOIN [Solution].[Solution] So ON C.SolutionID = So.Solution_ID  WHERE D.Name = ?\s""";
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, queryTxt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result.append("Possible symptom: ").append(rs.getString("symptom")).append(" has the solution of: ").append(rs.getString("solution")).append(". Please see at: ").append(rs.getString("platform")).append(", more details: ").append(rs.getString("description")).append("\n\n");
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
            System.out.println("Connected to the Database");
            String preparedQuery = """
                    SELECT CONCAT(DAY(H.Date), ' / ' , MONTH(H.Date)) AS DATE , H.Place, S.FullName, S.Phone
                    FROM [Account].[Specialist] S
                    INNER JOIN [Booking].[HealingInformation] H
                    ON S.Specialist_ID = H.SpecialistID
                    INNER JOIN [Booking].[Booking] B
                    ON H.HealingInformation_ID = B.HealingInformationID
                    INNER JOIN [Account].[Patient] P
                    ON B.PatientID = P.Patient_ID
                    WHERE P.Patient_ID = ? ORDER BY H.Date""";
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, patientTxt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result.append("Date: ").append(rs.getString("date")).append("\n").append("Place: ").append(rs.getString("place")).append("\n").append("Specialist name: ").append(rs.getString("fullname")).append("\n").append("Phone: ").append(rs.getString("phone")).append("\n").append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
        return result.toString();
    }

    public static void showAvailableBookingQuery(JTable resultTable) {
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = """
                    SELECT H.HealingInformation_ID AS ID, H.Date, H.Place, CAST(H.Fee AS INT) AS Fee, S.FullName, S.Sex
                    FROM [Booking].[HealingInformation] H
                    INNER JOIN [Account].[Specialist] S
                    ON S.Specialist_ID = H.SpecialistID
                    WHERE H.HealingInformation_ID NOT IN (SELECT DISTINCT B.HealingInformationID
                    FROM [Booking].[Booking] B) ORDER BY H.Date""";
            stmt = con.prepareStatement(preparedQuery);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "No available booking found! Please try again later", "Message", JOptionPane.WARNING_MESSAGE);
            } else {
                resultTable.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
    }

    public static boolean submitBooking(String patientTxt, String healingTxt) {
        Connection con = null;
        PreparedStatement stmt;
        int rs;
        boolean isUpdated = false;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            con.setAutoCommit(false);
            String updateString = """
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

    public static boolean submitHealing(String specialistTxt, String placeTxt, String dateTxt, String feeTxt, String descTxt, String extraTxt) {
        Connection con = null;
        PreparedStatement stmt;
        int rs;
        boolean isUpdated = false;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            con.setAutoCommit(false);
            String updateString = """
                    INSERT INTO [Booking].[HealingInformation] ([SpecialistID], [Place], [DATE], [Fee], [Description], [Extra_Information])
                    VALUES (?,?,?,?,?,?)""";
            stmt = con.prepareStatement(updateString);
            stmt.setString(1, specialistTxt);
            stmt.setString(2, placeTxt);
            stmt.setString(3, dateTxt);
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
            System.out.println("Connected to the Database");
            String preparedQuery = """
                    SELECT H.HealingInformation_ID AS ID, CONCAT(DAY(H.Date), ' / ' , MONTH(H.Date)) AS DATE, H.Place, H.Fee, P.FullName, P.Sex, P.Email
                    FROM [Account].[Specialist] S
                    INNER JOIN [Booking].[HealingInformation] H
                    ON S.Specialist_ID = H.SpecialistID
                    INNER JOIN [Booking].[Booking] B
                    ON H.HealingInformation_ID = B.HealingInformationID
                    INNER JOIN [Account].[Patient] P
                    ON B.PatientID = P.Patient_ID
                    WHERE S.[Specialist_ID] = ? ORDER BY H.Date""";
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, specialistTxt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result.append("ID: ").append(rs.getString("id")).append("\n").append("Date: ").append(rs.getString("date")).append("\n").append("Place: ").append(rs.getString("place")).append("\n").append("Fee: ").append(rs.getString("fee")).append("\n").append("Patient name: ").append(rs.getString("fullname")).append("\n").append("Sex: ").append(rs.getString("sex")).append("\n").append("Email: ").append(rs.getString("email")).append("\n").append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
        return result.toString();
    }

    public static ArrayList<Integer> showTestQuery() {
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        ArrayList<Integer> results = new ArrayList<>();
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = """
                    SELECT DISTINCT TestID
                    FROM [Test].[Question]""";
            stmt = con.prepareStatement(preparedQuery);
            rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(rs.getInt("testID"));
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
            System.out.println("Connected to the Database");
            String preparedQuery = """
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
            System.out.println("Connected to the Database");
            String preparedQuery = """
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
            System.out.println("Connected to the Database");
            String preparedQuery = """
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
            System.out.println("Connected to the Database");
            String preparedQuery = """
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

    public static String[] showSolutionQuery(String testTxt, int weight) {
        Connection con = null;
        PreparedStatement stmt;
        ResultSet rs;
        StringBuilder result = new StringBuilder();
        String[] results = new String[2];
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = """
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
                result.append("Possible solution: ").append(rs.getString("name")).append(" has the benefit of: ").append(rs.getString("benefit")).append(". Please see at: ").append(rs.getString("platform")).append(", more details: ").append(rs.getString("description")).append("\n\n");
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

    public static boolean submitRecord(String patientTxt, String resultTxt) {
        Connection con = null;
        PreparedStatement stmt;
        int rs;
        boolean isUpdated = false;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            con.setAutoCommit(false);
            String updateString = """
                    INSERT INTO [Account].[PatientRecord]
                    VALUES (?, ?)
                    """;
            stmt = con.prepareStatement(updateString);
            stmt.setString(1, patientTxt);
            stmt.setString(2, resultTxt);
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

}
