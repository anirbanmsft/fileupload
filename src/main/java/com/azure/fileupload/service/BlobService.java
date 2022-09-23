package com.azure.fileupload.service;

import org.springframework.web.multipart.MultipartFile;

import com.azure.fileupload.model.ContainerBlobResponse;


public interface BlobService {
	public ContainerBlobResponse writeBlobFile(MultipartFile file) throws Exception;
}
