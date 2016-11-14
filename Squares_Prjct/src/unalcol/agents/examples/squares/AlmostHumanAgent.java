package unalcol.agents.examples.squares;

import java.util.Iterator;

import unalcol.agents.Action;
import unalcol.agents.Agent;
import unalcol.agents.Percept;
import unalcol.agents.AgentProgram;
import unalcol.types.collection.vector.Vector;




//An expert player tries to force their opponent to open the first long chain, 
//because the player who first opens a long chain usually loses.

//the expert simply has to make the correct number of sacrifices to 
//encourage the opponent to hand him the first chain long enough to ensure a win.

public class AlmostHumanAgent  implements AgentProgram {
	
	protected String color;
	protected boolean marked[][];
	protected int pnumber;
	protected int sq1;
	protected int sq2;
	protected int sq3;
	protected int sq4;
	protected int markable1;
	protected int markable2;
	protected int markable3;
	protected int markable4;
	
	int board_size = 0;
	

	Vector<String> Q ;
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	public AlmostHumanAgent(String color){
		this.color = color;	
		marked = new boolean[100][100];
		for(int x = 0; x< 100; x++ ){
			for(int y = 0; y< 100; y++){
				marked[x][y] = false;
				
			}
			
		}
		
		
		
	}
	
	
	
	public int utility(int i , int j, String the_turn, Percept p){
		
		
		
		if(((String)p.getAttribute(i+":"+j+":"+Squares.LEFT)).equals(Squares.FALSE))
			return utility(i ,j-1, the_turn, p);
		if(((String)p.getAttribute(i+":"+j+":"+Squares.TOP)).equals(Squares.FALSE))
            //counter++;
          if(((String)p.getAttribute(i+":"+j+":"+Squares.BOTTOM)).equals(Squares.FALSE))
            //counter++;
          if(((String)p.getAttribute(i+":"+j+":"+Squares.RIGHT)).equals(Squares.FALSE))
            //counter++;
          
         return 0;
		
		
		return 0;
		
	}
	
	

	public void editCount(int i, int j){
		if(i<(board_size/2) && j<(board_size/2) ){
			sq1++;		
		}
		if(i>(board_size/2) && j<(board_size/2) ){
			sq2++;		
		}
		if(i<(board_size/2) && j>(board_size/2) ){
			sq3++;		
		}
		if(i>(board_size/2) && j>(board_size/2) ){
			sq4++;		
		}
		
		
	}
	
	
	String whereIs(Percept p, int a, int b){
		 
		if(((String)p.getAttribute(a+":"+b+":"+Squares.RIGHT)).equals(
				 Squares.FALSE))		 
			return Squares.RIGHT;
		
		if(((String)p.getAttribute(a+":"+b+":"+Squares.BOTTOM)).equals(
				 Squares.FALSE))		 
			return Squares.BOTTOM;
		
		if(((String)p.getAttribute((a+1)+":"+(b+1)+":"+Squares.TOP)).equals(
				 Squares.FALSE))		 
			return Squares.TOP;
		
		if(((String)p.getAttribute((a+1)+":"+(b+1)+":"+Squares.LEFT)).equals(
				 Squares.FALSE))		 
			return Squares.LEFT;
		
		else{
			return "";
		
		}
		
	}
	
	
	
	
	public Action ourOwnAction(Percept p, int i, int j){
				
		
		
		int left_up = 0;
		int right_up = 0;
		int left_down = 0;
		int right_down = 0;
		
		
		if(((String)p.getAttribute(i+":"+j+":"+Squares.LEFT)).equals(
				 Squares.TRUE)) 
					 left_up++;
					 left_down++;
		if(((String)p.getAttribute(i+":"+j+":"+Squares.TOP)).equals(
				 Squares.TRUE)) 
					 left_up++;
					 right_up++;
		if(((String)p.getAttribute(i+":"+j+":"+Squares.RIGHT)).equals(
				 Squares.TRUE)) 
					 right_up++;
					 right_down++;
		if(((String)p.getAttribute(i+":"+j+":"+Squares.BOTTOM)).equals(
				 Squares.TRUE)) 
					 left_down++;
					 right_down++;
					 
					 
		if(((String)p.getAttribute((i-1)+":"+(j-1)+":"+Squares.RIGHT)).equals(
				 Squares.TRUE)) 
					 left_up++;
		if(((String)p.getAttribute((i-1)+":"+(j-1)+":"+Squares.BOTTOM)).equals(
				 Squares.TRUE)) 
					 left_up++;
		
		if(((String)p.getAttribute((i-1)+":"+(j)+":"+Squares.RIGHT)).equals(
				 Squares.TRUE)) 
					 right_up++;				
		if(((String)p.getAttribute((i)+":"+(j+1)+":"+Squares.TOP)).equals(
				 Squares.TRUE)) 
					 right_up++;
		
		
		if(((String)p.getAttribute((i+1)+":"+(j-1)+":"+Squares.TOP)).equals(
				 Squares.TRUE)) 
					 left_down++;
		if(((String)p.getAttribute((i+1)+":"+(j-1)+":"+Squares.RIGHT)).equals(
				 Squares.TRUE)) 
					 left_down++;
		

		if(((String)p.getAttribute((i+1)+":"+(j+1)+":"+Squares.TOP)).equals(
				 Squares.TRUE)) 
					 right_down++;
		if(((String)p.getAttribute((i+1)+":"+(j+1)+":"+Squares.LEFT)).equals(
				 Squares.TRUE)) 
					 right_down++;
		
		
		
					
					
		
		System.out.println("left_up:" + left_up );
		System.out.println("left_down:" + left_down );
		System.out.println("right_up:" + right_up );
		System.out.println("right_down:" + right_down );
		System.out.println("i:" + i + "j: " +j);
		
		
		int min1 = Math.min(left_up,right_up);
		
		String where = "right_up";
		if(min1 == left_up){
			where = "left_up";
		}
		
				
		int min2 = Math.min(left_down, right_down);
		
		String where2 = "left_down";
		if(min2 == right_down){
			where2 = "right_Down";
		}
				
		int mint = Math.min(min1,  min2);
		
		String act = where;
		if(mint == min2){
			act= where2;
		}
		
		
		
		if(mint <= 1 || mint >= 3 ){
			
			switch(act){
			
				case "left_up":
					String tentative_Action = whereIs(p, i-1, j-1);
					if(tentative_Action == Squares.TOP || tentative_Action == Squares.LEFT){
						System.out.println("Jugó");
						return new Action(i+":"+j+":"+ tentative_Action );
					}
					else{
						if( tentative_Action == Squares.RIGHT || tentative_Action == Squares.BOTTOM){
							System.out.println("Jugó");
							return new Action((i-1)+":"+(j-1)+":"+tentative_Action);
						}
					
					}				
					break;
				
				case "left_down":
					String tentative_Action2 = whereIs(p, i, j-1);
					if(tentative_Action2 == Squares.TOP || tentative_Action2 == Squares.LEFT){
						System.out.println("Jugó");
						return new Action((i+1)+":"+(j)+":"+ tentative_Action2 );
					}
					else{
						if( tentative_Action2 == Squares.RIGHT || tentative_Action2 == Squares.BOTTOM){
							System.out.println("Jugó");
							return new Action((i)+":"+(j-1)+":"+tentative_Action2);
						}
						
					}	
				
					break;
			
					case "right_up":
						String tentative_Action3 = whereIs(p, i-1, j);
						if(tentative_Action3 == Squares.TOP || tentative_Action3 == Squares.LEFT){
							System.out.println("Jugó");
							return new Action(i+":"+(j+1)+":"+ tentative_Action3 );
						}
						else{
							if( tentative_Action3 == Squares.RIGHT || tentative_Action3 == Squares.BOTTOM){
								System.out.println("Jugó");
								return new Action((i-1)+":"+(j)+":"+tentative_Action3);
							}
					
						}	
				
				break;
			case "right_down":
				String tentative_Action4 = whereIs(p, i, j);
				if(tentative_Action4 == Squares.TOP || tentative_Action4 == Squares.LEFT){
					System.out.println("Jugó");
					return new Action((i+1)+":"+(j+1)+":"+ tentative_Action4 );
				}
				else{
					if( tentative_Action4 == Squares.RIGHT || tentative_Action4 == Squares.BOTTOM){
						System.out.println("Jugó");
						return new Action((i)+":"+(j)+":"+tentative_Action4);
					}
					
				}	
				break;		
			
			}
		}
		
		if(mint ==4){
			marked[i][j] = true;						
		}
		
		
			int x = (int)((board_size- 2)* Math.random()+1);
			int y = (int)((board_size- 2)* Math.random()+1);
		
			while(marked[x][y] == true){
			 x = (int)((board_size- 2)* Math.random()+1);
			 y = (int)((board_size- 2)* Math.random()+1);
			
			}
		
		return ourOwnAction(p, x, y);
		
			
			

		
	}
	
	
	
