int main(){
    const int a[4][2] = {{1, 2}, {3, 4}, {}, 7};
    const int N = 3;
    int d[N + 1][2] = {1, 2, {3}, {5}, a[3][0], 8};
    return d[3][1];
}
