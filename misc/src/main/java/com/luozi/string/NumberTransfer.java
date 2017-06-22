package com.luozi.string;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by luoziyihao on 6/19/17.
 */
public class NumberTransfer {

    private static final char NUMBER_PLACEHOLDER = '#';
    private static final String NUMBER_PLACEHOLDER_STRING = "#";

    private static final Map<Integer, Character> BIGNUMBER_VOICE_MAP = ImmutableMap.<Integer, Character>builder()
            .putAll(ImmutableMap.of(
                    100000000, '亿'
                    , 10000, '万'
                    , 1, NUMBER_PLACEHOLDER
            ))
            .build();


    private static final Map<Integer, Character> NUMBER_VOICE_MAP = ImmutableMap.<Integer, Character>builder()
            .putAll(ImmutableMap.of(
                    1000, '千'
                    , 100, '百'
                    , 10, '十'
                    , 1, NUMBER_PLACEHOLDER
            ))
            .build();

    private static final Map<Integer, Character> NUMBER_MAP = ImmutableMap.<Integer, Character>builder()
            .putAll(ImmutableMap.of(
                    0, '零'
                    , 1, '一'
                    , 2, '二'
                    , 3, '三'
                    , 4, '四'
            ))
            .putAll(ImmutableMap.of(
                    5, '五'
                    , 6, '六'
                    , 7, '七'
                    , 8, '八'
                    , 9, '九'
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
        if (num == 0) {
            return ZERO_NUMBER_DESCRIPTION_STRING;
        }
        for (Map.Entry<Integer, Character> entry : BIGNUMBER_VOICE_MAP.entrySet()) {
            int count = num / entry.getKey();
            num = num % entry.getKey();
            if (count > 0) {
                builder.append(processForFourBit(count))    // 数字按照四位分组处理
                        .append(entry.getValue());
            }
        }

        CheckAndRemoveSpareFirstZero(builder);
        CheckAndRemoveSpareLastZero(builder);
        return builder.toString()
                .replaceAll(NUMBER_PLACEHOLDER_STRING, StringUtils.EMPTY)
                .replaceAll(ZERO_NUMBER_REPLAX_REGEX, ZERO_NUMBER_DESCRIPTION_STRING)
                ;
    }

    private static final int zero = 0;
    private static final char ZERO_NUMBER_DESCRIPTION = '零';
    private static final String ZERO_NUMBER_DESCRIPTION_STRING = "零";
    private static final String ZERO_NUMBER_REPLAX_REGEX = "[零]+";


    private static final int ZERO_CHECK_NUMBER = 1000;

    private static StringBuilder processForFourBit(int num) {
        StringBuilder builder = new StringBuilder();
        if (num < ZERO_CHECK_NUMBER) {  // 是否需要在第一位添零
            builder.append(NUMBER_MAP.get(zero));
        }
        if (num >= HIGH) {
            throw new IllegalStateException("this method did't support number greater then " + HIGH);
        }
        for (Map.Entry<Integer, Character> entry : NUMBER_VOICE_MAP.entrySet()) {  // 拿到每一位的数 和 值
            int count = num / entry.getKey();
            if (count >= 1) {
                builder.append(NUMBER_MAP.get(count))
                        .append(entry.getValue());
            } else {    // 指定位数没有值, 补零, 如果存在多为为零的情况, 最后用正则统一替换掉
                builder.append(ZERO_NUMBER_DESCRIPTION);
            }
            num = num % entry.getKey();

        }
        CheckAndRemoveSpareLastZero(builder);
        return builder;
    }

    private static void CheckAndRemoveSpareFirstZero(StringBuilder builder) {
        int length = builder.length();
        while (length > 1 && getFirstChar(builder) == ZERO_NUMBER_DESCRIPTION) {
            builder.deleteCharAt(zero);
            length = builder.length();
        }
    }

    private static void CheckAndRemoveSpareLastZero(StringBuilder builder) {
        int length = builder.length();
        while (length > 1 && getLastChar(builder) == ZERO_NUMBER_DESCRIPTION) {
            builder.deleteCharAt(length - 1);
            length = builder.length();
        }
    }

    /**
     * 方法在调用前必须保证 builder的length 大于 0
     *
     * @param builder
     * @return
     */
    private static Character getLastChar(StringBuilder builder) {
        return builder.charAt(builder.length() - 1);
    }

    /**
     * 方法在调用前必须保证 builder的length 大于 0
     *
     * @param builder
     * @return
     */
    private static char getFirstChar(StringBuilder builder) {
        return builder.charAt(0);
    }


}
