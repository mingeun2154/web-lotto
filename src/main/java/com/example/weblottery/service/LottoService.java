package com.example.weblottery.service;

import com.example.weblottery.common.MultiplePureNumbers;
import com.example.weblottery.common.PureNumber;
import com.example.weblottery.core.BonusNumber;
import com.example.weblottery.core.DrawResult;
import com.example.weblottery.core.LotteryPortfolio;
import com.example.weblottery.core.LotteryTicket;
import com.example.weblottery.controller.PortfolioReport;
import com.example.weblottery.core.PurchaseAmount;
import com.example.weblottery.core.RandomLotteryNumberProvider;
import org.springframework.stereotype.Service;

@Service
public class LottoService {

    private LotteryTicket winningLotteryTicket;
    private BonusNumber bonusNumber;
    private LotteryPortfolio portfolio;

    public LottoService() {
        portfolio = new LotteryPortfolio();
    }

    public LotteryPortfolio issueRequiredAmountOfLotteryTickets(PureNumber number) throws IllegalArgumentException {
        PurchaseAmount amount = PurchaseAmount.amountOf(number);
        for (int i = 0; i < amount.getTicketQuantity(); i++) {
            portfolio.add(new LotteryTicket(RandomLotteryNumberProvider.lotteryNumbers()));
        }
        return portfolio;
    }

    public void setWinningNumbers(MultiplePureNumbers numbers) throws IllegalArgumentException {
        winningLotteryTicket = LotteryTicket.create(numbers);
    }

    public void setBonusNumber(PureNumber number) throws IllegalArgumentException {
        bonusNumber = BonusNumber.create(winningLotteryTicket, number);
    }

    public DrawResult checkResult(LotteryTicket lotteryTicket) {
        return DrawResult.resultOf(lotteryTicket.countHitNumbers(this.winningLotteryTicket),
                containsBonusNumber(lotteryTicket));
    }

    public boolean containsBonusNumber(LotteryTicket lotteryTicket) {
        return lotteryTicket.containsBonusNumber(this.bonusNumber);
    }

    public PortfolioReport analyzePortfolio() {
        return portfolio.analyze(winningLotteryTicket, bonusNumber);
    }
}
