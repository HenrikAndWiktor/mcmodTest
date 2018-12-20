/*
MIT License

Copyright (c) 2018 Yellow Duck / Wiktor Eriksson

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
 */
package se.wiktoreriksson.modding.test;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;
import se.wiktoreriksson.modding.test.block.BlockBlue;
import se.wiktoreriksson.modding.test.item.block.ItemBlockBlue;

import java.util.Timer;
import java.util.TimerTask;

import static se.wiktoreriksson.modding.test.TestMod.MODID;

@Mod.EventBusSubscriber(modid = MODID)
@Mod(modid = MODID, name = TestMod.NAME, version = TestMod.VERSION, updateJSON = "http://github.com/YellowDuckSE/mcmodTest/update.json")
public class TestMod
{
    public static final String MODID = "test";
    public static final String NAME = "Test Mod";
    public static final String VERSION = "0.1.0";

    private static Logger logger;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BlockBlue.getRegistryBlock());
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(new ItemBlockBlue(BlockBlue.getRegistryBlock()));
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        registerRender(Item.getItemFromBlock(BlockBlue.getRegistryBlock()));
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof EntityPlayer) {
            ForgeVersion.CheckResult check = ForgeVersion.getResult(Loader.instance().activeModContainer());
            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            switch (check.status) {
                                case OUTDATED:
                                    event.getEntity().sendMessage(
                                            new TextComponentString(
                                                    "§1Outdated version of " + NAME + "!" +
                                                            "\n§1Please get version " + check.target
                                            ));
                                    break;
                                case BETA_OUTDATED:
                                    event.getEntity().sendMessage(
                                            new TextComponentString(
                                                    "§1New "+NAME+" beta version detected!" +
                                                    "\n§1New beta: " + check.target
                                            )
                                    );
                                    break;
                                case FAILED:
                                    event.getEntity().sendMessage(
                                            new TextComponentString(
                                                    "§1Failed to fetch version data for "+NAME+"!"
                                            )
                                    );
                                    break;
                                case AHEAD:
                                    event.getEntity().sendMessage(
                                            new TextComponentString(
                                                    "§1You are ahead of the latest version! Wait a second... thats impossible!"
                                            )
                                    );
                                    break;
                                default:
                                    //Do nothing
                            }
                        }
                    }
            ,1000);
        }
    }
}
