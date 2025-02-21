package com.marketing.campaign.api.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
@Data
public class MailchimpDataConfig {

    @Value("${mailchimp.api.key}")
    private String apiKey;

    @Value("${mailchimp.api.url}")
    private String apiUrl;
}
