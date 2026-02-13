#include <iostream>
#include <vector>

using namespace std;

int main() {
    string s; cin >> s;
    int cnt1 = 0; int cnt0 = 0;
    for(int i=0; i<s.length(); i++) {
        if(s[i] == '1') cnt1++;
        else cnt0++;
    }

    int limit = cnt1/2;
    for(int i=0; i<s.length(); i++) {
        if(limit <= 0) break;
        if(s[i] == '1') {
            s[i] = '\0';
            limit--;
        }
    }

    limit = cnt0/2;
    for(int i=s.length()-1; i>=0; i--) {
        if(limit <= 0) break;
        if(s[i] == '0') {
            s[i] = '\0';
            limit--;
        }
    }

    string ans = "";
    for(int i=0; i<s.length(); i++) {
        if(s[i] != '\0') ans += s[i];
    }
    cout << ans;
    return 0;
}