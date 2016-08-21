package bloomberg.calculator;

public class RemoveInvalidParenthesisSample {

	public static void main(String[] args) {
		
		String exp;
        RemoveInvalidParenthesisSample parser;
		
		/*
		exp = "(( aaaa + bb ) + (( ccccccccccccccc + d )))";
		exp = "(( a + b ) + (( c - d - e) ))";
		exp = "c+d+e";
		exp = "c-d-e";
		parser = new Parser(exp);
        System.out.println(parser.parse());
		//*/

		/*
		exp = "(( a + b ) * (( c + d )))";
		parser = new Parser(exp);
        System.out.println(parser.parse());
        //*/
		
		/*
		exp = "a + b + c+d-e+f";
		parser = new Parser(exp);
        System.out.println(parser.parse());
        //*/
		
        //*
        exp = "(a + (b)) * ( c-(a-b)-d)";
		parser = new RemoveInvalidParenthesisSample(exp);
        System.out.println(parser.parse());
        //*/
		
        //*
        exp = "(a + (b*(c *d))) * ( w*(y*z)/a)";
		parser = new RemoveInvalidParenthesisSample(exp);
        System.out.println(parser.parse());
        //*/
    }
	
	private final static char EOF = ';';
    private String mInput;
    private int mCurPos;

    public RemoveInvalidParenthesisSample(String input) {
        mInput = input + EOF; // mark the end
        mCurPos = -1;
    }

    public String parse() throws IllegalArgumentException {
        moveToNextToken();
        Result result = expression();
        if (curTokenHead() != EOF) {
            throw new IllegalArgumentException("Found unexpected character '" + curTokenHead() + "' at position " + mCurPos);
        }
        return result.getText();
    }

    // "expression()" handles "term" or "term + term" or "term - term"
    private Result expression() throws IllegalArgumentException {
        Result leftArg = term();

        char operator = curTokenHead();
        if (operator != '+' && operator != '-') {
            return leftArg; // EXIT
        }
        
        StringBuffer sbExp = new StringBuffer(leftArg.getText());
        char rightOp, nextTokenHead = 0;
        do {
        	if (nextTokenHead != 0) operator = nextTokenHead;
        	
	        moveToNextToken();
	        Result rightArg = term();
	
	        rightOp = rightArg.getOp();
	        if (operator == '-' && (rightOp == '-' || rightOp == '+')) {
	            rightArg = encloseInParentheses(rightArg);
	        }

	        sbExp.append(" " + operator + " " + rightArg.getText());
        
            nextTokenHead = preFetchNextTokenHead();
        }
        while ( (nextTokenHead == '+') || (nextTokenHead == '-') );
        
        return new Result(sbExp.toString(), operator);
    }

    // "term()" handles "factor" or "factor * factor" or "factor / factor"
    private Result term() throws IllegalArgumentException {
        Result leftArg = factor();

        char operator = curTokenHead();
        if (operator != '*' && operator != '/') {
            return leftArg; // EXIT
        }
        
        char leftOp = leftArg.getOp();
        if (leftOp == '+' || leftOp == '-') {
            leftArg = encloseInParentheses(leftArg);
        }
        
        StringBuffer sbExp = new StringBuffer(leftArg.getText());
        char rightOp, nextTokenHead = 0;
        do {
        	if (nextTokenHead != 0) operator = nextTokenHead;
        	
	        moveToNextToken();
	        Result rightArg = term();
	
	        rightOp = rightArg.getOp();
	        if (rightOp == '+' || rightOp == '-' || (operator == '/' && (rightOp == '/' || rightOp == '*'))) {
	            rightArg = encloseInParentheses(rightArg);
	        }

	        sbExp.append(" " + operator + " " + rightArg.getText());
        
            nextTokenHead = preFetchNextTokenHead();
        }
        while ( (nextTokenHead == '*') || (nextTokenHead == '/') );
        
        return new Result(sbExp.toString(), operator);
        
        /*
        nextToken();
        Result rightArg = factor();

        char leftOp = leftArg.getOp();
        if (leftOp == '+' || leftOp == '-') {
            leftArg = encloseInParentheses(leftArg);
        }
        char rightOp = rightArg.getOp();
        if (rightOp == '+' || rightOp == '-' || (operator == '/' && (rightOp == '/' || rightOp == '*'))) {
            rightArg = encloseInParentheses(rightArg);
        }

        return new Result(leftArg.getText() + " " + operator + " " + rightArg.getText(), operator);
        //*/
    }

    // "factor()" handles a "paren" or a "variable"
    private Result factor() throws IllegalArgumentException {
        Result result;
        if (curTokenHead() == '(') {
            result = paren();
        }
        else if (Character.isLetter(curTokenHead())) {
            result = variable();
        }
        else {
            throw new IllegalArgumentException("Expected variable or '(', found '" + curTokenHead() + "' at position " + mCurPos);
        }
        
        return result;
    }

    // "paren()" handles an "expression" enclosed in parentheses
    // Called with currToken an opening parenthesis
    private Result paren() throws IllegalArgumentException {
        moveToNextToken();
        Result result = expression();
        if (curTokenHead() != ')') {
            throw new IllegalArgumentException("Expected ')', found '" + curTokenHead() + "' at position " + mCurPos);
        }
        moveToNextToken();
        
        return result;
    }

    // "variable()" handles a variable
    // Called with curToken a variable
    private Result variable() throws IllegalArgumentException {
        Result result = new Result(getCurOperand(), ' ');
        moveToNextToken();
        
        return result;
    }

    private char curTokenHead() {
        return mInput.charAt(mCurPos);
    }

    private String getCurOperand() {
    	int curPos = mCurPos;
    	char ch;
    	do {
    		ch = mInput.charAt(curPos);
    		curPos++;
    	}
    	while ( (ch != ' ') && (ch != '+') && (ch != '-') && (ch != '*') && (ch != '/') && (ch != '(') && (ch != ')') && (ch != EOF) );
    	
    	curPos--;
        String token = mInput.substring(mCurPos, curPos);
        mCurPos = curPos - 1;	// move to the last position of current token
        return token;
    }

    private void moveToNextToken() {
        if (mCurPos >= mInput.length() - 1) {
            throw new IllegalArgumentException("Unexpected end of input");
        }
        
        char curHead;
        do {
            ++mCurPos;
            curHead = curTokenHead();
        }
        while (curHead != EOF && curHead == ' ');
    }

    private char preFetchNextTokenHead() {

    	char nextTokenHead = 0;
    	
        if (mCurPos >= mInput.length() - 1) {
            return nextTokenHead;
        }
        
        int pos = mCurPos;
        char head;
        do {
            head = mInput.charAt(pos);
            pos++;
        }
        while (head != EOF && head == ' ');
        
        return head;
    }
    
    private static Result encloseInParentheses(Result result) {
        return new Result("(" + result.getText() + ")", result.getOp());
    }

    private static class Result {
        private final String mText;
        private final char mOp;

        private Result(String text, char op) {
            this.mText = text;
            this.mOp = op;
        }

        public String getText() {
            return mText;
        }

        public char getOp() {
            return mOp;
        }
    }
}
