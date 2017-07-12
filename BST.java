import edu.princeton.cs.algs4.*; 
 
 
public class BST<Key extends Comparable<Key>,Value>{ 
    private Node root; 
    private class Node{ 
        private Key key; 
        private Value val; 
        private Node left; 
        private Node right; 
        private int N; 
        private int h; 
        private int length; 
        public Node(Key key, Value val, int N,int h, int length){ 
            this.key = key; 
            this.val = val; 
            this.N = N; 
            this.h = h; 
            this.length = length; 
        } 
    } 
     
    public int size(){ 
        return size(root); 
    } 
    private int size(Node x){ 
        if(x == null) return 0; 
        else return x.N; 
    } 
     
    /* 
     * Exercise3_2_6 
     */ 
    public int height(){ 
        return height(root); 
    } 
    private int height(Node x){ 
        if(x == null) return 0; 
        return x.h+1; 
    } 
     
    /* 
     * Length 
     */ 
    private int length(Node x){ 
        if(x == null) return 0; 
        return x.length; 
    } 
     
    /* 
     * Exercise3_2_7 
     */ 
    public double avgCompares(){ 
        return length(root)/1.0/size() + 1; 
    } 
     
    /* 
     * Exercise3_2_8 
     */ 
    public double optCompares(int N){ 
        return Math.log(N); 
    } 
    public Value get(Key key){ 
        return get(root, key); 
    } 
    private Value get(Node x, Key key){ 
        if(x == null) return null; 
        int cmp = key.compareTo(x.key); 
        if(cmp < 0) return get(x.left, key); 
        else if(cmp > 0) return get(x.right, key); 
        else return x.val; 
    } 
     
    public void put (Key key, Value val){ 
        root = put(root, key, val); 
    } 
    private Node put(Node x, Key key, Value val){ 
        if(x == null) return new Node(key, val, 1,1,0); 
        int cmp = key.compareTo(x.key); 
        if(cmp < 0) x.left = put(x.left, key, val); 
        else if(cmp > 0) x.right = put(x.right, key, val); 
        else x.val = val; 
        x.N = 1 + size(x.left) + size(x.right); 
        x.h = 1+ Math.max(height(x.right), height(x.left)); 
        x.length = length(x.left) +  length(x.right) +  size(x) -1; 
        return x; 
    } 
     
    public Key min(){ 
        return min(root).key; 
    } 
    private Node min(Node x){ 
        if(x.left == null) return x; 
        return min(x.left); 
    } 
     
    public Key max(){ 
        return max(root).key; 
    } 
    private Node max(Node x){ 
        if(x.right == null) return x; 
        return max(x.right); 
    } 
     
    public Key floor(Key key){ 
        Node x = floor(root, key); 
        if(x == null) return null; 
        return x.key; 
    } 
    private Node floor(Node x, Key key){ 
        if(x == null) return null; 
        int cmp = key.compareTo(x.key); 
        if(cmp == 0) return x; 
        else if(cmp < 0) return floor(x.left, key); 
        Node t = floor(x.right, key); 
        if(t != null) return t; 
        else return x; 
    } 
     
    public Key ceiling(Key key){ 
        Node x = ceiling(root, key); 
        if(x == null) return null; 
        return x.key; 
    } 
    private Node ceiling(Node x, Key key){ 
        if(x == null) return null; 
        int cmp = key.compareTo(x.key); 
        if(cmp == 0) return x; 
        else if(cmp > 0) return ceiling(x.right, key); 
        Node t = ceiling(x.left, key); 
        if(t != null) return t; 
        else return x; 
    } 
     
    public Key select(int k){ 
        return select(root, k).key; 
    } 
    private Node select(Node x, int k){ 
        if(x == null) return null; 
        int t = size(x.left); 
        if(t > k) return select(x.left, k); 
        else if(t < k) return select(x.right, k-t-1); 
        else return x; 
    } 
     
    public int rank(Key key){ 
        return rank(root, key); 
    } 
    private int rank(Node x, Key key){ 
        if(x == null) return 0; 
        int cmp = key. compareTo(x.key); 
        if(cmp < 0) return rank(x.left, key); 
        if(cmp > 0) return 1 + size(x.left) + rank(x.right, key); 
        else return size(x.left); 
    } 
     
    public void deleteMin(){ 
        root = deleteMin(root); 
    } 
    private Node deleteMin(Node x){ 
        if(x.left == null) return x.right; 
        x.left = deleteMin(x.left); 
        x.N = 1+ size(x.left)+size(x.right); 
        x.h = 1+ Math.max(height(x.right), height(x.left)); 
        x.length = length(x.left) +  length(x.right) +  size(x) -1; 
        return x; 
    } 
     
    public void deleteMax(){ 
        root = deleteMax(root); 
    } 
    private Node deleteMax(Node x){ 
        if(x.right  == null) return x.left; 
        x.right = deleteMax(x.right); 
        x.N = 1+size(x.left)+size(x.right); 
        x.h = 1+ Math.max(height(x.right), height(x.left)); 
        x.length = length(x.left) +  length(x.right) +  size(x) -1; 
        return x; 
    } 
     
