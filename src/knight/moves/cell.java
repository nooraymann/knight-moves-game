/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knight.moves;

/**
 *
 * @author Ayman
 */
public class cell {
    //each cell contain x-axis point , Y-axis point , the distance travelled and her parent
    int p1,p2,distance;
    cell parent;
    public cell(int p1, int p2,int distance,cell parent)
    {
        this.p1=p1;
        this.p2 = p2;
        this.distance=distance;
        this.parent=parent;
    }
     public cell(int p1, int p2) //to get input from user
    {
        this.p1=p1;
        this.p2 = p2;
     
    }
 
}

    
 