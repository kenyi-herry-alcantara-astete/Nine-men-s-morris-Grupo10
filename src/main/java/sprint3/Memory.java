package sprint3;

import java.util.ArrayList;

public class Memory {

    //Matriz recuerdo
    private ArrayList<String> MenoryTreEnRaya = new ArrayList<String>();
    private static Memory memory = new Memory();

    private Memory(){
        //
    }

    public ArrayList<String> getMenoryTreEnRaya() {
        return MenoryTreEnRaya;
    }
    public static Memory getMyMemoryObject(){
        return memory;
    }

    public void addToMemory(String caseTresEnRaya){
        MenoryTreEnRaya.add(caseTresEnRaya);
    }

    public void removeOfTheMemory(int indexRow, int indexColumn){


        String posititon = indexRow +String.valueOf(indexColumn);
        ArrayList <String> Temporary = new ArrayList<String>();

        for ( String groupPosition:MenoryTreEnRaya) {
            if (MenoryTreEnRaya.size() != 0){
                String one = groupPosition.substring(0,2);
                String two = groupPosition.substring(2,4);
                String three = groupPosition.substring(4,6);
                if (one.equals(posititon) || two.equals(posititon) || three.equals(posititon)) {
                    Temporary.add(one+two+three);
                }
            }
        }
        for (String group:Temporary) {
            MenoryTreEnRaya.remove(group);
        }
        //Elimnando Temporary
        Temporary.clear();

    }


    public boolean isInTheMemory(String caseTresEnRaya){
        boolean res = false;

        for (String oneCase: MenoryTreEnRaya){
            if(oneCase.equals(caseTresEnRaya)){
                res = true;
            }
        }
        return  res;
    }


}
