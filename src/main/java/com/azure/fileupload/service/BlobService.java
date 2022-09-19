package com.azure.fileupload.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.azure.fileupload.model.ContainerBlobResponse;


public interface BlobService {
	public ResponseEntity<ContainerBlobResponse> writeBlobFile(MultipartFile file) throws Exception;
}
