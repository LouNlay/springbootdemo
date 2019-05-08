package com.bytedance.questions;

import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.buf.HexUtils;
import org.xml.sax.ext.Attributes2;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {
    /**
     *在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int [][] array) {

        for(int i=0;i<array.length;i++){
            
            for(int k=0;k<array[i].length;k++){
                if(target==array[i][k]){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        return str.toString().replace(" ", "%20");
    }


    /**
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<>();

        while (listNode != null) {
            result.add(listNode.val);
            listNode = listNode.next;
        }
        Collections.reverse(result);
        return result;

    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * @param base
     */
    public double Power(double base, int exponent) {

        return Math.pow(base, exponent);
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * @param array
     */
    public void reOrderArray(int [] array) {
        if (array.length > 1) {

            int[] result = array.clone();
            int j=0,k=array.length/2;
            if (array.length % 2 == 1) {
                k=k+1;
            }
            for (int i = 0; i < array.length; i++) {
                if (result[i] % 2 == 0) {
                    array[k]=result[i];
                    k++;
                }else {
                    array[j] = result[i];
                    j++;
                }
            }
            System.out.println(JSONObject.toJSONString(result));
            System.out.println(JSONObject.toJSONString(array));
        }
    }

    /**
     * 输入一个链表，输出该链表中倒数第k个结点。
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head,int k) {

        try {
            ArrayList<ListNode> listNodes = new ArrayList<>();
            while (head != null) {
                listNodes.add(head);
                head = head.next;
            }
            return listNodes.get(listNodes.size() - k);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 输入一个链表，反转链表后，输出新链表的表头
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {

        LinkedList<ListNode> listNodes = new LinkedList<>();
        while (head != null) {
            listNodes.addFirst(head);
            head = head.next;
        }
        if (listNodes == null || listNodes.isEmpty()) {
            return null;
        }
        for (int i = 0; i <listNodes.size()-1; i++) {
            listNodes.get(i).next = listNodes.get(i + 1);
        }
        listNodes.getLast().next = null;
        return listNodes.getFirst();

    }

    /**
     * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null && list2 == null) {
            return list1;
        }
        if (list1 != null && list2 == null) {
            return list1;
        }
        if (list1 == null && list2 != null) {
            return list2;
        }
        LinkedList<ListNode> listNodes = new LinkedList<>();
        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                ListNode temp = list2.next;
                list2.next = list1;
                listNodes.add(list2);
                list2 = temp;
            }else {
                ListNode temp = list1.next;
                list1.next = list2;
                listNodes.add(list1);
                list1 = temp;
            }
        }
        return listNodes.getFirst();
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i <matrix.length ; ) {

            for (int j = 0; j < matrix.length; ) {

                if (i == 0) {
                    list.add(matrix[i][j++]);//矩阵第一行

                } else if (j == matrix.length - 1) {
                    list.add(matrix[i++][j]);
                } else if (i == j && j == matrix.length - 1) {
                    list.add(matrix[i][j--]);
                } else if (j == 0 && i == matrix.length - 1) {
                    list.add(matrix[i--][j]);
                }


            }
        }

        return list;
    }

    /**
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     */
    private Stack stack;
    public void push(int node) {
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return (int)stack.pop();
    }

    public int min() {
        int min = (int)stack.pop();
        while(stack.size()!=0){
            int pop = (int)stack.pop();
            if(min>pop){
                min = pop;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[] array = {1,2,3,4,5,6,7};
//        ArrayList<Integer> integers = solution.printListFromTailToHead(null);
//        solution.reOrderArray(array);
//        System.out.println(JSONObject.toJSONString(integers));

        int a2 = Integer.valueOf("A", 16);
        System.out.println(Integer.toOctalString(a2));
        String str = "2E51012D8BF5503DB48B1E62775F4BC45276AA09489AA1";
        String[] split = str.split("");
        StringBuffer sb = new StringBuffer();
        for (String c: split
             ) {
            if (c.equals("A")) {
                sb.append(Integer.toBinaryString(10));
                continue;
            }
            if (c.equals("B")) {
                sb.append(Integer.toBinaryString(11));
                continue;
            }
            if (c.equals("C")) {
                sb.append(Integer.toBinaryString(12));
                continue;
            }
            if (c.equals("D")) {
                sb.append(Integer.toBinaryString(13));
                continue;
            }
            if (c.equals("E")) {
                sb.append(Integer.toBinaryString(14));
                continue;
            }
            if (c.equals("F")) {
                sb.append(Integer.toBinaryString(15));
                continue;
            }
            sb.append(Integer.toBinaryString(Integer.valueOf(c)));

        }
        System.out.println(sb.toString());
        if (sb.length() % 3 == 1) {
            sb.insert(0, "00");
        }
        if (sb.length() % 3 == 2) {
            sb.insert(0, "0");
        }

        StringBuffer octalString = new StringBuffer();
        while (sb != null) {
            StringBuffer delete = sb.delete(0, 2);
        }


    }






}