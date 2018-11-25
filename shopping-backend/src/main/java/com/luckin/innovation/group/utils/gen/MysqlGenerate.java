package com.luckin.innovation.group.utils.gen;

import com.luckin.innovation.group.utils.gen.bean.GenerateColumn;
import com.luckin.innovation.group.utils.gen.bean.GenerateTable;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jasper Lee (Jian) 创建于 18-7-12 上午10:04
 * @company Luckin Coffe AI Group
 * 版权所有 违法必究
 */
@Service
public class MysqlGenerate extends DatabaseGenerateService {

    private static String driverName = "com.mysql.cj.jdbc.Driver";

    public static String getDriverName() {
        return driverName;
    }

    private String url;
    private String userName;

    private String passWord;

    public static void setDriverName(String driverName) {
        MysqlGenerate.driverName = driverName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MysqlGenerate() {
    }


    @Override
    public List getDatabase() {
        List<String> database = new ArrayList<>();
        Connection conn = getConn(driverName, url,userName, passWord);
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT TABLE_SCHEMA from information_schema.COLUMNS");
            rs = ps.executeQuery();
            while (rs.next()){
                String tableSchema = rs.getString(1);
                database.add(tableSchema);
            }
        }catch (Exception e){
            e.printStackTrace();
            return database;
        }finally {
            closeConnect(conn, rs);
        }
        return database;
    }


    @Override
    public List getSchema(String database) {
        ArrayList<GenerateTable> tables = new ArrayList<>();
        ResultSet rs  = null;
        Connection conn = getConn(driverName, url,userName, passWord);
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(database, null, "%", new String[] {"TABLE"});
            while (rs.next()) {
                tables.add(new GenerateTable(rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect(conn, rs);
        }
        return tables;
    }

    public String getSchemaRemark(String database, String datetable){
        ResultSet rs  = null;
        String remark="";
        Connection conn = getConn(driverName, url,userName, passWord);
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(database, null, datetable, new String[] {"TABLE"});
            while (rs.next()) {
                remark =  rs.getString(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnect(conn, rs);
        }
        return remark;
    }

    @Override
    public List getColumn(String database, String table) {
        ArrayList<GenerateColumn> columns = new ArrayList<>();
        Connection conn = getConn(driverName, url,userName, passWord);
        ResultSet rs = null;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getColumns(database, null, table, null);
            ResultSet pkRSet = dbmd.getPrimaryKeys(database, null, table);
            String pk = "";
            while (pkRSet.next()){
                pk = pkRSet.getString(4);
            }
            while (rs.next()) {
                String pri = "";
                if(pk.equals(rs.getString(4))){
                    pri = "PRI";
                }
                columns.add(new GenerateColumn(rs.getString(4),rs.getString(6),rs.getString(12),pri));
            }
        }catch (Exception e){
            e.printStackTrace();
            return columns;
        }finally {
            closeConnect(conn, rs);
        }
        return columns;
    }
}
