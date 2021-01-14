package com.indrasol.supervue.supervuewebhook.model;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Link {

    private String href;
    private String rel;
}
