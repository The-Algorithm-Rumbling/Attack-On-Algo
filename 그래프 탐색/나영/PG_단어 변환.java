import java.util.*;

class Solution {
    static boolean isS;
    static boolean [] vis;
    static String [] words;
    static String begin, target;
    static Queue<int []> que = new LinkedList<>();
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        this.begin = begin;
        this.target = target;
        this.words = words;
        
        for (String word : words) {
            if (target.equals(word)) isS = true;
        }
        
        if (!isS) return answer;
        
        vis = new boolean [words.length];
        
        for (int i = 0; i < words.length; i++) {
            if (check(begin, words[i])) {
                vis[i] = true;
                que.offer(new int [] {i, 1});
            }
        }
        
        return bfs();
    }
    
    static int bfs() {
        while (!que.isEmpty()) {
            int [] q = que.poll();
            String s = words[q[0]];
            
            if (s.equals(target)) return q[1];
            
            for (int i = 0; i < words.length; i++) {
                if (!vis[i] && check(s, words[i])) {
                    vis[i] = true;
                    que.offer(new int [] {i, q[1] + 1});
                }
            }
        }
        
        return 0;
    }
    
    static boolean check(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) cnt++;
        }
        
        return cnt == 1;
    }
}