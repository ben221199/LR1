package com.lego.racers.file.cdb;

import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryList;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.binary.object.Position;
import com.lego.racers.file.cdb.object.AmbientLight;
import com.lego.racers.file.cdb.object.AmbientLights;
import com.lego.racers.file.cdb.object.Camera;
import com.lego.racers.file.cdb.object.Cameras;
import com.lego.racers.file.cdb.object.Color;
import com.lego.racers.file.cdb.object.Cutscene;
import com.lego.racers.file.cdb.object.Cutscenes;
import com.lego.racers.file.cdb.object.Direction;
import com.lego.racers.file.cdb.object.DirectionalLight;
import com.lego.racers.file.cdb.object.DirectionalLights;
import com.lego.racers.file.cdb.object.Event;
import com.lego.racers.file.cdb.object.Events;
import com.lego.racers.file.cdb.object.Model;
import com.lego.racers.file.cdb.object.Models;
import com.lego.racers.file.cdb.object.Rotation;
import com.lego.racers.file.cdb.object.SceneFiles;
import com.lego.racers.file.cdb.object._Unknown54;
import com.lego.racers.file.cdb.object._Unknowns54;

public class CDBFile{

	public static final byte BLOCK_CUTSCENE = 0x27;
	public static final byte BLOCK_CAMERA = 0x29;
	public static final byte BLOCK_MODEL = 0x2E;
	public static final byte BLOCK_AMBIENT_LIGHT = 0x35;
	public static final byte BLOCK_EVENT = 0x37;
	public static final byte BLOCK_DIRECTIONAL_LIGHT = 0x3A;

	public static final byte PROPERTY_CUTSCENES = 0x27;
	public static final byte PROPERTY_SCENE_FILES = 0x28;
	public static final byte PROPERTY_CAMERAS = 0x29;
	public static final byte PROPERTY_CAMERA_NAME = 0x2A;
	public static final byte PROPERTY_STARTFRAME = 0x2B;
	public static final byte PROPERTY_DURATION = 0x2C;
	public static final byte PROPERTY_ANIMATION_SEQUENCE = 0x2D;
	public static final byte PROPERTY_MODELS = 0x2E;
	public static final byte PROPERTY_MODEL_NAME_STATIC = 0x2F;
	public static final byte PROPERTY_MODEL_NAME = 0x30;
	public static final byte PROPERTY_50 = 0x32;
	public static final byte PROPERTY_POSITION = 0x33;
	public static final byte PROPERTY_ROTATION = 0x34;
	public static final byte PROPERTY_AMBIENT_LIGHTS = 0x35;
	public static final byte PROPERTY_54 = 0x36;
	public static final byte PROPERTY_EVENTS = 0x37;
	public static final byte PROPERTY_LIGHT_COLOR = 0x38;
	public static final byte PROPERTY_LIGHT_DIRECTION = 0x39;
	public static final byte PROPERTY_DIRECTIONAL_LIGHTS = 0x3A;
	public static final byte PROPERTY_SPEED = 0x3B;
	public static final byte PROPERTY_LIGHT_STROBE = 0x3C;

	private Cutscenes cutscenes;
	private SceneFiles sceneFiles;

