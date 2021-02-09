
public class City {
	private double x;		//x_coordinate
	private double y;		//y_coordinate
	private String name;
	
	public City(String name, double x, double y) {
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	public City(String name) {
		this.name = name;
	}
	
	//getter for the x coordinate
	public double getX() {
		return this.x;
	}
	
	//seter for the x coordinate
	public void setX(double x) {
		this.x = x;
	}
	
	//getter for the y coordinate
	public double getY() {
		return this.y;
	}
	
	//setter for the y coordinate
	public void setY(double y) {
		this.y = y;
	}
	
	//returns the name of the city
	public String getName() {
		return this.name;
	}
	
	//returns the distance between this and neighbour 
	public double getDistance(City neighbour) {
		double xSquared = (this.x - neighbour.x) * (this.x - neighbour.x);
		double ySquared = (this.y - neighbour.y) * (this.y - neighbour.y);
		return Math.sqrt((xSquared + ySquared));
	}
	
	public void print() {
		System.out.println("[Name = " + this.name + "] | " + "[X = " + this.x + "] | " + "[Y = " + this.y + "]");
	}
	
}
