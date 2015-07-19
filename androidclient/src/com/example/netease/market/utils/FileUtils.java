package com.example.netease.market.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/**
 * 文件操作工具类
 * 
 * @author Dengxiaohua 
 *
 */
public final class FileUtils {
static final String LOG_TAG = "FileUtils";
	
	/**
	 * 操作成功返回值
	 */
	public static final int SUCCESS = 0;
	
	/**
	 * 操作失败返回值
	 */
	public static final int FAILED = -1;
	
	private static final int BUF_SIZE = 32 * 1024; // 32KB

	/**
	 * 文件类型枚举
	 */
	public static enum FileState {
		FState_Dir("I am director!"), // 目录
		FState_File("I am file!"), // 文件
		FState_None("I am a ghost!"), // 不存在
		FState_Other("I am not human!"); // 其他类型
		
		private String tag;
		
		private FileState(String tag) {
			this.tag = tag;
		}
		
		public String getTag() {
			return tag;
		}
		
		@Override
		public String toString() {
			return tag;
		}
	}
	
	private FileUtils() {
	}

	/**
	 * 获取文件状态
	 * 
	 * @param path
	 * @return
	 */
	public static FileState fileState(String path) {
		return fileState(new File(path));
	}

	public static FileState fileState(File file) {
		if (!file.exists())
			return FileState.FState_None;

		if (file.isFile())
			return FileState.FState_File;

		if (file.isDirectory())
			return FileState.FState_Dir;

		return FileState.FState_Other;
	}
	
	/**
	 * 创建文件夹
	 * 
	 * @param path
	 * @return
	 */
	public static int createDir(String path) {
//		int l = path.length();
//		if(path.charAt(l-1) == File.separatorChar){ //如果末尾是 / 会导致创建目录失败，测试发现不会
//			path = path.substring(0, l-1);
//		}
		
		return createDir(new File(path));
	}

	public static int createDir(File file) {
		if (file.exists()) {
			if (file.isDirectory())
				return SUCCESS;
			file.delete(); // 避免他是一个文件存在
		}

		if (file.mkdirs())
			return SUCCESS;

		return FAILED;
	}

	public static int removeDir(String path) {
		return removeDir(new File(path));
	}
	
	/**
	 * 删除一个文件夹
	 * 
	 * <p>by:yichou 2013-5-7 15:24:41<p>
	 * 
	 * @param dir
	 * @return
	 */
	public static int removeDir(File dir) {
		if(!dir.exists())
			return SUCCESS;
		
		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			if (files != null) {
				for (File f : files) {
					if(f.isDirectory())
						removeDir(f);
					else
						f.delete();
				}
			}
		}
		
