#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    for(int a=1; a<= yellow; a++) {
        int b = 0;
        if(yellow % a == 0) {
            b = yellow / a;
        }
        
        int tmp = (b+2)*2 + (a+2)*2 -4;
        if(brown == tmp) {
            answer.push_back(b+2); answer.push_back(a+2);
            break;
        }
    }
    return answer;
}