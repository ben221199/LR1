package com.lego.racers.file.ddb;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.ddb.object.Driver;
import com.lego.racers.file.ddb.object.Drivers;
import com.lego.racers.file.ddb.object.TMPDriverObject;

import java.util.Map;

public class DDBFile{

	public static final byte BLOCK_DRIVERS = 0x27;

	public static final byte PROPERTY_DRIVER = 0x27;
	public static final byte PROPERTY_40 = 0x28;
	public static final byte PROPERTY_41 = 0x29;
	public static final byte PROPERTY_42 = 0x2A;
	public static final byte PROPERTY_43 = 0x2B;
	public static final byte PROPERTY_44 = 0x2C;
	public static final byte PROPERTY_45 = 0x2D;
	public static final byte PROPERTY_46 = 0x2E;
	public static final byte PROPERTY_47 = 0x2F;
	public static final byte PROPERTY_48 = 0x30;
	public static final byte PROPERTY_49 = 0x31;
	public static final byte PROPERTY_51 = 0x33;
	public static final byte PROPERTY_52 = 0x34;
	public static final byte PROPERTY_53 = 0x35;
	public static final byte PROPERTY_54 = 0x36;
	public static final byte PROPERTY_55 = 0x37;
	public static final byte PROPERTY_56 = 0x38;
	public static final byte PROPERTY_58 = 0x3A;

	private Drivers drivers;

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this.drivers!=null){
			bin.getTokens().add(new BinaryToken(DDBFile.BLOCK_DRIVERS));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.drivers.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			for(Map.Entry<String,Driver> entry : this.drivers.entrySet()){
				obj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_DRIVER));
				obj.getTokens().add(new BinaryString(entry.getKey()));
				BinaryObject driverObj = new BinaryObject();
				if(entry.getValue().get40()!=null){
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_40));
					driverObj.getTokens().add(new BinaryString(entry.getValue().get40()));
				}
				if(entry.getValue().get41()!=null){
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_41));
					driverObj.getTokens().add(new BinaryString(entry.getValue().get41()));
				}
				if(entry.getValue().get42()!=null){
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_42));
					driverObj.getTokens().add(new BinaryString(entry.getValue().get42()));
				}
				if(entry.getValue().get43()!=null){
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_43));
					driverObj.getTokens().add(new BinaryString(entry.getValue().get43()));
				}
				if(entry.getValue().get44()!=null){
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_44));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get44()));
				}
				if(entry.getValue().get45()!=null){
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_45));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get45()));
				}
				if(entry.getValue().get46()!=null) {
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_46));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get46()));
				}
				if(entry.getValue().get47()!=null) {
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_47));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get47()));
				}
				if(entry.getValue().get48()!=null) {
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_48));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get48()));
				}
				if(entry.getValue().get49()!=null) {
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_49));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get49()));
				}
				if(entry.getValue().get51()!=null) {
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_51));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get51()));
				}
				if(entry.getValue().get52()!=null) {
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_52));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get52()));
				}
				if(entry.getValue().get53()!=null) {
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_53));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get53()));
				}
				if(entry.getValue().get54()!=null) {
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_54));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get54()));
				}
				if(entry.getValue().get55()!=null) {
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_55));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get55()));
				}
				if(entry.getValue().get56()!=null) {
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_56));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get56()));
				}
				if(entry.getValue().get58()!=null){
					driverObj.getTokens().add(new BinaryToken(DDBFile.PROPERTY_58));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get58().getVarA()));
					driverObj.getTokens().add(new BinaryIntegerSigned(entry.getValue().get58().getVarB()));
				}
				obj.getTokens().add(driverObj);
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static DDBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		DDBFile ddb = new DDBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==DDBFile.BLOCK_DRIVERS){
				DDBFile.initDrivers(ddb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return ddb;
	}

	private static void initDrivers(DDBFile file,BinaryObject obj){
		Drivers drivers = new Drivers();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==DDBFile.PROPERTY_DRIVER){
				DDBFile.initDriver(drivers,(BinaryString) obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.drivers = drivers;
	}

	private static void initDriver(Drivers drivers,BinaryString str,BinaryObject obj){
		Driver driver = new Driver();

		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()== DDBFile.PROPERTY_51){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set51(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_41){
				BinaryString f = (BinaryString) obj.getTokens().get(i+1);
				driver.set41(f.getString());
			}
			if(token.getToken()== DDBFile.PROPERTY_40){
				BinaryString f = (BinaryString) obj.getTokens().get(i+1);
				driver.set40(f.getString());
			}
			if(token.getToken()== DDBFile.PROPERTY_42){
				BinaryString f = (BinaryString) obj.getTokens().get(i+1);
				driver.set42(f.getString());
			}
			if(token.getToken()== DDBFile.PROPERTY_43){
				BinaryString f = (BinaryString) obj.getTokens().get(i+1);
				driver.set43(f.getString());
			}
			if(token.getToken()== DDBFile.PROPERTY_44){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set44(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_45){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set45(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_46){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set46(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_47){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set47(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_48){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set48(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_49){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set49(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_52){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set52(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_53){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set53(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_54){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set54(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_55){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set55(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_56){
				BinaryIntegerSigned f = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				driver.set56(f.getIntegerSigned());
			}
			if(token.getToken()== DDBFile.PROPERTY_58){
				BinaryIntegerSigned a = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				BinaryIntegerSigned b = (BinaryIntegerSigned) obj.getTokens().get(i+2);
				TMPDriverObject tmp = new TMPDriverObject();
				tmp.setVarA(a.getIntegerSigned());
				tmp.setVarB(b.getIntegerSigned());
				driver.set58(tmp);
			}
		}

		drivers.put(str.getString(),driver);
	}


	@Override
	public String toString() {
		return "DDBFile{" +
				"drivers=" + drivers +
				'}';
	}

}