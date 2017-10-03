
public class Location {
	
	protected int x;
	
	protected int y;
	
	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getXCoordinate(){
		return this.x;
	}
	
	public int getYCoordinate(){
		return this.y;
	}
	
	public void printLocationDetails(){
		System.out.println("("+getXCoordinate()+","+getYCoordinate()+")");
	}
	
	//Distance of current location from another location measured through Manhattan distance
	public int manhattanDistance(Location target){
		int xDiff = Math.abs(getXCoordinate()-target.getXCoordinate());
		int yDiff = Math.abs(getYCoordinate()-target.getYCoordinate());
		return xDiff+yDiff;
	}

}
