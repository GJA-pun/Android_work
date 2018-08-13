package com.example.hegua.androidwork.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import android.os.Environment;
import android.text.TextUtils;

import com.example.hegua.androidwork.object.user_object.User;

public class ReUserInfo {
/*
	public static boolean saveUserInfo(String username,String CPnumber){
		
		String path = "/data/data/com.example.textjson/usernumber.txt";
		try {
			FileOutputStream fos = new FileOutputStream(path);
			
			String data = username + "##" + CPnumber;
			
			fos.write(data.getBytes());
			
			fos.flush();
			fos.close();
			
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	public static Map<String, String> getUserInfo(){
		
		String path = "/data/data/com.example.textjson/usernumber.txt";
		FileInputStream fis;
		try {
			fis = new FileInputStream(path);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			
			String string = reader.readLine();
			if(!TextUtils.isEmpty(string)){
			String[] split = string.split("##");
			Map<String ,String> userInfoMap = new HashMap<String,String>();
			
			
			userInfoMap.put("username", split[0]);
			userInfoMap.put("CPnumber", split[1]);
			return userInfoMap;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
*/
public static boolean saveUserInfo(String username, String password) {
		
		try {
			String state = Environment.getExternalStorageState();
			
			if(!Environment.MEDIA_MOUNTED.equals(state)) {
				System.out.println("saveUserInfo-------->SD?"+state);
				return false;
			}

			File sdCardFile = Environment.getExternalStorageDirectory();
			System.out.println("SD------------------->"+Environment.getExternalStorageDirectory());
			File file = new File(sdCardFile, "AWuserinfo.txt");
			FileOutputStream fos = new FileOutputStream(file);
			String data = username + "##" + password;
			fos.write(data.getBytes());
			fos.flush();
			fos.close();
			return true;
		} catch (Exception e) {
			System.out.println("e---------------------------->"+e);
			e.printStackTrace();
		}
		return false;
	}

public static User getUserInfo() {
	
	try {
		String state = Environment.getExternalStorageState();
		
		if(!Environment.MEDIA_MOUNTED.equals(state)) {
			return null;
		}
		
		File sdCardFile = Environment.getExternalStorageDirectory();
		File file = new File(sdCardFile, "AWuserinfo.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String text = br.readLine();
		br.close();
		
		if(!TextUtils.isEmpty(text)) {
			String[] split = text.split("##");
			if (split[0].equals("")&&split[1].equals("")){
				return null;
			}else {
				User user = new User(split[0], split[1]);
				return user;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

}
