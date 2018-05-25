package test.lyz.code.infinity.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipOutputStreamTest {

	public static void main(String args[]) throws IOException {
		test1();
		test2();
	}
	
	public static void test1() throws IOException {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:/JerryWork/Infinity/ziptest/testZip.zip"), Charset.forName("GBK"));
		//实例化一个名称为ab.txt的ZipEntry对象
		ZipEntry entry = new ZipEntry("ab.txt");
		//设置注释
		zos.setComment("zip测试for单个文件");
		//把生成的ZipEntry对象加入到压缩文件中，而之后往压缩文件中写入的内容都会放在这个ZipEntry对象里面
		zos.putNextEntry(entry);
		InputStream is = new FileInputStream("D:/JerryWork/Infinity/ziptest/ab.txt");
		int len = 0;
		while ((len = is.read()) != -1)
			zos.write(len);
		is.close();
		zos.close();
	}
	
	public static void test2() throws IOException {
		File inFile = new File("D:/JerryWork/Infinity/ziptest");
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:/JerryWork/Infinity/ziptest/test.zip"), Charset.forName("GBK"));
		zos.setComment("多文件处理");
		zipFile(inFile, zos, "");
		zos.close();
	}
	
	public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException {
		if (inFile.isDirectory()) {
			File[] files = inFile.listFiles();
			for (File file:files)
				zipFile(file, zos, dir + "/" + inFile.getName());
		} else {
			String entryName = null;
			if (!"".equals(dir))
				entryName = dir + "/" + inFile.getName();
			else
				entryName = inFile.getName();
			ZipEntry entry = new ZipEntry(entryName);
			zos.putNextEntry(entry);
			InputStream is = new FileInputStream(inFile);
			int len = 0;
			while ((len = is.read()) != -1)
				zos.write(len);
			is.close();
		}

	}
	
}