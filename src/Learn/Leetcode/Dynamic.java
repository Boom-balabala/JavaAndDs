package Learn.Leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 动态规划5步 1.确定dp数组（dp table）以及下标的含义 2.确定递推公式 3.dp数组如何初始化 4.确定遍历顺序 5.举例推导dp数组
 */
public class Dynamic {
    /**
     * 62. 不同路径 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。 问总共有多少条不同的路径？
     */
    public int uniquePaths(int m, int n) {
        // dp数组表示到达坐标(a,b)处的方案个数
        int[][] dp = new int[m + 1][n + 1];
        // 递推公式 dp[i][j]=dp[i][j-1]+dp[i-1][j]
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 63. 不同路径 II 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // dp数组表示到达坐标(a,b)处的方案个数
        int[][] dp = new int[m][n];
        // 递推公式 dp[i][j]=dp[i][j-1]+dp[i-1][j]
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            }
            dp[0][j] = 1;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 70. 爬楼梯 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */
    public int climbStairs(int n) {
        // dp[i] 表示爬到第i阶的方法数目
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 96. 不同的二叉搜索树 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            // TODO:
        }

        return dp[n];
    }

    //139. 单词拆分
//    public boolean wordBreak(String s, List<String> wordDict) {
//
//    }

    /**
     * 打家劫舍 专题
     */
    // 198. 打家劫舍：给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        // 偷到第 i 家的最大收益
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[nums.length];
    }

    /**
     * 322. 零钱兑换
     * @param coins
     * @param amount
     * @return
     */
//    public int coinChange(int[] coins, int amount) {
//
//    }

    /**
     * 343. 整数拆分 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。 示例 1: 输入: 2 输出: 1 解释: 2 = 1 +
     * 1, 1 × 1 = 1。 示例 2: 输入: 10 输出: 36 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 说明: 你可以假设 n 不小于 2 且不大于
     * 58。
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        dp[1] = 1;
        for (int i = 3; i < n + 1; i++) {
            for (int j = 1; j <= i / 2; j++) {
                // 这里，之所以要比较dp[i]是因为在遍历J的过程中可能发生最大值已经在dp数组中，这个递推公式要理解
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }
        return dp[n];
    }

    /**
     * 509. 斐波那契数 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。
     */
    public int fib(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            if (i == 0 || i == 1) {
                dp[i] = i;
            } else dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 746. 使用最小花费爬楼梯 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
     * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。 请你计算并返回达到楼梯顶部的最低花费。
     */
    public int minCostClimbingStairs(int[] cost) {
        // 确定dp数组及其下标的含义：dp数组表示到达第x阶的最低花费，索引即为第x阶
        int[] dp = new int[cost.length + 1];
        // 递推公式：dp[i] = min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2])
        for (int i = 0; i < cost.length + 1; i++) {
            if (i == 0) {
                dp[i] = 0;
            } else if (i == 1) {
                dp[i] = Math.min(dp[i - 1] + cost[i - 1], 0);
            } else {
                dp[i] = Math.min(dp[i - 1] + cost[i - 1], cost[i - 2] + dp[i - 2]);
            }
        }
        return dp[cost.length];
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int length = in.nextInt();
//        int[] nums = new int[length];
//        for (int i = 0; i < length; i++) {
//            nums[i] = in.nextInt();
//        }
//        int amount = in.nextInt();
//        System.out.println(new Dynamic().coinChange(nums, amount));
    }
}
