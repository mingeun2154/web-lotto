package com.example.weblottery.controller;

import com.example.weblottery.service.LottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lotto")
public class LottoMissionController {

    private LottoService service;

    @Autowired
    public LottoMissionController() {
        service = new LottoService();
    }

    @PostMapping("/draw")
    public Response draw(@RequestBody Request request) {
        try {
            service.issueRequiredAmountOfLotteryTickets(request.getPurchaseAmount());
            service.setWinningNumbers(request.getWinningNumbers());
            service.setBonusNumber(request.getBonusNumber());
            return new Response(service.analyzePortfolio(), null);
        } catch (IllegalArgumentException e) {
            return new Response(null, e.getMessage());
        }
    }

}
