package kr.teamnine.voice.tab3;

import kr.teamnine.voice.DBHandler;
import kr.teamnine.voice.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;


public class HistoryList extends Activity {
	/** Called when the activity is first created. */
    private SimpleCursorAdapter cursorAdapter;
    ListView voiceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
    	
        super.onCreate(savedInstanceState);
		setContentView(R.layout.tab3);

		
		System.out.println("요긴 히스토리리스ㅡㅌ");
		Button btnGoFavorites = (Button)findViewById(R.id.goFavorites);
		btnGoFavorites.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				onBackPressed();
			}
			
		});
				
        voiceList = (ListView) findViewById(R.id.favoritesListView);

        
        // data get (category list)
        DBHandler dbhandler = DBHandler.open(this);
    	Cursor cursor = dbhandler.selectHistoryList();
        startManagingCursor(cursor);
        
        String[] FROM = new String[]{"_id","voiceData"};
        int[] TO = new int[]{R.id.code, R.id.list};
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.tab2row, cursor, FROM, TO );
        voiceList.setAdapter(cursorAdapter);
        dbhandler.close();
        
        
        voiceList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
        		System.out.println(arg3);

        	}
		});
        
        

    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
    	System.out.println(id);
    }	

    
    @Override
    public void onBackPressed(){
    	FavoritesMain parent = ((FavoritesMain)getParent()); 
    	parent.onBackPressed();
    	
    }
    
}