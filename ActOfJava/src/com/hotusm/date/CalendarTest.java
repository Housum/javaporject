package com.hotusm.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CalendarTest {
	
	public static void main(String[] args) {
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH);
		int day=c.get(Calendar.DAY_OF_MONTH);
		int hour=c.get(Calendar.HOUR_OF_DAY);
		
		Scanner scanner=new Scanner(System.in);
		int targetHour = Integer.valueOf(scanner.next());
		Calendar cc=null;
		if(targetHour>=hour){
			cc=new GregorianCalendar(year, month, day, targetHour, 59);
		}else{
			cc=new GregorianCalendar(year, month, day+1, targetHour, 59);
		}
		
		System.out.println(cc.getTime());
		//System.out.println("year :"+year +" month :"+ month+" day :"+day+" hour :"+hour);
		
		
	}
}
