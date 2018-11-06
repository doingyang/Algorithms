package com.project.ydy.algorithm;

/**
 * **************************************************
 * 文件名称 : SortAlgorithmUtil
 * 作    者 : Created by ydy
 * 创建时间 : 2018/10/17 14:22
 * 文件描述 :
 * 注意事项 :
 * 修改历史 : 2018/10/17 1.00 初始版本
 * **************************************************
 */
public class SortAlgorithmUtil {

    /**
     * 冒泡排序
     * @param arr 原数据
     */
    public static void bubblingSort(int[] arr) {
        int temp;
        //判断是否有执行交换动作
        int flag;
        int size = arr.length;
        for (int i = size - 1; i > 0; i--) {
            flag = 0;
            for (int j = 0; j < i; j++) {
                //比较相邻两数，若第一数较大则交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    //执行了交换
                    flag++;
                }
            }
            //如果一次比较之后没有发生交换，表示排序完成，可跳出循环
            if (flag == 0) {
                break;
            }
        }

    }

    /**
     * 选择排序
     * N个数据由大到小排序
     * 以第一个位置的数据依次与后面位置的数据比较
     * 如果数据大于或等于某个位置，则两个位置的数据不变，否则进行交换
     * @param arr 原数据
     */
    public static void selectSort(int[] arr) {
        int temp;
        int size = arr.length;
        //执行size-1次
        for (int i = 0; i < size - 1; i++) {
            //从i+1开始，比较size-1次
            for (int j = i + 1; j < size; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 插入排序
     * 将数组中的数据，逐一与已排序好的数据比较
     * 将该数据插入适当的位置
     * @param arr 原数据
     */
    public static void insertSort(int[] arr) {
        int temp;
        int size = arr.length;
        //定位比较的元素
        int j;
        //默认第一个元素已排好序，从第二个元素开始，执行size-1次
        for (int i = 1; i < size; i++) {
            temp = arr[i];
            j = i - 1;
            //比较data[i]与data[i-1]，若data[i]<data[i-1]，则把所有元素往后推1
            while (j >= 0 && temp < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            //最小的元素移到空位处
            arr[j + 1] = temp;
        }
    }

    /**
     * 希尔排序
     * 时间复杂度o(n^1.5)
     * @param arr 原数据
     */
    public static void shellSort(int[] arr) {
        int temp;
        int jmp;//间隔位移量
        int size = arr.length;
        int j;//定位比较的元素
        jmp = size / 2;//先以数组大小的一半作为间隔位移量
        while (jmp != 0) {
            for (int i = jmp; i < size; i++) {
                temp = arr[i];
                j = i - jmp;
                //插入排序
                while (j >= 0 && temp < arr[j]) {
                    arr[j + jmp] = arr[j];
                    j = j - jmp;
                }
                arr[jmp + j] = temp;
            }
            jmp = jmp / 2;
        }
    }

    /**
     * 快速排序
     * 1、取k为第一个键值
     * 2、从左向右找出一个键值ki，使得ki>k
     * 3、从右向左找出一个键值kj，使得kj<k
     * 4、若i<j，则ki与kj交换，继续步骤2
     * 5、若i>=j，则将k与kj交换，并以j为记号将数据分为左右两部分
     * 以递归方式分别对左右两边进行排序，直到排序完成
     */
    public static void quickSort(int[] arr, int left, int right) {
        int temp;
        int leftIndex;//从左向右移动的键
        int rightIndex;//从右向左移动的键
        //第一个键值为data[left]
        if (left < right) {
            leftIndex = left + 1;
            rightIndex = right;
            while (true) {
                for (int i = left + 1; i <= right; i++) {
                    //2-从左向右找出一个键值>data[left]
                    if (arr[i] >= arr[left]) {
                        leftIndex = i;
                        break;
                    }
                    leftIndex++;
                }
                for (int j = right; j >= left + 1; j--) {
                    //3-从右向左找出一个键值<data[left]
                    if (arr[j] <= arr[left]){
                        rightIndex = j;
                        break;
                    }
                    rightIndex--;
                }
                //4-若leftIndex<rightIndex，则交换data[leftIndex]和data[rightIndex]
                if (leftIndex < rightIndex) {
                    temp = arr[leftIndex];
                    arr[leftIndex] = arr[rightIndex];
                    arr[rightIndex] = temp;
                } else {
                    break;
                }
            }
            //
            if (leftIndex >= rightIndex) {
                //5-若leftIndex>=rightIndex，则交换data[left]和data[rightIndex]
                temp = arr[left];
                arr[left] = arr[rightIndex];
                arr[rightIndex] = temp;
                //以rightIndex为基准点分为两半，递归排序
                quickSort(arr, left, rightIndex - 1);
                quickSort(arr, rightIndex + 1, right);
            }
        }
    }

    /**
     * 1、建初始堆：将R[1...n]构造为初始堆
     * 2、堆调整：将当前无序区的堆顶记录R[1]和该区间的最后一个记录交换，然后将新的无序区调整为堆
     */
    public static void buildHeap(int[] arr) {
        int heapSize = arr.length;
        int filter = heapSize / 2;
        //i从第一个非叶子结点开始
        for (int i = filter - 1; i >= 0; i--) {
            heapAdjust(arr, i, heapSize);
        }
    }

    /**
     * H.r[i...heapSize]中记录的关键字除H.r[i]外，均满足最大堆结构
     */
    private static void heapAdjust(int[] arr, int i, int heapSize) {
        //当前待调整的元素
        int temp = arr[i];
        //该元素的左孩子
        int index = 2 * i + 1;
        while (index < heapSize) {
            //如果右孩子大于左孩子，则index+1，即交换右孩子和双亲节点
            if (index + 1 < heapSize && arr[index] < arr[index + 1]) {
                index = index + 1;
            }
            if (arr[i] < arr[index]) {
                //交换孩子和双亲节点
                arr[i] = arr[index];
                //重新赋值
                i = index;
                index = 2 * i + 1;
            } else {
                //已经是最大堆
                break;
            }
            //将双亲值赋给孩子节点
            arr[i] = temp;
        }
    }

    /**
     * 堆排序
     * @param arr 原数据
     */
    public static void heapSort(int[] arr) {
        int heapSize = arr.length;
        for (int i = heapSize - 1; i > 0; i--) {
            //交换堆顶和最后一个元素
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            //在heapSize范围内根节点的左右子树都已经是最大堆，所以只需要看新交换的堆顶元素是否满足
            //将H.r[0...i]重新调整为最大堆
            heapAdjust(arr, 0, i);
        }
    }

    /**
     * 归并排序
     */
    public static void mergeSort(int[] arr, int first, int last, int[] temp) {
        if (first < last) {
            int middle = (first + last) / 2;
            //左半部分排好序
            mergeSort(arr, first, middle, temp);
            //右半部分排好序
            mergeSort(arr, middle + 1, last, temp);
            //合并左右部分
            mergeArray(arr, first, middle, last, temp);
        }
    }

    /**
     * 合并 ：将两个序列a[first-middle],a[middle+1-end]合并
     */
    private static void mergeArray(int[] arr, int first, int middle, int end, int[] temp) {
        int i = first;
        int m = middle;
        int j = middle + 1;
        int n = end;
        int k = 0;
        while (i <= m && j <= n) {
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                k++;
                i++;
            } else {
                temp[k] = arr[j];
                k++;
                j++;
            }
        }
        while (i <= m) {
            temp[k] = arr[i];
            k++;
            i++;
        }
        while (j <= n) {
            temp[k] = arr[j];
            k++;
            j++;
        }

        for (int ii = 0; ii < k; ii++) {
            arr[first + ii] = temp[ii];
        }
    }

    /**
     * 基数排序
     * @param arr  原数组
     * @param temp 临时数组 new int[arr.length]
     * @param n    序列的数字个数 arr.length
     * @param k    最大的位数2
     * @param r    基数10
     * @param cnt  存储bin[i]的个数 new int[r]
     */
    public static void radixSort(int[] arr, int[] temp, int n, int k, int r, int[] cnt) {
        for (int i = 0, rtok = 1; i < k; i++, rtok = rtok * r) {
            //初始化
            for (int j = 0; j < r; j++) {
                cnt[j] = 0;
            }
            //计算每个箱子的数字个数
            for (int j = 0; j < n; j++) {
                cnt[(arr[j] / rtok) % r]++;
            }
            //cnt[j]的个数修改为前j个箱子一共有几个数字
            for (int j = 1; j < r; j++) {
                cnt[j] = cnt[j - 1] + cnt[j];
            }
            //重点
            for (int j = n - 1; j >= 0; j--) {
                cnt[(arr[j] / rtok) % r]--;
                temp[cnt[(arr[j] / rtok) % r]] = arr[j];
            }
            for (int j = 0; j < n; j++) {
                arr[j] = temp[j];
            }
        }
    }
}
