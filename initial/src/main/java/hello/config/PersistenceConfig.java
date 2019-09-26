package hello.config;


import javax.sql.DataSource;
import java.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import com.google.common.base.Preconditions;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
@PropertySource({ "classpath:persistence-mysql.properties" })
public class PersistenceConfig {

    @Autowired
    private Environment env;


    @Bean
    public DataSource restDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("mysql.jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("mysql.jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("mysql.user")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("mysql.pass")));
        try (Connection con = dataSource.getConnection();
             CallableStatement cstmt = con.prepareCall("{call sp_get_uids}");) {
            ResultSet rs = cstmt.executeQuery();
            System.out.println("MY SQL RESULT:");
            while (rs.next()) {
                System.out.println(rs.getString("id") + ", " + rs.getString("uid"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public String mssqlDataSource() throws SQLException, ClassNotFoundException {

        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setURL(Preconditions.checkNotNull(env.getProperty("mssql.url")));
        ds.setUser(Preconditions.checkNotNull(env.getProperty("mssql.user")));
        ds.setPassword(Preconditions.checkNotNull(env.getProperty("mssql.pass")));
        JdbcTemplate template = new JdbcTemplate(ds);
        try (Connection con = ds.getConnection();
            CallableStatement cstmt = con.prepareCall("{call dbo.get_test}");) {
            ResultSet rs = cstmt.executeQuery();
            System.out.println("SQL SERVER RESULT:");
            while (rs.next()) {
                System.out.println(rs.getString("id") + ", " + rs.getString("driver-class-name"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "ds";
    }

}
