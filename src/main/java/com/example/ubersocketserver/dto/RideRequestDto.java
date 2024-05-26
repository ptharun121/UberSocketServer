package com.example.ubersocketserver.dto;

import com.example.ubersocketserver.models.ExactLocation;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {

    private long passengerId;

    private ExactLocation startLocation;

    private ExactLocation endLocation;

    private List<Long> driverIds;
}
