package com.example.rundate;

import java.util.ArrayList;
import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	//��ʼ��locationClient�࣬������������������
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = null;
    public LocationClientOption option = null;
    public Button button = null;
	
    
    //SlideMenu
    SlideMenu sMenu = null;
    ImageView headInfo = null;
    TextView personalInfo = null;
    TextView achievement = null;
    TextView dateRecord = null;
    TextView myRoute = null;
    
	//top1�ı�ǩ
    ImageView head = null;//(ImageView)findViewById(R.id.head);
    TextView personalSign = null;//(TextView)findViewById(R.id.personal_sign);
    ImageView search = null;//(ImageView)findViewById(R.id.search);
    ImageView add = null;//(ImageView)findViewById(R.id.add);
    
    
	//����fragment�л��ͻ����ı���-->top2+ViewPager
    private ViewPager viewPager = null;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private ImageView tabLine;
    private List<Fragment> list;
    private int tabLineLength;//����֮һ��Ļ��
    private int currentPage = 0;//��ǰҳΪ0
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*
		 * ��λϵͳ�Զ���λ
		 * */
		//text = (TextView)findViewById(R.id.text);
		//ʵ����LocationClient(Context);
		mLocationClient = new LocationClient(getApplicationContext());
		//����option
		option  = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//���ö�λģʽ
		option.setCoorType("gcj02");//���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		option.setScanSpan(5000);//���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true);//�����Ƿ���Ҫ��ַ��Ϣ��Ĭ��û�е�ַ
		mLocationClient.setLocOption(option);//��λ����Ϊoption
		myListener = new MylocationListener();
		//ע�����������û�м����������޷�������������
		mLocationClient.registerLocationListener(myListener);
		mLocationClient.start();//��ʼ��λ
		
		//
		 sMenu = (SlideMenu)findViewById(R.id.slide_menu);
		 headInfo = (ImageView)findViewById(R.id.headinfo);
		 personalInfo = (TextView)findViewById(R.id.personal_info);
		 achievement = (TextView)findViewById(R.id.achievement);
		 dateRecord = (TextView)findViewById(R.id.date_record);
		 myRoute = (TextView)findViewById(R.id.my_route);
		 
//		 headInfo.setOnClickListener(new OnClickListener(){
//
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					
//				}
//				 
//			 });
		 personalInfo.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//����һ��intent������Ϣ
					Intent intent = new Intent();
					//ָ��ԭ����class��Ҫ������class
					intent.setClass(MainActivity.this, PersonalInfoActivity.class);
					//��������һ���µ�Activity
					startActivity(intent);
					// �ر�ԭ����Activity,���Ҫ���أ��򲻹ر�
					//MainActivity.this.finish();
				}
				 
			 });
		 achievement.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					//����һ��intent������Ϣ
					Intent intent = new Intent();
					//ָ��ԭ����class��Ҫ������class
					intent.setClass(MainActivity.this, AchieveActivity.class);
					//��������һ���µ�Activity
					startActivity(intent);
					// �ر�ԭ����Activity,���Ҫ���أ��򲻹ر�
					//MainActivity.this.finish();
				}
				 
			 });
		 dateRecord.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					//����һ��intent������Ϣ
					Intent intent = new Intent();
					//ָ��ԭ����class��Ҫ������class
					intent.setClass(MainActivity.this, DateRecordActivity.class);
					//��������һ���µ�Activity
					startActivity(intent);
					// �ر�ԭ����Activity,���Ҫ���أ��򲻹ر�
					//MainActivity.this.finish();
				}
				 
			 });
		 myRoute.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					//����һ��intent������Ϣ
					Intent intent = new Intent();
					//ָ��ԭ����class��Ҫ������class
					intent.setClass(MainActivity.this, MyRouteActivity.class);
					//��������һ���µ�Activity
					startActivity(intent);
					// �ر�ԭ����Activity,���Ҫ���أ��򲻹ر�
					//MainActivity.this.finish();
				}
				 
			 });
		 
		 //top1�ļ���ʵ��
		 head = (ImageView)findViewById(R.id.head);
//		 personalSign = (TextView)findViewById(R.id.personal_sign);
//		 search = (ImageView)findViewById(R.id.search);
		 add = (ImageView)findViewById(R.id.add);
		 //���ͷ��ʵ�ֲ�߻���
		 head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch(v.getId()){
				case R.id.head:
					if(sMenu.isMainScreenShowing()){
						sMenu.openMenu();
					}else{
						sMenu.closeMenu();
					}
				}
				
			}
		});
		 
		 //�������ǩ���༭����ǩ��
//		 personalSign.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//����һ��intent������Ϣ
//				Intent intent = new Intent();
//				//ָ��ԭ����class��Ҫ������class
//				intent.setClass(MainActivity.this, SetPersonalSignActivity.class);
//				//��������һ���µ�Activity
//				startActivity(intent);
//				// �ر�ԭ����Activity,���Ҫ���أ��򲻹ر�
//				//MainActivity.this.finish();
//			}
//			 
//		 });
		 
