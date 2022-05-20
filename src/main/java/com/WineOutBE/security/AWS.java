package com.WineOutBE.security;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AWS {

    AWSCredentials credentials = new BasicAWSCredentials(
            "AKIA5MCS6GPMXD4ZC5KO",
            "68FfIarE2TN54R/royij5rW+gM1wda306ovg6V5F"
    );

    AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion("US East (N. Virginia) us-east-1")
            .build();
}
