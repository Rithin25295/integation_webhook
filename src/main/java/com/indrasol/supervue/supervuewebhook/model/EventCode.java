package com.indrasol.supervue.supervuewebhook.model;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class EventCode {

    private String IncidentEventCode;
    private String IncidentEventSummary;
}
