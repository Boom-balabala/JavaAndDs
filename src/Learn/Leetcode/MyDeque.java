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
        StringBuilder str = new StringBuilder();
        String[] slots = path.split("/+");
        for (String slot : slots) {
            if (Objects.equals(slot, ".") || slot == null || slot.isEmpty()) {
                continue;
            } else if (Objects.equals(slot, "..")) {
                if (!deque.isEmpty()) {
                    deque.pop();
                }
            } else {
                deque.push(slot);
            }
        }
/*
        path = path + '/';
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c != '/') {
                str.append(c);
            } else {
                if (str.isEmpty()) {
                    continue;
                } else {
                    if (String.valueOf(str).equals(".")) {
                        str.setLength(0);
                    } else if (String.valueOf(str).equals("..")) {
                        if (!deque.isEmpty()) {
                            deque.pop();
                        }
                        str.setLength(0);
                    } else {
                        deque.push(String.valueOf(str));
                        str.setLength(0);
                    }
                }
            }
        }
*/
        if (deque.isEmpty()) {
            return "/";
        }
        while (!deque.isEmpty()) {
            str.insert(0, "/" + deque.pop());
        }
        return String.valueOf(str.toString());
    }

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
