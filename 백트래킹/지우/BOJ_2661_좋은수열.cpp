#include <iostream>
#include <vector>

using namespace std;

int N;

void dfs(string seq, int idx) {

    if(idx == N) {
        cout << seq;
        exit(0);
    }

    for(int i=1; i<=3; i++) {
        string tmp = seq + to_string(i);

        bool isGood = true;
        int len = tmp.length();
        
        for(int s=1; s<= len/2; s++) {
            if(tmp.substr(len-s, s) == tmp.substr(len-2*s, s)) isGood = false;
        }
        
        if(isGood) dfs(tmp, idx+1);
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    dfs("", 0);
    
    return 0;
}