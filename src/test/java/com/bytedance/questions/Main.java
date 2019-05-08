package com.bytedance.questions;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void Replace(String str) {
		Pattern pattern = Pattern.compile(" ([a-z])\\1{3,}");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            //match.group(0) 是匹配的字符串，比如111234445466中的111
            //match.group(1) 是匹配的字符串的单个字符，比如111234445466中的111中的1
            String repeat = matcher.group(1);
            //appendReplacement用第二个参数去替换匹配成功的子串，并把结果存放在sb中，前面未匹配成功的也会放进去，后面的未匹配成功的不会放进去。例如：11123444546634 最后会替换成(1)23(4)54(6)
            matcher.appendReplacement(sb, repeat+repeat );
         }
         //把后面未匹配成功的附加到sb上,例如：11123444546634 最后会替换成1123445466
        matcher.appendTail(sb);
        System.out.println(sb.toString());
	}
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=2;
        //int n = sc.nextInt();  //整数 n
        String[] str=new String[n];
        str[0]="helloo";
        str[1]="wooooooooooow";
        /*for(int i=0;i<n;i++) {
        	str[i] = sc.next();
        }*/

        for(int i=0;i<n;i++) {
        	Replace(str[i]);
        }
        
    }
}