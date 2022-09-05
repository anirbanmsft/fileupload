package com.azure.fileupload.helper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "azure")
public class StorageSasProperty {
	
	private String storageAccountName;
	private String containerSasToken;
	private String containerName;
	private String blobEndpoint;

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
	public String getBlobEndpoint() {
		return blobEndpoint;
	}
	public void setBlobEndpoint(String blobEndpoint) {
		this.blobEndpoint = blobEndpoint;
	}
}
