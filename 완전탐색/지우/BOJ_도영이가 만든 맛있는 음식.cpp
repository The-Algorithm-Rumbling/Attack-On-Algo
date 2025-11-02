#include <iostream>
#include <vector>

using namespace std;
int N;
vector<pair<int,int>> list;
vector<int> picks;
int minDiff = 987654321;

int calc() {
    int sumS = 1; int sumB = 0;
    for(auto p : picks) {
        sumS *= list[p].first;
        sumB += list[p].second;
    }
    return abs(sumS - sumB);
}

void dfs(int diff, int start) {
    if(start != 0) minDiff = min(minDiff, diff);

    for(int i=start; i<N; i++) {
        picks.push_back(i);
        dfs(calc(), i+1);
        picks.pop_back();
    }
}

int main() {
    cin >> N;
    for(int i=0; i<N; i++) {
        int S, B; cin >> S >> B;
        list.push_back({S,B});
    }

    dfs(0,0);
    cout << minDiff;
    
    return 0;
}