package kr.teamnine.voice.tab5;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import kr.teamnine.voice.R;

public class AppSetting extends PreferenceActivity {

	protected void onCreate(Bundle savedlnstanceState){
		
		super.onCreate(savedlnstanceState);
		addPreferencesFromResource(R.xml.setting);
	}
	
}
