int f(int x, int y) {
    return x + y;
}

int main() {
    int a = 3;
    int b = 4;
    int c = 2;
    c = c + f(a, b);
    return c;
}
