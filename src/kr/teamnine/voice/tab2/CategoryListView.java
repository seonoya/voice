package kr.teamnine.voice.tab2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CategoryListView extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(this);
		textView.setText("��2");
		setContentView(textView);
	}
}