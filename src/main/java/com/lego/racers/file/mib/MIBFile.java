package com.lego.racers.file.mib;

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
import com.lego.racers.binary.object.ColorARGB;
import com.lego.racers.file.mib.object.Arrow;
import com.lego.racers.file.mib.object.Arrows;
import com.lego.racers.file.mib.object.Button;
import com.lego.racers.file.mib.object.Buttons;
import com.lego.racers.file.mib.object.Coloring;
import com.lego.racers.file.mib.object.Colorings;
import com.lego.racers.file.mib.object.Controller;
import com.lego.racers.file.mib.object.Controllers;
import com.lego.racers.file.mib.object.Frame;
import com.lego.racers.file.mib.object.Frames;
import com.lego.racers.file.mib.object.Image;
import com.lego.racers.file.mib.object.Images;
import com.lego.racers.file.mib.object.Input;
import com.lego.racers.file.mib.object.Inputs;
import com.lego.racers.file.mib.object.Item;
import com.lego.racers.file.mib.object.Positioning;
import com.lego.racers.file.mib.object.Rectangle;
import com.lego.racers.file.mib.object.Rocker;
import com.lego.racers.file.mib.object.Rockers;
import com.lego.racers.file.mib.object.ComplexScene;
import com.lego.racers.file.mib.object.ComplexScenes;
import com.lego.racers.file.mib.object.Scene;
import com.lego.racers.file.mib.object.Scenes;
import com.lego.racers.file.mib.object.Selector;
import com.lego.racers.file.mib.object.Selectors;
import com.lego.racers.file.mib.object.Slider;
import com.lego.racers.file.mib.object.Sliders;
import com.lego.racers.file.mib.object.Text;
import com.lego.racers.file.mib.object.Texts;
import com.lego.racers.file.mib.object.X2B;

import java.util.Map;

public class MIBFile{

	public static final byte PROPERTY_AMOUNT = 0x27;
	public static final byte PROPERTY_COLORINGS = 0x37;
	public static final byte PROPERTY_IMAGES = 0x38;
	public static final byte PROPERTY_TEXTS = 0x39;
	public static final byte PROPERTY_FRAMES = 0x3A;
	public static final byte PROPERTY_ARROWS = 0x3B;
	public static final byte PROPERTY_ROCKERS = 0x3D;
	public static final byte PROPERTY_CONTROLLERS = 0x3E;
	public static final byte PROPERTY_SELECTORS = 0x3F;
	public static final byte PROPERTY_SLIDERS = 0x40;
	public static final byte PROPERTY_SCENES_COMPLEX = 0x42;
	public static final byte PROPERTY_INPUTS = 0x43;
	public static final byte PROPERTY_SCENES = 0x45;
	public static final byte PROPERTY_BUTTONS = 0x46;

	public static final byte PROPERTY_COLORING = 0x37;
	public static final byte PROPERTY_IMAGE = 0x38;
	public static final byte PROPERTY_TEXT = 0x39;
	public static final byte PROPERTY_FRAME = 0x3A;
	public static final byte PROPERTY_ARROW = 0x3B;
	public static final byte PROPERTY_ROCKER = 0x3D;
	public static final byte PROPERTY_CONTROLLER = 0x3E;
	public static final byte PROPERTY_SELECTOR = 0x3F;
	public static final byte PROPERTY_SLIDER = 0x40;
	public static final byte PROPERTY_SCENE_COMPLEX = 0x42;
	public static final byte PROPERTY_INPUT = 0x43;
	public static final byte PROPERTY_SCENE = 0x45;
	public static final byte PROPERTY_BUTTON = 0x46;

	public static final byte PROPERTY_BORDER = 0x28;
	public static final byte PROPERTY_FONT = 0x29;
	public static final byte PROPERTY_TINT = 0x2A;
	public static final byte PROPERTY_NAME = 0x2D;
	public static final byte PROPERTY_RECTANGLE = 0x2F;
	public static final byte PROPERTY_PARENT = 0x31;
	public static final byte PROPERTY_LIMIT = 0x33;
	public static final byte PROPERTY_POSITIONING = 0x36;

	public static final byte PROPERTY_x28 = 0x28;
	public static final byte PROPERTY_x2B = 0x2B;
	public static final byte PROPERTY_x2C = 0x2C;
	public static final byte PROPERTY_x2E = 0x2E;
	public static final byte PROPERTY_x2F = 0x2F;
	public static final byte PROPERTY_x32 = 0x32;
	public static final byte PROPERTY_x33 = 0x33;

