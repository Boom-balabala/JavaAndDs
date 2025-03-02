package Learn.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    public static void main(String[] args) {
        Array array = new Array();
        // [-1,-100,3,99]
        int[] nums = {-1, -100, 3, 99};
        array.rotate(nums, 2);
    }
}
