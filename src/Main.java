import java.util.Stack;

public class Main {

    static class Solution{
        /**
         * 数组中出现次数超过一半的数字
         * @param array
         * @return
         */
        public int MoreThanHalfNum_Solution(int [] array) {
            Stack<Integer> stack=new Stack<>();
            int res;
            int count=0;
            for (int i:array){
                if (stack.empty())
                    stack.push(i);
                else{
                    if (stack.peek()!=i)
                        stack.pop();
                    else
                        stack.push(i);
                }
            }
            if (stack.empty())
                return 0;
            else
                res=stack.peek();
            for (int i:array){
                if(i==res)
                    count++;
            }
            return (count>(array.length/2))?res:0;
        }
    }

    public static void main(String[] args) {
        Solution solution=new Solution();
        System.out.println(solution.MoreThanHalfNum_Solution(new int[]{1,2,3,2,4,2,5,2,3}));
    }
}
