package data;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListaUsers
{
    private Connection conn;

    public ListaUsers(Connection c) {
        conn = c;
    }

    public void insert(String numar_telefon, String nume, int id_user) throws SQLException
    {
        PreparedStatement ps = null;

        String sql = "INSERT INTO users(numar_telefon, nume, id_user) VALUES(?,?,?,?) ";
        ps = conn.prepareStatement(sql);
        ps.setString(1, numar_telefon);
        ps.setString(2, nume);
        ps.setInt(3, id_user);
        ps.execute();

        System.out.println("Data has been inserted!");
    }

    public List<User> readAllData()
    {
        List<User> rezervari = new ArrayList<User>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        System.out.println("All Users");
        try
        {
            String sql = "SELECT * FROM Users";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                String numar_telefon = rs.getString("numar_telefon");
                String nume = rs.getString("nume");
                int id_user = rs.getInt("id_user");

                rezervari.add(new User(numar_telefon, nume, id_user));

                /*
                System.out.println("Numar Telefon: " + numar_telefon);
                System.out.println("Nume: " + nume);
                System.out.println("Id User " + id_user + "\n");
                /*
                 */
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            try
            {
                rs.close();
                ps.close();
                conn.close();
            }
            catch (SQLException e)
            {
                System.out.println(e.toString());
            }
        }

        return rezervari;
    }

    public void remove_data(int id_user) throws IOException, SQLException
    {
        PreparedStatement ps = null;

        String sql = "DELETE FROM Users WHERE id_user = " + id_user;
        ps = conn.prepareStatement(sql);
        ps.execute();
    }

    public void change_data(int id, String coloana, String de_modificat) throws IOException, SQLException
    {
        PreparedStatement ps = null;

        String sql = "UPDATE Users SET " + coloana + " = \"" + de_modificat + "\" WHERE id_user = " + id;
        ps = conn.prepareStatement(sql);
        ps.executeUpdate();
    }

}
