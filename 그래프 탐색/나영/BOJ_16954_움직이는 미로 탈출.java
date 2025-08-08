import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char [][] map = new char [8][8];
    static boolean [][] visited = new boolean [8][8];
    static int [] dr = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int [] dc = {0, -1, 0, 1, -1, 1, -1, 1};
    static Queue<int[]> que = new LinkedList<>();
    static Queue<int[]> wall = new LinkedList<>();
    static Queue<int[]> wall2 = new LinkedList<>();
    static int size;
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                if(map[i][j] == '#') wall.offer(new int[] {i, j});
            }
        }
        
        System.out.println(bfs(7, 0));
    }

    static int bfs(int a, int b) {
        que.offer(new int[] {a, b});
        visited[a][b] = true;

        while (true) {
            int qSize = que.size();  
            size = wall.size();
            visited = new boolean[8][8];
            boolean [][] visitedWall = new boolean[8][8];
            
            // 벽 이동
            for (int i = 0; i < size; i++) {
                int[] w = wall.poll();
                
                int nr = w[0] + 1;
                int nc = w[1];

                if(check(nr, nc)) {
                    map[nr][nc] = '#';
                    wall.offer(new int[] {nr, nc});
                    visitedWall[nr][nc] = true;
                }

                wall2.offer(w);
            }
            
            // 사람 이동
            for (int i = 0; i < qSize; i++) {
                int[] q = que.poll();
                if(map[q[0]][q[1]] != '#') que.offer(new int[] {q[0], q[1]});

                if (q[0] == 0 && q[1] == 7) return 1;

                for (int d = 0; d < 8; d++) {
                    int nr = q[0] + dr[d];
                    int nc = q[1] + dc[d];

                    if(check(nr, nc) && map[nr][nc] != '#' && !visited[nr][nc]) {
                        // System.out.println(nr + " " + nc);
                        visited[nr][nc] = true;
                        que.offer(new int[] {nr, nc});
                    }
                }
            }
            
            // 이전 벽 제거
            
            for (int i = 0; i < size; i++) {
                int[] w = wall2.poll();
                if (!visitedWall[w[0]][w[1]]) map[w[0]][w[1]] = '.';
            }
            
            if (que.size() == 0) return 0;
        }

    }

    static boolean check(int r, int c) {
        return r >= 0 && r < 8 && c >= 0 && c < 8;
    }
}