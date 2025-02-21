package com.marketing.campaign.api.controllers;


import com.marketing.campaign.api.services.MailChimpService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailchimpController {

    private final MailChimpService mailChimpService;

    public MailchimpController(MailChimpService mailChimpService) {
        this.mailChimpService = mailChimpService;
    }

    @PostMapping("/api/mc/v1/mail/send-email/{segmentId}")
    public void sendEmailToSegment(@PathVariable Long segmentId) {
        mailChimpService.sendEmailToSegment(segmentId);
    }
}
