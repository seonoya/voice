package kr.teamnine.voice.tab3;

import java.util.ArrayList;

import kr.teamnine.voice.R;
import kr.teamnine.voice.tab3.FavoritesListView;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FavoritesMain extends ActivityGroup {
	/** Called when the activity is first created. */
	public static FavoritesMain listGroup; 
	private ArrayList<View>history;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

    	super.onCreate(savedInstanceState);
		setContentView(R.layout.tab3);
		
		history = new ArrayList<View>();
		listGroup = this;
        
		Intent intent = new Intent(FavoritesMain.this, FavoritesListView.class);
		View view = getLocalActivityManager().startActivity("FavoritesListView", intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
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
}