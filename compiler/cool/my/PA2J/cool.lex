/*
 *  The scanner definition for COOL.
 */

import java_cup.runtime.Symbol;

%%

%{

/*  Stuff enclosed in %{ %} is copied verbatim to the lexer class
 *  definition, all the extra variables/functions you want to use in the
 *  lexer actions should go here.  Don't remove or modify anything that
 *  was there initially.  */

    // Max size of string constants
    static int MAX_STR_CONST = 1025;

    // For assembling string constants
    StringBuffer string_buf = new StringBuffer();

    private int curr_lineno = 1;
    int get_curr_lineno() {
       return curr_lineno;
    }

    private AbstractSymbol filename;

    void set_filename(String fname) {
       filename = AbstractTable.stringtable.addString(fname);
    }

    AbstractSymbol curr_filename() {
       return filename;
    }
%}

%init{

/*  Stuff enclosed in %init{ %init} is copied verbatim to the lexer
 *  class constructor, all the extra initialization you want to do should
 *  go here.  Don't remove or modify anything that was there initially. */

    // empty for now
%init}

%eofval{

/*  Stuff enclosed in %eofval{ %eofval} specifies java code that is
 *  executed when end-of-file is reached.  If you use multiple lexical
 *  states and want to do something special if an EOF is encountered in
 *  one of those states, place your code in the switch statement.
 *  Ultimately, you should return the EOF symbol, or your lexer won't
 *  work.  */

    if (!eof_err_catched) {
      switch(yy_lexical_state) {
      case YYINITIAL:
        /* nothing special to do in the initial state */
        break;

      case COOL_STRING:
        eof_err_catched = true;
        return new Symbol(TokenConstants.ERROR, "EOF in string constant");

      case COOL_COMMENT:
        eof_err_catched = true;
        return new Symbol(TokenConstants.ERROR, "EOF in comment");
      }  
    }
    
    eof_err_catched = false;
    return new Symbol(TokenConstants.EOF);
%eofval}

%{
  private int comment_count = 0;
  private StringBuffer str = null;
  private boolean escaped = false;
  private boolean eof_err_catched = false;
%}

%class CoolLexer
%cup

%state COOL_COMMENT
%state COOL_STRING

