import com.sun.org.apache.regexp.internal.RE;

import java.util.*;

public class Main {

    static class Solution{
        /**
         * 字符串的排列
         * @param str
         * @return
         */
        public ArrayList<String> Permutation(String str) {
            HashSet<String> res=new HashSet<>();
            if (str.length()==0)
                return new ArrayList<>();
            else if (str.length()==1){
                res.add(str);
                return new ArrayList<>(res);
            }else{
                for (int i=0;i<str.length();i++){
                    String substr = str.substring(0,i).concat(str.substring(i+1));
                    for (String s:Permutation(substr)){
                        res.add(str.substring(i,i+1).concat(s));
                    }
                }
                ArrayList<String> re=new ArrayList<>(res);
                Collections.sort(re);
                return re;
            }
        }
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

        /**
         * 最小的k个数
         * @param input
         * @param k
         * @return
         */
        public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
            Arrays.sort(input);
            ArrayList<Integer> res=new ArrayList<>();
            if (input.length==0||k==0||k>input.length)
                return res;
            for (int i=0;i<k;i++){
                res.add(input[i]);
            }
            return res;
        }
        /**
         * 连续子数组最大和
         * @param array
         * @return
         */
        public int FindGreatestSumOfSubArray(int[] array) {
            if (array.length==0)
                return 0;
            int res=Integer.MIN_VALUE;
            int sum=array[0];
            for (int i=1;i<array.length;i++){
                if (sum+array[i]>0){
                    sum+=array[i];
                    res=(sum<res)?res:sum;
                }else
                    sum=0;
            }
            if (res==Integer.MIN_VALUE) {
                for (int i:array){
                    if (res<i)
                        res=i;
                }
            }
            return res;
        }
    }



    public static void main(String[] args) {
        Solution solution=new Solution();
        System.out.println(solution.FindGreatestSumOfSubArray(new int[]{-11,-2,-3}));
    }
}
