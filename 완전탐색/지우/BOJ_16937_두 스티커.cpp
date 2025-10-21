#include <iostream>
#include <vector>

using namespace std;
int H, W;
int N; int maxW = 0;
vector<vector<int>> stickers;
vector<int> picks;

bool check(int a1, int b1, int a2, int b2) {
    return max(a1,b1) <= H && a2+b2 <= W || max(a1,b1) <= W && a2+b2 <= H;
}

int calculate() {
    int a = picks[0]; int b = picks[1];
    int a1 = stickers[a][0]; int a2 = stickers[a][1];
    int b1 = stickers[b][0]; int b2 = stickers[b][1];

    int sum = a1*a2 + b1*b2;
    if(sum > H*W) return 0;

    for(int i=0; i<2; i++) {
        for(int j=0; j<2; j++) {
            if(check(stickers[a][i], stickers[b][j], stickers[a][1-i], stickers[b][1-j])) return sum;
        }
    }
    return 0;
}

void dfs(int cnt, int start) {
    if(cnt == 2) {        
        maxW = max(calculate(), maxW);
        return;
    }
    
    for(int i=start; i<N; i++) {
        picks.push_back(i);
        dfs(cnt+1, i+1);
        picks.pop_back();
    }
}

int main() {
    cin >> H >> W;
    cin >> N;
    stickers.resize(N, vector<int>(2,0));

    for(int i=0; i<N; i++) {
        cin >> stickers[i][0];
        cin >> stickers[i][1];
    }

    dfs(0,0);
    cout << maxW;
    return 0;
}