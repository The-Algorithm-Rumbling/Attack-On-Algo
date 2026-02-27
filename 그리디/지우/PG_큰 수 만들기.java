package 그리디.지우;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int len = number.length() - k; // 최종 길이
        
        for(int i=0; i<number.length(); i++) {
            char ch = number.charAt(i);
            
            while(k>0 && sb.length() > 0 && sb.charAt(sb.length()-1) < ch) { // 지금 들어오는 수가 더 큽니다~!! 길을 비키세요~!
                sb.deleteCharAt(sb.length()-1); // 마지막 원소 out - 가능한 한도 내에 더 큰 수가 들어왔기 때문!
                k--; // 뺄 수 있는 수 줄여주기
            }
            
            sb.append(ch);
        }
        
        return sb.substring(0, len); // 98765435 <- 내림차순인 경우 k--가 안됨
    }
}