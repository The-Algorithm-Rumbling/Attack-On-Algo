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
    static boolean inRange(int r, int c) {
        return r>=0 && r<N && c>=0 && c<M;
    }
    
    static int distance(int r, int c, int a, int b) {
        return Math.abs(r-a) + Math.abs(c-b);
    }

    static boolean aMove() {
        int size = A.size();
        while(size-- > 0) {
            Pos p = A.poll();
            int r = p.r; int c = p.c;
            
            int minDis = 98765432; int minD = 0;

            for(int d=1; d<=9; d++) {
                if(d==5) continue;
                int nr = r + dr[d]; int nc = c + dc[d];
                if(!inRange(nr,nc)) continue;
                int dis = distance(jR,jC,nr,nc);
                if(minDis > dis) {
                    minDis = dis;
                    minD = d;
                }
            }

            int nr = r + dr[minD];
            int nc = c + dc[minD];
            Arr[r][c]--; 
            Arr[nr][nc]++;

            A.add(new Pos(nr,nc));
        }

        size = A.size();
        HashSet<Pos> tmps = new HashSet<>();
        
        while(size-- > 0) {
            Pos p = A.poll();
            int r = p.r; int c = p.c;
            if(Jrr[r][c]) return false;
            if(Arr[r][c] > 1) { // 폭파!!
                tmps.add(new Pos(r,c));
                continue;
            }
            A.add(new Pos(r,c));
        }

        for(Pos t : tmps) {
            Arr[t.r][t.c] = 0;
        }
        
        return true;
    }

    static boolean jMove(int d) {
        int nr = jR + dr[d];
        int nc = jC + dc[d];

        Jrr[jR][jC] = false;
        Jrr[nr][nc] = true;
        jR = nr; jC = nc;

        // 아두이노 칸 확인
        if(Arr[jR][jC] > 0) {
            return false;
        }

        return true;
    }
    
    static int N, M, jR, jC;
    static boolean[][] Jrr;
    static int[][] Arr;
    static Queue<Pos> A = new LinkedList<>();
    static ArrayList<Integer> commands = new ArrayList<>();
    static int dr[] = {0, 1,1,1,0,0,0,-1,-1,-1};
    static int dc[] = {0, -1,0,1,-1,0,1,-1,0,1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Jrr = new boolean[N][M];
        Arr = new int[N][M];

        for(int r=0; r<N; r++) {
            String line = br.readLine();
            for(int c=0; c<M; c++) {
                char ch = line.charAt(c);
                if(ch == 'I') {
                    jR = r; jC = c;
                    Jrr[r][c] = true;
                }
                else if(ch == 'R') {
                    A.add(new Pos(r,c));
                    Arr[r][c] = 1;
                }
            }
        }

        int cnt = 0;
        String line = br.readLine();
        for(int i=0; i<line.length(); i++) {
            int command = line.charAt(i) - '0';
            cnt++;

            if(!jMove(command)) {
                System.out.println("kraj " + cnt);
                return;
            }

            if(!aMove()) {
                System.out.println("kraj " + cnt);
                return;
            }
        }

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(Jrr[r][c]) System.out.print('I');
                else if (Arr[r][c] > 0) System.out.print('R');
                else System.out.print('.');
            }
            System.out.println();    
        }
        
    }
}