import java.util.*;

class Main {
    static int A, B, C;
    static int[] limits = new int[3];
    static boolean[][][] vis = new boolean[201][201][201];
    static Set<Integer> ans = new TreeSet<>();
    static Queue<int[]> q = new LinkedList<>();

    static void bfs() {
        vis[0][0][C] = true;
        q.add(new int[]{0, 0, C});

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int a = curr[0]; int b = curr[1]; int c = curr[2];

            if(a == 0) ans.add(c);

            for(int s = 0; s<3; s++) {
                for(int e=0; e<3; e++) {
                    if(s == e) continue;
                    int nxt[] = {a, b, c};
                    
                    // 옮길 수 있는 물의 양 - s거 다 OR e 꽉 채워질 때까지
                    if(nxt[s]+nxt[e]-limits[e] > 0) { // e 꽉 채우고도 넘친다! 
                        nxt[s] = nxt[s]+nxt[e]-limits[e]; nxt[e] = limits[e];
                    } else { // s거 다 보내도 충분!
                        nxt[e] = nxt[s] + nxt[e]; nxt[s] = 0; 
                    }

                    int na = nxt[0]; int nb = nxt[1]; int nc = nxt[2];
                    if(!vis[na][nb][nc]) {
                        vis[na][nb][nc] = true;
                        q.add(new int[]{na,nb,nc});
                    }
                    
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        A = sc.nextInt(); B = sc.nextInt(); C = sc.nextInt();
        limits[0] = A; limits[1] = B; limits[2] = C;
        bfs();
        for(int s : ans) {
            sb.append(s).append(" ");
        }
        System.out.println(sb);
    }
}