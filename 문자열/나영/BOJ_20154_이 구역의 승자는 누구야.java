import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static char [] charn;
    static int [] cnt, newSum;
    static int [] arr = {3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1};
    static int max, ans;
    public static void main(String[] args) {
        charn = sc.nextLine().toCharArray();
        
        if (charn.length % 2 == 0) cnt = new int [charn.length / 2];
        else cnt = new int [charn.length/2 + 1];
        
        for (int i = 0; i < cnt.length; i++) {
            if (i*2 == charn.length-1) {
                cnt[i] = arr[charn[i*2]-'A'];
                break;
            }
            
            int n1 = i * 2;
            int n2 = n1 + 1;

            cnt[i] = arr[charn[n1]-'A'] + arr[charn[n2]-'A'];
        }

        while (cnt.length > 1) {
            find(cnt);
        }

        if (cnt[0] % 2 == 0) System.out.println("You're the winner?");
        else System.out.println("I'm a winner!");

        System.out.println(sb.toString());
    }

    static void find (int [] sum) {
        if (sum.length % 2 == 0) newSum = new int [sum.length/2];
        else newSum = new int [sum.length/2 + 1];
        
        for (int i = 0; i < newSum.length; i++) {
            if (i*2 == sum.length-1) {
                newSum[i] = sum[i*2];
                break;
            }
            int n1 = i * 2;
            int n2 = n1 + 1;

            newSum[i] = sum[n1] + sum[n2];
        }

        cnt = newSum.clone();
    }
}