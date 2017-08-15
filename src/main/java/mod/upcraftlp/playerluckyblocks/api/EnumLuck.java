package mod.upcraftlp.playerluckyblocks.api;

import java.util.Random;

import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.MathHelper;

//FIXME better calculations
public enum EnumLuck implements IStringSerializable {
	
	POSITIVE("positive"),
	NEGATIVE("negative"),
	NEUTRAL("neutral"),
	BADASS("badass");
	
	private final String name;
	
	public static EnumLuck byName(String name) {
		return EnumLuck.valueOf(name);
	}
	
	EnumLuck(String name) {
		this.name = name;
	}

	public static int randomLuck() {
		int luck = random.nextInt(150) - random.nextInt(150);
		return MathHelper.clamp(luck, -100, 100);
	}

	public static EnumLuck rollTheDice(int luck)
	{
		luck = MathHelper.clamp(luck, -100, 100);
		//REAL BAD LUCK
		if(luck <= -90) {
		    if(random.nextFloat() < 0.75f) return LuckyConfig.pussyMode ? NEGATIVE : BADASS;
		    else luck = -80;
		}
		
		//BAD LUCK
		if(luck > -90 && luck < 0)
		{
			
			int luck3 = luck * -1;
			int luck2 = random.nextInt(luck3);
			if(luck2 == 0) return BADASS;
			if(luck2 > luck3 * 0.5 && luck2  < luck3 * 0.8) return POSITIVE;
			if(luck2 >= luck3 * 0.8) return NEUTRAL;
			return NEGATIVE;
		}
		
		//NORMAL LUCK
		if(luck > 0 && luck < 60)
		{
			int luck2 = random.nextInt(luck);
			if(luck2 < luck * 0.2) return POSITIVE;
			if(luck2 > luck * 0.7) return NEGATIVE;
			return NEUTRAL;
		}
		
		//GOOD LUCK
		if(luck >= 60)
		{
			int luck2 = random.nextInt(luck);
			if(luck2 == 0) return BADASS;
			if(luck2 < luck * 0.3) return NEGATIVE;
			if(luck2 >= luck*0.3 && luck2 < luck*0.6) return NEUTRAL;
			return POSITIVE;
		}
		
		//NEUTRAL (Completely random, but no BADASS values)
		EnumLuck[] possibleLuck = new EnumLuck[] {POSITIVE, NEGATIVE, NEUTRAL};
		return possibleLuck[random.nextInt(possibleLuck.length)];
	}
	
	private static final Random random = new Random();

	@Override
	public String getName() {
		return this.name;
	}

}
