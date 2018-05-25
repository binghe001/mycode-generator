package com.lyz.code.infinity.domain;

/**
 * 把一个文件夹里的所有文件包括文件夹 一并原样拷贝到另一个目录中；
 *@author shuishui
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopyer {
	public File dirFrom;
	public File dirTo;

	// 目标路径创建文件夹
	public synchronized void listFileInDir(File file) {
		File[] files = file.listFiles();
		for (File f : files) {
			String tempfrom = f.getAbsolutePath();
			String tempto = tempfrom.replace(dirFrom.getAbsolutePath(),
					dirTo.getAbsolutePath()); // 后面的路径 替换前面的路径名
			if (f.isDirectory()) {
				File tempFile = new File(tempto);
				tempFile.mkdirs();
				listFileInDir(f);
			} else {
				System.out.println("源文件:" + f.getAbsolutePath());
				//
				String separator = System.getProperty("file.separator");
				int endindex = tempto.lastIndexOf(separator);// 找到"/"所在的位置
				String mkdirPath = tempto.substring(0, endindex);
				File tempFile = new File(mkdirPath);
				tempFile.mkdirs();// 创建立文件夹
				System.out.println("目标点:" + tempto);
				copy(tempfrom, tempto);
			}
		}
	}

	/**
	 * 封装好的文件拷贝方法
	 */
	public synchronized void copy(String from, String to) {
		try {
			InputStream in = new FileInputStream(from);
			OutputStream out = new FileOutputStream(to);

			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = in.read(buff)) != -1) {
				out.write(buff, 0, len);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		File fromfile = new File("e:\\shui\\test");// 源文件夹
		File tofile = new File("e:\\Jying\\shui");// 目标

		FileCopyer copy = new FileCopyer();
		// 设置来源去向
		copy.dirFrom = fromfile;
		copy.dirTo = tofile;
		copy.listFileInDir(fromfile);

	}
}