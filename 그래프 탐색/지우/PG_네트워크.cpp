#include <string>
#include <vector>
#include <queue>

using namespace std;
int N;
vector<vector<int>> coms;
vector<bool> vis;
queue<int> q;

void bfs(int start) {
    vis[start] = true;
    q.push(start);
    
    while(!q.empty()) {
        int curr = q.front(); q.pop();
        
        for(int nxt=0; nxt<N; nxt++) {
            if(!vis[nxt] && coms[curr][nxt]==1) {
                vis[nxt] = true;
                q.push(nxt);
            }
        }
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    coms = computers; N = n;
    vis.resize(n,false);
    
    for(int i=0; i<n; i++) {
        if(!vis[i]) {
            bfs(i);
            answer++;
        }
    }
    
    return answer;
}