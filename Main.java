import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Sudoku! \nWould you like to play? Type \"play\" to start:");
		System.out.println("Type \"tutorial\" if you would like a tutorial:");
		String ans = scan.nextLine();
		boolean intro = false;
		while (!intro) {
			if (ans.toUpperCase().equals("TUTORIAL")) {
				System.out.println("Sudoku is a game where each number 1 - 9 must not repeat within the same row or column. A 3x3 cell should be filled out with numbers 1 - 9 and each row and column must have the numbers 1 - 9, with no numbers breaking the rule mentioned above. First type the row number you want, then a comma following with the column number you want. The last number you type of the three numbers you input per turn is the value you want to insert into the box.\nNow please type \"play\"");
				ans = scan.nextLine();
			}
			if (!ans.toUpperCase().equals("PLAY")) {
				System.out.println("Please type \"play\" or \"tutorial\":");
				ans = scan.nextLine();
			}
			if (ans.toUpperCase().equals("PLAY")) {
				intro = true;
			}
		}

		System.out.println("Select a puzzle level: 1 (Easy), 2 (Hard), 3 (Impossible). Remember that any number beyond 3 will default to the Easy difficulty.");
		int level = scan.nextInt();
		

		SudokuBoard board = new SudokuBoard(level);

		while (!board.isSolved() && !board.gameOver()) {
			board.displayBoard();
			System.out.println("Enter your move in the format: row,column-value");
			String input = scan.nextLine();

			//divides the given string called input into 3 different segments and stores them in an array. then the ints are parsed to get row, column, and value.
			if (input.matches("\\d,\\d-\\d")) {
				String[] parts = input.split("[-,]");
				int row = Integer.parseInt(parts[0]) - 1;
				int col = Integer.parseInt(parts[1]) - 1; 
				int val = Integer.parseInt(parts[2]);

				if (row >= 0 && row < 9 && col >= 0 && col < 9 && val >= 1 && val <= 9) {
					board.inputNum(row, col, val);
				} else {
					// invalid format asks user for new input
					System.out.println("Invalid input. Rows and columns must be between 1-9. Value must be between 1-9.");
				}
			} else {
				// invalid format asks user for new input
				System.out.println("Invalid input format. Please use the format: [row,column] - value");
			}
            // after game ends, explanation to player
			if (board.gameOver()) {
				System.out.println("Game over! You made too many mistakes.");
				break;
			}
		}
        // after game is solved, explanation to player
		if (board.isSolved()) {
			System.out.println("Congratulations! You solved the Sudoku puzzle.");
		}
	}
}
