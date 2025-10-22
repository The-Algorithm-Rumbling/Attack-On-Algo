#include <iostream>
#include <vector>

using namespace std;

string T; int N;
vector<int> costs;
vector<string> books;
int INF = 98765432;
int minCost = INF;

int check(string s) {
    vector<bool> visS(s.size(), false);
    int visCnt = 0;

    for(int i=0; i<T.size(); i++) {
        for(int j=0; j<s.size(); j++) {
            if(visS[j]) continue;
            if(T[i] == s[j]) {
                visCnt++;
                visS[j] = true;
                break;
            }
        }
    }
    return visCnt == T.size();
}

void dfs(int cost, string s, int start) {
    if(cost != 0 && check(s)) {
        minCost = min(minCost, cost);
    }
    
    for(int i=start; i<N; i++) {
        dfs(cost+costs[i], s+books[i], i+1);
    }
}

int main() {
    cin >> T >> N;
    costs.resize(N, 0);
    books.resize(N, "");
    
    for(int i=0; i<N; i++) {
        int cost; string book;
        cin >> cost >> book;
        costs[i] = cost;
        books[i] = book;
    }

    dfs(0, "", 0);
    if(minCost == INF) cout << -1;
    else cout << minCost;
    
    
    return 0;
}