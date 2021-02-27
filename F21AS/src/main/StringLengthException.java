package main1;

public class StringLengthException extends Exception {
	public StringLengthException(String field, int length) {
		super(field + " cannot be greater than " + length + " characters.");
	}
}
