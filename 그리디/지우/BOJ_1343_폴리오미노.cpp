#include <iostream>
#include <vector>

using namespace std;
string s;

bool check(int idx, int len) {
    for(int i=idx; i<idx+len; i++) {
        if(i >= s.size() || s[i] != 'X') return false;
    }
    return true;
}

void dfs(int idx, string curr) {
    if(curr.size() == s.size()) {
        cout << curr;
        exit(0);
    }
    if(idx >= s.size()) return;
    
    
    if(s[idx] == '.') {
        dfs(idx+1, curr+".");
    }
    else {
        // AAAA
        if(check(idx, 4)) {
            dfs(idx+4, curr+"AAAA");
        }
        if(check(idx, 2)) {
            dfs(idx+2, curr+"BB");
        }
    }
}

int main() {
    cin >> s;
    dfs(0,"");
    cout << -1;
    return 0;
}