		return dir.delete()? SUCCESS : FAILED;
	}

	/**
	 * 获取文件的目录路径. 需要注意的是输入的路径是完整的文件名路径. 否则会导致解析出错<br>
	 * 路径的统一方式为:<br>
	 * CmmnFiles.SDCardFolder + "..."<br>
	 * 或者:<br>
	 * CmmnFiles.InnerStortageFolder + "..."
	 * 
	 * @param path
	 * 
	 * @return 父目录 或者 null 不存在父目录
	 */
	public static String getFolderFromPath(String path) {
		return getFolderFromPath(new File(path));
	}

	public static String getFolderFromPath(File file) {
		if (!file.isAbsolute())
			return null;

		String path = file.getAbsolutePath();
		String[] slipArray = path.split(File.separator);
		// 错误的格式
		if (slipArray.length <= 0)
			return null;
		// /XXX/YYY/ZZZ.CCC
		int pathLen = path.length();
		int fileNameLen = slipArray[slipArray.length - 1].length();

		return path.substring(0, pathLen - fileNameLen);
	}
	
	/**
	 * @see {@link #checkParentPath(File)}
	 */
	public static void checkParentPath(String path) {
		checkParentPath(new File(path));
	}
	
	/**
	 * 在打开一个文件写数据之前，先检测该文件路径的父目录是否已创建，保证能创建文件
	 * 
	 * @param file
	 */
	public static void checkParentPath(File file) {
		File parent = file.getParentFile();
		if (parent != null)
			createDir(parent);
	}

	/**
	 * 将一缓冲流写入文件
	 * 
	 * @param path
	 *            目标文件路径
	 * @param is 输入流
	 * @param isAppend 是否追加
	 * 
	 * @return 成功 {@link #SUCCESS}； 失败 {@link #FAILED}
	 */
	public static int streamToFile(String path, InputStream is, boolean isAppend) {
		return streamToFile(new File(path), is, isAppend);
	}

	public static int streamToFile(File file, InputStream is, boolean isAppend) {
		checkParentPath(file);

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file, isAppend);
			byte[] buf = new byte[BUF_SIZE];
			int readSize = 0;

			while ((readSize = is.read(buf)) != -1)
				fos.write(buf, 0, readSize);
			fos.flush();

			return SUCCESS;
		} catch (Exception e) {
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}

		return FAILED;
	}
	
	/**
	 * 写字节数组到文件
	 * 
	 * @param file 目标文件
	 * @param data 字节数组
	 * @param offset 偏移 {@code >=0&&<=data.length}
	 * @param length 长度 ==0 表示 data.length
	 * @param isAppend 是否追加
	 * 
	 * @return 成功 {@link #SUCCESS}； 失败 {@link #FAILED}
	 */
	public static int bytesToFile(File file, byte[] data, int offset, int length, boolean isAppend) {
		checkParentPath(file);
		
		if(data == null)
			return FAILED;
		
		if(length <= 0)
			length = data.length;

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file, isAppend);
			fos.write(data, offset, length);
			fos.flush();
			
			return SUCCESS;
		} catch (Exception e) {
		} finally {
			try {
				fos.close();
			} catch (Exception e) {
			}
		}

		return FAILED;
	}
	
	public static int bytesToFile(File file, byte[] data){
		return bytesToFile(file, data, 0, data.length, false);
	}
	
	/**
	 * @see {@link #bytesToFile(File file, byte[] data, int offset, int length, boolean isAppend)}
	 */
	public static int bytesToFile(String path, byte[] data, int offset, int length, boolean isAppend) {
		return bytesToFile(new File(path), data, offset, length, isAppend);
	}
	
	/**
	 * 读取文件内容到二进制缓冲区
	 * 
	 * @param path 文件路径
	 * @param offset 起始位置
	 * @param length 读取长度 ，0为全部
	 * 
	 * @return 失败 或 length <=0 返回null，成功返回 字节数组
	 */
	public static byte[] fileToBytes(String path, int offset, int length) {
		return fileToBytes(new File(path), offset, length);
	}
	
	public static byte[] fileToBytes(File file){
		return fileToBytes(file, 0, 0);
	}
	/**
	 * 读取文件内容到二进制缓冲区
	 * 
	 * @param path 文件路径
	 * @param offset 起始位置
	 * @param length 读取长度，==0 为全部
	 * 
	 * @return 失败 或 length < 0  返回null，成功返回 字节数组
	 */
	public static byte[] fileToBytes(File file, int offset, int length) {
		if( length < 0 )
			return null;
		
		InputStream is = null;
		
		try {
			is = new FileInputStream(file);
			if(length == 0)
				length = is.available();
			byte[] outBuf = new byte[length];
			is.read(outBuf, offset, length);
			
			return outBuf;
		} catch (Exception e) {
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
		}
		
		return null;
	}

	/**
	 * 复制文件, 对于大的文件, 推荐开启一个线程来复制. 防止长时间阻塞
	 * 
	 * @param newPath
	 * @param oldPath
	 * 
	 * @return 成功 {@link #SUCCESS}； 失败 {@link #FAILED}
	 */
	public static int copyTo(String dstPath, String srcPath) {
		return copyTo(new File(dstPath), new File(srcPath));
	}

	public static int copyTo(File dstFile, File srcFile) {
		if (fileState(srcFile) != FileState.FState_File) // 源非文件
			return FAILED;

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(srcFile);

			return streamToFile(dstFile, fis, false);
		} catch (Exception e) {
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}

		return FAILED;
	}

	/**
	 * @see {@link #assetToFile(Context context, String assetName, File file)}
	 */
	public static int assetToFile(Context context, String assetName, String path) {
		return assetToFile(context, assetName, new File(path));
	}

	/**
	 * assets 目录下的文件保存到本地文件
	 * 
	 * @param context 
	 * @param assetName assets下名字，非根目录需包含路径 a/b.xxx
	 * @param file 目标文件
	 * 
	 * @return 成功 {@link #SUCCESS}； 失败 {@link #FAILED}
	 */
	public static int assetToFile(Context context, String assetName, File file) {
		InputStream is = null;

		try {
			is = context.getAssets().open(assetName);
			return streamToFile(file, is, false);
		} catch (Exception e) {
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
		}

		return FAILED;
	}
	
	/**
	 * 从 Assets 文件读取文件全部，并转为字符串
	 * 
	 * @param manager
	 * @param name
	 *            文件名
	 * @return 读取到的字符串
	 * 
	 * @author Yichou
	 * <p>date 2013-4-2 11:30:05
	 */
    public static String readStringFromAsset(AssetManager manager, String name){
		InputStream is = null;
		
		try {
			is = manager.open(name);
			byte[] buf = new byte[is.available()];
			is.read(buf);
			
			return new String(buf);
		} catch (Exception e) {
//			FsSdkLog.e(LOG_TAG, "readStringFromAsset(" + name + ") error!\n" + e.getMessage());
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
		}
		
		return "";
    }

	/**
	 * 检查 assets 下是否存在某文件
	 * 
	 * @param am
	 * @param name
	 * @return
	 */
	public static boolean assetExist(AssetManager am, String name) {
		InputStream is = null;
		try {
			is = am.open(name);
			return true;
		} catch (IOException e) {
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
		}

		return false;
	}

	/**
	 * @return SD卡是否已挂载
	 */
	public static boolean isSDMounted() {
		String sdState = Environment.getExternalStorageState(); // 判断sd卡是否存在
		return sdState.equals(android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * @return SD卡剩余容量
	 */
	public static long getSDLeftSpace() {
		if (isSDMounted() == false) {
			return 0;
		} else {
			StatFs statfs = new StatFs(Environment.getExternalStorageDirectory() + File.separator);
			return (long) statfs.getAvailableBlocks() * (long) statfs.getBlockSize();
		}
	}
	
	public static String coverSize(long size) {
		String s = "";
		if (size < 1024)
			s += size + "b";
		else if (size < 1024 * 1024) {
			s = String.format(Locale.US, "%.2f Kb", size / 1024f);
		} else if (size < 1024 * 1024 * 1024) {
			s = String.format(Locale.US, "%.2f Mb", size / 1024 / 1024f);
		} else {
			s = String.format(Locale.US, "%.2f Gb", size / 1024 / 1024 / 1024f);
		}
		return s;
	}
	
	public static long getROMLeft() {
		File data = Environment.getDataDirectory();
		
		StatFs sf = new StatFs(data.getAbsolutePath());
		long blockSize = sf.getBlockSize();
		long blockCount = sf.getBlockCount();
		long availCount = sf.getAvailableBlocks();
		
		Log.i("", "ROM Total:" + coverSize(blockSize * blockCount) 
				+ ", Left:" + coverSize(availCount * blockSize));
		
		return availCount * blockSize;
	}

	/**
	 * 获取私有目录下的文件夹绝对路径，末尾带 "/"，不创建
	 * 
	 * @param context
	 * @param name
	 *            文件夹名
	 * @return
	 */
	public static String getDirPathInPrivate(Context context, String name) {
		return context.getDir(name, Context.MODE_PRIVATE).getAbsolutePath() + File.separator;
	}
	
	/**
	 * 或者本应用 so 存放路径
	 * 
	 * @param context
	 * @return
	 */
	public static String getSoPath(Context context) {
		return context.getApplicationInfo().dataDir + "/lib/";
	}
	
	public static FileLock tryFileLock(String path) {
		return tryFileLock(new File(path));
	}
	
	/**
	 * 占用某个文件锁
	 * 
	 * @param file
	 * @return
	 */
	public static FileLock tryFileLock(File file) {
		try {
			checkParentPath(file); //父目录不存在会导致创建文件锁失败

			FileOutputStream fos = new FileOutputStream(file);
			FileLock fl = fos.getChannel().tryLock();
			if(fl.isValid()) {
				Log.i(LOG_TAG, "tryFileLock " + file + " SUC!");
				return fl;
			}
		} catch (Exception e) {
			Log.e(LOG_TAG, "tryFileLock " + file + " FAIL! " + e.getMessage());
		}
		
		return null;
	}
	
	public static void freeFileLock(FileLock fl, File file) {
		if(file != null)
			file.delete();

		if(fl == null || !fl.isValid()) return;
		
		try {
			fl.release();
			Log.i(LOG_TAG, "freeFileLock " + file + " SUC!");
		} catch (IOException e) {
		}
	}
	
	/**
	 * 截取路径名
	 * 
	 * @return
	 */
	public static String getPathName(String absolutePath) {
		int start = absolutePath.lastIndexOf(File.separator) + 1;
		int end = absolutePath.length();
		return absolutePath.substring(start, end);
	}
	
	/**
	 * 重命名
	 * 
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public static boolean reNamePath(String oldName, String newName) {
		File f = new File(oldName);
		return f.renameTo(new File(newName));
	}
	
	/**
	 * 列出root目录下所有子目录
	 * 
	 * @param path
	 * @return 绝对路径
	 */
	public static List<String> listPath(String root) {
		List<String> allDir = new ArrayList<String>();
		SecurityManager checker = new SecurityManager();
		File path = new File(root);
		checker.checkRead(root);
		if (path.isDirectory()) {
			for (File f : path.listFiles()) {
				if (f.isDirectory()) {
					allDir.add(f.getAbsolutePath());
				}
			}
		}
		return allDir;
	}
	
	/**
	 * 删除空目录
	 * 
	 * 返回 0代表成功 ,1 代表没有删除权限, 2代表不是空目录,3 代表未知错误
	 * 
	 * @return
	 */
	public static int deleteBlankPath(String path) {
		File f = new File(path);
		if (!f.canWrite()) {
			return 1;
		}
		if (f.list() != null && f.list().length > 0) {
			return 2;
		}
		if (f.delete()) {
			return 0;
		}
		return 3;
	}
	
	/**
	 * 获取SD卡的根目录，末尾带\
	 * 
	 * @return
	 */
	public static String getSDRoot() {
		return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
	}
}
