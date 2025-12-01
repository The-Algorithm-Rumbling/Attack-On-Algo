import java.util.*;
import java.lang.*;
import java.io.*;
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k;
    static int [] arr;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int [n+1];
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) {
                for (int j = 1; b*j < n+1; j++) {
                    arr[b*j] = 1-arr[b*j];
                }
            } else {
                arr[b] = 1-arr[b];
                int l = b-1; int r = b+1;

                while (l > 0 && r < n+1) {
                    if (arr[l] != arr[r]) break;
                    
                    arr[l] = 1-arr[l];
                    arr[r] = arr[l];
                    l--; r++;
                }
            }
        }
        
        for (int x = 1; x < n+1; x++) {
            System.out.print(arr[x] + " ");
            if (x%20 == 0) System.out.println();
        }
    }
}