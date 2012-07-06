package kr.teamnine.voice.tab4;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.teamnine.voice.DBHandler;
import kr.teamnine.voice.R;
import kr.teamnine.voice.VoiceApplication;
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
    private int noteCode;
    private String noteData;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_view);
        
        mBodyText = (EditText) findViewById(R.id.noteDataView);

        //request Data
		VoiceApplication ACN = (VoiceApplication)getApplicationContext();
		noteData = ACN.getNoteData();
		
		if (noteData == null) {
			noteCode  = 0;
			mBodyText.setText(null);
		} else {
			noteCode = ACN.getNoteCode();
			mBodyText.setText(ACN.getNoteData());
		}


        Button resetButton = (Button) findViewById(R.id.noteResetButton);
        Button backButton =  (Button) findViewById(R.id.noteBackButton);
        
        resetButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

    }
    
	public void onClick(View v) {
		int id = v.getId();

		// close Note
		if (id == R.id.noteBackButton) {
			
	    	EditText editText = (EditText) findViewById(R.id.noteDataView);
	    	InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
	    	imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

			cmdCloseNote();
        	//reset btn
		} else if(id == R.id.noteResetButton){
			mBodyText.setText(null);
			noteCode = 0;
    	}

	}

    
    @Override
    public void onBackPressed(){
    	cmdCloseNote();
    }

	
	public void cmdCloseNote() {
		
		saveState();
		
    	NotePadMain parent = ((NotePadMain)getParent()); 
    	parent.onBackPressed();
	}
    
    


    private void saveState() {
        String body = mBodyText.getText().toString();

        if (body.length() > 0l) {
            if (noteData.length() == 0) {
            	//  생성
            	SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");  
            	Date date = new Date(System.currentTimeMillis()); 
            	String formattedDate = dateFormat.format(date);
            	
            	DBHandler dbhandler = DBHandler.open(this);
            	if (dbhandler.insertNoteData(body, formattedDate)) {
					System.out.println("생성 완료");
				} else {
					System.out.println("생성 오류");
				}
        		dbhandler.close();

            } else if (noteData.equals(body)) {
            	// 업데이트
            	SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");  
            	Date date = new Date(System.currentTimeMillis()); 
            	String formattedDate = dateFormat.format(date);   
            	
            	DBHandler dbhandler = DBHandler.open(this);
            	if (dbhandler.updateNoteData(noteCode, body, formattedDate)) {
					System.out.println("갱신 완료");
				} else {
					System.out.println("갱신 오류");
				}
        		dbhandler.close();
            }
		}
        
    }


    
}
