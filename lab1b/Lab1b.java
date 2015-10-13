//--
//Name: Dakota Smith
//CST: 200
//Time: 2:02
//Date: 9/13/15
//Assignment: 1b
//--

import java.text.NumberFormat;
import java.io.*;

public class Lab1b {
	public static void main(String[] args) {


		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String in;
		double loa, lwl, beam, disp, sa;

		try {
			while((in = stdin.readLine()) != null && !in.equals("q") && !in.equals("Q")){
				double[] fields = parseLine(in);
				loa = fields[0]; lwl = fields[1]; beam = fields[2]; disp = fields[3]; sa = fields[4];
				System.out.printf("LOA: %s%n", value(loa));
				System.out.printf("LWL: %s%n", value(lwl));
				System.out.printf("Beam: %s%n", value(beam));
				System.out.printf("Displacement: %s%n", value(disp));
				System.out.printf("Sail area: %s%n", value(sa));
				System.out.println();
				System.out.printf("Hull speed: %s%n", value(hullSpeed(lwl)));
				System.out.printf("D/L: %s%n", value(dl(disp, lwl)));
				System.out.printf("SA/D: %s%n", value(sad(sa, disp)));
				System.out.printf("Capsize index: %s%n", value(capI(beam, disp)));
				System.out.printf("Comfort index: %s%n", value(comfI(disp, lwl, loa, beam)));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	public static double hullSpeed(double lwl) {
		return 1.34 * Math.pow(lwl, 0.5);
	}

	public static double dl(double disp, double lwl) {
		if(lwl == 0) {
			return -100;	//Sentinel. Would rather golang's (value, error);
		}
		return (disp/2240)/(Math.pow(0.01 * lwl, 3));
	}

	public static double sad(double sa, double disp) {
		if(disp == 0) {
			return -100;
		}
		return (sa)/(Math.pow((disp/64), 0.67));
	}

	public static double capI(double beam, double disp) {
		if(disp == 0) {
			return -100;
		}
		return (beam)/(Math.pow((disp/64), 0.33));
	}

	public static double comfI(double disp, double lwl, double loa, double beam) {
		double denom = (.65 * (0.7 * lwl + 0.3 * loa) * Math.pow(beam, 1.33));
		if(denom == 0){
			return -100;
		}

		return (disp)/denom;
	}

	public static String value(double v) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setGroupingUsed(false);
		if(v < 0) {
			return "Cannot be performed";
		} else {
			return String.format("%.02f", v);
		}
	}
	public static double[] parseLine(String s){
		String[] toks = s.split("\\s+");	
		double[] fields = new double[toks.length];
		for(int i = 0; i < toks.length; i++){
			try {
				fields[i] = Double.parseDouble(toks[i]);
			}
			catch(Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		return fields;
	}

}
