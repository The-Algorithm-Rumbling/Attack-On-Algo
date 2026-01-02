import java.util.*;
import java.io.*;

class Main {
    static int L, R, C;
    static char[][][] maps; 
    static boolean[][][] vis;
    
    static int[] di = {1,-1,0,0,0,0};
    static int[] dr = {0,0, 1, 0, -1, 0};
    static int[] dc = {0,0, 0, 1, 0, -1};

    static boolean inRange(int i, int r, int c) {
        return i>=0 && i<L && r>=0 && r<R && c>=0 && c<C;
    }

    static int bfs(int si, int sr, int sc) {
        Queue<int[]> q = new LinkedList<>();
        vis[si][sr][sc] = true;
        q.add(new int[]{si, sr, sc, 0});

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int i = curr[0]; int r = curr[1]; int c = curr[2]; int time = curr[3];
            if(maps[i][r][c] == 'E') {
                return time;
            }
            
            for(int d=0; d<6; d++) {
                int ni = i+di[d]; int nr = r+dr[d]; int nc= c+dc[d];
                if(inRange(ni, nr, nc) && !vis[ni][nr][nc] && maps[ni][nr][nc] != '#') {
                    vis[ni][nr][nc] = true;
                    q.add(new int[]{ni, nr, nc, time+1});
                }
            }
        }
        return -1;
        
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
        
        while( L!=0 && R!=0 && C!=0) {
            int si = 0; int sr = 0; int sc = 0; 
            maps = new char[L][R][C];
            vis = new boolean[L][R][C];
            
            for(int i=0; i<L; i++) {
                for(int r=0; r<R; r++) {
                    String s = br.readLine();
                    for(int c=0; c<C; c++) {
                        maps[i][r][c] = s.charAt(c);
                        if(maps[i][r][c] == 'S') {
                            si = i; sr = r; sc = c;
                        }
                    }
                }
                br.readLine(); // 층 사이 빈 줄!
            }

            int answer = bfs(si, sr, sc);
            if(answer == -1) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + answer + " minute(s).");
            }

            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken()); R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken());
        }
    }
}