package kr.teamnine.voice.tab3;

import kr.teamnine.voice.DBHandler;
import android.app.ActivityGroup;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import kr.teamnine.voice.R;

public class FavoritesListView extends ActivityGroup  {
	/** Called when the activity is first created. */
    private SimpleCursorAdapter cursorAdapter;
    ListView voiceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
    	
        super.onCreate(savedInstanceState);
		setContentView(R.layout.tab3);

		Button btngoHistory = (Button)findViewById(R.id.goHistory);
		btngoHistory.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){

				tempMove();
			}
			
		});
		


		
        voiceList = (ListView) findViewById(R.id.favoritesList);

        // data get (category list)
        DBHandler dbhandler = DBHandler.open(this);
    	Cursor cursor = dbhandler.selectFavoritesList();
        startManagingCursor(cursor);
        
        String[] FROM = new String[]{"_id","voiceData"};
        int[] TO = new int[]{R.id.code, R.id.list};
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.tab2row, cursor, FROM, TO );
        voiceList.setAdapter(cursorAdapter);
        dbhandler.close();
        
        
        voiceList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
        		tempMove();
        	}
		});
        
        

    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
    	System.out.println(id);
    }	

    public void onClick(View v){
    	
    }
    
    
    @Override
    public void onBackPressed(){
    	FavoritesMain parent = ((FavoritesMain)getParent()); 
    	parent.onBackPressed();
    	
    }
    
    public void tempMove(){
    	
		System.out.println("--->"+ FavoritesMain.favoritesGroup);
		Intent intent = new Intent(FavoritesListView.this, HistoryList.class);
		
		View view = FavoritesMain.favoritesGroup.getLocalActivityManager()
				.startActivity("HistoryList", intent
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
			
			FavoritesMain.favoritesGroup.replaceView(view);

    	
    	
    }
    
}