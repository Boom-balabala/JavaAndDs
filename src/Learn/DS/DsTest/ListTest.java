package Learn.DS.DsTest;

import Learn.DS.DSClass.MyLinkList;

import Learn.DS.interfacePackage.Queue;
import org.junit.Test;

import java.util.Scanner;

public class ListTest {
    @Test
    public void addTest() {
        MyLinkList<Integer> list = new MyLinkList<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            int index = in.nextInt();
            int val = in.nextInt();
            if (index == -1) {
                break;
            } else {
                list.add(val, index);
            }
            list.print();
        }
    }

    @Test
    public void removeTest() {
        MyLinkList<Integer> list = new MyLinkList<>();
        for (int i = 0; i < 10; i++) {
            list.addLast(i);
        }
        list.print();
        Scanner in = new Scanner(System.in);
        while (true) {
            int index = in.nextInt();
            if (index == -1) {
                break;
            } else {
                list.remove(index);
            }
            list.print();
        }
    }

    @Test
    public void queueTest() {
        Queue<Integer> queue = new MyLinkList<>();
        for (int i = 0; i < 10; i++) {
            queue.addQueue(i);
            queue.printQueue();
            }
        while (!queue.isEmpty()) {
            queue.removeQueue();
            queue.printQueue();
        }
    }
}
