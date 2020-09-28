package com.chong.awssdksample.aws;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credentials {

    @Builder
    public Credentials(String endPoint, String regionName, String accessKey, String secretKey) {
        this.endPoint = endPoint;
        this.regionName = regionName;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    private String endPoint;
    private String regionName;
    private String accessKey;
    private String secretKey;
}
