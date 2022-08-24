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

	public List<BinaryProperty> getProperties(){
		List<BinaryProperty> properties = new ArrayList<>();
		BinaryProperty property = null;
		for(BinaryToken token : this.tokens){
			if(token.isStructInstance()){
				BinaryStructInstance structInstance = (BinaryStructInstance) token;
				for(BinaryToken subToken : structInstance.getTokens()){
					if(subToken.isKey()){
						property = new BinaryProperty();
						property.setStructInstance(true);
						property.getTokens().add(subToken);
						properties.add(property);
						continue;
					}
					if(property!=null){
						property.getTokens().add(subToken);
					}
				}
				continue;
			}
			if(token.isKey()){
				property = new BinaryProperty();
				property.getTokens().add(token);
				properties.add(property);
				continue;
			}
			if(property!=null){
				property.getTokens().add(token);
			}
		}
		return properties;
	}

	public void addProperty(BinaryProperty property){
		this.tokens.add(property.getKey());
		this.tokens.addAll(property.getValues());
	}

}