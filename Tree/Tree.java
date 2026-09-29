import java.util.*;

//used in pre in post in one traversal
class Tuple{
    Node root;
    int num;
    Tuple(Node root, int num){
        this.root = root;
        this.num = num;
    }
}

//structure of tree
class Node{
    int data;
    Node left = null;
    Node right = null;
    Node(int data){
        this.data = data;
    }
}

public class Tree {
    //used to convert arr -> tree
    static Node TreeBuilder(int[] arr, int n){
        if (n == 0 || arr[0] == -1) return null;
        Queue<Node> q = new ArrayDeque<>();
        Node root = new Node(arr[0]);
        q.offer(root);
        int i = 1;
        while (i < n && !q.isEmpty()){
            Node curr = q.poll();
            if (i < n && arr[i] != -1){
                curr.left = new Node(arr[i]);
                q.offer(curr.left);
            }
            i++;
            if (i < n && arr[i] != -1){
                curr.right = new Node(arr[i]);
                q.offer(curr.right);
            }
            i++;
        }
        return root;
    }

    //preorder traversal
    static void preOrder(Node root){
        if (root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    //inorder traversal
    static void inOrder(Node root){
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    //postorder traversal
    static void postOrder(Node root){
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    //level order traversal
    static List<Integer> levelOrder(Node root){
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()){
            Node top = q.poll();
            list.add(top.data);
            if (top.left != null) q.offer(top.left);
            if (top.right != null) q.offer(top.right);
        }
        return list;
    }

    //zig zag traversal
    static List<List<Integer>> zigZag(Node root){
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        boolean flag = false;
        while (!q.isEmpty()){
            int size = q.size();
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < size; i++){
                Node top = q.poll();
                if (top.left != null) q.offer(top.left);
                if (top.right != null) q.offer(top.right);
                arr.add(top.data); 
            }
            if (flag) Collections.reverse(arr);
            list.add(arr);
            flag = !flag;
        }
        return list;
    }

    //pre in post in one traversal
    static void preInPost(Node root) {
        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> in = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();

        if (root == null) return;

        Stack<Tuple> st = new Stack<>();
        
        st.push(new Tuple(root, 1));

        while (!st.isEmpty()){
            Tuple item = st.pop();
            Node node = item.root;
            int num = item.num;
            if (num == 1){
                pre.add(node.data);
                item.num = 2;
                st.push(new Tuple(node, 2));
                if (node.left != null) st.push(new Tuple(node.left, 1));
            }
            else if (num == 2){
                in.add(node.data);
                item.num = 3;
                st.push(new Tuple(node, 3));
                if (node.right != null) st.push(new Tuple(node.right, 1));
            }
            else{
                post.add(node.data);
            }
        }

        System.out.println("Preorder: " + pre);
        System.out.println("Inorder: " + in);
        System.out.println("Postorder: " + post);
    }

    static boolean isLeaf(Node node){
        return node != null && node.left == null && node.right == null;
    }

    static void addLeftBoundary(Node node, List<Integer> list){
        Node curr = node;
        while(curr != null){
            if (!isLeaf(curr)){
                list.add(curr.data);
            }
            if (curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }

    static void addLeafNodes(Node node, List<Integer> list){
        if (node == null) return;

        if (isLeaf(node)){
            list.add(node.data);
            return;
        }
        addLeafNodes(node.left, list);
        addLeafNodes(node.right, list);
    }

    static void addRightBoundary(Node node, List<Integer> list){
        Stack<Integer> st = new Stack<>();
        Node curr = node;
        while (curr != null){
            if (!isLeaf(curr)) st.push(curr.data);
            if (curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        while (!st.isEmpty()){
            list.add(st.pop());
        }
    }

    static List<Integer> boundary(Node root){
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        list.add(root.data);
        addLeftBoundary(root.left, list);
        addLeafNodes(root, list);
        addRightBoundary(root.right, list);
        return list;
    }

    //max depth or height of tree
    static int maxDepth(Node root){
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Math.max(left, right);
    }

    //is the given tree balance tree?
    static int isBalanced(Node root){
        if (root == null) return 0;
        int lh = isBalanced(root.left);
        int rh = isBalanced(root.right);
        if (lh == -1 || rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return 1 + Math.max(lh, rh);
    }

    //diameter of tree
    static int diameter(Node root, int[] maxi){
        if (root == null) return 0;
        int lh = diameter(root.left, maxi);
        int rh = diameter(root.right, maxi);
        maxi[0] = Math.max(maxi[0], lh + rh);
        return 1 + Math.max(lh, rh);
    }

    //max path in tree
    static int maxPath(Node root, int[] maxi2){
        if (root == null) return 0;
        int left = maxPath(root.left, maxi2);
        if (left < 0) left = 0;
        int right = maxPath(root.right, maxi2);
        if (right < 0) right = 0;
        maxi2[0] = Math.max(maxi2[0], left + root.data + right);
        return root.data + Math.max(left, right);
    }

    //are two trees same ?
    static boolean isSame(Node root, Node root2){
        if (root == null || root2 == null) return root == root2;
        return root.data == root2.data && isSame(root.left, root2.left) && isSame(root.right, root2.right);
    }

    //is tree symmetric ?
    static boolean isSymmetric(Node left, Node right){
        if (left == null || right == null) return left == right;
        if (left.data != right.data) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        // for (int item : arr){
        //     System.out.print(item + " ");
        // }
        Node root = TreeBuilder(arr, n);
        // System.out.println(root.data);
        // System.out.println(root.left.data);
        // System.out.println(root.right.data);
        // preOrder(root);
        // inOrder(root);
        // postOrder(root);
        // List<Integer> list = levelOrder(root);
        // for (int item : list){
        //     System.out.print(item + " ");
        // }
        // List<List<Integer>> rev = zigZag(root);
        // for (List<Integer> inn : rev){
        //     for (int item : inn){
        //         System.out.print(item + " ");
        //     }
        // }
        //preInPost(root);
        //System.out.println(maxDepth(root));
        //System.out.println(isBalanced(root));
        //int[] maxi = new int[1];
        //System.out.println(diameter(root, maxi));
        // int[] arr2 = {1,2,3,4,5};
        // Node root2 = TreeBuilder(arr2, arr2.length);
        // System.out.println(isSame(root, root2));
        // int[] maxi2 = new int[1];
        // System.out.println(maxPath(root, maxi2));
        // System.out.println(maxi2[0]);
        // List<Integer> list = boundary(root);
        // System.out.println(list);
        System.out.println(isSymmetric(root.left, root.right));
        sc.close();
    }
}