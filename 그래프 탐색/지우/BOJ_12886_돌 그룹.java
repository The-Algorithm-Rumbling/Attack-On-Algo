import java.util.*;

class Main {
    static int INF = Integer.MAX_VALUE;
    static boolean vis[][] = new boolean[1501][1501];
    static Queue<int[]> q = new LinkedList<>();
    
    static int bfs(int A, int B, int C) {
        vis[A][B] = true;
        q.add(new int[]{A, B, C});

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int a = curr[0]; int b = curr[1]; int c = curr[2];
            if(a==b && a==c && b==c) return 1;

            for(int s=0; s<3; s++) {
                for(int e=0; e<3; e++) {
                    if(s==e || curr[s]==curr[e]) continue;

                    int[] nxt = {a,b,c};
                    if(nxt[s] < nxt[e]) {
                        nxt[e] = nxt[e] - nxt[s];
                        nxt[s] = nxt[s] + nxt[s];
                    } else {
                        nxt[s] = nxt[s] - nxt[e];
                        nxt[e] = nxt[e] + nxt[e];
                    }

                    int na = nxt[0]; int nb = nxt[1]; int nc = nxt[2];
                    if(!vis[na][nb]) {
                        vis[na][nb] = true;
                        q.add(new int[]{na, nb, nc});
                    }
                }
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt(); 
        int B = sc.nextInt();
        int C = sc.nextInt();
        System.out.println(bfs(A, B, C));
    }
}