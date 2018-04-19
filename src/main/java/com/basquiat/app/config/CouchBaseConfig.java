package com.basquiat.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

/**
 * 
 * CouchBaseConfig
 * 
 * create by basquiat 2018.04.19
 *
 */
@Configuration
public class CouchBaseConfig {

    @Value("${couchbase.bucket.name}")
    private String bucketName;

    @Value("${couchbase.bucket.password}")
    private String bucketPassword;

    @Value("${couchbase.bucket.ip}")
    private String bucketIp;
    
    @Autowired
    private Cluster couchbaseCluster;

    public @Bean Cluster cluster() {
    	System.setProperty("com.couchbase.queryEnabled", "true");
        return CouchbaseCluster.create(bucketIp);
    }
    
    public @Bean Bucket connectBucket() {
        return couchbaseCluster.openBucket(bucketName, bucketPassword);
    }
    
}
