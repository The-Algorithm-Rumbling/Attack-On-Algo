#include <iostream>
#include <vector>

using namespace std;

int N;
string s;
int ans = 98765432;

void right(char c) {
    int targetIdx = N-1;
    int result = 0;

    for(int i=N-1; i>=0; i--) {
        if(s[i] == c) {
            if(i != targetIdx) result++;
            targetIdx--;
        }
    }
    ans = min(ans, result);
}

void left(char c) {
    int targetIdx = 0;
    int result = 0;

    for(int i=0; i<N; i++) {
        if(s[i] == c) {
            if(i != targetIdx) result++;
            targetIdx++;
        }
    }
    ans = min(ans, result);
}

int main() {
    cin >> N >> s;

    left('R'); right('R');
    left('B'); right('B');
    cout << ans;
    return 0;
}