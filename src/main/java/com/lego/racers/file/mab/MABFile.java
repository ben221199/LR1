package com.lego.racers.file.mab;

import com.lego.racers.binary.BinaryArray;
import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.mab.object.Animation;
import com.lego.racers.file.mab.object.Animations;
import com.lego.racers.file.mab.object.Frames;

import java.util.Map;

public class MABFile{

	public static final byte BLOCK_ANIMATION = 0x28;

	public static final byte PROPERTY_FRAMES = 0x27;
	public static final byte PROPERTY_ANIMATIONS = 0x28;
	public static final byte PROPERTY_FRAME_COUNT = 0x29;
	public static final byte PROPERTY_SPEED = 0x2A;

	private Frames frames;
	private Animations animations;

	public Frames getFrames(){
		return frames;
	}

	public void setFrames(Frames frames){
		this.frames = frames;
	}

	public Animations getAnimations(){
		return this.animations;
	}

	public void setAnimations(Animations animations){
		this.animations = animations;
	}

	public byte[] toBytes(){
		BinaryFile bin = new BinaryFile();
		if(this.frames!=null){
			bin.getTokens().add(new BinaryToken(MABFile.PROPERTY_FRAMES));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.frames.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			for(Map.Entry<String,Integer> entry : this.frames.entrySet()){
				obj.getTokens().add(new BinaryString(entry.getKey()));
				obj.getTokens().add(new BinaryIntegerSigned(entry.getValue()));
			}
			bin.getTokens().add(obj);
		}
		if(this.animations!=null){
			bin.getTokens().add(new BinaryToken(MABFile.PROPERTY_ANIMATIONS));
			BinaryArray length = new BinaryArray();
			length.getTokens().add(new BinaryIntegerSigned(this.animations.size()));
			bin.getTokens().add(length);
			BinaryObject obj = new BinaryObject();
			for(Animation animation : this.animations){
				obj.getTokens().add(new BinaryToken(MABFile.BLOCK_ANIMATION));
				BinaryObject animationObj = new BinaryObject();

				animationObj.getTokens().add(new BinaryToken(MABFile.PROPERTY_FRAMES));
				animationObj.getTokens().add(new BinaryIntegerSigned(animation.getFrameOffset()));
				animationObj.getTokens().add(new BinaryIntegerSigned(animation.getFrameLimit()));

				animationObj.getTokens().add(new BinaryToken(MABFile.PROPERTY_FRAME_COUNT));
				animationObj.getTokens().add(new BinaryIntegerSigned(animation.getFrameCount()));

				animationObj.getTokens().add(new BinaryToken(MABFile.PROPERTY_SPEED));
				animationObj.getTokens().add(new BinaryIntegerSigned(animation.getSpeed()));

				obj.getTokens().add(animationObj);
			}
			bin.getTokens().add(obj);
		}
		return bin.toBytes();
	}

	public static MABFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		MABFile mab = new MABFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==MABFile.PROPERTY_FRAMES){
				MABFile.initFrames(mab,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==MABFile.PROPERTY_ANIMATIONS){
				MABFile.initAnimations(mab,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return mab;
	}

	private static void initFrames(MABFile file,BinaryObject obj){
		Frames frames = new Frames();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token instanceof BinaryString){
				BinaryString key = (BinaryString) token;
				BinaryIntegerSigned value = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				frames.put(key.getString(),value.getIntegerSigned());
			}
		}
		file.frames = frames;
	}

	private static void initAnimations(MABFile file,BinaryObject obj){
		Animations animations = new Animations();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==MABFile.BLOCK_ANIMATION){
				MABFile.initAnimation(animations,(BinaryObject) obj.getTokens().get(i+1));
			}
		}
		file.animations = animations;
	}

	private static void initAnimation(Animations animations,BinaryObject obj){
		Animation animation = new Animation();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==MABFile.PROPERTY_FRAMES){
				BinaryIntegerSigned frameOffset = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				BinaryIntegerSigned frameLimit = (BinaryIntegerSigned) obj.getTokens().get(i+2);
				animation.setFrameOffset(frameOffset.getIntegerSigned());
				animation.setFrameLimit(frameLimit.getIntegerSigned());
			}
			if(token.getToken()==MABFile.PROPERTY_FRAME_COUNT){
				BinaryIntegerSigned frameCount = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				animation.setFrameCount(frameCount.getIntegerSigned());
			}
			if(token.getToken()==MABFile.PROPERTY_SPEED){
				BinaryIntegerSigned speed = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				animation.setSpeed(speed.getIntegerSigned());
			}
		}
		animations.add(animation);
	}

	@Override
	public String toString() {
		return "MABFile{" +
				"frames=" + frames +
				", animations=" + animations +
				'}';
	}

}