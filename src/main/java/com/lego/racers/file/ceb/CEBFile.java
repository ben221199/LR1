package com.lego.racers.file.ceb;

import com.lego.racers.binary.BinaryFile;
import com.lego.racers.binary.BinaryFloat;
import com.lego.racers.binary.BinaryIntegerSigned;
import com.lego.racers.binary.BinaryObject;
import com.lego.racers.binary.BinaryString;
import com.lego.racers.binary.BinaryToken;
import com.lego.racers.file.ceb.object.Color;
import com.lego.racers.file.ceb.object.Event;
import com.lego.racers.file.ceb.object.Events;
import com.lego.racers.file.ceb.object.Fade;
import com.lego.racers.file.ceb.object.Fades;
import com.lego.racers.file.ceb.object.SoundBanks;
import com.lego.racers.file.ceb.object.SoundEffects;
import com.lego.racers.file.ceb.object.StringReferences;
import com.lego.racers.file.ceb.object.Subtitle;
import com.lego.racers.file.ceb.object.Subtitles;
import com.lego.racers.file.ceb.object._Unknown3;
import com.lego.racers.file.ceb.object.SoundEffect;
import com.lego.racers.file.ceb.object._Unknown4;
import com.lego.racers.file.ceb.object._Unknown5;
import com.lego.racers.file.ceb.object._Unknowns;
import com.lego.racers.file.ceb.object._Unknowns2;
import com.lego.racers.file.ceb.object._Unknowns3;
import com.lego.racers.file.ceb.object._Unknowns4;
import com.lego.racers.file.ceb.object._Unknowns5;

public class CEBFile{

	public static final byte BLOCK__UNKNOWN3 = 0x2B;
	public static final byte BLOCK_SOUND_EFFECT = 0x2F;
	public static final byte BLOCK__UNKNOWN4 = 0x3C;
	public static final byte BLOCK_SUBTITLE = 0x3F;
	public static final byte BLOCK__UNKNOWN5 = 0x4D;
	public static final byte BLOCK_EVENT_START = 0x56;
	public static final byte BLOCK_EVENT_END = 0x57;
	public static final byte BLOCK_FADE = 0x60;

	public static final byte PROPERTY__UNKNOWNS = 0x27;
	public static final byte PROPERTY_STRING_REFERENCES = 0x28;
	public static final byte PROPERTY__UNKNOWNS2 = 0x2A;
	public static final byte PROPERTY__UNKNOWNS3 = 0x2B;
	public static final byte PROPERTY_45 = 0x2D;
	public static final byte PROPERTY_SOUND_EFFECTS = 0x2F;
	public static final byte PROPERTY_SOUND_BANK = 0x30;
	public static final byte PROPERTY_VOLUME = 0x32;
	public static final byte PROPERTY_51 = 0x33;
	public static final byte PROPERTY_52 = 0x34;
	public static final byte PROPERTY_LOOP = 0x35;
	public static final byte PROPERTY__UNKNOWNS4 = 0x3C;
	public static final byte PROPERTY_61 = 0x3D;
	public static final byte PROPERTY_SUBTITLES = 0x3F;
	public static final byte PROPERTY_STRING_REFERENCE = 0x40;
	public static final byte PROPERTY_FONT = 0x42;
	public static final byte PROPERTY_VERTICAL_POSITION = 0x44;
	public static final byte PROPERTY_69 = 0x45;
	public static final byte PROPERTY_70 = 0x46;
	public static final byte PROPERTY__UNKNOWNS5 = 0x4D;
	public static final byte PROPERTY_78 = 0x4E;
	public static final byte PROPERTY_79 = 0x4F;
	public static final byte PROPERTY_EVENTS_START = 0x56;
	public static final byte PROPERTY_EVENTS_END = 0x57;
	public static final byte PROPERTY_SOUND_BANKS = 0x5C;
	public static final byte PROPERTY_93 = 0x5D;
	public static final byte PROPERTY_94 = 0x5E;
	public static final byte PROPERTY_FADES = 0x60;
	public static final byte PROPERTY_DURATION = 0x61;
	public static final byte PROPERTY_98 = 0x62;
	public static final byte PROPERTY_FADE_IN = 0x63;
	public static final byte PROPERTY_FADE_OUT = 0x64;
	public static final byte PROPERTY_COLOR = 0x66;

