package com.jiang.oder_management.delete;

import com.jiang.oder_management.utils.JdbcUtils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteOrder {

    public static void drop(int id) {
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = JdbcUtils_DBCP.getConnection();
            conn.setAutoCommit(false);

            //区别
            //使用？占位符代替参数
            String sql = "delete from t_order where `id` = ?";

            st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行

            st.setInt(1, id);


            //执行
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("删除成功!");
            }
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils_DBCP.release(conn, st, null);
        }
    }
}


