package com.example.ubersocketserver.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExactLocation{

    private Double longitude;

    private Double latitude;
}
