/*
设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) -- 将元素 x 推入栈中。
pop() -- 删除栈顶的元素。
top() -- 获取栈顶元素。
getMin() -- 检索栈中的最小元素。
示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
*/
//还可以使用不同步的辅助栈，减少重复压入的空间开销，但是需要注意好边界条件
//还可以不使用辅助栈，保存当前最小值，缺点是最小值弹出后需要遍历栈获取新的最小值
class MinStack { //辅助栈法
    private Stack<Integer> data;  //先在外面初始化
    private Stack<Integer> helper;

    /** initialize your data structure here. */
    public MinStack() {   //我们需要两个栈
        data = new Stack<>();   //一个是正常操作的栈
        helper = new Stack<>();  //另一个是降序的栈，用于获取最小值
    }

    public void push(int x) {
        data.push(x);
        if (helper.isEmpty() || helper.peek() >= x)
            helper.push(x);  //只接受小于等于辅助栈栈顶元素的值
        else
            helper.push(helper.peek());  //否则重复压入栈顶元素，以保持两个栈同步
    }

    public void pop() {
        if (!data.isEmpty()) {
            data.pop();
            helper.pop(); //同时弹出
        }
    }

    public int top() {
        return data.peek();

    }

    public int getMin() {
        return helper.peek(); //最小元素就是辅助栈栈顶元素
    }
}

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj =
 * new MinStack(); obj.push(x); obj.pop(); int param_3 = obj.top(); int param_4
 * = obj.getMin();
 */