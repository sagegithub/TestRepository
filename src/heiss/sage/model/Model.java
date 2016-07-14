package heiss.sage.model;

import java.math.BigDecimal;
import java.lang.Double;

public class Model {
	
	boolean bool1;
	Boolean bool2;

	public Model() {
		System.out.println("Create new Model in Sage Test Project 01");
	}
	
	private void compareTwoBigDecimals(){
		BigDecimal x = new BigDecimal("1");
	    BigDecimal y = new BigDecimal("1.00");
	    System.out.println(x.equals(y));
        System.out.println(x.compareTo(y) == 0 ? "true": "false");
        System.out.println(x == y);
        System.out.println("****************");
        x = new BigDecimal("1.0000000000000000000000000001");
	    y = new BigDecimal("1.00");
	    System.out.println(x.equals(y));
        System.out.println(x.compareTo(y) == 0 ? "true": "false");
        System.out.println(x == y);
        System.out.println("****************");
	}
	
	// remember - Java has: Double and double types.
	//		one is a class and the other a primitive data type
	private void compareTwoDoubles(){
		Double x = 1.0;
		Double y = 1.00;
	    System.out.println(x.equals(y));
        System.out.println(x.compareTo(y) == 0 ? "true": "false");
        System.out.println(x == y);
        System.out.println(Double.compare(x, y) == 0 ? "true": "false");
        System.out.println("****************");
        x = 1.0001;
	    y = 1.000001;
	    System.out.println(x.equals(y));
        System.out.println(x.compareTo(y) == 0 ? "true": "false");
        System.out.println(x == y);
        System.out.println(Double.compare(x, y) == 0 ? "true": "false");
        System.out.println("****************");
        x = 1.00;
	    y = 1.00000000000000000000000000000000000001;
        //x = new Double(1.00);
	    //y = new Double(1.00000000000000000000000000000000000001);
	    System.out.println(x.equals(y));
        System.out.println(x.compareTo(y) == 0 ? "true": "false");
        System.out.println(x == y);
        System.out.println(Double.compare(x, y) == 0 ? "true": "false");
        System.out.println("****************");
        double temp = (1.00000000000000000000000000000000000001 + 0.00000000000000000000000000000000000001);
        System.out.println("Double Addition: 1.00000000000000000000000000000000000001 + 0.00000000000000000000000000000000000001 = " + temp);
	}
	
	public static void main(String[] args){
		Model model = new Model();
		model.compareTwoBigDecimals();
		System.out.println("##################");
		model.compareTwoDoubles();
	}
	
}
