package com.example.weblottery.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    @JsonProperty("result")
    @JsonInclude(Include.NON_NULL)
    private PortfolioReport report;

    @JsonProperty("error_msg")
    @JsonInclude(Include.NON_NULL)
    private String errMsg;

    public Response(PortfolioReport report, String errMsg) {
        this.report = report;
        this.errMsg = errMsg;
    }
}
