package kr.teamnine.voice.tab2;


import kr.teamnine.voice.DBHandler;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import kr.teamnine.voice.R;

public class CategoryListView extends ListActivity {
	/** Called when the activity is first created. */
    private SimpleCursorAdapter cursorAdapter;
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
    	
        super.onCreate(savedInstanceState);
        DBHandler dbhandler = DBHandler.open(this);
        
        Cursor cursor = dbhandler.selectAll();
        startManagingCursor(cursor);
        
        String[] FROM = new String[]{"cateCode","cateName"};
        int[] TO = new int[]{R.id.code, R.id.list};
        
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.tab2row, cursor, FROM, TO );
        setListAdapter(cursorAdapter);        
        
        dbhandler.close();
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {

    	System.out.println(id);
    }	

}
