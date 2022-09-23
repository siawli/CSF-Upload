package CSF.Upload.uploadFileServer.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.partitions.model.Endpoint;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfig {

    @Value("${accessKey}")
    private String accessKey;

    @Value("${secretKey}")
    private String secretKey;

    @Bean
    public AmazonS3 getS3Client() {
        BasicAWSCredentials cred = new BasicAWSCredentials(accessKey, secretKey);

        EndpointConfiguration epConfig = new EndpointConfiguration(
            "sgp1.digitaloceanspaces.com/", "spg1");

        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(epConfig)
                .withCredentials(new AWSStaticCredentialsProvider(cred))
                .build();
    }
    
}
