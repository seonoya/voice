package kr.teamnine.voice.tab1;

import kr.teamnine.voice.DBHandler;
import kr.teamnine.voice.R;
import kr.teamnine.voice.VoiceApplication;
import kr.teamnine.voice.tab2.ListMain;
import android.app.ActivityGroup;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class VoiceEdit extends ActivityGroup implements OnClickListener{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.tab1_voice_insert);

 
		Button btnSave			= (Button)findViewById(R.id.insertBoiceSaveBtn);
		Button btnClose			= (Button)findViewById(R.id.insertBoiceCloseBtn);
		
		btnSave.setOnClickListener(this);
		btnClose.setOnClickListener(this);
		
	}

	
    public void onClick(View v){
    	int id = v.getId();
    	
    	
    	//Save btn
    	if(id == R.id.insertBoiceSaveBtn){
    		saveVoice();
    	
    	//Close btn
    	}else if(id == R.id.insertBoiceCloseBtn){
    		onBackPressed();

    	}
    	
    }
    
    
    // save voice
    public void saveVoice(){
    	EditText voiceEdit =(EditText)findViewById(R.id.noteDataEdit);
    	String voiceTxt = voiceEdit.getText().toString();
    	
    	// file save
        FileDownload fileDownload;
        DBHandler dbhandler = DBHandler.open(this);
    	fileDownload = new FileDownload();
        String fileName = fileDownload.startDownload(voiceTxt);
        
        // 초성 가져오기''
        
        // data insert
        int voiceCode = (int) dbhandler.insertVoiceData(voiceTxt, " ", fileName, 0);
        
        Log.e("voice insert", voiceCode +"//"+ voiceTxt);
        
        VoiceApplication ACN = (VoiceApplication)getApplicationContext();        		
		ACN.setVoiceCode(voiceCode);
		ACN.setVoiceTxt(voiceTxt);
		ACN.setOnStart(true);

		onBackPressed();
        
        
    	
        
        
        
    }
    
    
    
    
    
    
    
    @Override
    public void onBackPressed(){
    	VoicePlayerMain parent = ((VoicePlayerMain)getParent()); 
    	parent.onBackPressed();
    	
    }
    
}
