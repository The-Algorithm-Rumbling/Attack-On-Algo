#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main() {

    int T; cin >> T;
    cin.ignore();
    
    while(T--) {
        int maxCnt = 0;
        vector<int> cnt(75,0);
        string s = ""; 
        getline(cin,s);

        for(int i=0; i<s.size(); i++) {
            if(s[i] == ' ') continue;
            cnt[s[i]-'0']++;
            maxCnt = max(maxCnt, cnt[s[i]-'0']);
        }

        int ansCnt = 0;
        char ans = '\0';
        for(int i=49; i<=74; i++) {
            if(cnt[i] == maxCnt) {
                ansCnt++;
                ans = i+'0';
            }
        }

        if(ansCnt == 1) cout << ans;
        else cout << "?";
        cout << "\n";
    }
}