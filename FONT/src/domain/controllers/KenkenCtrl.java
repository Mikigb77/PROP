package src.domain.controllers;

import src.domain.classes.board.Cell;
import src.domain.classes.board.Group;
import src.domain.classes.kenken.Kenken;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class KenkenCtrl {
    private final Map<Integer, Kenken> kenkenSet;
    private int kenkenIdAvailable;

    public KenkenCtrl() {
        kenkenSet = new HashMap<>();
        kenkenIdAvailable = 0;
    }

    private void readCells(Kenken kenken, int groupId, String[] vs) throws IllegalArgumentException{
        int ap = 3;
        int NC = Integer.parseInt(vs[2]);

        for (int i = 0; i < NC; ++i){
            // una celda
            int X = Integer.parseInt(vs[ap]);
            int Y = Integer.parseInt(vs[ap+1]);
            Cell c = new Cell(X,Y);
            c.setGroupId(groupId);

            if ((ap+2 < vs.length) && vs[ap + 2].startsWith("[") && vs[ap + 2].endsWith("]")){
                //vs[ap+2] = "[X]"
                String valueCell = vs[ap + 2].substring(1, vs[ap + 2].length() - 1);
                c.setValue(Integer.parseInt(valueCell));
                ap += 3;
            }
            else ap += 2;

            //añadir la celda al board
            kenken.getBoard().setCellInBoard(c);
        }
    }

    private Kenken createKenken(int kenkenId, String author, ArrayList<String> strings) throws IllegalArgumentException{
        if (strings == null || strings.isEmpty()) throw new IllegalArgumentException("kenken information in strings format invalid.");
        Date date = new Date();
        Kenken kenken = new Kenken(kenkenId, author, date);
        boolean firstLine = true;
        int N;
        int groupId = 0;

        for (String line : strings) {
            String[] vs = line.split(" ");
            if (firstLine) {
                if (vs.length == 2) {
                    N = Integer.parseInt(vs[0]);

                    //S'assigna al kenken un nou tauler de mida N*N
                    kenken.setNewBoard(N);
                    firstLine = false;
                }
                else throw new IllegalArgumentException("Incorrect format or file\n");
            }
            else {
                int opId = Integer.parseInt(vs[0]);
                int result = Integer.parseInt(vs[1]);
                // es crea un nou grup
                Group g = new Group(groupId, opId, result);
                // S'afegeix el grup al tauler
                kenken.getBoard().addNewGroup(g);
                readCells(kenken, groupId, vs);
                ++groupId;
            }
        }
        kenken.getBoard().checkGroups();
        return kenken;
    }

    public int newKenken(String author, ArrayList<String> strings) {
        Kenken kenken = createKenken(kenkenIdAvailable, author, strings);
        kenkenSet.put(kenkenIdAvailable, kenken);
        kenkenIdAvailable++;
        return kenkenIdAvailable-1;
    }

    public Kenken getKenken(int kenkenId) {
        return kenkenSet.get(kenkenId);
    }

}
