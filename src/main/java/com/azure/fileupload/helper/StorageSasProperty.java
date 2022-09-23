package com.azure.fileupload.helper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "azure")
@PropertySource("classpath:access.properties")
public class StorageSasProperty {
	
	private String storageAccountName;
	private String containerSasToken;
	private String containerName;

	public String getStorageAccountName() {
		return storageAccountName;
	}
	public void setStorageAccountName(String storageAccountName) {
		this.storageAccountName = storageAccountName;
	}
	public String getContainerSasToken() {
		return containerSasToken;
	}
	public void setContainerSasToken(String containerSasToken) {
		this.containerSasToken = containerSasToken;
	}
	public String getContainerName() {
		return containerName;
	}
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
}
