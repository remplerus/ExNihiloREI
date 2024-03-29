package novamachina.exnihilorei.rei.compost;

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
import net.minecraft.world.item.ItemStack;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.common.Config;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

public class CompostRecipeCategory implements DisplayCategory<SimpleBasicDisplay> {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "compost");

  @Nonnull
  private static final ResourceLocation texture =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");
  public static final CategoryIdentifier<CompostRecipeDisplay> COMPOSTING = CategoryIdentifier.of(UID);

  @Override
  public CategoryIdentifier<? extends SimpleBasicDisplay> getCategoryIdentifier() {
    return COMPOSTING;
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return Component.translatable("jei.category.compost");
  }

  @Override
  public Renderer getIcon() {
    return EntryStacks.of(EXNBlocks.OAK_BARREL);
  }

  @Override
  public List<Widget> setupDisplay(SimpleBasicDisplay display, Rectangle bounds) {
    final Point startPoint = new Point(bounds.getCenterX() - 83, bounds.getCenterY() - 27);
    List<Widget> widgets = Lists.newArrayList();
    widgets.add(Widgets.createTexturedWidget(texture, startPoint.x, startPoint.y, 0, 168, 166, 54));

    EntryIngredient output = display.getOutputEntries().get(0);
    for (CompostRecipe recipe : ExNihiloRegistries.COMPOST_REGISTRY.getRecipeList()) {
      if (Arrays.stream(recipe.getInput().getItems()).toList().get(0).is(((ItemStack) display.getInputEntries().get(0).get(0).castValue()).getItem())) {
        output.get(0).tooltip(Component.literal(String.format("Amount: %d / %d", recipe.getAmount(), Config.getBarrelMaxSolidAmount())));
      }
    }
    widgets.add(Widgets.createSlot(new Point(startPoint.x + 3, startPoint.y + 21))
        .entries(output).markOutput());

    int rerolls = 1;
    if (display.getInputEntries().size() > 21) {
        rerolls = (int) Math.ceil(display.getInputEntries().size() / 21.0);
    }
    while (rerolls > 0) {
      for (int xf = 0; xf < display.getInputEntries().size(); xf++) {
        final int slotX = 39 + (xf % 7 * 18);
        final int slotY = 3 + xf / 7 * 18;
        widgets.add(Widgets.createSlot(new Point(startPoint.x + slotX, startPoint.y + slotY))
          .entries(display.getInputEntries().get(xf)).tooltipsEnabled(true).markInput());
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
