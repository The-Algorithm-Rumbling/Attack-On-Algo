#include <iostream>
#include <vector>
#include <deque>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    string s; cin >> s;
    
    deque<char> L; deque<char> R;
    for(int i=0; i<s.size(); i++) {
        L.push_back(s[i]);
    }
    
    int M; cin >> M;
    while(M--) {
        char cmd; cin >> cmd;
        if(cmd == 'L') {
            if(L.empty()) continue; 
            R.push_back(L.back());
            L.pop_back();
        }
        else if(cmd == 'D') {
            if(R.empty()) continue;
            L.push_back(R.front());
            R.pop_front();
        }
        else if(cmd == 'B') {
            if(L.empty()) continue; 
            L.pop_back();
        }
        else {
            char x; cin >> x;
            L.push_back(x);
        }
    }

    for(char i:L) cout << i;
    for(char i:R) cout << i;
    return 0;
}