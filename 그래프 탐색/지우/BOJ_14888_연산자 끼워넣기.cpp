#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int N;
int min_ans = INT_MAX; 
int max_ans = INT_MIN;
vector<int> nums;
vector<int> cntOp;
int operators[] = {1,-1};

int NextSum(int sum, int d, int i) {
    if(d<2) {
        int nxt = nums[i] * operators[d];
        return sum + nxt;
    }
    else if(d==2) {
        return sum * nums[i];
    }
    else {
        if(sum < 0) return abs(sum) / nums[i] * -1;
        return sum/nums[i];
    }
}

void dfs(int sum, int s) {
    if(s == N) {
        min_ans = min(min_ans, sum);
        max_ans = max(max_ans, sum);
    }

    for(int d=0; d<4; d++) {
        if(cntOp[d] > 0) {
            cntOp[d]--;
            int nxtSum = NextSum(sum, d, s);
            dfs(nxtSum, s+1);
            cntOp[d]++;
        }
    }

}

int main() {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(0);
    cin >> N;
    nums.resize(N,0);
    cntOp.resize(4,0);
    for(int i=0; i<N; i++) {
        cin >> nums[i];
    }

    for(int i=0; i<4; i++) {
        cin >> cntOp[i];
    }

    dfs(nums[0],1);
    cout << max_ans << "\n" << min_ans;    
    return 0;
}