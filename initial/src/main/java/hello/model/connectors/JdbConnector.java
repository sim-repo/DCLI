package hello.model.connectors;


import hello.enums.ConnectorEnum;

import java.io.Serializable;


public class JdbConnector implements Serializable {
    public Integer id;
    public String code = "";
    public String driverClassName = "";
    public String dbURL= "";
    public String dbServerName = "";
    public String dbName = "";
    public String login = "";
    public String psw = "";
    public String endpointId = "";


    public static JdbConnector createSqlServer() {
        JdbConnector conn1 = new JdbConnector();
        conn1.code = ConnectorEnum.sqlserver.toValue();
        conn1.endpointId = "jdb";
        conn1.dbServerName = "DEEPCOOLER-PC\\SQLEXPRESS";
        conn1.dbName = "jdb2";
        conn1.login = "jservice";
        conn1.psw = "jservice";
        return conn1;
    }

    public static JdbConnector createMySql() {
        JdbConnector conn1 = new JdbConnector();
        conn1.code = ConnectorEnum.mysql.toValue();
        conn1.endpointId = "max5";
        conn1.driverClassName = "com.mysql.cj.jdbc.Driver";
        conn1.dbURL = "jdbc:mysql://localhost:3306/max5?serverTimezone=UTC&autoReconnect=true&useSSL=false";
        conn1.login = "root";
        conn1.psw = "root";
        return conn1;
    }


    @Override
    public String toString() {
        return "JdbConnector{" +
                "code='" + code + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", dbURL='" + dbURL + '\'' +
                ", dbServerName='" + dbServerName + '\'' +
                ", dbName='" + dbName + '\'' +
                ", login='" + login + '\'' +
                ", psw='" + psw + '\'' +
                ", endpointId='" + endpointId + '\'' +
                '}';
    }
}
