
package edu.neu.csye6200.bg;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Administrator
 */
public class BGGenerationSet extends Observable implements Runnable{
    
    private int totalGenNumber;

    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void setGenerationList(ArrayList<BGGeneration> GenerationList) {
        this.GenerationList = GenerationList;
    }

    public static void setInstance(BGGenerationSet instance) {
        BGGenerationSet.instance = instance;
    }

    public int getTotalGenNumber() {
        return totalGenNumber;
    }

    public int getRule() {
        return rule;
    }

    public boolean isIsPaused() {
        return isPaused;
    }

    public static BGGenerationSet getInstance() {
        return instance;
    }

    public void setTotalGenNumber(int totalGenNumber) {
        this.totalGenNumber = totalGenNumber;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }
    private int rule;
    private boolean isPaused; 
    private ArrayList<BGGeneration>  GenerationList;
    private static BGGenerationSet instance = null;
    
    
    
    public BGGenerationSet(){
        GenerationList = new ArrayList<BGGeneration>();
        
    }
    
    public static BGGenerationSet instance(){
        if(instance == null) {
    		instance = new BGGenerationSet();
    	}
    	return instance;
    }
    public void setGenerationlist(){
        this.GenerationList = GenerationList;
    }
    
    public ArrayList<BGGeneration> getGenerationList(){
        return GenerationList;
        
    }
    public void addGenerationList(BGGeneration bggeneration){
        GenerationList.add(bggeneration);
    }

    @Override
    public void run() {
        int curGenNumber = 0; 			
	while(curGenNumber < totalGenNumber) {	
			if(!isPaused){
                            int gListSize = GenerationList.size();
                            ArrayList<BGStem> stemList = GenerationList.get(gListSize -1).getStemList();
                            BGGeneration bgg = new BGGeneration();
                            for(int i = 0; i < stemList.size(); i++){                          
                                BGStem preStem = stemList.get(i);
				bgg.CreateG(preStem, getRule());
                            }

                            GenerationList.add(bgg);
				
                            setChanged(); // Something happened
                            notifyObservers(new String ("****** New message: New BGGeneration Created " ));
                            
				try {
					Thread.sleep(1200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                                
                                curGenNumber++;	
                        }
                }
    }
    
    
    
    
}
