package kr.teamnine.voice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;

public class AppIntroActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_intro);

		initialize();

	}

	private void initialize() {
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				finish(); // 액티비티 종료
				overridePendingTransition(0, 0);
			}
		};

		handler.sendEmptyMessageDelayed(0, 1000); // 2초후 종료시킴

	}

}
