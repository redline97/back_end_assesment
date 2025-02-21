package com.marketing.campaign.api.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserSegmentId implements Serializable {

    @Serial
    private static final long serialVersionUID = 3606845304690772657L;

    private Long userId;
    private Long segmentId;
}
