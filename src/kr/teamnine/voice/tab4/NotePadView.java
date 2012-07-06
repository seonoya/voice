package kr.teamnine.voice.tab4;

import kr.teamnine.voice.R;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.app.Activity;
import android.content.Context;

public class NotePadView extends Activity implements OnClickListener {

    private EditText mBodyText;
    private Long mRowId;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_view);
        
        mBodyText = (EditText) findViewById(R.id.noteDataView);

        
//        mRowId = (savedInstanceState == null) ? null :
//            (Long) savedInstanceState.getSerializable(NotesDbAdapter.KEY_ROWID);
//		if (mRowId == null) {
//			Bundle extras = getIntent().getExtras();
//			mRowId = extras != null ? extras.getLong(NotesDbAdapter.KEY_ROWID)
//									: null;
//		}

		populateFields();
    
        Button resetButton = (Button) findViewById(R.id.noteResetButton);
        Button backButton =  (Button) findViewById(R.id.noteBackButton);
        
        resetButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

		
    }
    
	public void onClick(View v) {
		int id = v.getId();

		// close Note
		if (id == R.id.noteBackButton) {
			cmdCloseNote();

        	//reset btn
		} else if(id == R.id.noteResetButton){
			mBodyText.setText("");
    	}

	}

    
    @Override
    public void onBackPressed(){
    	EditText editText = (EditText) findViewById(R.id.noteDataView);
    	InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    	imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    	
    	cmdCloseNote();
    }

	
	public void cmdCloseNote() {
    	NotePadMain parent = ((NotePadMain)getParent()); 
    	parent.onBackPressed();
	}
    
    
    
    private void populateFields() {
        if (mRowId != null) {
//            Cursor note = mDbHelper.fetchNote(mRowId);
//            startManagingCursor(note);
//            mBodyText.setText(note.getString(
//                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
//        outState.putSerializable(NotesDbAdapter.KEY_ROWID, mRowId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }

    private void saveState() {
        String body = mBodyText.getText().toString();

        if (mRowId == null) {
        	//  생
//            long id = mDbHelper.createNote(title, body);
//            if (id > 0) {
//                mRowId = id;
//            }
        } else {
        	// 업데이
//            mDbHelper.updateNote(mRowId, title, body);
        }
    }


    
}
