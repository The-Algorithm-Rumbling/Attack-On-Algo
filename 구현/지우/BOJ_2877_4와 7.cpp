#include <iostream>
#include <deque>
#include <algorithm>

using namespace std;

int main() {
    int K; cin >> K;

    int idx = K+1;
    deque<int> dq;
    while(idx > 1) {
        
        if(idx % 2 == 0) {
            dq.push_front(4);
        } else {
            dq.push_front(7);
        }
        idx = idx/2;
    }

    for(int d : dq) {
        cout << d;
    }
    return 0;
}