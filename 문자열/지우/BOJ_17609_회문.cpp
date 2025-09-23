#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int ans;
string s;

void dfs(int left, int right, int dCnt) {
    if(left >= right) {
        ans = min(ans, dCnt);
        return;
    }
    if(dCnt > 2) return;
    
    if(s[left] == s[right]) {
        dfs(left+1, right-1, dCnt);
    } else {
        dfs(left+1, right, dCnt+1);
        dfs(left, right-1, dCnt+1);
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int T; cin >> T;
    while(T--) {
        cin >> s;
        ans = 2;
        dfs(0, s.size()-1, 0);
        cout << ans << "\n";
    }
    return 0;
}