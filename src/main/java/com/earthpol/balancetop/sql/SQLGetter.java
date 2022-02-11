package com.earthpol.balancetop.sql;

import com.earthpol.balancetop.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SQLGetter {
    private final Main instance;
    public SQLGetter(Main instance) {
        this.instance = instance;
    }

    public Map<String,Double> balances(){
        Map<String,Double> setBalances = new HashMap<>();
        try{
            PreparedStatement ps = instance.SQL.getConnection().prepareStatement("SELECT * FROM economy ORDER BY balance DESC LIMIT 10");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                //instance.getLogger().info("Username: " + username + " | Balance: " + balance);
                assert false;
                setBalances.put(rs.getString("username"), rs.getDouble("balance"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return setBalances;
    }
}
