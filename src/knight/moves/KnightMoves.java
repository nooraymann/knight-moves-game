/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knight.moves;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;

public class KnightMoves {
    //X -axis 
   static int horizontal[] = {2, 1, -1, -2, -2, -1, 1, 2};//if horse moved 2 steps on the horizontal axis it will move 1 step on the vertical axis "L",....
   //Y-axis 
   static int vertical[] = {1, 2, 2, 1, -1, -2, -2, -1}; 
   
   //check if it is not out of border (if a point has value less than 0 or greater than 8 ) and if it doesn't meet an obstacle 
   static boolean check_moves(int p1, int p2, ArrayList<ArrayList<Integer>> obstacles) {//[0,0]--[[1,2],[2,0]]
        int flags[] = new int[obstacles.size()];
        boolean flag = false;
        if (p1 >= 0 && p2 >= 0 && p1 < 8 && p2 < 8) { 
            for (int j = 0; j < obstacles.size(); j++) {
                if (p1 != obstacles.get(j).get(0) || p2 != obstacles.get(j).get(1)) { //check if the point not an obstacle
                    flags[j] = 0; //mark the index as not obstacle
                } else {
                    flags[j] = 1;//mark the index as an obstacle
                }
            }
            for (int i = 0; i < flags.length; i++) { //check the flags array if there is the point met any obstacle from the obstacles list
                if (flags[i] == 1) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                return true;
            } else {
                return false;
            }
       } else {
            return false;
        }
    }
    static cell findPath(ArrayList<ArrayList<Integer>> obstacles, cell start, cell goal) //takes obstcales list, start point'knight',goal point  
    {
        HashSet<cell> Closed = new HashSet<>(); //contain visited cells
        Queue<cell> Opened = new ArrayDeque<>();//scheduled to visit
        Opened.add(start); 
        while (!Opened.isEmpty()) {
            cell next = Opened.poll(); //take the first element in the Opened list
            int point1 = next.p1; //x-axis
            int point2 = next.p2;//y-axis
            int shortest_distance = next.distance; 
            if (point1 == goal.p1 && point2 == goal.p2) { //base case
                System.out.println("The shortest Path is ->"+next.distance);
                return next;
                
            }
            if (!Closed.contains(next)) {
                Closed.add(next);
            }
            for (int i = 0; i < 8; i++) {
                int try_p1 = point1 + horizontal[i]; // try all the possible moves "L moves" on both horizontal and vertical axis
                int try_p2 = point2 + vertical[i];
                // add the new state with her parent to the Opened list to visit it and increase the distance by 1
                if (check_moves(try_p1, try_p2, obstacles)) { //if the point is valid 
                    Opened.add(new cell(try_p1, try_p2, shortest_distance+1,next));
                }
            }
        }
        return null; // finally if there is no valid path the function return null 
    }
     static void displayMoves(cell cell) { //recursion function to display all the moves with the shortest path 
        if (cell != null) { 
            
         displayMoves(cell.parent);// get the parent
        System.out.print(" "+"("+cell.p1 +","+cell.p2+")");
            
        }
        else{
            System.out.print("There is no path");
            return;}
           }
    public static void main(String[] args) { 
        //Test the assignment example
        ArrayList<ArrayList<Integer>> obstacles = new ArrayList<>(); // a list of obstacles lists
        obstacles.add(new ArrayList<Integer>(Arrays.asList(1, 4))); //add the first point to the row 0
        obstacles.add(1, new ArrayList<>(Arrays.asList(2, 2)));//add the first point to the row 1,...
        obstacles.add(2, new ArrayList<>(Arrays.asList(6, 2)));
        obstacles.add(2, new ArrayList<>(Arrays.asList(4, 4)));
        obstacles.add(2, new ArrayList<>(Arrays.asList(1, 5)));
        obstacles.add(2, new ArrayList<>(Arrays.asList(3, 7)));
        cell start=new cell(0,0);
        cell goal=new cell(6,6);
        cell result=null;
        result=findPath(obstacles,start,goal);
        if(result==null){
            System.out.println("There is no valid path to this point");}
        else{
            System.out.print("The Moves performed to reach the shortest path -> ");
            displayMoves(result);
            System.out.println();
        }
    }
}