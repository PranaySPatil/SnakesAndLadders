

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
//import java.awt.image.ImageObserver;
//import java.awt.image.ImageProducer;
import java.util.*;


//import java.util.List;



class Main implements ActionListener
{
 JFrame f;
 JTabbedPane tabPane;
 
 JPanel mainPanel,introPanel,gamePanel,diePanel,useripanel,companel,namepanel; 
 JPanel gameCenter,gameEast,gameWest,gameNorth,gameSouth;
 

 Icon p1,p2,header;


 Icon icon[][]= new Icon[10][10];
 Icon usericon[]=new Icon[9];
 Icon comicon[]=new Icon[9];
 Icon dieIcon;


 JButton introB[]= new JButton[5];
 JButton b[][]=new JButton[10][10];
 JButton bu[]=new JButton[8];
 JButton buc[]=new JButton[8];
 JButton start,restart;
 JButton JBplayer,JBcomputer,change;  
 JTextField name;
JLabel die,l1,l2;


 
 Random randomNo;


 
 int i,j,num;
 int prevIp1,prevJp1;

 int path;
 int p1value,p2value;
 int gameover;
 


 String str,namestr;





 int game[][]={
	       {100,99,98,97,96,95,94,93,92,91},
	        {81,82,83,84,85,86,87,88,89,90},
		{80,79,78,77,76,75,74,73,72,71},
		{61,62,63,64,65,66,67,68,69,70},
		{60,59,58,57,56,55,54,53,52,51},
		{41,42,43,44,45,46,47,48,49,50},
		{40,39,38,37,36,35,34,33,32,31},
		{21,22,23,24,25,26,27,28,29,30},
		{20,19,18,17,16,15,14,13,12,11},
		{ 1, 2, 3, 4, 5, 6, 7, 8, 9,10},
	      };
	

 

