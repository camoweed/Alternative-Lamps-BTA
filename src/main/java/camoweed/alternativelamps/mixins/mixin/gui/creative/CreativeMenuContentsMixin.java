package camoweed.alternativelamps.mixins.mixin.gui.creative;

import camoweed.alternativelamps.AlternativeLampsBlocks;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.CreativeMenuContents;
import net.minecraft.core.util.helper.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static camoweed.alternativelamps.mixins.mixin.accessor.CreativeMenuContentsAccessor.getRAINBOW_ORDER;

@Mixin(value = CreativeMenuContents.class, remap = false)
public abstract class CreativeMenuContentsMixin {
	@Inject(method = "addPaintedTypes", at = @At("TAIL"))
	private static void addDyedAlternativeLampVariantsInMetadataOrderAfterAllOtherDyedBlocksAndItemsInTheCreativeMenu(List<ItemStack> list, CallbackInfo ci){
		for(DyeColor dyeColor: getRAINBOW_ORDER()){
			list.add(new ItemStack(AlternativeLampsBlocks.ALT_LAMP_IDLE, 1, dyeColor.itemMeta));
		}
	}


}
