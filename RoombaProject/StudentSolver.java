import java.util.ArrayList;
import java.util.*;

class StudentSolver {
  public static ArrayList<Integer> run(double r, ArrayList<Pair<Pair<Double,Double>, Integer>> mess){

    ArrayList<Pair<Pair<Double,Double>, Integer>> arrangedPoints = rearrangeY(mess); //list of pairs with increasing y coordinates
    //System.out.println("\n\n" + arrangedPoints + "\n\n");
    //System.out.println("\n\n" + mess);
    int sum1 = 0;
    int sum2 = 0;
    ArrayList<Integer> coordinates = new ArrayList<Integer>();
    ArrayList<Integer> coordinates2 = new ArrayList<Integer>();
    for(int i = 0; i < arrangedPoints.size() - 1; i++){
      coordinates.add(i);
      sum1 = arrangedPoints.get(i).second + sumOfMesses(arrangedPoints, coordinates, r, i, i + 1);
      //System.out.println(coordinates); 
      //System.out.println("Sum " + i + " = " + sum1);
      if (sum1 > sum2){
        sum2 = sum1;
        for(int x = 0; x < coordinates2.size(); x++){
          coordinates2.remove(x);
        }
        for(int x = 0; x < coordinates.size(); x++){
          coordinates2.add(coordinates.get(x));
        }
      }
      //clears
      for(int index = 0; index < coordinates.size(); index++){
        coordinates.remove(index);
      }
      //System.out.println(coordinates);
    }
    ArrayList<Integer> c3 = remove(coordinates2);
    Collections.sort(c3);
    //System.out.println(c3);
    ArrayList<Integer> c4 = finalList(mess, arrangedPoints, c3);
    //System.out.println(sum2);
    //System.out.println(c4);
    return c4;
  }

  //Method rearranges the pairs based on y coordinates to achieve a bottom up ordering on graph
  public static ArrayList<Pair<Pair<Double,Double>, Integer>> rearrangeY (ArrayList<Pair<Pair<Double,Double>,Integer>> m){
    ArrayList<Pair<Pair<Double,Double>, Integer>> tempList = new ArrayList<Pair<Pair<Double,Double>,Integer>>();
    //add all pairs to a temporary list in order to sort
    for(int pairs = 0; pairs < m.size(); pairs++){
      tempList.add(m.get(pairs));
    }
    //rearrange pairs according to y location(bottom to top)
    for(int y = 0; y < tempList.size(); y++){
      for(int yNext = y + 1; yNext < tempList.size(); yNext++){
        double firstPoint = tempList.get(y).first.second;//first pairs y coordinate
        double secondPoint = tempList.get(yNext).first.second; // next pairs y coordinates
        if(firstPoint > secondPoint){ // checks if y1 > y2
           Pair<Pair<Double,Double>, Integer> temp = tempList.get(y); // add y1 to temporary holder
           tempList.set(y, tempList.get(yNext)); // swaps y2 into y1 index
           tempList.set(yNext, temp); // swaps y1 into y2s index
        }
      }
    }
    return tempList;
  }
  //recursive method finds sum of messes while meeting restrictions of ratio
  public static int sumOfMesses(ArrayList<Pair<Pair<Double,Double>,Integer>> m, ArrayList<Integer> c, double ratio, int p1, int p2){
    double negRatio = -ratio;
    double slope = (m.get(p2).first.second - m.get(p1).first.second)/(m.get(p2).first.first - m.get(p1).first.first);
    //System.out.println(m.get(p2).first.getSecond() +" - " + m.get(p1).first.getSecond() + "/" + m.get(p2).first.getFirst() + " - " + m.get(p1).first.getFirst() + " = " + slope);
    if(p2 == m.size() - 1){
      if(slope >= ratio){ 
        c.add(p2);
        return m.get(p2).second;
      }else if (slope < 0 && slope <= negRatio){
        c.add(p2);
        return m.get(p2).second;
      }
      else{
        return 0;
      }
    }else if(slope >= ratio){
      //System.out.println(m.get(p2).getSecond());
      c.add(p2);
      return m.get(p2).second + sumOfMesses(m, c, ratio, p2, p2 + 1);
    }else if(slope < 0 && slope <= negRatio){
      //System.out.println(m.get(p2).getSecond());
      c.add(p2);
      return m.get(p2).second + sumOfMesses(m, c, ratio, p2, p2 + 1);
    }else {
      //c.add(p2);
      return sumOfMesses(m, c, ratio, p1, p2 + 1);
    }
  }

  //method removes any duplicate values from list.
  public static ArrayList<Integer> remove (ArrayList<Integer> l){
    ArrayList<Integer> l2 = new ArrayList<Integer>();
    for (Integer x : l) {
      if (!l2.contains(x)) {
        l2.add(x);
      }
    }
    return l2;
  }

  public static ArrayList<Integer> finalList (ArrayList<Pair<Pair<Double,Double>,Integer>> originalMess, ArrayList<Pair<Pair<Double,Double>,Integer>> m2, ArrayList<Integer> list){
    ArrayList<Pair<Double,Double>> pairs = new ArrayList<Pair<Double,Double>>();
    ArrayList<Integer> finalListPairs = new ArrayList<Integer>();
    //locate the points from ordered arrayList
    for(int i = 0; i < m2.size(); i++){
      if(list.contains(i)){
        pairs.add(m2.get(i).first); //adds coordinates to new list of Pairs
      }
    }
   // System.out.println(pairs);
    //iterates through original list of messes to find indexs of coordinates
    for(int j = 0; j < pairs.size(); j++){
      for(int k = 0; k < originalMess.size(); k++){
        if(pairs.get(j).equals(originalMess.get(k).first)){
          finalListPairs.add(k);
        }
      }
    }
    return finalListPairs;
  }
}
