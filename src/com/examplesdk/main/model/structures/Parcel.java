package com.examplesdk.main.model.structures;

import java.io.Serializable;

public class Parcel implements Serializable{

	private static final long serialVersionUID = -8818031021216882948L;
	private Integer material;
	
	public Parcel(Integer material){
		
		this.material = material;
		
	}

	public Integer getMaterial() {
		return material;
	}

	public void setMaterial(Integer material) {
		this.material = material;
	}


}
