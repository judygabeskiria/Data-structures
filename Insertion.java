import edu.princeton.cs.algs4.*;
import java.util.*;
public class Insertion {

    private Insertion() { }

    public static void sort(Comparable[] a) {
     int n = a.length;
        for (int i = 1; i < n; i++) {
             Comparable v = a[i];
            int start = 0, end = i;
            while (start < end) {
                int middle = start + (end - start) / 2; //middle element for binary search
                if (less(v, a[middle])) 
                {end = middle;}
                else        
                {start = middle + 1;}
            }

            //change and shift
            for (int j = i; j > start; --j)
                a[j] = a[j-1];
            a[start] = v;
        }
       
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Insertion.sort(a);
        show(a);
    }
}