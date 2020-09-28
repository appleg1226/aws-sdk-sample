package com.chong.awssdksample.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Log
@Service
@Scope("prototype")
public class S3ClientConnect {

    private AmazonS3 s3Client;

    public void setupReceiver(Credentials credentials) {
        if (s3Client == null) {
            // S3 client
            s3Client = AmazonS3ClientBuilder.standard()
                    .withEndpointConfiguration(
                            new AwsClientBuilder.EndpointConfiguration(credentials.getEndPoint(), credentials.getRegionName()))
                    .withCredentials(new AWSStaticCredentialsProvider(
                            new BasicAWSCredentials(credentials.getAccessKey(), credentials.getSecretKey())))
                    .build();
            // log.info("connection to s3 instance is done! - " + s3Client.toString());
        }
    }

    public void closeConnection() {
        s3Client.shutdown();
    }

    public AmazonS3 getS3Client() {
        return s3Client;
    }
}
