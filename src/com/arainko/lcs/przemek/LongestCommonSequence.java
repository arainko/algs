
import java.util.ArrayList;

class LCSHelper {
	public char[][] b;
	public int[][] c;
	public LCSHelper(char[][] b, int[][] c) {
		this.b = b;
		this.c = c;
	}
	
	public void print() {
		for (int i = 1; i < b.length; i++){
			for (int j = 1; j < b[0].length; j++) {
				System.out.print(b[i][j]);
				System.out.print(c[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}

class index {
	public int i;
	public int j;
	public index (int i, int j) {
		this.i = i;
		this.j = j;
	}
}

public class LongestCommonSequence {
	
	public static LCSHelper length(String x, String y) {
		int m = x.length();
		int n = y.length();
		char[][] b = new char[m+1][n+1];
		int[][] c = new int[m+1][n+1];
		for (int i = 0; i < m; i++)
			c[i][0] = 0;
		for (int i = 1; i < n; i++)
			c[0][i] = 0;
		
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					c[i][j] = c[i-1][j-1] + 1;
					b[i][j] = '\\';
				} else {
					if (c[i-1][j] >= c[i][j-1]) {
						c[i][j] = c[i-1][j];
						b[i][j] = '|';
					} else {
						c[i][j] = c[i][j-1];
						b[i][j] = '-';
					}
				}
			}
		}
		return new LCSHelper(b, c);
	}
	
	public static void printLCS(String x, String y) {
		LCSHelper lcs = length(x, y);
		char[][] b = lcs.b;
		int[][] c = lcs.c;
		ArrayList<index> all = new ArrayList<index>();
		
		for (int i = b.length - 1; i > 0; i--) {
			for (int j = b[0].length - 1; j > 0; j--) {
				if (c[i][j] == c[b.length - 1][b[0].length - 1] && b[i][j] == '\\')
					all.add(new index(i, j));
			}
		}
		
		for (index a: all) {
			printLCSHelper(x, y, b, a.i, a.j);
			System.out.println();
		}
	}
	
	public static void printLCSHelper(String x, String y, char[][] b, int i, int j) {
		if (i == 0 || j == 0) return;
		if (b[i][j] ==  '\\') {
			printLCSHelper(x, y, b, i-1, j-1);
			System.out.print(x.charAt(i-1));
		} else {
			if (b[i][j] == '|')
				printLCSHelper(x, y, b, i-1, j);
			else
				printLCSHelper(x, y, b, i, j-1);
		}
	}

	public static void main(String[] args) {
		String seq1 = "AATCC";
		String seq2 = "ACACG";
		
		printLCS(seq1, seq2);
	}

}
