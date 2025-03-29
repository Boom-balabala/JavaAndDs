package Learn.Leetcode;

import javax.swing.event.MenuDragMouseListener;
import java.util.HashMap;

public class LRUCache {
    int capacity;
    ListNode head;
    ListNode end;
    HashMap<Integer, ListNode> map;

    private class ListNode {
        ListNode prv, nxt;
        int value;
        int key;
        ListNode(int key,int val) {
            this.key = key;
            this.value = val;
            prv = null;
            nxt = null;
        }

    }


    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new ListNode(-1,-1);
        end = new ListNode(-1,-1);
        head.nxt = end;
        end.prv = head;
        map = new HashMap<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        // 找到当前节点
        ListNode node = map.get(key);
        // 获取前驱和后继节点
        ListNode pre = node.prv;
        ListNode next = node.nxt;
        // 更新前驱节点的下一个元素
        pre.nxt = next;
        // 更新后继节点的前一个元素
        next.prv = pre;
        // 更新当前节点的前驱和后继
        node.prv = head;
        node.nxt = head.nxt;
        // 更新原本头节点前驱后继信息
        head.nxt.prv = node;
        head.nxt = node;
        return node.value;
    }

    public void put(int key, int value) {
        // 存在则移动到队列首部
        if (map.containsKey(key)) {
            // 找到当前节点
            ListNode node = map.get(key);
            // 获取前驱和后继节点
            ListNode pre = node.prv;
            ListNode next = node.nxt;
            // 更新前驱节点的下一个元素
            pre.nxt = next;
            // 更新后继节点的前一个元素
            next.prv = pre;
            // 更新当前节点的前驱和后继
            node.prv = head;
            node.nxt = head.nxt;
            // 更新原本头节点前驱后继信息
            head.nxt.prv = node;
            head.nxt = node;
        }else{
            //不存在首先判断是否有容量
            if (map.size()==capacity){
                // 没有容量逐出最后一个
                ListNode cur = end.prv;
                ListNode pre = cur.prv;
                pre.nxt = end;
                end.prv = pre;
                cur.nxt =null;
                cur.prv= null;
                map.remove(cur.key);
                cur =null;
            }
            ListNode node = new ListNode(key,value);
            node.prv = head;
            node.nxt = head.nxt;
            head.nxt.prv = node;
            head.nxt = node;
            map.put(key,node);
        }
    }

}
