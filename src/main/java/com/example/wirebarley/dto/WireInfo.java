package com.example.wirebarley.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class WireInfo {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("terms")
    private String terms;

    @JsonProperty("privacy")
    private String privacy;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("source")
    private String source;

    @JsonProperty("quotes")
    private Map<String, Double> quotes;
}
