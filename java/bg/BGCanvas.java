
package edu.neu.csye6200.bg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author wang.xiaowan
 */
public class BGCanvas extends JPanel implements Runnable {
    private int rule;
    private int GenNumber;
    
    public BGCanvas(int rule, int GenNumber) {
        this.rule = rule;
	this.GenNumber = GenNumber;
	}
    
    public void setRule(int rule){
        this.rule = rule;
    }
    
    public void setGenNumber(int GenNumber){
        this.GenNumber = GenNumber;
    }
    
    
    public double getGenNumber(){
        return GenNumber;
    }
    public int getRule(){
        return rule;
    }
    
    
    @Override
    public void paint(Graphics g) {
        
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        Dimension size = getSize();
        
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, size.width, size.height);
        g2d.setColor(Color.white);

        ArrayList<BGGeneration> generationList = BGGenerationSet.getInstance().getGenerationList();	
        int genSize = generationList.size(); 
        while(genSize > 0){
            ArrayList<BGStem> bgStemList = generationList.get(genSize - 1).getStemList();
		for(int j = 0; j < bgStemList.size(); j++) {	
		g2d.setStroke(new BasicStroke((float) bgStemList.get(j).getwidth())); 
		g2d.drawLine((int)bgStemList.get(j).getStart().getX(), (int)bgStemList.get(j).getStart().getY(), 
				(int)bgStemList.get(j).getend().getX(), (int)bgStemList.get(j).getend().getY());					
		}
            genSize--;
        }
    }
  
    
    @Override
    public void run() {
        paintImmediately(0, 0, getWidth(), getHeight());
    }
    
    
    
}
