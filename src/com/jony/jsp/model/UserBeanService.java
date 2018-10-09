package com.jony.jsp.model;

import com.jony.jsp.dao.ConnDB;
import com.mysql.cj.exceptions.ExceptionFactory;

import java.sql.*;
import java.util.ArrayList;

public class UserBeanService {
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection connection=null;

    public Integer getPageNomber(int pageSize){
        int rowCount=0,pageNomber=0;
        String sql = "SELECT count(*) FROM haoyun.customers";
        connection = new ConnDB().getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                rowCount = resultSet.getInt(1);
            }
            if(rowCount%pageSize==0){
                pageNomber = rowCount/pageSize;
            }else{
                pageNomber = rowCount/pageSize + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pageNomber;
    }

    public boolean check(String username, String passwd){
        connection = new ConnDB().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Passwd FROM haoyun.Users where Username=? LIMIT 1");
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if (passwd.equals(resultSet.getString(1))){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection, preparedStatement,resultSet);
        }
        return false;
    }

    public Boolean deleteUser(int userId){
        if(userId<1){
            throw ExceptionFactory.createException("Wrong input userId number");
        }
        int result = 0;
        String sql = "DELETE FROM haoyun.customers  WHERE ID = ?";
        Connection connection = new ConnDB().getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection, preparedStatement,resultSet);
        }
        return result==1;
    }

    public Boolean updateUser(UserBean userBean){
        if(userBean.getId()<1){
            throw ExceptionFactory.createException("Wrong ID");
        }
        int result = 0;
        String sql = "UPDATE haoyun.customers SET Name=?,City=?,Age=? WHERE ID = ?";
        Connection connection = new ConnDB().getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userBean.getName());
            preparedStatement.setString(2,userBean.getCity());
            preparedStatement.setInt(3,userBean.getAge());
            preparedStatement.setInt(4,userBean.getId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection, preparedStatement,resultSet);
        }
        return result==1;
    }

    public Boolean insertUser(UserBean userBean){
        if(userBean.getName()==null){
            throw ExceptionFactory.createException("You must at least pass the user's name");
        }
        int result = 0;
        String sql = "INSERT INTO haoyun.customers (Name, City, Age) VALUES (?,?,?)";
        Connection connection = new ConnDB().getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userBean.getName());
            preparedStatement.setString(2,userBean.getCity());
            preparedStatement.setInt(3,userBean.getAge());
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection, preparedStatement,resultSet);
        }
        return result==1;
    }

    public UserBean getUser(int userId){
        if(userId<1){
            throw ExceptionFactory.createException("Wrong input userId number");
        }
        UserBean userBean=null;
        String sql = "SELECT * FROM haoyun.customers  WHERE ID = ?";
        Connection connection = new ConnDB().getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                userBean = new UserBean(resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("City"),
                        resultSet.getInt("Age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection, preparedStatement,resultSet);
        }
        return userBean;
    }

    public UserBean getUserByName(String userName,Boolean flag){
        if(userName==null){
            throw ExceptionFactory.createException("Wrong input userId number");
        }
        UserBean userBean=null;
        String sql;
        if(flag){
            sql= "SELECT * FROM haoyun.jsp_test  WHERE Name = ?";
        }else {
            sql="SELECT * FROM haoyun.jsp_test  WHERE Name LIKE ?";
        }

        Connection connection = new ConnDB().getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,'%'+userName+'%');
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                userBean = new UserBean(resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("City"),
                        resultSet.getInt("Age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection, preparedStatement,resultSet);
        }
        return userBean;
    }

    public ArrayList<UserBean> getUsers(int pageSize, int pageNow) {
        if(pageNow<1){
            throw ExceptionFactory.createException("Wrong input pagenow number");
        }
        if(pageSize<1){
            throw ExceptionFactory.createException("Wrong input pagesize number");
        }
        ArrayList<UserBean> userBeans=new ArrayList<>();
        String sql = "SELECT * FROM haoyun.customers  LIMIT ? OFFSET ?";
        Connection connection = new ConnDB().getConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,pageSize);
            preparedStatement.setInt(2,pageSize*(pageNow-1));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UserBean userBean = new UserBean(resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("City"),
                        resultSet.getInt("Age"));
                userBeans.add(userBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(connection, preparedStatement,resultSet);
        }
        return userBeans;
    }

    private static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection!=null){
                connection.close();
            }
            if (statement!=null){
                statement.close();
            }
            if (resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
