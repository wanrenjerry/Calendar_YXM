package com.example.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.learntest.R;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Grid extends CalendarParent{
	
	private int dayColor;//日期的颜色
	private int todayColor;//今天日期的颜色
	private int todayBackgroundColor;//今天日期的边框颜色
	private int innerGridColor;//日历网格线颜色
	private int otherMonth;
	
	private float daySize;//日期文字的大小尺寸
	private float dayTopOffset;//日期文字距当前网格顶端的偏移量
	private float currentDaySize;//当前日期的文字大小
	
	private boolean flag=true;
	
	private Calendar calendar;//方便有关日期的计算
	
	private int currentYear;//今年
	private int currentMonth;//当月
	private int currentDay;
	private String[] monthNames;
	private int currentDayIndex;
	
	String[] days=new String[42];
	
	private TextView tvMsg1;
	private TextView tvMsg2;
	
	private Button decyear;
	private TextView year;
	private Button addyear;
	private Button decmonth;
	private TextView month;
	private Button addmonth;
	
	
	
	private boolean changed=false;

	public Grid(Activity activity, View view) {
		super(activity, view);
		// TODO Auto-generated constructor stub
		tvMsg1=(TextView)activity.findViewById(R.id.tvMsg1);
		tvMsg2=(TextView)activity.findViewById(R.id.tvMsg2);
		
		decyear=(Button)activity.findViewById(R.id.decyear);
		decyear.setOnClickListener(new Decyear());
		year=(TextView)activity.findViewById(R.id.year);
		addyear=(Button)activity.findViewById(R.id.addyear);
		addyear.setOnClickListener(new Addyear());
		
		decmonth=(Button)activity.findViewById(R.id.decmonth);
		decmonth.setOnClickListener(new Decmonth());
		month=(TextView)activity.findViewById(R.id.month);
		addmonth=(Button)activity.findViewById(R.id.addmonth);
		addmonth.setOnClickListener(new Addmonth());
		
		
		
		dayColor=activity.getResources().getColor(R.color.day_color);
		todayColor=activity.getResources().getColor(R.color.today_color);
		todayBackgroundColor=activity.getResources().getColor(R.color.todayBackgroundColor);
		innerGridColor=activity.getResources().getColor(R.color.innerGridColor);
		otherMonth=activity.getResources().getColor(R.color.othermonth);
		
		daySize=activity.getResources().getDimension(R.dimen.day_size);
		dayTopOffset=activity.getResources().getDimension(R.dimen.day_top_offset);
		currentDaySize=activity.getResources().getDimension(R.dimen.current_day_size);
		
		calendar=Calendar.getInstance();
		currentYear=calendar.get(calendar.YEAR);
		currentMonth=calendar.get(calendar.MONTH);
		currentDay=calendar.get(calendar.DAY_OF_MONTH);
		
		monthNames=activity.getResources().getStringArray(R.array.month_name);
		
	}
	public void draw(Canvas canvas){
		float left=borderMargin_left;
		float top=borderMargin_top+weekNameSize+2*weekNameMargin;
		float calendarWidth=view.getMeasuredWidth()-left*2-3;
		float calendarHeight=view.getMeasuredHeight()-top-12;
		float cellWidth=calendarWidth/7;
		float cellHeight=calendarHeight/6;
		
		paint.setColor(innerGridColor);
		canvas.drawLine(left, top, left+view.getMeasuredWidth()-borderMargin_left*2, top, paint);
		for(int i=1;i<6;i++){
			canvas.drawLine(left, top+cellHeight*i,
					left+view.getMeasuredWidth()-borderMargin_left*2, top+cellHeight*i, paint);
		}
		for(int i=1;i<7;i++){
			canvas.drawLine(left+cellWidth*i, top,
					left+cellWidth*i, view.getMeasuredHeight()-12, paint);
		}
		
		calculateDays();
		Calendar cal_temp=Calendar.getInstance();
		if(flag==true){
			System.out.println("进来了!");
		      cal_temp.set(currentYear, currentMonth,currentDay);
		      flag=false;
		}
		int day=cal_temp.get(calendar.DAY_OF_MONTH);
		int myYear=cal_temp.get(calendar.YEAR);
		int myMonth=cal_temp.get(calendar.MONTH);
		cal_temp.set(myYear, myMonth,1);
		int week=cal_temp.get(calendar.DAY_OF_WEEK);
		int todayIndex=week+day-2;//记录好几天在数组days中的位
		boolean today=false;
		if(currentDayIndex==-1){
			currentDayIndex=todayIndex;
		}
		for(int i=0;i<days.length;i++){
			today=false;
			int row=i/7;//计算当前行
			int col=i%7;//计算当前列
			String text=days[i];
			//配置颜色的选择
			if(text.startsWith("*")){
				paint.setColor(otherMonth);
			}
			else if(i%7==0||(i-6)%7==0){
				paint.setColor(sundaySaturdayColor);
			}
			else
			{
				paint.setColor(dayColor);
			}
			
			text=text.startsWith("*")?text.substring(1):text;//去掉星号操作
			Rect dst=new Rect();
			dst.left=(int)(left+cellWidth*col);
			dst.top=(int)(top+cellHeight*row);
			dst.bottom=(int)(dst.top+cellHeight);
			if(col==6){
				dst.right=(int)(dst.left+cellWidth)+3;
			}
			else
			    dst.right=(int)(dst.left+cellWidth);
			String mytext=text;
			
			paint.setTextSize(daySize);
			float textLeft=left+cellWidth*col+(cellWidth-paint.measureText(text))/2;
			float textTop=top+cellHeight*row+(cellHeight-paint.getTextSize())/2+dayTopOffset;
			
			if(myYear==currentYear&&myMonth==currentMonth&&i==todayIndex){//如果是今天
				paint.setTextSize(currentDaySize);
				
				paint.setColor(todayBackgroundColor);//画边框的颜色
				//dst.left+=1;
				//dst.top+=1;
				canvas.drawLine(dst.left, dst.top, dst.right, dst.top, paint);
				canvas.drawLine(dst.right, dst.top, dst.right, dst.bottom, paint);
				canvas.drawLine(dst.right, dst.bottom, dst.left, dst.bottom, paint);
				canvas.drawLine(dst.left, dst.bottom, dst.left, dst.top, paint);
				
				paint.setColor(todayColor);//设置画文本的颜色
				today=true;
				updateMsg(today);
			}
			
			canvas.drawText(mytext, textLeft, textTop, paint);
			
		}
	}
	
	private void calculateDays(){
		Calendar cal_Cal=Calendar.getInstance();
		cal_Cal.set(currentYear,currentMonth,1);
		//获得当前月的第一天是所在周的第几天
		int week=cal_Cal.get(calendar.DAY_OF_WEEK);
		int monthDays=0;
		int preMonthDays=0;
		
		monthDays=getMonthDays(currentYear,currentMonth);
		if(currentMonth==0){//如果是一月份，特别注意一月对应0
			preMonthDays=31;
		}
		else
		{
			preMonthDays=getMonthDays(currentYear,currentMonth-1);
		}
		for(int i=week,day=preMonthDays;i>1;i--,day--){//先填上个月的日期
			days[i-2]="*"+String.valueOf(day);
		}
		for(int i=week-1,day=1;day<=monthDays;i++,day++){
			days[i]=String.valueOf(day);
			if(day==currentDay){
				currentDayIndex=i;
			}
		}
		for(int i=week+monthDays-1,day=1;i<days.length;i++,day++){
			days[i]="*"+String.valueOf(day);
		}
	}
	private int getMonthDays(int year,int month){
		month++;
		switch(month){
		    case 1:
		    case 3:
		    case 5:
		    case 7:
		    case 8:
		    case 10:
		    case 12:
		    {
			    return 31;
		    }
		    case 4:
		    case 6:
		    case 9:
		    case 11:
		    {
		    	return 30;	
		    }
		    case 2:
		    {
		    	if(((year%4==0)&&(year%100!=0))||(year%400==0)){
		    		return 29;
		    	}
		    	else
		    		return 28;
		    }
			
		
		}
		return 0;
	}
	
	private void updateMsg(boolean today){
		String monthName=monthNames[currentMonth];
		String dataString="";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy 年 M 月 d 日");
		Calendar cal=Calendar.getInstance();
		cal.set(currentYear, currentMonth, currentDay);
		dataString=sdf.format(cal.getTime());
		
		String lunarStr="";
		monthName+="   本月第"+cal.get(cal.WEEK_OF_MONTH)+"周";
		tvMsg1.setText(monthName);
		
		dataString+="   本年第"+cal.get(cal.WEEK_OF_YEAR)+"周";
		tvMsg2.setText(dataString);
	}
	public void chonghui(){
		view.invalidate();
	}
    
	//增加年时的相应事件
	class Addyear implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			currentYear++;
			String syear=currentYear+"年";
			String smonth=(currentMonth+1)+"月";
			year.setText(syear);
			month.setText(smonth);
			chonghui();
			
		}
	}
	
	//减少年时的相应事件
	class Decyear implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			currentYear--;
			String syear=currentYear+"年";
			String smonth=(currentMonth+1)+"月";
			year.setText(syear);
			month.setText(smonth);
			chonghui();
			
		}
		
	}
	
	//增加月时的相应事件
	class Addmonth implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(currentMonth<11){
				currentMonth++;
			}
			else
			{
				currentMonth=0;
				currentYear++;
			}
			String syear=currentYear+"年";
			String smonth=(currentMonth+1)+"月";
			year.setText(syear);
			month.setText(smonth);
			chonghui();
			
		}	
	}
	
	//减少月时的相应事件
	class Decmonth implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(currentMonth==0){
				currentMonth=11;
				currentYear--;
			}
			else
			{
				currentMonth--;
			}
			String syear=currentYear+"年";
			String smonth=(currentMonth+1)+"月";
			year.setText(syear);
			month.setText(smonth);
			chonghui();
			
		}	
	}
	public void backToday(){
		currentYear=calendar.get(calendar.YEAR);
		currentMonth=calendar.get(calendar.MONTH);
		currentDay=calendar.get(calendar.DAY_OF_MONTH);
		year.setText(""+currentYear);
		month.setText(""+(currentMonth+1));
		view.invalidate();
		
	}

	//获取当前年月份
	public int getCurrentYear(){
		return currentYear;
	}
	public int getCurrentMonth(){
		return currentMonth;
	}
	public int getCurrentDay(){
		return currentDay;
	}
	
	//设置当前年月份
	public void setCurrentYear(int int_year){
		currentYear=int_year;
	}
	public void setCurrentMonth(int int_month){
		currentMonth=int_month;
	}
	public void setCurrentDay(int int_day){
		currentDay=int_day;
	}
	//设置红线标记位
	public void setflag(boolean ff){
		flag=ff;
	}
}
