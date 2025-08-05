#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
#include <cmath>

using namespace std;

int N; int ans = 0;
vector<tuple<int,int,int>> inputs;
vector<vector<int>> trees;
vector<bool> vis;

bool cmp(tuple<int,int,int> a, tuple<int,int,int> b) {
    return get<2>(a) < get<2>(b); // 오름차순
}

bool inOrOut(tuple<int,int,int> a, tuple<int,int,int> b) {
    auto [x1, y1, r1] = a;
    auto [x2, y2, r2] = b;

    long double d1 = x2-x1;
    long double d2 = y2-y1;
    long double d = sqrt(d1*d1 + d2*d2); // pow(,2)는 Float 기반 연산이라 이게 더 안정적이라고 함 

    // 두 원 서로 
    if(d==0 || d < abs(r1-r2)) return 1; // 내부
    return 0; // 외부
}

void Connect(int curr) {
    int nxt = curr + 1;
    bool alreadySeen = false;

    while(nxt < inputs.size()) {
        if(inOrOut(inputs[curr], inputs[nxt])) { // 서로 내부임!
            if(!trees[nxt].empty()) alreadySeen = true; // 이미 저번에 다 돌아서 그 다음 연결 노드 다 담았음 
            
            trees[curr].push_back(nxt);
            trees[nxt].push_back(curr);

            curr = nxt; // 이제 curr이었던 애 부모부터 연결관계 확인해보자
        }

        if(alreadySeen) return;
        nxt++;
    }
}

int dfs(int node, int depth) {
    int maxDepth = depth;
    for(auto nxt : trees[node]) {
        if(!vis[nxt]) {
            vis[nxt] = true;
            maxDepth = max(maxDepth, dfs(nxt, depth+1));
            vis[nxt] = false;
        }
    }
    return maxDepth;
}
 
int main() {
    cin >> N;
    inputs.clear();
    trees.resize(N+1);
    vis.resize(N+1, false);
    tuple<int,int,int> 좌표평면 = make_tuple(0,0,20000000); // 훨씬 크게 만들어주기
    inputs.push_back(좌표평면);

    for(int i=0; i<N; i++) {
        int x, y, r;
        cin >> x >> y >> r;

        tuple<int,int,int> input = make_tuple(x,y,r);
        inputs.push_back(input);
    }

    sort(inputs.begin(), inputs.end(), cmp);

    // 처음부터 끝까지 포함관계끼리 트리로 연결해보기 
    for(int i=0; i<inputs.size(); i++) {
        if(trees[i].empty()) {
            Connect(i);
        }
    }

    // DFS로 조건에 알맞게 제일 깊은 depth를 구해라
    for(int i=0; i<inputs.size(); i++) {
        vis[i] = true;
        ans = max(ans, dfs(i,0));
        vis[i] = false;
    }

    cout << ans;
    
    return 0;
}