	private Integer amount;
	private Colorings colorings;
	private Images images;
	private Texts texts;
	private Frames frames;
	private Arrows arrows;
	private Rockers rockers;
	private Controllers controllers;
	private Selectors selectors;
	private Sliders sliders;
	private ComplexScenes complexScenes;
	private Inputs inputs;
	private Scenes scenes;
	private Buttons buttons;

	public Integer getAmount(){
		return this.amount;
	}

	public void setAmount(Integer amount){
		this.amount = amount;
	}

	public Colorings getColorings(){
		return this.colorings;
	}

	public void setColorings(Colorings colorings){
		this.colorings = colorings;
	}

	public Images getImages(){
		return this.images;
	}

	public void setImages(Images images){
		this.images = images;
	}

	public Texts getTexts(){
		return this.texts;
	}

	public void setTexts(Texts texts){
		this.texts = texts;
	}

	public Frames getFrames(){
		return this.frames;
	}

	public void setFrames(Frames frames){
		this.frames = frames;
	}

	public Arrows getArrows(){
		return this.arrows;
	}

	public void setArrows(Arrows arrows){
		this.arrows = arrows;
	}

	public Rockers getRockers(){
		return this.rockers;
	}

	public void setRockers(Rockers rockers){
		this.rockers = rockers;
	}

	public Controllers getControllers(){
		return this.controllers;
	}

	public void setControllers(Controllers controllers){
		this.controllers = controllers;
	}

	public Selectors getSelectors(){
		return this.selectors;
	}

	public void setSelectors(Selectors selectors){
		this.selectors = selectors;
	}

	public Sliders getSliders(){
		return this.sliders;
	}

	public void setSliders(Sliders sliders){
		this.sliders = sliders;
	}

	public ComplexScenes getComplexScenes(){
		return this.complexScenes;
	}

	public void setComplexScenes(ComplexScenes complexScenes){
		this.complexScenes = complexScenes;
	}

	public Inputs getInputs(){
		return this.inputs;
	}

	public void setInputs(Inputs inputs){
		this.inputs = inputs;
	}

	public Scenes getScenes(){
		return this.scenes;
	}

	public void setScenes(Scenes scenes){
		this.scenes = scenes;
	}

	public Buttons getButtons(){
		return this.buttons;
	}

