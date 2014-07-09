package com.game.engine;

/**
 * Class that encapsulate information for Actions. These are used to modify Controllers, Models, Views and Sprites
 *
 */
public class ActionEvent {

	private Entity entity;
	private String actionName;
	private Object arguments;
	
	public ActionEvent(Entity entity, String actionName) {
		this.setEntity(entity);
		this.setActionName(actionName);
	}
	
	public ActionEvent(Entity entity, String actionName, Object arguments) {
		this(entity, actionName);
		this.arguments = arguments;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName.toUpperCase();
	}
	
	public Object getArguments() {
		return arguments;
	}
	
	public void setArguments(Object arguments) {
		this.arguments = arguments;
	}
	
	
}
