package com.util;

import java.util.Calendar;

/**
 * 取得当前日期相对应的月初，月末，季初，季末，年初，年末
 * @author user
 */
public class TimeUtil {
	 	private int x;                  // 日期属性：年   
	    private int y;                  // 日期属性：月   
	    private int z;                  // 日期属性：日   
	    private Calendar localTime;     // 当前日期   
	       
	    public TimeUtil() {   
	        localTime = Calendar.getInstance();   
	    }   
	       
	    public static void main(String[] args){   
	    	TimeUtil time = new TimeUtil();   
	    	System.out.println("当前时间："+time.today());  
	    	System.out.println("当前月月首："+time.thisMonth());  
	    	System.out.println("当前月月末："+time.thisMonthEnd());  
	    	System.out.println("当前季初："+time.thisSeason());  
	    	System.out.println("当前季末："+time.thisSeasonEnd());  
	    	System.out.println("当前年初："+time.thisYear());  
	    	System.out.println("当前年末："+time.thisYearEnd());  
	    	System.out.println("是否闰年："+time.leapYear(2000));  
	    }   
	      
	    public String today() {   
	        String strY = null;   
	        String strZ = null;   
	        x = localTime.get(Calendar.YEAR);   
	        y = localTime.get(Calendar.MONTH) + 1;   
	        z = localTime.get(Calendar.DATE);   
	        strY = y >= 10 ? String.valueOf(y) : ("0" + y);   
	        strZ = z >= 10 ? String.valueOf(z) : ("0" + z);   
	        return x + "-" + strY + "-" + strZ;   
	    }   
	      
	    public String thisMonth() {   
	        String strY = null;   
	        x = localTime.get(Calendar.YEAR);   
	        y = localTime.get(Calendar.MONTH) + 1;   
	        strY = y >= 10 ? String.valueOf(y) : ("0" + y);return x + "-" + strY + "-01";   
	    }   
	      
	    public String thisMonthEnd() {   
	        String strY = null;   
	        String strZ = null;   
	        boolean leap = false;   
	        x = localTime.get(Calendar.YEAR);   
	        y = localTime.get(Calendar.MONTH) + 1;   
	        if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {   
	            strZ = "31";   
	        }   
	        if (y == 4 || y == 6 || y == 9 || y == 11) {   
	            strZ = "30";   
	        }   
	        if (y == 2) {   
	            leap = leapYear(x);   
	            if (leap) {   
	                strZ = "29";   
	            }else {   
	                strZ = "28";   
	            }   
	        }   
	        strY = y >= 10 ? String.valueOf(y) : ("0" + y);   
	        return x + "-" + strY + "-" + strZ;   
	    }   
	  
	      
	    public String thisSeason() {   
	        String dateString = "";   
	        x = localTime.get(Calendar.YEAR);   
	        y = localTime.get(Calendar.MONTH) + 1;   
	        if (y >= 1 && y <= 3) {   
	            dateString = x + "-" + "01" + "-" + "01";   
	        }   
	        if (y >= 4 && y <= 6) {   
	            dateString = x + "-" + "04" + "-" + "01";   
	        }   
	        if (y >= 7 && y <= 9) {   
	            dateString = x + "-" + "07" + "-" + "01";   
	        }   
	        if (y >= 10 && y <= 12) {   
	            dateString = x + "-" + "10" + "-" + "01";   
	        }   
	        return dateString;   
	        }   
	  
	      
	    public String thisSeasonEnd() {   
	        String dateString = "";   
	        x = localTime.get(Calendar.YEAR);   
	        y = localTime.get(Calendar.MONTH) + 1;   
	        if (y >= 1 && y <= 3) {   
	            dateString = x + "-" + "03" + "-" + "31";   
	        }   
	        if (y >= 4 && y <= 6) {   
	            dateString = x + "-" + "06" + "-" + "30";   
	        }   
	        if (y >= 7 && y <= 9) {   
	            dateString = x + "-" + "09" + "-" + "30";   
	        }   
	        if (y >= 10 && y <= 12) {   
	            dateString = x + "-" + "12" + "-" + "31";   
	        }   
	        return dateString;   
	    }   
	  
	      
	    public String thisYear() {   
	        x = localTime.get(Calendar.YEAR);   
	        return x + "-01" + "-01";   
	    }   
	       
	      
	    public String thisYearEnd() {   
	        x = localTime.get(Calendar.YEAR);   
	        return x + "-12" + "-31";   
	    }   
	       
	      
	    public boolean leapYear(int year) {   
	        boolean leap;   
	        if (year % 4 == 0) {   
	            if (year % 100 == 0) {   
	                if (year % 400 == 0) leap = true;   
	                    else leap = false;   
	                }   
	            else leap = true;   
	        }   
	        else leap = false;   
	        return leap;   
	    }   
}
