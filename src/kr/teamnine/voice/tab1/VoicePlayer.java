package kr.teamnine.voice.tab1;


import java.io.File;
import java.io.IOException;



import kr.teamnine.voice.DBHandler;
import kr.teamnine.voice.R;
import kr.teamnine.voice.VoiceApplication;
import android.app.ActivityGroup;
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
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TabHost;
import android.widget.Toast;

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
    private LinearLayout dynamicLayout;
    private int LENGTH_TO_SHOW = Toast.LENGTH_SHORT;
	int voiceCode = 0;
    String voiceTxt = "";


    
    
    
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
		
		Button btnAddFavo		= (Button)findViewById(R.id.insertFavoites);
		Button btnDelFavo		= (Button)findViewById(R.id.deleteFavoites);
		
		btnPlay.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnInsertVoice.setOnClickListener(this);
		btnSearchVoice.setOnClickListener(this);
		btnInsertNotePad.setOnClickListener(this);
		btnViewManaul.setOnClickListener(this);
		btnAddFavo.setOnClickListener(this);
		btnDelFavo.setOnClickListener(this);
 
        
    } 
    
    @Override
    protected void onResume () {
    	
    	super.onResume();
    	init();
    }

    public void init(){
    	
    	//환경설정 세팅
    	setGlobalSetting();
    	
        //request Data
        VoiceApplication ACN = (VoiceApplication)getApplicationContext();        		
		voiceCode = ACN.getVoiceCode();
        voiceTxt = ACN.getVoicevoiceTxt();
        boolean onStart = ACN.getOnStart();
        
        if (voiceCode == 0)voiceCode = 1;
        if (voiceTxt == "")voiceTxt="123";
        Log.e("시작",voiceCode +"//"+ voiceTxt);
        
        final TextView mTextView = (TextView)findViewById(R.id.playerTextView);
        mTextView.setText(voiceTxt);
        

        // 기 저장된 파일 
        if(voiceCode > 0){
        	
        	fileName = getFileName(voiceCode);
        	
        }else{

//        	// mp3 file Setting / insert voice Data
//        	fileDownload = new FileDownload();
//            fileName = fileDownload.startDownload(voiceTxt);
//            
//            // 초성 가져오기''
//            
//            voiceCode = (int) dbhandler.insertVoiceData(voiceTxt, " ", fileName, 0);
//            
//        	cursor = dbhandler.selectFileName(voiceCode);
//            if (cursor.getCount() == 0) {
//            	Log.e(TAG, "selectFileName bad");
//            	//nothing
//            } else {         
//            	
//                fileName = cursor.getString(cursor.getColumnIndex("fileName"));
//                Log.e(TAG, "selectFileName :" + fileName);
//            }
//
//            cursor.close();                
            
        }

        // get filePath
        //mp3Path = getMp3Path(voiceCode);

        mp3Path = filePath + "/" + fileName; //test
        // auto Play
        if(autoPlay && onStart)playMp3();        
            
        
   	
    }
    
    public void setRecentList(){
    	DBHandler dbhandler = DBHandler.open(this);
    	Cursor cursor = dbhandler.selectRecntVoice();

    	dynamicLayout = (LinearLayout)findViewById(R.id.recentList);
        
//      while(cursor.moveToNext()){
//      	cursor.moveToFirst();
////      	Button recentBtn = new Button(this);
////      	recentBtn.setId(cursor.getColumnIndex("_id"));
////      	recentBtn.setText(cursor.getString(cursor.getColumnIndex("voiceData")));
////      	
////      	recentBtn.setOnClickListener(this);
////      	dynamicLayout.addView(recentBtn, new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT ));
//      	
//      }
//      cursor.close();
      
//      String[] FROM = new String[]{"_id", "voiceData", "fileName"};
//      int[] TO = new int[]{R.id.recentCode, R.id.recentBtn};
//      cursorAdapter = new SimpleCursorAdapter(this, R.layout.tab1_recent_list, cursor, FROM, TO );
//      recentList.setAdapter(cursorAdapter);
//      System.out.println(recentList.getCount());    	
        dbhandler.close();
    }
    
    public String getFileName(int voiceCode){
    	DBHandler dbhandler = DBHandler.open(this);
    	Cursor cursor = dbhandler.selectRecntVoice();

    	cursor = dbhandler.selectFileName(voiceCode);
        if (cursor.getCount() == 0) {
        	Log.e(TAG, "selectFileName bad");
        } else {    
        	
            cursor.moveToFirst();
            fileName = cursor.getString(cursor.getColumnIndex("fileName"));
            checkFavo(Integer.parseInt(cursor.getString(cursor.getColumnIndex("favo"))));
            Log.e(TAG, "selectFileName :" + fileName);
        }

        cursor.close();                
        dbhandler.close();
        
        
    	return fileName;
    	
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
    	    		
        	//stop btn
    	}else if(id == R.id.insertFavoites){
    		addFavo(voiceCode, voiceTxt);

        	//stop btn
    	}else if(id == R.id.deleteFavoites){
    		delavo(voiceCode);
    	}else{
    		//nothing
    	}
    	
    }
   
    public void checkFavo(int cnt){
    	if(cnt > 0){
        	findViewById(R.id.insertFavoites).setVisibility(View.INVISIBLE);
        	findViewById(R.id.deleteFavoites).setVisibility(View.VISIBLE);
    		
    	}else{
        	findViewById(R.id.insertFavoites).setVisibility(View.VISIBLE);
        	findViewById(R.id.deleteFavoites).setVisibility(View.INVISIBLE);
    	}
    	
    }
    
    
    public void addFavo(int voiceCode, String voiceTxt){
    	
    	
    	if(voiceCode > 0){
    		
            int isSucess = 0;
        	DBHandler dbhandler = DBHandler.open(this);
            isSucess = (int)dbhandler.insertFavorites(voiceCode, voiceTxt);
            dbhandler.close();
            
            if(isSucess > 0){
            	
            	findViewById(R.id.insertFavoites).setVisibility(View.INVISIBLE);
            	findViewById(R.id.deleteFavoites).setVisibility(View.VISIBLE);
            	
            	Toast.makeText(VoicePlayer.this, "즐겨찾기등록  성공", LENGTH_TO_SHOW).show();
            	
            	
            }else{

            	Toast.makeText(VoicePlayer.this, "즐겨찾기등록  실패", LENGTH_TO_SHOW).show();
            	
            }
    		
    	}else{
    		Toast.makeText(VoicePlayer.this, "즐겨찾기할수 있는 보이스가 없습니다.", LENGTH_TO_SHOW).show();
    		
    	}
        
    }
    
 
    public void delavo(int voiceCode){
    	
    	
    	if(voiceCode > 0){
    		
            int isSucess = 1;
        	DBHandler dbhandler = DBHandler.open(this);
            dbhandler.deleteFavorites(voiceCode);
            dbhandler.close();
            
            if(isSucess > 0){
            	
            	findViewById(R.id.deleteFavoites).setVisibility(View.INVISIBLE);
            	findViewById(R.id.insertFavoites).setVisibility(View.VISIBLE);
            	
            	Toast.makeText(VoicePlayer.this, "즐겨찾기삭제  성공", LENGTH_TO_SHOW).show();
            	
            	
            }else{

            	Toast.makeText(VoicePlayer.this, "즐겨찾기삭제  실패", LENGTH_TO_SHOW).show();
            	
            }
    		
    	}else{
    		Toast.makeText(VoicePlayer.this, "즐겨찾기할수 있는 보이스가 없습니다.", LENGTH_TO_SHOW).show();
    		
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
					
			        VoiceApplication ACN = (VoiceApplication)getApplicationContext();
			        System.out.println(ACN.getOnStart());
			        ACN.setOnStart(false);					
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
