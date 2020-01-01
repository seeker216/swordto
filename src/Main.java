import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

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

        /**
         * 1出现的次数
         * @param n
         * @return
         */
        public int NumberOf1Between1AndN_Solution(int n) {
            int count=0;
            for (int m=1;m<=n;m*=10){
                int a=n/m,b=n%m;
                count+=(a+8)/10*m+(a%10==1?b+1:0);
            }
            return count;
        }

        /**
         * 把数组排成最小的数
         * @param numbers
         * @return
         */
        public String PrintMinNumber(int [] numbers) {
            String res="";
            for(int i=0; i < numbers.length; i++){
                for(int j = i+1; j < numbers.length; j++){
                    int sum1 = Integer.valueOf(numbers[i]+""+numbers[j]);
                    int sum2 = Integer.valueOf(numbers[j]+""+numbers[i]);
                    if(sum1 > sum2){
                        int temp = numbers[j];
                        numbers[j] = numbers[i];
                        numbers[i] = temp;
                    }
                }
            }
            for (int i:numbers)
                res=res.concat(String.valueOf(i));
            return res;
        }

        /**
         * 丑数
         * @param index
         * @return
         */
        public int GetUglyNumber_Solution(int index) {
            if (index<=0)
                return 0;
            int[] uglyNum=new int[index];
            int p2=0,p3=0,p5=0;
            uglyNum[0]=1;
            for (int i=1;i<index;i++){
                uglyNum[i]=Math.min(uglyNum[p2]*2,Math.min(uglyNum[p3]*3,uglyNum[p5]*5));
                if (uglyNum[i]==uglyNum[p2]*2)
                    p2++;
                if (uglyNum[i]==uglyNum[p3]*3)
                    p3++;
                if (uglyNum[i]==uglyNum[p5]*5)
                    p5++;
            }
            return uglyNum[index-1];
        }

        /**
         * 第一个只出现一次的字符
         * @param str
         * @return
         */
        public int FirstNotRepeatingChar(String str) {
            if (str==null||str=="")
                return -1;
            Map<Character,Integer> map=new LinkedHashMap<>();
            for(int i=0;i<str.length();i++){
                if (map.containsKey(str.charAt(i))){
                    int count=map.get(str.charAt(i));
                    map.put(str.charAt(i),count+1);
                }else {
                    map.put(str.charAt(i),1);
                }
            }
            for (Map.Entry<Character,Integer> entry:map.entrySet()){
                if (entry.getValue()==1){
                    for (int i=0;i<str.length();i++)
                        if (str.charAt(i)==entry.getKey())
                            return i;
                }
            }
            return -1;
        }

        /**
         * 数组中的逆序对
         * @param array
         * @return
         */
        public int InversePairs(int [] array) {
            if (array.length<=0)
                return 0;
            mergeSort(array,0,array.length-1);
            return count;
        }
        private int count=0;
        private void mergeSort(int[] array,int start,int end){
            if (start>=end)
                return;
            int mid=(start+end)/2;
            mergeSort(array,start,mid);
            mergeSort(array,mid+1,end);
            mergeOne(array,start,mid,end);
        }
        private void mergeOne(int[] array,int start,int mid,int end){
            int[] temp=new int[end-start+1];
            int k=0,i=start,j=mid+1;
            while(i<=mid && j<= end){
                if(array[i] <= array[j])
                    temp[k++] = array[i++];
                else{
                    temp[k++] = array[j++];
                    count = (count + (mid-i+1))%1000000007;
                }
            }
            while(i<= mid)
                temp[k++] = array[i++];
            while(j<=end)
                temp[k++] = array[j++];
            for(int l=0; l<k; l++){
                array[start+l] = temp[l];
            }
        }

        /**
         * 两个链表的第一个公共结点
         * @param pHead1
         * @param pHead2
         * @return
         */
         public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
             if (pHead1==null||pHead2==null)
                 return null;
             Set<Integer> set=new LinkedHashSet<>();
             while (pHead1!=null){
                 set.add(pHead1.val);
                 pHead1=pHead1.next;
             }
             while (pHead2!=null){
                 if (set.contains(pHead2.val))
                     return pHead2;
                 else
                     pHead2=pHead2.next;
             }
             return null;
         }

        /**
         * 统计一个数字在排序数组中出现的次数
         * @param array
         * @param k
         * @return
         */
        public int GetNumberOfK(int [] array , int k) {
            int cnt=0;
            for (int i:array){
                if (i==k)
                    cnt++;
            }
            return cnt;
        }

        /**
         * 二叉树的深度
         * @param root
         * @return
         */
        public int TreeDepth(TreeNode root) {
            if (root==null)
                return 0;
            else if (root.left==null&&root.right==null)
                return 1;
            else if (root.left==null&&root.right!=null)
                return 1+TreeDepth(root.right);
            else if (root.left!=null&&root.right==null)
                return 1+TreeDepth(root.left);
            else
                return 1+Math.max(TreeDepth(root.left),TreeDepth(root.right));
        }

        /**
         * 平衡二叉树
         * @param root
         * @return
         */
        public boolean IsBalanced_Solution(TreeNode root) {
            if(root==null||(root.left==null&&root.right==null))
                return true;
            int leftDepth=TreeDepth(root.left);
            int rightDepth=TreeDepth(root.right);
            if (leftDepth==rightDepth||leftDepth-rightDepth==1||rightDepth-leftDepth==1)
                return true;
            else
                return false;
        }

        /**
         * 数组中只出现一次的数字
         * @param array
         * @param num1
         * @param num2
         */
        public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
            Set<Integer> set=new HashSet<>();
            for(int i:array){
                if (set.contains(i))
                    set.remove(i);
                else
                    set.add(i);
            }
            Iterator<Integer> iterator=set.iterator();
            num1[0]=iterator.next();
            num2[0]=iterator.next();
            return;
        }

        /**
         * 和为S的连续正数序列
         * @param sum
         * @return
         */
        public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
            ArrayList<ArrayList<Integer>> res=new ArrayList<>();
            if (sum<3)
                return res;
            int plow=1,phigh=2;
            while (phigh>plow){
                int cur=(phigh+plow)*(phigh-plow+1)/2;
                if (cur==sum){
                    ArrayList<Integer> seq=new ArrayList<>();
                    for (int i=plow;i<=phigh;i++)
                        seq.add(i);
                    res.add(seq);
                    plow++;
                }else if (cur>sum)
                    plow++;
                else
                    phigh++;
            }
            return res;
        }
        /**
         * 和为S的两个数字
         * @param array
         * @param sum
         * @return
         */
        public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
            Set<Integer> set=new HashSet<>();
            int product=Integer.MAX_VALUE;
            ArrayList<Integer> res=new ArrayList<>();
            for (int i:array){
                if (set.contains(sum-i)&&(i*(sum-i)<product)){
                    res.clear();
                    res.add(sum-i);
                    res.add(i);
                    product=i*(sum-i);
                }else
                    set.add(i);
            }
            return res;
        }

        /**
         * 左旋转字符串
         * @param str
         * @param n
         * @return
         */
        public String LeftRotateString(String str,int n) {
            return n>str.length()?"":str.substring(n).concat(str.substring(0,n));
        }

        /**
         * 翻转单词顺序列
         * @param str
         * @return
         */
        public String ReverseSentence(String str) {
            if (str==null||str.length()==0)
                return "";
            Stack<String> stack=new Stack<>();
            String[] words=str.split(" ");
            if (words.length==0)
                return str;
            for (String s:words)
                stack.push(s);
            String res="";
            while (!stack.empty()){
                res=res.concat(stack.pop()+" ");
            }
            return res.substring(0,res.length()-1);
        }

        /**
         * 扑克牌顺子
         * @param numbers
         * @return
         */
        public boolean isContinuous(int [] numbers) {
            if (numbers.length!=5)
                return false;
            Arrays.sort(numbers);
            int num0=0;
            int pre=-1;
            for (int i=0;i<numbers.length;i++){
                if (numbers[i]==0)
                    num0++;
                else if (pre==-1)
                    pre=numbers[i];
                else if (pre==numbers[i])
                    return false;
                else if (numbers[i]==pre+1)
                    pre=numbers[i];
                else if (num0!=0&&(numbers[i]<=(pre+1+num0))) {
                    num0--;
                    pre=numbers[i];
                }
                else
                    return false;
            }
            return true;
        }

        /**
         * 圆圈中最后剩下的数
         * @param n
         * @param m
         * @return
         */
        public int LastRemaining_Solution(int n, int m) {
            if (n==0||m==0)
                return -1;
            ListNode head=new ListNode(0);
            ListNode pHead=head;
            while (head.val<n-1){
                head.next=new ListNode(head.val+1);
                head=head.next;
            }
            head.next=pHead;
            while (pHead.next.val!=pHead.val){
                for (int i=0;i<m-2;i++)
                    pHead=pHead.next;
                pHead.next=pHead.next.next;
                pHead=pHead.next;
            }

            return pHead.val;
        }
    }





    public static void main(String[] args) {
        Solution solution=new Solution();

        System.out.println(solution.LastRemaining_Solution(5,2));
    }
}
