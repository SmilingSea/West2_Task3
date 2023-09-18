package com.jiang.oder_management.select;

import com.jiang.oder_management.utils.JdbcUtils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectCommodityByPrice {
    public static void select(){
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils_DBCP.getConnection();
            conn.setAutoCommit(false);


            String sql = "select * from t_commodity order by `price` ";
            st = conn.prepareStatement(sql);

            rs = st.executeQuery();

            while(rs.next()){
                System.out.println(rs.getInt("c_id"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getDouble("price"));
                System.out.println("=======================================");
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JdbcUtils_DBCP.release(conn,st,null);
        }
    }
}
