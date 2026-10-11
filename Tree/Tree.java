import java.util.*;

import javax.swing.tree.TreeNode;

//structure of tree
class Node{
    int data;
    Node left = null;
    Node right = null;
    Node(int data){
        this.data = data;
    }
}

//used in pre in post in one traversal
class Tuple{
    Node root;
    int num;
    Tuple(Node root, int num){
        this.root = root;
        this.num = num;
    }
}

//used in vertical traversal
class Tuple2{
    Node root;
    int row;
    int col;
    Tuple2(Node root, int row, int col){
        this.root = root;
        this.row = row;
        this.col = col;
    }
}

//used in top view
class Tuple3{
    Node node;
    int x;
    Tuple3(Node node, int x){
        this.node = node;
        this.x = x;
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

    //boundary traversal
    //check for leaf nodes
    static boolean isLeaf(Node node){
        return node != null && node.left == null && node.right == null;
    }

    //adding left boundary except leaf nodes
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

    //adding leaf nodes
    static void addLeafNodes(Node node, List<Integer> list){
        if (node == null) return;

        if (isLeaf(node)){
            list.add(node.data);
            return;
        }
        addLeafNodes(node.left, list);
        addLeafNodes(node.right, list);
    }

    //adding right boundary except leaf nodes in reverse dir
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

    //main boundary func
    static List<Integer> boundary(Node root){
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        list.add(root.data);
        addLeftBoundary(root.left, list);
        addLeafNodes(root, list);
        addRightBoundary(root.right, list);
        return list;
    }

    //vertical traversal
    static List<List<Integer>> verticalTravel(Node root){
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Queue<Tuple2> q = new ArrayDeque<>();
        q.offer(new Tuple2(root, 0, 0));
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        while (!q.isEmpty()){
            Tuple2 curr = q.poll();
            Node node = curr.root;
            int row = curr.row;
            int col = curr.col;
            map.putIfAbsent(col, new TreeMap<>());
            map.get(col).putIfAbsent(row, new PriorityQueue<>());
            map.get(col).get(row).offer(node.data);
            if (node.left != null) q.offer(new Tuple2(node.left, row + 1, col - 1));
            if (node.right != null) q.offer(new Tuple2(node.right, row + 1, col + 1));
        }
        for (TreeMap<Integer,PriorityQueue<Integer>> something : map.values()){
            List<Integer> arr = new ArrayList<>();
            for (PriorityQueue<Integer> haaa : something.values()){
                while (!haaa.isEmpty()){
                    arr.add(haaa.poll());
                }
            }
            list.add(arr);
        }
        return list;
    }

    //top view
    static ArrayList<Integer> topView(Node root) {
        // code here
        ArrayList<Integer> arr = new ArrayList<>();
        if (root == null) return arr;
        Queue<Tuple3> q = new ArrayDeque<>();
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        q.offer(new Tuple3(root, 0));
        while (!q.isEmpty()){
            Tuple3 curr = q.poll();
            Node node = curr.node;
            int x = curr.x;
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(node.data);
            if (node.left != null) q.offer(new Tuple3(node.left, x - 1));
            if (node.right != null) q.offer(new Tuple3(node.right, x + 1));
        }
        for (ArrayList<Integer> haaa : map.values()){
            arr.add(haaa.get(0));
        }
        return arr;
    }

    //bottom view
    static ArrayList<Integer> bottomView(Node root) {
        // code here
        ArrayList<Integer> arr = new ArrayList<>();
        if (root == null) return arr;
        Queue<Tuple3> q = new ArrayDeque<>();
        q.offer(new Tuple3(root, 0));
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        while (!q.isEmpty()){
                Tuple3 curr = q.poll();
                Node node = curr.node;
                int x = curr.x;
                map.putIfAbsent(x, new ArrayList<>());
                map.get(x).add(node.data);
                if (node.left != null) q.offer(new Tuple3(node.left, x - 1));
                if (node.right != null) q.offer(new Tuple3(node.right, x + 1));
        }
        for (ArrayList<Integer> haaa : map.values()){
            arr.add(haaa.get(haaa.size() - 1));
        }
        return arr;
    }

    //right view
    static ArrayList<Integer> rightView(Node root) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (root == null) return arr;
        Queue<Tuple3> q = new ArrayDeque<>();
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        q.offer(new Tuple3(root, 0));
        while (!q.isEmpty()){
            Tuple3 curr = q.poll();
            Node node = curr.node;
            int x = curr.x;
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(node.data);
            if (node.left != null) q.offer(new Tuple3(node.left, x + 1));
            if (node.right != null) q.offer(new Tuple3(node.right, x + 1));
        }
        for (ArrayList<Integer> haaa : map.values()){
            arr.add(haaa.get(haaa.size() - 1));
        }
        return arr;
    }

