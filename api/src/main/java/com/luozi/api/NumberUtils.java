package com.luozi.api;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Objects;

/**
 * Created by luoziyihao on 6/19/17.
 */
public class NumberUtils {

    private static final Map<Integer, String> numberVoiceMap = ImmutableMap.<Integer, String>builder()
            .putAll(ImmutableMap.of(
                    10000, "万"
                    , 1000, "千"
                    , 100, "百"
                    , 10, "十"
                    , 1, ""
            ))
            .build();

    private static final Map<Integer, String> numberMap = ImmutableMap.<Integer, String>builder()
            .putAll(ImmutableMap.of(
                    0, "零"
                    , 1, "一"
                    , 2, "二"
                    , 3, "三"
                    , 4, "四"
            ))
            .putAll(ImmutableMap.of(
                    5, "五"
                    , 6, "六"
                    , 7, "七"
                    , 8, "八"
                    , 9, "九"
            ))
            .build();

    private static final int high = 100000;
    /**
     * 整数转读音 只支持到万位
     *
     * @param num
     * @return
     */
    public static String numberToVoice(int num) {
        if (num >= high) {
            throw new IllegalStateException("this method did't support number greater then " + high);
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, String> entry : numberVoiceMap.entrySet()) {
            int count = num / entry.getKey();
            if (count >= 1) {
                builder.append(numberMap.get(count))
                        .append(entry.getValue());
            } else if (checkIsAppendZero(builder, entry)) {
                builder.append(numberMap.get(count));
            }
            num = num % entry.getKey();

        }
        CheckAndRemoveSpareZero(builder);
        return builder.toString();
    }

    private static final int zero = 0;
    private static void CheckAndRemoveSpareZero(StringBuilder builder) {
        int length = builder.length();
        while (length > 1 && getLastChar(builder).equals(numberMap.get(zero))) {
            length = builder.length();
            if (Objects.equals(getLastChar(builder), numberMap.get(zero)) && length > 1) {
                builder.deleteCharAt(length - 1);
            }
        }

    }

    private static String getLastChar(StringBuilder builder) {
        int length = builder.length();
        if (length >= 1){
            return builder.charAt(length - 1) + "";
        } else {
            return "";
        }
    }

    private static boolean checkIsAppendZero(StringBuilder builder, Map.Entry<Integer, String> entry) {
        int length = builder.length();
        return (length > 0 && !Objects.equals(getLastChar(builder), numberMap.get(zero)))
                || entry.getKey() == 1;
    }
}
