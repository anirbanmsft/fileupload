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

import com.azure.core.credential.AzureSasCredential;
import com.azure.fileupload.helper.HashCreator;
import com.azure.fileupload.helper.StorageSasProperty;
import com.azure.fileupload.model.ContainerBlobResponse;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;

@Service
public class BlobServiceImpl implements BlobService {
 
    @Autowired
    private StorageSasProperty storageSasProperty;
    
    @Autowired
	private ConverterService mp4ToAviConverterServiceImpl;
    
    Logger logger = LogManager.getLogger(BlobServiceImpl.class);

	@Override
	public ContainerBlobResponse writeBlobFile(MultipartFile file) throws Exception {
		

		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	String blobname = HashCreator.getFileChecksum(md5Digest, timeStamp + file.getOriginalFilename()) + ".avi";
    	
    	logger.info("azure.storage.blob-name: " + blobname);
    	
        BlobClient blob;
		try {
			String blobEndpoint = String.format("https://%s.blob.core.windows.net/%s/%s", 
					storageSasProperty.getStorageAccountName(), 
					storageSasProperty.getContainerName(),
					blobname);
			logger.info("blobEndpoint name: " + blobEndpoint);
			blob = new BlobClientBuilder()
					.endpoint(blobEndpoint)
					.credential(new AzureSasCredential(storageSasProperty.getContainerSasToken()))
					.buildClient();

			logger.info("MultipartFile name: " + file.getOriginalFilename());
			logger.info("MultipartFile Extension: " + FilenameUtils.getExtension(file.getOriginalFilename()));
			if(FilenameUtils.getExtension(file.getOriginalFilename()).equalsIgnoreCase("mp4")) {
				File aviFile = mp4ToAviConverterServiceImpl.convert(file, blobname);
				logger.info("AVI File name: " + aviFile.getName());
				logger.info("AVI File path: " + aviFile.toPath());
				blob.upload(FileUtils.openInputStream(aviFile), Files.size(aviFile.toPath()), true);
				aviFile.delete();
			} else {
				blob.upload(file.getInputStream(), file.getSize(), true);
			}
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		ContainerBlobResponse response = new ContainerBlobResponse(storageSasProperty.getStorageAccountName(), 
				storageSasProperty.getContainerName(), 
				blob.getBlobUrl());
		logger.info("ContainerBlobResponse" + response);
        return response;
	}

}
