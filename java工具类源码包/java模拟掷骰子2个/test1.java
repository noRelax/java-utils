
//test1.java

import java.util.*;

public class test1 {
	public static void main(String[] args){
		Random rand = new Random();
		int sum2 = 0,sum3 = 0,sum4 = 0,sum5 = 0,sum6 = 0,sum7 = 0,sum8 = 0,sum9 = 0,sum10 = 0,sum11 = 0,sum12 = 0;
		int sum = 0;
		for (int i = 0; i < 10000; i++){
			int a = rand.nextInt(6) + 1;
			int b = rand.nextInt(6) + 1;
			System.out.print("(" + a + "," + b + ")");
			sum = a + b;
			if(sum == 2)
				sum2+=1;
			if(sum == 3)
				sum3+=1;
			if(sum == 4)
				sum4+=1;
			if(sum == 5)
				sum5+=1;
			if(sum == 6)
				sum6+=1;
			if(sum == 7)
				sum7+=1;
			if(sum == 8)
				sum8+=1;
			if(sum == 9)
				sum9+=1;
			if(sum == 10)
				sum10+=1;
			if(sum == 11)
				sum11+=1;	
			if(sum == 12)
				sum12+=1;
		}		
		System.out.println();
		System.out.println("��Ϊ2�Ĵ����� " + sum2 + ", ����Ϊ: " + ((double)sum2/10000));			
		System.out.println("��Ϊ3�Ĵ����� " + sum3+ ", ����Ϊ: " + ((double)sum3/10000));
		System.out.println("��Ϊ4�Ĵ����� " + sum4+ ", ����Ϊ: " + ((double)sum4/10000));
		System.out.println("��Ϊ5�Ĵ����� " + sum5+ ", ����Ϊ: " + ((double)sum5/10000));
		System.out.println("��Ϊ6�Ĵ����� " + sum6+ ", ����Ϊ: " + ((double)sum6/10000));					
		System.out.println("��Ϊ7�Ĵ����� " + sum7+ ", ����Ϊ: " + ((double)sum7/10000));
		System.out.println("��Ϊ8�Ĵ����� " + sum8+ ", ����Ϊ: " + ((double)sum8/10000));
		System.out.println("��Ϊ9�Ĵ����� " + sum9+ ", ����Ϊ: " + ((double)sum9/10000));
		System.out.println("��Ϊ10�Ĵ����� " + sum10+ ", ����Ϊ: " + ((double)sum10/10000));
		System.out.println("��Ϊ11�Ĵ����� " + sum11+ ", ����Ϊ: " + ((double)sum11/10000));
		System.out.println("��Ϊ12�Ĵ����� " + sum12+ ", ����Ϊ: " + ((double)sum12/10000));
		System.out.println("����ʱ�䣺 " + new Date());	
		
	}	
}