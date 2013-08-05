package com.hymer.core.util;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

public class ZipUtil {
	
	public static void compress(String srcDir) {
		String zipFile = srcDir + "/zip.zip";
		compress(srcDir, zipFile);
	}

	public static void compress(String srcDir, String zipFilePath) {
		File srcdir = new File(srcDir);
		if (!srcdir.exists())
			throw new RuntimeException(srcDir + "不存在！");
		
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setDestFile(new File(zipFilePath));
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		fileSet.setDir(srcdir);
		//fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
		//fileSet.setExcludes(...); 排除哪些文件或文件夹
		zip.addFileset(fileSet);
		zip.execute();
	}
	
}