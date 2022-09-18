package com.team23.mainPr.Domain.Login.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer loginId;
    Integer memberId;
    String token;
    ZonedDateTime lastLoginDate;
    ZonedDateTime logoutDate;
    Boolean logouted;

}
