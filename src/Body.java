
public class Body {
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}
	
	public Body(Body b) {
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName;
	}
	
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
	public double getXVel() {
		return myXVel;
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	
	public String getName() {
		return myFileName;
	}
	
	public double calcDistance(Body b) {
		double radiusSquared = Math.pow(this.getX() - b.getX(),2) + Math.pow(this.getY() - b.getY(),2);
		double radius = Math.sqrt(radiusSquared);
		return radius;
	}
	
	public double calcForceExertedBy(Body p) {
		double force = 6.67e-11 * ( (this.getMass() * p.getMass())/ Math.pow(this.calcDistance(p), 2) );
		return force;
	}
	
	public double calcForceExertedByX(Body p) {
		double forceX = this.calcForceExertedBy(p) * ( (p.getX() - this.getX()) / this.calcDistance(p) );
		return forceX;
	}
	
	public double calcForceExertedByY(Body p) {
		double forceY = this.calcForceExertedBy(p) * ( (p.getY() - this.getY()) / this.calcDistance(p) );
		return forceY;
	}
	
	public double calcNetForceExertedByX(Body[] bodies) {
		double sum = 0;
		for (Body b : bodies) {
			if (! b.equals(this)) {
				sum += this.calcForceExertedByX(b);
			}
		}
	return sum; 
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		double sum = 0;
		for (Body b : bodies) {
			if (! b.equals(this)) {
				sum += this.calcForceExertedByY(b);
			}
		}
	return sum; 
	}
	
	public void update(double deltaT, double xForce, double yForce) {
		double accX = xForce / this.getMass();
		double accY = yForce / this.getMass();
		
		double nvx = myXVel + deltaT * accX;
		double nvy = myYVel + deltaT * accY;
		
		double nx = myXPos + deltaT * nvx;
		double ny = myYPos + deltaT * nvy;
		
		myXVel = nvx;
		myYVel = nvy;
		myXPos = nx;
		myYPos = ny;
	}
	
	public void draw() {
		StdDraw.picture(myXPos,  myYPos, "images/"+myFileName);
	}
}
