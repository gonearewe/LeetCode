# LeetCode
My Code for LeetCode

## 双指针 Two Pointers

[26-删除排序数组中的重复项.java](26-删除排序数组中的重复项.java)
[27-移除元素.java](27-移除元素.java)

## 动态规划 

## 2019-7-25
347-前 K 个高频元素
如果确定时间复杂度大于O（n），可以直接先遍历一遍建立map，反正常数次遍历都是O（n）。    
我一开始竟然打算一边处理堆一边建立map,但是元素出现次数是个变量，这样逻辑上将很难动态处理堆。   
我在这里自己实现了一个mini堆，用struct绑定num和cnt。按堆元素的cnt排序，最后弹出对应的num构成结果。Go原生的堆元素只是一个数字。我参考的写法在container/heap/example_pq_test.go之中（其实本来就可以用优先队列来做）。   
