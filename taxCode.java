import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainClass {

	static double appliedSalesTax = 0.10;
	static double appliedImportTax = 0.05;

	static double roundIt2pointAndNearest(double value) {
		return Math.round((Math.round(value * 100.0) / 100.0) * 20.0) / 20.0;
	}
	
	static double roundIt2point(double value) {
		return Math.round((value) * 100.0) / 100.0;
	}
	
	static int numberOfItems = 4;
	
	static void output(List<String> output2,double tax, double mainTotal) {
		for (String d : output2) {
			System.out.println(d);
		}
		tax = MainClass.roundIt2pointAndNearest(tax);
		mainTotal = MainClass.roundIt2point(mainTotal);
		System.out.println("Sales Tax : " + MainClass.formatter(tax));
		System.out.println("Total : " + mainTotal);
	}
	
	static String formatter(double value) {
		DecimalFormat decim = new DecimalFormat("0.00");
		return decim.format(value);
	}

	public static void main(String[] args) {
		double salestax = 0;
		double importTax = 0;
		double mainTotal = 0;
		double tax = 0;
		List<String> output = new ArrayList<String>();
		List<String> output2 = new ArrayList<String>();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < numberOfItems; i++) {
			String str = sc.nextLine();
			output.add(str);
		}
		for (String c : output) {
			String str = c;
			String[] arr = str.split(" ");
			int quantity = Integer.parseInt(arr[0]);
			float price = Float.parseFloat(arr[arr.length - 1]);
			if (str.contains("imported")) {
				importTax = MainClass.roundIt2pointAndNearest((price * quantity) * MainClass.appliedImportTax);
			}
			if (str.contains("food") || str.contains("chocolate") || str.contains("book")|| str.contains("pill")) {
			} else {
				salestax = MainClass.roundIt2pointAndNearest((price * quantity) * MainClass.appliedSalesTax);
			}
			tax = tax + (importTax + salestax);
			double total = (quantity * price) + (importTax + salestax);
			mainTotal = mainTotal + total;
			// formating output
			String outcome = Integer.parseInt(String.valueOf(quantity))
					+ str.substring(0 + arr[0].length(), str.length() - arr[arr.length - 1].length() - 3) + ": "
					+ MainClass.formatter(total);
			output2.add(outcome);
			total = 0;
			price = 0;
			quantity = 0;
			salestax = 0;
			importTax = 0;
		}
		MainClass.output(output2, tax, mainTotal);
	}

}
