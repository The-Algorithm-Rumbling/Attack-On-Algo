#include <string>
#include <vector>

using namespace std;
int answer = 0;
vector<int> nums;
int t;

void dfs(int sum, int idx) {
        
    if(idx == nums.size()) {
        if(t == sum) {
            answer++;
        }
        return;
    }
    dfs(sum-nums[idx], idx+1);
    dfs(sum+nums[idx], idx+1);
}

int solution(vector<int> numbers, int target) {
    nums = numbers;
    t = target;
    
    dfs(0, 0);
    return answer;
}