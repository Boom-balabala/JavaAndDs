package Learn.Leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

public class MyDeque {

    // 71 简化路径

    /**
     * 在 Unix 风格的文件系统中规则如下：
     * <p>
     * 一个点 '.' 表示当前目录本身。
     * 此外，两个点 '..' 表示将目录切换到上一级（指向父目录）。
     * 任意多个连续的斜杠（即，'//' 或 '///'）都被视为单个斜杠 '/'。
     * 任何其他格式的点（例如，'...' 或 '....'）均被视为有效的文件/目录名称。
     * 返回的 简化路径 必须遵循下述格式：
     * <p>
     * 始终以斜杠 '/' 开头。
     * 两个目录名之间必须只有一个斜杠 '/' 。
     * 最后一个目录名（如果存在）不能 以 '/' 结尾。
     * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        String[] strs = path.split("/");
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            if (str == null || str.isEmpty() || str.equals(".")) {
                continue;
            } else if (str.equals("..")) {
                if (!deque.isEmpty()) {
                    deque.pop();
                }
            } else {
                deque.push(str);
            }
        }
        if (deque.isEmpty()) {
            return "/";
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!deque.isEmpty()) {
            stringBuilder.insert(0, "/" + deque.pop());
        }
        return stringBuilder.toString();

    }

    // [150. 逆波兰表达式求值](https://leetcode.cn/problems/evaluate-reverse-polish-notation/)
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String str : tokens) {
            switch (str) {
                case "+" -> {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a + b);
                }
                case "-" -> {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                }
                case "*" -> {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a * b);
                }
                case "/" -> {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b / a);
                }
                case null, default -> stack.push(Integer.valueOf(str));
            }
        }
        return stack.pop();
    }

    /**
     * 155. 最小栈
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * <p>
     * 实现 MinStack 类:
     * <p>
     * MinStack() 初始化堆栈对象。
     * void push(int val) 将元素val推入堆栈。
     * void pop() 删除堆栈顶部的元素。
     * int top() 获取堆栈顶部的元素。
     * int getMin() 获取堆栈中的最小元素。
     */
    class MinStack {
        Deque<Integer> stack;
        Deque<Integer> minStack;

        public MinStack() {
            stack = new ArrayDeque<>();
            minStack = new ArrayDeque<>();
        }

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || minStack.peek() >= val) {
                minStack.push(val);
            }
        }

        public void pop() {
            int val = stack.pop();
            if (val == minStack.peek()) {
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
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
