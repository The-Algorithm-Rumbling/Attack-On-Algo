import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, idx=1;
    static int [] arr;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        arr = new int [2*n+1];
        int front = 1;
        int rear = n;

        for (int i = 1; i < n+1; i++) arr[i] = i;

        while (front != rear) {
            // 맨 앞 카드 제거
            front++;

            // 두 번째 카드 뒤로 이동
            arr[++rear] = arr[front];
            front++;
        }
        
        System.out.println(arr[front]);
    }
}