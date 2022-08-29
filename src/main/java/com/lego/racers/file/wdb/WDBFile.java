package com.lego.racers.file.wdb;

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
import com.lego.racers.file.cdb.object.Color;
import com.lego.racers.file.cdb.object.Direction;
import com.lego.racers.file.cdb.object.Rotation;
import com.lego.racers.file.jam.JAMNodeFile;
import com.lego.racers.file.wdb.object.AmbientLight;
import com.lego.racers.file.wdb.object.AmbientLights;
import com.lego.racers.file.wdb.object.Animations;
import com.lego.racers.file.wdb.object.BoundingVolumeModel;
import com.lego.racers.file.wdb.object.BoundingVolumeModels;
import com.lego.racers.file.wdb.object.BoundingVolumes;
import com.lego.racers.file.wdb.object.BoxModel;
import com.lego.racers.file.wdb.object.Boxes;
import com.lego.racers.file.wdb.object.BoxModels;
import com.lego.racers.file.wdb.object.Camera;
import com.lego.racers.file.wdb.object.Cameras;
import com.lego.racers.file.wdb.object.DirectionalLight;
import com.lego.racers.file.wdb.object.DirectionalLights;
import com.lego.racers.file.wdb.object.DynamicModel;
import com.lego.racers.file.wdb.object.DynamicModels;
import com.lego.racers.file.wdb.object.Graphics;
import com.lego.racers.file.wdb.object.MaterialAnimations;
import com.lego.racers.file.wdb.object.Materials;
import com.lego.racers.file.wdb.object.Model;
import com.lego.racers.file.wdb.object.Skeletons;
import com.lego.racers.file.wdb.object.StaticModel;
import com.lego.racers.file.wdb.object.StaticModels;
import com.lego.racers.file.wdb.object.TextureModel;
import com.lego.racers.file.wdb.object.TextureModels;
import com.lego.racers.file.wdb.object.Textures;
import com.lego.racers.file.wdb.object.X3E;
import com.lego.racers.file.wdb.object.X3Es;

public class WDBFile extends JAMNodeFile{

	public static final byte PROPERTY_TEXTURE = 0x27;
	public static final byte PROPERTY_MATERIAL = 0x28;
	public static final byte PROPERTY_ANIMATION = 0x29;
	public static final byte PROPERTY_GRAPHIC = 0x2A;
	public static final byte PROPERTY_GRAPHIC2 = 0x2B;
	public static final byte PROPERTY_SKELETON = 0x2C;
	public static final byte PROPERTY_BOXES = 0x2D;
	public static final byte PROPERTY_MODEL_STATIC = 0x2E;
	public static final byte PROPERTY_MODEL_DYNAMIC = 0x2F;
	public static final byte PROPERTY_MODEL_BOX = 0x30;
	public static final byte PROPERTY_POSITION = 0x31;
	public static final byte PROPERTY_ROTATION = 0x32;
	public static final byte PROPERTY_GRAPHIC_ANIMATION_SKELETON = 0x33;
	public static final byte PROPERTY_x34 = 0x34;
	public static final byte PROPERTY_x35 = 0x35;
	public static final byte PROPERTY_MODEL_TEXTURE = 0x37;
	public static final byte PROPERTY_MATERIAL_ANIMATION = 0x3D;
	public static final byte PROPERTY_x3E = 0x3E;
	public static final byte PROPERTY_x3F = 0x3F;
	public static final byte PROPERTY_BOUNDING_VOLUME = 0x40;
	public static final byte PROPERTY_BOUNDING_VOLUME_MODEL = 0x41;
	public static final byte PROPERTY_x42 = 0x42;
	public static final byte PROPERTY_CAMERA = 0x43;
	public static final byte PROPERTY_PLANE_NEAR = 0x45;
	public static final byte PROPERTY_PLANE_FAR = 0x46;
	public static final byte PROPERTY_FIELD_OF_VIEW =  0x47;
	public static final byte PROPERTY_AMBIENT_LIGHT = 0x48;
	public static final byte PROPERTY_DIRECTIONAL_LIGHT = 0x49;
	public static final byte PROPERTY_COLOR = 0x4A;
	public static final byte PROPERTY_x4C = 0x4C;