//		 search.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent();
//				//ָ��ԭ����class��Ҫ������class
//				intent.setClass(MainActivity.this, SearchActivity.class);
//				//��������һ���µ�Activity
//				startActivity(intent);
//				// �ر�ԭ����Activity,���Ҫ���أ��򲻹ر�
//				//MainActivity.this.finish();
//			}
//			 
//		 });
		 
		 //��������Ӻ���
		 add.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				//ָ��ԭ����class��Ҫ������class
				intent.setClass(MainActivity.this, AddFriendsActivity.class);
				//��������һ���µ�Activity
				startActivity(intent);
				// �ر�ԭ����Activity,���Ҫ���أ��򲻹ر�
				//MainActivity.this.finish();
			}
			 
		 });
		 
		 
		 
		/*
		 * �²����ܽ���Ļ����͵���л���ʵ��
		 * */
		//��ʼ��������1/3
		initTabLine();
		//��ʼ������
		initView();
		
	}

	private void initTabLine() {
		// TODO Auto-generated method stub
		//��ȡ��ʾ����Ϣ
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		//�����ʾ�����
		DisplayMetrics  metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		//����֮һ��Ļ���
		tabLineLength = metrics.widthPixels/3;
		//��ȡ�ؼ�ʵ��
		tabLine = (ImageView)findViewById(R.id.tabline);
		LayoutParams lp = tabLine.getLayoutParams();
		lp.width=tabLineLength;
		tabLine.setLayoutParams(lp);
		
	}
	

	private void initView() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager)findViewById(R.id.viewpager);
		tv1 = (TextView)findViewById(R.id.chat);
		tv2 = (TextView)findViewById(R.id.friend);
		tv3 = (TextView)findViewById(R.id.date);
		list = new ArrayList<Fragment>();
		
		ChatFragment chatFrag = new ChatFragment();
		FriendsFragment friendsFrag = new FriendsFragment();
		DateFragment dateFrag = new DateFragment();
		
		list.add(chatFrag);
		list.add(friendsFrag);
		list.add(dateFrag);
		
		//����������
		FragmentPagerAdapter fragAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				return list.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return list.get(arg0);
			}
		};
		
		//��������
		viewPager.setAdapter(fragAdapter);
		//���û�������
	    viewPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams)tabLine.getLayoutParams();
			
				if(currentPage==0&&arg0==0){//0->1�ƶ�����һҳ���ڶ�ҳ
					ll.leftMargin = (int)(currentPage*tabLineLength+arg1*tabLineLength);
				}else if(currentPage==1&&arg0==1){//1->2�ƶ�����2ҳ����3ҳ
					ll.leftMargin = (int)(currentPage*tabLineLength+arg1*tabLineLength);
				}else if(currentPage==1&&arg0==0){//1->0�ƶ�����2ҳ����1ҳ
					ll.leftMargin = (int)(currentPage*tabLineLength-(1-arg1)*tabLineLength);
				}else if(currentPage==2&&arg0==1){//2->1�ƶ�����3ҳ����2ҳ
					ll.leftMargin = (int)(currentPage*tabLineLength-(1-arg1)*tabLineLength);
				}
				
				tabLine.setLayoutParams(ll);
			}

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				tv1.setTextColor(Color.BLACK);
				tv2.setTextColor(Color.BLACK);
				tv3.setTextColor(Color.BLACK);
				switch(arg0){
				case 0:
					tv1.setTextColor(Color.rgb(51, 153, 0));
					break;
				case 1:
					tv2.setTextColor(Color.rgb(51, 153, 0));
					break;
				case 2:
					tv3.setTextColor(Color.rgb(51, 153, 0));
					break;
				}
				currentPage = arg0;
			}
	    	
	    });
	    
	    tv1.setOnClickListener(new MyOnClickListener(0));
	    tv2.setOnClickListener(new MyOnClickListener(1));
	    tv3.setOnClickListener(new MyOnClickListener(2));
	    
	    
	}
	
	public class MyOnClickListener implements OnClickListener{
		int index = 0;
		public MyOnClickListener(int i){

			index = i;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			viewPager.setCurrentItem(index);
		}
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//ʵ��BDLocationListener,��λ
	public class MylocationListener implements BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			if(location == null){
				return;
			}
			StringBuffer sb = new StringBuffer(256);
			sb.append("time:");
			sb.append(location.getTime());
			sb.append("\nerror code:");
			sb.append(location.getLocType());
			sb.append("\nlatitude:");
			sb.append(location.getLatitude());
			sb.append("\nlongitude:");
			sb.append(location.getLongitude());
			//��λ����
			sb.append("\nradius:");
			sb.append(location.getRadius());
			sb.append("\nprovince:");
			sb.append(location.getProvince());
			sb.append("\ncity:");
			sb.append(location.getCity());
			sb.append("\ndistrict");
			sb.append(location.getDistrict());
			
			//�����gps��λ���
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				//�����net��λ���
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\naddr : ");
				//��ȡ���������
				sb.append(location.getAddrStr());
			} 
			logMsg(sb.toString());
		}

	}
	
	public void logMsg(String string) {
		//text.setText(string);
	}

}


