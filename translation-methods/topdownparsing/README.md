#Top-down parser
Top-down parser for logical expression in ```C```

bash command:
---
    cd topdownparsing
    script.sh

Start grammer:
---
    E  -> OE'
    E' -> |E | ε
    O  -> XO'
    O' -> ^O | ε
    X  -> AX'
    X' -> &X | ε
    A -> value | (E) | !E

LL1 grammer:
---
    E  -> OE'
    E' -> |OE'  | epsilon
    O  -> XO'
    O' -> xorXO' | epsilon
    X  -> AX'
    X' -> andAX' | epsilon
    A  -> a | (E) | !E

not terminals
---
    E - expression
    O - or
    X - xor
    A - and

terminals
---
    a, &, |, ^, !, (, ), ε

FIRST & FOLLOW
===
|A          |FIRST(A)   |FOLLOW(A)        |
|:---------:|:---------:|:---------------:|
|E          |a, !, (    |$, )             |
|E'         |or, ɛ      |$, )             |
|O          |a, !, (    |$, ), or         |
|O'         |^, ɛ       |$, ), or         |
|X          |a, !, (    |$, ), or, ^      |
|X'         |&, ɛ       |$, ), or, ^      |
|A          |a, !, (    |$, ), or, ^, &   |