package com.chong.awssdksample.aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class BucketObjectUtil {

    public static List<Bucket> getBucketList(S3ClientConnect s3ClientConnect) throws AmazonClientException {
        log.info("getting bucket list....");
        return s3ClientConnect.getS3Client().listBuckets();
    }

    public static List<S3ObjectSummary> getObjectList(S3ClientConnect s3ClientConnect, String bucketName) throws
            AmazonClientException {
        log.info("Getting object List of <" + bucketName + ">");

        ObjectListing result = s3ClientConnect.getS3Client().listObjects(bucketName);
        List<S3ObjectSummary> keyList = new ArrayList<S3ObjectSummary>(result.getObjectSummaries());

        while (result.isTruncated()) {
            result = s3ClientConnect.getS3Client().listNextBatchOfObjects(result);
            keyList.addAll(result.getObjectSummaries());
        }

        return keyList;
    }

    public static Long getObjectLength(S3ClientConnect s3ClientConnect, String bucketName, String keyName) throws
            AmazonClientException {
        ObjectMetadata objectMetadata = s3ClientConnect.getS3Client().getObjectMetadata(bucketName, keyName);
        return objectMetadata.getContentLength();
    }

    public static long getAllObjectLengthSumInBucket(List<S3ObjectSummary> objList) {
        return objList.stream()
                .mapToLong(S3ObjectSummary::getSize)
                .sum();
    }
}
