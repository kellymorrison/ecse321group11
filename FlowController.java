package LightRacerPackage;

public class FlowController {

	public static void main(String[] args) {

		private static final int NUMBER_OF_GAMES = 5;
		private boolean endProgram = false;
	
		User user1 = new User();
		User user2 = new User();
		Login _Login = new Login(user1, user2);
		GameSettings _GameSettings = new GameSettings(user1, user2);
		GameEngine _GameEngine = new GameEngine(user1, user2, GameSettings);
		
		while(!endProgram) {

			//reset game number to deal with logout case
			_GameEngine.resetGameNumber();
				
			//display will call createAccount if needed
			while(!user1.isVerified() || !user2.isVerified() ) {
				login.display();
			}
			
			//loop to play number of games required
			while(_GameSettings.getGameNumber() < NUMBER_OF_GAMES) {
				
				//if first game in series dislay logout option
				if(_GameSettings.getGameNumber() == 0) {
					_GameSettings.logoutOptionOn();
				}
				else { _GameSettings.logoutOptionOff(); }

				//take input settings until valid inputs have been sent
				while(!_GameSettings.validSettings() ) {
					_GameSettings.display();
				}

				//if logout has been pressed exit loop to enter users
				if(_GameSettings.wantsToLogout()) { 
					user1.unVerify();
					user2.unVerify();
					break; 
				}

				//play game and replay if tie
				_GameEngine.playGame();
				if(_GameEngine.isTie() ) {
					continue;
				}
				_GameEngine.incrementGameNumber();
			}
			_GameEngine.resetGameNumber();
		}

	}

			