package com.earthpol.balancetop.sql;

import com.earthpol.balancetop.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    //Move This To A Config!
    private String host = Main.getInstance().getConfig().getString("host");
    private String port = Main.getInstance().getConfig().getString("port");
    private String database = Main.getInstance().getConfig().getString("database");
    private String username = Main.getInstance().getConfig().getString("username");
    private String password = Main.getInstance().getConfig().getString("password");

    private Connection connection;

    public boolean isConnected() {return(connection != null);}

    public void connect() throws ClassNotFoundException, SQLException {
        if(!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=true", username, password);
        }
    }

    public void disconnect(){
        if(isConnected()){
            try {
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        return connection;
    }
}