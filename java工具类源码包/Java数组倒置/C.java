import java.io.*;

public class C
{
	public static final int N = 3;
	
	public static void main(String[] args)
	{
		int[][] a = {{1,2,3},{4,5,6},{7,8,9}};
		
		System.out.println("The old array is:");
		for (int i = 0; i < N; i++){
			for (int j = 0;j < N; j++)
			   System.out.print(a[i][j]+"\t");
			   
			   System.out.println();
		}
		
		for (int i = 0;i < N;i++){
			for (int j = 0; j < i; j++){
				int temp = a[i][j];
				    a[i][j] = a[j][i];
				    a[j][i] = temp;
			}
		}
		
		System.out.println("The new array is:");
		for (int i = 0; i < N; i++){
			for (int j = 0;j < N; j++)
			   System.out.print(a[i][j]+"\t");
			   
			   System.out.println();
		}
	}
}