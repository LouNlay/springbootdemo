package com.yonyou.demo;

import java.util.ArrayList;
import java.util.List;

/**
 *TODO 两个集合之间求交集 并集 差集 去重复并集
 * @author 寇爽
 * @date 2017年11月21日
 * @version 1.8
 */
public class MethodDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
((ArrayList<Integer>) list1).trimToSize();
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(2);
		list2.add(3);
		list2.add(4);
		list2.add(5);
		// 并集
//		 list1.addAll(list2);
		// 交集
//		list1.retainAll(list2);
		// 差集
//		 list1.removeAll(list2);
		// 无重复并集
		list2.removeAll(list1);
		list1.addAll(list2);
		for (Integer i : list1) {
			System.out.println(i);
		}
//		System.out.println();
	}

}