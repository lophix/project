package com.flag.test.abstractclass;

public abstract class MyAbstractClass {
	
	public static String str = "hello";
	
	abstract String get(String str);
	
	String getString(String str){
		if(str ==null)
			return MyAbstractClass.str;
		else 
			return str;
	}
}
