import repository.StockRepository;
import util.ApplicationContext;
import util.Menu;

import java.sql.SQLException;
import java.util.Scanner;

public class OnlineMarketplaceApplication {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ApplicationContext applicationContext = new ApplicationContext();

        Scanner stringInput = new Scanner(System.in);

        Menu menu = new Menu();

        while (true) {
            applicationContext.getSecurityContext().logout();
            menu.showWelcomeMenu();

            System.out.print("choose: ");

            String choice = stringInput.nextLine();

            if (choice.equals("1")) {

                applicationContext.getSecurityContext().setCurrentUser(applicationContext.getUserRepository().defineNewUser());

                if (applicationContext.getSecurityContext().getCurrentUser() != null)
                    applicationContext.getCartRepository().startShopping();

            } else if (choice.equals("2")) {

                applicationContext.getSecurityContext().setCurrentUser(applicationContext.getUserRepository().loginToUser());

                if (applicationContext.getSecurityContext().getCurrentUser() != null)
                    applicationContext.getCartRepository().startShopping();


            } else if (choice.equals("3"))
                break;
            else if (choice.equals("AccessStock")) {
                StockRepository stockRepository = new StockRepository(applicationContext.getDatabaseUtil().getConnection(), applicationContext.getProductRepository());
                stockRepository.printMenu();
            } else
                System.out.println("invalid entry");
        }

    }
}