    //left view
    static ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (root == null) return arr;
        Queue<Tuple3> q = new ArrayDeque<>();
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        q.offer(new Tuple3(root, 0));
        while (!q.isEmpty()){
            Tuple3 curr = q.poll();
            Node node = curr.node;
            int x = curr.x;
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(node.data);
            if (node.left != null) q.offer(new Tuple3(node.left, x + 1));
            if (node.right != null) q.offer(new Tuple3(node.right, x + 1));
        }
        for (ArrayList<Integer> haaa : map.values()){
            arr.add(haaa.get(0));
        }
        return arr;
    }

    //root to leaf all paths
    static void dfs(Node root, List<Integer> path, List<List<Integer>> ans){
        if (root == null) return;
        path.add(root.data);
        if (root.left == null && root.right == null){
            ans.add(new ArrayList<>(path));
        } 
        else{
            dfs(root.left, path, ans);
            dfs(root.right, path, ans);
        }
        path.remove(path.size() - 1);
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

    //does tree follows child sum prop ?
    static int func(Node root){
        if (root == null) return 0;
        return root.data;
    }

    static boolean isSumProperty(Node root) {
        //  code here
        //base case
        if (root == null || (root.left == null && root.right == null)) return true;
        int left = func(root.left);
        int right = func(root.right);
        if (left + right != root.data) return false;
        return isSumProperty(root.left) && isSumProperty(root.right);
    }

    //child sum prop variant 2 - make it follow child sum
    static boolean isSumProperty2(Node root){
        if (root == null || (root.left == null && root.right == null)) return true;
        int left = func(root.left);
        int right = func(root.right);
        if (left + right > root.data){
            root.data = left + right;
        }
        else{
            if (root.left != null) root.left.data = root.data;
            if (root.right != null) root.right.data = root.data;
        }
        return isSumProperty2(root.left) && isSumProperty2(root.right);
    }

    static int funcy(Node root){
        if (root == null || (root.left == null && root.right == null)) return root.data;
        int left = funcy(root.left);
        int right = funcy(root.right);
        root.data = left + right;
        return root.data;
    }

    //lowest common ancestor
    static Node lca(Node root, int p, int q){
        if (root == null || root.data == p || root.data == q) return root;
        Node left = lca(root.left, p, q);
        Node right = lca(root.right, p, q);
        if (left == null) return right;
        else if (right == null) return left;
        else return root;
    }

    //max width
    static int widthOfBinaryTree(Node root) {
        if (root == null) return 0;
        Queue<Tuple3> q = new ArrayDeque<>();
        q.offer(new Tuple3(root, 0));
        int width = 0;
        while (!q.isEmpty()){
            int first = 0;
            int last = 0;
            int min = q.peek().node.data;
            int size = q.size();
            for (int i = 0; i < size; i++){
                Tuple3 curr = q.poll();
                Node node = curr.node;
                int x = curr.x;
                if (i == 0) first = x;
                if (i == size - 1) last = x;
                if (node.left != null) q.offer(new Tuple3(node.left, 2 * x + 1));
                if (node.right != null) q.offer(new Tuple3(node.right, 2 * x + 2));
            }
            width = Math.max(width, (last - first) + 1);
        }
        return width;
    }

    //root to node
    static boolean root2Node(Node root, Node target, ArrayList<Integer> list){
        if (root == null) return false;
        list.add(root.data);
        if (root == target) return true;
        boolean left = root2Node(root.left, target, list);
        boolean right = root2Node(root.right, target, list);
        if (left == true ||  right == true) return true;
        else{
            list.remove(list.size() - 1);
            return false;
        }
    }

    //BT using inorder & preorder
    static Node buildTreeInPre(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = inorder.length;
        for (int i = 0; i < len; i++){
            map.put(inorder[i], i);
        }
        Node root = funcInPre(inorder, 0, len - 1, preorder, 0, len - 1, map);
        return root;
    }
    static Node funcInPre(int[] inorder, int ins, int ine, int[] preorder, int pres, int pree, HashMap<Integer, Integer> map){
        if (pres > pree || ins > ine) return null;
        Node root = new Node(preorder[pres]);
        int inR = map.get(preorder[pres]);
        int numsLeft = inR - ins;
        root.left = funcInPre(inorder, ins, inR - 1, preorder, pres + 1, pres + numsLeft, map);
        root.right = funcInPre(inorder, inR + 1, ine, preorder, pres + numsLeft + 1, pree, map);
        return root;
    }

    //BT using inorder & postorder
    static Node buildTreeInPost(int[] inorder, int[] postorder) {
        int len = inorder.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++){
            map.put(inorder[i], i);
        }
        Node root = funcInPost(inorder, 0, len - 1, postorder, 0, len - 1, map);
        return root;
    }
    static Node funcInPost(int[] inorder, int ins, int ine, int[] postorder, int posts, int poste, HashMap<Integer, Integer> map){
        if (ins > ine || posts > poste) return null;
        Node root = new Node(postorder[poste]);
        int inR = map.get(postorder[poste]);
        int numsLeft = inR - ins;
        root.left = funcInPost(inorder, ins, inR - 1, postorder, posts, posts + numsLeft - 1, map);
        root.right = funcInPost(inorder, inR + 1, ine, postorder, posts + numsLeft, poste - 1, map);
        return root;
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
        //System.out.println(isSymmetric(root.left, root.right));
        //List<List<Integer>> ans = verticalTravel(root);
        //System.out.println(ans);
        // ArrayList<Integer> topi = topView(root);
        // System.out.println(topi);
        // ArrayList<Integer> boti = bottomView(root);
        // System.out.println(boti);
        // ArrayList<Integer> lifi = leftView(root);
        // System.out.println(lifi);
        // List<Integer> path = new ArrayList<>();
        // List<List<Integer>> ans = new ArrayList<>();
        // dfs(root, path, ans);
        // System.out.println(ans);
        // Node bahah = lca(root, 8,9);
        // System.out.println(bahah.data);
        //System.out.println(widthOfBinaryTree(root));
        // ArrayList<Integer> haaa = new ArrayList<>();
        // root2Node(root, root, haaa);
        // System.out.println(haaa);
        //System.out.println(isSumProperty(root));
        // System.out.println(levelOrder(root));
        // isSumProperty2(root);
        // funcy(root);
        // System.out.println(levelOrder(root));
        sc.close();
    }
}