package com.learning.java8plus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UTCTimeZone {
public static void main(String[] args) {
		
		Map<String, String> timeZoneMap= new LinkedHashMap<>();
		
		TimezoneDisplay display = new TimezoneDisplay();
		 
        //System.out.println("Time zones in UTC:");
        List<String> utc = display.getTimeZoneList(
          TimezoneDisplay.OffsetBase.UTC);
        //see all the list of UTC timezone
      //  utc.forEach(System.out::println);
        
        //create a map of timezoneId as key and offset as value
        
     /*   utc.forEach(timeZone -> {
        	String parts[]=(timeZone.split(" ",2));
        	String timeZoneId = parts[1];
    		String offset = parts[0];;
    		timeZoneMap.put(timeZoneId, offset);
        });
        
     
        
        String timeZoneId = "Asia/Kolkata";
        if(timeZoneMap.containsKey(timeZoneId)){
        	String offset = timeZoneMap.get(timeZoneId);
        	System.out.println("timeZoneId => " + timeZoneId + " offset => " +offset);
        	System.out.println("Full TimeZone String ==> " + offset + " " + timeZoneId);
        }
        **/

	} 

}
