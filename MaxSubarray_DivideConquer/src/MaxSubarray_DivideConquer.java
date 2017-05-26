import java.util.Scanner;


public class MaxSubarray_DivideConquer{
	

	
	public static Result divMaxSubArray(int A[],int start, int end)
	{
		if(start == end)
			return new Result(start,end,A[start]);
		else
		{
			int middle = (start + end)/2;
			Result leftRes = divMaxSubArray(A, start,middle);
			Result rightRes = divMaxSubArray(A, middle+1, end);
			Result crossRes = maxOverCrossing(A, start, middle, end);
			
			if ((leftRes.sum >= rightRes.sum) && (leftRes.sum >= crossRes.sum))
				return leftRes;
			else if((rightRes.sum >= leftRes.sum) && (rightRes.sum >= crossRes.sum))
				return rightRes;
			else
				return crossRes;
		}
		
	}

	
	public static Result maxOverCrossing(int A[], int start, int middle, int end)
	{
		int currLeftSum =  0;
		int leftSum = Integer.MIN_VALUE;
		int maxLeft = 0;
		
		for(int i=middle; i>=start; i--)
		{
			currLeftSum += A[i];
			if(currLeftSum > leftSum)
			{
				leftSum = currLeftSum;
				maxLeft = i;
			}
		}
		
		int currRightSum = 0;
		int rightSum = Integer.MIN_VALUE;
		int maxRight = -1; 
				
		for(int i=middle+1; i<=end; i++ )
		{
			currRightSum += A[i];
			if(currRightSum > rightSum)
			{
				rightSum = currRightSum;
				maxRight = i;
			}
		}
		
		return new Result(maxLeft, maxRight, leftSum + rightSum);
	}
	
	public static void main(String args[])
	{
		final long startTime = System.nanoTime();
		//int[] a = {-2,1,-3,4,-1,2,1,-5,4};
		//int[] a = {-2,-3,4,-1,-2,1,5,-3};
		//int[] a = {-3,-4,-1};
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the size of the Original input subarray");
		int sizeOfArray = Integer.parseInt(scan.nextLine());
		int[] SubArray = new int[sizeOfArray];
		System.out.print("Enter the number: ");
		for (int i = 0; i < SubArray.length; i++) {
			
			SubArray[i] = scan.nextInt();
			
		}
		scan.close();
		System.out.println();
		System.out.println("Divide and Conquer");
		Result rDiv = divMaxSubArray(SubArray,0,SubArray.length-1);
		System.out.print("{");
		if (rDiv.right >= 0) 
		{
			for(int i=rDiv.left; i<=rDiv.right; i++)
			{
				System.out.print(SubArray[i]);
				if(i!=rDiv.right)System.out.print(",");
			}
		}
		System.out.print("}");
		System.out.println("=> Sum " + rDiv.sum);
		
		System.out.println();
		final long duration = System.nanoTime() - startTime;
		System.out.println(duration);
	
		
	}
}

class Result{
	int left;
	int right;
	int sum;
	Result(int l, int r, int s)
	{
		this.left = l;
		this.right = r;
		this.sum  = s;
	}
}

