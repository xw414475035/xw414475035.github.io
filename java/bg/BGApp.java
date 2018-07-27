
package edu.neu.csye6200.bg;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author wangxiaowan
 */
public class BGApp implements Observer, ActionListener{

private Logger log = Logger.getLogger(BGApp.class.getName());
private JFrame frame = null;
protected JFrame newframe = null;
protected JPanel northPanel = null;
protected JButton startBtn = null;
protected JButton stopBtn = null;
protected JButton pauseBtn = null;
private JComboBox jComboBox = null;
private JTextField jTextField = null;
protected JPanel ComboPanel = null;
protected JPanel centerPanel = null;
private JLabel jLabel = null;
private JLabel jLabel1 = null;
private int GenNumber;
private int rule;
private BGCanvas bgCanvas;

BGGenerationSet bgGenSet = BGGenerationSet.instance();


//creat a BG ui        
public BGApp(){
    log.info("BGApp started");
    initialize();
    bgGenSet.addObserver(this);
} 

@Override
public void update(Observable o, Object arg) {
    bgCanvas.run();
}

public JPanel getNorthPanel(){
    northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout());
    	
    startBtn = new JButton("Start");//start Button
    startBtn.setFont(new Font("Helvetica", Font.PLAIN, 18));
    startBtn.addActionListener(this); 
    northPanel.add(startBtn);
    	
    pauseBtn = new JButton("Pause");
    pauseBtn.setFont(new Font("Helvetica", Font.PLAIN, 18));
    pauseBtn.addActionListener(this);
    northPanel.add(pauseBtn);
    
    
    stopBtn = new JButton("Stop"); // Stop button
    stopBtn.setFont(new Font("Helvetica", Font.PLAIN, 18));
    stopBtn.addActionListener(this);
    northPanel.add(stopBtn);
    
    
    jLabel = new JLabel("   Rules:   ");
        jLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
        northPanel.add(jLabel);
    
    jComboBox=new JComboBox();//Rule button
    	jComboBox.addItem("1");
    	jComboBox.addItem("2");
    	jComboBox.addItem("3");
    	jComboBox.setFont(new Font("Helvetica", Font.PLAIN, 18));
    northPanel.add(jComboBox);
    
    
    jLabel1 = new JLabel("   Please enter a number of generation  ");
	jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	northPanel.add(jLabel1);
        
    jTextField = new JTextField(5);
	jTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
	northPanel.add(jTextField);
        
        
    return northPanel;
    
	}



//Initialize the contents of the frame.
private void initialize() {
		
	frame = new JFrame();
        
        frame.setSize(1200, 900);//Make it bigger
        frame.setResizable(true);
        frame.setTitle("MyTreeUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setLayout(new BorderLayout());
        frame.add(getNorthPanel(), BorderLayout.NORTH);
        
        
        bgCanvas = new BGCanvas(rule,GenNumber);
        
        frame.add(bgCanvas, BorderLayout.CENTER);
        
      
        frame.setVisible(true);//Let`s see it?!..
		
	
	}
@Override
    public void actionPerformed(ActionEvent bg) {
        if (bg.getSource() == startBtn) {
            startBtn.setEnabled(false);
            bgGenSet.setIsPaused(false);
            
            // Tree trunk
		Point2D start = new Point2D.Double(frame.getWidth()/2, frame.getHeight());
		Point2D end = new Point2D.Double(frame.getWidth()/2, frame.getHeight() * 7/9);		
		BGStem preStem = new BGStem(start, end, 100.0, Math.PI);
		ArrayList<BGStem> bgStemList = new ArrayList<>();
		bgStemList.add(preStem);
		BGGeneration bgg = new BGGeneration();
		bgg.setStemList(bgStemList);
		bgGenSet.addGenerationList(bgg);	
            
	// when START PAINTING button is pressed
            log.info("Start");
            System.out.println("Start button  pressed");
            String StrNumber = jTextField.getText();
            int chooserule = Integer.parseInt(jComboBox.getSelectedItem().toString());
            
            try {
                Integer.parseInt(jTextField.getText()); 
            } catch(NumberFormatException e) { 
                JOptionPane.showMessageDialog(null, "Please enter numbers only");
            }
            
            if (StrNumber.equals(" ")){
                JOptionPane.showMessageDialog(null,"No generation number!","Warning",JOptionPane.INFORMATION_MESSAGE);
            }
            
            int Number = Integer.parseInt(StrNumber);

            bgCanvas.setGenNumber(Number);
            bgCanvas.setRule(chooserule);
            bgCanvas.setVisible(true);	
            
            bgGenSet.setTotalGenNumber(Number);
            bgGenSet.setRule(chooserule);
            
            Thread t1 = new Thread(bgGenSet); // create a thread for the bgS
	    t1.start();	 
            
        }
        
        if (bg.getSource() == pauseBtn) {
            log.info("Pause");
            System.out.println("Pause button pressed");
            startBtn.setEnabled(true);
            bgGenSet.setIsPaused(true);        
        }   
        
        if (bg.getSource() == stopBtn) {
            log.info("Stop");
            System.out.println("Stop button pressed");    
            bgCanvas.setVisible(false); 
            startBtn.setEnabled(true);
            bgGenSet.getGenerationList().clear();          
        }                                                                                              
 
    }


    
public static void main(String[] args){
   
    		// Create the frame on the event dispatching thread.
                SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BGApp();
			}
		});

}
    
}


    
   

