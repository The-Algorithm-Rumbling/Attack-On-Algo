class Solution {
    static int [] numbers;
    static int target, answer = 0;
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        dfs(0, 0);
        
        return answer;
    }
    
    static void dfs (int idx, int sum) {
        if (idx == numbers.length) {
            if (sum == target) answer++;
            return;
        }
        
        dfs(idx+1, sum + numbers[idx]);
        dfs(idx+1, sum - numbers[idx]);
    }
}