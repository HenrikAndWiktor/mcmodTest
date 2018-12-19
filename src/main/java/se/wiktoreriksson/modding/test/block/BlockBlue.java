package se.wiktoreriksson.modding.test.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nonnull;

public class BlockBlue extends Block {
    private static BlockBlue blockblueObj=null;
    public BlockBlue() {
        super(Material.ROCK);
        setLightLevel(15/16f)
                .setCreativeTab(CreativeTabs.MISC)
                .setBlockUnbreakable()
                .setRegistryName("test:blue_block")
                .setUnlocalizedName("blue");
    }

    /**
     * Get the MapColor for this Block and the given BlockState
     *
     * @param state state
     * @param worldIn worldIn
     * @param pos pos
     */
    @Override @Nonnull
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.BLUE;
    }

    public static BlockBlue getRegistryBlock() {
        if (blockblueObj==null) blockblueObj=new BlockBlue();
        return blockblueObj;
    }

    @Override @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     *
     * @param state state
     */
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
