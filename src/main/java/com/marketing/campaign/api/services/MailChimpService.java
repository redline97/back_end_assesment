package com.marketing.campaign.api.services;


import com.marketing.campaign.api.config.MailchimpDataConfig;
import com.marketing.campaign.api.model.UserEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class MailChimpService {

    private final UserSegmentService segmentService;
    private final MailchimpDataConfig mailchimpConfig;
    private final RestTemplate restTemplate;

    public MailChimpService(UserSegmentService segmentService,
                            MailchimpDataConfig mailchimpConfig,
                            RestTemplate restTemplate) {
        this.segmentService = segmentService;
        this.mailchimpConfig = mailchimpConfig;
        this.restTemplate = restTemplate;
    }


    public void sendEmailToSegment(Long segmentId) {
        List<UserEntity> userEntities = segmentService.getUsersBySegmentId(segmentId);
        userEntities.forEach(userEntity -> {
            // 1.Create a campaign for each user
            String campaignId = createCampaign(userEntity.getUserEmail());

            // 2.Send the campaign
            sendCampaign(campaignId);
        });
    }

    private String createCampaign(String email) {
        String url = mailchimpConfig.getApiUrl() + "campaigns";

        // Request body to create the campaign for the user
        HttpEntity<String> entity = getStringHttpEntity(email);
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, entity, Map.class);

        // Handle response and get campaign ID
        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> data = response.getBody();
            return (String) data.get("id"); // Return campaign ID
        } else {
            throw new RuntimeException("Failed to create campaign: " + response.getBody());
        }
    }

    private HttpEntity<String> getStringHttpEntity(String email) {
        String subject = "Exciting News from Our Company!";
        String content = "<html>"
                + "<body>"
                + "<h1>Welcome to Our Newsletter!</h1>"
                + "<p>We have some exciting updates and offers for you. Stay tuned for more!</p>"
                + "<p>Best regards,<br>Your Company Team</p>"
                + "</body>"
                + "</html>";


        String body = "{ \"type\": \"regular\", "
                + "\"recipients\": { "
                + "\"list_id\": \"your_list_id\", "
                + "\"segment_opts\": { \"match\": \"all\" }, "
                + "\"emails\": [ { \"email\": \"" + email + "\" } ] "
                + "}, "
                + "\"settings\": { \"subject_line\": \"" + subject + "\", \"title\": \"" + subject + "\", "
                + "\"from_name\": \"Your Name\", \"reply_to\": \"your-email@example.com\" }, "
                + "\"content\": { \"html\": \"" + content + "\" } }";


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "apikey " + mailchimpConfig.getApiKey());
        headers.set("Content-Type", "application/json");

        return new HttpEntity<>(body, headers);
    }


    private void sendCampaign(String campaignId) {
        String url = mailchimpConfig.getApiUrl() + "campaigns/" + campaignId + "/actions/send";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "apikey " + mailchimpConfig.getApiKey());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Campaign sent successfully");
        } else {
            System.out.println("Error sending campaign: " + response.getBody());
        }
    }
}
