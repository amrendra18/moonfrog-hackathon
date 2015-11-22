package in.titans.funwithdots.gameutils;

public class Record {
	private int lineCount=0;
	private Box box=null;
	private Point stPoint=null;
	private Point endPoint=null;
	Record(int boxNumber,Point stPoint,Point endPoint){
		this.lineCount=0;
		this.box= new Box(boxNumber);
		this.stPoint = stPoint;
		this.endPoint = endPoint;
	}

    public Point getStPoint() {
        return stPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void lineInc() {
        lineCount++;
    }

    public  void lineDec() {
        lineCount--;
    }

	public Box getBox() {
		return box;
	}

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    @Override
	public String toString() {
		return "lineCount: "+lineCount+" boxNumber : "+box.boxNumber +" "+ box.toString();
	}
	
	class Box {
		int boxNumber=0;
		boolean up=false;
		boolean down=false;
		boolean left=false;
		boolean right=false;
		
		Box(int boxNumber) {
			this.boxNumber = boxNumber;
			
		}
		public void markUp(boolean val) {
			up=val;
		}
		
		public void markDown(boolean val){
			down=val;
		}
		
		public void markLeft(boolean val) {
			left=val;
		}
		
		public void markRight(boolean val) {
			right=val;
		}

		public boolean hasUp() {
			return up;
		}

		public boolean hasDown(){
			return down;
		}

		public boolean hasLeft() {
			return left;
		}

		public boolean hasRight() {
			return right;
		}
		
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(" up: "+up);
			builder.append(" right: "+right);
			builder.append(" down: "+down);
			builder.append(" left: "+left);
			return builder.toString();
		}
		
	}

	
}
