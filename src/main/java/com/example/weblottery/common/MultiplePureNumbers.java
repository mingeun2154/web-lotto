package com.example.weblottery.common;

import com.example.weblottery.exception.NonPureNumberException;
import java.util.ArrayList;
import java.util.List;

public class MultiplePureNumbers {

    private static final String SEPERATOR = ",";
    private final List<PureNumber> pureNumbers;

    public static MultiplePureNumbers wrap(String rawInput) throws NonPureNumberException {
        return new MultiplePureNumbers(rawInput);
    }

    public List<PureNumber> getPureNumbers() {
        return pureNumbers;
    }

    public MultiplePureNumbers(String rawInput) throws NonPureNumberException {
        pureNumbers = new ArrayList<>();
        for (String token : rawInput.split(SEPERATOR)) {
            pureNumbers.add(PureNumber.wrap(token.trim()));
        }
    }
}
