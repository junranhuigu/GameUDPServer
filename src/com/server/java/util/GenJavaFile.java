package com.server.java.util;

import java.io.File;

import com.hp.csv.JavaGen;

public class GenJavaFile {

	

	public static void CreateFileServer2(String path) {
		// 根据符号"/"来分隔路径   
		String[] paths = path.split("/");
		String returnStr = "";
		int length = paths.length;
		boolean bool = false;
		for (int i = 0; i < length; i++) {
			returnStr += paths[i];
			File file = new File(returnStr.toString());
			if (!file.isDirectory()) {
				bool = file.mkdir();
			}
			returnStr += ("/");
		}

	}

	public static void main(String[] args) {
		// JavaGen.genJavaFile("src/dbconfig/file.xml","C:/tomcat_debug/webapps/game/WEB-INF/");
		// JavaGen.genJavaFile("src/dbconfig/file.xml","C:/Users/chengxu/Downloads/apache-tomcat-6.0.39-windows-x64/apache-tomcat-6.0.39/webapps/ROOT/WEB-INF/");
		// CreateFileServer2("C:/Documents and Settings/huhua/My Documents/toto_3364_huhua/hp_game/src/com/hp/game/logic");
		// JavaGen.genJavaFile("src/dbconfig/file.xml","src/");
		// JavaGen.genJavaFile("src/dbconfig/file.xml","C:/Users/Server/workspaceSVN/game/");
		JavaGen.genJavaFile("src/dbconfig/file.xml", System.getProperty("user.dir")
				+ File.separator);

	}

}
