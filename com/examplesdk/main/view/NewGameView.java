package com.examplesdk.main.view;

import com.game.engine.ActionEvent;
import com.game.engine.BaseView;
import com.game.engine.Engine;
import com.game.engine.InputEvent;
import com.game.engine.Sprite;
import com.game.engine.Sprite2D;

public class NewGameView extends BaseView{

	private static final long serialVersionUID = 6693516746752154370L;

	private Sprite background;
	
	public NewGameView(Engine engine) {
		super(engine);
	}
	
	public void inputUpdate(InputEvent event) {

	}

	public void actionUpdate(ActionEvent event) {

	}

	public void initialize() {
		background = new Sprite2D(engine, "/assets/background.png");
		engine.getRenderManager().addSprite(background);
	}

	public void render() {

	}

}
