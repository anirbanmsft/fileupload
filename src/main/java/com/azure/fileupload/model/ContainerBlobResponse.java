package com.azure.fileupload.model;

public class ContainerBlobResponse {

	private String accountName;
	private String containerName;
	private String BlobURL;
	public ContainerBlobResponse(String accountName, String containerName, String blobURL) {
		super();
		this.accountName = accountName;
		this.containerName = containerName;
		this.BlobURL = blobURL;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
	public String getBlobURL() {
		return BlobURL;
	}
	public void setBlobURL(String blobURL) {
		BlobURL = blobURL;
	}
	@Override
	public String toString() {
		return "ContainerBlobResponse [accountName=" + accountName + ", containerName=" + containerName
				+ ", BlobURL=" + BlobURL + "]";
	}
	
}
