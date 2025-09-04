#include <string>
#include <vector>

using namespace std;
vector<vector<int>> ds; 
vector<bool> vis;
int answer = -1;
int n;

void dfs(int power, int cnt) {
    answer = max(answer, cnt);
    
    for(int i=0; i<n; i++) {
        if(!vis[i] && power >= ds[i][0]) {
            vis[i] = true;
            dfs(power-ds[i][1], cnt+1);
            vis[i] = false;
        }
    }
}

int solution(int k, vector<vector<int>> dungeons) {
    n = dungeons.size();
    ds = dungeons;
    vis.resize(n,false);
    
    dfs(k, 0);

    return answer;
}