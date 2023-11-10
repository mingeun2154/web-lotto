package com.example.weblottery;

import static com.example.weblottery.core.DrawResult.FIFTH_PRIZE;
import static com.example.weblottery.core.DrawResult.FIRST_PRIZE;
import static com.example.weblottery.core.DrawResult.FOURTH_PRIZE;
import static com.example.weblottery.core.DrawResult.NO_PRIZE;
import static com.example.weblottery.core.DrawResult.SECOND_PRIZE;
import static com.example.weblottery.core.DrawResult.THIRD_PRIZE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.weblottery.common.MultiplePureNumbers;
import com.example.weblottery.common.PureNumber;
import com.example.weblottery.core.BonusNumber;
import com.example.weblottery.core.DrawResult;
import com.example.weblottery.core.LotteryPortfolio;
import com.example.weblottery.core.LotteryTicket;
import com.example.weblottery.service.LottoService;
import java.text.DecimalFormat;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LotteryTicketServiceTest {

    static final List<Integer> winningNumbers = List.of(1, 2, 32, 43, 29, 45);
    static final LotteryTicket WINNING_LOTTERY_TICKET = new LotteryTicket(winningNumbers);
    static BonusNumber bonusNumber = new BonusNumber(WINNING_LOTTERY_TICKET, 25);
    static final LottoService service = new LottoService();

    @BeforeAll
    static void setWinninConditions() {
        service.setWinningNumbers(MultiplePureNumbers.wrap("1,2 , 32, 43,29, 45"));
        service.setBonusNumber(PureNumber.wrap(" 25"));
    }

    @DisplayName("당첨금 계산 (보너스 숫자 안 맞은 경우)")
    @Test
    void calculateWinningAmountWithoutBonusNumber() {
        // given
        LotteryTicket lotteryTicket1StPlace = new LotteryTicket(winningNumbers);
        LotteryTicket lotteryTicket3RdPlace = new LotteryTicket(List.of(45, 2, 43, 32, 1, 30));
        LotteryTicket lotteryTicket4ThPlace = new LotteryTicket(List.of(45, 29, 43, 32, 3, 30));
        LotteryTicket lotteryTicket5ThPlace = new LotteryTicket(List.of(1, 29, 43, 31, 3, 30));
        // when
        DrawResult actualResultOf1stPlaceLotto = service.checkResult(lotteryTicket1StPlace);
        DrawResult actualResultOf3rdPlaceLotto = service.checkResult(lotteryTicket3RdPlace);
        DrawResult actualResultOf4thPlaceLotto = service.checkResult(lotteryTicket4ThPlace);
        DrawResult actualResultOf5thPlaceLotto = service.checkResult(lotteryTicket5ThPlace);
        // then
        assertThat(actualResultOf1stPlaceLotto).isEqualTo(FIRST_PRIZE);
        assertThat(actualResultOf3rdPlaceLotto).isEqualTo(THIRD_PRIZE);
        assertThat(actualResultOf4thPlaceLotto).isEqualTo(FOURTH_PRIZE);
        assertThat(actualResultOf5thPlaceLotto).isEqualTo(FIFTH_PRIZE);
    }


    @DisplayName("당첨금 계산 (보너스 숫자가 맞은 경우)")
    @Test
    void calculateWinningAmountWithBonusNumber() {
        // given
        LotteryTicket lotteryTicket2NdPlace = new LotteryTicket(List.of(45, 2, 43, 32, 1, 25));
        LotteryTicket lotteryTicket4ThPlaceWithBonus = new LotteryTicket(List.of(45, 29, 43, 32, 3, 25));
        LotteryTicket lotteryTicket5ThPlaceWithBonus = new LotteryTicket(List.of(1, 29, 43, 31, 3, 25));
        // when
        DrawResult actualResultOf2ndPlaceLotto = service.checkResult(lotteryTicket2NdPlace);
        DrawResult actualResultOf4thPlaceWithBonusLotto = service.checkResult(lotteryTicket4ThPlaceWithBonus);
        DrawResult actualResultOf5thPlaceWithBonusLotto = service.checkResult(lotteryTicket5ThPlaceWithBonus);
        // then
        assertThat(actualResultOf2ndPlaceLotto).isEqualTo(SECOND_PRIZE);
        assertThat(actualResultOf4thPlaceWithBonusLotto).isEqualTo(FOURTH_PRIZE);
        assertThat(actualResultOf5thPlaceWithBonusLotto).isEqualTo(FIFTH_PRIZE);
    }

    @DisplayName("수익률 계산")
    @Test
    void calculateProfitRate() {
        // given
        LotteryPortfolio portfolio = new LotteryPortfolio();
        LotteryTicket lotteryTicket1StPlace = new LotteryTicket(winningNumbers);
        LotteryTicket lotteryTicket2NdPlace = new LotteryTicket(List.of(45, 2, 43, 32, 1, 25));
        LotteryTicket lotteryTicket3RdPlace = new LotteryTicket(List.of(45, 2, 43, 32, 1, 30));
        LotteryTicket lotteryTicket4ThPlace = new LotteryTicket(List.of(45, 29, 43, 32, 3, 30));
        LotteryTicket lotteryTicket5ThPlace = new LotteryTicket(List.of(1, 29, 43, 31, 3, 30));
        LotteryTicket lotteryTicketNoHit = new LotteryTicket(List.of(12, 27, 40, 31, 3, 25));
        // when
        portfolio.add(lotteryTicket1StPlace);
        portfolio.add(lotteryTicket2NdPlace);
        portfolio.add(lotteryTicket3RdPlace);
        portfolio.add(lotteryTicket4ThPlace);
        portfolio.add(lotteryTicket5ThPlace);
        portfolio.add(lotteryTicketNoHit);
        // then
        double expectedProfitRate = ((double) FIRST_PRIZE.getWinnings() + SECOND_PRIZE.getWinnings() +
                THIRD_PRIZE.getWinnings() + FOURTH_PRIZE.getWinnings() +
                FIFTH_PRIZE.getWinnings() + NO_PRIZE.getWinnings()) / 6_000 * 100;
        assertThat(portfolio.profitRate(WINNING_LOTTERY_TICKET, bonusNumber))
                .isEqualTo(roundToOneDecimalPlace(expectedProfitRate));
    }

    @DisplayName("큰 수의 당첨금에 대한 수익률 계산")
    @Test
    void calculateExtremeProfitRate() {
        // given
        LotteryPortfolio portfolio = new LotteryPortfolio();
        int firstPlaceCount = 999999;
        // when
        for (int i = 0; i < firstPlaceCount; i++) {
            portfolio.add(new LotteryTicket(winningNumbers));
        }
        // then
        double expectedProfitRate = ((double) (FIRST_PRIZE.getWinnings() * firstPlaceCount))
                / (1000 * firstPlaceCount) * 100.0;
        assertThat(portfolio.profitRate(WINNING_LOTTERY_TICKET, bonusNumber))
                .isEqualTo(roundToOneDecimalPlace(expectedProfitRate));
    }

    private double roundToOneDecimalPlace(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return Double.parseDouble(decimalFormat.format(number));
    }
}
