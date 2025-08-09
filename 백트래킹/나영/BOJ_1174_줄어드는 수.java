import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int [] arr = {9,8,7,6,5,4,3,2,1,0};
    static List<Long> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        dfs(0, 0);

        list.sort(null);

        if(n <= list.size()) System.out.println(list.get(n-1));
        else System.out.println(-1);
    }

    static void dfs(int start, long sum) {
        if(start!=0) list.add(sum);
        
        for (int i = start; i < 10; i++) {
            dfs(i+1, sum*10+arr[i]);
        }
    }
}