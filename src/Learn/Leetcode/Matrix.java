package Learn.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Matrix {

    // 73. 矩阵置零
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int temp = 0;
        if (matrix[0][0] != 0) {
            for (int i = 0; i < n; i++) {
                if (matrix[i][0] == 0) {
                    // 第0列
                    temp = -1;
                    break;
                }
            }
            for (int j = 0; j < m; j++) {
                if (matrix[0][j] == 0) {
                    if (temp == -1) {
                        // 0行0列
                        temp = -2;
                    } else {
                        temp = -3;
                    }
                    break;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (matrix[i][0] == 0) {
                Arrays.fill(matrix[i], 0);
            }
        }
        for (int j = 1; j < m; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < n; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (temp == -1) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        } else if (temp == -2) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
            Arrays.fill(matrix[0], 0);
        } else if (temp == -3) {
            Arrays.fill(matrix[0], 0);
        }
    }

    // 旋转矩阵
    public void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int temp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = temp;
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                swap(matrix, i, j, i, n - j + 1);
            }
        }
    }

    // 56. 合并区间
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });
        int curLeft = intervals[0][0];
        int curRight = intervals[0][1];
        List<int[]> list = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int thisLeft = intervals[i][0];
            int thisRight = intervals[i][1];
            // 此区间左已经大于当前最右
            if (thisLeft > curRight) {
                list.add(new int[]{curLeft, curRight});
                curLeft = thisLeft;
                curRight = thisRight;
            } else {
                curRight = Math.max(curRight, thisRight);
            }
        }
        list.add(new int[]{curLeft, curRight});
        int[][] res = new int[list.size()][2];
        int i = 0;
        for (int[] interval : list) {
            res[i] = interval;
            i++;
        }
        return res;
    }
}
