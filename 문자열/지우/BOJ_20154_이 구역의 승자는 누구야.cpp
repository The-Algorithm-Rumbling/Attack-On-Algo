#include <iostream>
#include <vector>

using namespace std;

int arr[] = {3,	2,	1,	2,	3,	3,	3,	3,	1,	1,	3,	1,	3,	3,	1,	2,	2,	2,	1,	2,	1,	1,	2,	2,	2,	1};

int main() {
    string s; cin >> s;

    int ans = 0;
    int pos = 0; int len = s.length();
    while(pos<len) {
        int a = 0; int b = 0;
        a = arr[s[pos] - 'A'];
        if(pos+1 < len) {
            b = arr[s[pos+1] - 'A'];
        }

        ans = (ans + a+b)%10;
        pos += 2;
    }

    if(ans%2 == 0) cout << "You're the winner?";
    else cout << "I'm a winner!";
    return 0;
}