/*
给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

示例 1:

输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:

输入: nums = [1], k = 1
输出: [1]
说明：

你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
*/

func topKFrequent(nums []int, k int) []int { //手撸的mini堆，效率很差，做完才想起来优先队列是现成的，省的自己实现
    m:=make(map[int]int)
    h:=&IntHeap{}
    for _,num:=range nums{
        m[num]++
    }                                       //先遍历一遍建立字典
    for num,cnt:=range m{
        if h.Len()<k{
            tmp:=&Map{num,cnt}
            heap.Push(h,tmp)
        }else if cnt>(*h)[0].cnt{
            heap.Pop(h)
            tmp:=&Map{num,cnt}              //把num和cnt绑定在一起作为一个元素push进去
            heap.Push(h,tmp)
        }
    }
    ans:=make([]int,0,k)   //注意make时注明长度为0，否则默认长度等于容量
    for h.Len()>0{
        element:=heap.Pop(h).(*Map)   //heap.Pop()返回的是interface{},先确定类型才能赋值
        ans=append(ans,element.num)
    }
    return ans[:k]
}
type Map struct{  //进堆的元素
    num int
    cnt int
}
type IntHeap []*Map  //以下来自container/example_intheap_test.go (package heap_test)

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i].cnt < h[j].cnt }  //比较元素的cnt
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IntHeap) Push(x interface{}) {
	// Push and Pop use pointer receivers because they modify the slice's length,
	// not just its contents.
	*h = append(*h, x.(*Map))    //注意类型确定
}

func (h *IntHeap) Pop() interface{} {  //这个方法不需要修改
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}