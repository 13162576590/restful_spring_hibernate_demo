package com.icss.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
*
* @author 肖海鹏
*
*/
public class BaseDao {

    protected Connection conn;  //数据库连接句柄

    /*
     * 获取数据库连接
     */
    protected void openconnection() throws ClassNotFoundException,SQLException{
        try {
            if( conn == null || conn.isClosed() ){
                Class.forName("com.mysql.jdbc.Driver");  //使用反射技术动态加载数据库的驱动
                conn = DriverManager.getConnection("jdbc:mysql:///day16","root","root");
            }
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        }
    }

    public void closeResource(){

        if(conn != null){
            try {
                conn.close();
                conn = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void beginTransaction() throws Exception{
        this.openconnection();
        if(conn != null){
            conn.setAutoCommit(false);
        }

    }

    public void commit() throws Exception{
        if(conn != null){
            conn.commit();
        }
    }

    public void rollback() throws Exception{
        if(conn != null){
            conn.rollback();
        }

    }
}
