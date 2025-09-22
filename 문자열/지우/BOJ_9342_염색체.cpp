#include <iostream>
#include <vector>
#include <set>

using namespace std;
string s;
set<char> sets;
char arr[] = {'A', 'B', 'C', 'D', 'E', 'F'};

bool check() { 
    int i=0; int check = 0;
    while(i<(int)s.size()) {
        if(check==0) {
            if(sets.count(s[i])) {
                check++;
                if(s[i] != 'A') i++;
            } else {
                return false;
            }
        }

        else if(check == 1) {
            if(s[i] == 'A') {
                i++;
                while(i<s.size() && s[i] == 'A') {
                    i++;
                }
                check++;
            } else {
                return false;
            }
        }

        else if(check == 2) {
            if(s[i] == 'F') {
                i++;
                while(i<s.size() && s[i] == 'F') {
                    i++;
                }
                check++;
            } else {
                return false;
            }
        }

        else if(check == 3) {
            if(s[i] == 'C') {
                i++;
                while(i<s.size() && s[i] == 'C') {
                    i++;
                }
                check++;
            } else {
                return false;
            }
        }

        else if(check == 4) {
            if(sets.count(s[i])) {
                check++;
                i++;
            } else {
                return false;
            }
        }

        else if(check == 5) {
            return false;
        }
    }
    return true;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    for(int i=0; i<6; i++) {
        sets.insert('A'+i);
    }
    
    int T; cin >> T;
    while(T--) {
        cin >> s;
        if(check()) cout << "Infected!" << "\n";
        else cout << "Good" << "\n";
    }
    return 0;
}