package com.jiang.oder_management.insert;
import com.jiang.oder_management.utils.JdbcUtils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class InsertOrder {

    public static void insert(int com_id, int count, double price) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

            try {
                conn = JdbcUtils_DBCP.getConnection();
                conn.setAutoCommit(false);

                //区别
                //使用？占位符代替参数
                String sql = "insert into t_order(`com_id`,`time`,`count`,`price`) values (?,?,?,?)";

                st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行

                //注意点：sql.Date    数据库
                //      util.Date   java       new Date().getTime()     获得时间戳
                st.setInt(1, com_id);
                st.setDate(2, new java.sql.Date(new Date().getTime()));
                st.setInt(3, count);
                st.setDouble(4, price);


                //执行
                int i = st.executeUpdate();
                if (i > 0) {
                    System.out.println("下单成功!");
                }
                conn.commit();

            } catch (SQLException e) {
                System.out.println("商品不合法");
            } finally {
                JdbcUtils_DBCP.release(conn, st, null);
            }
        }
    }
