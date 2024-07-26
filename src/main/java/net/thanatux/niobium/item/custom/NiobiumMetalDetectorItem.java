package net.thanatux.niobium.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.sounds.SoundSource;
import net.thanatux.niobium.util.ModTags;
import net.thanatux.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NiobiumMetalDetectorItem extends Item {
    public NiobiumMetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level world = pContext.getLevel();
        if (!world.isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState state = world.getBlockState(positionClicked.below(i));

                if (isValuableBlock(state)) {
                    outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                    foundBlock = true;
                    playFoundSound(world, player); // Reproduzir som de minério encontrado
                    break;
                }
            }

            if (!foundBlock) {
                playNoOreSound(world, player);
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.niobium.niobium_metal_detector.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }

    private void playFoundSound(Level world, Player player) {
        // Reproduzir som de minério encontrado
        world.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.METAL_DETECTOR_FOUND_ORE.get(),
                SoundSource.PLAYERS, 1.0f, 1.0f);
    }

    private void playNoOreSound(Level world, Player player) {
        // Reproduzir som de nenhum minério encontrado
        world.playSound(null, player.getX(), player.getY(), player.getZ(), ModSounds.METAL_DETECTOR_NO_ORE.get(),
                SoundSource.PLAYERS, 1.0f, 1.0f);
    }
}
