package kr.teamnine.voice.tab1;

import java.util.ArrayList;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import kr.teamnine.voice.R;
import kr.teamnine.voice.tab1.VoicePlayer;

public class VoicePlayerMain extends ActivityGroup {
	/** Called when the activity is first created. */
	public static VoicePlayerMain playerGroup; 
	private ArrayList<View>history;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab1);
		System.out.println("여기까진1");
		
		history = new ArrayList<View>();
		playerGroup = this;
        
		Intent intent = new Intent(VoicePlayerMain.this, VoicePlayer.class);
		intent.putExtra("voiceCode", 0);
		intent.putExtra("voiceTxt", "");
		View view = getLocalActivityManager().startActivity("voicePlayer", intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
		replaceView(view);		

	}
	
    public void replaceView(View view) {
		// TODO Auto-generated method stub
		history.add(view);
		setContentView(view);
		
	}

    public void back(){
    	if(history.size() > 0){
    		
    		history.remove(history.size() - 1);
    		if(history.size() == 0){
    			
    			finish();
    		}else{
    			
    			setContentView(history.get(history.size() - 1));
    		}
    	}else{
    		
    		finish();
    	}
    	
    }
    
    @Override
    public void onBackPressed(){
    	playerGroup.back();

    	return;
    }
	
}
