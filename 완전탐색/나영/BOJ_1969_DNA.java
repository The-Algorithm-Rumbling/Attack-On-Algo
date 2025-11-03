import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, max, idx, dis;
    static String[] str;
    static int [] sum;
    static char [] bases = {'A', 'C', 'G', 'T'};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();

        str = new String [n];

        for (int i = 0; i < n; i++) {
            str[i] = sc.nextLine();
        }

        find();
        
        System.out.println(sb.toString());
        System.out.println(dis);
    }

    static void find () {
        for (int i = 0; i < m; i++) {
            sum = new int [4];
            for (int j = 0; j < n; j++) {
                char c = str[j].charAt(i);
                if (c == 'A') sum[0]++;
                else if (c == 'C') sum[1]++;
                else if (c == 'G') sum[2]++;
                else if (c == 'T') sum[3]++;
            }

            max = 0;
            idx = 0;

            for (int j = 0; j < 4; j++) {
                if (max < sum[j]) {
                    max = sum[j];
                    idx = j;
                }
            }

            sb.append(bases[idx]);
            dis += n - max;
        }
    }
}