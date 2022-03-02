package com.deserve;

import java.util.Scanner;

public class Game {
	private Player p1;
	private Player p2;
	private Play turn;
	private int scoreToWin;

	void displayGameMenu() {
		System.out.println();
		System.out.println("(1) Play one round");
		System.out.println("(2) Who is leading now?");
		System.out.println("(3) Exit game");
		System.out.print("Choose an option: ");
	}

	void selectGameOption(int optionSelected) {
		switch (optionSelected) {
		case 1:
			this.playOneRound(p1);
			this.playOneRound(p2);
			printScore();
			break;
		case 2:
			this.whoIsLeading();
			break;
		default:
			break;
		}
	}

	private void printScore() {
		System.out.format("Player: %s with score %d", p1.getName(), p1.getTotalScore());
		System.out.println();
		System.out.format("Player: %s with score %d", p2.getName(), p2.getTotalScore());
		System.out.println();
	}

	void startNewGame() {
		String p1Name;
		String p2Name;

		Scanner sc = new Scanner(System.in);
		p1Name = "A1";
		p2Name = "A2";
		System.out.print("Please enter the maximum score required to win: ");
		scoreToWin = sc.nextInt();
		p1 = new Player(p1Name);
		p2 = new Player(p2Name);
		turn = new Play();
	}

	void playOneRound(Player p1) {
		System.out.println(p1.getName() + " playing:");
		int firstTurn = turn.serve();
		int secondTurn = turn.serve();

		System.out.println("firstTurn: " + firstTurn + " " + "firstTurn: " + secondTurn);
		if (firstTurn == 1) {
			increment(p1, false);
		} else if (firstTurn == 0) {
			increment(p1, true);
		}
		if (secondTurn == 1) {
			increment(p1, false);
		} else if (secondTurn == 0) {
			increment(p1, true);
		}
	}

	private void increment(Player player, boolean b) {
		if (player.getName().equals(p1.getName())) {
			if (b) {
				p1.setTotalScore(1);
			} else {
				p2.setTotalScore(1);
			}
		} else {
			if (b) {
				p2.setTotalScore(1);
			} else {
				p1.setTotalScore(1);
			}
		}
	}

	void whoIsLeading() {
		if (p1.getTotalScore() == p2.getTotalScore()) {
			System.out.format("Its currently a draw, " + "%s has %d, %s has %d", p1.getName(), p1.getTotalScore(),
					p2.getName(), p2.getTotalScore());
		} else if (p1.getTotalScore() > p2.getTotalScore()) {
			System.out.printf("%s is leading, %s has %d points, " + "%s has %d points", p1.getName(), p1.getName(),
					p1.getTotalScore(), p2.getName(), p2.getTotalScore());
		} else if (p1.getTotalScore() < p2.getTotalScore()) {
			System.out.format("%s is leading, %s has %d points, " + "%s has %d points.", p2.getName(), p2.getName(),
					p2.getTotalScore(), p1.getName(), p1.getTotalScore());
		}
		System.out.println();
	}

	boolean checkIfAnyoneHasWon() {
		return checkWon();
	}

	private boolean checkWon() {
		if (p1.getTotalScore() >= scoreToWin && p2.getTotalScore() >= scoreToWin) {
			System.out.println("Its a draw! Both players have exceeded the score limit");
			return true;
		} else if (p1.getTotalScore() >= scoreToWin && p2.getTotalScore() < scoreToWin) {
			System.out.format("%s won", p1.getName());
			return true;
		} else if (p1.getTotalScore() < scoreToWin && p2.getTotalScore() >= scoreToWin) {
			System.out.format("%s won", p2.getName());
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println("Welcome to the Dice and Roll game!");

		Game game = new Game();

		game.startNewGame();

		while (true) {
			System.out.println("Enter yes or y to play");
			Scanner sc = new Scanner(System.in);
			String response = sc.next();
			if (response.toLowerCase().equals("y") || response.toLowerCase().equals("yes")) {
				System.out.println("Enter option 1 to play and 2 to know who is leading:");
				int option = sc.nextInt();
				game.selectGameOption(option);
			}
			boolean anyoneWin = game.checkIfAnyoneHasWon();
			if (anyoneWin) {
				System.out.println();
				System.out.println("Game ended.");
				break;
			}
		}
	}
}