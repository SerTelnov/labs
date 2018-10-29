package parser.exception

import parser.Token

open class UnexpectedActionException(token: Token, notTerminalName: String) :
        ParserException("unexpected token: '$token' at notTerminal: '$notTerminalName'")