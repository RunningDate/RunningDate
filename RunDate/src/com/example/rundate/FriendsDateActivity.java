package com.example.rundate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FriendsDateActivity extends Activity {
	
	ListView friendsTrendsList;//好友动态列表

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_friends_date);
		
		
		friendsTrendsList =(ListView)findViewById(R.id.friends_trends);
		//从服务器获得数据，假设获得的数据是一个list
		//遍历每一个数据
//		for(datalist:DataList){
//			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		    for(int i=0;i<3;i++){
//		    	Map<String,Object> data = new Map<String, Object>();
//		    	data.put(key, value);
//		    	data.put(key, value);
//		    	data.put(key, value);
//		    	friendsTrendList.add(data);
//		    }
//		}
//		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.chat_simple_item,
//				new String[]{"name"},new int[]{R.id.name_chat});
		
		String[] name = new String[]{"立白","李清照","韩愈"};
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i = 0;i<3;i++){
			Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("name", name[i]);
			list.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,list,R.layout.chat_simple_item,
				new String[]{"name"},new int[]{R.id.name_chat});
		friendsTrendsList.setAdapter(simpleAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friends_date, menu);
		return true;
	}

}
