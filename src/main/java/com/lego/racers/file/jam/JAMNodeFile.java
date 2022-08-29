package com.lego.racers.file.jam;

import com.lego.racers.file.adb.ADBFile;
import com.lego.racers.file.bdb.BDBFile;
import com.lego.racers.file.bvb.BVBFile;
import com.lego.racers.file.ccb.CCBFile;
import com.lego.racers.file.cdb.CDBFile;
import com.lego.racers.file.ceb.CEBFile;
import com.lego.racers.file.cmb.CMBFile;
import com.lego.racers.file.cpb.CPBFile;
import com.lego.racers.file.crb.CRBFile;
import com.lego.racers.file.ddb.DDBFile;
import com.lego.racers.file.emb.EMBFile;
import com.lego.racers.file.evb.EVBFile;
import com.lego.racers.file.fdb.FDBFile;
import com.lego.racers.file.gcb.GCBFile;
import com.lego.racers.file.gdb.GDBFile;
import com.lego.racers.file.ghb.GHBFile;
import com.lego.racers.file.hzb.HZBFile;
import com.lego.racers.file.idb.IDBFile;
import com.lego.racers.file.leb.LEBFile;
import com.lego.racers.file.lsb.LSBFile;
import com.lego.racers.file.mab.MABFile;
import com.lego.racers.file.mdb.MDBFile;
import com.lego.racers.file.mib.MIBFile;
import com.lego.racers.file.msb.MSBFile;
import com.lego.racers.file.pcb.PCBFile;
import com.lego.racers.file.pwb.PWBFile;
import com.lego.racers.file.rab.RABFile;
import com.lego.racers.file.rcb.RCBFile;
import com.lego.racers.file.rrb.RRBFile;
import com.lego.racers.file.sbk.SBKFile;
import com.lego.racers.file.sdb.SDBFile;
import com.lego.racers.file.skb.SKBFile;
import com.lego.racers.file.spb.SPBFile;
import com.lego.racers.file.srf.SRFFile;
import com.lego.racers.file.tdb.TDBFile;
import com.lego.racers.file.tgb.TGBFile;
import com.lego.racers.file.tib.TIBFile;
import com.lego.racers.file.tmb.TMBFile;
import com.lego.racers.file.trb.TRBFile;
import com.lego.racers.file.wdb.WDBFile;

abstract public class JAMNodeFile{

	abstract public byte[] toBytes();

	public static JAMNodeFile fromBytes(String name,byte[] bytes){
		if(name==null){
			return null;
		}
		if("LEGOMSC".equalsIgnoreCase(name)){
			return SBKFile.from(bytes);
		}
		if(name.contains(".")){
			String extension = name.split("\\.")[1].toUpperCase();
			switch(extension){
				case "ADB":return ADBFile.from(bytes);
				case "BDB":return BDBFile.from(bytes);
				case "BVB":return BVBFile.from(bytes);
				case "CCB":return CCBFile.from(bytes);
				case "CDB":return CDBFile.from(bytes);
				case "CEB":return CEBFile.from(bytes);
				case "CMB":return CMBFile.from(bytes);
				case "CPB":return CPBFile.from(bytes);
				case "CRB":return CRBFile.from(bytes);
				case "DDB":return DDBFile.from(bytes);
				case "EMB":return EMBFile.from(bytes);
				case "EVB":return EVBFile.from(bytes);
				case "FDB":return FDBFile.from(bytes);
				case "GCB":return GCBFile.from(bytes);
				case "GDB":return GDBFile.from(bytes);
				case "GHB":return GHBFile.from(bytes);
				case "HZB":return HZBFile.from(bytes);
				case "IDB":return IDBFile.from(bytes);
				case "JAM":return JAMFile.from(bytes);
				case "LEB":return LEBFile.from(bytes);
				case "LSB":return LSBFile.from(bytes);
				case "MAB":return MABFile.from(bytes);
				case "MDB":return MDBFile.from(bytes);
				case "MIB":return MIBFile.from(bytes);
				case "MSB":return MSBFile.from(bytes);
				case "PCB":return PCBFile.from(bytes);
				case "PWB":return PWBFile.from(bytes);
				case "RAB":return RABFile.from(bytes);
				case "RCB":return RCBFile.from(bytes);
				case "RRB":return RRBFile.from(bytes);
				case "SBK":return SBKFile.from(bytes);
				case "SDB":return SDBFile.from(bytes);
				case "SKB":return SKBFile.from(bytes);
				case "SPB":return SPBFile.from(bytes);
				case "SRF":return SRFFile.from(bytes);
				case "TDB":return TDBFile.from(bytes);
				case "TGB":return TGBFile.from(bytes);
				case "TIB":return TIBFile.from(bytes);
				case "TMB":return TMBFile.from(bytes);
				case "TRB":return TRBFile.from(bytes);
				case "WDB":return WDBFile.from(bytes);
			}
		}
		return null;
	}

}