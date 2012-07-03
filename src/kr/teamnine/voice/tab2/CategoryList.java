package kr.teamnine.voice.tab2;

import kr.teamnine.voice.DBHandler;
import android.app.ActivityGroup;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import kr.teamnine.voice.R;


public class CategoryList extends ActivityGroup {
	/** Called when the activity is first created. */
    private SimpleCursorAdapter cursorAdapter;
    ListView categoryList;
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

    	super.onCreate(savedInstanceState);
		setContentView(R.layout.tab2);
		//getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.tab2titlebar);
		
		
        categoryList = (ListView) findViewById(R.id.categoryList);

        
        
        // data get (category list)
        DBHandler dbhandler = DBHandler.open(this);
    	Cursor cursor = dbhandler.selectAll();
        startManagingCursor(cursor);
        
        String[] FROM = new String[]{"_id","cateName"};
        int[] TO = new int[]{R.id.code, R.id.list};
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.tab2row, cursor, FROM, TO );
        categoryList.setAdapter(cursorAdapter);
        dbhandler.close();
        
        
        categoryList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
        		Intent intent = new Intent(CategoryList.this, VoiceListView.class);
        		intent.putExtra("cateCode", arg3);
        		
        		System.out.println("=============="+categoryList.getId());
        		View view = ListMain.listGroup.getLocalActivityManager()
        				.startActivity("VoiceListView", intent
        				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
        		
        		ListMain.listGroup.replaceView(view);		

        	}
		});

        
        
    }

}
