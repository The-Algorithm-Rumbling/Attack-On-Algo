#include <iostream>
#include <vector>

using namespace std;

int main() {
    int N, X; cin >> N >> X;

    vector<int> days(N, 0);
    vector<int> prefix(N, 0);
    for(int i=0; i<N; i++) {
        cin >> days[i];
        if(i>0) prefix[i] = prefix[i-1] + days[i];
        else prefix[i] = days[i];
    }

    int maxV = 0; int cnt = 0;
    for(int i=X-1; i<N; i++) {
        int vis = prefix[i];
        if(i-X >= 0) vis -= prefix[i-X];

        if(vis > maxV) {
            maxV = vis; cnt = 1;
        }
        else if(vis == maxV) {
            cnt++;
        }
    }

    if(maxV == 0) {
        cout << "SAD";
        return 0;
    }
    cout << maxV << "\n" << cnt;
    
    return 0;
}