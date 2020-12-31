package cn.shiyujun.algorithm;

import org.junit.Test;


/**
 * d
 *
 * @author syj
 * CreateTime 2019/07/25
 * describe: 基础排序算法
 */
public class BasicsAlgorithmTest {
	/**
	 * 选择排序
	 */
	@Test
	public void test1() {
		int i;        // 有序区的末尾位置
		int j;        // 无序区的起始位置
		int min;    // 无序区中最小元素位置
		int[] a = new int[]{3, 1, 0, 2, 8, 4, 2};
		int n = a.length;
		for (i = 0; i < n; i++) {
			min = i;
			// 找出"a[i+1] ... a[n]"之间的最小元素，并赋值给min。
			for (j = i + 1; j < n; j++) {
				if (a[j] < a[min])
					min = j;
			}
			// 若min!=i，则交换 a[i] 和 a[min]。
			// 交换之后，保证了a[0] ... a[i] 之间的元素是有序的。
			if (min != i) {
				int tmp = a[i];
				a[i] = a[min];
				a[min] = tmp;
			}
		}
	}

	/**
	 * 插入排序
	 */
	@Test
	public void test2() {
		int[] num = new int[]{3, 1, 0, 2, 8, 4, 2};
		for (int i = 1, n = num.length; i < n; i++) {
			if (num[i] < num[i - 1]) {
				for (int j = i; j > 0; j--) {
					if (num[j] < num[j - 1]) {
						int temp = num[j];
						num[j] = num[j - 1];
						num[j - 1] = temp;
					}
				}
			}
		}
		for (int i : num) {
			System.out.print(i + ",");
		}
	}

	/**
	 * 快速排序
	 *
	 * @param a
	 * @param l
	 * @param r
	 */
	void quickSort(int[] a, int l, int r) {
		if (l < r) {
			int i, j, n;
			i = l;
			j = r;
			n = a[i];
			while (i < j) {
				while (i < j) {
					if (a[j] < n) {
						a[i] = a[j];
						break;
					}
					j--;
				}
				while (i < j) {
					if (a[i] >= n) {
						a[j] = a[i];
						break;
					}
					i++;
				}
			}
			a[i] = n;
			quickSort(a, l, i - 1); /* 递归调用 */
			quickSort(a, i + 1, r); /* 递归调用 */
		}
	}

	/**
	 * 希尔排序
	 */
	void shellSort(int list[], int length) {
		int gap, i, j, temp;
		for (gap = length / 2; gap > 0; gap /= 2) {
			for (i = gap; i < length; i++) {
				for (j = i - gap; j >= 0 && list[j] > list[j + gap]; j -= gap) {
					temp = list[j];
					list[j] = list[j + gap];
					list[j + gap] = temp;
				}
			}
		}
	}

	/**
	 * 冒泡排序
	 */
	@Test
	public void test3() {
		int[] a = new int[]{3, 1, 0, 2, 8, 4, 2};
		int i, j;
		int flag;                 // 标记
		for (i = a.length - 1; i > 0; i--) {
			flag = 0;            // 初始化标记为0
			// 将a[0...i]中最大的数据放在末尾
			for (j = 0; j < i; j++) {
				if (a[j] > a[j + 1]) {
					// 交换a[j]和a[j+1]
					int tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
					flag = 1;    // 若发生交换，则设标记为1
				}
			}

			if (flag == 0)
				break;            // 若没发生交换，则说明数列已有序。
		}
		for (int ii : a) {
			System.out.print(ii + ",");
		}
	}


}

