package com.lego.racers.file.pwb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.pwb.object.ColoredPowerup;
import com.lego.racers.file.pwb.object.ColoredPowerups;
import com.lego.racers.file.pwb.object.Position;
import com.lego.racers.file.pwb.object.Powerup;
import com.lego.racers.file.pwb.object.WhitePowerup;
import com.lego.racers.file.pwb.object.WhitePowerups;

public class PWBFile{

	public static final byte BLOCK_POWERUPS_COLORED = 0x27;
	public static final byte BLOCK_POWERUPS_WHITE = 0x2F;

	public static final byte PROPERTY_POWERUP_COLORED = 0x27;
	public static final byte PROPERTY_POSITION = 0x28;
	public static final byte PROPERTY_COLOR_RED = ColoredPowerup.COLOR_RED;
	public static final byte PROPERTY_COLOR_YELLOW = ColoredPowerup.COLOR_YELLOW;
	public static final byte PROPERTY_COLOR_BLUE = ColoredPowerup.COLOR_BLUE;
	public static final byte PROPERTY_COLOR_GREEN = ColoredPowerup.COLOR_GREEN;
	public static final byte PROPERTY_POWERUP_WHITE = 0x2F;

	private ColoredPowerups coloredPowerups;
	private WhitePowerups whitePowerups;

	public ColoredPowerups ColoredPowerups(){
		return this.coloredPowerups;
	}

	public void setColoredPowerups(ColoredPowerups coloredPowerups){
		this.coloredPowerups = coloredPowerups;
	}

	public WhitePowerups getWhitePowerups(){
		return this.whitePowerups;
	}

	public void setWhitePowerups(WhitePowerups whitePowerups){
		this.whitePowerups = whitePowerups;
	}

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		boolean struct23 = false;
		if(this.coloredPowerups!=null){
			bin.getTokens().add(new BinaryToken(PWBFile.BLOCK_POWERUPS_COLORED));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.coloredPowerups.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			for(int i=0;i<this.coloredPowerups.size();i++){
				obj.getTokens().add(new BinaryToken(PWBFile.PROPERTY_POWERUP_COLORED));
				if(i==0 && !struct23){
					obj.getTokens().add(new BinaryStruct((byte) 0x17,(byte)4,new byte[]{0x28,3,3,3}));
					struct23 = true;
				}
				BinaryObject powerupObj = new BinaryObject();
				ColoredPowerup powerup = this.coloredPowerups.get(i);
				BinaryStructInstance position = new BinaryStructInstance((byte) 0x17);
				position.getTokens().add(new BinaryFloat(powerup.getPosition().getX()));
				position.getTokens().add(new BinaryFloat(powerup.getPosition().getY()));
				position.getTokens().add(new BinaryFloat(powerup.getPosition().getZ()));
				powerupObj.getTokens().add(position);
				powerupObj.getTokens().add(new BinaryToken(powerup.getColor()));
				obj.getTokens().add(powerupObj);
			}
			bin.getTokens().add(obj);
		}
		if(this.whitePowerups!=null){
			bin.getTokens().add(new BinaryToken(PWBFile.BLOCK_POWERUPS_WHITE));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.whitePowerups.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			for(int i=0;i<this.whitePowerups.size();i++){
				obj.getTokens().add(new BinaryToken(PWBFile.PROPERTY_POWERUP_WHITE));
				if(i==0 && !struct23){
					obj.getTokens().add(new BinaryStruct((byte) 0x17,(byte)4,new byte[]{0x28,3,3,3}));
					struct23 = true;
				}
				BinaryObject powerupObj = new BinaryObject();
				WhitePowerup powerup = this.whitePowerups.get(i);
				BinaryStructInstance position = new BinaryStructInstance((byte) 0x17);
				position.getTokens().add(new BinaryFloat(powerup.getPosition().getX()));
				position.getTokens().add(new BinaryFloat(powerup.getPosition().getY()));
				position.getTokens().add(new BinaryFloat(powerup.getPosition().getZ()));
				powerupObj.getTokens().add(position);
				obj.getTokens().add(powerupObj);
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static PWBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		PWBFile pwb = new PWBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==PWBFile.BLOCK_POWERUPS_COLORED){
				PWBFile.initColoredPowerups(pwb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==PWBFile.BLOCK_POWERUPS_WHITE){
				PWBFile.initWhitePowerups(pwb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return pwb;
	}

	private static void initColoredPowerups(PWBFile file,BinaryObject obj){
		ColoredPowerups powerups = new ColoredPowerups();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==PWBFile.PROPERTY_POWERUP_COLORED){
				int j = 1;
				while(obj.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				PWBFile.initColoredPowerup(powerups,(BinaryObject) obj.getTokens().get(i+j));
			}
		}
		file.coloredPowerups = powerups;
	}

	private static void initColoredPowerup(ColoredPowerups powerups,BinaryObject obj){
		ColoredPowerup powerup = new ColoredPowerup();

		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryStructInstance){
				initPosition(powerup,(BinaryStructInstance) token);
			}
		}

		for(BinaryToken token : obj.getTokens()){
			if(token.getToken()==PWBFile.PROPERTY_COLOR_RED){
				powerup.setColor(ColoredPowerup.COLOR_RED);
				break;
			}
			if(token.getToken()==PWBFile.PROPERTY_COLOR_YELLOW){
				powerup.setColor(ColoredPowerup.COLOR_YELLOW);
				break;
			}
			if(token.getToken()==PWBFile.PROPERTY_COLOR_BLUE){
				powerup.setColor(ColoredPowerup.COLOR_BLUE);
				break;
			}
			if(token.getToken()==PWBFile.PROPERTY_COLOR_GREEN){
				powerup.setColor(ColoredPowerup.COLOR_GREEN);
				break;
			}
		}

		powerups.add(powerup);
	}

	private static void initWhitePowerups(PWBFile file,BinaryObject obj){
		WhitePowerups powerups = new WhitePowerups();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==PWBFile.PROPERTY_POWERUP_WHITE){
				int j = 1;
				while(obj.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				PWBFile.initWhitePowerup(powerups,(BinaryObject) obj.getTokens().get(i+j));
			}
		}
		file.whitePowerups = powerups;
	}

	private static void initWhitePowerup(WhitePowerups powerups,BinaryObject obj){
		WhitePowerup powerup = new WhitePowerup();

		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryStructInstance){
				initPosition(powerup,(BinaryStructInstance) token);
			}
		}

		powerups.add(powerup);
	}

	private static void initPosition(Powerup powerup,BinaryStructInstance structInstance){
		if(structInstance.getTokens().size()>0 && structInstance.getTokens().get(0).getToken()==PWBFile.PROPERTY_POSITION){
			Position position = new Position();
			position.setX(((BinaryFloat)structInstance.getTokens().get(1)).getFloat());
			position.setY(((BinaryFloat)structInstance.getTokens().get(2)).getFloat());
			position.setZ(((BinaryFloat)structInstance.getTokens().get(3)).getFloat());
			powerup.setPosition(position);
		}
	}

	@Override
	public String toString() {
		return "PWBFile{" +
				"coloredPowerups=" + coloredPowerups +
				", whitePowerups=" + whitePowerups +
				'}';
	}

}