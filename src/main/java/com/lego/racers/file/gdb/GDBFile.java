package com.lego.racers.file.gdb;

import com.lego.racers.binary.BinaryByteUnsigned;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryShortUnsigned;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryStruct;
import com.lego.racers.binary.BinaryStructInstance;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.gdb.object.ColoredVertex;
import com.lego.racers.file.gdb.object.ColoredVertices;
import com.lego.racers.file.gdb.object.Index;
import com.lego.racers.file.gdb.object.Indices;
import com.lego.racers.file.gdb.object.IndicesMetas;
import com.lego.racers.file.gdb.object.IndicesRangeIndicesMeta;
import com.lego.racers.file.gdb.object.MaterialIndicesMeta;
import com.lego.racers.file.gdb.object.Materials;
import com.lego.racers.file.gdb.object.NormalVertex;
import com.lego.racers.file.gdb.object.NormalVertices;
import com.lego.racers.file.gdb.object.SkeletonIndicesMeta;
import com.lego.racers.file.gdb.object.TMPColoredVertex5Floats;
import com.lego.racers.file.gdb.object.TMPColoredVertexInteger;
import com.lego.racers.file.gdb.object.VerticesRangeIndicesMeta;
import com.lego.racers.file.jam.JAMNodeFile;

import java.util.List;

public class GDBFile extends JAMNodeFile{

	//39, 41, 42, 45, 46, [51>]
	public static final byte BLOCK_MATERIALS = 0x27;
	public static final byte BLOCK_VERTICES_NORMAL = 0x29;
	public static final byte BLOCK_VERTICES_COLORED = 0x2A;
	public static final byte BLOCK_INDICES = 0x2D;
	public static final byte BLOCK_INDICES_META = 0x2E;

	//49, 50, [>51]
	public static final byte PROPERTY_INDICES_META_MATERIAL = 0x27;
	public static final byte PROPERTY_INDICES_META_RANGE_INDICES = 0x2D;
	public static final byte PROPERTY_INDICES_META_RANGE_VERTICES = 0x31;
	public static final byte PROPERTY_INDICES_META_SKELETON = 0x32;
	public static final byte PROPERTY_SCALE = 0x33;

	private Materials materials;
	private NormalVertices normalVertices;
	private ColoredVertices coloredVertices;
	private Indices indices;
	private IndicesMetas indicesMetas;
	private Float scale;

	public byte[] toBytes(){
		//TODO
		return new byte[0];
	}

