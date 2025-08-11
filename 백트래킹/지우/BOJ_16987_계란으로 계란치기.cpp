#include <iostream>
#include <vector>

using namespace std;

int N;
int maxCnt = -1;
vector<int> dur;
vector<int> w;

void dfs(int sIdx, int cnt) {
    maxCnt = max(cnt, maxCnt);
    if(sIdx == N) return;

    for(int i=0; i<N; i++) {
        if(i == sIdx) continue;
        if(dur[sIdx] > 0) {
            if(dur[i] > 0) {
                dur[sIdx] = dur[sIdx] - w[i];
                dur[i] = dur[i] - w[sIdx];
     
                int tmpCnt = cnt;
                if(dur[sIdx] <= 0) tmpCnt++;
                if(dur[i] <= 0) tmpCnt++;
                dfs(sIdx+1, tmpCnt);
    
                dur[sIdx] = dur[sIdx] + w[i];
                dur[i] = dur[i] + w[sIdx];
            }
        }
        else {
            dfs(sIdx+1, cnt);
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    dur.resize(N,0);
    w.resize(N,0);

    for(int i=0; i<N; i++) {
        cin >> dur[i];
        cin >> w[i];
    }

    dfs(0,0);
    cout << maxCnt;
    return 0;
}