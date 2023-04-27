import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.*;

public class ConnectSQL {
    static String connectionUrl = "jdbc:sqlserver://sql.bsite.net\\MSSQL2016;databaseName=congbang0711_;user=congbang0711_;password=mindfulness;encrypt=true;trustServerCertificate=true;";

    public static void closeConnect(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection");
            }
        }
    }

    public static String showSearchQuery(String txtQuery) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuilder result = new StringBuilder();
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = "SELECT TOP 5 S.Name AS symptom, So.Name AS solution, So.Platform, So.Description FROM [Disease].[Disease] D INNER JOIN [Disease].[DiseaseSymptom] DS ON D.Disease_ID = DS.DiseaseID\n" +
                    "INNER JOIN [Disease].[Symptom] S ON DS.SymptomID = S.Symptom_ID\n" +
                    "INNER JOIN [Solution].[CureOneByOne] C ON S.Symptom_ID = C.SymptomID\n" +
                    "INNER JOIN [Solution].[Solution] So ON C.SolutionID = So.Solution_ID  WHERE D.Name = ? ";
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, txtQuery);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result.append("Possible symptom: ").append(rs.getString("symptom"))
                        .append(" has the solution of: ").append(rs.getString("solution"))
                        .append(". Please view more at: ").append(rs.getString("platform"))
                        .append(" on the article: ").append(rs.getString("description"))
                        .append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
        return result.toString();

    }

    public static String showPatientBooking(String patientID) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuilder result = new StringBuilder();
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = "SELECT TOP 3 CONCAT(DAY(H.Date), ' / ' , MONTH(H.Date)) AS Date , H.Place, S.FullName, S.Phone\n" +
                    "FROM [Account].[Specialist] S\n" +
                    "INNER JOIN [Booking].[HealingInformation] H\n" +
                    "ON S.Specialist_ID = H.SpecialistID\n" +
                    "INNER JOIN [Booking].[Booking] B\n" +
                    "ON H.HealingInformation_ID = B.HealingInformationID\n" +
                    "INNER JOIN [Account].[Patient] P\n" +
                    "ON B.PatientID = P.Patient_ID\n" +
                    "WHERE P.Patient_ID = ? ORDER BY H.Date";
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, patientID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result.append("Date: ").append(rs.getString("date")).append("\n")
                        .append("Place: ").append(rs.getString("place")).append("\n")
                        .append("Specialist name: ").append(rs.getString("fullname")).append("\n")
                        .append("Phone: ").append(rs.getString("phone")).append("\n")
                        .append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
        return result.toString();
    }

    public static boolean submitBooking(String patientID, String healingID) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rs = 0;
        boolean isUpdated = false;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            con.setAutoCommit(false);
            String updateString = "INSERT INTO [Booking].[Booking] ([PatientID], [HealingInformationID])\n" +
                    "     VALUES (?,?)";
            stmt = con.prepareStatement(updateString);
            stmt.setString(1, patientID);
            stmt.setString(2, healingID);
            rs = stmt.executeUpdate();
            if (rs > 0) {
                isUpdated = true;
            }
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
        return isUpdated;
    }

    public static String showSpecialistBooking(String specialistID) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuilder result = new StringBuilder();
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = "SELECT TOP 5 H.HealingInformation_ID AS ID, CONCAT(DAY(H.Date), ' / ' , MONTH(H.Date)) AS Date, H.Place, H.Fee, P.FullName, P.Sex, P.Email\n" +
                    "FROM [Account].[Specialist] S\n" +
                    "INNER JOIN [Booking].[HealingInformation] H\n" +
                    "N S.Specialist_ID = H.SpecialistID\n" +
                    "INNER JOIN [Booking].[Booking] B\n" +
                    "ON H.HealingInformation_ID = B.HealingInformationID\n" +
                    "INNER JOIN [Account].[Patient] P\n" +
                    "ON B.PatientID = P.Patient_ID\n" +
                    "WHERE S.[Specialist_ID] = 1 ORDER BY H.Date";
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, specialistID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                result.append("ID: ").append(rs.getString("id")).append("\n")
                        .append("Date: ").append(rs.getString("date")).append("\n")
                        .append("Place: ").append(rs.getString("place")).append("\n")
                        .append("Fee: ").append(rs.getString("fee")).append("\n")
                        .append("Patient name: ").append(rs.getString("fullname")).append("\n")
                        .append("Sex: ").append(rs.getString("sex")).append("\n")
                        .append("Email: ").append(rs.getString("email")).append("\n")
                        .append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
        return result.toString();
    }

    public static boolean submitHealing(String patientID, String healingID) {
        Connection con = null;
        PreparedStatement stmt = null;
        int rs = 0;
        boolean isUpdated = false;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            con.setAutoCommit(false);
            String updateString = "INSERT INTO [Booking].[Booking] ([PatientID], [HealingInformationID])\n" +
                    "     VALUES (?,?)";
            stmt = con.prepareStatement(updateString);
            stmt.setString(1, patientID);
            stmt.setString(2, healingID);
            rs = stmt.executeUpdate();
            if (rs > 0) {
                isUpdated = true;
            }
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
        return isUpdated;
    }

    public static void showAvailableBooking(JTable resultTable) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = "SELECT H.HealingInformation_ID AS ID, H.Date, H.Place, CAST(H.Fee AS INT) AS Fee, S.FullName, S.Sex\n" +
                    "FROM [Booking].[HealingInformation] H\n" +
                    "INNER JOIN [Account].[Specialist] S\n" +
                    "\tON S.Specialist_ID = H.SpecialistID\n" +
                    "WHERE H.HealingInformation_ID NOT IN (SELECT DISTINCT B.HealingInformationID\n" +
                    "FROM [Booking].[Booking] B) ORDER BY H.Date";
            stmt = con.prepareStatement(preparedQuery);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "No available booking found! Please try again later", "Message", JOptionPane.WARNING_MESSAGE);
                return;
            } else {

                resultTable.setModel(DbUtils.resultSetToTableModel(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(con);
        }
    }


    public static String showQuestionQuery(String txtQuery) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String result = "";
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = "SELECT *\n" +
                    "FROM [Test].[Question] \n" +
                    "WHERE TestID = ?";
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, txtQuery);
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

}

