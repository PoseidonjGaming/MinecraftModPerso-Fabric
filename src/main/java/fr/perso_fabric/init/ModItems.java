package fr.perso_fabric.init;

import fr.perso_fabric.lib.object.FabricShieldItem;
import fr.perso_fabric.Perso;

import fr.perso_fabric.lib.object.FabricShieldItem;
import fr.perso_fabric.utils.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Map;


public class ModItems {

    public static final Item vibranium_ingot= new Item(new Item.Settings().group(Perso.persoGroup));
    public static final Item vibranium_stick= new Item(new Item.Settings().group(Perso.persoGroup));
    public static final Item heart_plant= new Item(new Item.Settings().group(Perso.persoGroup).food(new FoodComponent.Builder().hunger(4).saturationModifier(10f).alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,0),1f).build()));

    public static final Item vibranium_helmet=new ArmorItem(ModArmorMaterial.VIBRANIUM, EquipmentSlot.HEAD,new FabricItemSettings().group(Perso.persoGroup));
    public static final Item vibranium_chestplate=new ArmorItem(ModArmorMaterial.VIBRANIUM, EquipmentSlot.CHEST,new FabricItemSettings().group(Perso.persoGroup));
    public static final Item vibranium_leggings=new ArmorItem(ModArmorMaterial.VIBRANIUM, EquipmentSlot.LEGS,new FabricItemSettings().group(Perso.persoGroup));
    public static final Item vibranium_boots=new ArmorItem(ModArmorMaterial.VIBRANIUM, EquipmentSlot.FEET,new FabricItemSettings().group(Perso.persoGroup));

    public static final Item vibranium_axe=new ModCustomAxe(ModToolMaterial.Vibranium,1,3f,new FabricItemSettings().group(Perso.persoGroup));
    public static final Item vibranium_pickaxe=new ModCustomPickaxe(ModToolMaterial.Vibranium,1,-2.8f,new FabricItemSettings().group(Perso.persoGroup));
    public static final Item vibranium_sword=new ModCustomSword(ModToolMaterial.Vibranium,1,3f,new FabricItemSettings().group(Perso.persoGroup));
    public static final Item vibranium_shovel=new ModCustomShovel(ModToolMaterial.Vibranium,1.5f,-3f,new FabricItemSettings().group(Perso.persoGroup));
    public static final Item vibranium_hoe=new ModCustomHoe(ModToolMaterial.Vibranium,-4,0.0f,new FabricItemSettings().group(Perso.persoGroup));

    public static final Item vibranium_shield=new FabricShieldItem((Item.Settings) new FabricItemSettings().group(Perso.persoGroup),10,13,vibranium_ingot);
    public static final Item vibranium_dust= new Item(new Item.Settings().group(Perso.persoGroup));



    public static void registerAll(){
        registerItem("vibranium_ingot",vibranium_ingot);
        registerItem("vibranium_stick",vibranium_stick);
        registerItem("vibranium_helmet",vibranium_helmet);
        registerItem("vibranium_chestplate",vibranium_chestplate);
        registerItem("vibranium_leggings",vibranium_leggings);
        registerItem("vibranium_boots",vibranium_boots);
        registerItem("vibranium_axe",vibranium_axe);
        registerItem("vibranium_pickaxe",vibranium_pickaxe);
        registerItem("vibranium_sword",vibranium_sword);
        registerItem("vibranium_shovel",vibranium_shovel);
        registerItem("vibranium_hoe",vibranium_hoe);
        registerItem("heart_plant",heart_plant);
        registerItem("vibranium_shield",vibranium_shield);
        registerItem("vibranium_dust",vibranium_dust);


    }

    public static void registerItem(String name,Item item){
        Registry.register(Registry.ITEM,new Identifier(Perso.ModId,name),item);
    }
    public static void registerItem(String name, Block block){
        Registry.register(Registry.ITEM,new Identifier(Perso.ModId,name),new BlockItem(block,new FabricItemSettings().group(Perso.persoGroup)));
    }
}
