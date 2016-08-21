package vmware;

import java.util.Scanner;

public class Knight {

    public static void main(String[] args) {
    	Knight knight = new Knight();
    	int res = knight.calcNumOfPaths();
    	System.out.println("result = " + res);
    }
    
	public int calcNumOfPaths() {
		
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        if (n <= 0) { scanner.close(); return 0; }
        if (n == 1) { scanner.close(); return 1; }
        
        int startPos = Integer.parseInt(scanner.nextLine());
        if ( (startPos < 0) || (startPos >= n) ) { scanner.close(); return 0; }
        
        int[][] state = new int[2][n];
        int i, j;

    	// ��ʼ������һ�в���Ԫ����0��x�������ǰλ���ǳ���λ�ã���״̬����1��������״̬����0
        String strLine = scanner.nextLine();
        for (j = 0; j < n; j++) {
        	if (j == startPos) state[1][j] = 1;
        	else state[1][j] = 0;
        }
        
        int curLine = 0;
        char ch;
        for (i = 1; i < n; i++) {
            strLine = scanner.nextLine();

    		// most left col
            ch = strLine.charAt(0);
    		if (ch == '0') state[curLine][0] = state[1 - curLine][0];
    		else if (ch == 'x') state[curLine][0] = state[1 - curLine][1];
    		
    		// inside col
    		for (j = 1; j < n - 1; j++) {
                ch = strLine.charAt(j);
    			if (ch == '0') state[curLine][j] = state[1 - curLine][j];
    			else if (ch == 'x') state[curLine][j] = state[1 - curLine][j - 1] + state[1 - curLine][j + 1];
    		}
    		
    		// most right col
            ch = strLine.charAt(n - 1);
    		if (ch == '0') state[curLine][n - 1] = state[1 - curLine][n - 1];
    		else if (ch == 'x') state[curLine][n - 1] = state[1 - curLine][n - 2];
    		
    		// --
    		curLine = 1 - curLine;
        }
        
        scanner.close();
        
    	// ��״̬��Ϊ���һ�е�״̬��֮��
        int res = 0;
    	for (i = 0; i < n; i++) res += state[1 - curLine][i];
    	
    	return res;
	}
	
}
