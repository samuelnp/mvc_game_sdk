package com.examplesdk.main.model.structures;

import java.io.Serializable;
import java.util.*;

import com.examplesdk.main.model.PieceModel;

public class ParcelStack implements Serializable {

	private static final long serialVersionUID = -4360092985116015290L;
	private List<Parcel> parcelStack;
	private Boolean occuped;
	private PieceModel piece;
	
	public ParcelStack(List<Parcel> parcels){
		occuped = false;
		piece = null;
		parcelStack = parcels;		
	}

	// ----------------------------------------------------------------
	
	public List<Parcel> getParcelStack() {
		return parcelStack;
	}

	public void setParcelStack(List<Parcel> parcelStack) {
		this.parcelStack = parcelStack;
	}

	public Boolean getOccuped() {
		return occuped;
	}

	public void setOccuped(Boolean occuped) {
		this.occuped = occuped;
	}

	public PieceModel getPiece() {
		return piece;
	}

	public void setPiece(PieceModel piece) {
		this.piece = piece;
	}
		
	public Integer getStackHeight(){
		return parcelStack.size();
	}
	
	public void addParcel(Parcel parcel, Integer position){
		parcelStack.add(position, parcel);
	}
	
	public void removeParcel(Integer position){
		parcelStack.remove(position);
	}
	
	public void addParcelTop(Parcel parcel){
		parcelStack.add(parcel);
	}
	
	public void removeParcelTop(){
		parcelStack.remove(parcelStack.size()-1);
	}
	
	
}
