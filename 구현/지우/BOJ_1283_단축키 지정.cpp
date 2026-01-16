#include <iostream>
#include <vector>
#include <unordered_set>
#include <cctype>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int N; cin >> N; cin.ignore();
    unordered_set<char> sets;

    while(N--) {
        string s; getline(cin, s); 
        int findIdx = -1;

        // 각 글자의 첫 단어 찾기
        char c = tolower(s[0]);
        if(sets.find(c) == sets.end()) {
            sets.insert(c);
            findIdx = 0;
        } else {
            for(int i=1; i<s.size()-1; i++) {
                c = tolower(s[i+1]);
                if(s[i] == ' ' && sets.find(c) == sets.end()) {
                    sets.insert(c);
                    findIdx = i+1;
                    break;
                }
            }
        }

        // 못 찾았으면 앞에서부터 안 나온거 찾기
        if(findIdx == -1) {
            for(int i=0; i<s.size(); i++) {
                if(s[i] == ' ') continue;
                c = tolower(s[i]);
                if(sets.find(c) == sets.end()) {
                    sets.insert(c);
                    findIdx = i;
                    break;
                }
            }
        }

        for(int i=0; i<s.size(); i++) {
            if(findIdx == i) {
                cout << "[" << s[i] << "]";
            } else {
                cout << s[i]; 
            }
        }
        cout << "\n";
    }
    
    return 0;
}