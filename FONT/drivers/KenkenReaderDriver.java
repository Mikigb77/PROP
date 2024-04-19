package drivers;

import src.domain.classes.board.Cell;
import src.domain.classes.board.Group;
import src.domain.classes.kenken.Kenken;
import src.domain.classes.types.Pair;
import src.domain.controllers.DomainCtrl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class KenkenReaderDriver {

    /**
     * Prints the Kenken information to the terminal.
     *
     * @param kenken the Kenken object to be printed
     */
    private static void printKenken(Kenken kenken) {
        System.out.println("KenkenId: " + kenken.getKenkenId());
        System.out.println("Kenken Author: " + kenken.getAuthor());
        System.out.println("Kenken size: " + kenken.getBoard().getGridSize());
        System.out.println("Kenken Date Creation: " + kenken.getCreationDate() + "\n");

        Map<Integer, Group> groupMap = kenken.getBoard().getGroups();

        for (Integer groupId : groupMap.keySet()) {
            Group g = groupMap.get(groupId);
            System.out.print("groupId: " + g.getGroupId() + ", OP: (" + g.operation.getOpString() + "), result: "
                    + g.getResult() + "\n");
            for (Pair<Integer, Integer> coord : g.getCellCoordinates()) {
                Cell c = kenken.getBoard().getCell(coord.getFirst(), coord.getSecond());
                System.out.print("  {cell: (" + c.getX() + "," + c.getY() + "), value: " + c.getValue() + "}\n");
            }
        }
    }

    /**
     * Reads a file and returns its content as a list of strings.
     *
     * @param path The path of the file to be read.
     * @return A list of strings representing the content of the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public static ArrayList<String> readFileToStrings(String path) throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return null;
        }
        return strings;
    }

    /**
     * Main method of the KenkenReaderDriver class.
     * This method is responsible for running the Kenken Reader Driver.
     * It prompts the user to enter their name and whether they want to use a
     * default directory.
     * It then continuously prompts the user to enter the name or path of the kenken
     * they want to read.
     * If the kenken is valid, it is read and all its information is displayed.
     * If the kenken is not valid, the reason for its invalidity is shown.
     *
     * @param args Command line arguments (not used in this program)
     * @throws IOException If an error occurs while reading the file
     */
    public static void main(String[] args) throws IOException {

        System.out.println("\n" + "Welcome to Kenken Reader Driver!");
        System.out.println("This Driver allows to test the operation of the reading a kenken from a path.");
        System.out.println("If the kenken complies with the valid formats, all kenken information will be " +
                "displayed by the terminal.");
        System.out.println("In the opposite case, the reason why the kenken is not valid will be shown.\n");

        DomainCtrl domainCtrl = DomainCtrl.getInstance();
        Scanner scanner = new Scanner(System.in);

        String author;
        String opt;
        String path = "";
        String nameOrPath;
        String finalPath;
        Kenken kenken = null;
        boolean succes;
        ArrayList<String> stringsKenken;
        int kenkenId;

        try {
            System.out.print("Enter your name: ");
            author = scanner.nextLine();
            System.out.print("Do you want to use a default directory to select all kenkens? [y/n]: ");
            opt = scanner.nextLine();
            if (Objects.equals(opt, "y")) {
                System.out.print("Enter the directory path: ");
                path = scanner.nextLine();
                if (!path.endsWith("/"))
                    path += "/";
            }

            while (true) {
                if (Objects.equals(opt, "y"))
                    System.out.print("Enter the name of the kenken you want to read: ");
                else
                    System.out.print("Enter the path of the kenken you want to read: ");
                nameOrPath = scanner.nextLine();
                finalPath = path + nameOrPath;

                try {
                    stringsKenken = readFileToStrings(finalPath);
                    kenkenId = domainCtrl.newKenken(author, stringsKenken);
                    kenken = domainCtrl.getKenken(kenkenId);
                    succes = true;
                } catch (IllegalArgumentException | IllegalStateException | IOException e) {
                    System.out.println("INVALID KENKEN: " + e.getMessage() + "\n");
                    succes = false;
                }

                if (succes) {
                    System.out.println("\nSUCCESSFUL READING\n");

                    printKenken(kenken);
                    System.out.print("\n");
                }
            }
        } finally {
            scanner.close();
        }
    }
}
