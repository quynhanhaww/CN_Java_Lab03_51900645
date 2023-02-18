import java.util.List;
import java.util.Scanner;

public class Program {
    private final static PhoneDAO phoneDAO = new PhoneDAO();
    private final static ManufactureDAO manufactureDAO = new ManufactureDAO();

    public static void main(String[] args) {
        initMainMenu();
    }

    private static void initMainMenu() {
        List<Manufacture> manufactureList = manufactureDAO.getAll();
        Scanner scanner = new Scanner(System.in);
        System.out.println("MOBILE PHONE FACTORY");
        System.out.println("************************* MENU *************************");
        System.out.println("** 1. Manufacture manager                             **");
        System.out.println("** 2. Mobile phone manager                            **");
        System.out.println("** 0. Exit program.                                   **");
        System.out.println("********************************************************");
        System.out.print("Your option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option)  {
            case 1:
                initManufactureMenu();
                return;
            case 2:
                if (manufactureList.size()==0) {
                    System.out.println("Add at least one manufacture first.");
                    initMainMenu();
                    return;
                }
                initPhoneMenu();
                return;
            default:
                return;
        }
    }

    private static void initManufactureMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("MANUFACTURE MANAGER");
        System.out.println("************************* MENU *************************");
        System.out.println("** 1. Add new manufacture                             **");
        System.out.println("** 2. Find manufacture by ID                          **");
        System.out.println("** 3. Show all manufactures                           **");
        System.out.println("** 4. Remove manufacture by ID                        **");
        System.out.println("** 5. Remove manufacture by information               **");
        System.out.println("** 6. Update manufacture by ID                        **");
        System.out.println("** 0. Exit program.                                   **");
        System.out.println("********************************************************");
        System.out.print("Your option: ");
        int option = scanner.nextInt();
        int id;
        switch (option) {
            case 1:
                if (manufactureDAO.add(inputManufacture(false))) {
                    System.out.println("Successfully added 1 manufacture");
                } else System.out.println("Failed to add a manufacture");
                break;
            case 2:
                System.out.print("Manufacture's ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
                Manufacture manufacture = manufactureDAO.get(id);
                if (manufacture != null) {
                    System.out.println(manufacture.toString());
                } else System.out.println("Manufacture not found.");
                break;
            case 3:
                for (Manufacture m : manufactureDAO.getAll()) {
                    System.out.println(m);
                }
                break;
            case 4:
                System.out.print("Manufacture's ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
                if (manufactureDAO.remove(id))
                    System.out.println("Successfully removed a manufacture");
                else System.out.println("Failed to remove a manufacture");

                break;
            case 5:
                if (manufactureDAO.remove(inputManufacture(true)))
                    System.out.println("Successfully removed a manufacture");
                else System.out.println("Failed to remove a manufacture");
                ;
                break;
            case 6:
                if (manufactureDAO.update(inputManufacture(true)))
                    System.out.println("Successfully updated a manufacture");
                else System.out.println("Failed to update a manufacture");
                break;
            default:
                initMainMenu();
                return;
        }

        System.out.println("############");
        System.out.print("Continue? (y/n): ");
        String isContinue = scanner.next();
        if (isContinue.equalsIgnoreCase("y") || isContinue.equalsIgnoreCase("yes")) {
            initManufactureMenu();
        } else {
            initMainMenu();
        }

        return;
    }

    private static void initPhoneMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("MOBILE PHONE MANAGER");
        System.out.println("************************* MENU *************************");
        System.out.println("** 1. Add new phone                                   **");
        System.out.println("** 2. Find a phone by ID                              **");
        System.out.println("** 3. Show all phones                                 **");
        System.out.println("** 4. Remove phone by ID                              **");
        System.out.println("** 5. Remove phone by information                     **");
        System.out.println("** 6. Update phone by ID                              **");
        System.out.println("** 0. Exit program.                                   **");
        System.out.println("********************************************************");
        System.out.print("Your option: ");
        int option = scanner.nextInt();
        int id;
        switch (option) {
            case 1:
                if (phoneDAO.add(inputPhone(false))) {
                    System.out.println("Successfully added 1 phone");
                } else System.out.println("Failed to add a phone");

                break;
            case 2:
                System.out.print("Phone's ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
                Phone phone = phoneDAO.get(id);
                if (phone != null) {
                    System.out.println(phone.toString());
                }
                else System.out.println("Phone not found.");
                break;
            case 3:
                for (Phone p: phoneDAO.getAll()) {
                    System.out.println(p);
                }
                break;
            case 4:
                System.out.print("Phone's ID: ");
                id = scanner.nextInt();
                scanner.nextLine();
                if (phoneDAO.remove(id))
                    System.out.println("Successfully removed a phone");
                else System.out.println("Failed to remove a phone");
                break;
            case 5:
                if (phoneDAO.remove(inputPhone(true)))
                    System.out.println("Successfully removed a phone");
                else System.out.println("Failed to remove a phone");
                break;
            case 6:
                if (phoneDAO.update(inputPhone(true)))
                    System.out.println("Successfully updated a phone");
                else System.out.println("Failed to update a phone");
                break;
            default:
                initMainMenu();
                return;
        }

        System.out.println("############");
        System.out.print("Continue? (y/n): ");
        String isContinue = scanner.next();
        if (isContinue.equalsIgnoreCase("y") || isContinue.equalsIgnoreCase("yes")) {
            initPhoneMenu();
        } else {
            initMainMenu();
        }
        return;
    }

    private static Manufacture inputManufacture(boolean includedID) {
        Scanner scanner = new Scanner(System.in);
        int id;
        String name, location;
        int employee;
        Manufacture manufacture = new Manufacture();
        if (includedID) {
            System.out.print("Manufacture's ID: ");
            id = scanner.nextInt();
            scanner.nextLine();
            manufacture.setId(id);
        }
        // Phone name
        System.out.print("Manufacture's name: ");
        name = scanner.nextLine();

        // Color
        System.out.print("Location: ");
        location = scanner.nextLine();


        // Price
        System.out.print("Employee: ");
        employee = scanner.nextInt();
        scanner.nextLine();

        manufacture.setName(name.trim().toLowerCase());
        manufacture.setLocation(location.trim().toLowerCase());
        manufacture.setEmployee(employee);

        return manufacture;
    }

    private static Phone inputPhone(boolean includedID) {
        Scanner scanner = new Scanner(System.in);
        int id;
        String name, color, country;
        int price, quantity;
        List<Manufacture> manufactureList = manufactureDAO.getAll();
        Phone phone = new Phone();
        if (includedID) {
            System.out.print("Phone's ID: ");
            id = scanner.nextInt();
            scanner.nextLine();
            phone.setId(id);
        }
        // Phone name
        System.out.print("Phone's name: ");
        name = scanner.nextLine();

        // Color
        System.out.print("Color: ");
        color = scanner.nextLine();

        // Country
        System.out.print("Country: ");
        country = scanner.nextLine();

        // Price
        System.out.print("Price: ");
        price = scanner.nextInt();
        scanner.nextLine();

        // Quantity
        System.out.print("Quantity: ");
        quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("<< Manufactures >>");
        for (Manufacture manufacture : manufactureList) {
            System.out.println("" + manufacture.getId() + ". " + manufacture.getName());
        }
        System.out.print("Choose a manufacture: ");
        int manufactureID = scanner.nextInt();
        Manufacture manufacture = manufactureDAO.get(manufactureID);

        phone.setName(name.trim().toLowerCase());
        phone.setColor(color.trim().toLowerCase());
        phone.setCountry(country.trim().toLowerCase());
        phone.setPrice(price);
        phone.setQuantity(quantity);
        phone.setManufacture(manufacture);
        return phone;
    }
}
