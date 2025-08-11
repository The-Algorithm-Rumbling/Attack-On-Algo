import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int [][] map = new int [9][9];
    static boolean isS;
    static List<int[]> list = new ArrayList<>();
    static Set<Integer> [] set1 = new HashSet[9];
    static Set<Integer> [] set2 = new HashSet[9];
    static Set<Integer> [][] set3 = new HashSet[3][3];
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            set1[i] = new HashSet<>();
            set2[i] = new HashSet<>();
        }

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                set3[r][c] = new HashSet<>();
            }
        }
        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                map[r][c] = sc.nextInt();
                if (map[r][c] == 0) list.add(new int[] {r, c});
                else {
                    set1[r].add(map[r][c]);
                    set2[c].add(map[r][c]);
                    set3[r/3][c/3].add(map[r][c]);
                }
            }
        }

        dfs(0);
    }

    static void dfs(int depth) {
        if (isS) return;
        
        if (depth == list.size()) {
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    System.out.print(map[r][c] + " ");
                }
                System.out.println();
            }
            isS = true;
            return;
        }

        int [] p = list.get(depth);
        int r = p[0];
        int c = p[1];
        
        for (int i = 1; i < 10; i++) {
            if (check(r, c, i)) {
                set1[r].add(i);
                set2[c].add(i);
                set3[r/3][c/3].add(i);
                
                map[r][c] = i;
                
                dfs(depth+1);
                
                set1[r].remove(i);
                set2[c].remove(i);
                set3[r/3][c/3].remove(i);
                
                map[r][c] = 0;
            }
        }
    }

    static boolean check(int r, int c, int tmp) {
        if (set1[r].contains(tmp)) return false; 
        if (set2[c].contains(tmp)) return false; 
        if (set3[r/3][c/3].contains(tmp)) return false; 
        return true;
    }
}