class Solution {
    public Node connect(Node root) {
        if(root==null) return root;//递归结束条件

        if(root.right!=null&&root.left!=null)
            root.left.next=root.right;
        else if(root.right!=null)
            pass(root, root.right);
        else if(root.left!=null)
            pass(root, root.left);

        connect(root.left);  //左右都进行递归
        connect(root.right);
        
        return root; //回溯结束
    }
    public void pass(Node root,Node child){
        child.next=null;
        Node node=root;
        while(node.next!=null){
            node=node.next;
        if(node.left!=null)
            child.next=node.left;
        else if(node.right!=null)
            child.next=node.right; 
        }
    }
}