package com.marketing.campaign.api.domain;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4713239037011893969L;

    private String name;
    private String email;
}
