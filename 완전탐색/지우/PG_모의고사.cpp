#include <string>
#include <vector>

using namespace std;

int nums1[] = {1,2,3,4,5};
int nums2[] = {1,3,4,5};
int nums3[] = {3,1,2,4,5};

vector<int> solution(vector<int> answers) {
    vector<int> answer;
    int size = answers.size();
    vector<int> one(size,0); vector<int> two(size,0); vector<int> three(size,0);
    vector<int> scores(4,0);
    
    // 1.
    int d = 0;
    for(int i=0; i<size; i++) {
        one[i] = nums1[d];
        d = (d+1)%5;
    }
    
    // 2.
    for(int i=0; i<size; i+=2) {
        two[i] = 2;
    }
    d = 0;
    for(int i=1; i<size; i+=2) {
        two[i] = nums2[d];
        d = (d+1)%4;
    }
    
    // 3. 
    d = 0;
    for(int i=0; i<size; i+=2) {
        three[i] = nums3[d]; 
        if(i+1 < size) three[i+1] = nums3[d];
        d = (d+1)%5;
    }
    
    
    for(int i=0; i<size; i++) {
        if(one[i] == answers[i]) scores[1]++;
        if(two[i] == answers[i]) scores[2]++;
        if(three[i] == answers[i]) scores[3]++;
    }
    int maxScore = max(scores[1], max(scores[2], scores[3]));
    
    for(int i=1; i<=3; i++) {
        if(scores[i] == maxScore) answer.push_back(i);
    }
    
    return answer;
}