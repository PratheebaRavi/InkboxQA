package Inkbox.Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import Helpers.LaunchDriver;

import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail extends LaunchDriver {

	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		SendEmail email = new SendEmail();
		email.Send_Email_Automatically2();
	}

	public String CompressFile() throws IOException {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String FolderName = "Report_" + df.format(dateobj).replaceAll(" ", "_").replaceAll("/", "_").replace(":", "_");
		FolderName = FolderName + ".zip";
		String path = System.getProperty("user.dir");
		path = path + "\\Reports";
		String sourceFile = path;
	//	FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\CompressedReport.zip");
		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\"+FolderName);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		File fileToZip = new File(sourceFile);

		zipFile(fileToZip, fileToZip.getName(), zipOut);
		zipOut.close();
		fos.close();
		return FolderName;
	}

	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
				zipOut.closeEntry();
			} else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
				zipOut.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();
	}

	public void Send_Email_Automatically() throws IOException {
		// Recipient's email ID needs to be mentioned.
		// String to = "kishvenni1921@hotmail.com";
		String to = getToAddress();

		// Sender's email ID needs to be mentioned
		// String from = "testinkbox2@gmail.com";
		String from = getFromAddress();

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.required", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		// Get the Session object.// and pass
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				// return new PasswordAuthentication("testinkbox2@gmail.com", "Pa55word123!!!");
				return new PasswordAuthentication(getFromAddress(), getFromPassword());
			}

		});
		// session.setDebug(true);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(getCcMails()));

			// Set Subject: header field
			message.setSubject("Automation report");

			Multipart multipart = new MimeMultipart();

			MimeBodyPart attachmentPart = new MimeBodyPart();
			String filename=CompressFile();
			MimeBodyPart textPart = new MimeBodyPart();
			String path = System.getProperty("user.dir");
			//path = path + "\\CompressedReport.zip";
			path = path + "\\"+filename;
			try {

//				File f = new File(
//						"C:\\Users\\IBCK-WL-037\\Inkbox_WorkSpace\\Inkbox\\testing\\Inkbox_UITestFramework\\Reports\\report.html");

				File f = new File(path);

				attachmentPart.attachFile(f);
				textPart.setText("Please find the attached for 3.3-rc1 report");
				multipart.addBodyPart(textPart);
				multipart.addBodyPart(attachmentPart);

			} catch (IOException e) {

				e.printStackTrace();

			}

			message.setContent(multipart);

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	String FromAddress;
	String FromAddressPassword;
	String ToAddress;
	String CC_mails;

	public void getEmailDetails() throws IOException {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//config.properties");
		Properties prop = new Properties();
		prop.load(file);
		// setFromAddress(prop.getProperty("FromAddress"));
		FromAddress = prop.getProperty("FromAddress");
		// setFromPassword(prop.getProperty("FromAddressPassword"));
		FromAddressPassword = prop.getProperty("FromAddressPassword");
		// setToAddress(prop.getProperty("ToAddress"));
		ToAddress = prop.getProperty("ToAddress");
		// setCcMails(prop.getProperty("CC_mails"));
		CC_mails = prop.getProperty("CC_mails");
	}

	public void Send_Email_Automatically2() throws IOException {
		getEmailDetails();
		// Recipient's email ID needs to be mentioned.

		// String to = "kishvenni1921@hotmail.com";
		String to = ToAddress;

		// Sender's email ID needs to be mentioned
		// String from = "Inkboxqa@getinkbox.com";
		String from = FromAddress;

		// Assuming you are sending email from through gmails smtp
		String host = "smtp.gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.required", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		// Get the Session object.// and pass
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				// return new PasswordAuthentication("Inkboxqa@getinkbox.com",
				// "xrfujofcoqhcfuuy");
				return new PasswordAuthentication(FromAddress, FromAddressPassword);
			}

		});
		// session.setDebug(true);
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// message.setRecipients(Message.RecipientType.CC,
			// InternetAddress.parse("vennelakish1@gmail.com,kishvenkish@hotmail.com"));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(CC_mails));
			// Set Subject: header field
			message.setSubject("Automation report");

			Multipart multipart = new MimeMultipart();

			MimeBodyPart attachmentPart = new MimeBodyPart();
			String filename=CompressFile();
			MimeBodyPart textPart = new MimeBodyPart();
			String path = System.getProperty("user.dir");
			//path = path + "\\CompressedReport.zip";
			path = path + "\\"+filename;
			try {

//				File f = new File(
//						"C:\\Users\\IBCK-WL-037\\Inkbox_WorkSpace\\Inkbox\\testing\\Inkbox_UITestFramework\\Reports\\report.html");

				File f = new File(path);

				attachmentPart.attachFile(f);
				textPart.setText("Please find the attached report");
				multipart.addBodyPart(textPart);
				multipart.addBodyPart(attachmentPart);

			} catch (IOException e) {

				e.printStackTrace();

			}

			message.setContent(multipart);

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public void ConvertToZip() {
		String path = System.getProperty("user.dir");
		path = path + "\\Reports";
		String srcFilename = path;
		String zipFile = path + ".zip";

		try {
			byte[] buffer = new byte[1024];
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			File srcFile = new File(srcFilename);
			FileInputStream fis = new FileInputStream(srcFile);
			zos.putNextEntry(new ZipEntry(srcFile.getName()));
			int length;
			while ((length = fis.read(buffer)) > 0) {
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();
			fis.close();
			zos.close();
		} catch (IOException ioe) {
			System.out.println("Error creating zip file" + ioe);
		}
	}

}
