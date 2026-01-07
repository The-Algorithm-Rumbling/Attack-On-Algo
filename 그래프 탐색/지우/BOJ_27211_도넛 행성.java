import java.util.*;

class Main {
    static int N, M;
    static int[][] maps; 
    static boolean[][] vis;
    static Queue<int[]> q = new LinkedList<>();

    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    static void bfs(int sr, int sc) {
        vis[sr][sc] = true;
        q.add(new int[]{sr,sc});

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0]; int c = curr[1];

            for(int d=0; d<4; d++) {
                int nr = r+dr[d]; int nc = c+dc[d];
                nr = (nr+N)%N; nc = (nc+M)%M;

                if(maps[nr][nc] == 0 && !vis[nr][nc]) {
                    vis[nr][nc] = true;
                    q.add(new int[]{nr,nc});
                }
                
            }
        }
        
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        maps = new int[N][M];
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                maps[r][c] = sc.nextInt();
            }
        }

        int answer = 0;
        vis = new boolean[N][M];
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(maps[r][c] == 0 && !vis[r][c]) {
                    answer++;
                    bfs(r,c);
                }
            }
        }

        System.out.println(answer);
        
    }
}
