package com.example.rundate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class DateFragment extends Fragment{
	
public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceStat){
		View view =inflater.inflate(R.layout.date_fragment, container, false);
		Button friendsInfo = (Button)view.findViewById(R.id.friends_info);
		Button nearbyInfo = (Button)view.findViewById(R.id.nearby_info);
		Button publish = (Button)view.findViewById(R.id.publish);
		
		friendsInfo.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//����һ��intent������Ϣ
				Intent intent = new Intent();
				//ָ��ԭ����class��Ҫ������class
				intent.setClass(getActivity(),FriendsDateActivity.class);
				//��������һ���µ�Activity
				startActivity(intent);
				// �ر�ԭ����Activity,���Ҫ���أ��򲻹ر�
				//MainActivity.this.finish();
			}
			
		});
		nearbyInfo.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//����һ��intent������Ϣ
				Intent intent = new Intent();
				//ָ��ԭ����class��Ҫ������class
				intent.setClass(getActivity(), NearByDateActivity.class);
				//��������һ���µ�Activity
				startActivity(intent);
				// �ر�ԭ����Activity,���Ҫ���أ��򲻹ر�
				//MainActivity.this.finish();
			}
			
		});
		publish.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//����һ��intent������Ϣ
				Intent intent = new Intent();
				//ָ��ԭ����class��Ҫ������class
				intent.setClass(getActivity(), PublishAvtivity.class);
				//��������һ���µ�Activity
				startActivity(intent);
				// �ر�ԭ����Activity,���Ҫ���أ��򲻹ر�
				//MainActivity.this.finish();
			}
			
		});
		return view;
		
	}

}
