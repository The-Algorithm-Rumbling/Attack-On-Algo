#include <iostream>
#include <vector>

using namespace std;

int ConsOrVow(char c) {
    if(c == 'a' || c == 'e' ||c == 'i' || c == 'o' || c == 'u' ) {
        return 1;
    } else {
        return 0;
    }
}

bool Evaluate(string s) {
    int len = s.length();

    // 모음 하나 필수
    int cntCons = 0;
    for(int i=0; i<len; i++) {
        if(ConsOrVow(s[i]) == 1) cntCons++;
    }
    if(cntCons < 1) return false;

    // 모음 3 연속 or 자음 3 연속 ㄴㄴ
    if(len >= 3) {
        for(int i=0; i<=len-3; i++) {
            if(ConsOrVow(s[i]) == ConsOrVow(s[i+1]) && ConsOrVow(s[i+1]) == ConsOrVow(s[i+2])) return false;
        }
    }

    // 같은 글자 연속 xx (ee, oo 제외)
    if(len >= 2) {
        for(int i=0; i<=len-2; i++) {
            if(s[i] == s[i+1] && s[i] != 'e' && s[i] != 'o') return false;
        }
    }

    return true;
}

int main() {
    while(1) {
        string s; cin >> s;
        if(s == "end") break;

        if(Evaluate(s)) {
            cout << "<" << s << "> is acceptable.";
        } else {
            cout << "<" << s << "> is not acceptable.";
        }

        cout << "\n";
    }
    return 0;
}