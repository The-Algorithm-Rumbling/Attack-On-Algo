#include <iostream>
#include <vector>
#include <climits>

using namespace std;
int N;
int minAns = INT_MAX;
int maxAns = INT_MIN;
vector<int> nums;
vector<string> ops = {"+", "-", "x", "/"};
vector<int> opCnts;
vector<string> picks;

int pre(string c) {
    if(c == "+" || c == "-") return 1;
    if(c == "x" || c == "/") return 2;
    return 0;
}

int Calc(int a, int b, string op) {
    if(op == "+") return a + b;
    if(op == "-") return a - b;
    if(op == "x") return a * b;
    return a / b;
}

void Calculate() {
    vector<string> s;

    for(int i=0; i<picks.size(); i++) {
        if(picks[i] == "+" || picks[i] == "-") {
            if(s.size() > 0) {
                s.push_back(picks[i]);
                s.push_back(to_string(nums[i+1]));
            }
            else {
                s.push_back(to_string(nums[i]));
                s.push_back(picks[i]);
                s.push_back(to_string(nums[i+1]));
            }
        }
        else { // 곱셈, 나눗셈일 때!
            if(s.size() > 0) {
                int num = stoi(s[s.size()-1]);
                int result = Calc(num, nums[i+1], picks[i]);
                s.pop_back();
                s.push_back(to_string(result));
            } else {
                int result = Calc(nums[i], nums[i+1], picks[i]);
                s.push_back(to_string(result));
            }
        }
    }

    // 이제 덧셈, 뺄셈만 남았다!!!!
    int result = stoi(s[0]);
    for(int i=1; i<s.size(); i=i+2) {
        result = Calc(result, stoi(s[i+1]), s[i]);
    }

    maxAns = max(maxAns, result);
    minAns = min(minAns, result);

}

void dfs(int idx) {
    if(idx == N-1) {
        Calculate();
        return;
    }

    for(int i=0; i<4; i++) {
        if(opCnts[i] > 0) {
            opCnts[i]--;
            picks.push_back(ops[i]);
            
            dfs(idx+1);
            
            opCnts[i]++;
            picks.pop_back();
        }
        
        
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    nums.resize(N, 0);
    for(int i=0; i<N; i++) {
        cin >> nums[i];
    }
    opCnts.resize(4,0);
    for(int i=0; i<4; i++) {
        cin >> opCnts[i];
    }

    dfs(0);
    cout << maxAns << "\n" << minAns;
    return 0;
}