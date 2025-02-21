package com.marketing.campaign.api.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SEGMENTS")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SegmentEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2387150917694284224L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "SEGMENT_NAME")
    private String segmentName;

    @Basic
    @Column(name = "SEGMENT_DESCRIPTION")
    private String segmentDescription;

}