    public void delete(Key key){ 
        root = delete(root, key); 
    } 
    private Node delete(Node x, Key key){ 
        if(x == null) return null; 
        int cmp = key.compareTo(x.key); 
        if(cmp < 0) x.left = delete(x.left, key); 
        else if(cmp > 0) x.right = delete(x.right, key); 
        else{ 
            if(x.right == null) return x.left; 
            if(x.left == null) return x.right; 
            Node t = x; 
            x = min(t.right); 
            x.right = deleteMin(t.right); 
            x.left = t.left; 
        } 
        x.N = 1+ size(x.left)+size(x.right); 
        x.h = 1+ Math.max(height(x.right), height(x.left)); 
        x.length = length(x.left) +  length(x.right) +  size(x) -1; 
        return x; 
    } 
     
    private void print(Node x){ 
        if(x == null) return; 
        print(x.left); 
        System.out.println(x.key); 
        print(x.right); 
    } 
    public Iterable<Key> keys(){ 
        return keys(min(),max()); 
    } 
    public Iterable<Key> keys(Key lo, Key hi){ 
        Queue<Key> queue = new Queue<Key>(); 
        keys(root, queue, lo, hi); 
        return queue; 
    } 
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){ 
        if(x == null) return; 
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key); 
         
        if(cmplo < 0) keys(x.left, queue, lo, hi); 
        if(cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
        if(cmphi > 0) keys(x.right, queue, lo, hi); 
    } 
     
    public void show(){ 
        Iterable<Key> l = this.keys(); 
        for (Key k : l) { 
            System.out.printf(k + " "); 
        } 
        System.out.println(); 
         
    } 
     
    //    public void drawTree(){ 
    //        drawTree(root); 
    //    } 
    //    private void drawTree(Node x){ 
    // 
    //    } 
    //    private double scaleX(Node t){ 
    //        return 1.0 * rank(t)/ 
    //    } 
    //    private double scaleY(Node t){ 
    //    } 
     
    public static void main(String[] args){ 
        BST<String, Integer> st = new BST<String, Integer>(); 
        for(int i = 0; !StdIn.isEmpty(); i++){ 
            String key = StdIn.readString(); 
            st.put(key, i); 
        } 
        for(String s: st.keys()){ 
            System.out.print(s + " " + st.get(s) + "****"); 
        } 
        System.out.println(); 
        System.out.println("Height of tree is " + st.height()); 
        System.out.println("Avergage Compares " + st.avgCompares()); 
        System.out.printf("Optimum Compares %.2f\n",st.optCompares(4)); 
        System.out.println("Minimum Element " + st.min()); 
        System.out.println("Maximum Element " + st.max()); 
        System.out.println("Floor of Element " + args[0] + " is " + st.floor(args[0])); 
        System.out.println("Ceiling of Element " + args[0] + " is " + st.ceiling(args[0])); 
         
        //        st.delete(args[0]); 
        //        System.out.println("After deleting " + args[0]); 
        //        st.show(); 
        //        st.deleteMax(); 
        //        System.out.println("After deleting Max "); 
        //        st.show(); 
        //        st.deleteMin(); 
        //        System.out.println("After deleting Min "); 
        //        st.show(); 
        // 
        System.out.println("************ Exercise 3_2_15**************"); 
        System.out.println("Floor(Q) = " + st.floor("Q")); 
        System.out.println("Select (5) = " + st.select(5)); 
        System.out.println("Celing(Q) = " + st.ceiling("Q")); 
        System.out.println("rank(J) = " +  st.rank("J")); 
        //System.out.println("size(D,T) = " +  st.size("D","T")); 
        System.out.println("keys(D,T) = "); 
        for(String s:  st.keys("D","T")){ 
            System.out.println(s + " " + st.get(s)); 
        } 
    } 
} 
/* 
 * Exercise3_2_10 
 * C:\Users\ngunti\algs4\Searching\BinarySearchTrees>java-algs4 BST M < 321.txt 
 A 1****E 6****I 9****N 11****O 10****Q 4****S 7****T 8****U 5****Y 3**** 
 Height of tree is 6 
 Avergage Compares 3.5 
 Optimum Compares 1.39 
 Minimum Element A 
 Maximum Element Y 
 Floor of Element M is I 
 Ceiling of Element M is N 
 After deleting M 
 A E I N O Q S T U Y 
 After deleting Max 
 A E I N O Q S T U 
 After deleting Min 
 E I N O Q S T U 
  
  
 Exercise3_2_15 
 ************ Exercise 3_2_15************** 
 Floor(Q) = Q 
 Select (5) = Q 
 Celing(Q) = Q 
 rank(J) = 3 
 keys(D,T) = 
 D 1 
 E 0 
 J 4 
 M 6 
 Q 2 
 S 7 
 T 5 
 */