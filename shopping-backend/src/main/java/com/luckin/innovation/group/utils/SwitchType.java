package com.luckin.innovation.group.utils;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
public class SwitchType {
    public String switchType(String type) {
        String dataType = StringUtils.lowerCase(type);
        switch (dataType) {
            case "int":
                return "Integer";
            case "varchar":
                return "String";
            case "bigint":
                return "BigInteger";
            case "text":
                return "Text";
            case "tinytext":
                return "String";
            case "tinyint":
                return "SmallInteger";
            case "float":
                return "Float";
            case "decimal":
                return "Decimal";
            case "bit":
                return "bir";
            case "double":
                return "Double";
            case "date":
                return "Date";
            case "datetime":
                return "Date";
            case "timestamp":
                return "Date";
            case "mediumtext":
                return "MediumText";
            default:
                return "";
        }
    }

    public String lower(String type) {
        return StringUtils.lowerCase(type);
    }

    public String ct(String columnName) {
        String tablePrefix = "luckin";
        if (org.apache.commons.lang.StringUtils.isNotBlank(tablePrefix)) {
            columnName = columnName.replace(tablePrefix, "" );
        }
        String replace = WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
        return WordUtils.uncapitalize(replace);
    }

    public String bt(String columnName) {
        String tablePrefix = "luckin";
        if (org.apache.commons.lang.StringUtils.isNotBlank(tablePrefix)) {
            columnName = columnName.replace(tablePrefix, "" );
        }
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }
}
