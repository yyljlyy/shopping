package com.luckin.innovation.group.utils.gen;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class DatabaseGenerateService {
    private String url;
    private DatabaseGenerateService service;
    
    private static Logger logger = LoggerFactory.getLogger(DatabaseGenerateService.class);

    /**
     * 列名转换成Java属性名
     */
    private static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "" );
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "" );
        }
        return columnToJava(tableName);
    }

    public static String tableToActionName(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "" );
        }
        return tableName;
    }

    protected void closeConnect(Connection conn, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public Connection getConn(String driverName, String url,String userName,String passWord) {
        Connection con = null;
        try {
            // Dependent on a mysql jar mysql-connector-java-5.1.37-bin.jar
            Class.forName(driverName);

        } catch (ClassNotFoundException e) {
            System.out.println("can not found class com.mysql.jdbc.Driver!");
            e.printStackTrace();
            return null;
        }
        try {
            con = DriverManager.getConnection(url,userName,passWord);
        } catch (SQLException se) {
            System.out.println("Database connection failed！" + se.getMessage());
            se.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return con;
    }

    public List query(String driverName, String url, String sql,String userName,String passWord) {
        ArrayList<Object> objects = new ArrayList<>();
        Connection conn = getConn(driverName, url,userName, passWord);
        ResultSet resultSet = null;
        try {
        	PreparedStatement ps = conn.prepareStatement(sql);
        	if(sql.contains("select") || sql.contains("SELECT")) {
        		logger.info("----->> sql:{},begin executeQuery.",sql);
        		resultSet = ps.executeQuery();
	            ResultSetMetaData rsmd = resultSet.getMetaData();
	            int count=rsmd.getColumnCount();
	            String[] names=new String[count];
	            for(int i=0;i<count;i++) {
	                names[i]=rsmd.getColumnName(i+1);
	            }
	            while (resultSet.next()){
	                HashMap<String, String> data = new HashMap<>();
	                for (String name : names) {
	                    String value = resultSet.getString(name);
	                    data.put(name, value);
	                }
	                objects.add(data);
	            }
        	}else {
        		logger.info("----->> sql:{},begin executeUpdate.",sql);
        		int executeUpdate = ps.executeUpdate();
        		objects.add(executeUpdate);
        	}
        }catch (Exception e){
            e.printStackTrace();
            return objects;
        }finally {
            closeConnect(conn, resultSet);
        }
        return objects;
    }

    abstract List getDatabase() throws SQLException;

    abstract List getSchema(String database);

    abstract List getColumn(String database, String table);
}
