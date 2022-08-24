package com.lego.racers.file.hzb;

import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryProperty;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.hzb.object.Cannon;
import com.lego.racers.file.hzb.object.Dumper;
import com.lego.racers.file.hzb.object.Flow;
import com.lego.racers.file.hzb.object.Hazards;
import com.lego.racers.file.hzb.object.Laser;
import com.lego.racers.file.hzb.object.Obstacle;
import com.lego.racers.file.hzb.object.Puller;
import com.lego.racers.file.hzb.object.Swinger;
import com.lego.racers.file.hzb.object.Water;
import com.lego.racers.file.hzb.object.X28;
import com.lego.racers.file.hzb.object.X29;
import com.lego.racers.file.hzb.object.X2A;
import com.lego.racers.file.hzb.object.X2B;
import com.lego.racers.file.hzb.object.X2C;
import com.lego.racers.file.hzb.object.X2D;
import com.lego.racers.file.hzb.object.X2E;
import com.lego.racers.file.hzb.object.X2F;
import com.lego.racers.file.hzb.object.X30;
import com.lego.racers.file.hzb.object.X32;
import com.lego.racers.file.hzb.object.PositionAndInteger;
import com.lego.racers.file.hzb.object.X3F;
import com.lego.racers.file.hzb.object.X49;

import java.util.ArrayList;
import java.util.List;

public class HZBFile{

	public static final byte PROPERTY_HAZARDS = 0x27;

	public static final byte PROPERTY_CANNON = 0x33;
	public static final byte PROPERTY_OBSTACLE = 0x34;
	public static final byte PROPERTY_WATER = 0x36;
	public static final byte PROPERTY_FLOW = 0x3D;
	public static final byte PROPERTY_SWINGER = 0x3E;
	public static final byte PROPERTY_LASER = 0x40;
	public static final byte PROPERTY_DUMPER = 0x43;
	public static final byte PROPERTY_PULLER = 0x48;

	private Hazards hazards;

