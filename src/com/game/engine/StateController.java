package com.game.engine;

/**
 * Controller implementation for game menus. When context change an state, 
 * a new state controller is loaded and manager are reseted.
 */
public abstract class StateController extends BaseEntity implements State, Controller{

	private static final long serialVersionUID = -585896828901115470L;

	protected Engine engine;
	protected Context context;
	
	protected StateController(Engine engine, Context context) {
		this.engine = engine;
		this.context = context;
		this.engine.getLogicManager().attachObserver(this);
	}

	public abstract void logicUpdate();

	public abstract void actionUpdate(ActionEvent event);

	public abstract void initialize();
	
	public void execute() {
		this.engine.getActionManager().attachObserver(this);
		this.engine.getLogicManager().attachObserver(this);
		
		initialize();
	}
	
	public abstract void goNext(Context context);
}
