package in.titans.funwithdots.gameutils;

public class Point {
	public static final int gridLength =  GridFactory.getCurrentGridLength();
	private int x;
	private int y;
	private int startBoxNum;
	private int endBoxNum;
	public Point(int y,int x) {
		this.x= x;
		this.y= y;
		startBoxNum = gridLength* x + y;
		endBoxNum = gridLength * (x-1) + (y-1);
		
	}
		
	public int getStartBoxNum() {
		return startBoxNum;
	}

	public int getEndBoxNum() {
		return endBoxNum;
	}
	
	@Override
	public int hashCode() {
		return new String(x+"_"+y).hashCode();
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}

