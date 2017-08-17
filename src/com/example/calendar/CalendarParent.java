package com.example.calendar;

import com.example.learntest.R;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class CalendarParent implements CalendarElement{
	protected Activity activity;//���ղ����õ�
	protected View view;
	protected Paint paint=new Paint();
	protected float borderMargin_top;//��ܵĸ��ֳߴ�
	protected float weekNameMargin;//��ʾ�ڼ��ܵĿ�ܳߴ�
	protected float borderMargin_left;//��ʾ�ڼ��ܵĿ�ܳߴ�
	protected float weekNameSize;//�����С
	protected int sundaySaturdayColor;//�����ܴ���ɫ
	
	public CalendarParent(Activity activity,View view){
		this.activity=activity;
		this.view=view;
		
		//����Դ�ļ��л��һЩ��������
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
