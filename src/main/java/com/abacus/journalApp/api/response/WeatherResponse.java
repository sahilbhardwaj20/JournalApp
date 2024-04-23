package com.abacus.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherResponse {
    public Location location;
    public Current current;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Current{
        //@JsonProperty("condition")
        //public Condition condition;
        @JsonProperty("temp_c")
        public double tempC;
        public int humidity;
        public int cloud;
        @JsonProperty("feelslike_c")
        public double feelslikeC;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Location{
        public String name;
        public String region;
        public String country;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Condition{
        @JsonProperty("text")
        public String text;
        @JsonProperty("icon")
        public String icon;
    }
}