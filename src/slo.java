import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
class  Pair{
    int first;
    int second;
    public Pair(int f,int s){
        this.first =f;
        this.second =s;
    }
}
public class slo {

    private static Pair InvalidIndex(int []nums){
        Pair pair = new Pair(-1,-1);
        Stack<Pair> stack = new Stack<>();
        int n  =nums.length;
        for(int i =0;i<n;i++){
            Pair x = new Pair(nums[i],i);
            if(nums[i]>0){
                stack.push(x);
            }else{
                if(!stack.isEmpty()&&stack.peek().first==-nums[i]){
                    stack.pop();
                }else{
                    pair = new Pair(i,stack.peek().second);
                    return pair;
                }
            }
        }
        return pair;
    }
    private static boolean check(int []nums){
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0;i<nums.length;i++){
            int num = nums[i];
            if(num>0){
                stack.push(num);
            }else{
                if(!stack.isEmpty()&&stack.peek()!=-num){
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    private static void swap(int []nums,int i,int j ){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        int []nums = new int [n];
        for(int i = 0;i<n;i++){
            nums[i] = in.nextInt();
        }
        Pair pair = InvalidIndex(nums);
        swap(nums,pair.first,pair.first+1);
        System.out.println(pair.first +" "+pair.second);
        if(check(nums)){
            System.out.println((pair.first+1)+" "+(pair.first+2));
        }
        else {
            System.out.println((pair.second)+" "+(pair.second+1));
        }
    }
}
