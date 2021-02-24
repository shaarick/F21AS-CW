package main;

public class IncorrectItemForMenuList extends Exception{
	public IncorrectItemForMenuList(String errorMessage) {
        super(errorMessage);
    }
}