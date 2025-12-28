import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int A, B, C;
    static Set<Integer> set = new TreeSet<>();
    static boolean [][][] vis;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        vis = new boolean [A+1][B+1][C+1];
        
        dfs(0, 0, C);
        
        for (int s : set) sb.append(s).append(" ");
        
        System.out.println(sb.toString());
    }

    static void dfs(int a, int b, int c) {
        if (vis[a][b][c]) return;
        vis[a][b][c] = true;
    
        if (a == 0) set.add(c);

        // A -> B
        int nb = Math.min(B, b + a);
        int na = a - (nb - b);
        dfs(na, nb, c);

        // B -> A
        na = Math.min(A, a + b);
        nb = b - (na - a);
        dfs(na, nb, c);
            
        // B -> C
        int nc = Math.min(C, c + b);
        nb = b - (nc - c);
        dfs(a, nb, nc);
        
        // C -> B
        nb = Math.min(B, b + c);
        nc = c - (nb - b);
        dfs(a, nb, nc);
        
        // A -> C
        nc = Math.min(C, c + a);
        na = a - (nc - c);
        dfs(na, b, nc);
        
        // C -> A
        na = Math.min(A, a + c);
        nc = c - (na - a);
        dfs(na, b, nc);
    }
}