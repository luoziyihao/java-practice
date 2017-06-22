package com.luozi.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by luoziyihao on 6/22/17.
 */
public class StringReverser {
    /**
     * 使用字节数组的处理方式
     * 先尝试理出大致思路, 找一个方向
     * 在构思算法的关键点, 可以划出来
     * 再边写边想
     */
    public String reverseStringBySplitChar(String src, char split){
        //校验src
        //循环处理字符数组, 将里面的char按照 split分别放在好几个String中
        //关键点是比较相邻的两个字符
        char[] arrs = src.toCharArray();
        List<String> lists = new ArrayList<String>();
        int length = arrs.length;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < length; i++) {
            char nowChar = arrs[i];
            if (i == 0) {
                builder.append(nowChar);
                continue;
            }
            char lastChar =  arrs[i-1];

            // 在循环中拼装出具体的子串, 子串的结束时机用isSplitStringEnd控制
            boolean isSplitAfterString = lastChar  != split && nowChar == split;
            boolean isStringAfterSplit = lastChar  == split && nowChar != split;
            if (isSplitAfterString || isStringAfterSplit) {
                lists.add(builder.toString());
                builder = new StringBuilder();
            }
            builder.append(nowChar);

        }
        lists.add(builder.toString());
        Collections.reverse(lists);
        return appendString(lists);
    }

    private String appendString(List<String> lists) {
        StringBuilder builder = new StringBuilder();
        for (String str : lists) {
            builder.append(str);
        }
        return builder.toString();

    }
}
