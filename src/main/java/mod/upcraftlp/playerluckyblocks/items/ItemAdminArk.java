package mod.upcraftlp.playerluckyblocks.items;

import core.upcraftlp.craftdev.API.templates.ItemSword;
import mod.upcraftlp.playerluckyblocks.init.LuckyMisc.ToolMaterials;

public class ItemAdminArk extends ItemSword {

	public ItemAdminArk() {
		super("admin_ark", ToolMaterials.ADMIN);
		this.setNoRepair();
		this.setMaxDamage(0); //0 = unbreakable
	}
	
}
