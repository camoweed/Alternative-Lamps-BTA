package camoweed.alternativelamps;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicLamp;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockLogicAlternativeLamp extends BlockLogicLamp {
	public BlockLogicAlternativeLamp(Block<?> block, boolean isActive, boolean isInverted) {
		super(block, isActive, isInverted);
	}

	public int getPlacedData(@Nullable Player player, @NotNull ItemStack itemStack, @NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		return itemStack.getMetadata();
	}

	public void onPlacedByWorld(@NotNull World world, @NotNull TilePosc tilePos) {
		if (this.isInverted) {
			if (this.isActive && (world.hasDirectSignal(tilePos) || world.hasNeighborSignal(tilePos))) {
				world.setBlockTypeDataNotify(tilePos, AlternativeLampsBlocks.ALT_LAMP_INVERTED_IDLE, world.getBlockData(tilePos));
			}
		} else if (this.isActive && !world.hasDirectSignal(tilePos) && !world.hasNeighborSignal(tilePos)) {
			world.setBlockTypeDataNotify(tilePos, AlternativeLampsBlocks.ALT_LAMP_IDLE, world.getBlockData(tilePos));
		}

	}

	public void onPlacedOnSide(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Side side, double xHit, double yHit) {
		if (!world.isClientSide) {
			boolean hasSignal = world.hasDirectSignal(tilePos) || world.hasNeighborSignal(tilePos);
			if (this.isInverted) {
				if (hasSignal && this.isActive) {
					world.setBlockTypeDataNotify(tilePos, AlternativeLampsBlocks.ALT_LAMP_INVERTED_IDLE, world.getBlockData(tilePos));
				} else if (!hasSignal && !this.isActive) {
					world.setBlockTypeDataNotify(tilePos, AlternativeLampsBlocks.ALT_LAMP_INVERTED_ACTIVE, world.getBlockData(tilePos));
				}
			} else if (hasSignal && !this.isActive) {
				world.setBlockTypeDataNotify(tilePos, AlternativeLampsBlocks.ALT_LAMP_ACTIVE, world.getBlockData(tilePos));
			} else if (!hasSignal && this.isActive) {
				world.setBlockTypeDataNotify(tilePos, AlternativeLampsBlocks.ALT_LAMP_IDLE, world.getBlockData(tilePos));
			}

		}
	}

	public void onNeighborChanged(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Block<?> block) {
		boolean hasSignal = world.hasDirectSignal(tilePos) || world.hasNeighborSignal(tilePos);
		boolean isPowered = this.isInverted ^ this.isActive;
		if (hasSignal != isPowered) {
			world.setBlockTypeDataNotify(tilePos, this.getBlockForSignal(hasSignal), world.getBlockData(tilePos));
		}
	}

	private @Nullable Block<BlockLogicAlternativeLamp> getBlockForSignal(boolean hasSignal) {
		Block var10000;
		switch ((hasSignal ? 1 : 0) | (this.isInverted ? 2 : 0)) {
			case 0 -> var10000 = AlternativeLampsBlocks.ALT_LAMP_IDLE;
			case 1 -> var10000 = AlternativeLampsBlocks.ALT_LAMP_ACTIVE;
			case 2 -> var10000 = AlternativeLampsBlocks.ALT_LAMP_INVERTED_ACTIVE;
			case 3 -> var10000 = AlternativeLampsBlocks.ALT_LAMP_INVERTED_IDLE;
			default -> var10000 = null;
		}

		return var10000;
	}

	public void invertLamp(@NotNull World world, @NotNull TilePosc tilePos) {
		if (this.isInverted) {
			if (this.isActive) {
				world.setBlockTypeDataNotify(tilePos, AlternativeLampsBlocks.ALT_LAMP_IDLE, world.getBlockData(tilePos));
			} else {
				world.setBlockTypeDataNotify(tilePos, AlternativeLampsBlocks.ALT_LAMP_ACTIVE, world.getBlockData(tilePos));
			}
		} else if (this.isActive) {
			world.setBlockTypeDataNotify(tilePos, AlternativeLampsBlocks.ALT_LAMP_INVERTED_IDLE, world.getBlockData(tilePos));
		} else {
			world.setBlockTypeDataNotify(tilePos, AlternativeLampsBlocks.ALT_LAMP_INVERTED_ACTIVE, world.getBlockData(tilePos));
		}

	}

	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		return new ItemStack[]{new ItemStack(AlternativeLampsBlocks.ALT_LAMP_IDLE, 1, data)};
	}
}
