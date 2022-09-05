package com.azure.fileupload.service;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

@Service
public class Mp4ToAviConverterServiceImpl implements ConverterService {
	
	Logger logger = LogManager.getLogger(Mp4ToAviConverterServiceImpl.class);

	@Override
	public File convert(MultipartFile file, String blobname) throws Exception {
		
		File sourceFile = new File("/tmp/" + file.getOriginalFilename());
		File targetFile = new File(blobname + ".avi");
		
		AudioAttributes audio = new AudioAttributes();
        audio.setCodec("aac");
        audio.setBitRate(64000);
        audio.setChannels(2);
        audio.setSamplingRate(44100);

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setInputFormat("mp4");
        attrs.setAudioAttributes(audio);
        
        try {
        	file.transferTo(sourceFile);
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(sourceFile), targetFile, attrs);
        } catch (Exception e) {
        	logger.error(e);
			throw e;
        }
		return targetFile;
	}

}
