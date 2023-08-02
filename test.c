int f(int x, int y) {
    return x + y;
}

int main() {
    int a = 1;
    int b = 2;
    int c = 3;
    int d;
    d = c + f(a, b);
    return d;
}