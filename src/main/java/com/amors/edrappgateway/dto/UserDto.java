package com.amors.edrappgateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonIgnore
    private Long id;

    private String username;
    private String email;
    private Integer age;
}
