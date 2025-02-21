package com.marketing.campaign.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "USER_SEGMENT")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSegmentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6619632621197423589L;

    @EmbeddedId
    private UserSegmentId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "FK_USER_ID", referencedColumnName = "ID")
    private UserEntity userEntity;


    @ManyToOne
    @MapsId("segmentId")
    @JoinColumn(name = "FK_SEGMENT_ID", referencedColumnName = "ID")
    private SegmentEntity segmentEntity;

}
