//--
//Name: Dakota Smith
//CST: 200
//Time: 9pm
//Date: 9/24/15
//Assignment: Lab3 (Projectile)
//--

import java.io.*;

public class Lab3 {

	public static final double G = 32.17;

	public static void main(String[] args) {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String line;
		String regex = "(0+(\\.(0+)?)?(\\s+)?)+";	//"""Read-only""" regex v2
		//If we were using Java 8 I'd do Arrays.stream(doubles).sum() == 0 to test for ending conditions

		try {
			while((line = stdin.readLine()) != null && !line.matches(regex)) {
				String[] tokens = line.split("(\\s)+");
				double[] doubles = new double[tokens.length];
				for(int i = 0; i < tokens.length; i++) {
					try {
						doubles[i] = Double.parseDouble(tokens[i]);
					}
					catch(NumberFormatException nfe) {
						System.err.printf("Couldn't parse to double: \"%s\", %dth input%n", tokens[i], i);
						nfe.printStackTrace();
					}
				}

				double vel = doubles[0];
				double angle = doubles[1];
				double dist = doubles[2];
				double size = doubles[3];
				double elev = doubles[4];
				double h = height(vel, time(dist, vel, radians(angle)), radians(angle));
				String s = val2string(h, size, elev);

				System.out.printf("The projectile's velocity is %s feet per second%n", fmt(vel));
				System.out.printf("The angle of elevation is %s degrees%n", fmt(angle));
				System.out.printf("The distance to the target is %s feet%n", fmt(dist));
				System.out.printf("The target's size is %s feet%n", fmt(size));
				System.out.printf("The target is located %s feet above the ground%n", fmt(elev));
				System.out.println(s);
			}
		}
		catch(IOException iox) {
			iox.printStackTrace();
		}
	}

	public static double time(double dist, double vel, double angle) {
		double divisor = vel * Math.cos(angle);
		if(divisor == 0) {
			return Double.NaN; //NaN will trickle through calculations, acting as a sentinel
		}
		return (dist) / (divisor);
	}

	public static double height(double vel, double time, double angle){
		return vel * time * Math.sin(angle) - (G * time * time)/2;
	}

	public static double radians(double angle){
		return angle * (Math.PI / 180.0);
	}

	public static String val2string(double projHeight, double tarSize, double tarHeight) {
		
		if(projHeight >= tarHeight && projHeight <= tarHeight+tarSize) {
			//The target is nont one-dimensional, so we have to take into account the height
			return "The target was hit by the projectile";
		}
		if(projHeight > tarHeight) {
			return String.format("The projectile was too high, height was: %s feet", fmt(projHeight));
		}
		if(projHeight < tarHeight && projHeight >= 0) {
			return String.format("The projectile was too low, height was: %s feet", fmt(projHeight));
		}
		if(projHeight < 0) {
			return "The computed distance was too short to reach the target";
		}
		if(Double.isNaN(projHeight)) {
			return "The computed distance cannot be calculated with the given data";
		}
		return String.format("This should not have happened; proj: %f, tar: %f", projHeight, tarHeight);
	}

	public static String fmt(double n) {
		return Double.isNaN(n) ? "" : String.format("%01.03f", n);
	}
}
