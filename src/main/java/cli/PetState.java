package cli;

import models.Category;
import models.Pet;
import models.Tag;
import service.PetService;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PetState extends CliState {
    CliState state;
    Scanner scanner;

    public PetState(CliFSM fsm) {
        super(fsm);

        scanner = fsm.getScanner();
        state = fsm.getState();


        System.out.println("Please, choose a command number\n" +
                "1- Upload pets image\n" +
                "2- Add new pet to the store\n" +
                "3- Update pet info\n" +
                "4- Find pet by status\n" +
                "5- Find pet by id\n" +
                "6- Update pet with form data\n" +
                "7- Delete pet\n" +
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
                    uploadImage();
                    break;
                case "2":
                    addPet();
                    break;
                case "3":
                    updatePet();
                    break;
                case "4":
                    findByStatus();
                    break;
                case "5":
                    findById();
                case "6":
                    updateWithFormData();
                case "7":
                    deletePet();
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


    public void uploadImage() {

        System.out.println("Please, enter pet's id:");
        String id = scanner.nextLine();

        System.out.println("Please, enter file path:");
        String path = scanner.nextLine().replaceAll("\\\\", "/");

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Sorry, file not found");
        }

        try {
            new PetService()
                    .uploadImg(file, id);
            System.out.println("Choose next command");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }

    public void addPet() {

        Pet pet = new Pet();
        setId(pet);
        setName(pet);
        setCategory(pet);
        setPhotoUrl(pet);
        setTag(pet);
        setPetStatus(pet);

        try {
            new PetService()
                    .add(pet);
            System.out.println("Pet was saved!\nChoose next command");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        inputLoop();
    }

    private void setId(Pet pet) {
        while (true) {
            System.out.println("Please, enter the id:");
            String id = scanner.nextLine();
            try {
                pet.setId(Integer.parseInt(id));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setName(Pet pet) {
        while (true) {
            System.out.println("Please, enter pets name");
            String name = scanner.nextLine();
            try {
                pet.setName(name);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setCategory(Pet pet) {
        while (true) {
            Category category = new Category();

            System.out.println("Please, enter category name");
            String categoryName = scanner.nextLine();

            try {
                category.setName(categoryName);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Please, enter category id");
            String categoryId = scanner.nextLine();

            try {
                category.setId(Integer.parseInt(categoryId));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            pet.setCategory(category);
        }
    }

    private void setPetStatus(Pet pet) {
        while (true) {
            System.out.println("Please, choose pets status (available, pending, sold)");
            String status = scanner.nextLine();
            try {
                pet.setPetStatus(Pet.PetStatus.valueOf(status));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setPhotoUrl(Pet pet) {
        while (true) {
            System.out.println("Please, enter location of the pets photo");
            String path = scanner.nextLine();
            try {
                pet.setPhotoUrl(path);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void setTag(Pet pet) {
        while (true) {
            Tag tag = new Tag();

            System.out.println("Please, enter tag name");
            String tagName = scanner.nextLine();

            try {
                tag.setName(tagName);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Please, enter tag id");
            String tagId = scanner.nextLine();

            try {
                tag.setId(Integer.parseInt(tagId));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            pet.setTag(tag);
        }
    }

    public void updatePet() {
        Pet pet = new Pet();
        System.out.println("Choose pet by enter pets id.");
        setId(pet);
        System.out.println("Now you can update pet info!");
        setName(pet);
        setCategory(pet);
        setPhotoUrl(pet);
        setPetStatus(pet);
        setTag(pet);

        try {
            new PetService().update(pet, pet.getId());
            System.out.println("User updated!\nChoose next command");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }

    public void findByStatus() {
        while (true) {
            System.out.println("Please, enter pets status:");
            String status = scanner.nextLine();
            try {
                new PetService().findByStatus(status);
                System.out.println("Choose next command");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            inputLoop();
        }
    }

    public void findById() {
        while (true) {
            System.out.println("Please, enter pets id:");
            String id = scanner.nextLine();
            try {
                new PetService().findById(id);
                System.out.println("Choose next command");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            inputLoop();
        }
    }

    public void updateWithFormData() {
        Pet pet = new Pet();
        System.out.println("Choose pet by enter pets id.");
        setId(pet);
        System.out.println("Now you can update pet info!");
        setName(pet);
        setPetStatus(pet);

        try {
            new PetService().updateWithFormData(pet, pet.getId());
            System.out.println("User updated!\nChoose next command");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        inputLoop();
    }

    public void deletePet() {
        while (true) {
            System.out.println("Please, enter pet id:");
            String petId = scanner.nextLine();
            try {
                new PetService().delete(petId);
                System.out.println("Choose next command");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            inputLoop();
        }
    }
}

