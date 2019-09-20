package com.spring.aws.demo;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsBootstrapConfiguration {

  @Bean
  AWSSecretsManager awsSecretsManager(
      @Value("${cloud.aws.region.static}") String region,
      AWSCredentialsProvider awsCredentialsProvider) {
    AWSSecretsManagerClientBuilder builder = AWSSecretsManagerClientBuilder.standard();
    builder.setRegion(region);
    builder.setCredentials(awsCredentialsProvider);
    return builder.build();
  }

  @Bean
  AWSCredentialsProvider awsCredentialsProvider(
      @Value("${cloud.aws.credentials.accessKey}") String accessKey,
      @Value("${cloud.aws.credentials.secretKey}") String secretKey) {
    return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
  }
}
