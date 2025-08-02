#include <iostream>
#include <unordered_set>
#include <vector>

using namespace std;

int T; int N; int cnt;
vector<int> adj;
vector<bool> vis;
vector<bool> done;

void dfs(int currStu);

int main() {
    cin >> T;
    for(int tc=1; tc<=T; tc++) {
        cin >> N; cnt = 0;
        adj.assign(N+1,0);
        vis.assign(N+1,false);
        done.assign(N+1,false); // 완전히 탐색이 끝났는지 확인하는 용도
        

        for(int i=1; i<=N; i++) {
            cin >> adj[i];
        }

        for(int i=1; i<=N; i++) {
            if(!vis[i]) {
                dfs(i);
            }
        }
        cout << N - cnt << "\n";
    }
    
    return 0;
}

void dfs(int currStu) {

    vis[currStu] = true;
    int nxt = adj[currStu]; 

    if(!vis[nxt]) {
        dfs(nxt);
    }
    else if(!done[nxt]) { // '이미 발견함 즉, currStu 포함 사이클 발생 + '이전 경로에서 다 돌아본 적 없는 친구'
        for(int i = nxt; i != currStu; i = adj[i]) { // 인접 -> 인접으로 사이클 돌기 
            cnt++;
        }
        cnt++;
    }
    
    done[currStu] = true; // 이 노드 탐색 완료! 
    return;
}