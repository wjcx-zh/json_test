package com.wjcx.json.model;

import java.util.Date;

public class Student {
	private String name;
	private Date date;
	//测试static属性的json格式
	//static属性和方法是属于类的，而不是属于对象的
	//json不会去访问静态的getter方法
	//解决方案1：去掉getter和setter的static
	//
	private static int age;
	
	//自关联
	private Student student=this;
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public /*static*/static int getAge() {
		return age;
	}
	public /*static*/static void setAge(int age) {
		Student.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", date=" + date + "]";
	}
	
	
}
