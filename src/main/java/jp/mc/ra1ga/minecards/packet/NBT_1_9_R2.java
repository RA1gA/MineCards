package jp.mc.ra1ga.minecards.packet;

import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;

import net.minecraft.server.v1_9_R2.ItemStack;
import net.minecraft.server.v1_9_R2.NBTTagCompound;
import net.minecraft.server.v1_9_R2.NBTTagDouble;
import net.minecraft.server.v1_9_R2.NBTTagInt;
import net.minecraft.server.v1_9_R2.NBTTagList;
import net.minecraft.server.v1_9_R2.NBTTagString;

public class NBT_1_9_R2 implements NBT {

	@Override
	public org.bukkit.inventory.ItemStack setItemSpeed(org.bukkit.inventory.ItemStack item, float speed) {
		ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		if(nmsStack != null){
			NBTTagCompound compound = nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();

			NBTTagList tags = new NBTTagList();

			NBTTagCompound aSpeed = new NBTTagCompound();
			aSpeed.set("AttributeName", new NBTTagString("generic.attackSpeed"));
			aSpeed.set("Name", new NBTTagString("generic.attackSpeed"));
			aSpeed.set("Amount", new NBTTagDouble(0.01));
			aSpeed.set("Operation", new NBTTagInt(0));
			aSpeed.set("UUIDLeast", new NBTTagInt(894654));
			aSpeed.set("UUIDMost", new NBTTagInt(2872));
			aSpeed.set("Slot", new NBTTagString("mainhand"));
			tags.add(aSpeed);

			compound.set("AttributeModifiers", tags);

			nmsStack.setTag(compound);
			item = CraftItemStack.asBukkitCopy(nmsStack);
		}
		return item;
	}

}
