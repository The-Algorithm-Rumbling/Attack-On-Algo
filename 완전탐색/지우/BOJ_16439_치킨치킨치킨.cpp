#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
int maxGood = -1;
vector<vector<int>> likes;
vector<int> cpicks;

int Calculate() {   
    int sum = 0;
    for(int r=0; r<N; r++) {
        int maxLike = -1;
        for(int i=0; i<cpicks.size(); i++) {
            maxLike = max(maxLike, likes[r][cpicks[i]]);
        }
        sum += maxLike;
    }
    return sum;
}

void dfs(int cnt, int start) {
    if(cnt >= 4) return;
    maxGood = max(Calculate(), maxGood);

    for(int i=start; i<M; i++) {
        cpicks.push_back(i);
        dfs(cnt+1, i+1);
        cpicks.pop_back();
    }
}

int main() {
    cin >> N >> M;
    likes.resize(N, vector<int>(M,0));

    for(int r=0; r<N; r++) {
        for(int c=0; c<M; c++) {
            cin >> likes[r][c];
        }
    }

    dfs(0,0);
    cout << maxGood;
}