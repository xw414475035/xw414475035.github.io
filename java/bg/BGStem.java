
package edu.neu.csye6200.bg;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author Administrator
 */
public class BGStem {
   
    private double width;
    private Point2D start;
    private Point2D end;
    private double alpha;
    
    

    public BGStem(Point2D start, Point2D end, double width, double alpha){
        this.start = start;
        this.end = end;
        this.width = width;
        this.alpha = alpha;
        
    }
    
    
    public Point2D getStart(){
        return start;
    }
    public Point2D getend(){
        return end;
    }
    public double getLegth(){
        return Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(),2));
        
    }
    //is it need another degree? the 3rd generation looks different may due to you choose a wrong angle
    public double getalpha(){
        return alpha;
    }
    public double getwidth(){
        return width;
    }
 
        
     
    }

