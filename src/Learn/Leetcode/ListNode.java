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

    // 23. 合并 K 个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue =
                new PriorityQueue<>(
                        new Comparator<ListNode>() {
                            @Override
                            public int compare(ListNode o1, ListNode o2) {
                                return o1.val - o2.val;
                            }
                        });
        ListNode res = new ListNode(-1);
        ListNode p = res;
        for (ListNode list : lists) {
            if (list != null) {
                priorityQueue.add(list);
            }
        }
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.remove();
            if (node.next != null) {
                priorityQueue.add(node.next);
            }
            node.next = null;
            p.next = node;
            p = p.next;
        }
        return res.next;
    }

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

    public static void main(String[] args) {
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
    private ListNode reverse(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ListNode tmp = cur.next;
        cur.next = pre;
        return reverse(tmp, cur);
    }

    public ListNode reverseList(ListNode head) {
        return reverse(head, null);
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
}
