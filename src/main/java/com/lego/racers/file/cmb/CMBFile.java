package com.lego.racers.file.cmb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryProperty;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.cmb.object.Chassis;
import com.lego.racers.file.cmb.object.ChassisList;
import com.lego.racers.file.cmb.object.Wheels;
import com.lego.racers.file.cmb.object.X30;
import com.lego.racers.file.cmb.object.X31;
import com.lego.racers.file.jam.JAMNodeFile;

import java.util.List;
import java.util.Map;

public class CMBFile extends JAMNodeFile{

	public static final byte PROPERTY_CHASSIS = 0x27;
	public static final byte PROPERTY_WHEELS = 0x28;
	public static final byte PROPERTY_x2A = 0x2A;
	public static final byte PROPERTY_SEAT = 0x2B;
	public static final byte PROPERTY_x2C = 0x2C;
	public static final byte PROPERTY_x2D = 0x2D;
	public static final byte PROPERTY_x2E = 0x2E;
	public static final byte PROPERTY_x2F = 0x2F;
	public static final byte PROPERTY_x30 = 0x30;
	public static final byte PROPERTY_x31 = 0x31;
	public static final byte PROPERTY_x32 = 0x32;
	public static final byte PROPERTY_x33 = 0x33;
	public static final byte PROPERTY_MATERIALS = 0x34;
	public static final byte PROPERTY_TEXTURES = 0x35;
	public static final byte PROPERTY__GDB = 0x36;
	public static final byte PROPERTY_SKELETONS = 0x37;
	public static final byte PROPERTY_ANIMATIONS = 0x38;
	public static final byte PROPERTY_BRICKS = 0x39;
	public static final byte PROPERTY_x3A = 0x3A;
	public static final byte PROPERTY_x3B = 0x3B;
	public static final byte PROPERTY_x3C = 0x3C;

	private ChassisList chassisList;

	public ChassisList getChassisList(){
		return this.chassisList;
	}

