#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int T; cin >> T;
    while(T--) {
        string s; cin >> s;
        int K; cin >> K;

        if(K == 1) {
            cout << 1 <<" " << 1 <<"\n";
            continue;
        }
        
        vector<vector<int>> alph_pos(26);
        for(int i=0; i<s.size(); i++) {
            alph_pos[s[i]-'a'].push_back(i);
        }

        int minL = 98765432;
        int maxL = -1;

        for(int i=0; i<26; i++) {
            if(alph_pos[i].size() < K) continue;

            for(int j=0; j<= alph_pos[i].size()-K; j++) {
                int curr = alph_pos[i][j+K-1] - alph_pos[i][j]+1;
                minL = min(minL, curr);
                maxL = max(maxL, curr);
            }
        }

        if(maxL == -1) {
            cout << -1 <<"\n";
        } else {
            cout << minL << " " << maxL <<"\n";
        }

    }
    return 0;
}