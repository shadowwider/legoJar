package com.xencio.lego.oozie.config;

import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.io.InputStream;

public class ConfigEmail {

	public static ConfigEmail load(String filename) {

		ConfigEmail config = new ConfigEmail();
		filename = "mail_template_"+ filename +".json";
		InputStream inputStream = null;
		
			inputStream = Config.class.getClassLoader().getResourceAsStream(filename);  //xbb帮我注意一下mail_template_hospital_error.json 这个文件放在什么路径才可以被正确读到
			if (inputStream == null) {
				inputStream = new FileInputStream(filename);
			}
			config = JSONObject.parseObject(inputStream,ConfigEmail.class);

		
		
		return config;
	}

	private String attachmentPath;
	private String hiveUser;
	private String hivePsw;

	private String adminMailAddresses;
	private String senderMailAddress;
	private String ownerMailAddresses;
	private String mailSubject;
	private String mailContentTemplate;
	private String[] attachmentHeadArray;
	
	private String mailHost;
	private String mailProtocol;
	private String mailHostPort;
	private String mailSmtpStarttls;
	private String mailSmtpAuth;
	private String mailAccountName;
	private String mailAccountPsw;
	private String mailSourceAddress;


	public String getAttachmentPath() {

		return attachmentPath;
	}

	public String getHiveUser() {

		return hiveUser;
	}

	public String getHivePsw() {

		return hivePsw;
	}

	public String getSenderMailAddress() {

		return senderMailAddress;
	}

	public String getMailSubject() {

		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {

		this.mailSubject = mailSubject;
	}

	public void setAttachmentPath(String attachmentPath) {

		this.attachmentPath = attachmentPath;
	}

	public void setHiveUser(String hiveUser) {

		this.hiveUser = hiveUser;
	}

	public void setHivePsw(String hivePsw) {

		this.hivePsw = hivePsw;
	}

	public void setSenderMailAddress(String senderMailAddress) {

		this.senderMailAddress = senderMailAddress;
	}

	public String getMailHost() {

		return mailHost;
	}

	public String getMailProtocol() {

		return mailProtocol;
	}

	public String getMailAccountName() {

		return mailAccountName;
	}

	public String getMailAccountPsw() {

		return mailAccountPsw;
	}

	public String getMailSourceAddress() {

		return mailSourceAddress;
	}

	public void setMailHost(String mailHost) {

		this.mailHost = mailHost;
	}

	public void setMailProtocol(String mailProtocol) {

		this.mailProtocol = mailProtocol;
	}

	public void setMailAccountName(String mailAccountName) {

		this.mailAccountName = mailAccountName;
	}

	public void setMailAccountPsw(String mailAccountPsw) {

		this.mailAccountPsw = mailAccountPsw;
	}

	public void setMailSourceAddress(String mailSourceAddress) {

		this.mailSourceAddress = mailSourceAddress;
	}

	public String getMailContentTemplate() {

		return mailContentTemplate;
	}

	public void setMailContentTemplate(String mailContentTemplate) {

		this.mailContentTemplate = mailContentTemplate;
	}

	public String[] getAttachmentHeadArray() {

		return attachmentHeadArray;
	}

	public void setAttachmentHeadArray(String[] attachmentHeadArray) {

		this.attachmentHeadArray = attachmentHeadArray;
	}

	public String getAdminMailAddresses() {

		return adminMailAddresses;
	}

	public void setAdminMailAddresses(String adminMailAddresses) {

		this.adminMailAddresses = adminMailAddresses;
	}



	
	public String getOwnerMailAddresses() {
	
		return ownerMailAddresses;
	}

	
	public void setOwnerMailAddresses(String ownerMailAddresses) {
	
		this.ownerMailAddresses = ownerMailAddresses;
	}

	public String getMailSmtpAuth() {
	
		return mailSmtpAuth;
	}

	
	public void setMailSmtpAuth(String mailSmtpAuth) {
	
		this.mailSmtpAuth = mailSmtpAuth;
	}

	public String getMailHostPort() {
	
		return mailHostPort;
	}

	
	public void setMailSmtpStarttls(String mailSmtpStarttls) {
	
		this.mailSmtpStarttls = mailSmtpStarttls;
	}
	public String getMailSmtpStarttls() {
	
		return mailSmtpStarttls;
	}

	
	public void setMailHostPort(String mailHostPort) {
	
		this.mailHostPort = mailHostPort;
	}
}
