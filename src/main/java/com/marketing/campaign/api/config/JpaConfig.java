package com.marketing.campaign.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.marketing.campaign.api.repositories"
        }
)
@EntityScan(basePackages =
        {
                "com.marketing.campaign.api.model"
        })
@EnableTransactionManagement
public class JpaConfig {
}
