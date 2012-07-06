package kr.teamnine.voice.tab4;

import kr.teamnine.voice.DBHandler;
import kr.teamnine.voice.R;
import kr.teamnine.voice.VoiceApplication;

import android.app.ActivityGroup;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class NotePadList extends ActivityGroup implements OnClickListener {
	/** Called when the activity is first created. */
	private SimpleCursorAdapter cursorAdapter;
    private Cursor mNotesCursor;	
	ListView noteList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_list);

		Button btnNoteAdd = (Button) findViewById(R.id.addButton);
		btnNoteAdd.setOnClickListener(this);

		noteList = (ListView) findViewById(R.id.noteList);

		// 리스트뷰의 경계선을 노란색으로
		noteList.setDivider(new ColorDrawable(Color.LTGRAY));
		// 경계선의 굵기를 1px
		noteList.setDividerHeight(1);
		
		fillData();

		noteList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

        		Intent intent = new Intent(NotePadList.this, NotePadView.class);

                Cursor c = mNotesCursor;
        		
        		VoiceApplication ACN = (VoiceApplication)getApplicationContext();        		
        	    ACN.setNoteCode((int)arg3);
        	    ACN.setNoteData(c.getString(c.getColumnIndexOrThrow("noteData")));

        		View view = NotePadMain.noteGroup.getLocalActivityManager()
        				.startActivity("NotePadView", intent
        				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
        		
        		NotePadMain.noteGroup.replaceView(view);	
				
			}
		});

	}

	private void fillData() {
        DBHandler dbhandler = DBHandler.open(this);
        mNotesCursor = dbhandler.selectNoteList();
        startManagingCursor(mNotesCursor);
        
        String[] FROM = new String[]{"noteData", "date"};
        int[] TO = new int[] { R.id.noteData, R.id.noteDate };
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.note_list_row, mNotesCursor, FROM, TO);
        noteList.setAdapter(cursorAdapter);

		dbhandler.close();		
	}
	
	public void onClick(View v) {
		int id = v.getId();

		// add Note
		if (id == R.id.addButton) {
			cmdAddNote();
		}
	}

	public void cmdAddNote() {
		System.out.println("버튼클릭");
		
		Intent intent = new Intent(this, NotePadView.class);
		
		VoiceApplication ACN = (VoiceApplication)getApplicationContext();        		
	    ACN.setNoteCode(0);
	    ACN.setNoteData(null);
		
		View view = NotePadMain.noteGroup.getLocalActivityManager()
				.startActivity("NotePadView", intent
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		
		NotePadMain.noteGroup.replaceView(view);	
		
	}
	
	
	
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("fillData();");
        fillData();
    }

	
	
	
}
