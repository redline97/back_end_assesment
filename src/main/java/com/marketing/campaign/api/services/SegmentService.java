package com.marketing.campaign.api.services;


import com.marketing.campaign.api.domain.SegmentDTO;
import com.marketing.campaign.api.model.SegmentEntity;
import com.marketing.campaign.api.repositories.SegmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SegmentService {

    private final SegmentRepository segmentRepository;

    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }


    @Transactional(
            value = "transactionManager",
            rollbackFor = {Exception.class},
            readOnly = true
    )
    public ResponseEntity<List<SegmentEntity>> listAll() {
        List<SegmentEntity> segmentServices = segmentRepository.findAll();
        return ResponseEntity.ok(segmentServices);
    }

    @Transactional(
            transactionManager = "transactionManager",
            rollbackFor = {Exception.class}
    )
    public ResponseEntity<String> createSegmentEntity(SegmentDTO segmentDetails) {
        SegmentEntity segmentEntity = new SegmentEntity();
        segmentEntity.setSegmentName(segmentDetails.getName());
        segmentEntity.setSegmentDescription(segmentDetails.getEmail());
        segmentRepository.save(segmentEntity);
        return ResponseEntity.ok("Segment created successfully!!");
    }

    @Transactional(
            transactionManager = "transactionManager",
            rollbackFor = {Exception.class}
    )
    public ResponseEntity<String> updateSegmentEntity(Long segmentId, SegmentDTO segmentDetails) {
        SegmentEntity segmentEntity = segmentRepository.findById(segmentId).orElse(null);
        if (segmentEntity == null) {
            return new ResponseEntity<>("Segment not found!", HttpStatus.NOT_FOUND);
        }
        segmentEntity.setSegmentName(segmentDetails.getName());
        segmentEntity.setSegmentDescription(segmentDetails.getEmail());
        segmentRepository.save(segmentEntity);
        return ResponseEntity.ok("Segment updated successfully!!");

    }


    @Transactional(
            transactionManager = "transactionManager",
            rollbackFor = {Exception.class}
    )
    public ResponseEntity<String> deleteSegment(Long segmentId) {
        SegmentEntity segmentEntity = segmentRepository.findById(segmentId).orElse(null);
        if (segmentEntity == null) {
            return new ResponseEntity<>("Segment not found!", HttpStatus.NOT_FOUND);
        }
        segmentRepository.deleteById(segmentId);
        return ResponseEntity.ok("Segment deleted successfully!!");
    }
}
