#include <iostream>
#include <vector>

using namespace std;

int main() {
    int N; cin >> N;
    vector<pair<int,int>> arr(N);
    for(int i=0; i<N; i++) {
        int x, y; cin >> x >> y;
        arr[i] = {x,y};
    }

    vector<int> ranks(N,0);
    for(int i=0; i<N; i++) {
        auto[x,y] = arr[i];
        int rank = 1;
        
        for(int j=0; j<N; j++) {
            if(i == j ) continue;
            auto[a,b] = arr[j];
            if(x<a && y<b) rank++;
        }
        ranks[i] = rank;
    }

    for(int r : ranks) {
        cout << r << " ";
    }
    
    return 0;
}