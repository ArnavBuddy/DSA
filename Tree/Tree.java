import java.util.*;

class Tuple{
    Node root;
    int num;
    Tuple(Node root, int num){
        this.root = root;
        this.num = num;
    }
}

class Node{
    int data;
    Node left = null;
    Node right = null;
    Node(int data){
        this.data = data;
    }
}

public class Tree {
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

    static void preOrder(Node root){
        if (root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void inOrder(Node root){
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    static void postOrder(Node root){
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

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

    
static void preInPost(Node root) {
    ArrayList<Integer> pre = new ArrayList<>();
    ArrayList<Integer> in = new ArrayList<>();
    ArrayList<Integer> post = new ArrayList<>();

    if (root == null) return;

    Stack<Tuple> st = new Stack<>();
    st.push(new Tuple(root, 1));

    while (!st.isEmpty()) {
        Tuple it = st.pop();
        Node node = it.root;
        int state = it.num;

        if (state == 1) {
            // Preorder
            pre.add(node.data);

            it.num = 2;
            st.push(it);

            if (node.left != null) {
                st.push(new Tuple(node.left, 1));
            }
        }
        else if (state == 2) {
            // Inorder
            in.add(node.data);

            it.num = 3;
            st.push(it);

            if (node.right != null) {
                st.push(new Tuple(node.right, 1));
            }
        }
        else {
            // Postorder
            post.add(node.data);
        }
    }

    System.out.println("Preorder: " + pre);
    System.out.println("Inorder: " + in);
    System.out.println("Postorder: " + post);
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
        preInPost(root);
        sc.close();
    }
}