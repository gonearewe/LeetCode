import java.util.*;

public class BinaryTree {
    private Integer value;
    private BinaryTree left, right;

    public ArrayList<Integer> inorderNonRecursively() {
        var cur = this;
        var stack = new LinkedList<BinaryTree>();
        var result = new ArrayList<Integer>();
        while (cur != null || !stack.isEmpty()) { // 没有走到头或者还有回头路可走
            while (cur != null) { // 一直向左走到头，沿途保存路径
                stack.push(cur); // 第一次访问该节点
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                result.add(cur.value); // 从栈中取元素并第二次访问该节点
                cur = cur.right; // 回头转向右子树
            }
        }

        return result;
    }

    public ArrayList<Integer> preorderNonRecursively() {
        var cur = this;
        var stack = new LinkedList<BinaryTree>();
        var result = new ArrayList<Integer>();
        while (cur != null || !stack.isEmpty()) { // 没有走到头或者还有回头路可走
            while (cur != null) { // 一直向左走到头，沿途保存路径
                result.add(cur.value); // 第一次访问时处理就是先序
                stack.push(cur); // 第一次访问该节点
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop(); // 第二次访问该节点
                cur = cur.right; // 回头转向右子树
            }
        }

        return result;
    }

    public ArrayList<Integer> levelOrderTraverse() {
        var cur = this;
        var queue = new LinkedList<BinaryTree>();
        var result = new ArrayList<Integer>();
        if (cur == null) { // 判断空树
            return result;
        }

        queue.offer(cur); // 根节点
        while (!queue.isEmpty()) { // 只要队列非空，取出一个节点并处理，然后把其左右儿子入队
            cur = queue.poll();
            result.add(cur.value);
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }

        return result;
    }
}