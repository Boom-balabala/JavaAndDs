import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int [][]intervals= new int[n][2];
        for(int i = 0;i<n;i++){
            intervals[i][0] = in.nextInt();
            intervals[i][1] = in.nextInt();
        }
        Arrays.sort(intervals,(a,b)->{
            if (a[0]!=b[0]){
                return Integer.compare(a[0],b[0]);
            }else{
                return Integer.compare(a[1],b[1]);
            }
        });
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int res = 0;
        for(int i = 0;i<n;i++){
            int left = intervals[i][0];
            int right = intervals[i][1];
            while(!heap.isEmpty()&&heap.peek()<left){
                heap.poll();
            }
            res+= heap.size();
            heap.offer(right);

        }
        System.out.println(res);

    }
}