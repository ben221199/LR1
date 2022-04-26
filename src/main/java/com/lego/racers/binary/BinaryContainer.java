package com.lego.racers.binary;

import java.util.ArrayList;
import java.util.List;

public abstract class BinaryContainer extends BinaryToken{

	protected List<BinaryToken> tokens = new ArrayList<>();

	public BinaryContainer(byte token){
		super(token);
	}

	public List<BinaryToken> getTokens(){
		return this.tokens;
	}

}