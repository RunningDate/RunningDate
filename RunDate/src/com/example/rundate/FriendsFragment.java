package com.example.rundate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class FriendsFragment extends Fragment{
	TextView text = null;
	
public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceStat){
	    View view = inflater.inflate(R.layout.friends_fragment, container, false);
		text = (TextView)view.findViewById(R.id.friendtext);
		text.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				text.setText("好友被电击了");
			}
			
		});
		return view;
		
	}

	
}
