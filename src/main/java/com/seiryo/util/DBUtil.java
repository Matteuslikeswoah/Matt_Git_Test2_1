package com.seiryo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**   
* 
* @ClassName: DBUtil.java
* @Description: JDBC工具类
* @author: matteus
* @date: 2024年10月17日
*
*/
public class DBUtil {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/JavaWeb_Project2?characterEncoding=UTF-8";
    private static final String USER = "root";
    private static final String PASSWORD = "qwerty123";

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    
    /**
     * @MethodName: initDatabase
     * @Description: 初始化数据库，获取连接
     * @throws SQLException
     */
    private static void initDatabase() throws SQLException, ClassNotFoundException {
        //加载数据库驱动
        Class.forName(DRIVER);

        //连接数据库
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * @MethodName: prepareStatement
     * @Description: 组装 PreparedStatement
     * @param sql 执行的 SQL 语句
     * @param objects SQL 参数
     */
    private static void prepareStatement(String sql, Object... objects) throws SQLException {
        //创建 PreparedStatement 对象
        preparedStatement = connection.prepareStatement(sql);

        //插入相关数据
        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }
    }

    /**
     * @MethodName: executeUpdate
     * @Description: 执行增删改操作
     * @param sql 执行的 SQL 语句
     * @param objects SQL 参数
     * @return 是否成功执行
     */
    public static boolean executeUpdate(String sql, Object... objects) throws SQLException, ClassNotFoundException {
        try {
            //初始化数据库连接
            initDatabase();

            //组装 PreparedStatement
            prepareStatement(sql, objects);

            //执行 SQL
            return preparedStatement.executeUpdate() > 0;
        } finally {
            //关闭使用的资源
            closeResources();
        }
    }

    /**
     * @MethodName: executeQuery
     * @Description: 执行查询操作
     * @param sql 执行的 SQL 语句
     * @param objects SQL 参数
     * @return ResultSet 查询结果集
     */
    public static ResultSet executeQuery(String sql, Object... objects) throws SQLException, ClassNotFoundException {
        try {
            //初始化数据库连接
            initDatabase();

            //组装PreparedStatement
            prepareStatement(sql, objects);

            //执行查询操作
            resultSet = preparedStatement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * @MethodName: closeResources
     * @Description: 关闭本类中使用的数据库资源
     */
    private static void closeResources() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * @MethodName: closeExternalResources
     * @Description: 关闭外部传入的数据库资源
     */
    public static void closeExternalResources(Connection con, PreparedStatement prepar, ResultSet rst) throws SQLException {
        if (rst != null) {
            rst.close();
        }
        if (prepar != null) {
            prepar.close();
        }
        if (con != null) {
            con.close();
        }
    }
}
