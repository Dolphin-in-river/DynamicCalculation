package com.example.dynamiccalculation.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class NumberTranslateServiceImpl implements NumberTranslateService {
    public static final String LAST_SYMBOLS_OF_ONE_IN_WHOLE_PART = "на";
    public static final String LAST_SYMBOLS_OF_TWO_IN_WHOLE_PART = "e";
    public static final String ZERO = "ноль";
    public static final String WHOLE_WORD_AFTER_ONE = " целая ";
    public static final String WHOLE_WORD_AFTER_TWO_UNDER_FOUR = " целые ";
    public static final String WHOLE_WORD_AFTER_FOUR = " целых ";
    public static final String LAST_SYMBOLS_OF_ONE_IN_DOUBLE_PART = "ая";
    private final int TEN = 10;
    private final int TWENTY = 20;
    private final int HUNDRED = 100;
    private final int THOUSAND = 1000;
    private final int TEN_THOUSAND = 10000;
    private final int HUNDRED_THOUSAND = 100000;
    private final int MILLION = 1000000;
    private final int TEN_MILLION = 10000000;
    private final int HUNDRED_MILLION = 100000000;
    private final int BILLION = 1000000000;

    String[] nums = {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
    String[] overTen = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
    String[] tens = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    String[] hundreds = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    String[] thousands = {"тысяч", "одна тысяча", "две тысячи", "три тысячи", "четыре тысячи", "пять тысяч", "шесть тысяч", "семь тысяч", "восемь тысяч", "девять тысяч"};
    String[] millions = {"миллионов", "один миллион", "два миллиона", "три миллиона", "четыре миллиона", "пять миллионов", "шесть миллионов", "семь миллионов", "восемь миллионов", "девять миллионов"};
    String[] partitions = {"", "десятых", "сотых", "тысячных", "десятитысячных", "стотысячных", "миллионных", "десятимилионных", "стомиллионных", "миллиардных"};
    String[] doubleNums = {"", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};

    private String printResult(String firstPart, String secondPart) {
        if (Objects.equals(secondPart, "")) {
            return firstPart;
        }
        return firstPart + " " + secondPart;
    }

    String numToStr(int n, String[] arr_nums) {
        if (n > 0) {
            if (n < TEN) return printResult(arr_nums[n], "");
            if (n < TWENTY) return printResult(overTen[n % TEN], "");
            if (n < HUNDRED) return printResult(tens[(n / TEN)], numToStr(n % TEN, arr_nums));
            if (n < THOUSAND) return printResult(hundreds[(n / HUNDRED)], numToStr(n % HUNDRED, arr_nums));
            if (n < TEN_THOUSAND) return printResult(thousands[(n / THOUSAND)], numToStr(n % THOUSAND, arr_nums));
            if (n < HUNDRED_THOUSAND) {
                int param = n / THOUSAND; // Получаем количество тысяч
                if (param < TWENTY) {
                    return printResult(numToStr(param, arr_nums) + " " + thousands[0], numToStr(n % THOUSAND, arr_nums));
                }
                int countThousand = param;
                param /= TEN; // Убираем последнюю цифру
                if (n % TEN_THOUSAND < THOUSAND) // Если старший разряд заканчивается на 0
                    return printResult(numToStr(param * TEN, arr_nums) + " " + thousands[0], numToStr(n % TEN_THOUSAND, arr_nums));
                return printResult(numToStr(param * TEN, arr_nums), numToStr(n % TEN_THOUSAND, arr_nums));
            }
            if (n < MILLION) {
                int param = n / HUNDRED_THOUSAND;
                if (n % HUNDRED_THOUSAND < THOUSAND) // Если старший разряд заканчивается на 0
                    return printResult(numToStr(param * HUNDRED, arr_nums) + " " + thousands[0], numToStr(n % HUNDRED_THOUSAND, arr_nums));
                return printResult(numToStr(param * HUNDRED, arr_nums), numToStr(n % HUNDRED_THOUSAND, arr_nums));
            }
            if (n < TEN_MILLION) {
                return printResult(millions[n / MILLION], numToStr(n % MILLION, arr_nums));
            }
            if (n < HUNDRED_MILLION) {
                int param = n / MILLION;
                if (param < TWENTY) {
                    return printResult(numToStr(param, arr_nums) + " " + millions[0], numToStr(n % MILLION, arr_nums));
                }
                param /= TEN;
                if (n % TEN_MILLION < MILLION) // Если старший разряд заканчивается на 0
                    return printResult(numToStr(param * TEN, arr_nums) + " " + millions[0], numToStr(n % TEN_MILLION, arr_nums));
                return printResult(numToStr(param * TEN, arr_nums), numToStr(n % TEN_MILLION, arr_nums));
            }
            if (n < BILLION) {
                int param = n / HUNDRED_MILLION;
                if (n % HUNDRED_MILLION < TEN_MILLION) // Если старший разряд заканчивается на 0
                    return printResult(numToStr(param * HUNDRED, arr_nums) + " " + millions[0], numToStr(n % HUNDRED_MILLION, arr_nums));
                return printResult(numToStr(param * HUNDRED, arr_nums), numToStr(n % HUNDRED_MILLION, arr_nums));
            }
        }
        return "";
    }

    @Override
    public String getTranslate(double number) {
        BigDecimal bigD = BigDecimal.valueOf(number);
        int intPart = bigD.intValue();
        double fractionalPart = bigD.subtract(BigDecimal.valueOf(intPart)).doubleValue();

        String wholeStr = numToStr(intPart, nums);
        String fracStr = "";
        if (fractionalPart > 0) {
            if (intPart % 10 == 1 && intPart % 100 != 11) { // поведение, когда последняя цифра числа 1 у 11 другое
                String finalFirstPart = wholeStr.substring(0, wholeStr.length() - 2);
                finalFirstPart += LAST_SYMBOLS_OF_ONE_IN_WHOLE_PART;
                wholeStr = finalFirstPart;
            }
            if (intPart % 10 == 2 && intPart % 100 != 12) { // поведение, когда последняя цифра числа 1 у 12 другое
                String finalFirstPart = wholeStr.substring(0, wholeStr.length() - 1);
                finalFirstPart += LAST_SYMBOLS_OF_TWO_IN_WHOLE_PART;
                wholeStr = finalFirstPart;
            }
            if (intPart == 0) fracStr += ZERO;
            int lastNumber = intPart % 10;
            int lastTwoNumbers = intPart % 100;
            if (lastNumber == 1 && intPart % 100 != 11) fracStr += WHOLE_WORD_AFTER_ONE;
            if (lastNumber > 1 && lastNumber <= 4 && (lastTwoNumbers <= 11 || lastTwoNumbers > 14))
                fracStr += WHOLE_WORD_AFTER_TWO_UNDER_FOUR;
            if (lastNumber == 0 || lastNumber > 4 || (lastTwoNumbers > 11 && lastTwoNumbers <= 14))
                fracStr += WHOLE_WORD_AFTER_FOUR;
            String newFormula = String.valueOf(BigDecimal.valueOf(fractionalPart));
            String cutFormula = newFormula.substring(2);
            int value = Integer.parseInt(cutFormula);
            fracStr += numToStr(value, doubleNums);
            fracStr += " ";
            fracStr += partitions[cutFormula.length()];
            if (value % 10 == 1 && intPart % 100 != 11) { // поведение, когда последняя цифра числа 1 у 11 другое
                String finalCut = fracStr.substring(0, fracStr.length() - 2);
                finalCut += LAST_SYMBOLS_OF_ONE_IN_DOUBLE_PART;
                return wholeStr + finalCut;
            }
        }
        return wholeStr + fracStr;
    }
}
