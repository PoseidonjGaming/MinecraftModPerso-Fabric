package fr.perso_fabric.initializers;

import com.mojang.datafixers.util.Pair;
import fr.perso_fabric.lib.object.FabricBannerShieldItem;
import fr.perso_fabric.lib.object.FabricShield;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class FabricShieldLibClient implements ClientModInitializer {

    /**
     * Will be made by user (dev code)
     */
    public static final EntityModelLayer fabric_banner_shield_model_layer = new EntityModelLayer(new Identifier(FabricShieldLib.MOD_ID, "fabric_banner_shield"),"main");
    
    @Override
    public void onInitializeClient() {

        /**
         * Register tooltip callback this is the same as mixing into the end of:
         * ItemStack.getTooltip()
         */
        ItemTooltipCallback.EVENT.register((stack, context, tooltip) -> {

            if(FabricShieldLib.config.enable_tooltips) {
                
                if(stack.getItem() instanceof FabricShield) {

                    FabricShield shield = (FabricShield) stack.getItem();

                    //Add any custom tooltips
                    shield.appendShieldTooltip(stack, tooltip, context);

                    //Add cooldown tooltip
                    if(shield.displayTooltip()) {
                        getCooldownTooltip(stack, context,tooltip, shield.getCooldownTicks());
                    }
                }

                //Display tooltip for vanilla shield
                if(stack.getItem().equals(Items.SHIELD)) {
                    getCooldownTooltip(stack, context,tooltip, 100);
                }
            }
        });

        if(FabricLoader.getInstance().isDevelopmentEnvironment()) {

            //Warn about dev code
            FabricShieldLib.logger.warn("FABRIC SHIELD LIB DEVELOPMENT CODE RAN!!!, if you are not in a development environment this is very bad! Client side banner code ran!");

            /*
             * Registers sprite directories and model layer, will be done by player, dev code
             */
            EntityModelLayerRegistry.registerModelLayer(fabric_banner_shield_model_layer, ShieldEntityModel::getTexturedModelData);
            ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
                registry.register(new Identifier(FabricShieldLib.MOD_ID, "entity/fabric_banner_shield_base"));
                registry.register(new Identifier(FabricShieldLib.MOD_ID, "entity/fabric_banner_shield_base_nopattern"));
            });
        }
    }

    /**
     * Used to simplify the mixin on the user end to make their shield render banner
     *
     * Uses params from the mixin method, and the model and sprite identifiers made by the player
     */
    public static void renderBanner(ItemStack stack, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, ShieldEntityModel model, SpriteIdentifier base, SpriteIdentifier base_nopattern){
        boolean bl = stack.getSubNbt("BlockEntityTag") != null;
        matrices.push();
        matrices.scale(1.0F, -1.0F, -1.0F);
        SpriteIdentifier spriteIdentifier = bl ? base : base_nopattern;
        VertexConsumer vertexConsumer = spriteIdentifier.getSprite().getTextureSpecificVertexConsumer(ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, model.getLayer(spriteIdentifier.getAtlasId()), true, stack.hasGlint()));
        model.getHandle().render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        if (bl) {
            List<Pair<BannerPattern, DyeColor>> list = BannerBlockEntity.getPatternsFromNbt(FabricBannerShieldItem.getColor(stack), BannerBlockEntity.getPatternListNbt(stack));
            BannerBlockEntityRenderer.renderCanvas(matrices, vertexConsumers, light, overlay, model.getPlate(), spriteIdentifier, false, list, stack.hasGlint());
        } else {
            model.getPlate().render(matrices, vertexConsumer, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        matrices.pop();
    }

    /**
     * Shield tooltip thing
     */
    public static List<Text> getCooldownTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, int cooldownTicks) {

        List<Text> advanced = new ArrayList<Text>();

        /**
         * These all loop in reverse to grab the first instance of a match
         * at the end of the tooltip
         */
        if(context.isAdvanced()) {

            /**
             * Grab durability
             */
            if(stack.isDamaged()) {

                for(int i = tooltip.size() - 1; i > 0; i--) {

                    Text text = tooltip.get(i);
                    String strText = text.getString();

                    if(strText.startsWith("Durability")) {
                        advanced.add(text);
                        tooltip.remove(i);
                        break;
                    }
                }
            }

            /**
             * Grab item id
             */
            for(int i = tooltip.size() - 1; i > 0; i--) {

                Text text = tooltip.get(i);
                String strText = text.getString().trim();

                if(Identifier.isValid(strText)) {
                    advanced.add(text);
                    tooltip.remove(i);
                    break;
                }
            }
            
            /**
             * Grab nbt string
             */
            if(stack.hasNbt()) {
                for(int i = tooltip.size() - 1; i > 0; i--) {

                    Text text = tooltip.get(i);
                    String strText = text.getString();

                    if(strText.startsWith("NBT: ")) {
                        advanced.add(text);
                        tooltip.remove(i);
                        break;
                    }
                }
            }
        }

        /**
         * Add disabled cooldown tooltip
         */
        tooltip.add(new LiteralText(""));
        tooltip.add(new TranslatableText("fabricshieldlib.shield_tooltip.start").append(new LiteralText(":")).formatted(Formatting.GRAY));

        /**
         * All of this is so if there is a .0 instead of there being a need for a 
         * decimal remove the .0
         */
        String cooldown = String.valueOf((Double)(cooldownTicks / 20.0));
        char[] splitCooldown = cooldown.toCharArray();
        if(splitCooldown.length >= 3) {

            if(splitCooldown[2] == '0') {

                if(!(splitCooldown.length >= 4)) {
                    cooldown = String.valueOf(splitCooldown[0]);
                }
            }
        }

        tooltip.add(new LiteralText(" " + cooldown).formatted(Formatting.DARK_GREEN).append(new TranslatableText("fabricshieldlib.shield_tooltip.unit")).append(new LiteralText(" ")).append(new TranslatableText("fabricshieldlib.shield_tooltip.end")));

        /**
         * Append advanced info
         */
        if(context.isAdvanced()) {
            tooltip.addAll(advanced);
        }
        return tooltip;
    }
}