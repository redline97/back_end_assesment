package com.marketing.campaign.api.repositories;


import com.marketing.campaign.api.model.SegmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepository extends JpaRepository<SegmentEntity, Long> {}
