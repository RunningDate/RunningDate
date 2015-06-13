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
	//初始化locationClient类，必须在主进程中声明
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
    
	//top1的标签
    ImageView head = null;//(ImageView)findViewById(R.id.head);
    TextView personalSign = null;//(TextView)findViewById(R.id.personal_sign);
    ImageView search = null;//(ImageView)findViewById(R.id.search);
    ImageView add = null;//(ImageView)findViewById(R.id.add);
    
    
	//设置fragment切换和滑动的变量-->top2+ViewPager
    private ViewPager viewPager = null;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private ImageView tabLine;
    private List<Fragment> list;
    private int tabLineLength;//三分之一屏幕宽
    private int currentPage = 0;//当前页为0
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*
		 * 定位系统自动定位
		 * */
		//text = (TextView)findViewById(R.id.text);
		//实例化LocationClient(Context);
		mLocationClient = new LocationClient(getApplicationContext());
		//设置option
		option  = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
		option.setCoorType("gcj02");//返回的定位结果是百度经纬度，默认值gcj02
		option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);//设置是否需要地址信息，默认没有地址
		mLocationClient.setLocOption(option);//定位设置为option
		myListener = new MylocationListener();
		//注册监听函数，没有监听函数，无法发起网络请求
		mLocationClient.registerLocationListener(myListener);
		mLocationClient.start();//开始定位
		
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
					//创建一个intent传递消息
					Intent intent = new Intent();
					//指定原本的class和要启动的class
					intent.setClass(MainActivity.this, PersonalInfoActivity.class);
					//调用另外一个新的Activity
					startActivity(intent);
					// 关闭原本的Activity,如果要返回，则不关闭
					//MainActivity.this.finish();
				}
				 
			 });
		 achievement.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					//创建一个intent传递消息
					Intent intent = new Intent();
					//指定原本的class和要启动的class
					intent.setClass(MainActivity.this, AchieveActivity.class);
					//调用另外一个新的Activity
					startActivity(intent);
					// 关闭原本的Activity,如果要返回，则不关闭
					//MainActivity.this.finish();
				}
				 
			 });
		 dateRecord.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					//创建一个intent传递消息
					Intent intent = new Intent();
					//指定原本的class和要启动的class
					intent.setClass(MainActivity.this, DateRecordActivity.class);
					//调用另外一个新的Activity
					startActivity(intent);
					// 关闭原本的Activity,如果要返回，则不关闭
					//MainActivity.this.finish();
				}
				 
			 });
		 myRoute.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// TODO Auto-generated method stub
					//创建一个intent传递消息
					Intent intent = new Intent();
					//指定原本的class和要启动的class
					intent.setClass(MainActivity.this, MyRouteActivity.class);
					//调用另外一个新的Activity
					startActivity(intent);
					// 关闭原本的Activity,如果要返回，则不关闭
					//MainActivity.this.finish();
				}
				 
			 });
		 
		 //top1的监听实现
		 head = (ImageView)findViewById(R.id.head);
//		 personalSign = (TextView)findViewById(R.id.personal_sign);
//		 search = (ImageView)findViewById(R.id.search);
		 add = (ImageView)findViewById(R.id.add);
		 //点击头像实现侧边滑动
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
		 
		 //点击个性签名编辑个性签名
//		 personalSign.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//创建一个intent传递消息
//				Intent intent = new Intent();
//				//指定原本的class和要启动的class
//				intent.setClass(MainActivity.this, SetPersonalSignActivity.class);
//				//调用另外一个新的Activity
//				startActivity(intent);
//				// 关闭原本的Activity,如果要返回，则不关闭
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
//				//指定原本的class和要启动的class
//				intent.setClass(MainActivity.this, SearchActivity.class);
//				//调用另外一个新的Activity
//				startActivity(intent);
//				// 关闭原本的Activity,如果要返回，则不关闭
//				//MainActivity.this.finish();
//			}
//			 
//		 });
		 
		 //点击添加添加好友
		 add.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				//指定原本的class和要启动的class
				intent.setClass(MainActivity.this, AddFriendsActivity.class);
				//调用另外一个新的Activity
				startActivity(intent);
				// 关闭原本的Activity,如果要返回，则不关闭
				//MainActivity.this.finish();
			}
			 
		 });
		 
		 
		 
		/*
		 * 下部功能界面的滑动和点击切换的实现
		 * */
		//初始化滑动条1/3
		initTabLine();
		//初始化界面
		initView();
		
	}

	private void initTabLine() {
		// TODO Auto-generated method stub
		//获取显示屏信息
		Display display = getWindow().getWindowManager().getDefaultDisplay();
		//获得显示屏宽度
		DisplayMetrics  metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		//三分之一屏幕宽度
		tabLineLength = metrics.widthPixels/3;
		//获取控件实例
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
		
		//设置适配器
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
		
		//绑定适配器
		viewPager.setAdapter(fragAdapter);
		//设置滑动监听
	    viewPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams)tabLine.getLayoutParams();
			
				if(currentPage==0&&arg0==0){//0->1移动，第一页到第二页
					ll.leftMargin = (int)(currentPage*tabLineLength+arg1*tabLineLength);
				}else if(currentPage==1&&arg0==1){//1->2移动，第2页到第3页
					ll.leftMargin = (int)(currentPage*tabLineLength+arg1*tabLineLength);
				}else if(currentPage==1&&arg0==0){//1->0移动，第2页到第1页
					ll.leftMargin = (int)(currentPage*tabLineLength-(1-arg1)*tabLineLength);
				}else if(currentPage==2&&arg0==1){//2->1移动，第3页到第2页
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
	
	//实现BDLocationListener,定位
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
			//定位精度
			sb.append("\nradius:");
			sb.append(location.getRadius());
			sb.append("\nprovince:");
			sb.append(location.getProvince());
			sb.append("\ncity:");
			sb.append(location.getCity());
			sb.append("\ndistrict");
			sb.append(location.getDistrict());
			
			//如果是gps定位结果
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				//如果是net定位结果
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\naddr : ");
				//获取反地理编码
				sb.append(location.getAddrStr());
			} 
			logMsg(sb.toString());
		}

	}
	
	public void logMsg(String string) {
		//text.setText(string);
	}

}


