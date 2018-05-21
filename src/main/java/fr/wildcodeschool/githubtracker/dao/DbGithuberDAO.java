package fr.wildcodeschool.githubtracker.dao;

import com.mysql.jdbc.Driver;
import fr.wildcodeschool.githubtracker.model.Githuber;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
@JdbcGithuber
public class DbGithuberDAO implements GithuberDAO {

    @Resource(lookup = "jdbc/githubtrackerDS")
    private DataSource ds;

    @Inject
    private GithubUtils ghu;

    private Connection connection;

   // public static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/githubtracker?useUnicode=true&serverTimezone=CET";

    private  HashMap<String, Githuber> mapGithubers = new HashMap<>();

    @PostConstruct
    private void postConstruct() {
        try{
           // openConnection();
            this.connection = ds.getConnection();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }


    }

    public DbGithuberDAO() {
    }

    @Override
    public List<Githuber> getGithubers() {
        List<Githuber> githubers = new ArrayList<>();

        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM githuber");
            while (rs.next()){
                Githuber g = extractGithuberFromResultSet(rs);
                githubers.add(g);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return githubers;
    }

    @Override
    public Githuber getGithuber(String login) {
        try(PreparedStatement ps = connection.prepareStatement("SELECT * FROM githuber WHERE login = ?")){
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if ( rs.next()){
                Githuber g = extractGithuberFromResultSet(rs);
                return g;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;

    }

    @Override
    public void saveGithuber(Githuber githuber) {
        if (githuber != null && getGithuber(githuber.getLogin()) == null){
            try{
                PreparedStatement ps = connection.prepareStatement("INSERT INTO githuber VALUES (NULL , ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setLong(1, githuber.getGithubId());
                ps.setString(2, githuber.getName());
                ps.setString(3, githuber.getLogin());
                ps.setString(4, githuber.getUrl());
                ps.setString(5, githuber.getEmail());
                ps.setString(6, githuber.getBio());
                ps.setString(7, githuber.getLocation());
                ps.setString(8, githuber.getAvatarUrl());
                System.out.println(ps);
                int i = ps.executeUpdate();
                System.out.println("insert githuber : " + i);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean deleteGithuber(long id){
        try(Statement stmt = connection.createStatement()){
            int i = stmt.executeUpdate("DELETE FROM githuber WHERE id = " + id);
            System.out.println("resultat delete = " +i);
            if (i == 1){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    /* public void openConnection() throws SQLException{

        Class<?> driverClass = null;
        try {
            driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driverInstance = (Driver) driverClass.newInstance(); // new driver instance
            DriverManager.registerDriver(driverInstance); // registering
            this.connection = DriverManager.getConnection(CONNECTION_URL, "root", "jecode4wcs");
        }
        catch (ClassNotFoundException ex) {
            throw new SQLException("JDBC driver classes not found", ex);
        }
        catch (IllegalAccessException ex) {
            throw new SQLException("cannot access to the JDBC driver", ex);
        }
        catch (InstantiationException ex) {
            throw new SQLException("The instantiation of the JDBC driver failed", ex);
        }

      //  this.connection = ds.getConnection();

    }*/


    private Githuber extractGithuberFromResultSet(ResultSet rs) throws SQLException{
        Githuber g = new Githuber();
        g.setId(rs.getLong("id"));
        g.setGithubId(rs.getLong("github_id"));
        g.setName(rs.getString("name"));
        g.setLogin(rs.getString("login"));
        g.setEmail(rs.getString("email"));
        g.setUrl(rs.getString("url"));
        g.setBio(rs.getString("bio"));
        g.setLocation(rs.getString("location"));
        g.setAvatarUrl(rs.getString("avatar_url"));
        return g;
    }

}
