#include <iostream>
#include <vector>
#include <queue>
#include <set>

using namespace std;
int N, K;
int INF = 98765432;
queue<pair<int,long long>> q;
set<int> sets;

long long  bfs() {
    int cnt = 0; long long sum = 0;
    
    while(!q.empty()) {
        auto [curr, sad] = q.front(); q.pop();
        
        if(sad != 0) cnt++; 
        sum += sad;
        if(cnt == K) return sum;

        for(int i=-1; i<=1; i++) {
            if(i == 0) continue;
            
            int nxt = curr + i;
            if(sets.find(nxt) == sets.end()) {
                sets.insert(nxt);
                q.push({nxt, sad+1});
            }
        }
        
    }
    return -1;
}

int main() {
    cin >> N >> K;

    for(int i=0; i<N; i++) {
        int a; cin >> a;
        sets.insert(a);
        q.push({a,0});
    }

    cout << bfs();
    
    return 0;
}