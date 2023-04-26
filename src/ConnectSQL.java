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
            String preparedQuery = "SELECT S.Name FROM [Disease].[Disease] D INNER JOIN [Disease].[DiseaseSymptom] DS ON D.Disease_ID = DS.DiseaseID\n" +
                    "INNER JOIN [Disease].[Symptom] S ON DS.SymptomID = S.Symptom_ID WHERE D.Name = ? ";
            stmt = con.prepareStatement(preparedQuery);
            stmt.setString(1, txtQuery.getText());
            rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Name");
                result.append("Possible symptom: ").append(name)
                        .append("\n");
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
        String result = "";
        try {
            con = DriverManager.getConnection(connectionUrl);
            stmt = con.createStatement();
            System.out.println("Connected to the Database");
            String preparedQuery = "SELECT Title FROM [Test].[Question] WHERE Question_ID =" + txtQuery;
            PreparedStatement query = con.prepareStatement(preparedQuery);
            rs = stmt.executeQuery(txtQuery.getText());
            while (rs.next()) {
                String question = rs.getString("Title");
                result = question;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnect(rs, stmt, con);
        }
        return result;

    }
}

