#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N;
vector<string> picks;

void dfs(int i, int pre, int num) {
    if(i == N+1) {
        if(num == 0) {
            string tmp = "";
            for(auto p : picks) {
                tmp += p;
            }
            cout << tmp << "\n";
        }
        return;
    }

    // " "
    picks.push_back(" " + to_string(i));
    int nxtPre;
    if(pre > 0) nxtPre = pre*10 + i;
    else nxtPre = pre*10 - i;
    dfs(i+1, nxtPre, num-pre+nxtPre);
    picks.pop_back();
    
    // +
    picks.push_back("+" + to_string(i));
    dfs(i+1, i, num+i);
    picks.pop_back();
    
    // - 
    picks.push_back("-" + to_string(i));
    dfs(i+1, -1 * i, num-i);
    picks.pop_back();
        
}
int main() {
    int T; cin >> T;
    while(T--) {
        cin >> N;
        picks.clear();
        picks.push_back("1");
        dfs(2,1,1);
        cout << "\n";
    }

    return 0;
}