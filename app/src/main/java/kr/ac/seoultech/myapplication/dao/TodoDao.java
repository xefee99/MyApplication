package kr.ac.seoultech.myapplication.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.ac.seoultech.myapplication.helper.TodoDatabaseHelper;
import kr.ac.seoultech.myapplication.model.Todo;

public class TodoDao {

    private TodoDatabaseHelper helper;
    private SQLiteDatabase db;
    public TodoDao(TodoDatabaseHelper helper) {
        this.helper = helper;
        this.db = this.helper.getWritableDatabase();
    }

    public void close() {
        this.db.close();
        this.helper.close();
    }

    public Long insert(Todo todo) {

        ContentValues values = new ContentValues();
        values.put("title", todo.getTitle());
        values.put("content", todo.getContent());
        values.put("create_at", todo.getCreateAt().getTime());

        Long id = db.insert("tb_todo", null, values);

        return id;
    }

    public void update(Todo todo) {

        ContentValues values = new ContentValues();
        values.put("title", todo.getTitle());
        values.put("content", todo.getContent());

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{ String.valueOf(todo.getId()) };

//        String idStr = String.valueOf(todo.getId());
//        String[] whereArgs = new String[1];
//        whereArgs[0] = idStr;

        db.update("tb_todo", values, whereClause, whereArgs);
    }

    public void delete(Long id) {
        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{ String.valueOf(id) };

        db.delete("tb_todo", whereClause, whereArgs);
    }

    public Todo select(Long id) {
        throw new RuntimeException("not implemented");
    }

    public List<Todo> selectList() {

        String[] columns = null;
        String whereClause = null;
        String[] whereArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = "_id desc";
        String limit = null;

        Cursor cursor = db.query("tb_todo", columns, whereClause, whereArgs, groupBy, having, orderBy, limit);

        int size = cursor.getCount();
        if (size == 0) return new ArrayList<Todo>();

        List<Todo> list = new ArrayList<>();

        int idIndex = cursor.getColumnIndex("_id");
        int titleIndex = cursor.getColumnIndex("title");
        int contentIndex = cursor.getColumnIndex("content");
        int createAtIndex = cursor.getColumnIndex("create_at");

        cursor.moveToFirst();
        for (int i=0; i<size; i++) {

            Long id = cursor.getLong(idIndex);
            String title = cursor.getString(titleIndex);
            String content = cursor.getString(contentIndex);
            long createAtMilli = cursor.getLong(createAtIndex);

            Todo todo = new Todo(id, title, content, new Date(createAtMilli));
            list.add(todo);
            cursor.moveToNext();
        }
        cursor.close();

        return list;
    }

}
