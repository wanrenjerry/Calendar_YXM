package com.example.calendar;

import com.example.learntest.R;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class Week extends CalendarParent{
	private String[] weekNames;
	private int weekNameColor;

	public Week(Activity activity, View view) {
		super(activity, view);
		// TODO Auto-generated constructor stub
		
		//获取周名称的颜色
		weekNameColor=activity.getResources().getColor(R.color.weekname_color);
		weekNames=activity.getResources().getStringArray(R.array.week_name);
		
		//设置周名称的字体大小
		paint.setTextSize(weekNameSize);
	}
	public void draw(Canvas canvas){
		float left=borderMargin_left;
		float top=borderMargin_top;
		float everyWeekWidth=(view.getMeasuredWidth()-borderMargin_left*2)/7;
		float everyWeekHeight=everyWeekWidth;
		//paint.setFakeBoldText(true);
		for(int i=0;i<weekNames.length;i++){
			if(i==0||i==weekNames.length-1){
				//由于周六，周日的文字颜色在其他地方要用到
				//所以SundaySaturdaycolor在Calendarparent中得到
				paint.setColor(sundaySaturdayColor);//
			}
			else{
				paint.setColor(weekNameColor);//
			}
			//采取移动的方式划线
			left=borderMargin_left+everyWeekWidth*i+(everyWeekWidth-paint.measureText(weekNames[i]))/2;
			//一次性完成了既写字，又给字定位，由于紫的大小已经定义了，所有可以只给一个点就能确定字的位置
			canvas.drawText(weekNames[i], left, top+paint.getTextSize()+weekNameMargin, paint);
			
		}
	}

}
