package com.ssafy.Dreamy.model.service;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class ImageServiceImpl implements ImageService {

	private AmazonS3 s3Client;

	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;

	@Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	@Value("${cloud.aws.region.static}")
	private String region;

	@PostConstruct
	public void setS3Client() throws Exception {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

	    s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
	        .withRegion(this.region).build();

	}

	@Override
	public String upload(MultipartFile file, String typeString) throws Exception {
		String fileName = file.getOriginalFilename();
		int nameLen = fileName.length();
		int index = fileName.lastIndexOf('.');

		String subFileName = fileName.substring(0, index) + "_" + LocalDateTime.now();
		String subFileExtension = fileName.substring(index, nameLen);
		String newFileName = typeString + "/" + subFileName + subFileExtension;

		System.out.println("newFileName : " + newFileName);
	    // + date.format(new Date());

	    // key가 존재하면 기존 파일은 삭제
	   /* if ("".equals(currentFilePath) == false && currentFilePath != null) {
	      boolean isExistObject = s3Client.doesObjectExist(bucket, currentFilePath);

	      if (isExistObject == true) {
	        s3Client.deleteObject(bucket, currentFilePath);
	      }
	    }*/

	    // 파일 업로드
	    s3Client.putObject(new PutObjectRequest(bucket, newFileName, file.getInputStream(), null)
	        .withCannedAcl(CannedAccessControlList.PublicRead));

	    return newFileName;
	}


	/*@Override
	public String upload(MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();

	    s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
	        .withCannedAcl(CannedAccessControlList.PublicRead));

	    return fileName;
	}*/

}
