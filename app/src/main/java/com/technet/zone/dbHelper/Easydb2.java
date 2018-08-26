package com.technet.zone.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class Easydb2 extends SQLiteOpenHelper {


    // Variables
    private String DATABASE_NAME, TABLE_NAME = "BOOKMARK_TABLE", SQL = "";
    private ArrayList<Column> columns = new ArrayList<>();
    private SQLiteDatabase writableDatabase;
    ContentValues contentValues = new ContentValues();
    private boolean initedDb = false;
    private static final String dbName = "bookmarks.db";

    //
    public Easydb2(Context context) {
        super(context, dbName, null, 1);
        this.context = context;
    }


    public Easydb2 addData(int columnNumber, String data) {
        if (!initedDb || writableDatabase == null) initDatabase();
        contentValues.put(columns.get(columnNumber - 1).columnName, data);
        return this;
    }

    public Easydb2 addData(String columnName, String data) {
        columnName = columnName.replaceAll(" ", "_");
        if (!initedDb || writableDatabase == null) initDatabase();
        contentValues.put(columnName, data);
        return this;
    }

    public Easydb2 addData(int columnNumber, int data) {
        if (!initedDb || writableDatabase == null) initDatabase();
        contentValues.put(columns.get(columnNumber - 1).columnName, data);
        return this;
    }

    public Easydb2 addData(String columnName, int data) {
        columnName = columnName.replaceAll(" ", "_");
        if (!initedDb || writableDatabase == null) initDatabase();
        contentValues.put(columnName, data);
        return this;
    }


    public boolean doneDataAdding() {
        long result = writableDatabase.insert(TABLE_NAME, null, contentValues);
        contentValues = new ContentValues();

        if (result == -1)
            return false;
        else
            return true;
    }

    //
    public Cursor getAllData() {
        if (!initedDb || writableDatabase == null) initDatabase();
        Cursor res = writableDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    //
    public Easydb2 updateData(int columnNumber, String data) {
        if (!initedDb || writableDatabase == null) initDatabase();
        contentValues.put(columns.get(columnNumber - 1).columnName, data);
        return this;
    }

    public Easydb2 updateData(int columnNumber, int data) {
        if (!initedDb || writableDatabase == null) initDatabase();
        contentValues.put(columns.get(columnNumber - 1).columnName, data);
        return this;
    }

    public boolean rowID(int id) {
        try {
            return writableDatabase.update(TABLE_NAME, contentValues, "id = ?", new String[]{String.valueOf(id)}) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    //
    public boolean deleteRow(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)}) == 1;
    }

    public void deleteAllDataFromTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }

    //
    public Easydb2 setTableName(String tableName) {
        this.TABLE_NAME = tableName.replaceAll(" ", "_");
        return this;
    }

    public Easydb2 addColumn(Column column) {
        columns.add(column);
        return this;
    }

    public Easydb2 doneTableColumn() {
        SQL = " CREATE TABLE " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, ";
        for (int i = 0; i < columns.size(); i++) {
            SQL += " " + columns.get(i).columnName + " " + columns.get(i).columnDataType + " ";
            if (i == columns.size() - 1) {
                SQL += " ) ";
            } else {
                SQL += " , ";
            }
        }

        if (!initedDb || writableDatabase == null) initDatabase();
        return this;
    }

    //
    public void initDatabase() {
        writableDatabase = getWritableDatabase();
        initedDb = true;
    }

    //
    public static Easydb2 init(Context context, SQLiteDatabase.CursorFactory factory, int version) {
//        if (!dbName.endsWith(".db"))
//            dbName += ".db";
//        dbName = dbName.replaceAll(" ", "_");
        return new Easydb2(context, dbName, factory, version);
    }

    public static Easydb2 init(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        if (!dbName.endsWith(".db"))
            dbName += ".db";
        dbName = dbName.replaceAll(" ", "_");
        return new Easydb2(context, dbName, factory, version, errorHandler);
    }

    //
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.writableDatabase = db;
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Saving, just in case :)
    // Codes below this might once or never be used :D
    private Context context;
    private SQLiteDatabase.CursorFactory factory;
    private int version;
    private DatabaseErrorHandler errorHandler;

    private Easydb2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        //
        this.context = context;
        this.DATABASE_NAME = name;
        this.factory = factory;
        this.version = version;
    }

    private Easydb2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);

        //
        this.context = context;
        this.DATABASE_NAME = name;
        this.factory = factory;
        this.version = version;
        this.errorHandler = errorHandler;
    }
}