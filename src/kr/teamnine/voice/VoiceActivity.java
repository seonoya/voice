package kr.teamnine.voice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import kr.teamnine.voice.tab1.VoicePlayerMain;
import kr.teamnine.voice.tab2.ListMain;
import kr.teamnine.voice.tab3.FavoritesMain;
import kr.teamnine.voice.tab4.NotePadMain;
import kr.teamnine.voice.tab5.AppSetting;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;


public class VoiceActivity extends TabActivity {
	
	public final String TAG = "Ryu";
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	// Intro Image
		startActivity(new Intent(this, AppIntroActivity.class));
		overridePendingTransition(0, 0);
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // DB copy
        copyDB();
        
        // MP3 copy
        mp3Copy();
        
        
        // TabBar Init
        initMainView();
        
    }
    
    
	private void initMainView() {
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // 탭1 - 보이스플레이어
        intent = new Intent().setClass(this, VoicePlayerMain.class);
        spec = tabHost.newTabSpec("voicePlayerMain").setIndicator("보이스",
                          res.getDrawable(R.drawable.img_tab_icon1))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        // 탭2 - 카테고리 리스트
        intent = new Intent().setClass(this, ListMain.class);
        spec = tabHost.newTabSpec("listMain").setIndicator("리스트",
                          res.getDrawable(R.drawable.img_tab_icon2))
                      .setContent(intent);
        tabHost.addTab(spec);

        // 탭3 - 즐겨찾기 리스트
        intent = new Intent().setClass(this, FavoritesMain.class);
        spec = tabHost.newTabSpec("favoritesMain").setIndicator("즐겨찾기",
                          res.getDrawable(R.drawable.img_tab_icon3))
                      .setContent(intent);
        tabHost.addTab(spec);


        // 탭4 - 메모장
        intent = new Intent().setClass(this, NotePadMain.class);
        spec = tabHost.newTabSpec("notePadList").setIndicator("메모장",
                          res.getDrawable(R.drawable.img_tab_icon4))
                      .setContent(intent);
        tabHost.addTab(spec);


        // 탭5 - 설정
        intent = new Intent().setClass(this, AppSetting.class);
        spec = tabHost.newTabSpec("appSetting").setIndicator("설정",
                          res.getDrawable(R.drawable.img_tab_icon5))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
	}
    
    
    public void copyDB(){
        // db체크
        File f = new File("/data/data/kr.teamnine.voice/databases/AppListData.db");
        File folder = new File("/data/data/kr.teamnine.voice/databases/");
        
        
        if(f.exists()){
        	Log.e(TAG, "exists!!!");
        	
        }else{
        	Log.e(TAG, "create DB");
        	try {
        		folder.mkdir();
        		AssetManager am = this.getResources().getAssets();        		
				InputStream is = am.open("AppListData.db");
				f.createNewFile();
				
				long fileSize = is.available();
				
				byte[] tempData = new byte[(int)fileSize];
				
				
				is.read(tempData);
				is.close();
				
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(tempData);
				fos.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
    	
    }
    
    public void mp3Copy(){
		File f;
        File folder = new File("/mnt/sdcard/voice/");
        
        if(!folder.exists()){
        	Log.e(TAG, "MP3 exists!!!");
        	
        }else{
        	
        	try{

                folder.mkdir();
                            
        		InputStream is;
        		
        		AssetManager am = this.getResources().getAssets();        		
        		String[] list = am.list("");
        		
        		for(int i = 0; i < list.length; i++){
        			if(list[i].toLowerCase().endsWith(".mp3")){
            			
            			is = am.open(list[i]);
            			
            			f = new File("/mnt/sdcard/voice/" + list[i]);
        				f.createNewFile();
        				Log.e(TAG, "folder:::"+ f.getPath().toString());    
        				
        				long fileSize = is.available();
        				
        				byte[] tempData = new byte[(int)fileSize];
        				
        				
        				is.read(tempData);
        				is.close();
        				
        				FileOutputStream fos = new FileOutputStream(f);
        				fos.write(tempData);
        				fos.close();        	
        				
        				Log.e(TAG, "folder:::"+ f.exists());    
        			}else{
        				//System.out.println("no:" + list[i].toString() );
        			}
        			
        		}
        		
        	}catch( IOException e){
        		Log.e(TAG, ""+e.toString());
        	}        	
        	
		}

	}

}
