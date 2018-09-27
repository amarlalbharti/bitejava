package com.bharti.constraints;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormats 
{
	public static SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	public static SimpleDateFormat ddMMMyyyy = new SimpleDateFormat("dd MMM yyyy");
	public static SimpleDateFormat ddMMyyyyathhmm = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
	public static SimpleDateFormat ddMMMyyyyathhmm = new SimpleDateFormat("dd MMM yyyy 'at' hh:mm a");
	
	public static String getTimeValue(Date date)
	{
		String st  = null;
		Date ct = new Date();
		if((ct.getTime() - date.getTime() ) < 60*1000)
		{
			st = ((ct.getTime()-date.getTime())/1000)+1 + " secs ago";
		}
		else if((ct.getTime() - date.getTime() ) < 60*60*1000)
		{
			st = ((ct.getTime()-date.getTime())/(60*1000))+1 + " mins ago";
		}
		else if((ct.getTime() - date.getTime() ) < 12*60*60*1000)
		{
			st = ((ct.getTime()-date.getTime())/(60*60*1000))+1 + " hours ago";
		}
		else
		{
			st = DateFormats.ddMMMyyyy.format(date);
		}
		
		return st;
	}
}
