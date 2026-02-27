#include <iostream>
#include <vector>

using namespace std;

int INF = 98765432;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int T; cin >> T;
    vector<int> cnts;


    while(T--) {
        cnts.assign('z'-'a'+1, 0);
        string s; cin >> s;
        int K; cin >> K;
        if(K == 1) {
            cout << 1 << " " << 1 << "\n";
            continue;
        }

        for(int i=0; i<s.size(); i++) {
            cnts[s[i]-'a']++;
        }

        int minLen = INF;
        int maxLen = -1;
        for(int i=0; i<s.size(); i++) {
            if(cnts[s[i]-'a'] < K) continue;

            int cnt = 1; // i번째 수 일단 포함!
            for(int j=i+1; j<s.size(); j++) {
                if(s[i] == s[j]) cnt++;

                if(cnt == K) {
                    minLen = min(j-i+1, minLen);
                    maxLen = max(j-i+1, maxLen);
                    break;
                }
            }
        }

        if(minLen == INF || maxLen == -1) cout << -1 << "\n";
        else cout << minLen << " " << maxLen << "\n";
    }
    
    return 0;
}