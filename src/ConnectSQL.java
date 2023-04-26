import javax.swing.*;
import java.sql.*;

public class ConnectSQL {
    static String connectionUrl = "jdbc:sqlserver://sql.bsite.net\\MSSQL2016;databaseName=congbang0711_;user=congbang0711_;password=mindfulness;encrypt=true;trustServerCertificate=true;";

    public static void closeConnect(ResultSet rs, Statement stmt, Connection con) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("Error closing result set");
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing statement");
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection");
            }
        }
    }

    public static String showSearchQuery(JTextField txtQuery) {
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
            stmt.setString(1, txtQuery.getText());
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
            closeConnect(rs, stmt, con);
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
                result.append("Date:").append(rs.getString("date")).append("\n")
                        .append("Place: ").append(rs.getString("place")).append("\n")
                        .append("Specialist name: ").append(rs.getString("fullname")).append("\n")
                        .append("Phone: ").append(rs.getString("phone")).append("\n")
                        .append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(rs, stmt, con);
        }
        return result.toString();
    }

    public static String showAvailableBooking() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuilder result = new StringBuilder();
        try {
            con = DriverManager.getConnection(connectionUrl);
            System.out.println("Connected to the Database");
            String preparedQuery = "SELECT H.HealingInformation_ID AS ID, H.Date, H.Place, H.Fee, S.FullName, S.Sex\n" +
                    "FROM [Booking].[HealingInformation] H\n" +
                    "INNER JOIN [Account].[Specialist] S\n" +
                    "\tON S.Specialist_ID = H.SpecialistID\n" +
                    "WHERE H.HealingInformation_ID NOT IN (SELECT DISTINCT B.HealingInformationID\n" +
                    "FROM [Booking].[Booking] B) ORDER BY H.Date";
            stmt = con.prepareStatement(preparedQuery);
            rs = stmt.executeQuery();
            int numOfCols = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= numOfCols; i++) {
                result.append(rs.getMetaData().getColumnName(i)).append("\t");
            }
            result.append("\n");
            while (rs.next()) {
                for (int i = 1; i <= numOfCols; i++) {
                    result.append(rs.getObject(i)).append("\t");
                }
                result.append("\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(rs, stmt, con);
        }
        return result.toString();
    }


    public static String showQuestionQuery(JTextArea txtQuery) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        StringBuilder result = new StringBuilder();
        try {
            con = DriverManager.getConnection(connectionUrl);
            stmt = con.createStatement();
            System.out.println("Connected to the Database");
            String preparedQuery = "SELECT Title FROM [Test].[Question] WHERE Question_ID =" + txtQuery;
            PreparedStatement query = con.prepareStatement(preparedQuery);
            rs = stmt.executeQuery(txtQuery.getText());
            while (rs.next()) {
                result.append(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(rs, stmt, con);
        }
        return result.toString();

    }


}

