#include <iostream>
#include <vector>

using namespace std;

int k;
string maxAns = "";
string minAns = "99999999999";
vector<bool> vis;
vector<char> signs;

void dfs(int signIdx, int pIdx, string num, int currNum) {
    if(pIdx == k) {
        maxAns = max(maxAns, num); 
        minAns = min(minAns, num);
        return;
    }

    char currSign = signs[signIdx];

    for(int i=0; i<=9; i++) {
        if(!vis[i]) {
            if(currSign == '>' && currNum > i) {
                vis[i] = true;
                dfs(signIdx+1, pIdx+1, num + to_string(i) , i);
                vis[i] = false;
            }
            if(currSign == '<' && currNum < i) {
                vis[i] = true;
                dfs(signIdx+1, pIdx+1, num + to_string(i) , i);
                vis[i] = false;
            }
        }
    }
}

int main() {
    cin >> k;
    vis.resize(10, false);
    signs.resize(k+1, '\0');

    for(int i=0; i<k; i++) {
        cin >> signs[i];
    }

    for(int i=0; i<=9; i++) {
        vis[i] = true;
        dfs(0, 0, to_string(i), i);
        vis[i] = false;
    }

    cout << maxAns << "\n" << minAns;
    return 0;
}