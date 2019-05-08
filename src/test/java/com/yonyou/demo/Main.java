package com.yonyou.demo;
import java.util.Arrays;
import java.util.Scanner;
import static java.util.Arrays.sort;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] add = new int[n];//输入的数组
        int[] b = new int[m];//要输出的第y次操作
        int[] arrays = new int[n];
        int k = 1;

        add(add,n,sc);//将第二组数字添加进数组
        add(b,m,sc);//将第三组书添加进数组

 /*       for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
        }*/
//        arrays = add.clone();
//        for(int i=0;i<n;i++) {
//        	arrays[i] = add[i];
        	//System.out.println("arrays[i]: "+arrays[i]);
        	for(int j = 0; j < b.length; j++) {
        	    get(b[j],add,b,k);
//        		}
        	}
//        }
    }

    private static void get(int i, int[] ints, int[] add, int k) {
        int[] newArray = new int[i];
        for (int x=0;x<newArray.length;x++){
            newArray[x] = add[x];
        }
//        		if(b[j]==(i+1)) {

        System.out.println("b[j]: "+i);
        sort(newArray);
        for (int num : newArray) {
            System.out.print(num+" ");
        }
        System.out.println();
        System.out.println(newArray[(k-1)]);
        k+=1;
    }

    private static void add(int[] add, int n, Scanner sc) {
        for (int i = 0; i < n; i++) {
            add[i] = sc.nextInt();
        }
    }
}