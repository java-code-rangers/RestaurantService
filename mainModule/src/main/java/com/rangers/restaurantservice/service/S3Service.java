package com.rangers.restaurantservice.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class S3Service {
    private final Regions regions = Regions.EU_NORTH_1;
    @Value("${aws.bucketName}")
    private String bucketName;
    @Value("${aws.accessKey}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secretKey;

    public void uploadToS3(InputStream inputStream, String filename)
            throws IOException, SdkClientException {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(regions)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/jpeg");
        metadata.setContentLength(inputStream.available());

        PutObjectRequest request = new PutObjectRequest(bucketName, filename, inputStream, metadata);

        s3Client.putObject(request);
    }
}