	public void setButtons(Buttons buttons){
		this.buttons = buttons;
	}

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		return bin.toBytes();
	}

	public static MIBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		MIBFile mib = new MIBFile();
		for(BinaryProperty prop : bin.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_AMOUNT){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				mib.amount = integer.getIntegerSigned();
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_COLORINGS){
				MIBFile.fromColorings(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_IMAGES){
				MIBFile.fromImages(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_TEXTS){
				MIBFile.fromTexts(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_FRAMES){
				MIBFile.fromFrames(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_ARROWS){
				MIBFile.fromArrows(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_ROCKERS){
				MIBFile.fromRockers(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_CONTROLLERS){
				MIBFile.fromControllers(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_SELECTORS){
				MIBFile.fromSelectors(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_SLIDERS){
				MIBFile.fromSliders(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_SCENES_COMPLEX){
				MIBFile.fromScenesComplex(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_INPUTS){
				MIBFile.fromInputs(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_SCENES){
				MIBFile.fromScenes(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_BUTTONS){
				MIBFile.fromButtons(mib,(BinaryObject) prop.getValues().get(1));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		return mib;
	}

	private static void fromColorings(MIBFile file,BinaryObject obj){
		Colorings colorings = new Colorings();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_COLORING){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromColoring(colorings,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.colorings = colorings;
	}

	private static void fromImages(MIBFile file,BinaryObject obj){
		Images images = new Images();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_IMAGE){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromImage(images,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.images = images;
	}

	private static void fromTexts(MIBFile file,BinaryObject obj){
		Texts texts = new Texts();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_TEXT){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromText(texts,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.texts = texts;
	}

	private static void fromFrames(MIBFile file,BinaryObject obj){
		Frames frames = new Frames();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_FRAME){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromFrame(frames,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.frames = frames;
	}

	private static void fromArrows(MIBFile file,BinaryObject obj){
		Arrows arrows = new Arrows();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_ARROW){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromArrow(arrows,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.arrows = arrows;
	}

	private static void fromRockers(MIBFile file,BinaryObject obj){
		Rockers rockers = new Rockers();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_ROCKER){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromRocker(rockers,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.rockers = rockers;
	}

	private static void fromControllers(MIBFile file,BinaryObject obj){
		Controllers controllers = new Controllers();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_CONTROLLER){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromController(controllers,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.controllers = controllers;
	}

	private static void fromSelectors(MIBFile file,BinaryObject obj){
		Selectors selectors = new Selectors();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_SELECTOR){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromSelector(selectors,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.selectors = selectors;
	}

	private static void fromSliders(MIBFile file,BinaryObject obj){
		Sliders sliders = new Sliders();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_SLIDER){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromSlider(sliders,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.sliders = sliders;
	}

	private static void fromScenesComplex(MIBFile file,BinaryObject obj){
		ComplexScenes complexScenes = new ComplexScenes();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_SCENE_COMPLEX){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromSceneComplex(complexScenes,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.complexScenes = complexScenes;
	}

	private static void fromInputs(MIBFile file,BinaryObject obj){
		Inputs inputs = new Inputs();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_INPUT){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromInput(inputs,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.inputs = inputs;
	}

	private static void fromScenes(MIBFile file,BinaryObject obj){
		Scenes scenes = new Scenes();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_SCENE){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromScene(scenes,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.scenes = scenes;
	}

	private static void fromButtons(MIBFile file,BinaryObject obj){
		Buttons buttons = new Buttons();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_BUTTON){
				int i = 1;
				while(prop.getValues().get(i) instanceof BinaryStruct){
					i++;
				}
				MIBFile.fromButton(buttons,(BinaryString) prop.getValues().get(0),(BinaryObject) prop.getValues().get(i));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		file.buttons = buttons;
	}

	private static void fromColoring(Colorings colorings,BinaryString str,BinaryObject obj){
		Coloring coloring = new Coloring();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_TINT){
				ColorARGB tint = new ColorARGB();
				BinaryList list = (BinaryList) prop.getValues().get(0);
				tint.setAlpha(((BinaryIntegerSigned) list.getTokens().get(0)).getIntegerSigned());
				tint.setRed(((BinaryIntegerSigned) list.getTokens().get(1)).getIntegerSigned());
				tint.setGreen(((BinaryIntegerSigned) list.getTokens().get(2)).getIntegerSigned());
				tint.setBlue(((BinaryIntegerSigned) list.getTokens().get(3)).getIntegerSigned());
				coloring.setTint(tint);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_RECTANGLE){
				Rectangle rectangle = new Rectangle();
				BinaryList list = (BinaryList) prop.getValues().get(0);
				rectangle.setX1(((BinaryIntegerSigned) list.getTokens().get(0)).getIntegerSigned());
				rectangle.setY1(((BinaryIntegerSigned) list.getTokens().get(1)).getIntegerSigned());
				rectangle.setX2(((BinaryIntegerSigned) list.getTokens().get(2)).getIntegerSigned());
				rectangle.setY2(((BinaryIntegerSigned) list.getTokens().get(3)).getIntegerSigned());
				coloring.setRectangle(rectangle);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_PARENT){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				coloring.setParent(string.getString());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		colorings.put(str.getString(),coloring);
	}

	private static void fromImage(Images images,BinaryString str,BinaryObject obj){
		Image image = new Image();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x28){
				BinaryString x28 = (BinaryString) prop.getValues().get(0);
				image.setX28(x28.getString());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(image,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		images.put(str.getString(),image);
	}

	private static void fromText(Texts texts,BinaryString str,BinaryObject obj){
		Text text = new Text();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x33){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				text.setX33(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(text,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		texts.put(str.getString(),text);
	}

	private static void fromFrame(Frames frames,BinaryString str,BinaryObject obj){
		Frame frame = new Frame();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_BORDER){
				Frame.Border border = new Frame.Border();
				BinaryList list = (BinaryList) prop.getValues().get(0);
				if(!list.getTokens().isEmpty()){
					border.setUpperLeft(((BinaryString) list.getTokens().get(0)).getString());
					border.setTop(((BinaryString) list.getTokens().get(1)).getString());
					border.setUpperRight(((BinaryString) list.getTokens().get(2)).getString());
					border.setRight(((BinaryString) list.getTokens().get(3)).getString());
					border.setBottomRight(((BinaryString) list.getTokens().get(4)).getString());
					border.setBottom(((BinaryString) list.getTokens().get(5)).getString());
					border.setBottomLeft(((BinaryString) list.getTokens().get(6)).getString());
					border.setLeft(((BinaryString) list.getTokens().get(7)).getString());
				}
				frame.setBorder(border);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_TINT){
				Frame.Tint tint = new Frame.Tint();
				BinaryList list = (BinaryList) prop.getValues().get(0);
				if(!list.getTokens().isEmpty()){
					ColorARGB border = new ColorARGB();
					border.setAlpha(((BinaryIntegerSigned) list.getTokens().get(0)).getIntegerSigned());
					border.setRed(((BinaryIntegerSigned) list.getTokens().get(1)).getIntegerSigned());
					border.setGreen(((BinaryIntegerSigned) list.getTokens().get(2)).getIntegerSigned());
					border.setBlue(((BinaryIntegerSigned) list.getTokens().get(3)).getIntegerSigned());
					tint.setBorder(border);
					ColorARGB background = new ColorARGB();
					background.setAlpha(((BinaryIntegerSigned) list.getTokens().get(4)).getIntegerSigned());
					background.setRed(((BinaryIntegerSigned) list.getTokens().get(5)).getIntegerSigned());
					background.setGreen(((BinaryIntegerSigned) list.getTokens().get(6)).getIntegerSigned());
					background.setBlue(((BinaryIntegerSigned) list.getTokens().get(7)).getIntegerSigned());
					tint.setBorder(background);
				}
				frame.setTint(tint);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x33){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				frame.setX33(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(frame,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		frames.put(str.getString(),frame);
	}

	private static void fromArrow(Arrows arrows,BinaryString str,BinaryObject obj){
		Arrow arrow = new Arrow();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x28){
				Arrow.States states = new Arrow.States();
				BinaryList list = (BinaryList) prop.getValues().get(0);
				if(!list.getTokens().isEmpty()){
					states.setP1(((BinaryString) list.getTokens().get(0)).getString());
					states.setP2(((BinaryString) list.getTokens().get(1)).getString());
					states.setP3(((BinaryString) list.getTokens().get(2)).getString());
					states.setP4(((BinaryString) list.getTokens().get(3)).getString());
					states.setP5(((BinaryString) list.getTokens().get(4)).getString());
					states.setP6(((BinaryString) list.getTokens().get(5)).getString());
				}
				arrow.setX28(states);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x2B){
				X2B x2B = new X2B();
				BinaryList list = (BinaryList) prop.getValues().get(0);
				x2B.setP1(((BinaryIntegerSigned) list.getTokens().get(0)).getIntegerSigned());
				x2B.setP2(((BinaryIntegerSigned) list.getTokens().get(1)).getIntegerSigned());
				x2B.setP3(((BinaryIntegerSigned) list.getTokens().get(2)).getIntegerSigned());
				x2B.setP4(((BinaryIntegerSigned) list.getTokens().get(3)).getIntegerSigned());
				x2B.setP5(((BinaryIntegerSigned) list.getTokens().get(4)).getIntegerSigned());
				arrow.setX2B(x2B);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x2C){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				arrow.setX2C(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x32){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				arrow.setX2C(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(arrow,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		arrows.put(str.getString(),arrow);
	}

	private static void fromRocker(Rockers rockers,BinaryString str,BinaryObject obj){
		Rocker rocker = new Rocker();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==0x28){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				rocker.setX28(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==0x3B){
				String s = "";
				BinaryString string = new BinaryString(s);
				Arrows arrows = new Arrows();
				MIBFile.fromArrow(arrows,string,(BinaryObject) prop.getValues().get(0));
				rocker.setArrow(arrows.get(s));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		rockers.put(str.getString(),rocker);
	}

	private static void fromController(Controllers controllers,BinaryString str,BinaryObject obj){
		Controller controller = new Controller();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x2C){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				controller.setX2C(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x33){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				controller.setX33(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(controller,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_FRAME){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				controller.setFrame(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_ARROWS){
				BinaryString arrow1 = (BinaryString) prop.getValues().get(0);
				BinaryString arrow2 = (BinaryString) prop.getValues().get(1);
				Arrow.Pair arrows = new Arrow.Pair();
				arrows.setArrow1(arrow1.getString());
				arrows.setArrow2(arrow2.getString());
				controller.setArrows(arrows);
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		controllers.put(str.getString(),controller);
	}

	private static void fromSelector(Selectors selectors,BinaryString str,BinaryObject obj){
		Selector selector = new Selector();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x2E){
				Float[] arr = new Float[9];
				BinaryList list = (BinaryList) prop.getValues().get(0);
				if(!list.getTokens().isEmpty()){
					arr[0] = ((BinaryFloat) list.getTokens().get(0)).getFloat();
					arr[1] = ((BinaryFloat) list.getTokens().get(1)).getFloat();
					arr[2] = ((BinaryFloat) list.getTokens().get(2)).getFloat();
					arr[3] = ((BinaryFloat) list.getTokens().get(3)).getFloat();
					arr[4] = ((BinaryFloat) list.getTokens().get(4)).getFloat();
					arr[5] = ((BinaryFloat) list.getTokens().get(5)).getFloat();
					arr[6] = ((BinaryFloat) list.getTokens().get(6)).getFloat();
					arr[7] = ((BinaryFloat) list.getTokens().get(7)).getFloat();
					arr[8] = ((BinaryFloat) list.getTokens().get(8)).getFloat();
				}
				selector.setX2E(arr);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x2F){
				BinaryList list = (BinaryList) prop.getValues().get(0);
				Integer[] arr = new Integer[list.getTokens().size()];
				for(int i=0;i<arr.length;i++){
					arr[i] = ((BinaryIntegerSigned) list.getTokens().get(i)).getIntegerSigned();
				}
				selector.setX2F(arr);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x33){
				Selector.X33 x33 = new Selector.X33();
				x33.setP1(((BinaryIntegerSigned) prop.getValues().get(0)).getIntegerSigned());
				x33.setP2(((BinaryIntegerSigned) prop.getValues().get(1)).getIntegerSigned());
				x33.setP3(((BinaryFloat) prop.getValues().get(2)).getFloat());
				selector.setX33(x33);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(selector,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		selectors.put(str.getString(),selector);
	}

	private static void fromSlider(Sliders sliders,BinaryString str,BinaryObject obj){
		Slider slider = new Slider();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x33){
				Slider.X33 x33 = new Slider.X33();
				x33.setP1(((BinaryIntegerSigned) prop.getValues().get(0)).getIntegerSigned());
				x33.setP2(((BinaryIntegerSigned) prop.getValues().get(1)).getIntegerSigned());
				slider.setX33(x33);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(slider,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_IMAGES){
				Slider.Images images = new Slider.Images();
				images.setBar(((BinaryString) prop.getValues().get(0)).getString());
				images.setTab(((BinaryString) prop.getValues().get(1)).getString());
				slider.setImages(images);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_ARROWS){
				BinaryString arrow1 = (BinaryString) prop.getValues().get(0);
				BinaryString arrow2 = (BinaryString) prop.getValues().get(1);
				Arrow.Pair arrows = new Arrow.Pair();
				arrows.setArrow1(arrow1.getString());
				arrows.setArrow2(arrow2.getString());
				slider.setArrows(arrows);
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		sliders.put(str.getString(),slider);
	}

	private static void fromSceneComplex(ComplexScenes complexScenes, BinaryString str, BinaryObject obj){
		ComplexScene complexScene = new ComplexScene();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_NAME){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				complexScene.setName(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x2E){
				Float[] arr = new Float[9];
				BinaryList list = (BinaryList) prop.getValues().get(0);
				if(!list.getTokens().isEmpty()){
					arr[0] = ((BinaryFloat) list.getTokens().get(0)).getFloat();
					arr[1] = ((BinaryFloat) list.getTokens().get(1)).getFloat();
					arr[2] = ((BinaryFloat) list.getTokens().get(2)).getFloat();
					arr[3] = ((BinaryFloat) list.getTokens().get(3)).getFloat();
					arr[4] = ((BinaryFloat) list.getTokens().get(4)).getFloat();
					arr[5] = ((BinaryFloat) list.getTokens().get(5)).getFloat();
					arr[6] = ((BinaryFloat) list.getTokens().get(6)).getFloat();
					arr[7] = ((BinaryFloat) list.getTokens().get(7)).getFloat();
					arr[8] = ((BinaryFloat) list.getTokens().get(8)).getFloat();
				}
				complexScene.setX2E(arr);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x33){
				ComplexScene.X33 x33 = new ComplexScene.X33();
				x33.setP1(((BinaryIntegerSigned) prop.getValues().get(0)).getIntegerSigned());
				x33.setP2(((BinaryIntegerSigned) prop.getValues().get(1)).getIntegerSigned());
				complexScene.setX33(x33);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(complexScene,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_FRAME){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				complexScene.setFrame(string.getString());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		complexScenes.put(str.getString(),complexScene);
	}

	private static void fromInput(Inputs inputs,BinaryString str,BinaryObject obj){
		Input input = new Input();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_FONT){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				input.setFont(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x2B){
				Integer[] arr = new Integer[9];
				BinaryList list = (BinaryList) prop.getValues().get(0);
				if(!list.getTokens().isEmpty()){
					arr[0] = ((BinaryIntegerSigned) list.getTokens().get(0)).getIntegerSigned();
					arr[1] = ((BinaryIntegerSigned) list.getTokens().get(1)).getIntegerSigned();
					arr[2] = ((BinaryIntegerSigned) list.getTokens().get(2)).getIntegerSigned();
					arr[3] = ((BinaryIntegerSigned) list.getTokens().get(3)).getIntegerSigned();
					arr[4] = ((BinaryIntegerSigned) list.getTokens().get(4)).getIntegerSigned();
					arr[5] = ((BinaryIntegerSigned) list.getTokens().get(5)).getIntegerSigned();
					arr[6] = ((BinaryIntegerSigned) list.getTokens().get(6)).getIntegerSigned();
					arr[7] = ((BinaryIntegerSigned) list.getTokens().get(7)).getIntegerSigned();
					arr[8] = ((BinaryIntegerSigned) list.getTokens().get(8)).getIntegerSigned();
				}
				input.setX2B(arr);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_LIMIT){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				input.setLimit(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(input,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		inputs.put(str.getString(),input);
	}

	private static void fromScene(Scenes scenes, BinaryString str, BinaryObject obj){
		Scene scene = new Scene();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_NAME){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				scene.setName(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(scene,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		scenes.put(str.getString(), scene);
	}

	private static void fromButton(Buttons buttons,BinaryString str,BinaryObject obj){
		Button button = new Button();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x2B){
				X2B x2B = new X2B();
				BinaryList list = (BinaryList) prop.getValues().get(0);
				x2B.setP1(((BinaryIntegerSigned) list.getTokens().get(0)).getIntegerSigned());
				x2B.setP2(((BinaryIntegerSigned) list.getTokens().get(1)).getIntegerSigned());
				x2B.setP3(((BinaryIntegerSigned) list.getTokens().get(2)).getIntegerSigned());
				x2B.setP4(((BinaryIntegerSigned) list.getTokens().get(3)).getIntegerSigned());
				x2B.setP5(((BinaryIntegerSigned) list.getTokens().get(4)).getIntegerSigned());
				button.setX2B(x2B);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x32){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				button.setX32(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x33){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				button.setX33(integer.getIntegerSigned());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_POSITIONING){
				MIBFile.fromPositioning(button,(BinaryObject) prop.getValues().get(0));
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		buttons.put(str.getString(),button);
	}

	private static void fromPositioning(Item item, BinaryObject obj){
		Positioning positioning = new Positioning();
		for(BinaryProperty prop : obj.getProperties()){
			if(prop.getKey().getToken()==MIBFile.PROPERTY_RECTANGLE){
				Rectangle rectangle = new Rectangle();
				BinaryList list = (BinaryList) prop.getValues().get(0);
				rectangle.setX1(((BinaryIntegerSigned) list.getTokens().get(0)).getIntegerSigned());
				rectangle.setY1(((BinaryIntegerSigned) list.getTokens().get(1)).getIntegerSigned());
				rectangle.setX2(((BinaryIntegerSigned) list.getTokens().get(2)).getIntegerSigned());
				rectangle.setY2(((BinaryIntegerSigned) list.getTokens().get(3)).getIntegerSigned());
				positioning.setRectangle(rectangle);
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_PARENT){
				BinaryString string = (BinaryString) prop.getValues().get(0);
				positioning.setParent(string.getString());
				continue;
			}
			if(prop.getKey().getToken()==MIBFile.PROPERTY_x32){
				BinaryIntegerSigned integer = (BinaryIntegerSigned) prop.getValues().get(0);
				positioning.setX32(integer.getIntegerSigned());
				continue;
			}
			throw new RuntimeException("Unknown property '"+prop.getKey()+"'.");
		}
		item.setPositioning(positioning);
	}

	@Override
	public String toString() {
		return "MIBFile{" +
				"amount=" + amount +
				", colorings=" + colorings +
				", images=" + images +
				", texts=" + texts +
				", frames=" + frames +
				", arrows=" + arrows +
				", rockers=" + rockers +
				", controllers=" + controllers +
				", selectors=" + selectors +
				", sliders=" + sliders +
				", scene42s=" + complexScenes +
				", inputs=" + inputs +
				", scenes=" + scenes +
				", buttons=" + buttons +
				'}';
	}

}