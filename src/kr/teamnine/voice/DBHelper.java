package kr.teamnine.voice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
    	
        super(context, "AppListData.db", null, 1);
        System.out.println(context.toString());
    }

    @Override
    public void onCreate(SQLiteDatabase db) { 

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {        
        db.execSQL("DROP TABLE IF EXISTS cars");
        onCreate(db);
    }
}
