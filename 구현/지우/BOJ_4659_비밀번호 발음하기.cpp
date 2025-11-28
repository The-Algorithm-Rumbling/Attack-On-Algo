#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

unordered_set<char> vowels;
unordered_set<char> cons;

bool Solve(string s) {
    
    int vCnt = 0;
    for(int i=0; i<s.size(); i++) {
        if(vowels.find(s[i]) != vowels.end()) vCnt++;

        if(i>=2) {
            if(vowels.find(s[i-2]) != vowels.end() && vowels.find(s[i-1]) != vowels.end() && vowels.find(s[i]) != vowels.end()) {
                return false;
            }

            if(vowels.find(s[i-2]) == vowels.end() && vowels.find(s[i-1]) == vowels.end() && vowels.find(s[i]) == vowels.end()) {
                return false;
            }
        }

        if(i>=1 && s[i] != 'e' && s[i] != 'o' && s[i-1] == s[i]) {
            return false;
        }
    }

    if(vCnt == 0) return false;
    return true;
}

int main() {
    vowels.insert('a'); vowels.insert('e'); vowels.insert('i'); vowels.insert('o'); vowels.insert('u');
    
    string s; cin >> s;
    while(s != "end") {
        cout << "<" << s << "> is ";
        if(Solve(s)) {
            cout << "acceptable.\n";
        } else {
            cout << "not acceptable.\n";
        }
        cin >> s;
    }
    
    return 0;
}