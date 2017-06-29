package kr.ac.seoultech.myapplication.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDatabaseHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "todo.db";
    private final static int DB_VERSION = 1;

    public TodoDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table tb_todo ( ");
        sb.append("_id integer primary key autoincrement, ");
        sb.append("title varchar(100), ");
        sb.append("content text, ");
        sb.append("create_at integer ");
        sb.append(")");
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}



