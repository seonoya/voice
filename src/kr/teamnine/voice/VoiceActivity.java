package kr.teamnine.voice;

import kr.teamnine.voice.R;
import kr.teamnine.voice.tab1.VoicePlayerMainView;
import kr.teamnine.voice.tab2.CategoryListView;
import kr.teamnine.voice.tab3.Tab3View;
import kr.teamnine.voice.tab4.Tab4View;
import kr.teamnine.voice.tab5.Tab5View;

import android.app.TabActivity;
import android.content.Intent;
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
        TabSpec tabSpec1 = tabHost.newTabSpec("Tab1").setIndicator("보이스",img);        
        tabSpec1.setContent(new Intent(this,VoicePlayerMainView.class));
        tabHost.addTab(tabSpec1);
        
        
        img = getResources().getDrawable(R.drawable.img_tab_icon02);
        TabSpec tabSpec2 = tabHost.newTabSpec("Tab2").setIndicator("리스트",img);
        tabSpec1.setContent(new Intent(this,CategoryListView.class));
        tabHost.addTab(tabSpec2);
        
        img = getResources().getDrawable(R.drawable.img_tab_icon03);
        TabSpec tabSpec3 = tabHost.newTabSpec("Tab3").setIndicator("즐겨찾기", img);
        tabSpec1.setContent(new Intent(this,Tab3View.class));
        tabHost.addTab(tabSpec3);

        img = getResources().getDrawable(R.drawable.img_tab_icon04);
        TabSpec tabSpec4 = tabHost.newTabSpec("Tab4").setIndicator("메모장", img);
        tabSpec1.setContent(new Intent(this,Tab4View.class));
        tabHost.addTab(tabSpec4);
        
        img = getResources().getDrawable(R.drawable.img_tab_icon05);
        TabSpec tabSpec5 = tabHost.newTabSpec("Tab5").setIndicator("설정", img);
        tabSpec1.setContent(new Intent(this,Tab5View.class));
        tabHost.addTab(tabSpec5);
        
        tabHost.setCurrentTab(0);
        
    }
}
