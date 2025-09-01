#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

int main() {
    int T; cin >> T;
    while(T--) {
        string s; cin >> s;
        string tmp = s;
        if(next_permutation(tmp.begin(), tmp.end())) {
            cout << tmp << "\n";
        } else {
            cout << s << "\n";
        }
    }
    return 0;
}