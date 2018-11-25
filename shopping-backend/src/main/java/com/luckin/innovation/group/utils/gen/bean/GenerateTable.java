package com.luckin.innovation.group.utils.gen.bean;

/**
 * @author xxljlxx
 */
public class GenerateTable {
    private String tableName;
    private String tableType;
    private String remarks;

    public GenerateTable() {
    }

    public GenerateTable(String tableName, String tableType, String remarks) {
        this.tableName = tableName;
        this.tableType = tableType;
        this.remarks = remarks;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
