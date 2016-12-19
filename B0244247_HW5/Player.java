package B0244247_HW5;

import java.util.*;

public class Player extends Person {

	private String name;
	private int chips;
	private int bet = 0;
	// private ArrayList<Card> oneRoundCard;//此牌局的卡

	public Player(String name, int chips) {
		// Player constructor，新增Player物件時，需要姓名、擁有的籌碼等兩個參數
		this.name = name;
		this.chips = chips;
	}

	public String get_name() {

		return name;
	}

	public int make_bet() {// 要檢查是否還有錢，沒錢了就不能再繼續下注
		bet = 1;
		
		if (chips > bet) {
			bet = 1;
		} 
		
		else {
			bet = 0;
		}

		return bet;
	}

	// public void setOneRoundCard(ArrayList<Card> cards){
	// oneRoundCard = cards;
	// }

	@Override
	public boolean hit_me(Table table) { // 基本參考條件：16點以下要牌，17點以上不要牌
											// 提示：用oneRoundCard來算

		if (getTotalValue() <= 16) {
			return true;
		}

		else {
			return false;
		}
	}

	// public int getTotalValue(){ //回傳此牌局所得的卡點數加總,JQK = 10, A=1 OR 11
	// int sum = 0;
	// for (Card card : oneRoundCard) {
	// if(card.rank == 11 || card.rank == 12 || card.rank ==13)
	// {
	// card.rank = 10;
	// }
	// sum += card.getRank();
	// }
	// return sum;
	// }

	public int get_current_chips() { // 回傳玩家現有籌碼

		return chips;
	}

	public void increase_chips(int diff) { // 玩家籌碼變動，setter
		chips += diff;
	}

	public void say_hello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");

	}
}
