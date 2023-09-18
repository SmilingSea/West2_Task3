package com.jiang.oder_management.delete;


import com.jiang.oder_management.utils.JdbcUtils_DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteCommodity {

        public static void drop(int c_id) {
            Connection conn = null;
            PreparedStatement st = null;
            PreparedStatement st1 = null;
            ResultSet rs = null;

            try {
                conn = JdbcUtils_DBCP.getConnection();
                conn.setAutoCommit(false);

                //区别
                //使用？占位符代替参数
                String sql = "delete from t_commodity where `c_id` = ?";
                String sql1 = "select * from t_order";

                st = conn.prepareStatement(sql);//预编译SQL，先写sql，然后不执行
                st1 = conn.prepareStatement(sql1);

                rs = st1.executeQuery();

                //如果删除已在订单中的商品，则会删除失败
                while(rs.next()) {
                    if (rs.getInt("com_id") ==c_id){
                        throw new Exception();
                    }
                }
                st.setInt(1, c_id);



                //执行
                int i = st.executeUpdate();
                if (i > 0) {
                    System.out.println("删除成功!");
                }
                conn.commit();

            } catch (Exception e) {
                System.out.println("删除失败！");
            } finally {
                JdbcUtils_DBCP.release(conn, st, rs);
            }
        }
    }


