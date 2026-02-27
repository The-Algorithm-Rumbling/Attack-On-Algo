package 시뮬레이션.지우;

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
    static int N, M, D;
    static int max = -1;
    static ArrayList<Pos> enemy = new ArrayList<>();
    static ArrayList<Pos> picks = new ArrayList<>();
    static ArrayList<Pos> eArr;

    static int distance(int r, int c ,int a , int b) {
        return Math.abs(r-a) + Math.abs(c-b);
    }
    
    static int Attack() {
        Set<Pos> sets = new HashSet<>();
        int cnt = 0;
        
        for(Pos p : picks) {
            int minDis = D+1; Pos a = null;

            for(Pos e : eArr) {
                int dis = distance(p.r, p.c, e.r, e.c);
                if(dis > D) continue;
                if(a == null) {
                    minDis = dis;
                    a = e;
                }
                else if(dis < minDis || (minDis == dis && e.c < a.c)) { // 가장 왼쪽!
                    minDis = dis;
                    a = e;
                }
            }
            if(a != null) sets.add(a);
        }

        cnt = sets.size();
        for(Pos x : sets) {
            eArr.remove(x);
        }
        
        return cnt;
    }

    static int Play() {
        int sum = 0;
        eArr = new ArrayList<>();
        for(Pos e : enemy) {
            eArr.add(new Pos(e.r, e.c));
        }

        while(!eArr.isEmpty()) {
            sum += Attack();
            
            Set<Pos> sets = new HashSet<>();
            for(Pos e : eArr) {
                e.r = e.r + 1;
                if(e.r == N) sets.add(e);
            }

            for(Pos x : sets) {
                eArr.remove(x);
            }
        }

        return sum;
    }

    static void dfs(int cnt , int start) {
        if(cnt == 3) {
            max = Math.max(Play(), max);
            return;
        }
        
        for(int c=start; c<M; c++) {
            picks.add(new Pos(N, c));
            dfs(cnt+1, c+1);
            picks.remove(picks.size()-1);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        D = sc.nextInt();

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                int a = sc.nextInt();
                if(a == 1) {
                    enemy.add(new Pos(r,c));
                }
            }
        }

        dfs(0, 0);
        System.out.println(max);
    }
}