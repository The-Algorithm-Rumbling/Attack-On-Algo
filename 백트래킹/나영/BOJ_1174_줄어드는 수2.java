import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static int [] arr = new int [10];
    static List<Long> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {

        for (int i = 8; i >= 0; i--) {
            arr[i] = 9 - i;
        }
        
        dfs(0, 0);

        list.sort(null);
        
        n = sc.nextInt();
        
        if (n <= list.size()) System.out.println(list.get(n-1));
        else System.out.println(-1);
    }

    static void dfs(long sum, int idx) {
        if (idx != 0) list.add(sum);

        for (int i = idx; i < 10; i++) {
            dfs(sum*10 + arr[i], i+1);
        }
        
    }
}