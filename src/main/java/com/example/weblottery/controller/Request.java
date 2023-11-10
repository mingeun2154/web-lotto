package com.example.weblottery.controller;

import com.example.weblottery.common.MultiplePureNumbers;
import com.example.weblottery.common.PureNumber;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@JsonIgnoreProperties({"purchaseAmount", "winningNumbers", "bonusNumber"})
public class Request implements Serializable {

    private PureNumber purchaseAmount;
    private MultiplePureNumbers winningNumbers;
    private PureNumber bonusNumber;

    @JsonProperty("purchase_amount")
    private String rawPurchaseAmount;

    @JsonProperty("winning_numbers")
    private String rawWinningNumbers;

    @JsonProperty("bonus_number")
    private String rawBonusNumber;

    public Request() { }

    public void setRawPurchaseAmount(String rawPurchaseAmount) {
        this.rawPurchaseAmount = rawPurchaseAmount;
    }

    public void setRawWinningNumbers(String rawWinningNumbers) {
        this.rawWinningNumbers = rawWinningNumbers;
    }

    public void setRawBonusNumber(String rawBonusNumber) {
        this.rawBonusNumber = rawBonusNumber;
    }

    public PureNumber getPurchaseAmount() {
        return PureNumber.wrap(rawPurchaseAmount);
    }

    public MultiplePureNumbers getWinningNumbers() {
        return MultiplePureNumbers.wrap(rawWinningNumbers);
    }

    public PureNumber getBonusNumber() {
        return PureNumber.wrap(rawBonusNumber);
    }
}
