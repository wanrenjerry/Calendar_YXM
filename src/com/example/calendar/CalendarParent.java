package com.example.calendar;

import com.example.learntest.R;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class CalendarParent implements CalendarElement{
	protected Activity activity;//接收参数用的
	protected View view;
	protected Paint paint=new Paint();
	protected float borderMargin_top;//框架的各种尺寸
	protected float weekNameMargin;//显示第几周的框架尺寸
	protected float borderMargin_left;//显示第几周的框架尺寸
	protected float weekNameSize;//字体大小
	protected int sundaySaturdayColor;//特殊周次颜色
	
	public CalendarParent(Activity activity,View view){
		this.activity=activity;
		this.view=view;
		
		//从资源文件中获得一些公共数据
		borderMargin_top=activity.getResources().getDimension(R.dimen.calendar_border_margin_top);
		borderMargin_left=activity.getResources().getDimension(R.dimen.calendar_border_margin_left);
		weekNameMargin=activity.getResources().getDimension(R.dimen.weekNameMargin);
		weekNameSize=activity.getResources().getDimension(R.dimen.weekname_size);
		sundaySaturdayColor=activity.getResources().getColor(R.color.sunday_saturday_color);
	}
	

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}

}
