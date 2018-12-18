Parser-generator
--

Parser generator for ``LL1`` grammar.

[Generator](src/main/java/ru/telnov/labs/translationmethods/parsergenerator/generator/Generator.java)

``First and follow`` [generators](src/main/java/ru/telnov/labs/translationmethods/parsergenerator/utils/FirstAndFollow.java)

#### Example of grammars
Grammar file
```text
start returns (String s)
    : HELLO { s = "Hello, World!"; } COMMA WORLD MARK
    | WORLD { s = "World"; }
    | name<"Hello"> { s = nameNode.getValue(); }
    | value<"Hello", "your value: "> { s = valueNode.getValue(); }
    ;

name(String input) returns (String s)
    : NAME { s = input + ", <ANY_NAME>"; }
    ;

value(String s1, String s2) returns (String s)
    : DIGITS { s = s1 + ", " + s2 + "'<ANY_VALUE>'"; }
    ;
```
Tokens
```text
HELLO:  'Hello';
WORLD:  'World';

COMMA:  '\\,';
MARK:   '\\!';

NAME:   [A-Z][a-z]+;
DIGITS: [1-9][0-9]*;
``` 

Other test [examples](src/test/resources/grammars)