	public static HZBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		HZBFile hzb = new HZBFile();
		for(BinaryProperty prop : bin.getProperties()){
			if(prop.getKey().getToken()==HZBFile.PROPERTY_HAZARDS){
				HZBFile.fromHazards(hzb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		return hzb;
	}

	private static void fromHazards(HZBFile file,BinaryObject obj){
		Hazards hazards = new Hazards();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==0x28){
				hazards.add(new X28());
				continue;
			}
			if(prop.getKey().getToken()==0x29){
				hazards.add(new X29());
				continue;
			}
			if(prop.getKey().getToken()==0x2A){
				hazards.add(new X2A());
				continue;
			}
			if(prop.getKey().getToken()==0x2B){
				hazards.add(new X2B());
				continue;
			}
			if(prop.getKey().getToken()==0x2C){
				hazards.add(new X2C());
				continue;
			}
			if(prop.getKey().getToken()==0x2D){
				hazards.add(new X2D());
				continue;
			}
			if(prop.getKey().getToken()==0x2E){
				hazards.add(new X2E());
				continue;
			}
			if(prop.getKey().getToken()==0x2F){
				hazards.add(new X2F());
				continue;
			}
			if(prop.getKey().getToken()==0x30){
				hazards.add(new X30());
				continue;
			}
			if(prop.getKey().getToken()==0x32){
				hazards.add(new X32());
				continue;
			}
			if(prop.getKey().getToken()==HZBFile.PROPERTY_CANNON){
				HZBFile.fromCannon(hazards,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==HZBFile.PROPERTY_OBSTACLE){
				HZBFile.fromObstacle(hazards,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==HZBFile.PROPERTY_WATER){
				Water water = new Water();
				water.setName(((BinaryString) prop.getValues().get(0)).getString());
				water.setF1(((BinaryFloat) prop.getValues().get(1)).getFloat());
				water.setF2(((BinaryFloat) prop.getValues().get(2)).getFloat());
				hazards.add(water);
				continue;
			}
			if(prop.getKey().getToken()==HZBFile.PROPERTY_FLOW){
				HZBFile.fromFlow(hazards,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==HZBFile.PROPERTY_SWINGER){
				HZBFile.fromSwinger(hazards,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==0x3F){
				hazards.add(new X3F());
				continue;
			}
			if(prop.getKey().getToken()==HZBFile.PROPERTY_LASER){
				HZBFile.fromLaser(hazards,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==HZBFile.PROPERTY_DUMPER){
				HZBFile.fromDumper(hazards,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==HZBFile.PROPERTY_PULLER){
				HZBFile.fromPuller(hazards,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==0x49){
				hazards.add(new X49());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.hazards = hazards;
	}

	private static void fromCannon(Hazards hazards,BinaryObject obj){
		Cannon cannon = new Cannon();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==0x37){
				Position position = new Position();
				position.setX(((BinaryFloat)prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat)prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat)prop.getValues().get(2)).getFloat());
				cannon.setP1(position);
				continue;
			}
			if(prop.getKey().getToken()==0x38){
				Position position = new Position();
				position.setX(((BinaryFloat)prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat)prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat)prop.getValues().get(2)).getFloat());
				cannon.setP2(position);
				continue;
			}
			if(prop.getKey().getToken()==0x39){
				Position position = new Position();
				position.setX(((BinaryFloat)prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat)prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat)prop.getValues().get(2)).getFloat());
				cannon.setP3(position);
				continue;
			}
			if(prop.getKey().getToken()==0x3A){
				BinaryFloat f = (BinaryFloat) prop.getValues().get(0);
				cannon.setF1(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==0x3B){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				cannon.setI1(integer.getIntegerSigned());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		hazards.add(cannon);
	}

	private static void fromObstacle(Hazards hazards,BinaryObject obj){
		Obstacle obstacle = new Obstacle();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==0x3B){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				obstacle.setX3B(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==0x41){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				obstacle.setX41(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==0x42){
				if(obstacle.getX42()==null){
					obstacle.setX42(new ArrayList<>());
				}
				BinaryString string = (BinaryString) prop.getValues().get(0);
				obstacle.getX42().add(string.getString());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		hazards.add(obstacle);
	}

	private static void fromFlow(Hazards hazards,BinaryObject obj){
		Flow flow = new Flow();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==0x33){
				HZBFile.fromFlowCannon(flow,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==0x37){
				HZBFile.fromX37(flow,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==0x38){
				HZBFile.fromX38(flow,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		hazards.add(flow);
	}

	private static void fromFlowCannon(Flow flow,BinaryObject object){
		Cannon cannon = new Cannon();
		for(BinaryProperty prop : object.getProperties()){
			if(prop.getKey().getToken()==0x37){
				Position position = new Position();
				position.setX(((BinaryFloat)prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat)prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat)prop.getValues().get(2)).getFloat());
				cannon.setP1(position);
				continue;
			}
			if(prop.getKey().getToken()==0x3A){
				BinaryFloat f = (BinaryFloat) prop.getValues().get(0);
				cannon.setF1(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==0x3B){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				cannon.setI1(integer.getIntegerSigned());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		flow.setX33(cannon);
	}

	private static void fromX37(Flow flow,BinaryObject object){
		List<PositionAndInteger> positionAndIntegers = new ArrayList<>();
		for(int i=0;i<object.getTokens().size();i+=4){
			PositionAndInteger positionAndInteger = new PositionAndInteger();
			Position position = new Position();
			position.setX(((BinaryFloat)object.getTokens().get(i)).getFloat());
			position.setY(((BinaryFloat)object.getTokens().get(i+1)).getFloat());
			position.setZ(((BinaryFloat)object.getTokens().get(i+2)).getFloat());
			positionAndInteger.setPosition(position);
			positionAndInteger.setI1(((BinaryIntegerSigned)object.getTokens().get(i+3)).getIntegerSigned());
			positionAndIntegers.add(positionAndInteger);
		}
		flow.setX37(positionAndIntegers);
	}

	private static void fromX38(Flow flow,BinaryObject object){
		List<PositionAndInteger> positionAndIntegers = new ArrayList<>();
		for(int i=0;i<object.getTokens().size();i+=4){
			PositionAndInteger positionAndInteger = new PositionAndInteger();
			Position position = new Position();
			position.setX(((BinaryFloat)object.getTokens().get(i)).getFloat());
			position.setY(((BinaryFloat)object.getTokens().get(i+1)).getFloat());
			position.setZ(((BinaryFloat)object.getTokens().get(i+2)).getFloat());
			positionAndInteger.setPosition(position);
			positionAndInteger.setI1(((BinaryIntegerSigned)object.getTokens().get(i+3)).getIntegerSigned());
			positionAndIntegers.add(positionAndInteger);
		}
		flow.setX38(positionAndIntegers);
	}

	private static void fromSwinger(Hazards hazards,BinaryObject obj){
		Swinger swinger = new Swinger();
		swinger.setS1(((BinaryString) obj.getTokens().get(0)).getString());
		swinger.setI1(((BinaryIntegerSigned) obj.getTokens().get(1)).getIntegerSigned());
		BinaryList list = (BinaryList) obj.getTokens().get(2);
		swinger.setF1(((BinaryFloat) list.getTokens().get(0)).getFloat());
		swinger.setF2(((BinaryFloat) list.getTokens().get(1)).getFloat());
		swinger.setF3(((BinaryFloat) list.getTokens().get(2)).getFloat());
		swinger.setF4(((BinaryFloat) list.getTokens().get(3)).getFloat());
		hazards.add(swinger);
	}

	private static void fromDumper(Hazards hazards,BinaryObject obj){
		Dumper dumper = new Dumper();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==0x3B){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				dumper.setX3B(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==0x42){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				dumper.setX42(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==0x44){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				dumper.setX44(integer.getIntegerSigned());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		hazards.add(dumper);
	}

	private static void fromPuller(Hazards hazards,BinaryObject obj){
		Puller puller = new Puller();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==0x3B){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				puller.setI1(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==0x42){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				BinaryFloat f1 = (BinaryFloat) prop.getValues().get(1);
				BinaryFloat f2 = (BinaryFloat) prop.getValues().get(2);
				BinaryFloat f3 = (BinaryFloat) prop.getValues().get(3);
				puller.setS1(string.getString());
				puller.setF1(f1.getFloat());
				puller.setF2(f2.getFloat());
				puller.setF3(f3.getFloat());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		hazards.add(puller);
	}

	private static void fromLaser(Hazards hazards,BinaryObject obj){
		Laser laser = new Laser();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==0x37){
				Position position = new Position();
				position.setX(((BinaryFloat)prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat)prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat)prop.getValues().get(2)).getFloat());
				laser.setPosition(position);
				continue;
			}
			if(prop.getKey().getToken()==0x3B){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				laser.setI1(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==0x42){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				laser.setName(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==0x46){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				laser.setI2(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==0x47){
				BinaryFloat f1 = (BinaryFloat) prop.getValues().get(0);
				BinaryFloat f2 = (BinaryFloat) prop.getValues().get(1);
				BinaryFloat f3 = (BinaryFloat) prop.getValues().get(2);
				laser.setF1(f1.getFloat());
				laser.setF1(f2.getFloat());
				laser.setF1(f3.getFloat());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		hazards.add(laser);
	}

	@Override
	public String toString() {
		return "HZBFile{" +
				"hazards=" + hazards +
				'}';
	}

}
