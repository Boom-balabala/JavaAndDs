package Learn.Leetcode;

import java.util.*;

public class Array {
    // [15. 三数之和](https://leetcode.cn/problems/3sum/)
    private List<List<Integer>> twoSum(int[] nums, int start, int target, int flag) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                temp.add(flag);
                temp.add(nums[left]);
                temp.add(nums[right]);
                res.add(new ArrayList<>(temp));
                temp.clear();
                right--;
                left++;
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            } else if (sum < target) {
                left++;
            } else {
                right--;
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
     * 阿里云笔试
     */

    public void test() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int max = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            queue.add(num);
            max = Math.max(max, num);
        }
        for (int i = 0; i < m; i++) {
            int temp = queue.remove() + in.nextInt();
            queue.add(temp);
            max = Math.max(max,temp);
            System.out.println(max);
        }
    }
    
    public static void main(String[] args) {
        new Array().test();
    }
}