 Main()
 {

 f= new JFrame("Snakes and Ladders Game");
 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
 mainPanel= new JPanel();
 tabPane =new JTabbedPane(JTabbedPane.LEFT);
 randomNo= new Random();

 JBplayer= new JButton("Player");
 JBplayer.setBackground(Color.BLACK);JBplayer.setForeground(Color.CYAN);
 JBcomputer= new JButton("Computer");
 JBcomputer.setBackground(Color.BLACK);JBcomputer.setForeground(Color.CYAN);
 JBplayer.setEnabled(false);
 JBcomputer.setEnabled(false);

 
 JBplayer.addActionListener(this);
 JBcomputer.addActionListener(this); 
 
 die = new JLabel();

game();
userimage();
comimage();
changename();
introduction();
  
  
   f.add(tabPane);
 f.setResizable(false);
   f.setSize(650,620);

  f.setVisible(true);
  

}

void changename()
{
	change = new JButton("Set");
	name = new JTextField("Enter your name ");
	namepanel = new JPanel();
	namepanel.setLayout(new FlowLayout());
	change.addActionListener(this);
	namepanel.add(name);
	namepanel.add(change);
	ImageIcon n = new ImageIcon(getClass().getResource("images/setname.png"));
	 tabPane.addTab("",n,namepanel,"Change player name");
}
void introduction()
{

 introPanel= new JPanel();
 JLabel l0= new JLabel();
 
 ImageIcon intro1 = new ImageIcon(getClass().getResource("images/introduction1.png"));
 l0.setIcon(intro1);
 introPanel.setLayout(new BorderLayout()); 
 introPanel.add(l0,BorderLayout.NORTH); 
 ImageIcon intro = new ImageIcon(getClass().getResource("images/iintro.png"));
 tabPane.addTab("",intro,introPanel,"About");

}
void comimage()
{
	companel = new JPanel();
	for(int j=0;j<8;j++)
	{		
			buc[j]= new JButton();
        	buc[j].addActionListener(this);        
        	comicon[j]=new ImageIcon(getClass().getResource("images/run"+Integer.toString(j)+".gif"));
        	buc[j].setIcon(comicon[j]);
	}
	ImageIcon comp = new ImageIcon(getClass().getResource("images/comp.png"));    	
	companel.setLayout(new GridLayout());	
	for(int j=0;j<8;j++){
		companel.add(buc[j]);
	}
	tabPane.addTab("",comp,companel,"Change computer icon");		
	
}
void userimage()
{
	useripanel = new JPanel();
	
	for(int j=0;j<8;j++)
	{		
			bu[j]= new JButton();
        	bu[j].addActionListener(this);        
        	usericon[j]=new ImageIcon(getClass().getResource("images/run"+Integer.toString(j)+".gif"));
        	bu[j].setIcon(usericon[j]);
	}
	ImageIcon userp = new ImageIcon(getClass().getResource("images/user.png"));    	
	useripanel.setLayout(new GridLayout());	
	for(int j=0;j<8;j++){
		useripanel.add(bu[j]);
	}
	tabPane.addTab("",userp,useripanel,"Change user icon");		
	
	
}


void game()
{
 JLabel gameheader= new JLabel();
 
 l1= new JLabel(); 
  l2= new JLabel(); 
 
 restart= new JButton("Restart");
 restart.setBackground(Color.BLACK);
 restart.setForeground(Color.CYAN);
 restart.setEnabled(false);
 restart.addActionListener(this);
 
 start= new JButton("Start");
 start.setBackground(Color.BLACK);
 start.setForeground(Color.CYAN);
 start.addActionListener(this);
 
 gamePanel= new JPanel();
 

 gameCenter=new JPanel();
 
 gameWest= new JPanel();
 gameWest.setBackground(Color.LIGHT_GRAY);


  gameCenter.setLayout(new GridLayout(10,10));
  gameCenter.setBackground(Color.BLACK);

  
 gameNorth=new JPanel(new FlowLayout());
 gameNorth.setBackground(Color.LIGHT_GRAY);
 gameSouth= new JPanel(new FlowLayout());
 gameSouth.setBackground(Color.LIGHT_GRAY);
 gamePanel.setLayout(new BorderLayout());
 
 p1= new ImageIcon(getClass().getResource("images/p1.gif"));        
 p2= new ImageIcon(getClass().getResource("images/p2.gif"));       

 
 l1.setIcon(p1);
 l2.setIcon(p2);
 
 header=new ImageIcon(getClass().getResource("images/header.jpg"));        
 gameheader.setIcon(header);
  
  for(int i=0;i<10;i++)
	for(int j=0;j<10;j++)
	{
			b[i][j]= new JButton();
        	b[i][j].addActionListener(this);        
			path=game[i][j];
			str=Integer.toString(path);
			icon[i][j]=new ImageIcon(getClass().getResource("images/"+str+".jpg"));
	        b[i][j].setIcon(icon[i][j]);
                 
	}





   
 str=Integer.toString(1);
 
 dieIcon= new ImageIcon(getClass().getResource("images/Game dies/"+str+".jpg"));
 die.setIcon(dieIcon);
	 
  
 gameSouth.add(l1); 
 gameSouth.add(JBplayer);
 gameSouth.add(die);
 gameSouth.add(JBcomputer); 
 gameSouth.add(l2); 

 gameNorth.add(start);
 gameNorth.add(gameheader);
 gameNorth.add(restart);
  
  for(int i=0;i<10;i++)
	{
	for(int j=0;j<10;j++)
	{
		gameCenter.add(b[i][j]);

              //System.out.print("["+i+"]["+j+"],");
                
	}
		//System.out.println();
	}


  
 gamePanel.add(gameCenter,BorderLayout.CENTER);
 gamePanel.add(gameWest,BorderLayout.WEST);
 gamePanel.add(gameNorth,BorderLayout.NORTH);
 gamePanel.add(gameSouth,BorderLayout.SOUTH);
 ImageIcon gameicon = new ImageIcon(getClass().getResource("images/game2.png"));
  tabPane.addTab("",gameicon,gamePanel,"Lets play");
  
  
}



void chance()
{
 int n=randomNo.nextInt(3);
 
 

//System.out.println("Chacne is :"+n) ;
 if(n==0)
	chance();
 if(n==1)
	{
	JBcomputer.setEnabled(false);
	JBplayer.setEnabled(true);
    
	}
 else if(n==2)
	{
	JBcomputer.setEnabled(true);
	JBplayer.setEnabled(false);
    
 	}

 
  
}






public void actionPerformed(ActionEvent e)
{
 
 int n=0;
try{
 if(e.getSource()==JBplayer)
	{
 
  //System.out.println("JB Player");
		do{
		n =playerPassNumber();
		}while(n==0);

		//System.out.println("UR playerPass value is :"+n);	        
		p1value=p1value+n;
         
		 if(p1value>=100)
		 {
			p1value=100-(p1value-100);
		 }
		 
		playerimageTraval(p1value);		
		if(n==6){
			JBcomputer.setEnabled(false);
			JBplayer.setEnabled(true);
		}
		else{
		JBcomputer.setEnabled(true);
		JBplayer.setEnabled(false);
		}
		setBothImage();
 	}
  else if(e.getSource()==JBcomputer)
	{
	//System.out.println("JB Computer");
		do{
		n =playerPassNumber();
		}while(n==0);
	
		//System.out.println("Computer Pass Value is "+n);	
		p2value=p2value+n;
		if(p2value>=100)
		{
		 p2value=100-(p2value-100);
		}
		computerimageTraval(p2value);
		if(n==6){
		JBcomputer.setEnabled(true);
		JBplayer.setEnabled(false);
		}
		else{
			JBcomputer.setEnabled(false);
			JBplayer.setEnabled(true);
		}
		setBothImage();	
	}
  else if(e.getSource()==restart)
	{
  	 p1value=0;
	 p2value=0;
	


	 chance();
	 rePrint();
	}
  else if(e.getSource()==start)
	{
		gameover=0;
		p1value=0;
		p2value=0;

		rePrint();
	  start.setEnabled(false);
	  restart.setEnabled(true);
	  chance();
	}

}catch(Exception ee)
	{
		//System.out.println("Error in BUTTON:"+ee);
	}
try{
	for(int o=0;o<8;o++)
		if(e.getSource()==bu[o]){
			p1= new ImageIcon(getClass().getResource("images/run"+Integer.toString(o)+".gif"));
			l1.setIcon(p1);
			JOptionPane.showMessageDialog(null, "Player icon has been changed");
		}
}
catch(Exception eq){}
try{
	for(int o=0;o<8;o++)
		if(e.getSource()==buc[o]){
			p2= new ImageIcon(getClass().getResource("images/run"+Integer.toString(o)+".gif"));
			l2.setIcon(p2);
			JOptionPane.showMessageDialog(null, "Computer icon has been changed");
		}
}
catch(Exception eq){}
try{
	if(e.getSource()==change)
		{namestr = name.getText();
		JBplayer.setText(namestr);
		JOptionPane.showMessageDialog(null, "Player Name has been changed");}
}
catch(Exception eq){}
}



int playerPassNumber()
{
 int i=	randomNo.nextInt(7);

         str=Integer.toString(i);
	 dieIcon= new ImageIcon(getClass().getResource("images/Game dies/"+str+".jpg"));
	 die.setIcon(dieIcon);
	
     return i;
 
}







void rePrint()
{
try{

for( i=0;i<10;i++)
	{
	for(j=0;j<10;j++)
	  {
		    // b[i][j].setIcon(null);
			 b[i][j].setIcon(icon[i][j]);
	  }
	}
	
   }catch(Exception e)
	{
	//System.out.println("error in repaint"+e);
	}

}




void playerimageTraval(int n)
{
int i=j=0;

int noFlag=0;

if(gameover==0)
{
try{
	rePrint();	
	  for(i=0;i<10;i++)
	       {
		 for(j=0;j<10;j++)
			{
			 if(n==game[i][j])
			    {
                noFlag=1;
				break;
			    }
			}
 		if(noFlag==1)
			break;
	       }

  
		 Thread.sleep(200);
		 b[i][j].setIcon(p1);
		 
		 prevIp1=i;
		 prevJp1=j;
	    

	/*****************checks for staires**********************/
		if(n==10)
			{
			n=28;
			playerimageTraval(n);
			p1value=n;
			}
		else if(n==17)
			{
			n=37;
			playerimageTraval(n);
			p1value=n;
			}
		else if(n==31)
			{
			n=70;
			playerimageTraval(n);
			p1value=n;
			}
		else if(n==45)
			{
			n=84;
			playerimageTraval(n);
			p1value=n;
			}
		else if(n==78)
			{
			n=97;
			playerimageTraval(n);
			p1value=n;
			}


		/*****************Checks for Snakes***************************/
		if(n==95)
			{
		         n=73;
			 playerimageTraval(n);
			 p1value=n;			
			}
		else if(n==79)
			{
		         n=59;
			 playerimageTraval(n);
			 p1value=n;			
			}
		else if(n==68)
			{
		         n=48;
			 playerimageTraval(n);
			 p1value=n;			
			}
		else if(n==44)
			{
		         n=21;
			 playerimageTraval(n);
			 p1value=n;			
			}
		else if(n==34)
			{
		         n=16;
			 playerimageTraval(n);
			 p1value=n;			
			}

			
		
	if(n>=100)
	{   gameover=1;
		JFrame W = new JFrame();
		W.setTitle("CONGRATULATIONS.....!!!!");
		W.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		W.setLayout(new BorderLayout());
		JLabel background=new JLabel(new ImageIcon(getClass().getResource("images/winner.gif")));
		background.setBackground(Color.BLACK);
		W.add(background);
		W.setSize(550, 250);
		W.setVisible(true);
		JOptionPane.showMessageDialog(null, "U WON THE GAME PRESS RESTART TO PLAY AGAIN");
		
		
	}
}
catch(Exception e)
{
 
}
}

}

void setBothImage()
{
int i=0;
int j=0;
int noFlag=0;

if(gameover==0)
{
for(i=0;i<10;i++)
	       {
		 for(j=0;j<10;j++)
			{
			 if(p1value==game[i][j])
			    {
                                noFlag=1;
				break;
			    }
			}
 		if(noFlag==1)
			break;
	       }



b[i][j].setIcon(p1);  


noFlag=0;

for(i=0;i<10;i++)
	       {
		 for(j=0;j<10;j++)
			{
			 if(p2value==game[i][j])
			    {
                                noFlag=1;
				break;
			    }
			}
 		if(noFlag==1)
			break;
	       }



b[i][j].setIcon(p2);

}
}


void computerimageTraval(int n)
{
int i=j=0;

int noFlag=0;

try{
	rePrint();	
	  for(i=0;i<10;i++)
      {
		 for(j=0;j<10;j++)
			{
			 if(n==game[i][j])
			    {
                noFlag=1;
				break;
			    }
			}
 		if(noFlag==1)
			break;
	       }

	 
		
		
		 Thread.sleep(200);
		 b[i][j].setIcon(p2);
		 
		 prevIp1=i;
		 prevJp1=j;
	       
           

              /*****************checks for staires**********************/

		if(n==10)
			{
			n=28;
			computerimageTraval(n);
			p2value=n;
			}
		else if(n==17)
			{
			n=37;
			computerimageTraval(n);
			p2value=n;
			}
		else if(n==31)
			{
			n=70;
			computerimageTraval(n);
			p2value=n;
			}
		else if(n==45)
			{
			n=84;
			computerimageTraval(n);
			p2value=n;
			}
		else if(n==78)
			{
			n=97;
			computerimageTraval(n);
			p2value=n;
			}


		/*****************Checks for Snakes***************************/
		if(n==95)
			{
		         n=73;
			 computerimageTraval(n);
			 p2value=n;			
			}
		else if(n==79)
			{
		         n=59;
			 computerimageTraval(n);
			 p2value=n;			
			}
		else if(n==68)
			{
		         n=48;
			 computerimageTraval(n);
			 p2value=n;			
			}
		else if(n==44)
			{
		         n=21;
			 computerimageTraval(n);
			 p2value=n;			
			}
		else if(n==34)
			{
		         n=16;
			 computerimageTraval(n);
			 p2value=n;			
			}

		
		

	if(n>=100)
	{   
		
		gameover=1;	
		
		
		JFrame W = new JFrame();
		W.setTitle("TRY AGAIN.....!!!!");
		W.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		W.setLayout(new BorderLayout());W.setBackground(Color.BLACK);
		JLabel background=new JLabel(new ImageIcon(getClass().getResource("images/lodt.gif")));
		W.setSize(400, 400);
		W.add(background);
		W.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "U LOST THE GAME PRESS RESTART TO PLAY AGAIN");
	}
		


}
catch(Exception e)
{
 
}


}


public static void main(String args[]){
	System.out.println("START");
 Main m= new Main();
 try {
	m.finalize();
} catch (Throwable e) {
	
	
}
 
}

}