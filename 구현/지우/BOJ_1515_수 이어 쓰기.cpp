#include <iostream>
#include <vector>

using namespace std;

int main() {
    string s; cin >> s;

    int idx = 0;
    int curr = 0;

    while(idx < s.length()) {
        curr++;
        string currS = to_string(curr);

        for(int i=0; i<currS.length(); i++) {
            if(currS[i] == s[idx]) {
                idx++;
            }

            if(idx == s.length()) {
                cout << curr;
                return 0;
            }
        }
    }    
    return 0;
}