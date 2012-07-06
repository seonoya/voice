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

public class VoiceManaul extends ActivityGroup implements OnClickListener{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.tab1_manaul);
		
		Button btnClose			= (Button)findViewById(R.id.manaulClose);
		btnClose.setOnClickListener(this);
		
	}

	
    
    
    
    @Override
    public void onBackPressed(){
    	VoicePlayerMain parent = ((VoicePlayerMain)getParent()); 
    	parent.onBackPressed();
    	
    }





	public void onClick(View v) {
		// TODO Auto-generated method stub
		onBackPressed();
	}
    
}
