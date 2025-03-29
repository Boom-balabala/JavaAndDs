import Learn.Leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

public class test {
    class Node{
        int val;
        Node next;
        public Node(){

        }
        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }
    public  void testReverse(){
        Node head = new Node(1);
        Node p = head;
        for(int i = 2;i<6;i++){
            p.next = new Node(i);
            p = p.next;
        }
        Node pre = null;
        Node cur = head;
        while(cur!=null){
            Node nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        Node p2  = pre;
        while(p2!=null){
            System.out.println(p2.val);
            p2= p2.next;
        }
    }
    public static void main(String[]args){
        test main = new test();
        main.testReverse();
    }



}
