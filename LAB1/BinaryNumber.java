//*****************//
//NidhiChovatiya
//CWID - 10457344
//Assignment 1 - CS 570A
//****************//

package binarynumber;

public class BinaryNumber {

	private int data[];
	private boolean overflow;

	// Creating a binary number of given length which consists only of zeros.

	public BinaryNumber(int length) {
		if (length < 0) {
			throw new IllegalArgumentException("enter a valid number");
		}
		data = new int[length];
		for (int i = 0; i < length; i++) {
			data[i] = 0;
		}
	}

	// A constructor for creating a binary number of given a string.

	public BinaryNumber(String str) {
		int length = str.length();
		if(length <= 0) {
			System.out.println("enter a valid binary number");
		}
		else {
			data = new int[length];
			for (int i = 0; i < length; i++) {
				int s = Character.getNumericValue(str.charAt(i));
				if (s == 0 || s == 1) {
					data[i] = s;
				} else {
					System.out.println("enter a valid binary number");
				}
			}
			
		}
	}

	// Length of a binary number.

	public int getLength() {
		int l = data.length;
		return l;
	}

	// get digit by given the index value

	public int getDigit(int index) {
		if (index < data.length) {
			return data[index];
		} else {
			throw new IllegalArgumentException("Index is out of bound");
		}
	}

	// Right Shift Operation

	public void shiftR(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Enter a positive value");
		}

		BinaryNumber reallocate = new BinaryNumber(data.length + amount);
		for (int i = amount; i < reallocate.getLength(); i++) {
			reallocate.data[i] = data[i - amount];
		}
		this.data = reallocate.data;
		System.out.println("new number after shifting: " + this.toString());
	}

	// Addition of Two Binary Nos

	public void add(BinaryNumber aBinaryNumber) {
		if (aBinaryNumber.getLength() != data.length) {
			System.out.println("Lengths of both Numbers should be same");
		} else {
			System.out.print("Summation of " + aBinaryNumber + " and " + toString() + "= ");
			int carried_Digit = 0;
			int sum[] = new int[data.length];
			for (int i = 0; i < data.length; i++) {
				int tp1 = carried_Digit + data[i] + aBinaryNumber.getDigit(i);

//				tp1 is addition of carried digit and each digit of given values
				if (tp1 == 0) {
					sum[i] = 0;
					carried_Digit = 0;
				}

				if (tp1 == 1) {
					sum[i] = 1;
					carried_Digit = 0;
				}

				if (tp1 == 2) {
					sum[i] = 0;
					carried_Digit = 1;
				}

				if (tp1 == 3) {
					sum[i] = 1;
					carried_Digit = 1;
				}
			}

			data = sum;

			if (carried_Digit == 1) {
				overflow = true;
			}
			System.out.println(toString());
		}
	}

	// Conversion : Binary to String

	public String toString() {
		int i;
		if (overflow == true) {
			return "Overflow";
		} else {
			String binaryString = "";
			for (i = 0; i < data.length; i++) {
				binaryString = binaryString + data[i];
			}
			return binaryString;
		}
	}

	// Conversion : Binary to Decimal

	public int toDecimal() {
		int value = 0, i;
		for (i = 0; i < data.length; i++) {
			value = value * 2 + data[i];
		}
		return value;
	}

	public void clearOverﬂow() {
		overflow = false;
	}

	public static void main(String[] args) {
		BinaryNumber No1 = new BinaryNumber("10110");

		BinaryNumber No2 = new BinaryNumber("11101");

		BinaryNumber No3 = new BinaryNumber("11100");
		
		BinaryNumber value_0 = new BinaryNumber(5);

		System.out.println("value = " + value_0);

		System.out.println("Length of " + No2 + " is = " + No2.getLength());

		System.out.println("decimal value of " + No1 + " is = " + No1.toDecimal());

		System.out.println("Digit at given index is= " + No2.getDigit(3));
		
	//	System.out.println("Digit at given index is= " + No2.getDigit(6));

		No2.shiftR(3);

		No1.add(No3);
		
		No1.add(No2);

	}
}