	public void setChassisList(ChassisList chassisList){
		this.chassisList = chassisList;
	}

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this.chassisList!=null){
			BinaryProperty chassisListProp = new BinaryProperty();
			chassisListProp.setKey(new BinaryToken(CMBFile.PROPERTY_CHASSIS));
			BinaryArray arr = new BinaryArray();
			arr.getTokens().add(new BinaryIntegerSigned(this.chassisList.size()));
			BinaryObject obj = new BinaryObject();
			this.toBytesChassisList(this.chassisList,obj);
			chassisListProp.setValues(List.of(arr,obj));
			bin.addProperty(chassisListProp);
		}
		return bin.toBytes();
	}

	private void toBytesChassisList(ChassisList chassisList,BinaryObject obj){
		for(Map.Entry<String,Chassis> me : chassisList.entrySet()){
			BinaryProperty coloringProp = new BinaryProperty();
			coloringProp.setKey(new BinaryToken(CMBFile.PROPERTY_CHASSIS));
			BinaryString str = new BinaryString(me.getKey());
			BinaryObject subObj = new BinaryObject();
			this.toBytesChassis(me.getValue(),subObj);
			coloringProp.setValues(List.of(str,subObj));
			obj.addProperty(coloringProp);
		}
	}

	private void toBytesChassis(Chassis chassis,BinaryObject obj){
		if(chassis.getWheels()!=null){
			BinaryProperty wheelsProp = new BinaryProperty();
			wheelsProp.setKey(new BinaryToken(CMBFile.PROPERTY_WHEELS));
			BinaryObject subObj = new BinaryObject();
			this.toBytesWheels(chassis.getWheels(),subObj);
			wheelsProp.setValues(List.of(subObj));
			obj.addProperty(wheelsProp);
		}
		if(chassis.getX2A()!=null){
			BinaryProperty x2AProp = new BinaryProperty();
			x2AProp.setKey(new BinaryToken(CMBFile.PROPERTY_x2A));
			BinaryFloat f1 = new BinaryFloat(chassis.getX2A()[0]);
			BinaryFloat f2 = new BinaryFloat(chassis.getX2A()[1]);
			BinaryFloat f3 = new BinaryFloat(chassis.getX2A()[2]);
			x2AProp.setValues(List.of(f1,f2,f3));
			obj.addProperty(x2AProp);
		}
		if(chassis.getSeat()!=null){
			BinaryProperty seatProp = new BinaryProperty();
			seatProp.setKey(new BinaryToken(CMBFile.PROPERTY_SEAT));
			BinaryFloat f1 = new BinaryFloat(chassis.getSeat().getX());
			BinaryFloat f2 = new BinaryFloat(chassis.getSeat().getY());
			BinaryFloat f3 = new BinaryFloat(chassis.getSeat().getZ());
			seatProp.setValues(List.of(f1,f2,f3));
			obj.addProperty(seatProp);
		}
		if(chassis.getX2C()!=null){
			BinaryProperty x2CProp = new BinaryProperty();
			x2CProp.setKey(new BinaryToken(CMBFile.PROPERTY_x2C));
			BinaryFloat f1 = new BinaryFloat(chassis.getX2C());
			x2CProp.setValues(List.of(f1));
			obj.addProperty(x2CProp);
		}
		if(chassis.getX2D()!=null){
			BinaryProperty x2DProp = new BinaryProperty();
			x2DProp.setKey(new BinaryToken(CMBFile.PROPERTY_x2D));
			BinaryFloat f1 = new BinaryFloat(chassis.getX2D()[0]);
			BinaryFloat f2 = new BinaryFloat(chassis.getX2D()[1]);
			x2DProp.setValues(List.of(f1,f2));
			obj.addProperty(x2DProp);
		}
		if(chassis.getX2E()!=null){
			BinaryProperty x2EProp = new BinaryProperty();
			x2EProp.setKey(new BinaryToken(CMBFile.PROPERTY_x2E));
			BinaryFloat f1 = new BinaryFloat(chassis.getX2E()[0]);
			BinaryFloat f2 = new BinaryFloat(chassis.getX2E()[1]);
			x2EProp.setValues(List.of(f1,f2));
			obj.addProperty(x2EProp);
		}
		if(chassis.getX2F()!=null){
			BinaryProperty x2FProp = new BinaryProperty();
			x2FProp.setKey(new BinaryToken(CMBFile.PROPERTY_x2F));
			BinaryFloat f1 = new BinaryFloat(chassis.getX2F());
			x2FProp.setValues(List.of(f1));
			obj.addProperty(x2FProp);
		}
		if(chassis.getX30()!=null){
			BinaryProperty x30Prop = new BinaryProperty();
			x30Prop.setKey(new BinaryToken(CMBFile.PROPERTY_x30));
			//TODO Perhaps move to own function
			BinaryList list = new BinaryList(BinaryToken.TOKEN_FLOAT);
			for(float f : chassis.getX30().getValue()){
				list.getTokens().add(new BinaryFloat(f));
			}
			BinaryObject subObj = new BinaryObject();
			subObj.getTokens().add(list);
			x30Prop.setValues(List.of(subObj));
			obj.addProperty(x30Prop);
		}
		if(chassis.getX31()!=null){
			BinaryProperty x31Prop = new BinaryProperty();
			x31Prop.setKey(new BinaryToken(CMBFile.PROPERTY_x31));
			//TODO Perhaps move to own function
			BinaryList list = new BinaryList(BinaryToken.TOKEN_FLOAT);
			for(float f : chassis.getX31().getValue()){
				list.getTokens().add(new BinaryFloat(f));
			}
			BinaryObject subObj = new BinaryObject();
			subObj.getTokens().add(list);
			x31Prop.setValues(List.of(subObj));
			obj.addProperty(x31Prop);
		}
		if(chassis.getX32()!=null){
			BinaryProperty x32Prop = new BinaryProperty();
			x32Prop.setKey(new BinaryToken(CMBFile.PROPERTY_x32));
			BinaryIntegerSigned i = new BinaryIntegerSigned(chassis.getX32());
			x32Prop.setValues(List.of(i));
			obj.addProperty(x32Prop);
		}
		if(chassis.getX33()!=null){
			BinaryProperty x33Prop = new BinaryProperty();
			x33Prop.setKey(new BinaryToken(CMBFile.PROPERTY_x33));
			BinaryIntegerSigned i = new BinaryIntegerSigned(chassis.getX33());
			x33Prop.setValues(List.of(i));
			obj.addProperty(x33Prop);
		}
		if(chassis.getBricks()!=null){
			BinaryProperty x39Prop = new BinaryProperty();
			x39Prop.setKey(new BinaryToken(CMBFile.PROPERTY_BRICKS));
			BinaryString i = new BinaryString(chassis.getBricks());
			x39Prop.setValues(List.of(i));
			obj.addProperty(x39Prop);
		}
		if(chassis.getX3A()!=null){
			BinaryProperty x3AProp = new BinaryProperty();
			x3AProp.setKey(new BinaryToken(CMBFile.PROPERTY_x3A));
			BinaryIntegerSigned i = new BinaryIntegerSigned(chassis.getX3A());
			x3AProp.setValues(List.of(i));
			obj.addProperty(x3AProp);
		}
		if(chassis.getX3B()!=null){
			BinaryProperty x3BProp = new BinaryProperty();
			x3BProp.setKey(new BinaryToken(CMBFile.PROPERTY_x3B));
			BinaryIntegerSigned i = new BinaryIntegerSigned(chassis.getX3B());
			x3BProp.setValues(List.of(i));
			obj.addProperty(x3BProp);
		}
		if(chassis.getX3C()!=null){
			BinaryProperty x3CProp = new BinaryProperty();
			x3CProp.setKey(new BinaryToken(CMBFile.PROPERTY_x3C));
			BinaryIntegerSigned i = new BinaryIntegerSigned(chassis.getX3C());
			x3CProp.setValues(List.of(i));
			obj.addProperty(x3CProp);
		}
	}

	private void toBytesWheels(Wheels wheels,BinaryObject obj){
		if(wheels.getMaterials()!=null){
			BinaryProperty x34Prop = new BinaryProperty();
			x34Prop.setKey(new BinaryToken(CMBFile.PROPERTY_MATERIALS));
			BinaryString string = new BinaryString(wheels.getMaterials());
			x34Prop.setValues(List.of(string));
			obj.addProperty(x34Prop);
		}
		if(wheels.getTextures()!=null){
			BinaryProperty x35Prop = new BinaryProperty();
			x35Prop.setKey(new BinaryToken(CMBFile.PROPERTY_TEXTURES));
			BinaryString string = new BinaryString(wheels.getTextures());
			x35Prop.setValues(List.of(string));
			obj.addProperty(x35Prop);
		}
		if(wheels.get_GDB()!=null){
			BinaryProperty x36Prop = new BinaryProperty();
			x36Prop.setKey(new BinaryToken(CMBFile.PROPERTY__GDB));
			BinaryString string = new BinaryString(wheels.get_GDB());
			x36Prop.setValues(List.of(string));
			obj.addProperty(x36Prop);
		}
		if(wheels.getSkeletons()!=null){
			BinaryProperty x37Prop = new BinaryProperty();
			x37Prop.setKey(new BinaryToken(CMBFile.PROPERTY_SKELETONS));
			BinaryString string = new BinaryString(wheels.getSkeletons());
			x37Prop.setValues(List.of(string));
			obj.addProperty(x37Prop);
		}
		if(wheels.getAnimations()!=null){
			BinaryProperty x38Prop = new BinaryProperty();
			x38Prop.setKey(new BinaryToken(CMBFile.PROPERTY_ANIMATIONS));
			BinaryString string = new BinaryString(wheels.getAnimations());
			x38Prop.setValues(List.of(string));
			obj.addProperty(x38Prop);
		}
	}

	public static CMBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		CMBFile cmb = new CMBFile();
		for(BinaryProperty prop : bin.getProperties()){
			if(prop.getKey().getToken()==CMBFile.PROPERTY_CHASSIS){
				CMBFile.fromChassisList(cmb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		return cmb;
	}

	private static void fromChassisList(CMBFile file,BinaryObject obj){
		ChassisList chassisList = new ChassisList();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==CMBFile.PROPERTY_CHASSIS){
				int i = 0;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				CMBFile.fromChassis(chassisList,(BinaryString) prop.getValues().get(i),(BinaryObject) prop.getValues().get(i+1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.chassisList = chassisList;
	}

	private static void fromChassis(ChassisList chassisList,BinaryString str,BinaryObject obj){
		Chassis chassis = new Chassis();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==CMBFile.PROPERTY_WHEELS){
				CMBFile.fromWheels(chassis,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x2A){
				BinaryFloat f1 = ((BinaryFloat) prop.getValues().get(0));
				BinaryFloat f2 = ((BinaryFloat) prop.getValues().get(1));
				BinaryFloat f3 = ((BinaryFloat) prop.getValues().get(2));
				chassis.setX2A(new float[]{f1.getFloat(),f2.getFloat(),f3.getFloat()});
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_SEAT){
				Position seat = new Position();
				seat.setX(((BinaryFloat) prop.getValues().get(0)).getFloat());
				seat.setY(((BinaryFloat) prop.getValues().get(1)).getFloat());
				seat.setZ(((BinaryFloat) prop.getValues().get(2)).getFloat());
				chassis.setSeat(seat);
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x2C){
				BinaryFloat f = ((BinaryFloat) prop.getValues().get(0));
				chassis.setX2C(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x2D){
				BinaryFloat f1 = ((BinaryFloat) prop.getValues().get(0));
				BinaryFloat f2 = ((BinaryFloat) prop.getValues().get(1));
				chassis.setX2D(new float[]{f1.getFloat(),f2.getFloat()});
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x2E){
				BinaryFloat f1 = ((BinaryFloat) prop.getValues().get(0));
				BinaryFloat f2 = ((BinaryFloat) prop.getValues().get(1));
				chassis.setX2E(new float[]{f1.getFloat(),f2.getFloat()});
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x2F){
				BinaryFloat f = ((BinaryFloat) prop.getValues().get(0));
				chassis.setX2F(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x30){
				BinaryObject o = (BinaryObject) prop.getValues().get(0);
				BinaryList l = (BinaryList) o.getTokens().get(0);

				X30 x30 = new X30();

				float[] value = new float[14];
				for(int i=0;i<value.length;i++){
					BinaryFloat f = (BinaryFloat) l.getTokens().get(i);
					value[i] = f.getFloat();
				}
				x30.setValue(value);

				chassis.setX30(x30);
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x31){
				BinaryObject o = (BinaryObject) prop.getValues().get(0);
				BinaryList l = (BinaryList) o.getTokens().get(0);

				X31 x31 = new X31();

				float[] value = new float[12];
				for(int i=0;i<value.length;i++){
					BinaryFloat f = (BinaryFloat) l.getTokens().get(i);
					value[i] = f.getFloat();
				}
				x31.setValue(value);

				chassis.setX31(x31);
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x32){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				chassis.setX32(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x33){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				chassis.setX33(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_BRICKS){//Only GAMEDATA
				BinaryString string = (BinaryString) prop.getValues().get(0);
				chassis.setBricks(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x3A){//Only GAMEDATA
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				chassis.setX3A(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x3B){//Only GAMEDATA
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				chassis.setX3B(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_x3C){//Only GAMEDATA
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				chassis.setX3C(integer.getIntegerSigned());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		chassisList.put(str.getString(),chassis);
	}

	private static void fromWheels(Chassis chassis,BinaryObject obj){
		Wheels wheels = new Wheels();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==CMBFile.PROPERTY_MATERIALS){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				wheels.setMaterials(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_TEXTURES){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				wheels.setTextures(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY__GDB){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				wheels.set_GDB(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_SKELETONS){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				wheels.setSkeletons(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==CMBFile.PROPERTY_ANIMATIONS){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				wheels.setAnimations(string.getString());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		chassis.setWheels(wheels);
	}

	@Override
	public String toString() {
		return "CMBFile{" +
				"chassisList=" + chassisList +
				'}';
	}

}
