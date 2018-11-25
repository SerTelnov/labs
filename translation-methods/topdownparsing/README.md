Top-down parser
--
Parser for logical expression in ```C```

Bash command:
---
    cd topdownparsing
    parser.sh

Grammar:
---
    E -> E|O  | O
    O -> O^X  | X
    X -> X&A  | A
    A -> A<<S | A>>S | S
    S -> a    | (E)  | !E

LL1 Grammar:
---
    E  -> OE'
    E' -> |OE' | ε
    O  -> XO'
    O' -> ^XO' | ε
    X  -> AX'
    X' -> &AX' | ε
    A  -> SA'
    A' -> >>SA' | <<SA' | ε  
    S  -> a | (E) | !E

Not Terminals
---
    E, E' - expression
    O, O' - or
    X, X' - xor
    A, A' - and
    S     - shift

Terminals
---
    a, &, |, ^, !, (, ), <<, >>, ε

FIRST and FOLLOW
===
|A          |FIRST(A)   |FOLLOW(A)              |
|:---------:|:---------:|:---------------------:|
|E          |a, !, (    |$, )                   |
|E'         |or, ɛ      |$, )                   |
|O          |a, !, (    |$, ), or               |
|O'         |^, ɛ       |$, ), or               |
|X          |a, !, (    |$, ), or, ^            |
|X'         |&, ɛ       |$, ), or, ^            |
|A          |a, !, (    |$, ), or, ^, &         |
|A'         |<<, >>, ɛ  |$, ), or, ^, &         |
|S          |a, !, (    |$, ), or, ^, &, <<, >> |
