package com.wjcx.json_test1;



import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JSONTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		String json="{\"name\":\"Lucy\"}";
		JSONObject obj1=new JSONObject(json);
		String name=obj1.getString("name");
		System.out.println(name);
		obj1.put("inItial", name.substring(0, 1).toLowerCase());
		
		String[] str=new String[]{"JavaScript","Web","Jackson"};
		obj1.put("str",str);
		Map<String,String> map=new HashMap<String,String>();
		map.put("division1", "Fairy Tail");
		map.put("division2","Green Hourse");
		obj1.put("map", map);
		System.out.println(obj1.toString());
	}

}
