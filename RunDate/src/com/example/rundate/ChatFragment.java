package com.example.rundate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ChatFragment extends Fragment{
	
	private ListView listItems;
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceStat){
		View view = inflater.inflate(R.layout.chat_fragment, container,false);

		ListView listView = (ListView)view.findViewById(R.id.chat_list);
		
//		List<String> data = new ArrayList<String>();
//		
//        for (int i = 0; i < 30; i++) {
//
//            data.add("smyh" + i);
//
//       }
//
//        //������ӵ�ArrayAdapter����
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
//
//                R.layout.aaa, data);
//
//        //��������ʱ������ͨ��ListFragment.setListAdapter()�ӿڣ�������ListView.setAdapter()����������
//
//       listView.setAdapter(adapter);
		
		//������adapter��list.setAdapter
		//��Щ�����Դӷ�������ã�Ȼ��ѭ������adapter
		String[] name = new String[]{"����","������","����"};
//		int[] imageIds = new int[]{R.drawable.actionbar_add_icon,R.drawable.actionbar_more_icon,R.drawable.actionbar_search_icon};
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i = 0;i<3;i++){
			Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("name", name[i]);
			list.add(listItem);
		}
//		SimpleAdapter simpleAdapter = new SimpleAdapter(this.getActivity(),list,R.layout.chat_simple_item,
//				new String[]{"name","headImage"},new int[]{R.id.head_image_chat,R.id.name_chat});
		SimpleAdapter simpleAdapter = new SimpleAdapter(this.getActivity(),list,R.layout.chat_simple_item,
				new String[]{"name"},new int[]{R.id.name_chat});
		listView.setAdapter(simpleAdapter);
		
		return view;
		
	}
	
	public void OnCreatre(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
//		String[] name = new String[]{"����","������","����"};
//		int[] imageIds = new int[]{R.drawable.actionbar_add_icon,R.drawable.actionbar_more_icon,R.drawable.actionbar_search_icon};
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		for(int i = 0;i<3;i++){
//			Map<String,Object> listItem = new HashMap<String,Object>();
//			listItem.put("name", name[i]);
//			listItem.put("headImage", imageIds[i]);
//			list.add(listItem);
//		}
//		SimpleAdapter simpleAdapter = new SimpleAdapter(this.getActivity(),list,
//				R.layout.chat_simple_item,
//				new String[]{"name","headImage"},
//				new int[]{R.id.name_chat,R.id.head_image_chat});
//		setListAdapter(simpleAdapter);
		
		
	}

}
