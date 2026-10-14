import java.util.*;

class BST{
    //search in BST
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        else if (root.val > val) return searchBST(root.left, val);
        else return searchBST(root.right, val);
    }

    //ceil in BST
    int func(Node root, int ceil, int x){
        if (root == null) return ceil;
        if (root.data == x) return root.data;
        else if (root.data > x){
            ceil = root.data;
            return func(root.left, ceil, x);
        }
        else{
            return func(root.right, ceil, x);
        }
    }
    
    int findCeil(Node root, int x) {
        // code here
        int ceil = -1;
        return func(root, ceil, x);
    }

    //floor in BST
    public int func(Node root, int floor, int k){
        if (root == null) return floor;
        if (root.data == k) return root.data;
        else if (root.data < k){
            floor = root.data;
            return func(root.right, floor, k);
        }
        else{
            return func(root.left, floor, k);
        }
    }
    
    public int findMaxFork(Node root, int k) {
        // code here.
        int floor = -1;
        return func(root, floor, k);
    }

    //insert a node in BST
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode haaa = new TreeNode(val);
        if (root == null){
            return haaa;
        }
        TreeNode curr = root;
        TreeNode prev = null;
        while (curr != null){
            if (curr.val < val){
                prev = curr;
                curr = curr.right;
            }
            else{
                prev = curr;
                curr = curr.left;
            }
        }
        //System.out.println(prev.val);
        if (prev.val > val) prev.left = haaa;
        else prev.right = haaa;
        return root;
    }

    //delete a node in BST
    public TreeNode func(TreeNode node){
        if (node == null) return null;
        while (node.right != null){
            node = node.right;
        }
        return node;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        TreeNode curr = root;
        TreeNode prev = null;
        while (curr != null && curr.val != key){
            if (curr.val > key){
                prev = curr;
                curr = curr.left;
            }
            else{
                prev = curr;
                curr = curr.right;
            }
        }
        if (curr == null) return root;
        if (curr == root){
            if (curr.left == null && curr.right == null) return null;
            else{
                if (curr.left != null && curr.right != null){
                    root = curr.left;
                    TreeNode haaa = func(root);
                    haaa.right = curr.right;
                    curr.left = null;
                    curr.right = null;
                }
                else if (curr.left == null){
                    root = curr.right;
                    curr.right = null;
                }
                else{
                    root = curr.left;
                    curr.left = null;
                }
            }
        }
        else if (curr.left == null && curr.right == null){
            if (prev.left == curr) prev.left = null;
            else prev.right = null;
        }
        else{
            if (curr.left != null && curr.right != null){
                TreeNode bahha = func(curr.left);
                bahha.right = curr.right;
                curr.right = null;
                if (prev.left == curr){
                    prev.left = curr.left;
                    curr.left = null;
                }
                else{
                    prev.right = curr.left;
                    curr.left = null;
                }
            }
            else if (curr.left == null){
                if (prev.left == curr){
                    prev.left = curr.right;
                    curr.right = null;
                }
                else{
                    prev.right = curr.right;
                    curr.right = null;
                }
            }
            else{
                if (prev.left == curr){
                    prev.left = curr.left;
                    curr.left = null;
                }
                else{
                    prev.right = curr.left;
                    curr.left = null;
                }
            }
        }
        return root;
    }

    //kth smallest element in BST
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;
        int cnt = 0;
        TreeNode curr = root;
        while (curr != null){
            if (curr.left == null){
                cnt++;
                if (cnt == k) return curr.val;
                curr = curr.right;
            }
            else{
                TreeNode maxi = func(curr.left, curr);
                if (maxi.right == null){
                    maxi.right = curr;
                    curr = curr.left;
                }
                else{
                    maxi.right = null;
                    cnt++;
                    if (cnt == k) return curr.val;
                    curr = curr.right;
                }
            }
        }
        return -1;
    }

    private TreeNode func(TreeNode root, TreeNode curr){
        if (root == null) return null;
        while(root.right != null && root.right != curr){
            root = root.right;
        }
        return root;
    }

    //kth largest element in BST
    private void count(Node root, int[] n){
        if (root == null) return;
        n[0]++;
        count(root.left, n);
        count(root.right, n);
    }
    
    public int kthLargest(Node root, int k) {
        // code here
        int ans = -1;
        if (root == null) return ans;
        int[] n = new int[1];
        n[0] = 0;
        count(root, n);
        int cnt = 0;
        Node curr = root;
        while (curr != null){
            if (curr.left == null){
                cnt++;
                if (cnt == n[0] - k + 1) ans = curr.data;
                curr = curr.right;
            }
            else{
                Node maxi = func(curr.left, curr);
                if (maxi.right == null){
                    maxi.right = curr;
                    curr = curr.left;
                }
                else{
                    maxi.right = null;
                    cnt++;
                    if (cnt == n[0] - k + 1) ans = curr.data;
                    curr = curr.right;
                }
            }
        }
        return ans;
    }
}