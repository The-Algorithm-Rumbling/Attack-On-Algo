#include <iostream>
#include <vector>

using namespace std;

int main() {
    int N, newScore, P;
    cin >> N >> newScore >> P;

    vector<int> arr;
    for(int i=0; i<N; i++) {
        int a; cin >> a;
        arr.push_back(a);
    }

    if(N==0) {
        cout << 1; 
        return 0;
    }
    if(N==P && arr[N-1] >= newScore) {
        cout << -1;
        return 0;
    }
    
    int answer = 1;
    for(int i=0; i<N; i++) {
        if(arr[i] > newScore) answer++;
    }
    cout << answer;
    return 0;
}