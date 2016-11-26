package mod.upcraftlp.playerluckyblocks.init;

import java.util.Set;

import mod.upcraftlp.playerluckyblocks.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.GuiConfig;

public class LuckyConfigGuiFactory implements IModGuiFactory {
	

	//private Minecraft mc;
	
	@Override
	public void initialize(Minecraft minecraftInstance) {
		//this.mc = minecraftInstance;
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return LuckyConfigGUI.class;
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}
	
	public static class LuckyConfigGUI extends GuiConfig {
		
		public LuckyConfigGUI(GuiScreen parent) {
		    super(parent, LuckyConfig.getEntries(),
		        Reference.MODID, false, false, I18n.format("config." + Reference.MODID + ".name"));
		  }
	}
	
}


