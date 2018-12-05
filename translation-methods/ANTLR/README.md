ANTLR
--

Obfuscator lite ``Java`` code

### Example of Obfuscator work:

#### Java Code
```java
class Example {

    public void f(int a, int b, int c) {
        if (a + b > c) {
            for (int i = 0; i != 10; i++) {
                while (a != b) {
                    c += i * a + b;
                    a += b;
                    String hello = "hello";
                }
            }
        }
    }

    public static void main(String[] args) {
        int a = 10 + 10 + 10;
    }
}
```

#### Obfuscator
```java
class I010010001 {
public void I010000001(int I010000000, int I001111111, int I001111110) {
if (I010000000 + I001111111 >I001111110) {
for (int I010000010 = 0;
I010000010 !=10; I010000010++) {
while (I010000000 !=I001111111) {
int I010000110 = I010000010;
int I010000111 = I010000000;
int I010001000 = I010000110 * I010000111;
int I010000011 = I010001000;
int I010000100 = I001111111;
int I010000101 = I010000011 + I010000100;
I001111110 = I001111110 + I010000101;
I010000000 = I010000000 + I001111111;
String I010001001 = "hello";
}
}
}
}
public static void main(String[] I010001010) {
int I010001011 = 10;
int I010001110 = 10;
int I010001111 = 10;
int I010010000 = I010001110 + I010001111;
int I010001100 = I010010000;
int I010001101 = I010001011 + I010001100;
int I010000000 = I010001101;
}
}
```