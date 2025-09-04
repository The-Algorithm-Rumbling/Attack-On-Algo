import java.util.*;

class Solution {
    static int [] arr;
    static boolean [] visited;
    static Set<Integer> set = new HashSet<>();
    public int solution(String numbers) {
        int answer = 0;
        
        arr = new int [numbers.length()];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = numbers.charAt(i) - '0';
        }
        
        visited = new boolean [arr.length];
        
        dfs(0);
        
        return set.size();
    }
    
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        
        return true;
    }
    
    static void dfs (int sum) {
        if (isPrime(sum)) {
            set.add(sum);
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(sum * 10 + arr[i]);
            visited[i] = false;
        }
    }
}