package com.github.stormwyrm.javawebsample.register_login_sys.dao;

import com.github.stormwyrm.javawebsample.register_login_sys.domain.User;

import java.sql.*;

public class UserDaoImp implements IUserDao {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/liqingfeng?useSSL=false&serverTimezone=UTC";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "fengfeng1314.";


    @Override
    public User findUser(String username, String password) {
        Connection connect = createConnect();
        Statement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connect.createStatement();
            String sql = String.format("SELECT * FROM user WHERE username = '%s' and password = '%s'", username, password);
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet, statement, connect);
        }
        return user;
    }

    @Override
    public User findUser(String username) {
        Connection connect = createConnect();
        Statement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            statement = connect.createStatement();
            String sql = String.format("SELECT * FROM user WHERE username = '%s'", username);
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet, statement, connect);
        }
        return user;
    }

    @Override
    public boolean add(User user) {
        Connection connect = createConnect();
        Statement statement = null;
        try {
            statement = connect.createStatement();
            String sql = String.format("INSERT INTO USER (username, password, email) VALUES ('%s','%s','%s')", user.getUsername(), user.getPassword(), user.getEmail());
            return statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement, connect);
        }
        return false;
    }

    private Connection createConnect() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    private void close(AutoCloseable... closeables) {
        for (AutoCloseable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
