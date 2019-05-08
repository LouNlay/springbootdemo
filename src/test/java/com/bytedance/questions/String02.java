package com.bytedance.questions;

import org.springframework.util.CollectionUtils;

import java.util.LinkedList;

/**
 * 2 万万没想到之聪明的编辑
 * 我叫王大锤，是一家出版社的编辑。我负责校对投稿来的英文稿件，这份工作非常烦人，因为每天都要去修正无数的拼写错误。但是，优秀的人总能在平凡的工作中发现真理。我发现一个发现拼写错误的捷径：
 *
 * 三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 helllo -> hello
 *
 * 两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
 *
 * 上面的规则优先“从左到右”匹配，即如果是AABBCC，虽然AABB和BBCC都是错误拼写，应该优先考虑修复AABB，结果为AABCC
 *
 * 请听题：请实现大锤的自动校对程序
 *
 * 输入描述:
 *
 * 第一行包括一个数字N，表示本次用例包括多少个待校验的字符串。
 * 后面跟随N行，每行为一个待校验的字符串。
 *
 * 输出描述:
 *
 * N行，每行包括一个被修复后的字符串。
 *
 * 输入：
 *
 * 2
 * helloo
 * wooooooow
 *
 * 输出：
 *
 * hello
 * woow
 */
public class String02 {

    public String validate3Letters(String string) {

        char[] chars = string.toCharArray();
        LinkedList<String> removeIndex = new LinkedList<>();
        //三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 helllo -> hello
        for (int i = 0; i < chars.length-2; i++) {
            if (chars[i] == chars[i + 1]) {
                if (chars[i + 1] == chars[i + 2]) {
                    removeIndex.addLast(new StringBuffer().append(chars[i]).append(chars[i+1]).append(chars[i+2]).toString());
                }
            }
        }
        if (!CollectionUtils.isEmpty(removeIndex)) {
            for (String str : removeIndex) {
                string = string.replace(str, str.substring(1));
            }
        }
        return string;
    }

    public String validateAABB(String string) {

        char[] chars = string.toCharArray();
        LinkedList<String> removeIndex = new LinkedList<>();
        //两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
        for (int i = 0; i < chars.length-3; i++) {
            if (chars[i] == chars[i + 1]) {
                if (chars[i + 2] == chars[i + 3]) {
                    removeIndex.addLast(new StringBuffer().append(chars[i]).append(chars[i+1]).append(chars[i+2]).append(chars[i+3]).toString());
                }
            }
        }

        if (!CollectionUtils.isEmpty(removeIndex)) {
            for (String str : removeIndex) {
                string = string.replace(str, str.substring(0, 3));
            }
        }
        return string;

    }


    public static void main(String[] args) {
        String02 str = new String02();
        String errorStr = "wooooooow";
        while (!errorStr.equals(str.validate3Letters(errorStr))) {
            errorStr = str.validate3Letters(errorStr);
            System.out.println("validate3Letters : "+errorStr);
        }

        while (!errorStr.equals(str.validateAABB(errorStr))) {
            errorStr = str.validateAABB(errorStr);
            System.out.println("validateAABB : "+errorStr);
        }

        System.out.println(errorStr);
    }





}
