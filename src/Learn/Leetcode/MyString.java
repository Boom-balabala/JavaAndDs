package Learn.Leetcode;

import java.util.*;

public class MyString {
    /**
     * 3.给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxlength = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
            } else {
                int prev = map.remove(s.charAt(i));
                // 如果当前节点在滑动窗口内
                if (left <= prev) {
                    left = prev + 1;
                }
                map.put(s.charAt(i), i);
            }
            maxlength = Math.max(maxlength, i-left+1);
        }
        return maxlength;
    }

    // [28.
    // 找出字符串中第一个匹配项的下标](https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/)
    // Todo: KMP implement
    public int[] get_prefix(String s) {
        int[] index = new int[s.length()];
        int j = 0; // 前缀末尾
        index[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = index[j - 1]; // 向前回退，看前一位next数组的值
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
                index[i] = j;
            }
        }
        return index;
    }

    public int KMP(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        } else if (needle.isEmpty()) {
            return 0;
        }
        int[] index = get_prefix(needle);
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (haystack.charAt(i) != needle.charAt(j)) {
                j = index[j];
            }
        }
        return j;
    }

    //136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        if (nums.length==1){
            return nums[0];
        }
        int res = nums[0];
        for (int i =1;i<nums.length;i++){
            res = res ^ nums[i];
        }
        return res;
    }
    // [344. 反转字符串](https://leetcode.cn/problems/reverse-string/)
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 438. 找到字符串中所有字母异位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     */
    private boolean judge(int[] nums) {
        for (int num : nums) {
            if (num != Integer.MIN_VALUE && num != 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int notExists = Integer.MIN_VALUE;
        int[] need = new int[26];
        List<Integer> res = new ArrayList<>();
        Arrays.fill(need, notExists);
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            need[p.charAt(i) - 'a'] = need[p.charAt(i) - 'a'] == notExists ? 1 : need[p.charAt(i) - 'a'] + 1;
        }
        int left = 0;
        int window = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            window = i - left + 1;
            // 不存在，窗口收缩
            if (need[index] == notExists) {
                for (int j = left; j < i; j++) {
                    char back = s.charAt(j);
                    need[back - 'a']++;
                }
                left = i + 1;
                continue;
            }
            need[index]--;
            if (window == p.length()) {
                if (need[index] == 0) {
                    if (judge(need)) {
                        res.add(left);
                    }
                }
                need[s.charAt(left) - 'a']++;
                left++;
            }
        }
        return res;
    }

    // [541. 反转字符串 II](https://leetcode.cn/problems/reverse-string-ii/)
    private void reversePartString(char[] s, int left, int right) {
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public String reverseStr(String s, int k) {
        char[] s_arr = s.toCharArray();
        int right = s.length() - 1;
        int pointer = 0;
        while (right - pointer >= 2 * k) {
            reversePartString(s_arr, pointer, pointer + k - 1);
            pointer += 2 * k;
        }
        if (right - pointer >= k) {
            reversePartString(s_arr, pointer, pointer + k - 1);
        } else {
            reversePartString(s_arr, pointer, right);
        }
        return new String(s_arr);
    }

    // 32 最长有效括号子串的长度
    // 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
    public int longestValidParentheses(String s) {
        return 0;
    }

    public static void main(String[] args) {
        MyString myString = new MyString();
        System.out.println(myString.findAnagrams("abab", "ab"));
    }
}
