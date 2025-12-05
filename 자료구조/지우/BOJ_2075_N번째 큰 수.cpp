#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    priority_queue<int, vector<int>, greater<int>> pq;

    int N; cin >> N;
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            int num; cin >> num;

            if(pq.size() < N) pq.push(num);
            else {
                if(pq.top() < num) {
                    pq.pop();
                    pq.push(num);
                }
            }
        }
    }

    cout << pq.top();
    return 0;
}