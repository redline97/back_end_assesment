package com.marketing.campaign.api.controllers;


import com.marketing.campaign.api.domain.SegmentDTO;
import com.marketing.campaign.api.model.SegmentEntity;
import com.marketing.campaign.api.services.SegmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SegmentController {

    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }


    @GetMapping("/api/mc/v1/segments")
    public ResponseEntity<List<SegmentEntity>> listAllSegments() {
        return segmentService.listAll();
    }

    @PostMapping("/api/mc/v1/segments/save")
    public ResponseEntity<String> createSegment(@RequestBody SegmentDTO segment) {

        if (segment.getName() == null || segment.getName().isEmpty()) {
            return new ResponseEntity<>("Missing segment name", HttpStatus.BAD_REQUEST);
        }
        try {
            return segmentService.createSegmentEntity(segment);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/mc/v1/segments/update/{segmentId}")
    public ResponseEntity<String> updateSegment(@PathVariable Long segmentId,
                                                @RequestBody SegmentDTO segment) {

        if (segment.getName() == null || segment.getName().isEmpty() || segmentId == null || segmentId <= 0) {
            return new ResponseEntity<>("Missing segment name & Id", HttpStatus.BAD_REQUEST);
        }

        try {
            return segmentService.updateSegmentEntity(segmentId, segment);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/mc/v1/segments/delete/{segmentId}")
    public ResponseEntity<String> deleteSegment(@PathVariable Long segmentId) {

        if (segmentId == null || segmentId <= 0) {
            return new ResponseEntity<>("Missing segment id", HttpStatus.BAD_REQUEST);
        }
        try {
            return segmentService.deleteSegment(segmentId);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
