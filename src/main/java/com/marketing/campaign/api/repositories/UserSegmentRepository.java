package com.marketing.campaign.api.repositories;


import com.marketing.campaign.api.model.UserSegmentEntity;
import com.marketing.campaign.api.model.UserSegmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSegmentRepository extends JpaRepository<UserSegmentEntity, UserSegmentId> {
    List<UserSegmentEntity> findBySegmentEntity_Id(Long segmentEntityId);
}


