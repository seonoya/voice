package kr.teamnine.voice.tab2;


import kr.teamnine.voice.DBHandler;
import kr.teamnine.voice.R;
import kr.teamnine.voice.VoiceApplication;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

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
		VoiceApplication ACN = (VoiceApplication)getApplicationContext();        		
	    int cateCode = 0;//ACN.getCateCode();
        
        // data get (category list)
        System.out.println("category code" + cateCode);
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
        		//View parentView = (View) arg0[arg3].getParent();
        	    VoiceApplication ACN = (VoiceApplication)getApplicationContext();        		
        	    
        	    ACN.setVoiceCode(Integer.parseInt(((TextView)arg1.findViewById(R.id.code)).getText().toString()));
        		ACN.setVoiceTxt( ((TextView)arg1.findViewById(R.id.list)).getText().toString() );
        		ACN.setOnStart(true);
        		
        		ListMain.listGroup.moveVoicePlayer();


        	}
		});
        
        

    }

    
    @Override
    public void onBackPressed(){
    	ListMain parent = ((ListMain)getParent()); 
    	parent.onBackPressed();
    	
    }
    
}
