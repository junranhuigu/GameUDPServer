package com.server.java.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.List;

public class FileUtil {
	/**覆盖保存*/
	public static void cover(List<? extends Object> records, String filePath) throws Exception{
		File file = new File(filePath);
		if(!file.exists()){
			file.createNewFile();
		}
		if(records == null){
			records = Collections.emptyList();
		}
		if(records.isEmpty()){
			return;
		}
		try(	FileOutputStream fos = new FileOutputStream(file);){ 
			for(Object record : records){
				fos.write(record.toString().getBytes("UTF-8"));
				fos.flush();
			}
		}
	}
	
}
