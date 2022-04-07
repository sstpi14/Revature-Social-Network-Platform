package com.revature.EnergySocialNetwork.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class S3Service {
    private String awsId = "AKIARSHEV3QQU6JBBSUL";
    private String secretKey = "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";
    private String region = "us-east-1";
    private String bucketName = "energy-social-network-bucket";

    public void uploadFile(File file) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsId, secretKey);

        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        s3Client.putObject(bucketName, "images/" + file.getName(), file);
    }
}
