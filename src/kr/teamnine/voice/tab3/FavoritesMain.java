package kr.teamnine.voice.tab3;

import java.util.ArrayList;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import kr.teamnine.voice.R;
import kr.teamnine.voice.tab3.FavoritesList;


public class FavoritesMain extends ActivityGroup {
	/** Called when the activity is first created. */
	public static FavoritesMain favoritesGroup; 
	private ArrayList<View>history;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

    	super.onCreate(savedInstanceState);
		setContentView(R.layout.tab3);
		
		history = new ArrayList<View>();
		favoritesGroup = this;
        
		System.out.println("요긴 어디: 즐겨찾기 메인");
		Intent intent = new Intent(FavoritesMain.this, FavoritesList.class);
		View view = getLocalActivityManager().startActivity("FavoritesList", intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
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
    	favoritesGroup.back();

    	return;
    }
}