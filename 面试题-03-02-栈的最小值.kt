import java.util.*

// 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。

// 示例：
// MinStack minStack = new MinStack();
// minStack.push(-2);
// minStack.push(0);
// minStack.push(-3);
// minStack.getMin();   --> 返回 -3.
// minStack.pop();
// minStack.top();      --> 返回 0.
// minStack.getMin();   --> 返回 -2.

class MinStack() {
    /** initialize your data structure here. */
    val minStack = LinkedList<Int>()
    val stack = LinkedList<Int>()

    fun push(x: Int) {
        if (stack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x)
        }

        stack.push(x)
    }

    fun pop() {
        val top = stack.pop()
        if (top == minStack.peek()) {
            minStack.pop()
        }
    }

    fun top() = stack.peek()

    fun getMin() = minStack.peek()
}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = MinStack()
 * obj.push(x)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */