package kr.teamnine.voice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import kr.teamnine.voice.tab1.VoicePlayerMainView;
import kr.teamnine.voice.tab2.ListMain;
import kr.teamnine.voice.tab3.FavoritesListView;
import kr.teamnine.voice.tab4.NotePadListView;
import kr.teamnine.voice.tab5.AppSetting;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;


public class VoiceActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        
        
        // db체크
        File f = new File("/data/data/kr.teamnine.voice/databases/AppListData.db");
        File folder = new File("/data/data/kr.teamnine.voice/databases/");
        
        System.out.println("f1111111111"+f.toString());
        
        if(f.exists()){
        	System.out.println("있다면???");
        }else{
        	System.out.println("없을때 생성 시작");
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
				
				System.out.println("시발 왜안돼");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        
        
        
        
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // 탭1 - 보이스플레이어
        intent = new Intent().setClass(this, VoicePlayerMainView.class);
        spec = tabHost.newTabSpec("voicePlayer").setIndicator("보이스",
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
        intent = new Intent().setClass(this, FavoritesListView.class);
        spec = tabHost.newTabSpec("favoritesList").setIndicator("즐겨찾기",
                          res.getDrawable(R.drawable.img_tab_icon3))
                      .setContent(intent);
        tabHost.addTab(spec);


        // 탭4 - 메모장
        intent = new Intent().setClass(this, NotePadListView.class);
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
    
    
}
