import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static Scanner sc = new Scanner(System.in);
    static int T, n;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            n = sc.nextInt();

            dfs(1, "1");
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }

    static void dfs(int idx, String s1) {
        if (idx == n) {
            String tmp = s1.replaceAll(" ", "");
            
            int sum = check(tmp);
            
            if (sum == 0) {
                sb.append(s1).append("\n");
            }
            return;
        }

        dfs(idx + 1, s1 + " " + (idx + 1)); // 공백(이어붙이기)
        dfs(idx + 1, s1 + "+" + (idx + 1)); // 더하기
        dfs(idx + 1, s1 + "-" + (idx + 1)); // 빼기
    }

    static int check(String s) {
        int sum = 0;
        int currentNumber = 0;
        char lastOperator = '+';
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c - '0' >= 1) {
                currentNumber = currentNumber * 10 + (c - '0');
            } else {
                // 연산자가 나오면 이전에 쌓아둔 숫자를 계산에 반영
                if (lastOperator == '+') {
                    sum += currentNumber;
                } else {
                    sum -= currentNumber;
                }
                // 현재 숫자를 초기화하고, 새로운 연산자로 갱신
                currentNumber = 0;
                lastOperator = c;
            }
        }
        
        // 반복문이 끝난 후 마지막에 남은 숫자를 최종적으로 계산에 포함
        if (lastOperator == '+') {
            sum += currentNumber;
        } else {
            sum -= currentNumber;
        }
        return sum;
    }
}