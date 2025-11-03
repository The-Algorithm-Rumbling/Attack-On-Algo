#include <iostream>
#include <vector>

using namespace std;

int N;
vector<int> times;
vector<int> salaries;
int maxProfit = -1;

void dfs(int profit, int day) {
    maxProfit = max(maxProfit, profit);
    
    for(int i=day; i<N; i++) {
        if(i + times[i] <= N) {
            dfs(profit+salaries[i], i+times[i]);
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    times.resize(N,0);
    salaries.resize(N,0);

    for(int i=0; i<N; i++) {
        cin >> times[i];
        cin >> salaries[i];
    }

    dfs(0,0);
    cout << maxProfit;
    return 0;
}