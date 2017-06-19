package com.luozi.api;

import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.Objects;

/**
 * Created by luoziyihao on 6/19/17.
 */
public class NumberUtils {

    private static final Map<Integer, String> BIGNUMBER_VOICE_MAP = ImmutableMap.<Integer, String>builder()
            .putAll(ImmutableMap.of(
                    100000000, "亿"
                    , 10000, "万"
            ))
            .build();

    private static final Map<Integer, String> NUMBER_VOICE_MAP = ImmutableMap.<Integer, String>builder()
            .putAll(ImmutableMap.of(
                    1000, "千"
                    , 100, "百"
                    , 10, "十"
                    , 1, ""
            ))
            .build();

    private static final Map<Integer, String> NUMBER_MAP = ImmutableMap.<Integer, String>builder()
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

    private static final int HIGH = 10000;

    /**
     * 整数转读音
     *
     * @param num
     * @return
     */
    public static String numberToVoice(int num) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Integer, String> entry : BIGNUMBER_VOICE_MAP.entrySet()) {
            int count = num / entry.getKey();
            num = num % entry.getKey();
            if (count > 0) {
                builder.append(processForFourBit(count))    // 数字按照四位分组处理
                        .append(entry.getValue());
            }

        }

        builder.append(processForFourBit(num));
        CheckAndRemoveSpareFirstZero(builder);
        CheckAndRemoveSpareLastZero(builder);
        return builder.toString();
    }

    private static void CheckAndRemoveSpareFirstZero(StringBuilder builder) {
        int length = builder.length();
        while (length > 1 && getFirstChar(builder).equals(NUMBER_MAP.get(zero))) {
            length = builder.length();
            if (Objects.equals(getFirstChar(builder), NUMBER_MAP.get(zero)) && length > 1) {
                builder.deleteCharAt(zero);
            }
        }

    }

    private static final int ZERO_CHECK_NUMBER = 1000;
    private static StringBuilder processForFourBit(int num) {
        StringBuilder builder = new StringBuilder();
        if (num < ZERO_CHECK_NUMBER) {  // 是否需要在第一位添零
            builder.append(NUMBER_MAP.get(zero));
        }
        if (num >= HIGH) {
            throw new IllegalStateException("this method did't support number greater then " + HIGH);
        }
        for (Map.Entry<Integer, String> entry : NUMBER_VOICE_MAP.entrySet()) {  // 拿到每一位的数 和 值
            int count = num / entry.getKey();
            if (count >= 1) {
                builder.append(NUMBER_MAP.get(count))
                        .append(entry.getValue());
            } else if (checkIsAppendZero(builder, entry)) {
                builder.append(NUMBER_MAP.get(count));
            }
            num = num % entry.getKey();

        }
        CheckAndRemoveSpareLastZero(builder);
        return builder;
    }

    private static final int zero = 0;

    private static void CheckAndRemoveSpareLastZero(StringBuilder builder) {
        int length = builder.length();
        while (length > 1 && getLastChar(builder).equals(NUMBER_MAP.get(zero))) {
            length = builder.length();
            if (Objects.equals(getLastChar(builder), NUMBER_MAP.get(zero)) && length > 1) {
                builder.deleteCharAt(length - 1);
            }
        }
    }

    private static String getLastChar(StringBuilder builder) {
        int length = builder.length();
        if (length >= 1) {
            return builder.charAt(length - 1) + "";
        } else {
            return "";
        }
    }

    private static String getFirstChar(StringBuilder builder) {
        int length = builder.length();
        if (length >= 1) {
            return builder.charAt(0) + "";
        } else {
            return "";
        }
    }

    private static boolean checkIsAppendZero(StringBuilder builder, Map.Entry<Integer, String> entry) {
        int length = builder.length();
        return (length >= 1 && !Objects.equals(getLastChar(builder), NUMBER_MAP.get(zero)))
                || entry.getKey() == 1;
    }


}
