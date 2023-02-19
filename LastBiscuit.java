/* A program "The Game of Last Biscuit"
 * CS-110
 * Assignment Due Wednesday by 11:00
 */
import java.util.Scanner;

public class LastBiscuit{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
	
		int barrelOneBiscuitNumber;
		int barrelTwoBiscuitNumber;
		int skipChancesPlayer1;
		int skipChancesPlayer2;
		int playerTurn = 1;
		int biscuitNumberInput = 0;
		
		final int BARREL_1 = 6;
		final int BARREL_2 = 8;
		
		String barrelChoiceInput = "";
		boolean isLoopRepeating = true;
		
		String gameStartMessage;
		
		String notLegalNumberMessage = "Sorry, that's not a legal number " 
							+ "of biscuits for that/those barrel(s)";
		String skipUsedMessage = "Sorry you've used your skip.";
		String chooseBarrelMessage = "Choose a barrel: " 
								+ "barrel1 (one), barrel2 (two), " 
								+ "or both (both), " 
								+ "or skip turn (skip)? ";
		String biscuitsLeftMessage = "Biscuits Left - Barrel ";
		String playerTurnMessage = "Player Turn: ";
		String enterBiscuitNumberMessage = "How many biscuits are you taking? ";
		String inputIntegerMessage = "Please input an integer: ";
		String winnerMessage = "Winner is player ";
		
		final String BARREL_ONE_BUTTON = "one";
		final String BARREL_TWO_BUTTON = "two";
		final String BARREL_BOTH_BUTTON = "both";
		final String SKIP_TURN_BUTTON = "skip";
		
		barrelOneBiscuitNumber = BARREL_1;
		barrelTwoBiscuitNumber = BARREL_2;
		skipChancesPlayer1 = 1;
		skipChancesPlayer2 = 1;
		
		/*
		 * This is the main loop.
		 * The main loop restarts every time till the barrels are empty.
		 */
		outer: do { 
			if (isLoopRepeating){
				// The next turn message.
				gameStartMessage = String.format("%s1: %d%n%s2: %d%n%s%d", 
					biscuitsLeftMessage, barrelOneBiscuitNumber, 
					biscuitsLeftMessage, barrelTwoBiscuitNumber, 
					playerTurnMessage, playerTurn);
				System.out.println(gameStartMessage);
			}
			
			/*
			 * This loop checks if the barrel choice input is legal.
			 * In case the user skips its turn it restarts the outer loop.
			 */
			inner: while (true){
				System.out.print(chooseBarrelMessage);
				barrelChoiceInput = in.nextLine();
				
				switch (barrelChoiceInput){
				case BARREL_ONE_BUTTON:
					break inner;
				case BARREL_TWO_BUTTON:
					break inner;
				case BARREL_BOTH_BUTTON:
					break inner;
				case SKIP_TURN_BUTTON:
					isLoopRepeating = true;
					
					if (playerTurn == 1){
						if (skipChancesPlayer1 > 0){
							skipChancesPlayer1--;
							playerTurn = 2;
							continue outer;
						} else {
							System.out.println(skipUsedMessage);
							isLoopRepeating = false;
							continue outer;
						}
					} else {
						if (skipChancesPlayer2 > 0){
							skipChancesPlayer2--;
							playerTurn = 1;
							continue outer;
						} else {
							System.out.println(skipUsedMessage);
							isLoopRepeating = false;
							continue outer;
						}
					}
					default:
						continue inner;
				}					
			}

			System.out.print(enterBiscuitNumberMessage);
			
			// Checks the integer input for legality.
			while (!in.hasNextInt()){
				System.out.print(inputIntegerMessage);
				in.next();
			}
			biscuitNumberInput = in.nextInt();
			
			in.nextLine(); // Consumes newline left-over
			
			/*
			 * The if and switch statements check if the biscuit number input is legal.
			 * If yes, it performs the specified operation 
			 * with the number of biscuits in the certain barrel.
			 */
			if (biscuitNumberInput < 1){
				isLoopRepeating = false;
				System.out.println(notLegalNumberMessage);
				continue;
			} else {
				
				switch (barrelChoiceInput){
				case BARREL_ONE_BUTTON:
					if (biscuitNumberInput > barrelOneBiscuitNumber){
						isLoopRepeating = false;
						System.out.println(notLegalNumberMessage);
						continue;
					} else {
						barrelOneBiscuitNumber -= biscuitNumberInput;
						break;
					}
				case BARREL_TWO_BUTTON:
					if (biscuitNumberInput > barrelTwoBiscuitNumber){
						isLoopRepeating = false;
						System.out.println(notLegalNumberMessage);
						continue;
					} else {
						barrelTwoBiscuitNumber -= biscuitNumberInput;
						break;
					}
				case BARREL_BOTH_BUTTON:
					if (biscuitNumberInput > barrelOneBiscuitNumber 
						&& biscuitNumberInput > barrelTwoBiscuitNumber){
						isLoopRepeating = false;
						System.out.println(notLegalNumberMessage);
						continue;
					} else {
						barrelOneBiscuitNumber -= biscuitNumberInput;
						barrelTwoBiscuitNumber -= biscuitNumberInput;
						break;
					}
				default:
					break;
				}
			}
			
			playerTurn = swapPlayerTurn(playerTurn);

			barrelChoiceInput = "";
			biscuitNumberInput = 0;
			isLoopRepeating = true;

	} while (barrelOneBiscuitNumber != 0 || barrelTwoBiscuitNumber != 0);
	
		playerTurn = swapPlayerTurn(playerTurn);
		
		// The final message.
		gameStartMessage = String.format("%s1: %d%n%s2: %d%n%s%d", 
			biscuitsLeftMessage, barrelOneBiscuitNumber, 
			biscuitsLeftMessage, barrelTwoBiscuitNumber, 
			winnerMessage, playerTurn);	
		System.out.println(gameStartMessage);
		
	}
	
	static int swapPlayerTurn(int playerTurn){
		if (playerTurn == 1){
				return 2;
		} else {
				return 1;
		}
	}
	
}
