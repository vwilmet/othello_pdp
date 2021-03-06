package com.model.view;

/**
Interface contenant les variables necessaires au bon fonctionnement de la vue
 * @author 	<ul>
 * 			<li>Vincent Wilmet</li>
 * 			</ul>
 * @version 1.0
 */
public class ViewSettings {

	/////////////////////////////////////////////////////////////////

	
	public static int PIECE_WIDTH = 10;
	public static int PIECE_HEIGHT = 10;

	public static int GAME_FRAME_WIDTH;
	public static int GAME_FRAME_HEIGHT;
	
	public static final int MENU_COMPONENT_VIEW_HEIGHT = 90;
	
	public static int MESSAGE_COMPONENT_VIEW_WIDTH = 0;
	public static final int MESSAGE_COMPONENT_VIEW_HEIGHT = 40;

	public static int INFORMATION_COMPONENT_VIEW_WIDTH = 0;
	public static int INFORMATION_COMPONENT_VIEW_HEIGHT = 0;

	public static int GAMEVIEW_COMPONENT_VIEW_WIDTH = 0;
	public static int GAMEVIEW_COMPONENT_VIEW_HEIGHT = 0;

	public static int STATISTICS_COMPONENT_VIEW_WIDTH = 0;
	
	/////////////////////////////////////////////////////////////////
	
	public static int CHOOSE_BOARD_FRAME_WIDTH;
	public static int CHOOSE_BOARD_FRAME_HEIGHT;
	
	public static int GAMEVIEW_COMPONENT_CHOOSE_VIEW_WIDTH = 0;
	public static int GAMEVIEW_COMPONENT_CHOOSE_VIEW_HEIGHT = 0;

	public static final int SLIDER_COMPONENT_CHOOSE_VIEW_HEIGHT = 40;
	
	public static int BUTTONS_COMPONENT_CHOOSE_VIEW_WIDTH = 0;
	
	/////////////////////////////////////////////////////////////////
	public static final int DRAW_LINE_SIZE = 2;
	
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
	public static final String BACK_BUTTON_DISABLE_IMAGE = "arrow/back_arrow_disable.png";
	public static final int FORWARD_BUTTON_CODE = 202;
	public static final String FORWARD_BUTTON_IMAGE = "arrow/forward_arrow.png";
	public static final String FORWARD_BUTTON_CLIKED_IMAGE = "arrow/forward_arrow_cliked.png";
	public static final String FORWARD_BUTTON_DISABLE_IMAGE = "arrow/forward_arrow_disable.png";
	public static final String BACK_BUTTON_TEXT = "Retour";
	public static final String FORWARD_BUTTON_TEXT = "Suivant";
	
	// reset button
	public static final int RESET_BUTTON_CODE = 203;
	public static final String RESET_BUTTON_IMAGE = "arrow/reset.png";
	public static final String RESET_BUTTON_CLIKED_IMAGE = "arrow/reset_cliked.png";
	public static final String RESET_BUTTON_DISABLE_IMAGE = "arrow/reset_disable.png";
	public static final String RESET_BUTTON_TEXT = "Recommencer";
	
	// help ia button
	public static final int HELP_IA_BUTTON_CODE = 300;
	public static final String HELP_IA_BUTTON_IMAGE = "bonus/help_ia.png";
	public static final String HELP_IA_BUTTON_CLIKED_IMAGE = "bonus/help_ia_cliked.png";
	public static final String HELP_IA_BUTTON_DISABLE_IMAGE = "bonus/help_ia_disable.png";
	public static final String HELP_IA_BUTTON_TEXT = "Aide de l'IA";
	
	// position button
	public static final int POSITION_BUTTON_CODE = 301;
	public static final String POSITION_BUTTON_IMAGE = "bonus/position.png";
	public static final String POSITION_BUTTON_CLIKED_IMAGE = "bonus/position_cliked.png";
	public static final String POSITION_BUTTON_DISABLE_IMAGE = "bonus/position_disable.png";
	public static final String POSITION_BUTTON_TEXT = "Revenir à une position";
	
	// reverse button
	public static final int REVERSE_BUTTON_CODE = 302;
	public static final String REVERSE_BUTTON_IMAGE = "bonus/reverse_player.png";
	public static final String REVERSE_BUTTON_CLIKED_IMAGE = "bonus/reverse_player_cliked.png";
	public static final String REVERSE_BUTTON_DISABLE_IMAGE = "bonus/reverse_player_disable.png";
	public static final String REVERSE_BUTTON_TEXT = "Changer les joueurs";
	
	// path
	public static final String IMAGE_BUTTON_PATH = "./resources/fx/";
	public static final String IMAGE_PIECE_PATH = "./resources/fx/piece/";
	
	public static final String WHITE_PIECE_IMG = "white_piece.png";
	public static final String IA_ADVISED_PIECE_IMG = "ia_advised_piece.png";
	public static final String BLACK_PIECE_IMG = "black_piece.png";
	public static final String POSSIBLE_PIECE_IMG = "possible_position.png";
	
	///////////////////////////////////////////////////////////////////////////
}