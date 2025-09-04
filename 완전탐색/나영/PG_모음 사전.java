class Solution {
    static int answer;
    static String tmp_word;
    static char [] arr = {'A', 'E', 'I', 'O', 'U'};
    public int solution(String word) {
        tmp_word = word;
        
        for (char c : arr) {
            if (dfs("" + c)) return answer;
        }
        
        return answer;
    }
    
    static boolean dfs (String s) {
        
        answer++;
        
        if (tmp_word.equals(s)) {
            return true;
        }
        
        if (s.length() >= 5) return false;
        
        for (int i = 0; i < 5; i++) {
            if (dfs(s+arr[i])) return true;
        }
        
        return false;
    }
}