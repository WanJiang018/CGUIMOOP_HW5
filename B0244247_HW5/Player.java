package B0244247_HW5;

import java.util.*;

public class Player extends Person {

	private String name;
	private int chips;
	private int bet = 0;
	// private ArrayList<Card> oneRoundCard;//���P�����d

	public Player(String name, int chips) {
		// Player constructor�A�s�WPlayer����ɡA�ݭn�m�W�B�֦����w�X����ӰѼ�
		this.name = name;
		this.chips = chips;
	}

	public String get_name() {

		return name;
	}

	public int make_bet() {// �n�ˬd�O�_�٦����A�S���F�N����A�~��U�`
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
	public boolean hit_me(Table table) { // �򥻰Ѧұ���G16�I�H�U�n�P�A17�I�H�W���n�P
											// ���ܡG��oneRoundCard�Ӻ�

		if (getTotalValue() <= 16) {
			return true;
		}

		else {
			return false;
		}
	}

	// public int getTotalValue(){ //�^�Ǧ��P���ұo���d�I�ƥ[�`,JQK = 10, A=1 OR 11
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

	public int get_current_chips() { // �^�Ǫ��a�{���w�X

		return chips;
	}

	public void increase_chips(int diff) { // ���a�w�X�ܰʡAsetter
		chips += diff;
	}

	public void say_hello() {
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");

	}
}
