package novamachina.exnihilorei.rei.heat;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.crafting.HeatRecipe;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class HeatRecipeCategory implements DisplayCategory<SimpleBasicDisplay> {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "heat");
  public static final ResourceLocation texture = new ResourceLocation(
          "roughlyenoughitems", "textures/gui/display.png");
  public static final CategoryIdentifier<HeatRecipeDisplay> HEATING = CategoryIdentifier.of(UID);

  @Nullable
  @Override
  public Renderer getIcon() {
    return EntryStacks.of(EXNBlocks.FIRED_CRUCIBLE);
  }

  @Override
  public CategoryIdentifier<? extends SimpleBasicDisplay> getCategoryIdentifier() {
    return HEATING;
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return Component.translatable("jei.category.heat");
  }

  @Override
  public List<Widget> setupDisplay(SimpleBasicDisplay display, Rectangle bounds) {
    final Point startPoint = new Point(bounds.getCenterX() - 9, bounds.getCenterY() - 7);
    List<Widget> widgets = Lists.newArrayList();
    widgets.add(Widgets.createTexturedWidget(texture, startPoint.x, startPoint.y, 0, 74, 18, 34));
    widgets.add(Widgets.createSlot(new Point(startPoint.x + 1, startPoint.y - 18))
            .entries(EntryIngredients.of(EXNBlocks.FIRED_CRUCIBLE)));
    widgets.add(Widgets.createBurningFire(new Point(startPoint.x + 1, startPoint.y))
            .animationDurationTicks(60 * 5));
    EntryIngredient ingredient = display.getInputEntries().get(0);

    HeatRecipe recipe = ExNihiloRegistries.HEAT_REGISTRY.getRecipeList().get(0);

    ingredient.get(0).tooltip(Component.literal(recipe.getAmount() + "X"));
    ItemStack stack = ingredient.get(0).castValue();
    Block block = Blocks.AIR;
    if (!stack.isEmpty()) {
      block = ((BlockItem) stack.getItem()).getBlock();
    }

    if (!stack.is(Items.AIR) || !block.defaultBlockState().is(Blocks.AIR)) {
      widgets.add(Widgets.createSlot(new Point(startPoint.x + 1, startPoint.y + 17))
              .entries(ingredient).markInput());
    }

    return widgets;
  }

  @Override
  public int getDisplayHeight() {
    return 56;
  }
}
