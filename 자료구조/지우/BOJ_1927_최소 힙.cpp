#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct cmp {
    bool operator()(int a, int b) {
        return a > b;
    }
};

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    priority_queue<int, vector<int>, cmp> pq;

    int N; cin >> N;
    while(N--) {
        int num; cin >> num;
        if(num == 0) {
            if(pq.size() == 0) cout << 0 << "\n";
            else {
                cout << pq.top() << "\n";
                pq.pop();
            }
            
        } else {
            pq.push(num);
        }
    }
    return 0;
}