import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner (System.in);
    static boolean [] vis;
    static int n, min=Integer.MAX_VALUE;
    static int [][] food;
    static int [] diet, arr;
    static List<Integer> prospect = new ArrayList<>();
    static List<Integer> list;
    public static void main(String[] args) {
        n = sc.nextInt();

        food = new int [n][5];
        diet = new int [4];
        arr = new int [5];

        for (int i = 0; i < 4; i++) {
            diet[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                food[i][j] = sc.nextInt();
            }
        }

        dfs(0);

        if (min == Integer.MAX_VALUE) System.out.print(-1);
        else {
            System.out.println(min);
            for (int i : list) System.out.print((i+1) + " ");
        }
    }
    
    static void dfs (int idx) {
        if (check()) {
            list = new ArrayList<>(prospect);
            min = arr[4];
            return;
        }
        
        if (idx == n) return;

        for (int i = idx; i < n; i++) {
            for (int j = 0; j < 5; j++) {
                arr[j] += food[i][j];
            }

            prospect.add(i);

            dfs(i + 1);
            
            for (int j = 0; j < 5; j++) {
                arr[j] -= food[i][j];
            }
            
            prospect.remove(prospect.size()-1);
        }
    }

    static boolean check() {
        if (min <= arr[4]) return false;
        for (int i = 0; i < 4; i++) {
            if (arr[i] < diet[i]) return false;
        }

        return true;
    }

}