WHITE_SPACE_CHAR = [\ \t\f\r\b\013]
LINE_COMMENT = --.*
STRING_CONST_TEXT = (\\\"|\\\\|[^\"])*

%%

<YYINITIAL> \n { curr_lineno++; }

<YYINITIAL> {WHITE_SPACE_CHAR}+ { }

<YYINITIAL> "(*" {
  yybegin(COOL_COMMENT);
  comment_count = comment_count + 1;
}
<COOL_COMMENT> "(*" {
  comment_count = comment_count + 1;
}
<COOL_COMMENT> "*)" {
  comment_count = comment_count - 1;
  if (comment_count == 0) {
    yybegin(YYINITIAL);
  }
}
<COOL_COMMENT> \n { curr_lineno++; }
<COOL_COMMENT> [^(*)]|\*|\(|\)  { }

<YYINITIAL> "*)" {
  return new Symbol(TokenConstants.ERROR, "Unmatched *)");
}

<YYINITIAL> {LINE_COMMENT} { }

<YYINITIAL> "{"  { return new Symbol(TokenConstants.LBRACE);   }
<YYINITIAL> "}"  { return new Symbol(TokenConstants.RBRACE);   }
<YYINITIAL> "("  { return new Symbol(TokenConstants.LPAREN);   }
<YYINITIAL> ")"  { return new Symbol(TokenConstants.RPAREN);   }
<YYINITIAL> "."  { return new Symbol(TokenConstants.DOT);      }
<YYINITIAL> ","  { return new Symbol(TokenConstants.COMMA);    }
<YYINITIAL> "@"  { return new Symbol(TokenConstants.AT);       }
<YYINITIAL> "*"  { return new Symbol(TokenConstants.MULT);     }
<YYINITIAL> "/"  { return new Symbol(TokenConstants.DIV);      }
<YYINITIAL> "+"  { return new Symbol(TokenConstants.PLUS);     }
<YYINITIAL> "-"  { return new Symbol(TokenConstants.MINUS);    }
<YYINITIAL> "<=" { return new Symbol(TokenConstants.LE);       }
<YYINITIAL> "<"  { return new Symbol(TokenConstants.LT);       }
<YYINITIAL> "="  { return new Symbol(TokenConstants.EQ);       }
<YYINITIAL> "=>" { return new Symbol(TokenConstants.DARROW);   }
<YYINITIAL> "~"  { return new Symbol(TokenConstants.NEG);      }
<YYINITIAL> "<-" { return new Symbol(TokenConstants.ASSIGN);   }
<YYINITIAL> ";"  { return new Symbol(TokenConstants.SEMI);     }
<YYINITIAL> ":"  { return new Symbol(TokenConstants.COLON);    }

<YYINITIAL> [Ii][Ss][Vv][Oo][Ii][Dd]
  { return new Symbol(TokenConstants.ISVOID);   }
<YYINITIAL> [Nn][Oo][Tt]
  { return new Symbol(TokenConstants.NOT);      }
<YYINITIAL> [Cc][Ll][Aa][Ss][Ss]
  { return new Symbol(TokenConstants.CLASS);    }
<YYINITIAL> [Ii][Nn][Hh][Ee][Rr][Ii][Tt][Ss]
  { return new Symbol(TokenConstants.INHERITS); }
<YYINITIAL> [Nn][Ee][Ww]
  { return new Symbol(TokenConstants.NEW);      }
<YYINITIAL> [Ii][Ff]
  { return new Symbol(TokenConstants.IF);       }
<YYINITIAL> [Tt][Hh][Ee][Nn]
  { return new Symbol(TokenConstants.THEN);     }
<YYINITIAL> [Ee][Ll][Ss][Ee]
  { return new Symbol(TokenConstants.ELSE);     }
<YYINITIAL> [Ff][Ii]
  { return new Symbol(TokenConstants.FI);       }
<YYINITIAL> [Ww][Hh][Ii][Ll][Ee]
  { return new Symbol(TokenConstants.WHILE);    }
<YYINITIAL> [Ll][Oo][Oo][Pp]
  { return new Symbol(TokenConstants.LOOP);     }
<YYINITIAL> [Pp][Oo][Oo][Ll]
  { return new Symbol(TokenConstants.POOL);     }
<YYINITIAL> [Ll][Ee][Tt]
  { return new Symbol(TokenConstants.LET);      }
<YYINITIAL> [Ii][Nn]
  { return new Symbol(TokenConstants.IN);       }
<YYINITIAL> [Cc][Aa][Ss][Ee]
  { return new Symbol(TokenConstants.CASE);     }
<YYINITIAL> [Oo][Ff]
  { return new Symbol(TokenConstants.OF);       }
<YYINITIAL> [Ee][Ss][Aa][Cc]
  { return new Symbol(TokenConstants.ESAC);     }
<YYINITIAL> (t[Rr][Uu][Ee])|(f[Aa][Ll][Ss][Ee]) {
    return new Symbol(TokenConstants.BOOL_CONST,
      new Boolean(yytext()));
}
<YYINITIAL> [0-9]+ {
    return new Symbol(TokenConstants.INT_CONST,
      new IntSymbol(yytext(), yytext().length(), 0));
}

<YYINITIAL> \" {
  str = new StringBuffer();
  escaped = false;
  yybegin(COOL_STRING);
}

<COOL_STRING> \" {

  if (escaped) {
    escaped = false;
    str.append('"');
  } else {

    String result = str.toString();
    yybegin(YYINITIAL);

    if (result.length() > 1024) {
      return new Symbol(TokenConstants.ERROR, "String constant too long");
    } else {
      return new Symbol(TokenConstants.STR_CONST,
        new StringSymbol(result, result.length(), 0));
    }
  }
}

<COOL_STRING> \0.*\" {
  yybegin(YYINITIAL);
  return new Symbol(TokenConstants.ERROR, "String contains escaped null character.");
}

<COOL_STRING> .|[\n\r] {

  char c = yytext().charAt(0);

  if (c == '\n') {
    curr_lineno++;
  }

  if (escaped) {
    
    switch (c) {
      case 'b':
        str.append("\b");
        break;
      case 't':
        str.append("\t");
        break;
      case 'n':
        str.append("\n");
        break;
      case 'f':
        str.append("\f");
        break;
      default:
        str.append(c);
    }

    escaped = false;

  } else {

    switch (c) {
      case '\\':
        escaped = true;
        break;
      case '\n':
        yybegin(YYINITIAL);
        return new Symbol(TokenConstants.ERROR, "Unterminated string constant");
      default:
        str.append(c);  
    }

  }
}

<YYINITIAL> [A-Z][A-Za-z0-9_]* {
    return new Symbol(TokenConstants.TYPEID,
              new IdSymbol(yytext(), yytext().length(), 0));
}

<YYINITIAL> [a-z][A-Za-z0-9_]* {
    return new Symbol(TokenConstants.OBJECTID,
              new IdSymbol(yytext(), yytext().length(), 0));
}

. {
    /* This rule should be the very last
       in your lexical specification and
       will match match everything not
       matched by other lexical rules. */
    // System.err.println("LEXER BUG - UNMATCHED: " + yytext());
    return new Symbol(TokenConstants.ERROR, yytext());
}
