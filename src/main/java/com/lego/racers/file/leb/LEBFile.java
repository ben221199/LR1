package com.lego.racers.file.leb;

import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.bvb.object.Positions;
import com.lego.racers.file.leb.object.BrickColors;
import com.lego.racers.file.leb.object.CarSets;
import com.lego.racers.file.leb.object.ColorNames;
import com.lego.racers.file.leb.object.Normal;
import com.lego.racers.file.leb.object.Normals;
import com.lego.racers.file.leb.object.Part;
import com.lego.racers.file.leb.object.Parts;
import com.lego.racers.file.leb.object.StudTuple;
import com.lego.racers.file.leb.object.StudTuples;
import com.lego.racers.file.leb.object.TextureTuple;
import com.lego.racers.file.leb.object.TextureTuples;
import com.lego.racers.file.leb.object.Triangle0;
import com.lego.racers.file.leb.object.Triangle1;
import com.lego.racers.file.leb.object.Triangle4;
import com.lego.racers.file.leb.object.Triangle5;
import com.lego.racers.file.leb.object.Triangle6;
import com.lego.racers.file.leb.object.Triangle6_5;
import com.lego.racers.file.leb.object.Triangle8;
import com.lego.racers.file.leb.object.Triangle9;
import com.lego.racers.file.leb.object.TriangleA_08;
import com.lego.racers.file.leb.object.TriangleA_149;
import com.lego.racers.file.leb.object.TriangleA_5;
import com.lego.racers.file.leb.object.Triangles;

import java.util.ArrayList;
import java.util.List;

public class LEBFile{

	public static final byte PROPERTY_PARTS = 0x27;
	public static final byte PROPERTY_TRIANGLES = 0x28;
	public static final byte PROPERTY_POSITIONS = 0x29;
	public static final byte PROPERTY_NORMALS = 0x2A;
	public static final byte PROPERTY_TEXTURES = 0x2B;
	public static final byte PROPERTY_STUDS = 0x2C;
	public static final byte PROPERTY_COLOR_NAMES = 0x2D;
	public static final byte PROPERTY_46 = 0x2E;
	public static final byte PROPERTY_CAR_SETS = 0x2F;
	public static final byte PROPERTY_BRICK_COLORS = 0x30;
	public static final byte PROPERTY_CHASSIS = 0x31;

	private Parts parts;
	private Triangles triangles;
	private Positions positions;
	private Normals normals;
	private TextureTuples textures;
	private StudTuples studs;
	private ColorNames colorNames;
	private Integer p46;
	private CarSets carSets;
	private BrickColors brickColors;
	private String chassis;

	public Parts getParts(){
		return this.parts;
	}

	public void setParts(Parts parts){
		this.parts = parts;
	}

	public Triangles getTriangles(){
		return this.triangles;
	}

	public void setTriangles(Triangles triangles){
		this.triangles = triangles;
	}

	public Positions getPositions(){
		return this.positions;
	}

	public void setPositions(Positions positions){
		this.positions = positions;
	}

	public Normals getNormals(){
		return this.normals;
	}

	public void setNormals(Normals normals){
		this.normals = normals;
	}

	public TextureTuples getTextures(){
		return this.textures;
	}

	public void setTextures(TextureTuples textures){
		this.textures = textures;
	}

	public StudTuples getStuds(){
		return this.studs;
	}

	public void setStuds(StudTuples studs){
		this.studs = studs;
	}

	public ColorNames getColorNames(){
		return this.colorNames;
	}

	public void setColorNames(ColorNames colorNames){
		this.colorNames = colorNames;
	}

	public Integer getP46(){
		return this.p46;
	}

	public void setP46(Integer p46){
		this.p46 = p46;
	}

	public CarSets getCarSets(){
		return this.carSets;
	}

	public void setCarSets(CarSets carSets){
		this.carSets = carSets;
	}

	public BrickColors getBrickColors(){
		return this.brickColors;
	}

	public void setBrickColors(BrickColors brickColors){
		this.brickColors = brickColors;
	}

	public String getChassis(){
		return this.chassis;
	}

	public void setChassis(String chassis){
		this.chassis = chassis;
	}

