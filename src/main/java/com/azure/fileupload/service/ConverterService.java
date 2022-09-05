package com.azure.fileupload.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface ConverterService {

	public File convert(MultipartFile file, String blobname) throws Exception;
}
