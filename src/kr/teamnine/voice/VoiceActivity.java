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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        
        
        // dbüũ
        File f = new File("/data/data/kr.teamnine.voice/databases/AppListData.db");
        File folder = new File("/data/data/kr.teamnine.voice/databases/");
        
        
        if(f.exists()){
        	Log.e(TAG, "exists!!!");
        	
        }else{
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
        
        
        
        
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // ��1 - ���̽��÷��̾�
        intent = new Intent().setClass(this, VoicePlayerMain.class);
        spec = tabHost.newTabSpec("voicePlayerMain").setIndicator("���̽�",
                          res.getDrawable(R.drawable.img_tab_icon1))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        // ��2 - ī�װ� ����Ʈ
        intent = new Intent().setClass(this, ListMain.class);
        spec = tabHost.newTabSpec("listMain").setIndicator("����Ʈ",
                          res.getDrawable(R.drawable.img_tab_icon2))
                      .setContent(intent);
        tabHost.addTab(spec);

        // ��3 - ���ã�� ����Ʈ
        intent = new Intent().setClass(this, FavoritesMain.class);
        spec = tabHost.newTabSpec("favoritesMain").setIndicator("���ã��",
                          res.getDrawable(R.drawable.img_tab_icon3))
                      .setContent(intent);
        tabHost.addTab(spec);


        // ��4 - �޸���
        intent = new Intent().setClass(this, NotePadMain.class);
        spec = tabHost.newTabSpec("notePadList").setIndicator("�޸���",
                          res.getDrawable(R.drawable.img_tab_icon4))
                      .setContent(intent);
        tabHost.addTab(spec);


        // ��5 - ����
        intent = new Intent().setClass(this, AppSetting.class);
        spec = tabHost.newTabSpec("appSetting").setIndicator("����",
                          res.getDrawable(R.drawable.img_tab_icon5))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        
    }
    
    
}
