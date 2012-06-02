package kr.teamnine.voice;

import kr.teamnine.voice.R;

import android.app.TabActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class VoiceActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TabHost tabHost = getTabHost();
        Drawable img = getResources().getDrawable(R.drawable.img_tab_icon01);
        TabSpec tabSpec1 = tabHost.newTabSpec("Tab1").setIndicator("Ã¹ ¹øÂ° ÅÇ",img);        
        tabSpec1.setContent(R.id.tab1);
        tabHost.addTab(tabSpec1);
        
        img = getResources().getDrawable(R.drawable.img_tab_icon02);
        TabSpec tabSpec2 = tabHost.newTabSpec("Tab2").setIndicator("µÎ ¹øÂ° ÅÇ",img);
        tabSpec2.setContent(R.id.tab2);
        tabHost.addTab(tabSpec2);
        
        img = getResources().getDrawable(R.drawable.img_tab_icon03);
        TabSpec tabSpec3 = tabHost.newTabSpec("Tab3").setIndicator("¼¼ ¹øÂ° ÅÇ", img);
        tabSpec3.setContent(R.id.tab3);
        tabHost.addTab(tabSpec3);

        img = getResources().getDrawable(R.drawable.img_tab_icon04);
        TabSpec tabSpec4 = tabHost.newTabSpec("Tab4").setIndicator("³× ¹øÂ° ÅÇ", img);
        tabSpec4.setContent(R.id.tab4);
        tabHost.addTab(tabSpec4);
        
        img = getResources().getDrawable(R.drawable.img_tab_icon05);
        TabSpec tabSpec5 = tabHost.newTabSpec("Tab4").setIndicator("´Ù¼¸ ¹øÂ° ÅÇ", img);
        tabSpec5.setContent(R.id.tab5);
        tabHost.addTab(tabSpec5);
        
        tabHost.setCurrentTab(0);
        
    }
}