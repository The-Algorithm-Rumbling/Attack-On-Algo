#include <iostream>
#include <vector>

using namespace std;

int main() {
    string s, t;
    while(cin >> s >> t) {
        int i=0;
        for(char ch : t) {
            if(i<s.size() && ch == s[i]) i++;
        }
        if(i==s.size()) cout << "Yes" << "\n";
        else cout << "No" << "\n";
    }
    return 0;
}