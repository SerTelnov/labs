package parser

/*
Grammar:
    E  -> OE'
    E' -> orOE'  | epsilon
    O  -> XO'
    O' -> xorXO' | epsilon
    X  -> AX'
    X' -> andAX' | epsilon
    A  -> a | (E) | !E

not terminals:
    E - expression
    O - or
    X - xor
    A - and

terminals:
'variable'([a-z])
'&' (and)
'|' (or)
'^' (xor)
'!' (negate)
'('
')'

 */

enum class Token {
    VARIABLE,
    AND,
    OR,
    XOR,
    NEGATE,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    END
}