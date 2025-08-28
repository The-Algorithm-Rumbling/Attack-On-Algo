#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

bool isAlphabet (char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
}

int main() {
    string s, k; cin >> s >> k;

    string tmpS = "";
    for(int i=0; i<s.size(); i++) {
        if(isAlphabet(s[i])) tmpS += s[i];
    }

    if(strstr(tmpS.c_str(), k.c_str()) != NULL) {
        cout << 1;
    } else {
        cout << 0;
    }
    return 0;
}