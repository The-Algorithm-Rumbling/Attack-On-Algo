#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int L, C;
vector<bool> vis;
vector<char> alphabets;

void dfs(int s, int cnt, int consCnt, int vowCnt) {
    if(cnt == L) {
        if(consCnt < 1 || vowCnt < 2) return;
        for(int i=0; i<C; i++) {
            if(vis[i]) cout << alphabets[i];
        }
        cout << "\n";
        return;
    }

    for(int i=s; i<C; i++) {
        int tmpCons = consCnt; int tmpVow = vowCnt;
        if(alphabets[i] == 'a' || alphabets[i] == 'e' ||alphabets[i] == 'i' ||alphabets[i] == 'o' ||alphabets[i] == 'u') {
            tmpCons++;
        } else {
            tmpVow++;
        }

        vis[i] = true;
        dfs(i+1, cnt+1, tmpCons, tmpVow);
        vis[i] = false;
    }
}

int main() {
    cin >> L >> C;
    vis.resize(C, false);
    alphabets.resize(C, '\0');

    for(int i=0; i<C; i++) {
        cin >> alphabets[i];
    }
    sort(alphabets.begin(), alphabets.end());

    dfs(0,0,0,0);
    
    return 0;
}