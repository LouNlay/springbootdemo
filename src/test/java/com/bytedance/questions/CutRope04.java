package com.bytedance.questions;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

/**
 * 4 剪绳子
 * 有 N 根绳子，第 i 根绳子长度为 Li，现在需要 M 根等长的绳子，你可以对 n 根绳子进行任意裁剪（不能拼接),请你帮忙计算出这 M 根绳子最长的长度是多少。
 *
 * 输入描述：
 *
 * 第一行包含 2 个正整数N、M，表示 N 根原始的绳子，和最终需要根绳子数
 * 第二行包含 N 个整数，第 i 个整数表示第 i 根绳子的长度
 * 其中
 * 1<=N,M<=100000，
 * 0<Li<1000000000
 *
 * 输出描述：
 *
 * 对每一个测试用例，输出一个数字表示裁剪后最长的长度，保留两位小数。
 *
 * 输入：
 *
 * 3 4
 * 3 5 4
 *
 * 输出：
 *
 * 2.50
 *
 * 说明：
 *
 * 第一根和第三根分别裁剪出一根2.50长度的绳子，第二根剪成2根2．50长度的绳子，刚好4根
 */
public class CutRope04 {

    //思路：根据题目描述，题目其实是求除数问题。
    //第二行输入的数分别作为被除数，分别整除除以某个相同的除数，商相加等于第一行输入的第二个数字
    //目前已知被除数，被除数的个数，商的和，求最大公共除数
    //


    public BigDecimal getRopeMaxLength(int n, int m, int[] ns) {

        validateParam(n,m,ns);
        BigDecimal temp = new BigDecimal(n + m).divide(new BigDecimal(n), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("temp : " + temp);
        int[] remainders = getRemainderByNS(temp,ns);
        System.out.println("remainders : " + JSONObject.toJSONString(remainders));
        BigDecimal[] values = calculation(ns,remainders);
        System.out.println("values : " + JSONObject.toJSONString(values));

        BigDecimal min = getMin(values);
        int index = getIndex(values, min);
        BigDecimal result =  min;
        if(min.compareTo(new BigDecimal(remainders[index]))<0){
            result = new BigDecimal(remainders[index]);
        }


        return result;
    }

    private int getIndex(BigDecimal[] values, BigDecimal min) {
        for (int i = 0; i < values.length; i++) {
            if (min == values[i]) {
                return i;
            }

        }
        return -1;
    }

    /**
     * 取最小值
     * @param values
     * @return
     */
    private BigDecimal getMin(BigDecimal[] values) {
        BigDecimal result = values[0];
        for (int i = 1; i < values.length; i++) {
            if (result.compareTo(values[i])>0) {
                result = values[i];
            }
        }
        return result;
    }

    /**
     * 用被除数除以商计算除数
     * @param ns
     * @param remainders
     * @return
     */
    private BigDecimal[] calculation(int[] ns, int[] remainders) {
        BigDecimal[] values = new BigDecimal[ns.length];
        for (int i = 0; i < ns.length; i++) {
            values[i] = new BigDecimal(ns[i]).divide(new BigDecimal(remainders[i]), 2, BigDecimal.ROUND_HALF_UP);
        }
        return values;
    }

    private int[] getRemainderByNS(BigDecimal temp, int[] ns) {
        int[] remainders = new int[ns.length];
        for (int i = 0; i < ns.length; i++) {
            remainders[i] = new BigDecimal(ns[i]).divide(temp,1, BigDecimal.ROUND_CEILING).intValue();
        }

        return remainders;
    }

    private void validateParam(int n, int m, int[] ns) {
        if (n < 0 || n > 100000) {
            throw new RuntimeException("N 参数不合法");
        }
        if (m < 0 || m > 100000) {
            throw new RuntimeException("M 参数不合法");
        }
        if (ns.length == n) {
            for (int i:ns
                 ) {
                if (i < 0 || i > 1000000000) {
                    throw new RuntimeException("Li 参数不合法");
                }
            }
        }else {
            throw new RuntimeException("数组长度不等于N");
        }

    }

    public static void main(String[] args) {
        CutRope04 cr = new CutRope04();
        int[] ns = {4,5,3};
        BigDecimal ropeMaxLength = cr.getRopeMaxLength(3, 4, ns);
        System.out.println("绳子的最大长度为："+ropeMaxLength);

    }
}
