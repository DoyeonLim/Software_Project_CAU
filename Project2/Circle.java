public class Circle {

	double radius;
	double center_x;
	double center_y;
	
	final double PI = 3.14;
	
	Circle() {
		
	}
	
	Circle() {
		this(1.0, 0.0, 0.0);
	}
	
	Circle(double r) {
		this(r, 0.0, 0.0);
	}
	
	Circle(double x, double y) {
		this(1.0, x, y);
	}
	
	Circle(double r, double x, double y) {
		this.radius = r;
		this.center_x = x;
		this.center_y = y;		
	}
	
	public void print() {		   
		System.out.println("radius = " + radius + ",  x of center = " + center_x + ",  y of center = " + center_y );		
	}
	
	public double area() {
		return PI*radius*radius;
	}
		
	
	public double compare(Circle c) {
		return this.area() - c.area();		
	}

		
	public void resize(double ratio) {   
		radius *= ratio;
	}
	
	boolean equal(Circle c) {		   
		if ( (this.radius == c.radius) && (this.center_x == c.center_x)  && (this.center_y == c.center_y) )			  
			return true;		   
		else			  
			return false;		
	}

	boolean equalSize(Circle c) {		   
		if ( this.radius == c.radius )			  
			return true;		   
		else			  
			return false;		
	}
	
}
