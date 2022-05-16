import util.ApplicationContext;
import util.Menu;

import java.sql.SQLException;
import java.util.Scanner;

public class OnlineMarketplaceApplication {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ApplicationContext applicationContext = new ApplicationContext();

        Scanner intInput = new Scanner(System.in);

        Menu menu = new Menu();

        while (true) {
            applicationContext.getSecurityContext().logout();
            menu.showWelcomeMenu();

            System.out.print("choose: ");

            int choice = intInput.nextInt();

            if (choice == 1) {

                applicationContext.getSecurityContext().setCurrentUser(applicationContext.getUserRepository().defineNewUser());

                if (applicationContext.getSecurityContext().getCurrentUser() != null)
                    applicationContext.getCartRepository().startShopping();

            } else if (choice == 2) {

                applicationContext.getSecurityContext().setCurrentUser(applicationContext.getUserRepository().loginToUser());

                if (applicationContext.getSecurityContext().getCurrentUser() != null)
                    applicationContext.getCartRepository().startShopping();


            } else if (choice == 3)
                break;
            else
                System.out.println("invalid entry");
        }

    }
}
