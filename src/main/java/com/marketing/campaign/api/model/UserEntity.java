package com.marketing.campaign.api.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "USERS")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = -6619632621197423589L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "USER_NAME")
    private String username;

    @Basic
    @Column(name = "USER_EMAIL")
    private String userEmail;


}