	public static CDBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		CDBFile cdb = new CDBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==CDBFile.PROPERTY_CUTSCENES){
				CDBFile.initCutscenes(cdb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CDBFile.PROPERTY_SCENE_FILES){
				CDBFile.initSceneFiles(cdb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return cdb;
	}

	private static void initCutscenes(CDBFile file,BinaryObject obj){
		Cutscenes cutscenes = new Cutscenes();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()== CDBFile.BLOCK_CUTSCENE){
				CDBFile.initCutscene(cutscenes,(BinaryString)obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.cutscenes = cutscenes;
	}

	private static void initCutscene(Cutscenes cutscenes,BinaryString str,BinaryObject obj){
		Cutscene cutscene = new Cutscene();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CDBFile.PROPERTY_CAMERAS){
				CDBFile.initCameras(cutscene,(BinaryObject) obj.getTokens().get(i+2));
			}
			if(token.getToken()==CDBFile.PROPERTY_DURATION){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				cutscene.setDuration(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_MODELS){
				CDBFile.initModels(cutscene,(BinaryObject) obj.getTokens().get(i+2));
			}
			if(token.getToken()==CDBFile.PROPERTY_AMBIENT_LIGHTS){
				CDBFile.initAmbientLights(cutscene,(BinaryObject) obj.getTokens().get(i+2));
			}
			if(token.getToken()==CDBFile.PROPERTY_EVENTS){
				CDBFile.initEvents(cutscene,(BinaryObject) obj.getTokens().get(i+2));
			}
			if(token.getToken()==CDBFile.PROPERTY_DIRECTIONAL_LIGHTS){
				CDBFile.initDirectionalLights(cutscene,(BinaryObject) obj.getTokens().get(i+2));
			}
			if(token.getToken()==CDBFile.PROPERTY_SPEED){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				cutscene.setSpeed(ii.getIntegerSigned());
			}
		}
		cutscenes.put(str.getString(),cutscene);
	}

	private static void initCameras(Cutscene cutscene,BinaryObject obj){
		Cameras cameras = new Cameras();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()== CDBFile.BLOCK_CAMERA){
				CDBFile.initCamera(cameras,(BinaryString)obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		cutscene.setCameras(cameras);
	}

	private static void initCamera(Cameras cameras,BinaryString str,BinaryObject obj){
		Camera camera = new Camera();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CDBFile.PROPERTY_CAMERA_NAME){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				camera.setName(string.getString());
			}
			if(token.getToken()==CDBFile.PROPERTY_STARTFRAME){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				camera.setStartFrame(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_DURATION){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				camera.setDuration(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_ANIMATION_SEQUENCE){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				camera.setAnimationSequenceId(ii.getIntegerSigned());
			}
		}
		cameras.put(str.getString(),camera);
	}

	private static void initModels(Cutscene cutscene,BinaryObject obj){
		Models models = new Models();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()== CDBFile.BLOCK_MODEL){
				CDBFile.initModel(models,(BinaryString)obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		cutscene.setModels(models);
	}

	private static void initModel(Models models,BinaryString str,BinaryObject obj){
		Model model = new Model();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);

			if(token.getToken()==CDBFile.PROPERTY_STARTFRAME){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				model.setStartFrame(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_DURATION){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				model.setDuration(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_ANIMATION_SEQUENCE){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				model.setAnimationSequenceId(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_MODEL_NAME_STATIC){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				model.setNameStatic(string.getString());
			}
			if(token.getToken()==CDBFile.PROPERTY_MODEL_NAME){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				model.setName(string.getString());
			}
			if(token.getToken()==CDBFile.PROPERTY_50){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				BinaryIntegerSigned ii2 = (BinaryIntegerSigned) obj.getTokens().get(i+2);
				model.setP50_1(ii.getIntegerSigned());
				model.setP50_2(ii2.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) obj.getTokens().get(i+1)).getFloat());
				position.setY(((BinaryFloat) obj.getTokens().get(i+2)).getFloat());
				position.setZ(((BinaryFloat) obj.getTokens().get(i+3)).getFloat());
				model.setPosition(position);
			}
			if(token.getToken()==CDBFile.PROPERTY_ROTATION){
				Rotation rotation = new Rotation();
				BinaryList list = (BinaryList) obj.getTokens().get(i+1);
				rotation.setV1(((BinaryFloat) list.getTokens().get(0)).getFloat());
				rotation.setV2(((BinaryFloat) list.getTokens().get(1)).getFloat());
				rotation.setV3(((BinaryFloat) list.getTokens().get(2)).getFloat());
				rotation.setV4(((BinaryFloat) list.getTokens().get(3)).getFloat());
				rotation.setV5(((BinaryFloat) list.getTokens().get(4)).getFloat());
				rotation.setV6(((BinaryFloat) list.getTokens().get(5)).getFloat());
				model.setRotation(rotation);
			}
			if(token.getToken()==CDBFile.PROPERTY_54){
				_Unknowns54 _unknowns54 = new _Unknowns54();
				BinaryObject obj2 = (BinaryObject) obj.getTokens().get(i+2);
				BinaryList list = (BinaryList) obj2.getTokens().get(0);
				for(int j=0;j<obj2.getTokens().size();j+=5){
					_Unknown54 _unknown54 = new _Unknown54();
					_unknown54.setV1(((BinaryIntegerSigned) list.getTokens().get(j)).getIntegerSigned());
					_unknown54.setV2(((BinaryIntegerSigned) list.getTokens().get(j+1)).getIntegerSigned());
					_unknown54.setV3(((BinaryIntegerSigned) list.getTokens().get(j+2)).getIntegerSigned());
					_unknown54.setV4(((BinaryIntegerSigned) list.getTokens().get(j+3)).getIntegerSigned());
					_unknown54.setV5(((BinaryIntegerSigned) list.getTokens().get(j+4)).getIntegerSigned());
					_unknowns54.add(_unknown54);
				}
				model.setP54(_unknowns54);
			}
		}
		models.put(str.getString(),model);
	}

	private static void initAmbientLights(Cutscene cutscene,BinaryObject obj){
		AmbientLights ambientLights = new AmbientLights();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()== CDBFile.BLOCK_AMBIENT_LIGHT){
				CDBFile.initAmbientLight(ambientLights,(BinaryString)obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		cutscene.setAmbientLights(ambientLights);
	}

	private static void initAmbientLight(AmbientLights ambientLights,BinaryString str,BinaryObject obj){
		AmbientLight ambientLight = new AmbientLight();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CDBFile.PROPERTY_STARTFRAME){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				ambientLight.setStartFrame(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_DURATION){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				ambientLight.setDuration(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_LIGHT_COLOR){
				Color color = new Color();
				color.setRed(((BinaryIntegerSigned) obj.getTokens().get(i+1)).getIntegerSigned());
				color.setGreen(((BinaryIntegerSigned) obj.getTokens().get(i+2)).getIntegerSigned());
				color.setBlue(((BinaryIntegerSigned) obj.getTokens().get(i+3)).getIntegerSigned());
				ambientLight.setColor(color);
			}
		}
		ambientLights.put(str.getString(),ambientLight);
	}

	private static void initEvents(Cutscene cutscene,BinaryObject obj){
		Events events = new Events();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()== CDBFile.BLOCK_EVENT){
				CDBFile.initEvent(events,(BinaryString)obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		cutscene.setEvents(events);
	}

	private static void initEvent(Events events,BinaryString str,BinaryObject obj){
		Event event = new Event();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CDBFile.PROPERTY_STARTFRAME){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				event.setStartFrame(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_DURATION){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				event.setDuration(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_POSITION){
				Position position = new Position();
				position.setX(((BinaryFloat) obj.getTokens().get(i+1)).getFloat());
				position.setY(((BinaryFloat) obj.getTokens().get(i+2)).getFloat());
				position.setZ(((BinaryFloat) obj.getTokens().get(i+3)).getFloat());
				event.setPosition(position);
			}
			if(token.getToken()==CDBFile.PROPERTY_ROTATION){
				Rotation rotation = new Rotation();
				BinaryList list = (BinaryList) obj.getTokens().get(i+1);
				rotation.setV1(((BinaryFloat) list.getTokens().get(0)).getFloat());
				rotation.setV2(((BinaryFloat) list.getTokens().get(1)).getFloat());
				rotation.setV3(((BinaryFloat) list.getTokens().get(2)).getFloat());
				rotation.setV4(((BinaryFloat) list.getTokens().get(3)).getFloat());
				rotation.setV5(((BinaryFloat) list.getTokens().get(4)).getFloat());
				rotation.setV6(((BinaryFloat) list.getTokens().get(5)).getFloat());
				event.setRotation(rotation);
			}
		}
		events.put(str.getString(),event);
	}

	private static void initDirectionalLights(Cutscene cutscene,BinaryObject obj){
		DirectionalLights directionalLights = new DirectionalLights();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()== CDBFile.BLOCK_DIRECTIONAL_LIGHT){
				CDBFile.initDirectionalLight(directionalLights,(BinaryString)obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		cutscene.setDirectionalLights(directionalLights);
	}

	private static void initDirectionalLight(DirectionalLights directionalLights,BinaryString str,BinaryObject obj){
		DirectionalLight directionalLight = new DirectionalLight();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CDBFile.PROPERTY_STARTFRAME){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				directionalLight.setStartFrame(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_DURATION){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				directionalLight.setDuration(ii.getIntegerSigned());
			}
			if(token.getToken()==CDBFile.PROPERTY_LIGHT_COLOR){
				Color color = new Color();
				color.setRed(((BinaryIntegerSigned) obj.getTokens().get(i+1)).getIntegerSigned());
				color.setGreen(((BinaryIntegerSigned) obj.getTokens().get(i+2)).getIntegerSigned());
				color.setBlue(((BinaryIntegerSigned) obj.getTokens().get(i+3)).getIntegerSigned());
				directionalLight.setColor(color);
			}
			if(token.getToken()==CDBFile.PROPERTY_LIGHT_DIRECTION){
				Direction direction = new Direction();
				direction.setX(((BinaryFloat) obj.getTokens().get(i+1)).getFloat());
				direction.setY(((BinaryFloat) obj.getTokens().get(i+2)).getFloat());
				direction.setZ(((BinaryFloat) obj.getTokens().get(i+3)).getFloat());
				directionalLight.setDirection(direction);
			}
			if(token.getToken()==CDBFile.PROPERTY_LIGHT_STROBE){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				BinaryIntegerSigned ii2 = (BinaryIntegerSigned) obj.getTokens().get(i+2);
				directionalLight.setStrobeOn(ii.getIntegerSigned());
				directionalLight.setStrobeOn(ii2.getIntegerSigned());
			}
		}
		directionalLights.put(str.getString(),directionalLight);
	}

	private static void initSceneFiles(CDBFile file,BinaryObject obj){
		SceneFiles sceneFiles = new SceneFiles();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryString string = (BinaryString) obj.getTokens().get(i);
			sceneFiles.add(string.getString());
		}
		file.sceneFiles = sceneFiles;
	}

	@Override
	public String toString() {
		return "CDBFile{" +
				"cutscenes=" + cutscenes +
				", sceneFiles=" + sceneFiles +
				'}';
	}

}
