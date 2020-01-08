package person.jzh.hello.mybatis.demo.v1;

import person.jzh.hello.mybatis.entity.Test;

import java.sql.*;

/**
 * @author jzh
 * @version 1.0.0
 * @title JExecutor
 * @date 2019/12/30 10:59
 * @description：
 */
public class JExecutor {

    public <T> T query(String statement, String params){
        Connection cn = null;
        PreparedStatement ps = null;
        Test test = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?useUnicode=true&useSSL=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=GMT%2B8", "root", "123");
            cn.setAutoCommit(false);
            ps = cn.prepareStatement(statement);
            ps.setInt(1, Integer.parseInt(params));
            cn.commit();
            rs = ps.executeQuery();
            while (rs.next()){
                test = new Test();
                test.setId(rs.getInt(1));
                test.setName(rs.getString(2));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != cn){
                    cn.close();
                }
                if (null != rs){
                    rs.close();
                }
                if (null != ps){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T) test;
    }
}
