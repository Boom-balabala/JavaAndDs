package Learn.Leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MyDeque {
    // [150. 逆波兰表达式求值](https://leetcode.cn/problems/evaluate-reverse-polish-notation/)
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                stack.push(stack.pop() + stack.pop());
            } else if ("-".equals(tokens[i])) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
            } else if ("*".equals(tokens[i])) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(tokens[i])) {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }

    // [239. 滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/)
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> pd = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        int left = 0;
        // init queue
        for (int i = 0; i < k; i++) {
            if (pd.isEmpty()) {
                pd.addLast(nums[i]);
            } else {
                if (pd.getLast() <= nums[i]) {
                    while (!pd.isEmpty() && pd.getLast() < nums[i]) {
                        pd.removeLast();
                    }
                    pd.addLast(nums[i]);
                } else {
                    pd.addLast(nums[i]);
                }
            }
        }
        res[0] = pd.getFirst();
        for (int i = k; i < nums.length; i++) {
            if (nums[left] == pd.getFirst()) {
                pd.removeFirst();
            }
            if (pd.isEmpty()) {
                pd.addLast(nums[i]);
            } else {
                if (nums[i] >= pd.getLast()) {
                    while (!pd.isEmpty() && pd.getLast() < nums[i]) {
                        pd.removeLast();
                    }
                    pd.addLast(nums[i]);
                } else {
                    pd.addLast(nums[i]);
                }
            }
            left++;
            res[left] = pd.getFirst();
        }
        return res;
    }

    //[1047. 删除字符串中的所有相邻重复项](https://leetcode.cn/problems/removeFirst-all-adjacent-duplicates-in-string/)
    public String removeFirstDuplicates(String s) {
        char[] stack = new char[s.length()];
        char[] input = s.toCharArray();
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            if (index == -1 || stack[index] != input[i]) {
                index++;
                stack[index] = input[i];
            } else {
                index--;
            }
        }
        return new String(stack, 0, index + 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 2, 0, 5};
        int k = 3;
        int[] res = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
