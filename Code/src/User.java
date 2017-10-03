
public class User {
	
	//Location of the user
	protected Location location;
	
	public void setLocation(int x, int y){
		this.location = new Location(x, y);
	}
	
	public Location getLocation(){
		return this.location;
	}
}