	public static GDBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		GDBFile gdb = new GDBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==GDBFile.BLOCK_MATERIALS){
				GDBFile.initMaterials(gdb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==GDBFile.BLOCK_VERTICES_NORMAL){
				int j = 2;
				while(bin.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				GDBFile.initVerticesNormal(gdb,(BinaryObject) bin.getTokens().get(i+j));
			}
			if(token.getToken()==GDBFile.BLOCK_VERTICES_COLORED){
				int j = 2;
				while(bin.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				GDBFile.initVerticesColored(gdb,(BinaryObject) bin.getTokens().get(i+j));
			}
			if(token.getToken()==GDBFile.BLOCK_INDICES){
				int j = 2;
				while(bin.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				GDBFile.initIndices(gdb,(BinaryObject) bin.getTokens().get(i+j));
			}
			if(token.getToken()==GDBFile.BLOCK_INDICES_META){
				int j = 2;
				while(bin.getTokens().get(i+j) instanceof BinaryStruct){
					j++;
				}
				GDBFile.initIndicesMetas(gdb,(BinaryObject) bin.getTokens().get(i+j));
			}
			if(token.getToken()==GDBFile.PROPERTY_SCALE){
				BinaryFloat f = (BinaryFloat) bin.getTokens().get(i+1);
				gdb.scale = f.getFloat();
			}
		}
		return gdb;
	}

	private static void initMaterials(GDBFile file,BinaryObject obj){
		Materials materials = new Materials();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token instanceof BinaryString){
				materials.add(((BinaryString) token).getString());
			}
			if(token instanceof BinaryList){
				for(BinaryToken string : ((BinaryList) token).getTokens()){
					if(string instanceof BinaryString){
						materials.add(((BinaryString) string).getString());
					}
				}
			}
		}
		file.materials = materials;
	}

	private static void initVerticesNormal(GDBFile file,BinaryObject obj){
		NormalVertices normalVertices = new NormalVertices();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(BinaryToken token : list.getTokens()){
			BinaryStructInstance structInstance = (BinaryStructInstance) token;

			NormalVertex normalVertex = new NormalVertex();

			BinaryFloat x = (BinaryFloat) structInstance.getTokens().get(0);
			normalVertex.setX(x.getFloat());
			BinaryFloat y = (BinaryFloat) structInstance.getTokens().get(1);
			normalVertex.setY(y.getFloat());
			BinaryFloat z = (BinaryFloat) structInstance.getTokens().get(2);
			normalVertex.setZ(z.getFloat());

			BinaryFloat tu = (BinaryFloat) structInstance.getTokens().get(3);
			normalVertex.setTU(tu.getFloat());
			BinaryFloat tv = (BinaryFloat) structInstance.getTokens().get(4);
			normalVertex.setTV(tv.getFloat());

			BinaryFloat nx = (BinaryFloat) structInstance.getTokens().get(5);
			normalVertex.setNX(nx.getFloat());
			BinaryFloat ny = (BinaryFloat) structInstance.getTokens().get(6);
			normalVertex.setNY(ny.getFloat());
			BinaryFloat nz = (BinaryFloat) structInstance.getTokens().get(7);
			normalVertex.setNZ(nz.getFloat());

			normalVertices.add(normalVertex);
		}

		file.normalVertices = normalVertices;
	}

	private static void initVerticesColored(GDBFile file,BinaryObject obj){
		ColoredVertices coloredVertices = new ColoredVertices();

		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token instanceof BinaryIntegerSigned){
				TMPColoredVertexInteger tmpColoredVertexInteger = new TMPColoredVertexInteger();
				tmpColoredVertexInteger.setVarA(((BinaryIntegerSigned) token).getIntegerSigned());
				coloredVertices.add(tmpColoredVertexInteger);
			}
			if(token instanceof BinaryStructInstance){
				BinaryStructInstance structInstance = (BinaryStructInstance) token;
				if(structInstance.getId()==0x17){
					GDBFile.initVertexColored(coloredVertices,structInstance);
				}
				if(structInstance.getId()==0x19){
					GDBFile.initIndex(coloredVertices,(BinaryStructInstance) token);
				}
			}
			if(token instanceof BinaryList) {
				BinaryList list = (BinaryList) token;
				if (list.getType() == BinaryToken.TOKEN_FLOAT) {
					TMPColoredVertex5Floats tmpColoredVertex5Floats = new TMPColoredVertex5Floats();
					tmpColoredVertex5Floats.setVarA(((BinaryFloat) list.getTokens().get(0)).getFloat());
					tmpColoredVertex5Floats.setVarB(((BinaryFloat) list.getTokens().get(1)).getFloat());
					tmpColoredVertex5Floats.setVarC(((BinaryFloat) list.getTokens().get(2)).getFloat());
					tmpColoredVertex5Floats.setVarD(((BinaryFloat) list.getTokens().get(3)).getFloat());
					tmpColoredVertex5Floats.setVarE(((BinaryFloat) list.getTokens().get(4)).getFloat());
					coloredVertices.add(tmpColoredVertex5Floats);
				} else {
					for (BinaryToken subToken : list.getTokens()) {
						if (subToken instanceof BinaryStructInstance) {
							BinaryStructInstance structInstance = (BinaryStructInstance) subToken;
							if (structInstance.getId() == 0x17) {
								GDBFile.initVertexColored(coloredVertices, structInstance);
							}
						}
					}
				}
			}
		}

		file.coloredVertices = coloredVertices;
	}

	private static void initVertexColored(ColoredVertices coloredVertices,BinaryStructInstance structInstance){
		ColoredVertex coloredVertex = new ColoredVertex();

		BinaryFloat x = (BinaryFloat) structInstance.getTokens().get(0);
		coloredVertex.setX(x.getFloat());
		BinaryFloat y = (BinaryFloat) structInstance.getTokens().get(1);
		coloredVertex.setY(y.getFloat());
		BinaryFloat z = (BinaryFloat) structInstance.getTokens().get(2);
		coloredVertex.setZ(z.getFloat());

		BinaryFloat tu = (BinaryFloat) structInstance.getTokens().get(3);
		coloredVertex.setTU(tu.getFloat());
		BinaryFloat tv = (BinaryFloat) structInstance.getTokens().get(4);
		coloredVertex.setTV(tv.getFloat());

		BinaryByteUnsigned r = (BinaryByteUnsigned) structInstance.getTokens().get(5);
		coloredVertex.setRed(r.getByteUnsigned());
		BinaryByteUnsigned g = (BinaryByteUnsigned) structInstance.getTokens().get(6);
		coloredVertex.setGreen(g.getByteUnsigned());
		BinaryByteUnsigned b = (BinaryByteUnsigned) structInstance.getTokens().get(7);
		coloredVertex.setBlue(b.getByteUnsigned());
		BinaryByteUnsigned a = (BinaryByteUnsigned) structInstance.getTokens().get(8);
		coloredVertex.setAlpha(a.getByteUnsigned());

		coloredVertices.add(coloredVertex);
	}

	private static void initIndices(GDBFile file,BinaryObject obj){
		Indices indices = new Indices();

		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryList){
				for(BinaryToken subToken : ((BinaryList) token).getTokens()){
					if(subToken instanceof BinaryStructInstance){
						if(((BinaryStructInstance) subToken).getId()==0x19){
							GDBFile.initIndex(indices,(BinaryStructInstance) subToken);
						}
					}
				}
			}
			if(token instanceof BinaryStructInstance){
				if(((BinaryStructInstance) token).getId()==0x19){
					GDBFile.initIndex(indices,(BinaryStructInstance) token);
				}
			}
		}

		file.indices = indices;
	}

	private static void initIndex(List list,BinaryStructInstance structInstance){
		Index index = new Index();

		BinaryByteUnsigned r = (BinaryByteUnsigned) structInstance.getTokens().get(0);
		index.setVarA(r.getByteUnsigned());
		BinaryByteUnsigned g = (BinaryByteUnsigned) structInstance.getTokens().get(1);
		index.setVarB(g.getByteUnsigned());
		BinaryByteUnsigned b = (BinaryByteUnsigned) structInstance.getTokens().get(2);
		index.setVarC(b.getByteUnsigned());

		list.add(index);
	}

	private static void initIndicesMetas(GDBFile file,BinaryObject obj){
		IndicesMetas indicesMetas = new IndicesMetas();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryStructInstance){
				BinaryStructInstance structInstance = (BinaryStructInstance) token;
				if(structInstance.getTokens().get(0).getToken()==GDBFile.PROPERTY_INDICES_META_RANGE_VERTICES){
					VerticesRangeIndicesMeta verticesRangeIndicesMeta = new VerticesRangeIndicesMeta();
					verticesRangeIndicesMeta.setVarA(((BinaryByteUnsigned) structInstance.getTokens().get(1)).getByteUnsigned());
					verticesRangeIndicesMeta.setVarB(((BinaryShortUnsigned) structInstance.getTokens().get(2)).getShortUnsigned());
					verticesRangeIndicesMeta.setVarC(((BinaryShortUnsigned) structInstance.getTokens().get(3)).getShortUnsigned());
					indicesMetas.add(verticesRangeIndicesMeta);
				}
				if(structInstance.getTokens().get(0).getToken()==GDBFile.PROPERTY_INDICES_META_RANGE_INDICES){
					IndicesRangeIndicesMeta indicesRangeIndicesMeta = new IndicesRangeIndicesMeta();
					indicesRangeIndicesMeta.setVarA(((BinaryShortUnsigned) structInstance.getTokens().get(1)).getShortUnsigned());
					indicesRangeIndicesMeta.setVarB(((BinaryShortUnsigned) structInstance.getTokens().get(2)).getShortUnsigned());
					indicesMetas.add(indicesRangeIndicesMeta);
				}
				if(structInstance.getTokens().get(0).getToken()==GDBFile.PROPERTY_INDICES_META_MATERIAL){
					MaterialIndicesMeta materialIndicesMeta = new MaterialIndicesMeta();
					materialIndicesMeta.setVarA(((BinaryShortUnsigned) structInstance.getTokens().get(1)).getShortUnsigned());
					indicesMetas.add(materialIndicesMeta);
				}
				if(structInstance.getTokens().get(0).getToken()==GDBFile.PROPERTY_INDICES_META_SKELETON){
					SkeletonIndicesMeta skeletonIndicesMeta = new SkeletonIndicesMeta();
					skeletonIndicesMeta.setVarA(((BinaryShortUnsigned) structInstance.getTokens().get(1)).getShortUnsigned());
					indicesMetas.add(skeletonIndicesMeta);
				}
			}
		}
		file.indicesMetas = indicesMetas;
	}

	@Override
	public String toString() {
		return "GDBFile{" +
				"materials=" + materials +
				", normalVertices=" + normalVertices +
				", coloredVertices=" + coloredVertices +
				", indices=" + indices +
				", indicesMetas=" + indicesMetas +
				", scale=" + scale +
				'}';
	}

}