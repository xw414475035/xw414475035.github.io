
package edu.neu.csye6200.bg;

import java.awt.Point;
import java.awt.geom.Point2D;


/**
 *
 * @author Administrator
 */
public class BGRule {
    
    private double leftLengthFactor;
    private double leftAngleDelta;
    private double leftweightFactor;
    private double rightLengthFactor;
    private double rightAngleDelta; 
    private double rightweightFactor;
    private static BGRule RM;
    
    
public static BGRule getInstance() {
    	if(RM == null) {
    		RM = new BGRule();
    	}
    	return RM;
    }

public void Ruleset(int rule){
        switch(rule){
	    	case 1:
                    leftLengthFactor = 3.0 / 4.0;
                    leftAngleDelta = Math.PI / 5.0;
                    leftweightFactor = 0.5;
                    rightLengthFactor = 3.0 / 4.0;
                    rightAngleDelta = - Math.PI / 5.0;
                    rightweightFactor = 0.5;
                    
                    break;
                case 2:
                    leftLengthFactor = 5.0 / 7.0;;
                    leftAngleDelta = Math.PI / 7.5;
                    leftweightFactor = 0.6;
                    rightLengthFactor = 15.0 / 19.0;;
                    rightAngleDelta = - Math.PI /6;
                    rightweightFactor = 0.6;
                    break;
                case 3:
                    leftLengthFactor = 5.0 / 7.0;
                    leftAngleDelta = Math.PI / 4.0;
                    leftweightFactor = 0.7;
                    rightLengthFactor = 4.0 / 7.0;
                    rightAngleDelta = - Math.PI / 6.0;
                    rightweightFactor = 0.7;
                    break;
                default:
                    break; 
    }
}



public double getLeftLengthFactor(){
    return leftLengthFactor;
}


public double getLeftAngleDelta(){
    return leftAngleDelta;
}

public double  getRightLengthFactor(){
    return rightLengthFactor;
}

   
public double getRightAngleDelta(){
    return rightAngleDelta;
}

public double getLeftweightFactor(){
    //System.out.println("!!!! hH: " + leftweightFactor);
    return leftweightFactor;
}
public double getRightweightFactor(){
    return rightweightFactor;
}
       }


