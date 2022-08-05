package com.demo.awss3wrapperjar.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.dinakaran.awss3wrapper.domain.S3File;
import com.dinakaran.awss3wrapper.upload.S3Client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
public class S3controller {

	@Autowired
	S3Client s3Client;

	@PostMapping("/upload")
	public ResponseEntity<Object> save(@RequestParam("file") MultipartFile multipartFile)
			throws AmazonServiceException, SdkClientException, IOException {
		return new ResponseEntity<>(s3Client.uploadFile(multipartFile), HttpStatus.OK);
	}

	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<StreamingResponseBody> export(@RequestParam String filePath)
			throws Exception {
		long start = System.currentTimeMillis();
		S3File s3File = s3Client.downloadFile(filePath);
		HttpHeaders responseHeaders = new HttpHeaders();
		StreamingResponseBody responseBody = null;
		responseHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + s3File.getFileName());
		responseHeaders.set("Access-Control-Expose-Headers", "Content-Disposition");
		responseBody = outputStream -> {
			try (InputStream inputStream = s3File.getFileContent()) {
				int numberOfBytesToWrite;
				byte[] data = new byte[1024];
				while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
					outputStream.write(data, 0, numberOfBytesToWrite);
				}
				inputStream.close();
			}
		};
		long end = System.currentTimeMillis();
		log.info("Time taken to download" + ((end - start) % 60000) + " minutes");
		return ResponseEntity.ok().headers(responseHeaders).contentType(MediaType.APPLICATION_OCTET_STREAM)
				.body(responseBody);
	}

}