package se.wiktoreriksson.modding.test.item.block;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import se.wiktoreriksson.modding.test.block.BlockBlue;

public class ItemBlockBlue extends ItemBlock{
    public ItemBlockBlue(BlockBlue block) {
        super(block);
        setRegistryName("test:blue");
        setCreativeTab(CreativeTabs.MISC);
        setUnlocalizedName("blue");
    }
}
