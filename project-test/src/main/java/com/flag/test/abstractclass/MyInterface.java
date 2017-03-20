package com.flag.test.abstractclass;

public interface MyInterface {
	public String str = "hello";
	
	abstract String get(String str);
	
	static String getString(String str){
		if(str ==null)
			return MyInterface.str;
		else 
			return str;
	}
}
