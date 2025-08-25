#include <iostream>
#include <vector>

using namespace std;

int N;
vector<vector<int>> cakes;
vector<int> picks;
vector<vector<int>> memo; // 0: 미계산 / -1: 실패

bool dfs(int day, int pre) { 
    if(day == N) {
        return true;
    }

    if(memo[day][pre] == -1) return false; // 이전에 실패했으면 더 나아가지 않는다.
    
    for(int c : cakes[day]) {
        if(pre != c) {
            picks.push_back(c);
            if(dfs(day+1, c)) return true; // 성공했음 탐색 종료
            picks.pop_back(); // 실패했으면 현재 떡 제거 후 다른 거 시도 
        }
    }

    // 여기까지 내려왔다는 것.. 아직 성공하지 못했다는 것..
    memo[day][pre] = -1;
    return false;

}

int main() {
    cin >> N;
    cakes.resize(N, vector<int>(0));
    for(int i=0; i<N; i++) {
        int m; cin >> m;
        for(int c=0; c<m; c++) {
            int a; cin >> a;
            cakes[i].push_back(a);
        }
    }

    memo.resize(N+1, vector<int>(10, 0));
    if(dfs(0, 0)) {
        for(int p : picks) {
            cout << p << "\n";
        }
    } else {
        cout << -1;
    }
    return 0;
}