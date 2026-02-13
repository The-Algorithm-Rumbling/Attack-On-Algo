import java.util.*;
import java.io.*;

class Pos {
    int r; int c;

    Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Main {
    static int n, sr, sc, er, ec;
    static Pos[] stores;
    static boolean[] vis;

    static int distance(int a, int b, int c, int d) {
        return Math.abs(a-c) + Math.abs(b-d);
    }

    static String bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(sr,sc));

        while(!q.isEmpty()) {
            Pos curr = q.poll();
            int r = curr.r; int c = curr.c;

            // 편의점 체크
            for(int i=0; i<n; i++) {
                if(!vis[i] && distance(r, c, stores[i].r, stores[i].c) <= 1000) {
                    vis[i] = true;
                    q.add(new Pos(stores[i].r , stores[i].c));
                }
            }

            // 목적지 체크
            if(distance(r, c, er, ec) <= 1000) {
                return "happy";
            }
        }

        return "sad";
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            stores = new Pos[n];
            vis = new boolean[n];

            st = new StringTokenizer(br.readLine());
            sr = Integer.parseInt(st.nextToken()); sc = Integer.parseInt(st.nextToken());

            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken()); int c = Integer.parseInt(st.nextToken());
                stores[i] = new Pos(r, c);
            }
            
            st = new StringTokenizer(br.readLine());
            er = Integer.parseInt(st.nextToken()); ec = Integer.parseInt(st.nextToken());

            sb.append(bfs()).append("\n");
        }

        System.out.println(sb);
        
    }
}