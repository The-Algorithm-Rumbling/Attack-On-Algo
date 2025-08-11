#include <iostream>
#include <vector>

using namespace std;

int N, M;
int ans;
vector<vector<bool>> maps;

void dfs(int idx) {
    if(idx == N*M) { // 끝까지 다 돌았다! (네모 없는 애들만 모았음.!)
        ans++;
        return;
    }

    int r = idx/M + 1; 
    int c = idx%M + 1;

    if(maps[r-1][c] && maps[r][c-1] && maps[r-1][c-1]) { // 위쪽 방향으로 2*2 모두 채워졌다?
        dfs(idx+1); //절대 maps[r][c] 에 채우면 안돼! (이럼 만들어지니까)
    }
    else {
        maps[r][c] = true;
        dfs(idx+1);
        maps[r][c] = false;
        dfs(idx+1);
    }
}

int main() {
    cin >> N >> M;
    maps.resize(N+1, vector<bool>(M+1, false));

    dfs(0);
    cout << ans;
                                              
    return 0;
}