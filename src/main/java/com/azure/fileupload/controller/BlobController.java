package com.azure.fileupload.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.azure.fileupload.model.ContainerBlobResponse;
import com.azure.fileupload.service.BlobService;

@RestController
@RequestMapping("blob")
public class BlobController {

	@Autowired
	private BlobService blobService;
	
	Logger logger = LogManager.getLogger(BlobController.class);

	@PostMapping("/writeBlobFile")
	public ContainerBlobResponse writeBlobFile(@RequestParam("file") MultipartFile file) {
		try {
			if (file == null) {
				logger.error("You must select the a file for uploading");
				throw new RuntimeException("You must select the a file for uploading");
			}
			return blobService.writeBlobFile(file);
		}
		catch(Exception e) {
			logger.error(e, e.getCause());
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
	}
}