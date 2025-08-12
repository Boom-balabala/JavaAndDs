package Learn.Leetcode;

import java.util.*;

/**
 * @author WYL
 */
public class TraceBack {

    /**
     * [17. 电话号码的字母组合](<a
     * href="https://leetcode.cn/problems/letter-combinations-of-a-phone-number/">...</a>) 给定一个仅包含数字
     * 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     */
    private String[] numberToChar = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> letterCombinationsRes;

    public void letterCombinationsHelper(String digits, int starter, StringBuilder temp) {
        if (starter == digits.length()) {
            if (temp.isEmpty()) {
                return;
            } else {
                letterCombinationsRes.add(temp.toString());
                return;
            }
        }
        int index = digits.charAt(starter) - '0';
        String str = numberToChar[index];
        for (int j = 0; j < str.length(); j++) {
            temp.append(str.charAt(j));
            letterCombinationsHelper(digits, starter + 1, temp);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        letterCombinationsRes = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        letterCombinationsHelper(digits, 0, stringBuilder);
        return letterCombinationsRes;
    }

    // 22. 括号生成
    List<String> generateParenthesisRes = new ArrayList<>();

    public void generateParenthesisHelper(int n, int leftCount, int rightCount, StringBuilder sb) {
        if (leftCount == n && rightCount == n) {
            generateParenthesisRes.add(sb.toString());
            return;
        }
        if (leftCount > n||rightCount>leftCount) {
            return;
        }
        sb.append("(");
        generateParenthesisHelper(n, leftCount + 1, rightCount, sb);
        sb.deleteCharAt(sb.length() - 1);
        if (leftCount > rightCount) {
            sb.append(")");
            generateParenthesisHelper(n, leftCount, rightCount + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        generateParenthesisHelper(n, 0, 0, new StringBuilder());
        return generateParenthesisRes;
    }

    /**
     * [39. 组合总和](<a href="https://leetcode.cn/problems/combination-sum/">...</a>) 给你一个 无重复元素 的整数数组
     * candidates 和一个目标整数 target ， 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。 你可以按 任意顺序
     * 返回这些组合。
     *
     * <p>candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     */
    private void combinationSum(
            List<List<Integer>> res,
            List<Integer> temp,
            int startIndex,
            int[] candidates,
            int target,
            int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        } else {
            for (int i = startIndex; i < candidates.length; i++) {
                if (sum + candidates[i] > target) {
                    return;
                } else {
                    temp.add(candidates[i]);
                    combinationSum(res, temp, i, candidates, target, sum + candidates[i]);
                    temp.removeLast();
                }
            }
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(res, temp, 0, candidates, target, 0);
        return res;
    }

    /**
     * 40. 组合总和 II candidates 中的每个数字在每个组合中只能使用 一次 。 注意：解集不能包含重复的组合。
     */
    private void combinationSum2(
            List<List<Integer>> res,
            List<Integer> temp,
            int startIndex,
            int[] candidates,
            int target,
            int sum) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        } else {
            for (int i = startIndex; i < candidates.length; i++) {
                if (i > startIndex && candidates[i - 1] == candidates[i]) {
                    continue;
                }
                if (sum + candidates[i] > target) {
                    return;
                } else {
                    temp.add(candidates[i]);
                    combinationSum2(res, temp, i + 1, candidates, target, sum + candidates[i]);
                    temp.removeLast();
                }
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum2(res, temp, 0, candidates, target, 0);

        return res;
    }

    /**
     * 46.
     *
     * <p>全排列 给定一 个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     */
    private boolean[] permuteIsUsed;
    List<List<Integer>> permuteRes = new ArrayList<>();

    private void permuteHelper(int[] nums, List<Integer> temp) {
        if (temp.size() == nums.length) {
            permuteRes.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (permuteIsUsed[i]) {
                continue;
            }
            permuteIsUsed[i] = true;
            temp.add(nums[i]);
            permuteHelper(nums, temp);
            temp.removeLast();
            permuteIsUsed[i] = false;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        permuteIsUsed = new boolean[nums.length];
        permuteHelper(nums, new ArrayList<>());
        return permuteRes;
    }

    /**
     * [77. 组合](<a href="https://leetcode.cn/problems/combinations/">...</a>) 给定两个整数 n 和 k，返回范围 [1,
     * n] 中所有可能的 k 个数的组合。
     */
    List<List<Integer>> combineRes = new ArrayList<>();

    public void combineHelper(int n, int k, int starter, List<Integer> temp) {
        if (temp.size() == k) {
            combineRes.add(new ArrayList<>(temp));
            return;
        } else if (k - temp.size() > n - starter + 1) {
            return;
        }
        temp.add(starter);
        combineHelper(n, k, starter + 1, temp);
        temp.removeLast();
        combineHelper(n, k, starter + 1, temp);
    }

    public List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1, new ArrayList<>());
        return combineRes;
    }

    /**
     * 78. 子集 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     */
    private void subsets(List<List<Integer>> res, List<Integer> temp, int[] nums, int startIndex) {
        res.add(new ArrayList<>(temp));
        for (int i = startIndex; i < nums.length; i++) {
            temp.add(nums[i]);
            subsets(res, temp, nums, i + 1);
            temp.removeLast();
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        subsets(res, temp, nums, 0);
        return res;
    }

    /**
     * 90. 子集 II 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     */
    private void subsetsWithDup(
            List<List<Integer>> res, List<Integer> temp, int[] nums, int startIndex) {
        res.add(new ArrayList<>(temp));
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            subsetsWithDup(res, temp, nums, i + 1);
            temp.removeLast();
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDup(res, temp, nums, 0);
        return res;
    }

    /**
     * 93. 复原 IP 地址 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     *
     * <p>例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址， 但是 "0.011.255.245"、"192.168.1.312" 和
     * "192.168@1.1" 是 无效 IP 地址。 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.'
     * 来形成。 你 不能 重新排序或删除 s 中的任何数字。 你可以按 任何 顺序返回答案。
     */
    public void restoreIpAddresses(List<String> res, String s, List<String> temp, int startIndex) {
        if (temp.size() == 4 && startIndex == s.length()) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < temp.size(); i++) {
                str.append(temp.get(i));
                if (i != temp.size() - 1) {
                    str.append(".");
                }
            }
            res.add(str.toString());
            return;
        }
        // GPT剪枝：如果段数已经超过4段，则直接返回
        if (temp.size() >= 4) {
            return;
        }
        // GPT剪枝：如果剩余字符过多或过少，不可能构成有效IP地址
        int remainingChars = s.length() - startIndex;
        if (remainingChars > (4 - temp.size()) * 3 || remainingChars < (4 - temp.size())) {
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String tep = s.substring(startIndex, i + 1);
            if (s.charAt(startIndex) == '0' && tep.length() > 1) {
                return;
            }
            if (tep.length() > 3 || Integer.parseInt(tep) > 255) {
                return;
            }
            temp.add(tep);
            restoreIpAddresses(res, s, temp, i + 1);
            temp.removeLast();
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        restoreIpAddresses(res, s, temp, 0);
        return res;
    }

    /**
     * [131. 分割回文串](<a href="https://leetcode.cn/problems/palindrome-partitioning/">...</a>)
     */
    List<List<String>> res131 = new ArrayList<>();

    private boolean isReaver(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void rever(String s, int startIndex, List<String> temp) {
        if (startIndex == s.length()) {
            res131.add(new ArrayList<>(temp));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String str = s.substring(startIndex, i + 1);
            if (isReaver(str)) {
                temp.add(str);
                rever(s, i + 1, temp);
                temp.removeLast();
            }
        }
    }

    public List<List<String>> partition(String s) {
        rever(s, 0, new ArrayList<>());
        return res131;
    }

    /**
     * [216. 组合总和 III](<a href="https://leetcode.cn/problems/combination-sum-iii/">...</a>)
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     *
     * <p>只使用数字1到9 每个数字 最多使用一次 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     */
    private void combinationSum3(
            List<List<Integer>> res, List<Integer> temp, int startIndex, int k, int n, int sum) {
        if (sum == n && temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            if (sum + i > n || temp.size() >= k) {
                return;
            } else {
                temp.add(i);
                combinationSum3(res, temp, i + 1, k, n, sum + i);
                temp.removeLast();
            }
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        combinationSum3(res, temp, 1, k, n, 0);
        return res;
    }

    /**
     * 491. 非递减子序列 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素 。 你可以按 任意顺序 返回答案。
     * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
     */
    private void findSubsequences(
            List<List<Integer>> res, List<Integer> tep, int startIndex, int[] nums) {
        if (tep.size() >= 2) {
            res.add(new ArrayList<>(tep));
        }
        int[] used = new int[201];
        for (int i = startIndex; i < nums.length; i++) {
            if (tep.isEmpty() || nums[i] >= tep.getLast()) {
                if (used[nums[i] + 100] == 0) {
                    used[nums[i] + 100] = 1;
                    tep.add(nums[i]);
                    findSubsequences(res, tep, i + 1, nums);
                    tep.removeLast();
                }
            }
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tep = new ArrayList<>();
        findSubsequences(res, tep, 0, nums);
        return res;
    }

    public static void main(String[] args) {
        TraceBack test = new TraceBack();
        String temp = "efe";
        System.out.println(test.partition(temp));
    }
}
