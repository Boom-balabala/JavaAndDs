package Learn.Leetcode;

import java.util.*;

public class ListNode {
    // Definition for singly-linked list.
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    private int getsize(ListNode head) {
        ListNode temp = head;
        int size = 0;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        return size;
    }

    /**
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     */
    ListNode successor = null;

    // [24. 两两交换链表中的节点](https://leetcode.cn/problems/swap-nodes-in-pairs/)
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        ListNode fhead = new ListNode(-1, head);
        ListNode pointer = fhead;
        while (pointer.next != null && pointer.next.next != null) {
            ListNode temp = pointer.next.next.next;
            ListNode node1 = pointer.next;
            ListNode node2 = pointer.next.next;
            // step1
            pointer.next = node2;
            // step2
            node2.next = node1;
            // step3
            node1.next = temp;
            pointer = node1.next;
            pointer = node1;
        }
        return fhead.next;
    }


    // 23. 合并 K 个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>((ListNode o1, ListNode o2) -> o1.val - o2.val);
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.add(lists[i]);
            }
        }
        ListNode fakehead = new ListNode(-1);
        ListNode p = fakehead;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            if (node.next != null) {
                queue.add(node.next);
            }
            node.next = null;
            p.next = node;
            p = p.next;
        }
        return fakehead.next;
    }

    // 25 k个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode p = head;
        int size = 0;
        while (p != null) {
            p = p.next;
            size++;
        }
        if (size == 0 || size < k) {
            return head;
        }
        ListNode fakeHead = new ListNode();
        fakeHead.next = head;
        ListNode starter = fakeHead;
        ListNode end = head;
        ListNode pre = null;
        ListNode cur = head;
        while (size >= k) {
            int count = 0;
            while (count < k) {
                ListNode nxt = cur.next;
                cur.next = pre;
                pre = cur;
                cur = nxt;
                count++;
            }
            starter.next = pre;
            end.next = cur;
            starter = end;
            end = cur;
            size -= k;
        }
        return fakeHead.next;
    }

    // 61 旋转链表
    // 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
    public ListNode rotateRight(ListNode head, int k) {
        int length = 0;
        ListNode p = head;
        ListNode last = head;
        while (p != null) {
            length++;
            p = p.next;
            if (p != null) {
                last = last.next;
            }
        }
        if (length == 0 || length == 1 || k % length == 0) {
            return head;
        }
        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;
        ListNode pre = fakeHead;
        p = head;
        k = length - k % length;
        while (k != 0) {
            pre = pre.next;
            p = p.next;
            k--;
        }
        last.next = head;
        pre.next = null;
        return p;
    }

    private ListNode reverseHelper(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseHelper(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    // 82. 删除排序链表中的重复元素 II
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fh = new ListNode(-101);
        ListNode p = fh;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            while (fast != null && fast.val == slow.val) {
                fast = fast.next;
            }
            if (slow.next == fast) {
                p.next = slow;
                p = p.next;
                p.next = null;
            }
            slow = fast;
        }
        return fh.next;
    }

    // 86. 分隔链表
    public ListNode partition(ListNode head, int x) {
        ListNode greaterHead = new ListNode(-1);
        ListNode slowerHead = new ListNode(-1);
        ListNode gp = greaterHead;
        ListNode sp = slowerHead;
        ListNode p = head;
        while (p != null) {
            ListNode nxt = p.next;
            if (p.val >= x) {
                gp.next = p;
                p.next = null;
                p = nxt;
                gp = gp.next;
            } else {
                sp.next = p;
                p.next = null;
                p = nxt;
                sp = sp.next;
            }
        }
        sp.next = greaterHead.next;
        return slowerHead.next;
    }

    // 92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode fakehead = new ListNode(-1);
        fakehead.next = head;
        ListNode pre = fakehead;
        ListNode cur = head;
        while (cur != null && left > 1) {
            pre = pre.next;
            cur = cur.next;
            left--;
            right--;
        }
        ListNode lastleft = pre;
        ListNode firstright = cur;
        ListNode prev = null;
        while (cur != null && right > 0) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
            right--;
        }
        lastleft.next = prev;
        firstright.next = cur;
        return fakehead.next;
    }

    // [142. 环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    // [160. 相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int sizeA = getsize(headA);
        int sizeB = getsize(headB);
        if (sizeA == 0 || sizeB == 0) {
            return null;
        }
        ListNode pa = headA;
        ListNode pb = headB;
        if (sizeA > sizeB) {
            for (int i = 0; i < sizeA - sizeB; i++) {
                pa = pa.next;
            }
        } else {
            for (int i = 0; i < sizeB - sizeA; i++) {
                pb = pb.next;
            }
        }
        while (pa != null) {
            if (pa == pb) {
                return pa;
            } else {
                pa = pa.next;
                pb = pb.next;
            }
        }
        return null;
    }

    // [203. 移除链表元素](https://leetcode.cn/problems/remove-linked-list-elements/)
    public ListNode removeElements(ListNode head, int val) {
        ListNode fakehead = new ListNode();
        fakehead.next = head;
        ListNode front = fakehead;
        ListNode last = head;
        while (last != null) {
            if (last.val == val) {
                front.next = last.next;
            }
            front = front.next;
            last = front.next;
        }
        return fakehead.next;
    }

    // [206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/)
    // 迭代
    public ListNode reverseListIteration(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode aft = null;
        while (cur != null) {
            aft = cur.next;
            cur.next = pre;
            pre = cur;
            cur = aft;
        }
        return pre;
    }


    // 递归
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    // 234. 回文链表
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode counter = head;
        int size = 0;
        while (counter != null) {
            counter = counter.next;
            size++;
        }
        ListNode flag = head;
        int half = size / 2;
        int count = half;
        while (count != 0) {
            flag = flag.next;
            count--;
        }
        if (size % 2 != 0) {
            flag = flag.next;
        }
        ListNode starter = reverseList(flag);
        while (starter != null) {
            if (starter.val != head.val) {
                return false;
            } else {
                starter = starter.next;
                head = head.next;
            }
        }
        return true;
    }

    // 347. 前 K 个高频元素
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> myMap = new HashMap<>();
        for (int num : nums) {
            if (myMap.containsKey(num)) {
                myMap.put(num, myMap.get(num) + 1);
            } else {
                myMap.put(num, 1);
            }
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue =
                new PriorityQueue<>(
                        new Comparator<Map.Entry<Integer, Integer>>() {
                            @Override
                            public int compare(
                                    Map.Entry<Integer, Integer> o1,
                                    Map.Entry<Integer, Integer> o2) {
                                return (o1.getValue() - o2.getValue());
                            }
                        });
        for (Map.Entry<Integer, Integer> entry : myMap.entrySet()) {
            if (queue.size() < k) {
                queue.add(entry);
            } else {
                queue.add(entry);
                queue.poll();
            }
        }
        int[] res = new int[k];
        int i = 0;
        while ((!queue.isEmpty())) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            res[i] = entry.getKey();
            i++;
        }
        return res;
    }

    // 876. 链表的中间结点
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // 143 重排链表
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        LinkedList<ListNode> lists = new LinkedList<>();
        ListNode p = head.next;
        while (p != null) {
            lists.add(p);
            p = p.next;
        }
        p = head;
        while (!lists.isEmpty()) {
            p.next = lists.removeLast();
            p = p.next;
            if (!lists.isEmpty()) {
                p.next = lists.removeFirst();
                p = p.next;
            }
        }
        p.next = null;
        return;
    }

    public ListNode reverseList(ListNode head) {
        return reverse(head);
    }

    public static void main(String[] args) {
        // test reverse kgroup lists
        ListNode head = new ListNode(1);
        ListNode p = head;
        for (int i = 2; i <= 5; i++) {
            p.next = new ListNode(i);
            p = p.next;
        }
        ListNode res = new ListNode().reverseKGroup(head, 2);
        // print
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
