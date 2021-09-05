package main.java.lexer;

public class Op extends Token {
    public final String operator;
    public Op(String oper) {
        super(Tag.OP);
        operator = oper;
        System.out.println(operator);
    }
}
