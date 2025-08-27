#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

string s;
int N;
vector<bool> used;
vector<bool> vis;
int maxNum = 0;

void dfs(int pos, string result, int numCnt) {
    if(pos == N && numCnt == maxNum) {
        cout << result;
        exit(0);
        return;
    }

    int num = s[pos] - '0';
    int tmpMax = maxNum;
    if(pos < N) {
        if(num <= N && !used[num]) {
            used[num] = true;
            maxNum = max(maxNum, num);
            
            if(pos == 0) dfs(pos+1, result + to_string(num), numCnt+1);
            else dfs(pos+1, result + " " + to_string(num), numCnt+1);
        
            used[num] = false;
            maxNum = tmpMax;
            
        }
    }

    if(N>=10 && pos+1 < N) {
        num = num*10 + s[pos+1] - '0';
        if(num <= N && !used[num]) {
            used[num] = true;
            maxNum = max(maxNum, num);
    
            if(pos == 0) dfs(pos+2, result + to_string(num), numCnt+1);
            else dfs(pos+2, result + " " + to_string(num), numCnt+1);
    
            used[num] = false;
            maxNum = tmpMax;
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    
    cin >> s;
    N = s.length();
    used.resize(51, false);
    
    dfs(0, "", 0);
    
    return 0;
}