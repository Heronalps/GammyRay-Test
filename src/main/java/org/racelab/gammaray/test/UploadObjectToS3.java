package org.racelab.gammaray.test;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.ByteArrayInputStream;
import java.io.IOException;


/**
 * Created by michaelzhang on 9/6/17.
 */
public class UploadObjectToS3 {
    private static String bucketName     = "gammaray-test";
    private static String keyName        = "racelab001";
    private static String uploadFileName = "GammayRayTestFile";

    public void handler(String str, Context context) throws IOException{
        AmazonS3 s3client = new AmazonS3Client();
        try {
            // Upload file
            ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
            s3client.putObject(bucketName, uploadFileName, inputStream, new ObjectMetadata());

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }

}
