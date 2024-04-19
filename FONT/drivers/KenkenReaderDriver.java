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

    private static ArrayList<String> readFileToStrings(String path){
        ArrayList<String> strings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                strings.add(line);
            }
        }
        catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return null;
        }
        return strings;
    }

    public static void main(String[] args) {

        System.out.println("\n"+ "Welcome to Kenken Reader Driver!");
        System.out.println("This Driver allows to test the operation of the reading a kenken from a path.");
        System.out.println("If the kenken complies with the valid formats, all kenken information will be " +
                "displayed by the terminal.");
        System.out.println("In the opposite case, the reason why the kenken is not valid will be shown.\n");

        DomainCtrl domainCtrl = DomainCtrl.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String author = scanner.nextLine();
        System.out.print("Do you want to use a default directory to select all kenkens? [y/n]: ");
        String opt = scanner.nextLine();
        String path = "";

        if (Objects.equals(opt, "y")){
            System.out.print("Enter the directory path: ");
             path = scanner.nextLine();
             if (!path.endsWith("/")) path += "/";
        }

        while(true){
            if (Objects.equals(opt, "y")) System.out.print("Enter the name of the kenken you want to read: ");
            else System.out.print("Enter the path of the kenken you want to read: ");
            String nameOrPath = scanner.nextLine();
            String finalPath = path + nameOrPath;

            boolean succes = true;
            Kenken kenken = null;
            try {
                ArrayList<String> stringsKenken = readFileToStrings(finalPath);
                int kenkenId = domainCtrl.newKenken(author, stringsKenken);
                 kenken = domainCtrl.getKenken(kenkenId);
            } catch (IllegalArgumentException | IllegalStateException e){
                System.out.println("INVALID KENKEN: " + e.getMessage() + "\n");
                succes = false;
            }

            if (succes){
                System.out.println("\nSUCCESSFUL READING\n");

                printKenken(kenken);
                System.out.print("\n");
            }
        }
    }
}
