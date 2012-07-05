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

    
    
    
    // voice insert
    public long insertVoiceData(String voiceData, String choData, String fileName, int cateCode){
    	
    	ContentValues values = new ContentValues();
    	values.put("voiceData"	, voiceData);
    	values.put("choData"	, choData);
    	values.put("fileName"	, fileName);
    	values.put("cateCode"	, cateCode);
    	values.put("count"		, 0);
    	values.put("data"		, 0);
    	
    	return db.insert("tVoiceData", null, values);
    	
    }
    
    // favorites insert
    public long insertFavorites(int voiceCode, String voiceData){
    	
    	ContentValues values = new ContentValues();
    	values.put("voiceCode"	, voiceCode);
    	values.put("voiceData"	, voiceData);

    	return db.insert("tFavorites", null, values);
    	
    }    
    
    // category insert
    public long insertCategory(int cateName){
    	
    	ContentValues values = new ContentValues();
    	values.put("cateName"	, cateName);

    	return db.insert("tCategory", null, values);
    	
    }        
   
    // category insert
    public long insertHistory(int voiceCode, String voiceData, String date){
    	
    	ContentValues values = new ContentValues();
    	values.put("voiceCode"	, voiceCode);
    	values.put("voiceData"	, voiceData);
    	values.put("date"		, date);
    	
    	return db.insert("tHistoryData", null, values);
    	
    }    
    
        
    
    
    

    public Cursor select(int id) throws SQLException {        
        Cursor cursor = db.query(true, "cars", 
                                new String[] {"_id", "car_name"},
                                "_id" + "=" + id, 
                                null, null, null, null, null);        
        if (cursor != null) { cursor.moveToFirst(); }        

        return cursor;
    }

    // recent voice
    public Cursor selectRecntVoice(){
    	String param[] = {"voiceCode"};
    	Cursor cursor = db.rawQuery(" SELECT voiceCode as _id , fileName " +
    								" FROM tVoiceData as A join tHistoryData as B on a.voiceCode = b.voiceCode " +
    								" order by b.date desc limit 0,10 ", param);
    	return cursor;
    }
    
    
    
    // voice Select
    public Cursor selectFileName(int voiceCode){
    	String param[] = {"voiceCode"};
    	Cursor cursor = db.rawQuery("SELECT voiceCode as _id , fileName FROM tVoiceData where voiceCode = ? ", param);
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
