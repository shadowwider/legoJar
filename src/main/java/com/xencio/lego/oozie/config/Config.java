package com.xencio.lego.oozie.config;

import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.io.InputStream;

public class Config {

	public static Config load() {

		Config config = new Config();

		InputStream inputStream = null;
		
			inputStream = Config.class.getResourceAsStream("/major-config.json");
			if (inputStream == null) {
				inputStream = new FileInputStream("major-config.json");
			}
			JSONObject.parseObject(inputStream,Config.class);
		
		return config;
	}

	private String hiveAddress;
	private String hiveUser;
	private String hivePsw;

	private String adminMailAddresses;
	private String senderMailAddress;
	private String ownerMailAddresses;
	private String mailContentTemplate;
	private String[] attachmentHeadArray;

	private String mailHost;
	private String mailProtocol;
	private String mailAccountName;
	private String mailAccountPsw;
	private String mailSourceAddress;


	public String getHiveAddress() {

		return hiveAddress;
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

	public void setHiveAddress(String hiveAddress) {

		this.hiveAddress = hiveAddress;
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
}
