package com.jiang.oder_management.insert;


import com.jiang.oder_management.utils.JdbcUtils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class InsertCommodity {

    public static void insert(int c_id,String name,double price) {
        Connection conn = null;
        PreparedStatement st = null;


        try {
            conn = JdbcUtils_DBCP.getConnection();
            conn.setAutoCommit(false);

            //区别
            //使用？占位符代替参数
            String sql = "insert into t_commodity(`c_id`,`name`,`price`) values (?,?,?)";

            st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行

            st.setInt(1, c_id);
            st.setString(2,name);
            st.setDouble(3, price);


            //执行
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("添加成功!");
            }
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils_DBCP.release(conn, st, null);
        }
    }
}
