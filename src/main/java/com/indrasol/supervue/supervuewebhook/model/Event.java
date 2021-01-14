package com.indrasol.supervue.supervuewebhook.model;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@With
public class Event {


    private String eventCategory;
    private String camId;
    private String eventTimestamp;
    private String totalPersons;
    private String lowRiskPersons;
    private String mediumRiskPersons;
    private String highRiskPersons;

}
