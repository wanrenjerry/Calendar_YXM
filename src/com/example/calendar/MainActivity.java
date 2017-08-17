package com.example.calendar;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.learntest.R;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	private CalendarView calendarView;
	private RelativeLayout mainLayout;
	
	private Button xuemei_menu;
	private AlertDialog.Builder builder;
	//private MyView myView;
	
	//菜单第二项中的变量
	private LinearLayout myDateLayout;
	private DatePicker dpSelectDate;
	private TextView tvDate;
	private TextView tvLunarDate;
	private AlertDialog adMyDate;
	
	private LinearLayout myAbout;
	private AlertDialog about;
	private TextView about_tx1;
	private TextView about_tx2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayout=(RelativeLayout)getLayoutInflater().inflate(R.layout.main,null);
     
        setContentView(mainLayout);
        
        calendarView=new CalendarView(this);
        //myView=new MyView(this);
        mainLayout.addView(calendarView);
        
        xuemei_menu=(Button)findViewById(R.id.menu_xuemei);
        
    }  


   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.main, popup.getMenu());
        popup.setOnMenuItemClickListener(new Menulistener(calendarView));
        popup.show();
    }

    class Menulistener implements OnMenuItemClickListener{
    	private CalendarView cal_view;
    	Menulistener(CalendarView cal_view){
    		this.cal_view=cal_view;
    	}

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			// TODO Auto-generated method stub
			switch (item.getItemId()) {
	              case R.id.today_choose:
	            	  Calendar cal_today=Calendar.getInstance();
	            	  calendarView.ce.grid.setCurrentYear(cal_today.get(Calendar.YEAR));
	      			  calendarView.ce.grid.setCurrentMonth(cal_today.get(Calendar.MONTH));
	      			  calendarView.ce.grid.setCurrentDay(cal_today.get(Calendar.DAY_OF_MONTH));
	      			calendarView.ce.grid.setflag(true);
	      			  calendarView.invalidate();
	            	 // calendarView.ce.grid.backToday();
	                  return true;
	              case R.id.specific_day:
	            	  specificDay();
	            	  return true;
	              case R.id.notes_remind:
	            	  return true;
	              case R.id.about:
	            	  about();
	            	  return true;
			      default:
			    	  return false;
			}
		}
    	
    }
    
    private void specificDay(){
    	builder=new AlertDialog.Builder(this);
    	builder.setTitle("指定日期");
    	myDateLayout=(LinearLayout)getLayoutInflater().inflate(R.layout.mydate, null);
    	dpSelectDate=(DatePicker)myDateLayout.findViewById(R.id.dpSelectDate);
    	tvDate=(TextView)myDateLayout.findViewById(R.id.tvDate);
    	tvLunarDate=(TextView)myDateLayout.findViewById(R.id.tvLunarDate);
    	
    	dpSelectDate.init(calendarView.ce.grid.getCurrentYear(),
    			calendarView.ce.grid.getCurrentMonth(), 
    			calendarView.ce.grid.getCurrentDay(),
    			new SpecificDaylistener());
    	
    	builder.setView(myDateLayout);
    	builder.setPositiveButton("确定", new SPbuttonSurelistener());
    	builder.setNegativeButton("取消", null);
    	adMyDate = builder.create();
    	adMyDate.show();
    }
    class SpecificDaylistener implements OnDateChangedListener{

		@Override
		public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy 年 M 月 d 日");
			Calendar cal_SP=Calendar.getInstance();
			cal_SP.set(year, monthOfYear,dayOfMonth);
			
			if(tvDate!=null){
				tvDate.setText(sdf.format(cal_SP.getTime()));
			}
			else{
				adMyDate.setTitle(sdf.format(cal_SP.getTime()));
			}
			
			//如果是今天，添加一个今天
			Calendar cal_SP2=Calendar.getInstance();
			if(cal_SP2.get(Calendar.YEAR)==year&&
					cal_SP2.get(Calendar.MONTH)==monthOfYear&&
					cal_SP2.get(Calendar.DATE)==dayOfMonth
					){
				if(tvDate!=null)
					tvDate.setText(tvDate.getText()+"(今天)");
				else
					adMyDate.setTitle(sdf.format(cal_SP.getTime())+"(今天)");
			}
			
			
		}
    	
    }
    
    class SPbuttonSurelistener implements android.content.DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			calendarView.ce.grid.setCurrentYear(dpSelectDate.getYear());
			calendarView.ce.grid.setCurrentMonth(dpSelectDate.getMonth());
			calendarView.ce.grid.setCurrentDay(dpSelectDate.getDayOfMonth());
			calendarView.ce.grid.setflag(true);
			calendarView.invalidate();
		}
    	
    }
    
    private void about(){
    	builder=new AlertDialog.Builder(this);
    	builder.setTitle("关于");
    	myAbout=(LinearLayout)getLayoutInflater().inflate(R.layout.about, null);
    	about_tx1=(TextView)myAbout.findViewById(R.id.about);
    	about_tx2=(TextView)myAbout.findViewById(R.id.about_photo);
    
    	builder.setView(myAbout);
    	builder.setPositiveButton("确定", null);
    	builder.setNegativeButton("取消", null);
    	about = builder.create();
    	about.show();
    }
    
}
