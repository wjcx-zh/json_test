package com.wjcx.json_test;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wjcx.json.convert.DateJsonValueProcessor;
import com.wjcx.json.model.Grade;
import com.wjcx.json.model.Student;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class JSONTest {
	private static Grade grade;
	private static Student student;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		student=new Student();
		
		grade=new Grade();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		student=null;
		grade=null;
	}
	
	
	
	@Test
	public void test() {
		grade.setId(1);
		grade.setName("java");
		System.out.println(JSONSerializer.toJSON(grade));
	}
	@Test
	public void test1() {
		grade.setId(1);
		grade.setName("java");
		Student stu1=new Student();
		Student stu2=new Student();
		stu1.setName("Kazaf");
		stu2.setName("Helen");
		List<Student> list=new ArrayList<Student>();
		list.add(stu1);
		list.add(stu2);
		grade.setStudents(list);
		System.out.println(JSONSerializer.toJSON(grade));
	}
	
	@Test   //测试static是不能转换为json属性的
	public void testStaticAge() {
		Student stu1=new Student();
		stu1.setName("Kazaf");
		//stu1.setDate(new Date());
		stu1.setAge(21);
		//解决方案2：如果返回的是static，或者返回类型不确定，可以采用map或自己构建json
		JSONObject obj=new JSONObject();
		obj.put("age", stu1.getAge());
		obj.put("date", stu1.getDate());
		obj.put("name", stu1.getName());
		System.out.println(JSONSerializer.toJSON(stu1));
		System.out.println(obj.toString());
	}
	
	@Test  //解决自关联问题
	public void test2(){
		student.setDate(new Date());
		student.setName("Ziv");
		/*
		 *循环关联自身，进入循环无法转换
		System.out.println(JSONSerializer.toJSON(student));
		*/
		//解决:通过jsonConfig来过滤相应的参数,例如密码
		JsonConfig config=new JsonConfig();
		/*
		config.setExcludes(new String[]{"student","date"});  //设置需要排除哪些字段
		System.out.println(JSONObject.fromObject(student, config));
		*/
		config.setExcludes(new String[]{"date"});
		//设置字段的自关联过滤:
		//STRICT:缺省，无论是否自关联，都要转换
		//LENIENT:如果自关联，设置为null
		//NOPROP:如果自关联，忽略属性
		
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		//config.setCycleDetectionStrategy(CycleDetectionStrategy.STRICT);
		//config.setCycleDetectionStrategy(CycleDetectionStrategy.NOPROP);
		System.out.println(JSONObject.fromObject(student, config));
	}
	@Test  //自定义日期处理类，格式化日期数据
	public void test3(){
		student.setDate(new Date());
		student.setName("Ziv");
		
		JsonConfig config=new JsonConfig();
		
		config.setExcludes(new String[]{"student"});  //设置需要排除哪些字段
		//指定某个json类型的处理方式
		DateJsonValueProcessor dateValue=new DateJsonValueProcessor();
		config.registerJsonValueProcessor(Date.class, dateValue);

		System.out.println(JSONObject.fromObject(student, config));
	}
}
