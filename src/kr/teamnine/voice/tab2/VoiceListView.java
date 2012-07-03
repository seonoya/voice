package kr.teamnine.voice.tab2;


import kr.teamnine.voice.DBHandler;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import kr.teamnine.voice.R;

public class VoiceListView extends Activity {
	/** Called when the activity is first created. */
    private SimpleCursorAdapter cursorAdapter;
    ListView voiceList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
    	
        super.onCreate(savedInstanceState);
		setContentView(R.layout.tab2);

        voiceList = (ListView) findViewById(R.id.categoryList);

        
        //request Data
        Intent intent = getIntent();
        int cateCode = intent.getExtras().getInt("cateCode");
        
        
        // data get (category list)
        DBHandler dbhandler = DBHandler.open(this);
    	Cursor cursor = dbhandler.selectVoiceList(cateCode);
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

    
    @Override
    public void onBackPressed(){
    	ListMain parent = ((ListMain)getParent()); 
    	parent.onBackPressed();
    	
    }
    
}
