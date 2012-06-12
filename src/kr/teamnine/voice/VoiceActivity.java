package kr.teamnine.voice;

import kr.teamnine.voice.tab1.VoicePlayerMainView;
import kr.teamnine.voice.tab2.CategoryListView;
import kr.teamnine.voice.tab3.FavoritesListView;
import kr.teamnine.voice.tab4.NotePadListView;
import kr.teamnine.voice.tab5.AppSettingView;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;


public class VoiceActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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
        intent = new Intent().setClass(this, CategoryListView.class);
        spec = tabHost.newTabSpec("categoryList").setIndicator("리스트",
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
        intent = new Intent().setClass(this, AppSettingView.class);
        spec = tabHost.newTabSpec("appSetting").setIndicator("설정",
                          res.getDrawable(R.drawable.img_tab_icon5))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        
    }
}
