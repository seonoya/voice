package kr.teamnine.voice.tab4;

import kr.teamnine.voice.DBHandler;
import kr.teamnine.voice.R;

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
	ListView noteList;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_list);

		Button btnNoteAdd = (Button) findViewById(R.id.addButton);
		btnNoteAdd.setOnClickListener(this);

		noteList = (ListView) findViewById(R.id.noteList);

		// data get (category list)
		DBHandler dbhandler = DBHandler.open(this);
		Cursor cursor = dbhandler.selectNoteList();
		startManagingCursor(cursor);

		String[] FROM = new String[] { "noteData", "date" };
		int[] TO = new int[] { R.id.noteData, R.id.noteDate };
		cursorAdapter = new SimpleCursorAdapter(this, R.layout.note_list_row,
				cursor, FROM, TO);
		noteList.setAdapter(cursorAdapter);

		// 리스트뷰의 경계선을 노란색으로
		noteList.setDivider(new ColorDrawable(Color.LTGRAY));
		// 경계선의 굵기를 1px
		noteList.setDividerHeight(1);

		dbhandler.close();

		noteList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

        		Intent intent = new Intent(NotePadList.this, NotePadView.class);
        		intent.putExtra("cateCode", arg3);
        		
        		System.out.println("=============="+noteList.getId());
        		View view = NotePadMain.noteGroup.getLocalActivityManager()
        				.startActivity("NotePadView", intent
        				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
        		
        		NotePadMain.noteGroup.replaceView(view);	
				
			}
		});

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
		
		View view = NotePadMain.noteGroup.getLocalActivityManager()
				.startActivity("NotePadView", intent
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		
		NotePadMain.noteGroup.replaceView(view);	
		
		
	}

}
