/*
给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
*/
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length==0||intervals==null) //特殊情况
            return new int[0][];
        List<int[]> ans=new ArrayList<>();
        Arrays.sort(intervals,(a,b)->a[0]-b[0]); //实现比较接口以按各区间左边界排序
        int i=0;
        while(i<intervals.length){
            int left=intervals[i][0],right=intervals[i][1];
            while(i<intervals.length-1&&intervals[i+1][0]<=right){
                i++;//同一个i，在上层循环的基础上寻找区间右边界
                right=Math.max(intervals[i][1],right);//更新右边界
            }
            ans.add(new int[]{left,right});//获得一个合并后的区间
            i++;
        }
        return ans.toArray(new int[0][]);
    }
}