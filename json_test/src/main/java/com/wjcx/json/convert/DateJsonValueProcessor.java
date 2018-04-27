package com.wjcx.json.convert;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor{
	
	private String format="yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat sdf=new SimpleDateFormat(format);
	
	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig config) {
		//System.out.println(key+","+value+","+config);
		if(value==null){
			return "";
		}else if(value instanceof Date){
			return sdf.format((Date) value);
		}else{
			return value.toString();
		}
		
	}
	
}
