package 구현.지우;

import java.util.*;
import java.io.*;

class Main {
    static int N, M, K;
    static int answer = 98765432;
    static int[][] maps;
    static int[][] arr;
    static ArrayList<Integer> picks = new ArrayList<>();
    static boolean[] vis;
    static int[][] tmp;

    static int dr[] = {1,0,-1,0};
    static int dc[] = {0,1,0,-1};

    static boolean inRange(int r, int c, int sr, int sc, int er, int ec) {
        return r>=sr && r <= er && c>=sc && c<=ec;
    }

    static void move(int sr, int sc, int er, int ec) {
        
        int num = tmp[sr][sc]; 
        int r = sr; int c = sc; int d=0;
        while(d<4) {
            int nr = r+dr[d]; int nc = c+dc[d];
            if(!inRange(nr,nc, sr,sc, er,ec)) {
                d++; 
                nr = r+dr[d]; nc = c+dc[d];
            }

            tmp[r][c] = tmp[nr][nc];
            r = nr; c = nc;
            if(r == sr && c == sc) break;
        }

        tmp[sr][sc+1] = num;
    }
    
    static int Do() {
        tmp = new int[N][M];
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                tmp[r][c] = maps[r][c];
            }
        }

        for(int idx : picks) {
           int r = arr[idx][0]; int c = arr[idx][1]; int s = arr[idx][2];

            for(int i=1; i<=s; i++) {
                move(r-i, c-i, r+i, c+i);
            }
        }

        int result = 98765432;
        for(int r=0; r<N; r++) {
            int sum = 0;
            for(int c=0; c<M; c++) {
                sum += tmp[r][c];
            }
            result = Math.min(result, sum);
        }

        return result;
    }

    static void dfs(int cnt) {
        if(cnt == K) {
            answer = Math.min(answer, Do());
            return;
        }

        for(int i=0; i<K; i++) {
            if(!vis[i]) {
                vis[i] = true; picks.add(i);
                dfs(cnt+1);
                vis[i] = false; picks.remove(picks.size()-1);
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        for(int r=0; r<N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++) {
                maps[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        arr = new int[K][3];
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken())-1; 
            arr[i][1] = Integer.parseInt(st.nextToken())-1;
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        vis = new boolean[K];
        dfs(0);
        System.out.println(answer);
        
    }
}