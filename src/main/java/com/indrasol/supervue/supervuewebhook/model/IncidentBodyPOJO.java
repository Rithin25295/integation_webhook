package com.indrasol.supervue.supervuewebhook.model;


import lombok.*;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class IncidentBodyPOJO {

    private String IncidentDate;
    private String IncidentSummary;
    private String IncidentDescription;
    private String LocationTypeCode;
    private String ReptrSpecificLocation;
    private String ReporterTypeCode;
    private String EmployeeId;
    private String LocationId;
    private String IncidentTypeCode;
    private String SeverityLevelCode;
    private String NotifiedPersonId;
    private String IncidentReportedDate;
    private String IncidentSourceCode;
    private List<EventCode> IncidentDetailKiosk;
   // private List<Link> links;
}
