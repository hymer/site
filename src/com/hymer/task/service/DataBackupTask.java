package com.hymer.task.service;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hymer.core.mail.MailBean;
import com.hymer.core.mail.MailSender;
import com.hymer.core.util.FileUtil;
import com.hymer.core.util.Formatters;
import com.hymer.core.util.ServerUtil;
import com.hymer.core.util.ZipUtil;

@Service
public class DataBackupTask {

	Log log = LogFactory.getLog(getClass());

	@Scheduled(cron = "08 08/10 * * * *")
	void backupByFile() {
		String current = getClass().getClassLoader().getResource("").getPath();
		String path = new File(current).getParentFile().getParentFile().getParent();
		final Calendar today = Calendar.getInstance();
		try {
			String dateString = Formatters.formatDate(today.getTime(),
					"yyyyMMdd");
			String zipFilePath = path + "/hsqldb/data." + dateString + ".zip";
			
			//检测是否今日已备份
			File backUpFile = new File(zipFilePath);
			if (backUpFile.exists()) { //已备份，则跳过
				return;
			}
			
			// 备份当前数据
			// FileUtil.copyFolder(path + "/hsqldb/data/", path +
			// "/hsqldb/data."
			// + dateString + ".bak/");
			
			// zip打包，邮件发送
			ZipUtil.compress(path + "/hsqldb/data/", zipFilePath);
			try {
				log.info("发送邮件...");
				MailBean mail = new MailBean();
				mail.setSubject("数据备份");
				String content = "此为" + dateString + "数据文件备份。<br/><br/>";
				mail.setContent(content + ServerUtil.getAllInformation());
				mail.setToAddress("he.w@qq.com");
				List<String> attachments = new ArrayList<String>();
				attachments.add(zipFilePath);
				mail.setAttachments(attachments);
				MailSender.getDefaultInstance().send(mail);
			} catch (Exception e) {
				//如果邮件发送失败，则将备份文件改名
				File nowBackUpFile = new File(zipFilePath);
				File localback = new File(zipFilePath + ".localbak");
				if (localback.exists()) {
					localback.delete();
				}
				nowBackUpFile.renameTo(localback);
				log.info("邮件发送失败...");
				throw e;
			}

			// 找出备份时间超过15天的文件夹，并删除之
			File hsqldb = new File(path + "/hsqldb/");
			File[] datas = hsqldb.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					String dirName = pathname.getName();
					try {
						if (dirName.startsWith("data.")
								&& dirName.endsWith(".zip")) {
							String dateStr = dirName.substring(
									dirName.indexOf("data.") + 5,
									dirName.indexOf(".zip"));
							Calendar c = Calendar.getInstance();
							Date date = Formatters.parseDate(dateStr,
									"yyyyMMdd");
							c.setTime(date);
							c.add(Calendar.DATE, 15);
							if (c.before(today)) {
								return true;
							}
						}
					} catch (Exception e) {
					}
					return false;
				}
			});
			for (File dir : datas) {
				FileUtil.deleteDir(dir);
			}
			log.info("备份成功...");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("备份失败...");
		}
	}

}
