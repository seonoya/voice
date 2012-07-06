package kr.teamnine.voice.tab1;


import java.io.File;
import java.io.IOException;



import kr.teamnine.voice.DBHandler;
import kr.teamnine.voice.R;
import kr.teamnine.voice.VoiceApplication;
import kr.teamnine.voice.tab2.CategoryList;
import kr.teamnine.voice.tab2.ListMain;
import kr.teamnine.voice.tab2.VoiceListView;
import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost;

public class VoicePlayer extends ActivityGroup implements OnClickListener{
	
	public final String TAG = "Ryu";
    

    private boolean autoPlay = false;
    private boolean vibration = false;
    private FileDownload fileDownload;
	private SimpleCursorAdapter cursorAdapter;
    public MediaPlayer mp;
    public String filePath = "/mnt/sdcard/voice";
    public String fileName = "";
    public String mp3Path = "";
    public ListView recentList;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.tab1);

		// button setting () 
		Button btnPlay			= (Button)findViewById(R.id.mp3play);
		Button btnStop			= (Button)findViewById(R.id.mp3stop);
		Button btnInsertVoice	= (Button)findViewById(R.id.insertVoice);
		Button btnSearchVoice	= (Button)findViewById(R.id.searchVoice);
		Button btnInsertNotePad = (Button)findViewById(R.id.insertNotePad);
		Button btnViewManaul	= (Button)findViewById(R.id.viewManaul);
		
		btnPlay.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnInsertVoice.setOnClickListener(this);
		btnSearchVoice.setOnClickListener(this);
		btnInsertNotePad.setOnClickListener(this);
		btnViewManaul.setOnClickListener(this);
		
		

		

 
        
    } 
    
    @Override
    protected void onResume () {
    	
    	super.onResume();
        DBHandler dbhandler = DBHandler.open(this);
    	
    	//환경설정 세팅
    	setGlobalSetting();
    	
        // recent voice
        //recentList = (ListView) findViewById(R.id.horizontalScrollView1);
        
    	Cursor cursor = dbhandler.selectRecntVoice();
        startManagingCursor(cursor);
        
        String[] FROM = new String[]{"_id", "voiceData", "fileName"};
        int[] TO = new int[]{R.id.code, R.id.recentBtn};
        cursorAdapter = new SimpleCursorAdapter(this, R.layout.tab1_recent_list, cursor, FROM, TO );
        //recentList.setAdapter(cursorAdapter);

        
        
        
        //request Data
        VoiceApplication ACN = (VoiceApplication)getApplicationContext();        		
		int voiceCode = ACN.getVoiceCode();
        String voiceTxt = ACN.getVoicevoiceTxt();
        
        Log.e("시작",voiceCode +"//"+ voiceTxt);

        if (voiceCode == 0)voiceCode = 1;
        if (voiceTxt == "")voiceTxt="123";
        
        Log.e("시작",voiceCode +"//"+ voiceTxt);
        // 기 저장된 파일 
        if(voiceCode > 0){
        	
        	cursor = dbhandler.selectFileName(voiceCode);
            if (cursor.getCount() == 0) {
            	Log.e(TAG, "selectFileName bad");
            	//nothing
            } else {    
            	
                cursor.moveToFirst();
                fileName = cursor.getString(cursor.getColumnIndex("fileName"));
                Log.e(TAG, "selectFileName :" + fileName);
            }

            cursor.close();                
                 	
        	
        	
        }else{

        	// mp3 file Setting / insert voice Data
        	fileDownload = new FileDownload();
            fileName = fileDownload.startDownload(voiceTxt);
            
            // 초성 가져오기''
            
            voiceCode = (int) dbhandler.insertVoiceData(voiceTxt, " ", fileName, 0);
            
        	cursor = dbhandler.selectFileName(voiceCode);
            if (cursor.getCount() == 0) {
            	Log.e(TAG, "selectFileName bad");
            	//nothing
            } else {         
            	
                fileName = cursor.getString(cursor.getColumnIndex("fileName"));
                Log.e(TAG, "selectFileName :" + fileName);
            }

            cursor.close();                
            
        }

        // get filePath
        //mp3Path = getMp3Path(voiceCode);

        mp3Path = filePath + "/" + fileName; //test
        
        File temp = new File(mp3Path);
        Log.e(TAG, temp.getPath() + temp.exists());
        // auto Play
        if(autoPlay)playMp3();        
        
            	
    	
    }
    
    
    public void setGlobalSetting(){
        // get setting Info
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        autoPlay	=  pref.getBoolean("autoPlay", false);
        vibration	=  pref.getBoolean("vibration", false);    	
    	
    }
    
    
    public void onClick(View v){
    	int id = v.getId();
    	
    	//play btn
    	if(id == R.id.mp3play){
    		playMp3();
    	
    	//stop btn
    	}else if(id == R.id.mp3stop){
    		stopMp3();

    	//stop btn
    	}else if(id == R.id.insertVoice){
    		moveInsertVoice();

    	//stop btn
    	}else if(id == R.id.searchVoice){
    		moveList();

        	//stop btn
    	}else if(id == R.id.insertNotePad){
    		moveNotePad();

        	//stop btn
    	}else if(id == R.id.viewManaul){
    		moveManaul();
    	
    	}else{
    		//nothing
    	}
    	
    }
    
    // togle Btn play/stop
    public void toglePlay(){
    	
    	
    }
    
    
    // get mp3 path
    public String getMp3Path(int voiceCode){
    	String filePath = "";
    	
    	
    	
    	return filePath;
    }
    
    
    // play mp3
    public void playMp3(){
    	try{
    		
    		if(vibration)onVibrate();
    		mp = new MediaPlayer();
        	mp.reset();
        	mp.setDataSource(mp3Path);
        	mp.prepare();
        	mp.start();
    		
			mp.setOnCompletionListener(new OnCompletionListener() {
				
				public void onCompletion(MediaPlayer arg0) {
					// TODO Auto-generated method stub
					if(vibration)onVibrate();
				}
			});
    		
    	} catch(IOException e){
    		
    		Log.e(TAG, ":" + mp3Path + "//"+ e.toString());
    	}
    	
    }

    // stop mp3
    public void stopMp3(){
    	try{
    		
    		mp.stop();
    		mp.release();
    		if(vibration)onVibrate();
    		
    	}catch (IllegalStateException e){
    		Log.e(TAG, "" + e.toString());
    	}
    	
    }
    
    
    // Vibrate
    public void onVibrate(){
    	Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    	vibe.vibrate(100);                    
    }

    
    // move insert Voice
    public void moveInsertVoice(){
		Intent intent = new Intent(VoicePlayer.this, VoiceEdit.class);
		View view = VoicePlayerMain.playerGroup.getLocalActivityManager()
				.startActivity("VoiceEdit", intent
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		VoicePlayerMain.playerGroup.replaceView(view);		
    }
    
    // move View Manaul
    public void moveManaul(){
		Intent intent = new Intent(VoicePlayer.this, VoiceManaul.class);
		View view = VoicePlayerMain.playerGroup.getLocalActivityManager()
				.startActivity("VoiceManaul", intent
				.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		VoicePlayerMain.playerGroup.replaceView(view);		
    }    

    
    
    
    // move List (search)
    public void moveList(){
    	VoicePlayerMain.playerGroup.moveList();
    }

    // move notePad
    public void moveNotePad(){
    	VoicePlayerMain.playerGroup.moveList();
    }
}
