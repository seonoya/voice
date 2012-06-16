package kr.teamnine.voice.tab2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import kr.teamnine.voice.R;

public class CategoryListView extends Activity implements AdapterView.OnItemClickListener {
	/** Called when the activity is first created. */
    private String[] cars = {"SM3", "SM5", "SM7", "SONATA", "AVANTE", "SOUL", "K5", "K7"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2);

        
        ListView list = (ListView) findViewById(R.id.list);
        
        list.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, cars));

        list.setOnItemClickListener(this);        
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

    
    }	

}
