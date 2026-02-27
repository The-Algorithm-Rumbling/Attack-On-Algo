#include <iostream>
#include <vector>
#include <algorithm>
#include <tuple>

using namespace std;

int main() {
    int N, K;
    cin >> N >> K;

    vector<tuple<int,int,int,int>> medals(N);
    for(int i=0; i<N; i++) {
        int num, g, s, b;
        cin >> num >> g >> s >> b;

        medals[i] = {g, s, b, num};
    }
    sort(medals.rbegin(), medals.rend());

    vector<int> ranks(N+1, 0);
    int rank = 0;
    tuple<int,int,int> tmp = {-1,-1,-1};
    
    for(int i=0; i<N; i++) {
        auto[g,s,b,num] = medals[i];
        tuple<int,int,int> curr = {g,s,b};

        if(curr != tmp) {
            rank = i + 1;
            ranks[num] = rank;
        }
        else {
            ranks[num] = rank;
        }

        tmp = curr;
    }
    cout << ranks[K];
    
    return 0;
}