package com.jiang.oder_management.update;



import com.jiang.oder_management.utils.JdbcUtils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class UpdateCommodity {

    public static void update(int c_id,String name,double price) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils_DBCP.getConnection();
            conn.setAutoCommit(false);

            //区别
            //使用？占位符代替参数
            String sql = "update `t_commodity` set `name`=?,`price`=? where `c_id`=?";

            st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行

            st.setString(1,name);
            st.setDouble(2,price);
            st.setInt(3, c_id);


            //执行
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("修改成功!");
            }
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils_DBCP.release(conn, st, null);
        }
    }
}
