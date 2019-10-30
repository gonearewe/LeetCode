/*
在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

示例 1:

输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
示例 2:

输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
说明:

你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
*/
func findKthLargest(nums []int, k int) int {   //Go标准库没有实现heap,只实现了接口
    h:=&IntHeap{}
    for _,num:=range nums{
        if h.Len()<k{
            heap.Push(h,num)   //heap.Push()执行h.Push()和up(),即push后自动排序
        }else if num>(*h)[0] {    //(*h)[0]是heap内最小的元素，即heap是mini heap
            heap.Pop(h)
            heap.Push(h,num)
        }
    }
    return (*h)[0]    
}
type IntHeap []int  //以下来自container/example_intheap_test.go (package heap_test)

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }  //修改这里可以实现最大堆
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *IntHeap) Push(x interface{}) {
	// Push and Pop use pointer receivers because they modify the slice's length,
	// not just its contents.
	*h = append(*h, x.(int))
}

func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}