#include <iostream>
#include <string>
#include <vector>
#include <set>

using namespace std;
set<string> sets;
char vows[] = {'A', 'E', 'I', 'O', 'U'};

void dfs(string str) {
    if(str != "") sets.insert(str);
    if(str.length() >= 5) return;
    
    for(int i=0; i<5; i++) {
        dfs(str+vows[i]);
    }
}

int solution(string word) {
    int answer = 0;
    dfs("");
    for(auto str : sets) {
        answer++;
        if(str == word) break;
    }
    return answer;
}