	@Override
	public Action compute(Percept p){
		
		board_size = Integer.parseInt((String) p.getAttribute(Squares.SIZE));
		int total_moves = ((board_size - 2) * board_size) * 2;
		int i = 0;
		int j = 0;

		
		
		if (p.getAttribute(Squares.TURN).equals(color)) {
			
			//board_size = Integer.parseInt((String) p.getAttribute(Squares.SIZE));

			

			if (pnumber < total_moves /2) {
				
				  Vector<String> v = new Vector<String>(); 
				  
				  i = (int)(board_size*Math.random()); 
				  j = (int)(board_size*Math.random());
				  
				 
				 if(((String)p.getAttribute(i+":"+j+":"+Squares.LEFT)).equals(
				 Squares.FALSE)) 
					 v.add(Squares.LEFT);
				 
				 if(((String)p.getAttribute(i+":"+j+":"+Squares.TOP)).equals(
				 Squares.FALSE)) 
					 v.add(Squares.TOP);
				 
				  if(((String)p.getAttribute(i+":"+j+":"+Squares.BOTTOM)).
				  equals(Squares.FALSE)) 
					  v.add(Squares.BOTTOM);
				  
				 if(((String)p.getAttribute(i+":"+j+":"+Squares.RIGHT)).equals
				 (Squares.FALSE)) 
					 v.add(Squares.RIGHT);
				
				editCount(i,j); 
				pnumber += 2;
				//Q.del(i+":"+j+":");
				return new Action(
				 i+":"+j+":"+v.get((int)(Math.random()*v.size())) );


			}
			
			else{
				  
				for(int x= 2; x< board_size-2; x++){
					for(int y = 2; y< board_size-2; y++){
						if (marked[x][y] == false){
							i= x;
							j = y;
							return(ourOwnAction(p, i, j));
							
						}
						
					}
					
					
				}			 
				
				
			}
		
			
			
			
			
		}
		Squares.TURN = color;
		
        return new Action(Squares.PASS);
	
	}
	
	@Override
	public void init(){
		//this.getClass();
		new Agent( new AlmostHumanAgent(Squares.BLACK));
		System.out.println("Reset");
		
		
		
	}


	
	
	
	
}
