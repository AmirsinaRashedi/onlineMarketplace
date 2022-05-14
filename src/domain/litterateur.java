package domain;

import java.util.Scanner;

public class litterateur extends Product {
    private String litterateurtitle;
    private String litterateurType;
    private String litterateurAuthor;

    public litterateur(int productId) {
        super(productId, 3);
        Scanner stringInput = new Scanner(System.in);
        System.out.print("enter the title: ");
        litterateurtitle = stringInput.nextLine();
        System.out.print("what kind of litterateur is it: ");
        litterateurType = stringInput.nextLine();
        System.out.print("enter name of the author: ");
        litterateurAuthor = stringInput.nextLine();
    }
}
