package kr.teamnine.voice.tab3;

import kr.teamnine.voice.DBHandler;
import kr.teamnine.voice.VoiceApplication;
import android.app.ActivityGroup;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import kr.teamnine.voice.R;
import kr.teamnine.voice.tab2.ListMain;

public class FavoritesList extends ActivityGroup  {
	/** Called when the activity is first created. */
    private SimpleCursorAdapter cursorAdapter;
    ListView voiceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
    	
        super.onCreate(savedInstanceState);
		setContentView(R.layout.tab3);
        voiceList = (ListView) findViewById(R.id.favoritesListView);

 
    }
    
    @Override
    protected void onResume () {
    	
    	super.onResume();
    	init();
    }
    
    public void init(){
    	
        
        // data get (category list)
        DBHandler dbhandler = DBHandler.open(this);
    	Cursor cursor = dbhandler.selectFavoritesList();
        startManagingCursor(cursor);
        
        String[] FROM = new String[]{"_id","voiceData"};
        int[] TO = new int[]{R.id.code, R.id.list};
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.tab2row, cursor, FROM, TO );
        voiceList.setAdapter(cursorAdapter);
        //Log.e("favoi cnt" , voiceList.getCount());
        System.out.println("voie count"+cursor.getCount());
        dbhandler.close();
        
        
        voiceList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){

        	    VoiceApplication ACN = (VoiceApplication)getApplicationContext();        		
        	    
        	    ACN.setVoiceCode(Integer.parseInt(((TextView)arg1.findViewById(R.id.code)).getText().toString()));
        		ACN.setVoiceTxt( ((TextView)arg1.findViewById(R.id.list)).getText().toString() );
        		ACN.setOnStart(true);
        		Log.e("", ACN.getVoiceCode() + "//" + ACN.getVoicevoiceTxt());
        		FavoritesMain.favoritesGroup.moveVoicePlayer();
        		
        		
        	}
		});
        
        
		Button btngoHistory = (Button)findViewById(R.id.goHistory);
		btngoHistory.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v)
			{
				
				
        		Intent intent = new Intent(FavoritesList.this, HistoryList.class);
        		System.out.println("--->"+FavoritesMain.favoritesGroup.getTaskId());
        		View view = FavoritesMain.favoritesGroup.getLocalActivityManager()
        				.startActivity("HistoryList", intent
        				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
        		
        		FavoritesMain.favoritesGroup.replaceView(view);		
				
				
			}
			
		});

    	
    }


    
}