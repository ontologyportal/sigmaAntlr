pacakge com.articulate.sigma.parsing;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import com.articulate.sigma.*;

public class SuokifVisitor extends AbstractParseTreeVisitor<String> {

    /** ***************************************************************
     */
    public Formula visitFile(SuokifParser.FileContext context) {

         System.out.println("Visiting file: " + context);
         System.out.println("# children: " + context.children.size());
         System.out.println("text: " + context.getText());
         for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$SentenceContext"))
                 visitSentence((SuokifParser.SentenceContext) c);
             if (c.getClass().getName().equals("SuokifParser$CommentContext")) 
                 visitComment((SuokifParser.CommentContext) c);
         }
         return null;
    }
    
    /** ***************************************************************
     */
    public Formula visitSentence(SuokifParser.SentenceContext context) {
       
        System.out.println("Visiting sentence: " + context);
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
         for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$RelsentContext"))
                 visitRelsent((SuokifParser.RelsentContext) c);
             if (c.getClass().getName().equals("SuokifParser$LogsentContext")) 
                 visitLogsent((SuokifParser.LogsentContext) c);
             if (c.getClass().getName().equals("SuokifParser$QuantsentContext")) 
                 visitQuantsent((SuokifParser.QuantsentContext) c);
             if (c.getClass().getName().equals("SuokifParser$VariableContext")) 
                 visitVariable((SuokifParser.VariableContext) c);
         }
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitComment(SuokifParser.CommentContext context) {
       
        System.out.println("Visiting comment: " + context);
        System.out.println(context.COMMENT().getText() + "\n");
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitRelsent(SuokifParser.RelsentContext context) {
       
        System.out.println("Visiting relsent: " + context);
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
        if (context.IDENTIFIER() != null)
            System.out.println("identifier: " + context.IDENTIFIER());
         for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$ArgumentContext")) 
                 visitArgument((SuokifParser.ArgumentContext) c);
         }
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitArgument(SuokifParser.ArgumentContext context) {
       
        System.out.println("Visiting argument: " + context);
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
         for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$SentenceContext")) 
                 visitSentence((SuokifParser.SentenceContext) c);
             if (c.getClass().getName().equals("SuokifParser$TermContext")) 
                 visitTerm((SuokifParser.TermContext) c);
         }
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitLogsent(SuokifParser.LogsentContext context) {
       
        System.out.println("Visiting logsent: " + context);
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
         for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$NotsentContext")) 
                 visitNotsent((SuokifParser.NotsentContext) c);
             if (c.getClass().getName().equals("SuokifParser$AndsentContext")) 
                 visitAndsent((SuokifParser.AndsentContext) c);
             if (c.getClass().getName().equals("SuokifParser$OrsentContext")) 
                 visitOrsent((SuokifParser.OrsentContext) c);
             if (c.getClass().getName().equals("SuokifParser$ImpliesContext")) 
                 visitImplies((SuokifParser.ImpliesContext) c);
             if (c.getClass().getName().equals("SuokifParser$IffContext")) 
                 visitIff((SuokifParser.IffContext) c);
             if (c.getClass().getName().equals("SuokifParser$EqsentContext")) 
                 visitEqsent((SuokifParser.EqsentContext) c);
         }
         return null;
    }


    /** ***************************************************************
     */
    public Formula visitNotsent(SuokifParser.NotsentContext context) {
       
        System.out.println("Visiting Notsent: " + context);
        System.out.println("# children: " + context.children.size());
        if (context.children.size() != 1)
            System.out.println("error wrong number of arguments in Notsent: ");
        System.out.println("text: " + context.getText());
        for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$SentenceContext")) 
                 visitSentence((SuokifParser.SentenceContext) c);
        }
        return null;
    }

    /** ***************************************************************
     */
    public Formula visitAndsent(SuokifParser.AndsentContext context) {
       
        System.out.println("Visiting Andsent: " + context);
        System.out.println("# children: " + context.children.size());
        if (context.children.size() < 2)
            System.out.println("error wrong number of arguments in Andsent: ");
        System.out.println("text: " + context.getText());
        for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$SentenceContext")) 
                 visitSentence((SuokifParser.SentenceContext) c);
        }
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitOrsent(SuokifParser.OrsentContext context) {
       
        System.out.println("Visiting Orsent: " + context);
        System.out.println("# children: " + context.children.size());
        if (context.children.size() < 2)
            System.out.println("error wrong number of arguments in Orsent: ");
        System.out.println("text: " + context.getText());
        for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$SentenceContext")) 
                 visitSentence((SuokifParser.SentenceContext) c);
        }
         return null;
    }


    /** ***************************************************************
     */
    public Formula visitImplies(SuokifParser.ImpliesContext context) {
       
        System.out.println("Visiting Implies: " + context);
        System.out.println("# children: " + context.children.size());
        if (context.children.size() != 5)
            System.out.println("error wrong number of arguments in Implies: ");
        System.out.println("text: " + context.getText());
        for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$SentenceContext")) 
                 visitSentence((SuokifParser.SentenceContext) c);
        }
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitIff(SuokifParser.IffContext context) {
       
        System.out.println("Visiting Iff: " + context);
        System.out.println("# children: " + context.children.size());
        if (context.children.size() != 5)
            System.out.println("error wrong number of arguments in Iff: ");
        System.out.println("text: " + context.getText());
        for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$SentenceContext")) 
                 visitSentence((SuokifParser.SentenceContext) c);
        }
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitEqsent(SuokifParser.EqsentContext context) {
       
        System.out.println("Visiting Eqsent: " + context);
        System.out.println("# children: " + context.children.size());
        if (context.children.size() != 2)
            System.out.println("error wrong number of arguments in Eqsent: ");
        System.out.println("text: " + context.getText());
        for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$TermContext")) 
                 visitTerm((SuokifParser.TermContext) c);
        }
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitQuantsent(SuokifParser.QuantsentContext context) {
       
        System.out.println("Visiting quantsent: " + context);
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
         for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$ForallContext"))
                 visitForall((SuokifParser.ForallContext) c);
             if (c.getClass().getName().equals("SuokifParser$ExistsContext")) 
                 visitExists((SuokifParser.ExistsContext) c);
         }
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitForall(SuokifParser.ForallContext context) {
       
        System.out.println("Visiting Forall: " + context);
        System.out.println("# children: " + context.children.size());
        if (context.children.size() != 2)
            System.out.println("error wrong number of arguments in Forall: ");
        System.out.println("text: " + context.getText());
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitExists(SuokifParser.ExistsContext context) {
       
        System.out.println("Visiting Exists: " + context);
        System.out.println("# children: " + context.children.size());
        if (context.children.size() != 2)
            System.out.println("error wrong number of arguments in Exists: ");
        System.out.println("text: " + context.getText());
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitVariable(SuokifParser.VariableContext context) {
       
        System.out.println("Visiting variable: " + context);
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitTerm(SuokifParser.TermContext context) {
       
        System.out.println("Visiting Term: " + context);
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
        if (context.IDENTIFIER() != null)
            System.out.println("identifier: " + context.IDENTIFIER());
        if (context.FUNWORD() != null)
            System.out.println("funword: " + context.FUNWORD());
         for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$FuntermContext"))
                 visitFunterm((SuokifParser.FuntermContext) c);
             if (c.getClass().getName().equals("SuokifParser$VariableContext")) 
                 visitVariable((SuokifParser.VariableContext) c);
             if (c.getClass().getName().equals("SuokifParser$StringContext")) 
                 visitString((SuokifParser.StringContext) c);
             if (c.getClass().getName().equals("SuokifParser$NumberContext")) 
                 visitNumber((SuokifParser.NumberContext) c);
         }
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitFunterm(SuokifParser.FuntermContext context) {
       
        System.out.println("Visiting funterm: " + context);
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
        if (context.FUNWORD() != null)
            System.out.println("funword: " + context.FUNWORD());
         for (ParseTree c : context.children) {
             System.out.println("child: " + c.getClass().getName());
             if (c.getClass().getName().equals("SuokifParser$ArgumentContext")) 
                 visitArgument((SuokifParser.ArgumentContext) c);
         }
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitString(SuokifParser.StringContext context) {
       
        System.out.println("Visiting string: " + context);
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
         return null;
    }

    /** ***************************************************************
     */
    public Formula visitNumber(SuokifParser.NumberContext context) {
       
        System.out.println("Visiting number: " + context);
        System.out.println("# children: " + context.children.size());
        System.out.println("text: " + context.getText());
         return null;
    }
/*

exists : '(' 'exists' '(' variable+ ')' sentence ')' ;
forall : '(' 'forall' '(' variable+ ')' sentence ')' ;

logsent :  (notsent | andsent | orsent | implies | iff | eqsent) ;
iff : '(' '<=>' sentence sentence ')' ;
implies :  '(' '=>' sentence sentence ')' ;
andsent : '(' 'and' sentence sentence+ ')' ;
orsent : '(' 'or' sentence sentence+ ')' ;
notsent : '(' 'not' sentence ')' ;
eqsent : '(' 'equal' term term ')' ;

funterm : '(' FUNWORD argument+ ')' ;
FUNWORD : LETTER WORDCHAR* 'Fn';

relsent : ('(' IDENTIFIER argument+ ')') | ('(' variable argument+ ')')  ;

term : (funterm | variable | string | number | FUNWORD | IDENTIFIER ) ;
IDENTIFIER : LETTER (LETTER | DIGIT)* ;

NUMBER : '-'? DIGIT+ ([.,] DIGIT+)? EXPONENT? ;
number : NUMBER ;

WORDCHAR : (LETTER | DIGIT | '-' | '_') ;

STRING : '"' ~('"')+ '"' ;
string: STRING ;


REGVAR : '?' [a-zA-Z0-9]+ ;
ROWVAR : '@' [a-zA-Z0-9]+ ;
variable : (REGVAR | ROWVAR) ;

EXPONENT : 'e' '-'? DIGIT+ ;
LETTER  : [A-Za-z] ;
DIGIT : [0-9] ;
WHITESPACE : [ \n\t\r]+ -> skip ;

*/

}
