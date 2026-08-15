package camoweed.alternativelamps.mixins.mixin.accessor;

import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.CreativeMenuContents;
import net.minecraft.core.util.helper.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(CreativeMenuContents.class)
public interface CreativeMenuContentsAccessor {
	@Accessor
	static DyeColor[] getRAINBOW_ORDER() {
		throw new UnsupportedOperationException();
	}
}
