#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int N; int M; cin >> N >> M;

    vector<int> A(N); 
    for(int i=0; i<N; i++) {
        cin >> A[i];
    }
    vector<int> B(M);
    for(int i=0; i<M; i++) {
        cin >> B[i];
    }

    int p1 = 0; int p2 = 0;
    while(p1<N && p2<M) {
        if(A[p1] <= B[p2]) {
            cout << A[p1] << " ";
            p1++;
        } else {
            cout << B[p2] << " ";
            p2++;
        }
    }

    while(p1<N) {
        cout << A[p1] << " ";
        p1++;
    }
    while(p2<M) {
        cout << B[p2] << " ";
        p2++;
    }
    return 0;
}