package com.marketing.campaign.api.services;

import com.marketing.campaign.api.model.UserEntity;
import com.marketing.campaign.api.model.UserSegmentEntity;
import com.marketing.campaign.api.repositories.UserSegmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSegmentService {

    private final UserSegmentRepository userSegmentRepository;

    public UserSegmentService(UserSegmentRepository userSegmentRepository) {
        this.userSegmentRepository = userSegmentRepository;
    }

    public List<UserEntity> getUsersBySegmentId(Long segmentId) {
        List<UserSegmentEntity> associations = userSegmentRepository.findBySegmentEntity_Id(segmentId);

        return associations.stream()
                .map(UserSegmentEntity::getUserEntity)
                .collect(Collectors.toList());
    }
}
