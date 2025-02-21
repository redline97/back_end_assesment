package com.marketing.campaign.api.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data

public class SegmentDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5708157409222722489L;

    private String name;
    private String email;
}
