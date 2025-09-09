#include <iostream>
#include <vector>
#include <climits>

using namespace std;

int ans;
int N, L, R, X;
int INF = INT_MAX;
vector<int> levels;

void dfs(int s, int cnt, int maxLevel, int minLevel, int sumLevel) {
    if(cnt >= 2) {
        if(L <= sumLevel && R >= sumLevel && (maxLevel-minLevel) >= X) {
            ans++;
        }
    }
    if(s >= N) return;
    
    for(int i=s; i<N; i++) {
        int tmpMax = maxLevel; int tmpMin = minLevel;
        int currL = levels[i];
        tmpMax = max(tmpMax, currL); 
        tmpMin = min(tmpMin, currL);
        dfs(i+1, cnt+1, tmpMax, tmpMin, sumLevel + currL);
    }
}

int main() {
    cin >> N >> L >> R >> X;
    levels.resize(N, 0);
    for(int i=0; i<N; i++) {
        cin >> levels[i];
    }

    dfs(0,0,-1,INF,0);
    cout << ans;
    return 0;
}