package com.azure.fileupload.helper;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StorageSasProperty {

	@Value("${azure.storageAccountName}")
	private String storageAccountName;
	@Value("${azure.containerSasToken}")
	private String containerSasToken;
	@Value("${azure.containerName}")
	private String containerName;
	@Value("${azure.vaultUri}")
	private String vaultUri;

	@Value("${azure.keyvault.clientId}")
	private String clientId;

	@Value("${azure.keyvault.clientSecret}")
	private String clientSecret;

	@Value("${azure.keyvault.tenantId}")
	private String tenantId;

	public void setStorageAccountName(String storageAccountName) {
		this.storageAccountName = storageAccountName;
	}

	public void setContainerSasToken(String containerSasToken) {
		this.containerSasToken = containerSasToken;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

	public void setVaultUri(String vaultUri) {
		this.vaultUri = vaultUri;
	}

	public String getStorageAccountName() {
		return storageAccountName;
	}

	public String getContainerSasToken() {
		return containerSasToken;
	}

	public String getContainerName() {
		return containerName;
	}

	@PostConstruct
	public void getStorageConfigFromKeyVault() {
		SecretClient secretClient = new SecretClientBuilder()
				.vaultUrl(this.vaultUri)
				.credential(new ClientSecretCredentialBuilder().clientId(this.clientId).tenantId(this.tenantId).clientSecret(this.clientSecret).build())
				.buildClient();
		KeyVaultSecret storageAccount = secretClient.getSecret(this.storageAccountName);
		this.storageAccountName = storageAccount.getValue();
		KeyVaultSecret storageContainer = secretClient.getSecret(this.containerName);
		this.containerName = storageContainer.getValue();
		KeyVaultSecret sasTokenForContainer = secretClient.getSecret(this.containerSasToken);
		this.containerSasToken = sasTokenForContainer.getValue();
	}
}
