package com.example.netease.market.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.example.netease.market.utils.CipherUtils;
import com.example.netease.market.utils.FileUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 本地数据根据
 * 
 * @author Dengxiaohua 
 *
 */
public final class LocalDataUtils {
	public static String genName(String key){
		return CipherUtils.Md5Enc16(key.getBytes());
	}

	public static void toLocal(String key, Bitmap bitmap) {
		String name = genName(key) + ".png";
		File file;
		
		if(FileUtils.isSDMounted()){
			file = new File(Globals.ICON_STORE_PATH, name);
		}else {
			file = Globals.context.getFileStreamPath(name);
		}
		
		FileUtils.checkParentPath(file);
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
			fos.flush();
		} catch (Exception e) {
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}
	
	public static Bitmap getBitmap(String key) {
		String name = genName(key) + ".png";
		File file;
		
		if(FileUtils.isSDMounted()){
			file = new File(Globals.ICON_STORE_PATH, name);
		}else {
			file = Globals.context.getFileStreamPath(name);
		}
		
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			return BitmapFactory.decodeStream(is);
		} catch (FileNotFoundException e) {
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
		}
		
		return null;
	}
	
	public static void toPrivate(String name, String string) {
		File file = Globals.context.getFileStreamPath(name);
		FileUtils.bytesToFile(file, string.getBytes());
		
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(file);
//			fos.write(string.getBytes());
//			fos.flush();
//		} catch (Exception e) {
//		} finally {
//			try {
//				fos.close();
//			} catch (Exception e) {
//			}
//		}
	}
	
	public static String getStringFromPrivate(String name) {
		File file = Globals.context.getFileStreamPath(name);
		byte[] buf = FileUtils.fileToBytes(file);
		if(buf != null)
			return new String(buf);
		return null;
	}
}
