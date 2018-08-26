package com.technet.zone.dbHelper;

public class Column {

    //
    String columnName, columnDataType;

    //
    public Column(String columnName, String columnDataType) {
        this.columnName = columnName.replaceAll(" ", "_");
        this.columnDataType = columnDataType;
    }
}