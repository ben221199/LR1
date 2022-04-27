package com.lego.racers.binary;

import java.util.ArrayList;
import java.util.List;

public class BinaryProperty{

	private List<BinaryToken> tokens = new ArrayList<>();

	public List<BinaryToken> getTokens(){
		return this.tokens;
	}

	public BinaryToken getKey(){
		if(!this.tokens.isEmpty()){
			return this.tokens.get(0);
		}
		return null;
	}

	public void setKey(BinaryToken key){
		if(!this.tokens.isEmpty()){
			this.tokens.set(0,key);
		}else{
			this.tokens.add(key);
		}
	}

	public List<BinaryToken> getValues(){
		return this.tokens.subList(1,this.tokens.size());
	}

	public void setValues(List<BinaryToken> values){
		BinaryToken key = this.getKey();
		this.tokens.clear();
		this.setKey(key);
		this.tokens.addAll(values);
	}

	@Override
	public String toString() {
		return "BinaryProperty{" +
				"key=" + this.getKey() +
				", values=" + this.getValues() +
				'}';
	}

}