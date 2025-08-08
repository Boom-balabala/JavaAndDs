package Learn.Leetcode;

import java.util.*;


public class Hash {
    class LRUCacheSimple {
        int capacity;
        LinkedHashMap<Integer, Integer> map;

        public LRUCacheSimple(int capacity) {
            map = new LinkedHashMap<>(capacity, 0.75f, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return map.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            map.put(key, value);
            if (map.size() > capacity) {
                map.pollFirstEntry();
            }
        }
    }

    class LRUCache {

        class Node {
            int key;
            int val;
            Node pre;
            Node next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
                pre = null;
                next = null;
            }
        }

        int size;
        int capacity;
        Map<Integer, Node> map;
        Node head;
        Node last;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            map = new HashMap<>();
            head = new Node(-1, -1);
            last = new Node(-1, -1);
            head.next = last;
            last.pre = head;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            // 移除尾部
            Node nxt = node.next;
            Node prev = node.pre;
            prev.next = nxt;
            nxt.pre = prev;
            // 插入头部
            Node headNext = head.next;
            node.next = headNext;
            headNext.pre = node;
            node.pre = head;
            head.next = node;
            return node.val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)){
                Node node = map.get(key);
                // 移除尾部
                Node nxt = node.next;
                Node prev = node.pre;
                prev.next = nxt;
                nxt.pre = prev;
                // 插入头部
                Node headNext = head.next;
                node.next = headNext;
                headNext.pre = node;
                node.pre = head;
                head.next = node;
                node.val = value;
                return;
            }
            Node node = new Node(key,value);
            map.put(key, node);
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
            size++;
            if (size > capacity) {
                Node removeNode = last.pre;
                removeNode.pre.next = last;
                last.pre = removeNode.pre;
                map.remove(removeNode.key);
                size--;
            }
        }
    }

    //[202. 快乐数](https://leetcode.cn/problems/happy-number/)
    private int get_sum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {
        Set<Integer> s = new HashSet<>();
        while (n != 1) {
            if (s.contains(n)) {
                return false;
            }
            s.add(n);
            n = get_sum(n);
        }
        return true;
    }

    //[242. 有效的字母异位词](https://leetcode.cn/problems/valid-anagram/)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> maps = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            maps.put(s.charAt(i), maps.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            maps.put(t.charAt(i), maps.getOrDefault(t.charAt(i), 0) - 1);
            if (maps.get(t.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    //[349. 两个数组的交集](https://leetcode.cn/problems/intersection-of-two-arrays/)
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> sets = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            sets.add(nums1[i]);
        }
        List<Integer> returns = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (sets.contains(nums2[i])) {
                returns.add(nums2[i]);
                sets.remove(nums2[i]);
            }
        }
        int[] res = new int[returns.size()];
        int j = 0;
        for (int i : returns) {
            res[j] = i;
            j++;
        }
        return res;
    }

    // [383. 赎金信](https://leetcode.cn/problems/ransom-note/)
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> mymap = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            if (mymap.containsKey(magazine.charAt(i))) {
                mymap.put(magazine.charAt(i), mymap.get(magazine.charAt(i)) + 1);
            } else
                mymap.put(magazine.charAt(i), 1);
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (!mymap.containsKey(ransomNote.charAt(i))) {
                return false;
            } else {
                if (mymap.get(ransomNote.charAt(i)) == 0) {
                    return false;
                }
                mymap.put(ransomNote.charAt(i), mymap.get(ransomNote.charAt(i)) - 1);
            }
        }
        return true;
    }

    // [454. 四数相加 II](https://leetcode.cn/problems/4sum-ii/)
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> mymap = new HashMap<>();
        for (int value : nums1) {
            for (int i : nums2) {
                int sum = value + i;
                if (!mymap.containsKey(sum)) {
                    mymap.put(sum, 1);
                } else {
                    mymap.put(sum, mymap.get(sum) + 1);
                }
            }
        }
        int count = 0;
        for (int j : nums3) {
            for (int k : nums4) {
                int sum = -j - k;
                if (mymap.containsKey(sum)) {
                    count += mymap.get(sum);
                }
            }
        }
        return count;
    }
}
