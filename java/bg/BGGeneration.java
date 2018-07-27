
package edu.neu.csye6200.bg;

import java.util.ArrayList;
import edu.neu.csye6200.bg.BGRule;
import java.awt.Point;
import java.awt.geom.Point2D;
/**
 *
 * @author Administrator
 */
public class BGGeneration {
    
   private ArrayList<BGStem> Stemlist = new ArrayList<>();
    
    public void BGGeration(){
        Stemlist = new ArrayList<>();

    }
    
   public void CreateG(BGStem runum, int rule){
       BGRule RM = BGRule.getInstance();
       RM.Ruleset(rule);
       Point2D start = runum.getend();
       Point2D Leftpoint = new Point2D.Double( 
               start.getX() + Math.sin(runum.getalpha() + RM.getLeftAngleDelta() ) * runum.getLegth() * RM.getLeftLengthFactor(),
               start.getY() + Math.cos(runum.getalpha() + RM.getLeftAngleDelta() ) * runum.getLegth() * RM.getLeftLengthFactor());
       Point2D Rightpoint = new Point2D.Double(
            start.getX() + Math.sin(runum.getalpha() + RM.getRightAngleDelta() ) * runum.getLegth()*RM.getRightLengthFactor(),
            start.getY() + Math.cos(runum.getalpha() + RM.getRightAngleDelta() ) * runum.getLegth() *RM.getRightLengthFactor());
       double Leftwigth = runum.getwidth() * RM.getLeftweightFactor();
       double Rightwigth = runum.getwidth() * RM.getRightweightFactor();
       double LeftAngle = runum.getalpha() + RM.getLeftAngleDelta();
       double RightAngle = runum.getalpha() + RM.getRightAngleDelta();
      
       
        BGStem LeftpointStemList = new BGStem(start, Leftpoint, Leftwigth , LeftAngle);
        Stemlist.add(LeftpointStemList);
        BGStem RightpointStemList = new BGStem(start, Rightpoint, Rightwigth, RightAngle);
        Stemlist.add(RightpointStemList);
        
       
   }

    
    public void  setStemList (ArrayList<BGStem> Stemlist){
        this.Stemlist = Stemlist;
    }
    
    public ArrayList<BGStem> getStemList(){
        return Stemlist;
    }
    
    public void addStemList(BGStem runum){
        Stemlist.add(runum);
    }
    public void addRightStemList(BGStem runum){
        Stemlist.add(runum);
    }
  
   
}
