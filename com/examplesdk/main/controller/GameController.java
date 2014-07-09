package com.examplesdk.main.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.examplesdk.main.model.BoardModel;
import com.examplesdk.main.model.PieceModel;
import com.examplesdk.main.model.PlayerModel;
import com.examplesdk.main.view.BoardView;
import com.examplesdk.main.view.GameGUIView;
import com.examplesdk.main.view.NewGameView;
import com.examplesdk.main.view.PieceView;
import com.game.engine.ActionEvent;
import com.game.engine.Engine;
import com.game.engine.BaseController;
import com.game.engine.SaveGame;

/**
 * Main game controller. This controller initialize and control execution of examplesdk
 * models and views.
 * @author samuelnp
 *
 */
public class GameController extends BaseController{

	private static final long serialVersionUID = -6889993552463885841L;
	
	BoardView boardView;
	NewGameView gameView;
	GameGUIView guiView;
	PieceView pieceView;
	
	BoardModel boardModel;
	
	private List<PlayerModel> players;
	private PlayerModel currentPlayer;
	private PieceView currentPiece;
	private Map<PlayerModel, PieceView> playerPieces;
	
	Boolean loadedGame;
	
	public GameController(Engine engine) {
		super(engine);
		boardView = null;
		pieceView = null;
		gameView = null;
		guiView = null;
		
		boardModel = null;
		
		players = new ArrayList<PlayerModel>();
		currentPlayer = null;
		currentPiece = null;
		playerPieces = new HashMap<PlayerModel, PieceView>();
		loadedGame = false;
	}

	/**
	 * Execute game logicObserver in LogicManager
	 * In this case, call every frame to render board and pieces
	 */
	public void logicUpdate() {
		boardView.render();
		for(PieceView pieceView : playerPieces.values()) {
			pieceView.render();
		}
	}

	/**
	 * Execute action events passed throughout action observers implemented in Controllers
	 * Models and Views
	 */
	public void actionUpdate(ActionEvent event) {
		
		/**
		 * If current action is NEW_TURN change current player
		 */
		if(event.getActionName().equals("NEW_TURN")) {

			playerPieces.get(currentPlayer).focus(false);
			Integer currentPosition = players.indexOf(currentPlayer);
			if(currentPosition >= (players.size() - 1)) {
				
				currentPlayer = players.get(0);
			} else {
			
				currentPlayer = players.get(currentPosition + 1);
				
			}
			playerPieces.get(currentPlayer).focus(true);

		}
		
		/**
		 * If current action is PIECE_CLICKED focus current piece
		 */
		if(event.getActionName().equals("PIECE_CLICKED")) {
			PieceView piece = (PieceView) event.getEntity();

			
			if(playerPieces.get(currentPlayer).getID().equals(piece.getID())) {
				currentPiece = piece;
				currentPiece.focus(true);
			}
		}
		
		/**
		 * If current action is PARCEL_CLICKED move current piece to specified coordinates
		 */
		if(event.getActionName().equals("PARCEL_CLICKED")) {
			Integer[] coords = (Integer[]) event.getArguments();

			if(currentPiece != null) {
				currentPiece.move(coords[0], coords[1]);
				currentPiece = null;
			}
		}
		
		/**
		 * If current action is SAVE_GAME execute PersistenceManager to save board object state
		 */
		if(event.getActionName().equals("SAVE_GAME")) {
			String board = engine.getPersistenceManager().save(boardModel);
			
			SaveGame savegame = new SaveGame();
			savegame.add(board);
			
			String saved = engine.getPersistenceManager().save(savegame, "save", "savegame");
			System.out.println(saved);
		}
	}

	/**
	 * Initialize all models and views
	 */
	public void initialize() {

		//Set Board if it is new game. Else previous board is preloaded
		if(!loadedGame) {
		 boardModel = new BoardModel(engine, 6, 6);
		 boardModel.initialize();
		}
		
		//Set pieces
		PieceModel piece1 = new PieceModel(engine);
		piece1.initialize();
		piece1.setID("PIECE_1");
		
		PieceModel piece2 = new PieceModel(engine);
		piece2.initialize();
		piece2.setID("PIECE_2");
				
		//Set Players
		PlayerModel player1 = new PlayerModel(engine);
		player1.initialize();
		player1.setID("PLAYER_1");
		player1.addPiece(piece1);
		players.add(player1);

		PlayerModel player2 = new PlayerModel(engine);
		player2.initialize();
		player2.addPiece(piece2);
		player2.setID("PLAYER_2");
		players.add(player2);
		
		//Set current player
		currentPlayer = player1;

		
		//Set pieces default positions in Board
		boardModel.getBoard().get(0).get(0).setPiece(piece1);
		boardModel.getBoard().get(5).get(5).setPiece(piece2);
		//Set Views
		gameView = new NewGameView(engine);
		gameView.initialize();
		
		boardView = new BoardView(engine, boardModel);
		boardView.setLeft(100);
		boardView.setTop(100);
		boardView.initialize();
		
		PieceView pieceView1 = new PieceView(engine, boardView, player1, 1);
		pieceView1.setID("PIECE_1_PLAYER_1");
		pieceView1.initialize();

		pieceView1.move(1, 1);

		
		PieceView pieceView2 = new PieceView(engine, boardView, player2, 2);
		pieceView2.setID("PIECE_1_PLAYER_2");
		pieceView2.initialize();

		pieceView2.move(4, 4);

		pieceView1.focus(true);
		
		playerPieces.put(player1, pieceView1);
		playerPieces.put(player2, pieceView2);

		guiView = new GameGUIView(engine);
		guiView.initialize();
	}


	
	public void loadSaveGame() {
		
		String directory = "savegame";
		File dir = new File(directory);

		String[] children = dir.list();
		String filename = children[children.length - 1];
		
		loadedGame = true;
		
		SaveGame savegame = (SaveGame) engine.getPersistenceManager().load(filename, directory);
		List<String> files = savegame.getAll();
		
		boardModel = (BoardModel) engine.getPersistenceManager().load(files.get(0));
		boardModel.setEngine(engine);
		
	}

}
