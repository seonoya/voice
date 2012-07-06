package kr.teamnine.voice.tab2;

import java.util.ArrayList;
import android.app.ActivityGroup;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import kr.teamnine.voice.tab2.CategoryList;


public class ListMain extends ActivityGroup {
	/** Called when the activity is first created. */
	public static ListMain listGroup; 
	private ArrayList<View>history;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

    	super.onCreate(savedInstanceState);

		history = new ArrayList<View>();
		listGroup = this;
        
		Intent intent = new Intent(ListMain.this, CategoryList.class);
		View view = getLocalActivityManager().startActivity("CategoryListView", intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
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
    	listGroup.back();

    	return;
    }
    
    // move Voice Player
    public void moveVoicePlayer(){
    	TabActivity parentTabActivity = (TabActivity)getParent();
    	TabHost tabHost = parentTabActivity.getTabHost();
    	tabHost.setCurrentTabByTag("voicePlayerMain");
    }

}
