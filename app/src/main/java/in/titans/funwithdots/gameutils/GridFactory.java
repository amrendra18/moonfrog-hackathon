package in.titans.funwithdots.gameutils;

public class GridFactory {
	public static int currentGridLength;
	public static final int easyGridLength=6;
	public static final int medGridLength=10;
	public static final int bigGridLength=20;
	public Grid getEasyGrid() {
		currentGridLength = easyGridLength;
		return new Grid(easyGridLength);		
	}

	public Grid medGrid(){
		currentGridLength = medGridLength;
		return new Grid(medGridLength);
	}

	public Grid bigGrid(){
		currentGridLength = bigGridLength;
		return new Grid(bigGridLength);
	}
	
	public static int getCurrentGridLength() {
		return currentGridLength;
	}
}
