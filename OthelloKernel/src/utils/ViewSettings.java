package utils;

/**
 * 
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public interface ViewSettings {
	public static final int sizeX = 740;
	public static final int sizeY = 660;

	// play pause button
	public static final int PLAY_BUTTON_CODE = 100;
	public static final String PLAY_BUTTON_IMAGE = "control/play.png";
	public static final String PLAY_BUTTON_CLIKED_IMAGE = "control/play_cliked.png";
	public static final String PAUSE_BUTTON_IMAGE = "control/pause.png";
	public static final String PAUSE_BUTTON_CLIKED_IMAGE = "control/pause_cliked.png";
	public static final String PLAY_BUTTON_TEXT = "Jouer/Pause";

	// forward back button
	public static final int BACK_BUTTON_CODE = 201;
	public static final String BACK_BUTTON_IMAGE = "arrow/back_arrow.png";
	public static final String BACK_BUTTON_CLIKED_IMAGE = "arrow/back_arrow_cliked.png";
	public static final int FORWARD_BUTTON_CODE = 202;
	public static final String FORWARD_BUTTON_IMAGE = "arrow/forward_arrow.png";
	public static final String FORWARD_BUTTON_CLIKED_IMAGE = "arrow/forward_arrow_cliked.png";
	public static final String BACK_BUTTON_TEXT = "Retour";
	public static final String FORWARD_BUTTON_TEXT = "Suivant";

	// reset button
	public static final int RESET_BUTTON_CODE = 203;
	public static final String RESET_BUTTON_IMAGE = "arrow/reset.png";
	public static final String RESET_BUTTON_CLIKED_IMAGE = "arrow/reset_cliked.png";
	public static final String RESET_BUTTON_TEXT = "Recommencer";

	// help ia button
	public static final int HELP_IA_BUTTON_CODE = 300;
	public static final String HELP_IA_BUTTON_IMAGE = "bonus/help_ia.png";
	public static final String HELP_IA_BUTTON_CLIKED_IMAGE = "bonus/help_ia_cliked.png";
	public static final String HELP_IA_BUTTON_TEXT = "Aide de l'IA";

	// position button
	public static final int POSITION_BUTTON_CODE = 301;
	public static final String POSITION_BUTTON_IMAGE = "bonus/position.png";
	public static final String POSITION_BUTTON_CLIKED_IMAGE = "bonus/position_cliked.png";
	public static final String POSITION_BUTTON_TEXT = "Revenir à une position";

	// reverse button
	public static final int REVERSE_BUTTON_CODE = 302;
	public static final String REVERSE_BUTTON_IMAGE = "bonus/reverse_player.png";
	public static final String REVERSE_BUTTON_CLIKED_IMAGE = "bonus/reverse_player_cliked.png";
	public static final String REVERSE_BUTTON_TEXT = "Changer les joueurs";

	// path
	public static final String IMAGE_BUTTON_PATH = "./resources/fx/";

}
