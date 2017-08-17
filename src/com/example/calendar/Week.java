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
		
		//��ȡ�����Ƶ���ɫ
		weekNameColor=activity.getResources().getColor(R.color.weekname_color);
		weekNames=activity.getResources().getStringArray(R.array.week_name);
		
		//���������Ƶ������С
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
				//�������������յ�������ɫ�������ط�Ҫ�õ�
				//����SundaySaturdaycolor��Calendarparent�еõ�
				paint.setColor(sundaySaturdayColor);//
			}
			else{
				paint.setColor(weekNameColor);//
			}
			//��ȡ�ƶ��ķ�ʽ����
			left=borderMargin_left+everyWeekWidth*i+(everyWeekWidth-paint.measureText(weekNames[i]))/2;
			//һ��������˼�д�֣��ָ��ֶ�λ�������ϵĴ�С�Ѿ������ˣ����п���ֻ��һ�������ȷ���ֵ�λ��
			canvas.drawText(weekNames[i], left, top+paint.getTextSize()+weekNameMargin, paint);
			
		}
	}

}
