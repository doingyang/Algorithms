package com.project.ydy.algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * **************************************************
 * 文件名称 : SortAlgorithmTest
 * 作    者 : Created by ydy
 * 创建时间 : 2018/10/19 17:15
 * 文件描述 :
 * 注意事项 :
 * 修改历史 : 2018/10/19 1.00 初始版本
 * **************************************************
 */
public class SortAlgorithmTest {

    @Test
    public void testSortAlgorithm() {
        int[] data = {1, 3, 5, 7, 9, 8, 6, 4, 2, 0};
//        SortAlgorithmUtil.bubblingSort(data);
//        SortAlgorithmUtil.selectSort(data);
//        SortAlgorithmUtil.insertSort(data);
//        SortAlgorithmUtil.shellSort(data);
//        SortAlgorithmUtil.quickSort(data, 0, data.length - 1);
//        SortAlgorithmUtil.buildHeap(data);
//        SortAlgorithmUtil.heapSort(data);
//        SortAlgorithmUtil.mergeSort(data, 0, data.length - 1, new int[data.length]);
        SortAlgorithmUtil.radixSort(data, new int[data.length], data.length, 2, 10, new int[10]);

        System.out.println(Arrays.toString(data));
    }

}
