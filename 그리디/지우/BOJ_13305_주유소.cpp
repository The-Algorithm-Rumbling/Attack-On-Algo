#include <iostream>
#include <vector>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int N; cin >> N;

    vector<long long> d(N-1, 0);
    vector<int> city(N, 0);

    for(int i=0; i<N-1; i++) {
        cin >> d[i];
    }
    for(int i=0; i<N; i++) {
        cin >> city[i];
    }

    long long answer = 0;
    int curr = 0;
    while(curr < N-1) {
        answer += city[curr]*d[curr];

        int nxt = curr+1;
        if(city[curr] < city[nxt]) {
            while(city[curr] < city[nxt] && nxt < N-1) {
                answer += city[curr]*d[nxt];
                nxt++;
            }
            curr = nxt;
        } else {
            curr++;
        }
    }
    cout << answer;
    
    return 0;
}