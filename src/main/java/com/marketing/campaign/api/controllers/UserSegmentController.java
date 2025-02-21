package com.marketing.campaign.api.controllers;


import com.marketing.campaign.api.model.UserEntity;
import com.marketing.campaign.api.services.UserSegmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserSegmentController {

    private final UserSegmentService segmentService;

    public UserSegmentController(UserSegmentService userSegmentRepository) {
        this.segmentService = userSegmentRepository;
    }


    @GetMapping("/api/mc/v1/user/by/segment/{segmentId}")
    public ResponseEntity<List<UserEntity>> listAllSegments(@PathVariable Long segmentId) {
        List<UserEntity> userEntities = segmentService.getUsersBySegmentId(segmentId);
        return ResponseEntity.ok(userEntities);
    }
}
