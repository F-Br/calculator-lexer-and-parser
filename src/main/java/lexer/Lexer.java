package main.java.lexer;

import java.io.*;
import java.util.*;
//import Math.pow;

public class Lexer {
    public int line = 1;
    private char peek = ' ';
    private Hashtable words = new Hashtable();
    private static final Set<Character> operatorSet = new HashSet<Character>(Arrays.asList('+', '-', '*', '!'));

    void reserve(Word t) {
        words.put(t.lexeme, t);
    }

    public Lexer() { // Add reserved lexemes here
        //reserve(new Word(Tag.WORD, "replace variable word here with new one")); // UNCOMMENT AND ADD FOR NEW RESERVED WORDS
    }

    // scanning from lexer
    public Token scan() throws IOException {
        for (; ; peek = (char)System.in.read()) {
            if (peek == ' '  || peek == '\t') { continue;} // whitespace
            else if (peek == '\n') { line += 1;} // end line
            else break;
        }


        // reading mechanism (to get full lexeme)

        if (Character.isDigit(peek)) { // handle if the input is a float NOTE: THIS DOESN'T STORE WITH NEGATIVES
            float v = 0;
            float u = 0;

            do {
                v = 10 * v + Character.digit(peek, 10);
                peek = (char)System.in.read();
            }
            while (Character.isDigit(peek));

            if (peek == '.') { // check if decimal point
                peek = (char)System.in.read();
                int i = 1;
                while (Character.isDigit(peek)) { // if so add after point
                    u += (float) Character.digit(peek, 10) / (Math.pow(10, i));
                    peek = (char)System.in.read();
                    i++;
                }

            }

            return new Num(v + u);
        }

        if (Character.isLetter(peek)) { // handle if the input is a word
            StringBuffer b = new StringBuffer();
            do {
                b.append(peek);
                peek = (char)System.in.read();
            } while (Character.isLetter(peek));
            String s = b.toString();

            // check if string operator
            if (s.equals("cos")) {
                Op cosine = new Op(s);
                return cosine;
            }

            // check if variable word
            /* // UNCOMMENT ALL TO ALLOW INCLUSION OF USER VARIABLES
            Word w = (Word)words.get(s);
            if (w != null) {return w;} // if lexeme is already in hashtable use get result
            w = new Word(s);
            words.put(s, w); // add to hashtable if not in
            return w;
            */
        }

        // handle non string operators
        if (operatorSet.contains(peek)) {
            Op operator;
            switch (peek) {
                case '+':
                    operator = new Op(Character.toString(peek));
                    break;
                case '-':
                    operator = new Op(Character.toString(peek));
                    break;
                case '*':
                    operator = new Op(Character.toString(peek));
                    break;
                case '!':
                    operator = new Op(Character.toString(peek));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + peek); // if getting this error, may not have implemented case for operator in switch
            }
            peek = (char)System.in.read();
            return operator;
        }

        Token t = new Token(peek);
        peek = ' ';
        return t;


    }
}