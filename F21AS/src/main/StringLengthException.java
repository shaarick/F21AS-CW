package main;

public class StringLengthException extends Exception {
	public StringLengthException(int length) {
		super("String must be less than " + length + " characters.");
	}
}
