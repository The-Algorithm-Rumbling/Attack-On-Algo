#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

int main() {
    int N; char K; cin >> N >> K;

    int standard = 0;
    if(K == 'Y') standard = 1;
    else if(K == 'F') standard = 2;
    else if(K == 'O') standard = 3;

    unordered_set<string> sets;

    int answer = 0;
    int curr = 0;
    for(int i=0; i<N; i++) {
        string s; cin >> s;

        if(curr < standard && sets.find(s) == sets.end()) {
            curr++;
            sets.insert(s);
        }

        if(curr == standard) {
            answer++;
            curr = 0;
        }
    }

    cout << answer;
    
    return 0;
}