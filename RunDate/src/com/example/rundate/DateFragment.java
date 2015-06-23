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
				//创建一个intent传递消息
				Intent intent = new Intent();
				//指定原本的class和要启动的class
				intent.setClass(getActivity(),FriendsDateActivity.class);
				//调用另外一个新的Activity
				startActivity(intent);
				// 关闭原本的Activity,如果要返回，则不关闭
				//MainActivity.this.finish();
			}
			
		});
		nearbyInfo.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//创建一个intent传递消息
				Intent intent = new Intent();
				//指定原本的class和要启动的class
				intent.setClass(getActivity(), NearByDateActivity.class);
				//调用另外一个新的Activity
				startActivity(intent);
				// 关闭原本的Activity,如果要返回，则不关闭
				//MainActivity.this.finish();
			}
			
		});
		publish.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//创建一个intent传递消息
				Intent intent = new Intent();
				//指定原本的class和要启动的class
				intent.setClass(getActivity(), PublishAvtivity.class);
				//调用另外一个新的Activity
				startActivity(intent);
				// 关闭原本的Activity,如果要返回，则不关闭
				//MainActivity.this.finish();
			}
			
		});
		return view;
		
	}

}
