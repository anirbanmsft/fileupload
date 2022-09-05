package com.azure.fileupload.model;

public class ContainerBlobResponse {

	private String accountName;
	private String containerSasToken;
	private String containerName;
	private String blobEndpoint;
	private String BlobURL;
	public ContainerBlobResponse(String accountName, String accountKey, String containerName, String blobEndpoint,
			String blobURL) {
		super();
		this.accountName = accountName;
		this.containerSasToken = accountKey;
		this.containerName = containerName;
		this.blobEndpoint = blobEndpoint;
		BlobURL = blobURL;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	public String getBlobURL() {
		return BlobURL;
	}
	public void setBlobURL(String blobURL) {
		BlobURL = blobURL;
	}
	@Override
	public String toString() {
		return "ContainerBlobResponse [accountName=" + accountName + ", containerName=" + containerName
				+ ", blobEndpoint=" + blobEndpoint + ", BlobURL=" + BlobURL + "]";
	}
	
}
