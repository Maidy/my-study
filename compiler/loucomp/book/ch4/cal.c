/* Simple integer arithmetic calculator
   according to the EBNF:

   <exp>    -> <term> { <addop> <term> }
   <addop>  -> + | -
   <term>   -> <factor> { <mulop> <factor> }
   <mulop>  -> *
   <factor> -> ( <exp> ) | number

   Inputs a line of text form stdin
   Outputs "error" or the result.
*/

#include <stdio.h>
#include <stdlib.h>

char token;

/* function prototype for recursive calls */
int expr(void);
int term(void);
int factor(void);

void error(void) {
  fprintf(stderr, "error");
  exit(1);
}

void match(char expected) {
  if (token == expected) {
    token = getchar();
  } else {
    error();
  }
}

int main(void) {
  int result;

  token = getchar();
  result = expr();

  if (token == '\n') {
    printf("Result = %d\n", result);
  } else {
    error();
  }
  return 0;
}

int expr(void) {

  int temp = term();

  while (token == '+' || token == '-') {

    switch (token) {
    case '+':
      match('+');
      temp += term();
      break;

    case '-':
      match('-');
      temp -= term();
      break;
    }

  }

  return temp;
}

int term() {
  int temp = factor();

  while (token == '*') {
    match('*');
    temp *= factor();
  }

  return temp;
}

int factor() {
  int temp;

  if (token == '(') {
    match('(');
    temp = expr();
    match(')');
  } else if (isdigit(token)) {
    ungetc(token, stdin);
    scanf("%d", &temp);
    token = getchar();
  } else {
    error();
  }

  return temp;
}
