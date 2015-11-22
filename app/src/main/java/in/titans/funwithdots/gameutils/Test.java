package in.titans.funwithdots.gameutils;

public class Test {
	public static void main(String args[]) {
		GridFactory factory = new GridFactory();
		Grid grid = factory.getEasyGrid();
		//draw line (1,1) to (2,1)
		Point start = new Point(1,1);
		Point end = new Point(2,1);
		
		Point start1 = new Point(2,1);
		Point end1 = new Point(2,2);
		
		Point start2 = new Point(3,1);
		Point end2 = new Point(3,2);
		
		Point start3 = new Point(2,1);
		Point end3 = new Point(3,1);
		
		Point start4 = new Point(2,2);
		Point end4 = new Point(3,2);

		
		
		
		
		grid.showRecords();
	}
	
	static void  print(int s){
		System.out.println(s);
	}
	
	
	
	
	
}