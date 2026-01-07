#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

string S, T;
queue<string> q;

int bfs() {
    q.push(T);
    
    while(!q.empty()) {
        string curr = q.front(); q.pop();
        if(curr == S) return 1;
        if(curr.size() < S.size()) return 0;

        string nxt = curr;
        if(curr.back() == 'A') {
            nxt.pop_back(); 
            q.push(nxt);
        }

        nxt = curr;
        if(curr.front() == 'B') {
            reverse(nxt.begin(), nxt.end());
            nxt.pop_back();
            q.push(nxt);
        }
    }

    return 0;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> S >> T;
    cout << bfs();
    return 0;
}