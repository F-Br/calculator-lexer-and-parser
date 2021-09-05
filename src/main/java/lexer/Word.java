package main.java.lexer;

public class Word extends Token {
    public final String lexeme;
    public Word(String s) {
        super(Tag.WORD);
        lexeme = new String(s);
    }
}