	private Textures textures;
	private Materials materials;
	private Animations animations;
	private Graphics graphics;
	private Graphics graphics2;
	private Skeletons skeletons;
	private Boxes boxes;
	private StaticModels staticModels;
	private DynamicModels dynamicModels;
	private BoxModels boxModels;
	private TextureModels textureModels;
	private MaterialAnimations materialAnimations;
	private BoundingVolumes boundingVolumes;
	private BoundingVolumeModels boundingVolumeModels;
	private Cameras cameras;
	private AmbientLights ambientLights;
	private DirectionalLights directionalLights;

	public byte[] toBytes(){
		//TODO
		return new byte[0];
	}

	public static WDBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		WDBFile wdb = new WDBFile();
		for(BinaryProperty prop : bin.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_TEXTURE){
				WDBFile.fromTextures(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MATERIAL){
				WDBFile.fromMaterials(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_ANIMATION){
				WDBFile.fromAnimations(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_GRAPHIC){
				WDBFile.fromGraphics(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_GRAPHIC2){
				WDBFile.fromGraphics2(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_SKELETON){
				WDBFile.fromSkeletons(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_BOXES){
				WDBFile.fromBoxes(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MODEL_STATIC){
				WDBFile.fromStaticModels(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MODEL_DYNAMIC){
				WDBFile.fromDynamicModels(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MODEL_BOX){
				WDBFile.fromBoxModels(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MODEL_TEXTURE){
				WDBFile.fromTextureModels(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MATERIAL_ANIMATION){
				WDBFile.fromMaterialAnimations(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_BOUNDING_VOLUME){
				WDBFile.fromBoundingVolumes(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_BOUNDING_VOLUME_MODEL){
				WDBFile.fromBoundingVolumeModels(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_CAMERA){
				WDBFile.fromCameras(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_AMBIENT_LIGHT){
				WDBFile.fromAmbientLights(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_DIRECTIONAL_LIGHT){
				WDBFile.fromDirectionalLights(wdb,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		return wdb;
	}

	private static void fromTextures(WDBFile file,BinaryObject obj){
		Textures textures = new Textures();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryString){
				BinaryString str = (BinaryString) token;
				textures.add(str.getString());
			}
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryString){
						BinaryString str = (BinaryString) token2;
						textures.add(str.getString());
					}
				}
			}
		}
		file.textures = textures;
	}

	private static void fromMaterials(WDBFile file,BinaryObject obj){
		Materials materials = new Materials();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryString){
				BinaryString str = (BinaryString) token;
				materials.add(str.getString());
			}
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryString){
						BinaryString str = (BinaryString) token2;
						materials.add(str.getString());
					}
				}
			}
		}
		file.materials = materials;
	}

	private static void fromAnimations(WDBFile file,BinaryObject obj){
		Animations animations = new Animations();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryString){
				BinaryString str = (BinaryString) token;
				animations.add(str.getString());
			}
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryString){
						BinaryString str = (BinaryString) token2;
						animations.add(str.getString());
					}
				}
			}
		}
		file.animations = animations;
	}

	private static void fromGraphics(WDBFile file,BinaryObject obj){
		Graphics graphics = new Graphics();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryString){
				BinaryString str = (BinaryString) token;
				graphics.add(str.getString());
			}
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryString){
						BinaryString str = (BinaryString) token2;
						graphics.add(str.getString());
					}
				}
			}
		}
		file.graphics = graphics;
	}

	private static void fromGraphics2(WDBFile file,BinaryObject obj){
		Graphics graphics2 = new Graphics();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryString){
				BinaryString str = (BinaryString) token;
				graphics2.add(str.getString());
			}
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryString){
						BinaryString str = (BinaryString) token2;
						graphics2.add(str.getString());
					}
				}
			}
		}
		file.graphics2 = graphics2;
	}

	private static void fromSkeletons(WDBFile file,BinaryObject obj){
		Skeletons skeletons = new Skeletons();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryString){
				BinaryString str = (BinaryString) token;
				skeletons.add(str.getString());
			}
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryString){
						BinaryString str = (BinaryString) token2;
						skeletons.add(str.getString());
					}
				}
			}
		}
		file.skeletons = skeletons;
	}

	private static void fromBoxes(WDBFile file,BinaryObject obj){
		Boxes boxes = new Boxes();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryString){
				BinaryString str = (BinaryString) token;
				boxes.add(str.getString());
			}
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryString){
						BinaryString str = (BinaryString) token2;
						boxes.add(str.getString());
					}
				}
			}
		}
		file.boxes = boxes;
	}

	private static void fromStaticModels(WDBFile file,BinaryObject obj){
		StaticModels staticModels = new StaticModels();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MODEL_STATIC){
				BinaryToken first = prop.getValues().get(0);
				if(first instanceof BinaryString){
					WDBFile.fromStaticModel(staticModels,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(1));
					continue;
				}
				WDBFile.fromStaticModel(staticModels,new BinaryString(null),(BinaryObject) prop.getValues().get(0));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.staticModels = staticModels;
	}

	private static void fromStaticModel(StaticModels staticModels,BinaryString str,BinaryObject obj){
		StaticModel staticModel = new StaticModel();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_GRAPHIC){
				int i = 0;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				BinaryIntegerSigned ii = (BinaryIntegerSigned) prop.getValues().get(i);
				int j = 1;
				while(prop.getValues().get(j) instanceof BinaryStruct){
					j++;
				}
				BinaryFloat f = (BinaryFloat) prop.getValues().get(j);
				staticModel.setGraphic(ii.getIntegerSigned());
				staticModel.setGraphicFloat(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat) prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat) prop.getValues().get(2)).getFloat());
				staticModel.setPosition(position);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_ROTATION){
				Rotation rotation = new Rotation();
				rotation.setV1(((BinaryFloat) prop.getValues().get(0)).getFloat());
				rotation.setV2(((BinaryFloat) prop.getValues().get(1)).getFloat());
				rotation.setV3(((BinaryFloat) prop.getValues().get(2)).getFloat());
				rotation.setV4(((BinaryFloat) prop.getValues().get(3)).getFloat());
				rotation.setV5(((BinaryFloat) prop.getValues().get(4)).getFloat());
				rotation.setV6(((BinaryFloat) prop.getValues().get(5)).getFloat());
				staticModel.setRotation(rotation);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x3E){
				WDBFile.fromX3Es(staticModel,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x3F){
				BinaryFloat f1 = (BinaryFloat) prop.getValues().get(0);
				BinaryFloat f2 = (BinaryFloat) prop.getValues().get(1);
				staticModel.setX3F(new float[]{f1.getFloat(),f2.getFloat()});
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x42){
				staticModel.setX42(true);
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		staticModels.put(str.getString(),staticModel);
	}

	private static void fromX3Es(Model model,BinaryObject obj){
		X3Es x3Es = new X3Es();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				X3E x3E = new X3E();
				x3E.setV1(((BinaryIntegerSigned) list.getTokens().get(0)).getIntegerSigned());
				x3E.setV2(((BinaryIntegerSigned) list.getTokens().get(1)).getIntegerSigned());
				x3E.setV3(((BinaryIntegerSigned) list.getTokens().get(2)).getIntegerSigned());
				x3E.setV4(((BinaryIntegerSigned) list.getTokens().get(3)).getIntegerSigned());
				x3Es.add(x3E);
			}
		}
		model.setX3Es(x3Es);
	}

	private static void fromDynamicModels(WDBFile file,BinaryObject obj){
		DynamicModels dynamicModels = new DynamicModels();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MODEL_DYNAMIC){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				WDBFile.fromDynamicModel(dynamicModels,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.dynamicModels = dynamicModels;
	}

	private static void fromDynamicModel(DynamicModels dynamicModels,BinaryString str,BinaryObject obj){
		DynamicModel dynamicModel = new DynamicModel();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_SKELETON){
				BinaryIntegerSigned i1 = (BinaryIntegerSigned) prop.getValues().get(0);
				BinaryIntegerSigned i2 = (BinaryIntegerSigned) prop.getValues().get(1);
				BinaryFloat f = (BinaryFloat) prop.getValues().get(2);
				dynamicModel.setAnimationSkeleton(new int[]{i1.getIntegerSigned(),i2.getIntegerSigned()});
				dynamicModel.setAnimationSkeletonFloat(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat) prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat) prop.getValues().get(2)).getFloat());
				dynamicModel.setPosition(position);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_ROTATION){
				Rotation rotation = new Rotation();
				rotation.setV1(((BinaryFloat) prop.getValues().get(0)).getFloat());
				rotation.setV2(((BinaryFloat) prop.getValues().get(1)).getFloat());
				rotation.setV3(((BinaryFloat) prop.getValues().get(2)).getFloat());
				rotation.setV4(((BinaryFloat) prop.getValues().get(3)).getFloat());
				rotation.setV5(((BinaryFloat) prop.getValues().get(4)).getFloat());
				rotation.setV6(((BinaryFloat) prop.getValues().get(5)).getFloat());
				dynamicModel.setRotation(rotation);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_GRAPHIC_ANIMATION_SKELETON){
				BinaryIntegerSigned i1 = (BinaryIntegerSigned) prop.getValues().get(0);
				BinaryIntegerSigned i2 = (BinaryIntegerSigned) prop.getValues().get(1);
				BinaryIntegerSigned i3 = (BinaryIntegerSigned) prop.getValues().get(2);
				BinaryFloat f = (BinaryFloat) prop.getValues().get(3);
				dynamicModel.setGraphicAnimationSkeleton(new int[]{i1.getIntegerSigned(),i2.getIntegerSigned(),i3.getIntegerSigned()});
				dynamicModel.setGraphicAnimationSkeletonFloat(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x35){
				BinaryIntegerSigned i = (BinaryIntegerSigned) prop.getValues().get(0);
				dynamicModel.setX35(i.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x3E){
				WDBFile.fromX3Es(dynamicModel,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x3F){
				BinaryFloat f1 = (BinaryFloat) prop.getValues().get(0);
				BinaryFloat f2 = (BinaryFloat) prop.getValues().get(1);
				dynamicModel.setX3F(new float[]{f1.getFloat(),f2.getFloat()});
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x42){
				dynamicModel.setX42(true);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x4C){
				dynamicModel.setX4C(true);
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		dynamicModels.put(str.getString(),dynamicModel);
	}

	private static void fromBoxModels(WDBFile file,BinaryObject obj){
		BoxModels boxModels = new BoxModels();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MODEL_BOX){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				WDBFile.fromBoxModel(boxModels,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.boxModels = boxModels;
	}

	private static void fromBoxModel(BoxModels boxModels,BinaryString str,BinaryObject obj){
		BoxModel boxModel = new BoxModel();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat) prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat) prop.getValues().get(2)).getFloat());
				boxModel.setPosition(position);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_ROTATION){
				Rotation rotation = new Rotation();
				rotation.setV1(((BinaryFloat) prop.getValues().get(0)).getFloat());
				rotation.setV2(((BinaryFloat) prop.getValues().get(1)).getFloat());
				rotation.setV3(((BinaryFloat) prop.getValues().get(2)).getFloat());
				rotation.setV4(((BinaryFloat) prop.getValues().get(3)).getFloat());
				rotation.setV5(((BinaryFloat) prop.getValues().get(4)).getFloat());
				rotation.setV6(((BinaryFloat) prop.getValues().get(5)).getFloat());
				boxModel.setRotation(rotation);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x34){
				BinaryIntegerSigned i1 = (BinaryIntegerSigned) prop.getValues().get(0);
				BinaryIntegerSigned i2 = (BinaryIntegerSigned) prop.getValues().get(1);
				BinaryFloat f = (BinaryFloat) prop.getValues().get(2);
				boxModel.setBox(new int[]{i1.getIntegerSigned(),i2.getIntegerSigned()});
				boxModel.setBoxFloat(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x3E){
				WDBFile.fromX3Es(boxModel,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x42){
				boxModel.setX42(true);
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		boxModels.put(str.getString(),boxModel);
	}

	private static void fromTextureModels(WDBFile file,BinaryObject obj){
		TextureModels textureModels = new TextureModels();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MODEL_TEXTURE){
				WDBFile.fromTextureModel(textureModels,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.textureModels = textureModels;
	}

	private static void fromTextureModel(TextureModels textureModels,BinaryObject obj){
		TextureModel textureModel = new TextureModel();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat) prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat) prop.getValues().get(2)).getFloat());
				textureModel.setPosition(position);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_GRAPHIC2){
				BinaryIntegerSigned i1 = (BinaryIntegerSigned) prop.getValues().get(0);
				BinaryIntegerSigned i2 = (BinaryIntegerSigned) prop.getValues().get(1);
				textureModel.setGraphic2s(new int[]{i1.getIntegerSigned(),i2.getIntegerSigned()});
				continue;
			}
			if(prop.getKey().getToken()==0x38){
				Position position = new Position();
				position.setX(((BinaryFloat) prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat) prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat) prop.getValues().get(2)).getFloat());
				textureModel.setPivot(position);
				continue;
			}
			if(prop.getKey().getToken()==0x39){
				BinaryString str = (BinaryString) prop.getValues().get(0);
				textureModel.setTexture(str.getString());
				continue;
			}
			if(prop.getKey().getToken()==0x3A){
				BinaryFloat f = (BinaryFloat) prop.getValues().get(0);
				textureModel.setWidth(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==0x3B){
				BinaryFloat f = (BinaryFloat) prop.getValues().get(0);
				textureModel.setHeight(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==0x3C){
				BinaryFloat f = (BinaryFloat) prop.getValues().get(0);
				textureModel.setCullRadius(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x3E){
				BinaryIntegerSigned i1 = (BinaryIntegerSigned) prop.getValues().get(0);
				BinaryIntegerSigned i2 = (BinaryIntegerSigned) prop.getValues().get(1);
				textureModel.setX3E(new int[]{i1.getIntegerSigned(),i2.getIntegerSigned()});
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		textureModels.add(textureModel);
	}

	private static void fromMaterialAnimations(WDBFile file,BinaryObject obj){
		MaterialAnimations materialAnimations = new MaterialAnimations();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryString){
				BinaryString str = (BinaryString) token;
				materialAnimations.add(str.getString());
			}
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryString){
						BinaryString str = (BinaryString) token2;
						materialAnimations.add(str.getString());
					}
				}
			}
		}
		file.materialAnimations = materialAnimations;
	}

	private static void fromBoundingVolumes(WDBFile file,BinaryObject obj){
		BoundingVolumes boundingVolumes = new BoundingVolumes();
		for(BinaryToken token : obj.getTokens()){
			if(token instanceof BinaryString){
				BinaryString str = (BinaryString) token;
				boundingVolumes.add(str.getString());
			}
			if(token instanceof BinaryList){
				BinaryList list = (BinaryList) token;
				for(BinaryToken token2 : list.getTokens()){
					if(token2 instanceof BinaryString){
						BinaryString str = (BinaryString) token2;
						boundingVolumes.add(str.getString());
					}
				}
			}
		}
		file.boundingVolumes = boundingVolumes;
	}

	private static void fromBoundingVolumeModels(WDBFile file,BinaryObject obj){
		BoundingVolumeModels boundingVolumeModels = new BoundingVolumeModels();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_BOUNDING_VOLUME_MODEL){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				WDBFile.fromBoundingVolumeModel(boundingVolumeModels,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.boundingVolumeModels = boundingVolumeModels;
	}

	private static void fromBoundingVolumeModel(BoundingVolumeModels boundingVolumeModels,BinaryString str,BinaryObject obj){
		BoundingVolumeModel boundingVolumeModel = new BoundingVolumeModel();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) prop.getValues().get(0)).getFloat());
				position.setY(((BinaryFloat) prop.getValues().get(1)).getFloat());
				position.setZ(((BinaryFloat) prop.getValues().get(2)).getFloat());
				boundingVolumeModel.setPosition(position);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_ROTATION){
				Rotation rotation = new Rotation();
				rotation.setV1(((BinaryFloat) prop.getValues().get(0)).getFloat());
				rotation.setV2(((BinaryFloat) prop.getValues().get(1)).getFloat());
				rotation.setV3(((BinaryFloat) prop.getValues().get(2)).getFloat());
				rotation.setV4(((BinaryFloat) prop.getValues().get(3)).getFloat());
				rotation.setV5(((BinaryFloat) prop.getValues().get(4)).getFloat());
				rotation.setV6(((BinaryFloat) prop.getValues().get(5)).getFloat());
				boundingVolumeModel.setRotation(rotation);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_BOUNDING_VOLUME){
				int i = 0;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				BinaryIntegerSigned ii = (BinaryIntegerSigned) prop.getValues().get(i);
				boundingVolumeModel.setBoundingVolume(ii.getIntegerSigned());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		boundingVolumeModels.put(str.getString(),boundingVolumeModel);
	}

	private static void fromCameras(WDBFile file,BinaryObject obj){
		Cameras cameras = new Cameras();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_CAMERA){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				WDBFile.fromCamera(cameras,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.cameras = cameras;
	}

	private static void fromCamera(Cameras cameras,BinaryString str,BinaryObject obj){
		Camera camera = new Camera();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_MODEL_DYNAMIC){
				BinaryIntegerSigned i1 = (BinaryIntegerSigned) prop.getValues().get(0);
				BinaryIntegerSigned i2 = (BinaryIntegerSigned) prop.getValues().get(1);
				camera.setDynamicModel(new int[]{i1.getIntegerSigned(),i2.getIntegerSigned()});
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) prop.getValues().get(0)).getFloat());
				position.setX(((BinaryFloat) prop.getValues().get(1)).getFloat());
				position.setX(((BinaryFloat) prop.getValues().get(2)).getFloat());
				camera.setPosition(position);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_ROTATION){
				Rotation rotation = new Rotation();
				rotation.setV1(((BinaryFloat) prop.getValues().get(0)).getFloat());
				rotation.setV2(((BinaryFloat) prop.getValues().get(1)).getFloat());
				rotation.setV3(((BinaryFloat) prop.getValues().get(2)).getFloat());
				rotation.setV4(((BinaryFloat) prop.getValues().get(3)).getFloat());
				rotation.setV5(((BinaryFloat) prop.getValues().get(4)).getFloat());
				rotation.setV6(((BinaryFloat) prop.getValues().get(5)).getFloat());
				camera.setRotation(rotation);
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_x35){
				BinaryIntegerSigned i = (BinaryIntegerSigned) prop.getValues().get(0);
				camera.setX35(i.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_PLANE_NEAR){
				BinaryFloat f = (BinaryFloat) prop.getValues().get(0);
				camera.setNearPlane(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_PLANE_FAR){
				BinaryFloat f = (BinaryFloat) prop.getValues().get(0);
				camera.setFarPlane(f.getFloat());
				continue;
			}
			if(prop.getKey().getToken()==WDBFile.PROPERTY_FIELD_OF_VIEW){
				BinaryFloat f = (BinaryFloat) prop.getValues().get(0);
				camera.setFieldOfView(f.getFloat());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		cameras.put(str.getString(),camera);
	}

	private static void fromAmbientLights(WDBFile file,BinaryObject obj){
		AmbientLights ambientLights = new AmbientLights();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_AMBIENT_LIGHT){
				WDBFile.fromAmbientLight(ambientLights,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.ambientLights = ambientLights;
	}

	private static void fromAmbientLight(AmbientLights ambientLights,BinaryString str,BinaryObject obj){
		AmbientLight ambientLight = new AmbientLight();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_COLOR){
				Color color = new Color();
				color.setRed(((BinaryIntegerSigned)prop.getValues().get(0)).getIntegerSigned());
				color.setGreen(((BinaryIntegerSigned)prop.getValues().get(1)).getIntegerSigned());
				color.setBlue(((BinaryIntegerSigned)prop.getValues().get(2)).getIntegerSigned());
				ambientLight.setColor(color);
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		ambientLights.put(str.getString(),ambientLight);
	}

	private static void fromDirectionalLights(WDBFile file,BinaryObject obj){
		DirectionalLights directionalLights = new DirectionalLights();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_DIRECTIONAL_LIGHT){
				WDBFile.fromDirectionalLight(directionalLights,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.directionalLights = directionalLights;
	}

	private static void fromDirectionalLight(DirectionalLights directionalLights,BinaryString str,BinaryObject obj){
		DirectionalLight directionalLight = new DirectionalLight();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==WDBFile.PROPERTY_COLOR){
				Color color = new Color();
				color.setRed(((BinaryIntegerSigned)prop.getValues().get(0)).getIntegerSigned());
				color.setGreen(((BinaryIntegerSigned)prop.getValues().get(1)).getIntegerSigned());
				color.setBlue(((BinaryIntegerSigned)prop.getValues().get(2)).getIntegerSigned());
				directionalLight.setColor(color);
				continue;
			}
			if(prop.getKey().getToken()==75){
				Direction direction = new Direction();
				direction.setX(((BinaryFloat)prop.getValues().get(0)).getFloat());
				direction.setY(((BinaryFloat)prop.getValues().get(1)).getFloat());
				direction.setZ(((BinaryFloat)prop.getValues().get(2)).getFloat());
				directionalLight.setDirection(direction);
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		directionalLights.put(str.getString(),directionalLight);
	}

	@Override
	public String toString() {
		return "WDBFile{" +
				"textures=" + textures +
				", materials=" + materials +
				", animations=" + animations +
				", graphics=" + graphics +
				", graphics2=" + graphics2 +
				", skeletons=" + skeletons +
				", boxes=" + boxes +
				", staticModels=" + staticModels +
				", dynamicModels=" + dynamicModels +
				", boxModels=" + boxModels +
				", textureModels=" + textureModels +
				", materialAnimations=" + materialAnimations +
				", boundingVolumes=" + boundingVolumes +
				", boundingVolumeModels=" + boundingVolumeModels +
				", cameras=" + cameras +
				", ambientLights=" + ambientLights +
				", directionalLights=" + directionalLights +
				'}';
	}

}