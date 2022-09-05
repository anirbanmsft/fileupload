package com.azure.fileupload.service;

import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.fileupload.helper.HashCreator;
import com.azure.fileupload.helper.StorageProperty;
import com.azure.fileupload.model.ContainerBlobResponse;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

@Service
public class BlobServiceImpl implements BlobService {
    
    @Autowired
    private StorageProperty storageProperty;
    
    @Autowired
	private ConverterService mp4ToAviConverterServiceImpl;
    
    Logger logger = LogManager.getLogger(BlobServiceImpl.class);

	@Override
	public ContainerBlobResponse writeBlobFile(MultipartFile file) throws Exception {
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	String blobname = HashCreator.getFileChecksum(md5Digest, timeStamp + file.getOriginalFilename());
    	
    	logger.info("azure.storage.account-name: " + storageProperty.getAccountName());
    	logger.info("azure.storage.container-name: " + storageProperty.getContainerName());
    	logger.info("azure.storage.blob-name: " + blobname);
        
    	String connectionString = "AccountName=" +  storageProperty.getAccountName() +
    					";AccountKey=" + storageProperty.getAccountKey() +
    					";BlobEndpoint=" + storageProperty.getBlobEndpoint() +
    					";DefaultEndpointsProtocol=https;";
    	
        BlobClient blob;
		try {
			
			BlobContainerClient container = new BlobContainerClientBuilder()
					.connectionString(connectionString)
					.containerName(storageProperty.getContainerName())
					.buildClient();
			logger.info("container.getBlobContainerName: " + container.getBlobContainerName());
			logger.info("container.getBlobContainerUrl: " + container.getBlobContainerUrl());
			logger.info("MultipartFile name: " + file.getOriginalFilename());
			logger.info("MultipartFile Extension: " + FilenameUtils.getExtension(file.getOriginalFilename()));
			blob = container.getBlobClient(blobname);
			if(FilenameUtils.getExtension(file.getOriginalFilename()).equalsIgnoreCase("mp4")) {
				File aviFile = mp4ToAviConverterServiceImpl.convert(file, blobname);
				logger.info("AVI File name: " + aviFile.getName());
				logger.info("AVI File path: " + aviFile.toPath());
				blob.upload(FileUtils.openInputStream(aviFile), Files.size(aviFile.toPath()), true);
			} else {
				blob.upload(file.getInputStream(), file.getSize(), true);
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		ContainerBlobResponse response = new ContainerBlobResponse(storageProperty.getAccountName(), 
				storageProperty.getAccountKey(), 
				storageProperty.getContainerName(), 
				storageProperty.getBlobEndpoint(), 
				blob.getBlobUrl());
		logger.info("ContainerBlobResponse" + response);
        return response;
	}

}
