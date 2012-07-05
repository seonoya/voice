package kr.teamnine.voice.tab4;

import kr.teamnine.voice.DBHandler;
import kr.teamnine.voice.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class NotePadList extends Activity {
	/** Called when the activity is first created. */
    private SimpleCursorAdapter cursorAdapter;
    ListView noteList;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_list);

		noteList = (ListView) findViewById(R.id.noteList);
		
        // data get (category list)
        DBHandler dbhandler = DBHandler.open(this);
    	Cursor cursor = dbhandler.selectNoteList();
        startManagingCursor(cursor);
        
        String[] FROM = new String[]{"noteData","date"};
        int[] TO = new int[]{R.id.noteData, R.id.noteDate};
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.note_list_row, cursor, FROM, TO );
        noteList.setAdapter(cursorAdapter);
        dbhandler.close();
        
        
        noteList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){

        	}
		});

	}
}
