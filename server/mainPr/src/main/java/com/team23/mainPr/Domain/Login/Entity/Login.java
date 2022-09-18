package com.team23.mainPr.Domain.Login.Entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Data
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer loginId;
    Integer memberId;
    String token;
    ZonedDateTime lastLoginDt;
    ZonedDateTime logoutDt;
    Boolean logouted;

}
