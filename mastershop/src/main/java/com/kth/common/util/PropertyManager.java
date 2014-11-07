/**
* JSP에서 사용하는 Property를 메모리에 로딩시킨다.
* Property파일에 변경이 있을경우 웹에서 loadProperties() 메소드를 호출해서
* Property파일을 다시 로딩시킨다.
*
* 외부에서 new 연산자로 객체를 생성하지 못하게 private로 아무일도 하지 않는 생성자를 추가하였다.
* PropertyManager는 Reading 이 Writing 보다 압도적으로 많기 때문에 가끔 한번씩 있는 Writing 때문에
* 매번 Reading 쓰레드가 접근할때마다 동기화를 시킨다는건 너무 비효율적이라는 생각이 들어서
* isWriting 조건변수를 두어서 읽기만 있는동안은 동기화를 시키지 않고 Writing중일때 접근하는 Reading 쓰레드는
* Writing이 끝날때까지 Blocking 시킨다.
* loadProperties() 메소드는 synchronized 블록안에서 FileInputStream을 얻어야 한다. 그렇지 않으면 한번에 여러개의
* Writing 쓰레드가 실행될 경우 Bad File Descriptor 에러가 날 수도 있다.
*/
package com.kth.common.util;

import java.util.*;
import java.io.*;

public class PropertyManager {
    
	public static String getProperty(String name) {
        ResourceBundle bundle = null;
        try {
            bundle = ResourceBundle.getBundle("common");
		} catch (Exception e) {
        }
		
		return (String)bundle.getString(name);
	}
	
	/*
	//private static String fileName = "D:\\properties\\common\\common.properties";
	//private static String fileName = "/home/olleh/WWW/properties/common.properties";
	private static String fileName = "/home/olleh/WWW/WEB-INF/config/common.properties";
	private static Properties prop = new Properties();
	private static FileInputStream fis = null;
	private static Object lock = new Object();
	private static boolean isWriting = false;

	static {
		loadProperties();
	}

	private PropertyManager() {}
	
	public static void loadProperties() {
		synchronized(lock) {
			isWriting = true;

			try {
				fis = new FileInputStream(fileName);
				//prop.clear(); //이전 Property 초기화
				prop.load(fis); //Property Loading

			} catch(Exception e) {
				System.out.println(e.toString());
			} finally {
				try {
					fis.close();
				} catch(Exception e) {}
			}

			isWriting = false;
		}
	}

	public static String getProperty(String key) {
		if(isWriting) {
			synchronized(lock) {
				return prop.getProperty(key);
			}
		} else {
			return prop.getProperty(key);
		}
	}

	public static String getProperty(String key, String defaultValue) {
		if(isWriting) {
			synchronized(lock) {
				return prop.getProperty(key, defaultValue);
			}
		} else {
			return prop.getProperty(key, defaultValue);
		}
	}
	*/
}
