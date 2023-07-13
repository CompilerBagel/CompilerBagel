const int c = 3;
int g = 1;
int main() {
    int a = c + 2;
    int b = a - g;
    a = a - b;
    b = b * a;
    int e = 100 / b;
    return e;
}