import jdk.nashorn.internal.ir.Flags;
import jdk.nashorn.internal.ir.ReturnNode;
import sun.plugin2.os.windows.FLASHWINFO;

import java.lang.management.PlatformLoggingMXBean;
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

    public static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
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

        /**
         * 求1到n的和
         * @param n
         * @return
         */
        public int Sum_Solution(int n) {
            int sum=n;
            boolean res = (n>0) && ((sum+=Sum_Solution(n-1))>0);
            return sum;
        }

        /**
         * 不用加减乘除求和
         * @param num1
         * @param num2
         * @return
         */
        public int Add(int num1,int num2) {
            int sum=0,carry=0;
            do {
                sum=num1^num2;
                carry=(num1&num2)<<1;
                num1=sum;
                num2=carry;
            }while (num2!=0);
            return sum;
        }

        /**
         * 字符串转整数
         * @param str
         * @return
         */
        public int StrToInt(String str) {
            int sum=0;
            boolean neg=false;
            if (str.length()==0)
                return 0;
            if (str.charAt(0)=='-')
                neg=true;
            if (str.charAt(0)=='+'||str.charAt(0)=='-')
                str=str.substring(1);
            for (int i=0;i<str.length();i++){
                if ((!neg&&sum==214748364&&str.charAt(i)>55)||sum>214748364||(neg&&sum==214748364&&str.charAt(i)>56))
                    return 0;
                if (str.charAt(i)>=48&&str.charAt(i)<=57) {
                    sum *= 10;
                    sum += (str.charAt(i)-48);
                }
                else
                    return 0;
            }
            return neg?-sum:sum;
        }

        /**
         * 数组中重复的数字
         * @param numbers
         * @param length
         * @param duplication
         * @return
         */
        public boolean duplicate(int numbers[],int length,int [] duplication) {
            Set<Integer> set=new HashSet<>();
            for (int i=0;i<length;i++){
                if (set.contains(numbers[i])){
                    duplication[0]=numbers[i];
                    return true;
                }else
                    set.add(numbers[i]);
            }
            return false;
        }

        /**
         * 构建乘积数组
         * @param A
         * @return
         */
        public int[] multiply(int[] A) {
            int[] B=new int[A.length];
            if (A.length==0)
                return B;
            B[0]=1;
            for (int i=1;i<A.length;i++){
                B[i]=B[i-1]*A[i-1];
            }
            int tmp=1;
            for (int j=A.length-2;j>-1;j--){
                tmp*=A[j+1];
                B[j]*=tmp;
            }
            return B;
        }

        /**
         * 正则表达式匹配
         * @param str
         * @param pattern
         * @return
         */
        public boolean match(char[] str, char[] pattern)
        {
            return matchStr(str,0,pattern,0);
        }
        private boolean matchStr(char[] str,int i,char[] pattern,int j){
            if (i==str.length&&j==pattern.length)//都到头
                return true;
            else if (j==pattern.length)//str没到头，pattern到头
                return false;

            boolean next=(j+1<pattern.length)&&pattern[j+1]=='*';//如果j+1是*
            if (next){
                if (i<str.length&&(str[i]==pattern[j]||pattern[j]=='.'))//需要判断i是否越界
                    return matchStr(str,i+1,pattern,j)||matchStr(str,i,pattern,j+2);//有可能i跳一步（被*吸收），也有可能j跳两步（str不存在，被*吸收）
                else
                    return matchStr(str,i,pattern,j+2);
            }else{
                if (i<str.length&&(str[i]==pattern[j]||pattern[j]=='.'))//如果不是*，则判断是否相等或存在‘.’
                    return matchStr(str,i+1,pattern,j+1);
                else
                    return false;
            }
        }

        /**
         * 表示数值的字符串
         * 可以分为有符号数、指数、小数
         * @param str
         * @return
         */
        private boolean isSigned(char[] str,int start){
            for (int i=start;i<str.length;i++){
                if (str[i]>=48&&str[i]<=57)
                    continue;
                else if (str[i]=='.')
                    return isDecimal(str,i+1);
                else if (str[i]=='E'||str[i]=='e')
                    return isExp(str,i+1);
                else
                    return false;
            }
            return true;
        }
        private boolean isDecimal(char[] str,int start){
            for (int i=start;i<str.length;i++){
                if (str[i]>=48&&str[i]<=57)
                    continue;
                else if (str[i]=='e'||str[i]=='E')
                    return isExp(str,i+1);
                else
                    return false;
            }
            return true;
        }
        private boolean isExp(char[] str,int start){
            if (start==str.length)
                return false;
            if (str[start]=='+'||str[start]=='-')
                start+=1;
            for (int i=start;i<str.length;i++){
                if (str[i]>=48&&str[i]<=57)
                    continue;
                else
                    return false;
            }
            return true;
        }
        public boolean isNumeric(char[] str) {
            if (str[0]=='+'||str[0]=='-')
                return isSigned(str,1);
            else {
                for (int i = 0;i<str.length;i++){
                    if (str[i]>=48&&str[i]<=57)
                        continue;
                    else if (str[i]=='.')
                        return isDecimal(str,i+1);
                    else if (str[i]=='e'||str[i]=='E')
                        return isExp(str,i+1);
                    else
                        return false;
                }
            }
            return true;
        }

        /**
         * 字符流中第一个不重复的字符
         * @param ch
         */
        int[] map=new int[256];
        String str=new String();
        //Insert one char from stringstream
        public void Insert(char ch)
        {
            str+=ch;
            if (map[ch]==0)
                map[ch]++;
            else
                map[ch]--;
        }
        //return the first appearence once char in current stringstream
        public char FirstAppearingOnce()
        {
            for (char ch:str.toCharArray()){
                if (map[ch]==1)
                    return ch;
            }
            return '#';
        }

        /**
         * 链表中环的入口结点
         * @param pHead
         * @return
         */
        public ListNode EntryNodeOfLoop(ListNode pHead)
        {
            Set<Integer> set=new HashSet<>();
            while (pHead!=null){
                if(set.contains(pHead.val))
                    return pHead;
                else{
                    set.add(pHead.val);
                    pHead=pHead.next;
                }
            }
            return null;
        }

        /**
         * 删除链表中重复节点
         * @param pHead
         * @return
         */
        public ListNode deleteDuplication(ListNode pHead)
        {
            Map<Integer,Integer> map=new HashMap<>();
            ListNode res=pHead;
            while (pHead!=null){
                if (!map.containsKey(pHead.val)){
                    map.put(pHead.val,1);
                }else
                    map.put(pHead.val,map.get(pHead.val)+1);
                pHead=pHead.next;
            }
            pHead=res;
            while (pHead!=null&&map.get(pHead.val)!=1){//第一项就重复
                pHead=pHead.next;
                res=pHead;
            }
            ListNode next=pHead==null?null:pHead.next;
            while (next!=null){
                if (map.get(next.val)==1){
                    pHead=next;
                    next=next.next;
                }else{
                    pHead.next=next.next;
                    next=pHead.next;
                }
            }
            return res;
        }

        /**
         * 二叉树的下一个节点
         * @param pNode
         * @return
         */
        public TreeLinkNode GetNext(TreeLinkNode pNode) {
            if (pNode.right != null) {//当右子树不为null
                TreeLinkNode right = pNode.right;
                while (right.left != null)
                    right = right.left;
                return right;
            } else if (pNode.next != null && pNode == pNode.next.left)//当是父节点的左子树
                return pNode.next;
            else {//当是父节点的右子树时，寻找第一个（自身是左子树的祖先）的父节点
                TreeLinkNode parent=pNode.next;
                while (parent!=null){
                    if (parent.next!=null&&parent==parent.next.left)
                        return parent.next;
                    else
                        parent=parent.next;
                }
                return null;
            }
        }

        /**
         * 对称二叉树
         * @param pRoot
         * @return
         */
        boolean isSymmetrical(TreeNode pRoot)
        {
            return pRoot==null?true:isMirrored(pRoot.left, pRoot.right);
        }
        boolean isMirrored(TreeNode pLeft,TreeNode pRight){
            if (pLeft==null&&pRight==null)
                return true;
            else if (pLeft!=null&&pRight!=null&&pLeft.val==pRight.val)
                return isMirrored(pLeft.left,pRight.right)&&isMirrored(pLeft.right,pRight.left);
            else
                return false;
        }

        /**
         * 按“之”字形打印二叉树
         * @param pRoot
         * @return
         */
        public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
            ArrayList<ArrayList<Integer>> res=new ArrayList<>();
            Stack<TreeNode> stack=new Stack<>();
            boolean isOdd=true;
            if (pRoot!=null)
                stack.push(pRoot);
            Stack<TreeNode> tmp=new Stack<>();
            while (!stack.empty()){
                ArrayList<Integer> subList=new ArrayList<>();
                if (isOdd){
                    while (!stack.empty()){
                        TreeNode tmpNode=stack.pop();
                        subList.add(tmpNode.val);
                        if (tmpNode.left!=null)
                            tmp.push(tmpNode.left);
                        if (tmpNode.right!=null)
                            tmp.push(tmpNode.right);
                    }
                    stack=(Stack<TreeNode>) tmp.clone();
                    isOdd=false;
                    tmp.clear();
                }else {
                    while (!stack.empty()){
                        TreeNode tmpNode=stack.pop();
                        subList.add(tmpNode.val);
                        if (tmpNode.right!=null)
                            tmp.push(tmpNode.right);
                        if (tmpNode.left!=null)
                            tmp.push(tmpNode.left);
                    }
                    stack=(Stack<TreeNode>) tmp.clone();
                    isOdd=true;
                    tmp.clear();
                }
                res.add(subList);
            }
            return res;
         }

        /**
         * 把二叉树打印成多行
         * @param pRoot
         * @return
         */
        ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {
            ArrayList<ArrayList<Integer>> res=new ArrayList<>();
            Queue<TreeNode> queue=new LinkedList<>();
            if (pRoot!=null)
                queue.add(pRoot);
            Queue<TreeNode> tmp=new LinkedList<>();
            while (!queue.isEmpty()){
                ArrayList<Integer> subList=new ArrayList<>();
                while (!queue.isEmpty()){
                    TreeNode tmpNode=queue.poll();
                    subList.add(tmpNode.val);
                    if (tmpNode.left!=null)
                        tmp.add(tmpNode.left);
                    if (tmpNode.right!=null)
                        tmp.add(tmpNode.right);
                }
                queue=new LinkedList<>(tmp);
                tmp.clear();
                res.add(subList);
            }
            return res;
        }

        /**
         * 序列化二叉树
         * @param root
         * @return
         */
        String tree=new String();
        TreeNode root=null;
        int i=0;
        String Serialize(TreeNode root) {
            if (root==null)
                return tree;
            preOrder(root);
            return new String(tree);
        }
        void preOrder(TreeNode root){
            if (root!=null)
                tree=tree.concat(String.valueOf(root.val))+'!';
            else
                tree=tree.concat("#!");
            if (root!=null)
                preOrder(root.left);
            if (root!=null)
                preOrder(root.right);
            return;
        }
        TreeNode Deserialize(String str) {
            String[] nodes=str.split("!");
            if (str.equals(""))
                return null;
            int i=0;
            root=preOrderDe(nodes);
            return root;
        }
        TreeNode preOrderDe(String[] nodes){
            if (nodes[i].equals("#")){
                i++;
                return null;
            }else{
                TreeNode tmp=null;
                tmp=new TreeNode(Integer.valueOf(nodes[i++]));
                tmp.left=preOrderDe(nodes);
                tmp.right=preOrderDe(nodes);
                return tmp;
            }
        }

        /**
         * 二叉树第K个节点
         * @param pRoot
         * @param k
         * @return
         */
        ArrayList<TreeNode> nodes=new ArrayList<>();
        TreeNode KthNode(TreeNode pRoot, int k)
        {
            inOrder(pRoot);
            if (k==0||k>nodes.size())
                return null;
            return nodes.get(k-1);
        }
        void inOrder(TreeNode pRoot){
            if (pRoot==null)
                return;
            inOrder(pRoot.left);
            nodes.add(pRoot);
            inOrder(pRoot.right);
        }

        /**
         * 数据流中的中位数
         * @param num
         */
        List<Integer> stream=new ArrayList<>();
        public void Insert(Integer num) {
            stream.add(num);
        }

        public Double GetMedian() {
            Collections.sort(stream);
            if((stream.size()&1)==1)
                return new Double(stream.get(stream.size()/2));
            else
                return (stream.get(stream.size()/2)+stream.get(stream.size()/2-1))/2.0;
        }

        /**
         * 滑动窗口最大值
         * @param num
         * @param size
         * @return
         */
        public ArrayList<Integer> maxInWindows(int [] num, int size)
        {
            ArrayList<Integer> res=new ArrayList<>();
            if (num.length==0||size<1)
                return res;
            ArrayDeque<Integer> deque=new ArrayDeque<>();
            int begin;
            for (int i=0;i<num.length;i++){
                begin=i-size+1;
                if (deque.isEmpty())
                    deque.add(i);
                else if (begin>deque.peekFirst())
                    deque.pollFirst();
                while ((!deque.isEmpty())&&num[deque.peekLast()]<=num[i])
                    deque.pollLast();
                deque.add(i);
                if (begin>=0)
                    res.add(num[deque.peekFirst()]);
            }
            return res;
        }

        /**
         * 矩阵中的路径
         * @param matrix
         * @param rows
         * @param cols
         * @param str
         * @return
         */
        public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
        {
            boolean[] flag=new boolean[matrix.length];
            for (int i=0;i<rows;i++){
                for (int j=0;j<cols;j++){
                    if (judge(matrix,i,j,rows,cols,str,0,flag)){
                        return true;
                    }
                }
            }
            return false;
        }
        private boolean judge(char[] matrix,int i,int j,int rows,int cols,char[] str,int k,boolean[] flag){
            int index=i*cols+j;
            if (i<0||j<0||i>=rows||j>=cols||matrix[index]!=str[k]||flag[index]==true)
                return false;
            else if (k==str.length-1)
                return true;
            else{
                flag[index]=true;
                if (judge(matrix,i+1,j,rows,cols,str,k+1,flag)||
                        judge(matrix,i-1,j,rows,cols,str,k+1,flag)||
                        judge(matrix,i,j+1,rows,cols,str,k+1,flag)||
                        judge(matrix,i,j-1,rows,cols,str,k+1,flag))
                    return true;
                else {
                    flag[index]=false;
                    return false;
                }
            }
        }

        /**
         * 机器人的运动范围
         * @param threshold
         * @param rows
         * @param cols
         * @return
         */
        public int movingCount(int threshold, int rows, int cols)
        {
            int[][] flag=new int[rows][cols];
            return reachable(flag,0,0,rows,cols,threshold);
        }
        private int reachable(int[][] flag,int i,int j,int rows,int cols,int threshold){
            if (i<0||j<0||i>=rows||j>=cols||rowColSum(i,j)>threshold||flag[i][j]==1)
                return 0;
            flag[i][j]=1;
            return reachable(flag,i-1,j,rows,cols,threshold)+
            reachable(flag,i+1,j,rows,cols,threshold)+
            reachable(flag,i,j-1,rows,cols,threshold)+
            reachable(flag,i,j+1,rows,cols,threshold)+1;
        }
        private int rowColSum(int rows,int cols){
            int sum=0;
            while (rows>=10){
                sum+=(rows%10);
                rows/=10;
            }
            while (cols>=10){
                sum+=(cols%10);
                cols/=10;
            }
            sum+=(rows+cols);
            return sum;
        }

        /**
         * 剪绳子
         * @param target
         * @return
         */
        public int cutRope(int target) {
            int x=target/3;
            int y=target%3;
            if (target==2)
                return 1;
            else if (target==3)
                return 2;
            switch (y){
                case 0:
                    return (int)Math.pow(3,x);
                case 1:
                    return 2*2*(int)Math.pow(3,x-1);
                case 2:
                    return 2*(int)Math.pow(3,x);
            }
            return 0;
        }
    }





    public static void main(String[] args) {
        Solution solution=new Solution();
        TreeNode root=new TreeNode(8);
        root.left=new TreeNode(6);
        root.left.left=new TreeNode(5);
        root.left.right=new TreeNode(7);
        root.right=new TreeNode(10);
        root.right.left=new TreeNode(9);
        root.right.right=new TreeNode(11);
        System.out.println(solution.cutRope(8));
    }
}
