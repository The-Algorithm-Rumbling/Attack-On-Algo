#include <iostream>
#include <string>
#include <set>

using namespace std;

int main() {
    int N; cin >> N;
    int cnt = 0;
    
    for(int i=0; i<N; i++) {
        string s; cin >> s;
        bool isS = true;
        set<char> sets;
        char pre = '\0';
        
        for(int i=0; i<s.length(); i++) {
            if(s[i] != pre && !sets.count(s[i])) {
                sets.insert(s[i]);
            }
            else if(s[i] != pre && sets.count(s[i])) {
                isS = false;
                break;
            }

            pre = s[i];
        }

        if(isS) cnt++;
    }
    cout << cnt;
    return 0;
}