	private _Unknowns _unknowns;
	private StringReferences stringReferences;
	private _Unknowns2 _unknowns2;
	private _Unknowns3 _unknowns3;
	private SoundEffects soundEffects;
	private _Unknowns4 _unknowns4;
	private Subtitles subtitles;
	private _Unknowns5 _unknowns5;
	private Events eventsStart;
	private Events eventsEnd;
	private SoundBanks soundBanks;
	private Fades fades;

	public byte[] toBytes(){
		//TODO
		return new byte[0];
	}

	public static CEBFile from(byte[] bytes){
		BinaryFile bin = BinaryFile.from(bytes);
		CEBFile ceb = new CEBFile();
		for(int i=0;i<bin.getTokens().size();i++){
			BinaryToken token = bin.getTokens().get(i);
			if(token.getToken()==CEBFile.PROPERTY__UNKNOWNS){
				CEBFile.init_Unknowns(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY_STRING_REFERENCES){
				CEBFile.initStringReferences(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY__UNKNOWNS2){
				CEBFile.init_Unknowns2(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY__UNKNOWNS3){
				CEBFile.init_Unknowns3(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY_SOUND_EFFECTS){
				CEBFile.initSoundEffects(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY__UNKNOWNS4){
				CEBFile.init_Unknowns4(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY_SUBTITLES){
				CEBFile.initSubtitles(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY__UNKNOWNS5){
				CEBFile.init_Unknowns5(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY_EVENTS_START){
				CEBFile.initEventsStart(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY_EVENTS_END){
				CEBFile.initEventsEnd(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY_FADES){
				CEBFile.initFades(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
			if(token.getToken()==CEBFile.PROPERTY_SOUND_BANKS){
				CEBFile.initSoundBanks(ceb,(BinaryObject) bin.getTokens().get(i+2));
			}
		}
		return ceb;
	}

	private static void init_Unknowns(CEBFile file,BinaryObject obj){
		_Unknowns _unknowns = new _Unknowns();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryString string = (BinaryString) obj.getTokens().get(i);
			_unknowns.add(string.getString());
		}
		file._unknowns = _unknowns;
	}

	private static void initStringReferences(CEBFile file,BinaryObject obj){
		StringReferences stringReferences = new StringReferences();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryString string = (BinaryString) obj.getTokens().get(i);
			stringReferences.add(string.getString());
		}
		file.stringReferences = stringReferences;
	}

	private static void init_Unknowns2(CEBFile file,BinaryObject obj){
		_Unknowns2 _unknowns2 = new _Unknowns2();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryString string = (BinaryString) obj.getTokens().get(i);
			_unknowns2.add(string.getString());
		}
		file._unknowns2 = _unknowns2;
	}

	private static void init_Unknowns3(CEBFile file,BinaryObject obj){
		_Unknowns3 _unknowns3 = new _Unknowns3();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.BLOCK__UNKNOWN3){
				CEBFile.init_Unknown3(_unknowns3,(BinaryString )obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file._unknowns3 = _unknowns3;
	}

	private static void init_Unknown3(_Unknowns3 _unknowns3,BinaryString str,BinaryObject obj){
		_Unknown3 _unknown3 = new _Unknown3();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.PROPERTY_45){
				Color color = new Color();
				color.setRed(((BinaryIntegerSigned)obj.getTokens().get(i+1)).getIntegerSigned());
				color.setGreen(((BinaryIntegerSigned)obj.getTokens().get(i+2)).getIntegerSigned());
				color.setBlue(((BinaryIntegerSigned)obj.getTokens().get(i+3)).getIntegerSigned());
				_unknown3.setColor(color);
			}
			if(token.getToken()==CEBFile.PROPERTY_93){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				_unknown3.setP93(string.getString());
			}
			if(token.getToken()==CEBFile.PROPERTY_94){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				_unknown3.setP94(string.getString());
			}
		}
		_unknowns3.put(str.getString(),_unknown3);
	}

	private static void initSoundEffects(CEBFile file,BinaryObject obj){
		SoundEffects soundEffects = new SoundEffects();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.BLOCK_SOUND_EFFECT){
				CEBFile.initSoundEffect(soundEffects,(BinaryString )obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.soundEffects = soundEffects;
	}

	private static void initSoundEffect(SoundEffects _unknowns4, BinaryString str, BinaryObject obj){
		SoundEffect soundEffect = new SoundEffect();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.PROPERTY_SOUND_BANK){
				soundEffect.setSoundBankId(((BinaryIntegerSigned)obj.getTokens().get(i+1)).getIntegerSigned());
				soundEffect.setSoundBankLine(((BinaryIntegerSigned)obj.getTokens().get(i+2)).getIntegerSigned());
			}
			if(token.getToken()==CEBFile.PROPERTY_VOLUME){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				soundEffect.setVolume(f.getFloat());
			}
			if(token.getToken()==CEBFile.PROPERTY_51){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				soundEffect.setP51(f.getFloat());
			}
			if(token.getToken()==CEBFile.PROPERTY_52){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				soundEffect.setP52(f.getFloat());
			}
			if(token.getToken()==CEBFile.PROPERTY_LOOP){
				soundEffect.setLoop(true);
			}
		}
		_unknowns4.put(str.getString(),soundEffect);
	}

	private static void init_Unknowns4(CEBFile file,BinaryObject obj){
		_Unknowns4 _unknowns4 = new _Unknowns4();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.BLOCK__UNKNOWN4){
				CEBFile.init_Unknown4(_unknowns4,(BinaryString )obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file._unknowns4 = _unknowns4;
	}

	private static void init_Unknown4(_Unknowns4 _unknowns4,BinaryString str,BinaryObject obj){
		_Unknown4 _unknown4 = new _Unknown4();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.PROPERTY_61){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				BinaryString string = (BinaryString) obj.getTokens().get(i+2);

				_unknown4.setP61_1(ii.getIntegerSigned());
				_unknown4.setP61_2(string.getString());
			}
		}
		_unknowns4.put(str.getString(),_unknown4);
	}

	private static void initSubtitles(CEBFile file,BinaryObject obj){
		Subtitles subtitles = new Subtitles();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.BLOCK_SUBTITLE){
				CEBFile.initSubtitle(subtitles,(BinaryString )obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.subtitles = subtitles;
	}

	private static void initSubtitle(Subtitles subtitles,BinaryString str,BinaryObject obj){
		Subtitle subtitle = new Subtitle();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.PROPERTY_STRING_REFERENCE){
				BinaryIntegerSigned id = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				BinaryIntegerSigned line = (BinaryIntegerSigned) obj.getTokens().get(i+2);
				subtitle.setStringReferenceId(id.getIntegerSigned());
				subtitle.setStringReferenceLine(line.getIntegerSigned());
			}
			if(token.getToken()==CEBFile.PROPERTY_FONT){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				subtitle.setFont(string.getString());
			}
			if(token.getToken()==CEBFile.PROPERTY_VERTICAL_POSITION){
				BinaryFloat f = (BinaryFloat) obj.getTokens().get(i+1);
				subtitle.setVerticalPosition(f.getFloat());
			}
			if(token.getToken()==CEBFile.PROPERTY_69){
				subtitle.setP69(true);
			}
		}
		subtitles.put(str.getString(),subtitle);
	}

	private static void init_Unknowns5(CEBFile file,BinaryObject obj){
		_Unknowns5 _unknowns5 = new _Unknowns5();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.BLOCK__UNKNOWN5){
				CEBFile.init_Unknown5(_unknowns5,(BinaryString )obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file._unknowns5 = _unknowns5;
	}

	private static void init_Unknown5(_Unknowns5 _unknowns5,BinaryString str,BinaryObject obj){
		_Unknown5 _unknown5 = new _Unknown5();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.PROPERTY_69){
				_unknown5.setP69(true);
			}
			if(token.getToken()==CEBFile.PROPERTY_70){
				_unknown5.setP70(true);
			}
			if(token.getToken()==CEBFile.PROPERTY__UNKNOWNS5){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				_unknown5.setP77(string.getString());
			}
		}
		_unknowns5.put(str.getString(),_unknown5);
	}

	private static void initEventsStart(CEBFile file,BinaryObject obj){
		Events events = new Events();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.BLOCK_EVENT_START){
				CEBFile.initEvent(events,(BinaryString )obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.eventsStart = events;
	}

	private static void initEventsEnd(CEBFile file,BinaryObject obj){
		Events events = new Events();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.BLOCK_EVENT_END){
				CEBFile.initEvent(events,(BinaryString )obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.eventsEnd = events;
	}

	private static void initEvent(Events events,BinaryString str,BinaryObject obj){
		Event event = new Event();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.PROPERTY__UNKNOWNS3){
				event.set_Unknown3(true);
			}
			if(token.getToken()==CEBFile.PROPERTY_SOUND_EFFECTS){
				event.setSoundEffect(true);
			}
			if(token.getToken()==CEBFile.PROPERTY__UNKNOWNS4){
				event.set_Unknown4(true);
			}
			if(token.getToken()==CEBFile.PROPERTY_SUBTITLES){
				event.setSoundEffect(true);
			}
			if(token.getToken()==CEBFile.PROPERTY__UNKNOWNS5){
				event.set_Unknown5(true);
			}
			if(token.getToken()==CEBFile.PROPERTY_78){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				event.setP78(string.getString());
			}
			if(token.getToken()==CEBFile.PROPERTY_79){
				BinaryString string = (BinaryString) obj.getTokens().get(i+1);
				event.setP79(string.getString());
			}
			if(token.getToken()==CEBFile.PROPERTY_FADES){
				event.setFade(true);
			}
		}
		events.put(str.getString(),event);
	}

	private static void initFades(CEBFile file,BinaryObject obj){
		Fades fades = new Fades();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.BLOCK_FADE){
				CEBFile.initFade(fades,(BinaryString )obj.getTokens().get(i+1),(BinaryObject) obj.getTokens().get(i+2));
			}
		}
		file.fades = fades;
	}

	private static void initFade(Fades fades,BinaryString str,BinaryObject obj){
		Fade fade = new Fade();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryToken token = obj.getTokens().get(i);
			if(token.getToken()==CEBFile.PROPERTY_DURATION){
				BinaryIntegerSigned ii = (BinaryIntegerSigned) obj.getTokens().get(i+1);
				fade.setDuration(ii.getIntegerSigned());
			}
			if(token.getToken()==CEBFile.PROPERTY_98){
				fade.setP98(true);
			}
			if(token.getToken()==CEBFile.PROPERTY_FADE_IN){
				fade.setFadeIn(true);
			}
			if(token.getToken()==CEBFile.PROPERTY_FADE_OUT){
				fade.setFadeOut(true);
			}
			if(token.getToken()==CEBFile.PROPERTY_COLOR){
				Color color = new Color();
				color.setRed(((BinaryIntegerSigned) obj.getTokens().get(i+1)).getIntegerSigned());
				color.setGreen(((BinaryIntegerSigned) obj.getTokens().get(i+2)).getIntegerSigned());
				color.setBlue(((BinaryIntegerSigned) obj.getTokens().get(i+3)).getIntegerSigned());
				fade.setColor(color);
			}
		}
		fades.put(str.getString(),fade);
	}

	private static void initSoundBanks(CEBFile file,BinaryObject obj){
		SoundBanks soundBanks = new SoundBanks();
		for(int i=0;i<obj.getTokens().size();i++){
			BinaryString string = (BinaryString) obj.getTokens().get(i);
			soundBanks.add(string.getString());
		}
		file.soundBanks = soundBanks;
	}

	@Override
	public String toString() {
		return "CEBFile{" +
				"_unknowns=" + _unknowns +
				", stringReferences=" + stringReferences +
				", _unknowns2=" + _unknowns2 +
				", _unknowns3=" + _unknowns3 +
				", soundEffects=" + soundEffects +
				", _unknowns4=" + _unknowns4 +
				", subtitles=" + subtitles +
				", _unknowns5=" + _unknowns5 +
				", eventsStart=" + eventsStart +
				", eventsEnd=" + eventsEnd +
				", soundBanks=" + soundBanks +
				", fades=" + fades +
				'}';
	}

}