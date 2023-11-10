package com.example.weblottery.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({"totalCount"})
public class PortfolioReport {

    private int totalCount;
    @JsonProperty("first_place_count")
    private int firstPlaceCount;
    @JsonProperty("second_place_count")
    private int secondPlaceCount;
    @JsonProperty("third_place_count")
    private int thirdPlaceCount;
    @JsonProperty("fourth_place_count")
    private int fourthPlaceCount;
    @JsonProperty("fifth_place_count")
    private int fifthPlaceCount;
    @JsonProperty("profit_rate")
    private double profitRate;

    public PortfolioReport() {
        this.totalCount = 0;
        this.firstPlaceCount = 0;
        this.secondPlaceCount = 0;
        this.thirdPlaceCount = 0;
        this.fourthPlaceCount = 0;
        this.fifthPlaceCount = 0;
        this.profitRate = 0.0;
    }

    public void winFirstPlace() {
        this.firstPlaceCount++;
    }

    public void winSecondPlace() {
        this.secondPlaceCount++;
    }

    public void winThirdPlace() {
        this.thirdPlaceCount++;
    }

    public void winFourthPlace() {
        this.fourthPlaceCount++;
    }

    public void winFifthPlace() {
        this.fifthPlaceCount++;
    }

    public void setProfitRate(double rate) {
        this.profitRate = rate;
    }

    public int getFirstPlaceCount() {
        return firstPlaceCount;
    }

    public int getSecondPlaceCount() {
        return secondPlaceCount;
    }

    public int getThirdPlaceCount() {
        return thirdPlaceCount;
    }

    public int getFourthPlaceCount() {
        return fourthPlaceCount;
    }

    public int getFifthPlaceCount() {
        return fifthPlaceCount;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
