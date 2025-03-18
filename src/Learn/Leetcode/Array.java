package Learn.Leetcode;

import java.util.*;

public class Array {
    // 11.
    // 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
    // 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
    // 返回容器可以储存的最大水量。
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left <= right) {
            res = Math.max(Math.min(height[left], height[right]) * (right - left), res);
            if (height[left] < height[right]) {
                left++;
            } else right--;
        }
        return res;
    }

    // [15. 三数之和](https://leetcode.cn/problems/3sum/)
    private List<List<Integer>> twoSum(int[] nums, int start, int target, int flag) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            while (i != 0 && nums[i] == nums[i - 1]) {
                i++;
            }
            if (i >= nums.length - 2) {
                break;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] > 0) {
                break;
            } else if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] < 0) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    res.add(temp);
                    break;
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> temp = twoSum(nums, i + 1, -nums[i], nums[i]);
            if (!temp.isEmpty()) {
                res.addAll(new ArrayList<>(temp));
            }
        }
        return res;
    }

    // 41. 缺少的第一个正数
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums.length && nums[i] >= 0) {
                swap(nums, i, nums[i]);
            }
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    // 42. 接雨水
    // 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    public int trap(int[] height) {
        int n = height.length;
        int[] maxl = new int[n];
        int[] maxr = new int[n];
        for (int i = 0; i < n; i++) {
            maxl[i] = i == 0 ? height[0] : Math.max(height[i], maxl[i - 1]);
            maxr[n - i - 1] = i == 0 ? height[n - 1] : Math.max(height[n - i - 1], maxr[n - i]);
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            res += Math.min(maxl[i], maxr[i]) - height[i];
        }
        return res;
    }


    // 75.颜色分类
    public void swap(int[] num, int left, int right) {
        int temp = num[left];
        num[left] = num[right];
        num[right] = temp;
    }

    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int p = 0;
        while (p <= right) {
            if (nums[p] == 1) {
                p++;
            } else if (nums[p] == 0) {
                swap(nums, left, p);
                left++;
            } else {
                swap(nums, right, p);
                right--;
            }
            if (p < left) {
                p = left;
            }
        }
    }

    // 80. 删除有序数组中的重复项
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        int insertIndex = 1;
        int faster = 1;
        int length = 0;
        while (faster != nums.length) {
            if (nums[insertIndex - 1] == nums[faster]) {
                length++;
                if (length < 2) {
                    nums[insertIndex] = nums[faster];
                    insertIndex++;
                }
                faster++;
            } else {
                length = 0;
                nums[insertIndex] = nums[faster];
                insertIndex++;
                faster++;
            }
        }
        return insertIndex;
    }

    // 169.多数元素
    public int majorityElement(int[] nums) {
        int target = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                target = nums[i];
                count++;
            } else if (target != nums[i]) {
                count--;
            } else {
                count++;
            }
        }
        return target;
    }

    // [209. 长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum/)
    public int minSubArrayLen(int target, int[] nums) {
        int result = Integer.MAX_VALUE;
        int sum = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                result = Math.min(result, right - left + 1);
                sum = sum - nums[left];
                left++;
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    // 189. 轮转数组
    private void helper(int[] nums, int start, int end) {
        int left = start;
        int right = end - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
        System.out.println(Arrays.toString(nums));
    }

    public void rotate(int[] nums, int k) {
        helper(nums, nums.length - k, nums.length);
        helper(nums, 0, nums.length - k);
        helper(nums, 0, nums.length);
    }

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(
                intervals,
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[0] - o2[0];
                    }
                });
        // list.get(i) 代表生成的第i个区间 维度为一行两列 int[2]
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; i++) {
            if (merged.isEmpty() || intervals[i][0] > merged.getLast()[1]) {
                merged.add(intervals[i]);
            } else {
                merged.getLast()[2] = Math.max(merged.getLast()[2], intervals[i][1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * 713.乘积小于k的子数组
     *
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int count = 0;
        int ars = 1;
        for (int i = 0; i < nums.length; i++) {
            ars *= nums[i];
            if (ars >= k && left < nums.length) {
                ars /= nums[left];
                left++;
            }
            count += i - left + 1;
        }
        return count;
    }

    // 974. 和可被 K 整除的子数组
    // 给定一个整数数组 nums 和一个整数 k ，返回其中元素之和可被 k 整除的非空 子数组 的数目。
    // (si-sj)&k==0
    // si%k = sj%k
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int res = 0;
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            int val = sum % k;
            val = val < 0 ? val + k : val;
            if (map.containsKey(val)) {
                res += map.get(val);
                map.put(val, map.get(val) + 1);
            } else {
                map.put(val, 1);
            }
        }
        return res;
    }

    /**
     * 1004 连续 1 的最大个数
     * 给定一个二进制数组 nums 和一个整数 k，假设最多可以翻转 k 个 0 ，则返回执行操作后 数组中连续 1 的最大个数 。
     *
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroCount = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
                while (zeroCount > k) {
                    if (nums[left] == 0) {
                        zeroCount--;
                    }
                    left++;
                }
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    /**
     * 1094. 拼车
     * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
     * <p>
     * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
     * <p>
     * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        int min_start = Integer.MAX_VALUE;
        int max_end = Integer.MIN_VALUE;
        for (int i = 0; i < trips.length; i++) {
            int count = trips[i][0];
            int start = trips[i][1] + 1;
            int end = trips[i][2] + 1;
            diff[start] += count;
            diff[end] -= count;
            min_start = Math.min(min_start, start);
            max_end = Math.max(max_end, end);
        }
        for (int i = min_start; i <= max_end; i++) {
            diff[i] = diff[i - 1] + diff[i];
            if (diff[i] > capacity) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1109. 航班预订统计 这里有 n 个航班，它们分别从 1 到 n 进行编号。
     * <p>
     * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
     * <p>
     * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
     *
     * @param bookings 代表预定记录的二维数组
     * @param n        航班数
     * @return
     */

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n];
        for (int i = 0; i < bookings.length; i++) {
            int start = bookings[i][0] - 1;
            int count = bookings[i][2];
            diff[start] += count;
            if (bookings[i][1] != n) {
                int end = bookings[i][1];
                diff[end] -= count;
            }
        }
        for (int i = 1; i < diff.length; i++) {
            diff[i] = diff[i - 1] + diff[i];
        }
        return diff;
    }

    // 1124. 表现良好的最长时间段

    /**
     * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
     * <p>
     * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
     * <p>
     * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
     * <p>
     * 请你返回「表现良好时间段」的最大长度。
     *
     * @param hours
     * @return
     */
    public int longestWPI(int[] hours) {
        int sum = 0;
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        // si - sj > 0;
        // sj = si -1
        for (int i = 0; i < hours.length; i++) {
            sum += hours[i] > 8 ? 1 : -1;
            if (map.containsKey(sum)) {

            } else {
                map.put(sum, i);
            }
            if (sum > 0) {
                max = Math.max(max, i + 1);
            } else {
                if (map.containsKey(sum - 1)) {
                    max = Math.max(max, i - map.get(sum - 1));
                }
            }

        }
        return max;

    }

    /**
     * 1658. 将 x 减到 0 的最小操作数
     * 给你一个整数数组 nums 和一个整数 x 。
     * <p>
     * 每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。
     * <p>
     * 请注意，需要 修改 数组以供接下来的操作使用。
     */

    public int minOperations(int[] nums, int x) {
        int sum = Arrays.stream(nums).sum();
        int left = 0;
        int max = -1;
        int target = sum - x;
        if (target < 0) {
            return -1;
        }
        sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > target) {
                while (sum > target) {
                    sum -= nums[left];
                    left++;
                }
            }
            if (sum == target) {
                max = Math.max(max, i - left + 1);
            }
        }
        return max == -1 ? -1 : nums.length - max;
    }

    public static void main(String[] args) {
//        //test carPooling
//        int[][] trips = new int[][]{{2, 1, 5}, {3, 5, 7}};
//        int capacity = 3;
        Array array = new Array();
//        System.out.println(array.carPooling(trips, capacity));

        // 1658. 将 x 减到 0 的最小操作数
        int[] nums = new int[]{3, 4, -1, 1};
        System.out.println(array.firstMissingPositive(nums));


    }
}
