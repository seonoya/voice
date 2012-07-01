package kr.teamnine.voice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBHandler {
    private DBHelper helper;
    private SQLiteDatabase db;
    
    private DBHandler(Context ctx) {
        this.helper = new DBHelper(ctx);
        this.db = helper.getWritableDatabase();
    }

    public static DBHandler open(Context ctx) throws SQLException {
        DBHandler handler = new DBHandler(ctx);        

        return handler;    
    }
    
    public void close() {
        helper.close();
    }

    public long insert(String car_name) {
        ContentValues values = new ContentValues();
        values.put("car_name", car_name);        

        return db.insert("cars", null, values);
    } 

    public Cursor select(int id) throws SQLException {        
        Cursor cursor = db.query(true, "cars", 
                                new String[] {"_id", "car_name"},
                                "_id" + "=" + id, 
                                null, null, null, null, null);        
        if (cursor != null) { cursor.moveToFirst(); }        

        return cursor;
    }

    // 카테고리 리스트
    public Cursor selectAll() throws SQLException {
        Cursor cursor = db.rawQuery("SELECT cateCode as _id, cateName from tCategory ",null);
        
        if (cursor != null) { cursor.moveToFirst(); }
        
        return cursor;
    }
    
    // Voice List
    public Cursor selectVoiceList(int cateCode) throws SQLException {
    	Cursor cursor;
    	if(cateCode == 0){
    		cursor = db.rawQuery("SELECT voiceCode as _id, voiceData from tVoiceData ",null);
    	}else{
    		String param[] = {"cateCode"};
    		cursor = db.rawQuery("SELECT voiceCode as _id, voiceData from tVoiceData where cateCode = ? ",param);
    	}
        
        
        if (cursor != null) { cursor.moveToFirst(); }
        
        return cursor;
    }

    
    // Favorites List
    public Cursor selectFavoritesList() throws SQLException {
    	Cursor cursor;
   		cursor = db.rawQuery("SELECT voiceCode as _id, voiceData from tFavorites ",null);
        
        
        if (cursor != null) { cursor.moveToFirst(); }
        
        return cursor;
    }

    // History List
    public Cursor selectHistoryList() throws SQLException {
    	Cursor cursor;
   		cursor = db.rawQuery("SELECT voiceCode as _id, voiceData from tHistoryData ",null);
        
        
        if (cursor != null) { cursor.moveToFirst(); }
        
        return cursor;
    }
    
}
