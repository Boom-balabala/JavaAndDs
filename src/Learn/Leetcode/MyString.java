package Learn.Leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyString {
    // 3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        int fast = 0;
        int slow = 0;
        int maxlength = 0;
        char[] str = s.toCharArray();
        Set<Character> myset = new HashSet<>();
        for (fast = 0; fast < str.length; fast++) {
            // 右边遇到重复元素，左边收缩
            if (myset.contains(str[fast])){
                while (str[slow]!=str[fast]){
                    myset.remove(str[slow]);
                    slow++;
                }
                slow++;
            }else{
                myset.add(str[fast]);
            }
            maxlength = Math.max(myset.size(),maxlength);
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

    public static void main(String[] args) {
        MyString myString = new MyString();
        int res = myString.KMP("hello", "ll");
        System.out.println(res);
    }
}
