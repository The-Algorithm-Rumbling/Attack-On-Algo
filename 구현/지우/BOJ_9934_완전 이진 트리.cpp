#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

vector<int> arr;
vector<vector<int>> trees;

void dfs(int depth, int start, int end) {
    
    if(start > end) return;
    int nodeIdx = (start+end)/2;
    
    trees[depth].push_back(arr[nodeIdx]);

    dfs(depth+1, start, nodeIdx-1);
    dfs(depth+1, nodeIdx+1, end);
}

int main() {
    int K; cin >> K;
    int arr_size = pow(2,K) - 1;
    
    arr.resize(arr_size, 0); trees.resize(K, vector<int>(0));
    int node = 0;
    for(int i=0; i<arr_size; i++) {
        cin >> arr[i];
    }

    dfs(0, 0, arr_size-1);
    for(int d=0; d<K; d++) {
        for(auto node : trees[d]) {
            cout << node << " ";
        }
        cout << "\n";
    }
    return 0;
}