	public static LEBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		LEBFile leb = new LEBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==LEBFile.PROPERTY_PARTS){
				LEBFile.initParts(leb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==LEBFile.PROPERTY_TRIANGLES){
				LEBFile.initTriangles(leb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==LEBFile.PROPERTY_POSITIONS){
				LEBFile.initPositions(leb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==LEBFile.PROPERTY_NORMALS){
				LEBFile.initNormals(leb,(BinaryObject) bin.getTokens().get(i+2));
			}
			//
			if(token.getToken()==LEBFile.PROPERTY_TEXTURES){
				LEBFile.initTextures(leb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==LEBFile.PROPERTY_STUDS){
				LEBFile.initStuds(leb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==LEBFile.PROPERTY_COLOR_NAMES){
				LEBFile.initColorNames(leb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==LEBFile.PROPERTY_46){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) bin.getTokens().get(i+1);
				leb.p46 = ii.getIntegerSigned();
			}
			if(token.getToken()==LEBFile.PROPERTY_CAR_SETS){
				LEBFile.initCarSets(leb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==LEBFile.PROPERTY_BRICK_COLORS){
				LEBFile.initBrickColors(leb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==LEBFile.PROPERTY_CHASSIS){
				BinaryString str = (BinaryString) bin.getTokens().get(i+1);
				leb.chassis = str.getString();
			}
		}
		return leb;
	}

	private static void initParts(LEBFile file,BinaryObject obj){
		Parts parts = new Parts();
		for(int i=0;i<obj.getTokens().size();i+=2){
			Part part = new Part();

			BinaryString str = (BinaryString) obj.getTokens().get(i);
			part.setName(str.getString());

			BinaryList list = (BinaryList) obj.getTokens().get(i+1);
			part.setV1(((BinaryIntegerSigned) list.getTokens().get(0)).getIntegerSigned());
			part.setV2(((BinaryIntegerSigned) list.getTokens().get(1)).getIntegerSigned());
			part.setV3(((BinaryIntegerSigned) list.getTokens().get(2)).getIntegerSigned());
			part.setV4(((BinaryIntegerSigned) list.getTokens().get(3)).getIntegerSigned());

			parts.add(part);
		}
		file.parts = parts;
	}

	private static void initTriangles(LEBFile file,BinaryObject obj){
		Triangles triangles = new Triangles();
		List<BinaryToken> tokens = new ArrayList<>();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryList list = (BinaryList) obj.getTokens().get(i);
			tokens.addAll(list.getTokens());
		}

		int i = 0;
		int lastType = -1;

		while(i<tokens.size()){
			int offset = i;
			BinaryIntegerSigned ii = (BinaryIntegerSigned) tokens.get(i++);

			int first = ii.getIntegerSigned();
			int type = first >> 12 & 0xF;

			// (p,n),(p),(p)
			if(type==0x0){
				lastType = type;

				Triangle0 triangle = new Triangle0();
				triangle.setType(first);
				triangle.setP1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setN1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangles.put(offset,triangle);
			}
			// (p,n,u),(p,u),(p,u)
			if(type==0x1){
				lastType = type;

				Triangle1 triangle = new Triangle1();
				triangle.setType(first);
				triangle.setP1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setN(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setU1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setU2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setU3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangles.put(offset,triangle);
			}

			// (p,n),(p,n),(p,n)
			if(type==0x4){
				lastType = type;

				Triangle4 triangle = new Triangle4();
				triangle.setType(first);
				triangle.setP1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setN1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setN2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setN3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangles.put(offset,triangle);
			}
			// (p,n,u),(p,n,u),(p,n,u)
			if(type==0x5){
				lastType = type;

				Triangle5 triangle = new Triangle5();
				triangle.setType(first);
				triangle.setP1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setN1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setU1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setN2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setU2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setN3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setU3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangles.put(offset,triangle);
			}
			// var
			if(type==0x6){
				if(lastType==0x5){
					lastType = type;

					Triangle6_5 triangle = new Triangle6_5();
					triangle.setType(first);
					triangle.setV1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangle.setV2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangle.setV3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangles.put(offset,triangle);
				}else{
					lastType = type;

					Triangle6 triangle = new Triangle6();
					triangle.setType(first);
					triangle.setV1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangle.setV2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangles.put(offset,triangle);
				}
			}

			// (p),(p),(p)
			if(type==0x8){
				lastType = type;

				Triangle8 triangle = new Triangle8();
				triangle.setType(first);
				triangle.setP1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangles.put(offset,triangle);
			}
			// (p,u),(p,u),(p,u)
			if(type==0x9){
				lastType = type;

				Triangle9 triangle = new Triangle9();
				triangle.setType(first);
				triangle.setP1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setU1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setU2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setP3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangle.setU3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
				triangles.put(offset,triangle);
			}
			// var
			if(type==0xA){
				if(lastType==0x0 || lastType==0x8){
					TriangleA_08 triangle = new TriangleA_08();
					triangle.setType(first);
					triangle.setV1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangles.put(offset,triangle);
				}
				if(lastType==0x1 || lastType==0x4 || lastType==0x9){
					TriangleA_149 triangle = new TriangleA_149();
					triangle.setType(first);
					triangle.setV1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangle.setV2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangles.put(offset,triangle);
				}
				if(lastType==0x5){
					TriangleA_5 triangle = new TriangleA_5();
					triangle.setType(first);
					triangle.setV1(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangle.setV2(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangle.setV3(((BinaryIntegerSigned) tokens.get(i++)).getIntegerSigned());
					triangles.put(offset,triangle);
				}
			}
		}
		file.triangles = triangles;
	}

	private static void initPositions(LEBFile file,BinaryObject obj){
		Positions positions = new Positions();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i+=3){
			Position position = new Position();

			position.setX(((BinaryIntegerSigned) list.getTokens().get(i)).getIntegerSigned());
			position.setY(((BinaryIntegerSigned) list.getTokens().get(i+1)).getIntegerSigned());
			position.setZ(((BinaryIntegerSigned) list.getTokens().get(i+2)).getIntegerSigned());

			positions.add(position);
		}
		file.positions = positions;
	}

	private static void initNormals(LEBFile file,BinaryObject obj){
		Normals normals = new Normals();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i+=3){
			Normal normal = new Normal();

			normal.setNX(((BinaryIntegerSigned) list.getTokens().get(i)).getIntegerSigned());
			normal.setNY(((BinaryIntegerSigned) list.getTokens().get(i+1)).getIntegerSigned());
			normal.setNZ(((BinaryIntegerSigned) list.getTokens().get(i+2)).getIntegerSigned());

			normals.add(normal);
		}
		file.normals = normals;
	}

	private static void initTextures(LEBFile file,BinaryObject obj){
		TextureTuples textures = new TextureTuples();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i+=2){
			TextureTuple texture = new TextureTuple();

			texture.setU(((BinaryIntegerSigned) list.getTokens().get(i)).getIntegerSigned());
			texture.setV(((BinaryIntegerSigned) list.getTokens().get(i+1)).getIntegerSigned());

			textures.add(texture);
		}
		file.textures = textures;
	}

	private static void initStuds(LEBFile file,BinaryObject obj){
		StudTuples studs = new StudTuples();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i+=2){
			StudTuple stud = new StudTuple();

			stud.setUpper(((BinaryIntegerSigned) list.getTokens().get(i)).getIntegerSigned());
			stud.setLower(((BinaryIntegerSigned) list.getTokens().get(i+1)).getIntegerSigned());

			studs.add(stud);
		}
		file.studs = studs;
	}

	private static void initColorNames(LEBFile file,BinaryObject obj){
		ColorNames colorNames = new ColorNames();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i++){
			BinaryString str = (BinaryString) list.getTokens().get(i);
			colorNames.add(str.getString());
		}
		file.colorNames = colorNames;
	}

	private static void initCarSets(LEBFile file,BinaryObject obj){
		CarSets carSets = new CarSets();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i++){
			BinaryString str = (BinaryString) list.getTokens().get(i);
			carSets.add(str.getString());
		}
		file.carSets = carSets;
	}

	private static void initBrickColors(LEBFile file,BinaryObject obj){
		BrickColors brickColors = new BrickColors();
		BinaryList list = (BinaryList) obj.getTokens().get(0);
		for(int i=0;i<list.getTokens().size();i+=2){
			BinaryString str = (BinaryString) list.getTokens().get(i);
			BinaryString str2 = (BinaryString) list.getTokens().get(i+1);
			brickColors.put(str.getString(),str2.getString());
		}
		file.brickColors = brickColors;
	}

	@Override
	public String toString() {
		return "LEBFile{" +
				"parts=" + parts +
				", triangles=" + triangles +
				", positions=" + positions +
				", normals=" + normals +
				", textures=" + textures +
				", studs=" + studs +
				", colorNames=" + colorNames +
				", p46=" + p46 +
				", carSets=" + carSets +
				", brickColors=" + brickColors +
				", chassis='" + chassis + '\'' +
				'}';
	}

}