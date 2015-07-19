package com.example.netease.market;

import java.io.File;

public class FileDelete {

	/**删除文件夹的方法（删除该文件夹下的所有文件）*/
   	public static void deleteFolder(File folder){
   		File[] fileArray = folder.listFiles();
   		if(fileArray.length == 0){//空文件夹则直接删除
   			folder.delete();
   		}else{
   			for(File currentFile:fileArray){//遍历该目录
   				if(currentFile.exists()&&currentFile.isFile()){//文件则直接删除
   					currentFile.delete();
   				}else{
   					deleteFolder(currentFile);//回调
   				}
   			}
   			folder.delete();
   		}
   	} 
}
