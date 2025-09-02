#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> sizes) {
    int answer = 0;
    int maxW = -1; int maxH = -1;
    for(int i=0; i<sizes.size(); i++) {
        sort(sizes[i].begin(), sizes[i].end());
        maxW = max(maxW, sizes[i][0]);
        maxH = max(maxH, sizes[i][1]);
    }
    answer = maxW * maxH;
    return answer;
}