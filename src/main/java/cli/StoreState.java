package cli;

import models.Order;
import service.StoreService;

import java.io.IOException;
import java.util.Scanner;

public class StoreState extends CliState {
    CliState state;
    Scanner scanner;

    public StoreState(CliFSM fsm) {
        super(fsm);

        scanner = fsm.getScanner();
        state = fsm.getState();


        System.out.println("Please, choose a command number\n" +
                "1- Place an order for a pet\n" +
                "2- Find purchase order by ID\n" +
                "3- Delete purchase order by ID\n" +
                "4- Get pet inventories by status\n" +
                "9- Back\n" +
                "0- Exit program"
        );
        inputLoop();
    }

    private void inputLoop() {
        while (true) {
            String command = scanner.nextLine();

            switch (command) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    placeOrder();
                    break;
                case "2":
                    findById();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    findByStatus();
                    break;
                case "9":
                    toMainMenu();
                    break;
                default:
                    unknownCommand(command);
            }
        }
    }

    @Override
    public void init() {
        inputLoop();
    }

    @Override
    public void unknownCommand(String cmd) {
        System.out.println("Unknown command: " + cmd + "\nPlease, enter the right command");
    }

    @Override
    public void toMainMenu() {
        new CliFSM();
    }

    public void placeOrder() {
        StoreService storeService = new StoreService();
        Order order = new Order();
        setOrderId(order);
        setPetId(order);
        setQuantity(order);
        setShipDate(order);
        order.setStatus(Order.orderStatus.placed.toString());
        order.setComplete(false);

        try {
            storeService.addNewOrder(order);
            System.out.println("Order placed!\nChoose next command");

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }

    private void setOrderId(Order order) {
        while (true) {
            System.out.println("Please, enter the order id:");
            String id = scanner.nextLine();
            try {
                order.setId(Integer.parseInt(id));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setPetId(Order order) {
        while (true) {
            System.out.println("Please, enter the pet id:");
            String id = scanner.nextLine();
            try {
                order.setPetId(Integer.parseInt(id));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setQuantity(Order order) {
        while (true) {
            System.out.println("Please, enter quantity:");
            String quantity = scanner.nextLine();
            try {
                order.setId(Integer.parseInt(quantity));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setShipDate(Order order) {
        while (true) {
            System.out.println("Please, enter desired ship date (YYYY-MM-DD)");
            String date = scanner.nextLine();
            try {
                order.setShipDate(date);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void findById() {
        while (true) {
            System.out.println("Please, enter an order id:");
            String id = scanner.nextLine();
            try {
                new StoreService().findOrderById(id);
                System.out.println("Choose next command");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            inputLoop();
        }
    }

    public void delete() {
        while (true) {
            System.out.println("Please, enter an order id:");
            String id = scanner.nextLine();
            try {
                new StoreService().deleteOrderById(id);
                System.out.println("Choose next command");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            inputLoop();
        }
    }

    public void findByStatus() {
        try {
            new StoreService().getInventoriesByStatus();
            System.out.println("Choose next command");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }
}
