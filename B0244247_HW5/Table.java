package B0244247_HW5;
import java.util.*;

public class Table {
  static final int MAXPLAYER = 4;
  private Deck decks;
  private Player[] players;
  private Dealer dealer;
  private int[] pos_betArray;
  
  public Table(int nDeck){
	  decks = new Deck(nDeck);
	  players = new Player[MAXPLAYER];
	  pos_betArray= new int[MAXPLAYER];
  }
  
  public void set_player(int pos, Player p){
	  players[pos] = p; 
  }
  
  public Player[] get_player(){
	  return players;
  }
  
  public void set_dealer(Dealer d){
	  dealer = d;
  }
  
  public Card get_face_up_card_of_dealer(){
	  return dealer.getOneRoundCard().get(1);
  }
  
  private void ask_each_player_about_bets(){
	  
	  for(int i = 0 ; i < players.length ; i++)
		{
			players[i].say_hello();
			pos_betArray[i] = players[i].make_bet();
		}
  }
  
  private void distribute_cards_to_dealer_and_players(){
	 
	  for(int i=0;i<players.length;i++)
		{
			ArrayList<Card> playerCard = new ArrayList<Card>();
			
			playerCard.add(decks.getOneCard(true));
			playerCard.add(decks.getOneCard(true));
			
			players[i].setOneRoundCard(playerCard);
		}
	  
	    ArrayList<Card> dealerCard = new ArrayList<Card>();
	  
	    dealerCard.add(decks.getOneCard(false));
		dealerCard.add(decks.getOneCard(true));
		
		dealer.setOneRoundCard(dealerCard);
		
		System.out.print("Dealer's face up card is ");
		
		dealer.getOneRoundCard().get(1).printCard();
  }

  
        private void ask_each_player_about_hits(){
        	
        	for(int i=0;i<players.length;i++)
    		{
    			ArrayList<Card> playerCard = players[i].getOneRoundCard();
    			System.out.println(players[i].get_name()+"'s Cards now:");
    			for(Card c : playerCard){
    				c.printCard();
    			}
    			boolean hit=false;
    			do{
    				hit = players[i].hit_me(this);
    				if(players[i].getTotalValue()>21)
    				{
    					System.out.println(players[i].get_name()+", Pass hit!");
    					System.out.println(players[i].get_name()+", Final Card:");
    					for(Card c : playerCard){
    						c.printCard();
    					}
    					continue;
    				}
    				else if(hit){
    					playerCard.add(decks.getOneCard(true));
    					players[i].setOneRoundCard(playerCard);
    					System.out.println("Hit! ");
    					System.out.println(players[i].get_name()+"'s Cards now:");
    					for(Card c : playerCard){
    						c.printCard();
    					}
    				}
    				else{
    					System.out.println(players[i].get_name()+", Pass hit!");
    					System.out.println(players[i].get_name()+", Final Card:");
    					for(Card c : playerCard){
    						c.printCard();
    					}
    				}
    			}while(hit);
    		}
        }

        
    	private void ask_dealer_about_hits(){
    		
    		ArrayList<Card> dealerCard=dealer.getOneRoundCard();
    	
    		boolean hit=false;
    		
    		do{
    			hit = dealer.hit_me(this);
    			
    			if(hit){
    				dealerCard.add(decks.getOneCard(true));
    				dealer.setOneRoundCard(dealerCard);
    			}
    			else{
    				System.out.println("Dealer's hit is over!");
    			}
    		}while(hit);
    	}
    	
    	private void calculate_chips(){
    		
    		System.out.println("Dealer's card value is "+dealer.getTotalValue()+",Cards:");
    		dealer.printAllCard();
    		
    		for(int i = 0 ; i < players.length ; i++)
    		{
    			System.out.print(players[i].get_name() + "'s Cards");
    			players[i].printAllCard();
    			System.out.print(players[i].get_name()+" card value is "+players[i].getTotalValue());
    			
    			//莊家贏
    			if((dealer.getTotalValue() < 22 && players[i].getTotalValue() > 21) || 
    			   (dealer.getTotalValue() < 22 && dealer.getTotalValue()>players[i].getTotalValue()))
    			{
    				players[i].increase_chips(-pos_betArray[i]);
    				System.out.print(", Loss "+pos_betArray[i]+" Chips, the Chips now is "+players[i].get_current_chips()+"\n");
    			}
    			
    			
    			//莊家輸
    			if((players[i].getTotalValue() < 22 && dealer.getTotalValue() > 21) || 
    	           (players[i].getTotalValue() < 22 && players[i].getTotalValue() > dealer.getTotalValue()))	
    		    {
    				players[i].increase_chips(pos_betArray[i]);
    				System.out.print(", Get "+pos_betArray[i]+" Chips,the Chips now is " + players[i].get_current_chips()+"\n");
    		    }
    			
    			//沒贏沒輸
    			else if((dealer.getTotalValue() > 21 && players[i].getTotalValue() > 22)||
   					    (dealer.getTotalValue()==players[i].getTotalValue()))
   		     	{
   			    	System.out.print(", chips have no change! The Chips now is " + players[i].get_current_chips()+"\n");
   		    	}
    		}	
    	}
    	
    	public int[] get_palyers_bet(){
    		
    		return pos_betArray;
    	}
    	
    	
    	public void play(){
    		
    		ask_each_player_about_bets();
    		distribute_cards_to_dealer_and_players();
    		ask_each_player_about_hits();
    		ask_dealer_about_hits();
    		calculate_chips();
    	}
}
