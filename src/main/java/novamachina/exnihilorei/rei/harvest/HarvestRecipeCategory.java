package novamachina.exnihilorei.rei.harvest;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilorei.rei.crushing.CrushingRecipeDisplay;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.EXNItems;
import novamachina.exnihilosequentia.world.item.crafting.HarvestRecipe;
import novamachina.novacore.util.StringUtils;

import javax.annotation.Nonnull;
import java.util.List;

public class HarvestRecipeCategory implements DisplayCategory<SimpleBasicDisplay> {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "harvest");

  @Nonnull
  private static final ResourceLocation texture =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");
  public static final CategoryIdentifier<CrushingRecipeDisplay> HARVESTING = CategoryIdentifier.of(UID);

  @Override
  public CategoryIdentifier<? extends SimpleBasicDisplay> getCategoryIdentifier() {
    return HARVESTING;
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return Component.translatable("jei.category.harvest");
  }

  @Override
  public Renderer getIcon() {
    return EntryStacks.of(EXNItems.CROOK_WOOD);
  }

  @Override
  public List<Widget> setupDisplay(SimpleBasicDisplay display, Rectangle bounds) {
    final Point startPoint = new Point(bounds.getCenterX() - 83, bounds.getCenterY() - 27);
    List<Widget> widgets = Lists.newArrayList();
    widgets.add(Widgets.createTexturedWidget(texture, startPoint.x, startPoint.y, 0, 112, 166, 54));

    widgets.add(Widgets.createSlot(new Point(startPoint.x + 11, startPoint.y + 39))
            .entries(display.getInputEntries().get(0)).markInput());

    int rerolls = 1;
    if (display.getInputEntries().size() > 21) {
      rerolls = (int) Math.ceil(display.getInputEntries().size() / 21.0);
    }
    while (rerolls > 0) {
      for (int i = 0; i < display.getOutputEntries().size(); i++) {
        final int slotX = 39 + (i % 7 * 18);
        final int slotY = 3 + i / 7 * 18;

        @Nonnull final EntryIngredient outputStack = display.getOutputEntries().get(i);
        HarvestRecipe recipe = ExNihiloRegistries.CROOK_REGISTRY.getRecipeList().get(i);
        outputStack.get(0).tooltip(Component.literal(String.format(
                "Chance: %s", StringUtils.formatPercent(recipe.getDrops().get(i).getChance()))));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + slotX, startPoint.y + slotY))
                .entries(outputStack).disableBackground().markOutput());
      }
      rerolls--;
    }

    return widgets;
  }

  @Override
  public int getDisplayHeight() {
    return 56;
  }
}
