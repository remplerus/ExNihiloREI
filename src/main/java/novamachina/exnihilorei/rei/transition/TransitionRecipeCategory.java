package novamachina.exnihilorei.rei.transition;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import javax.annotation.Nonnull;
import java.util.List;

public class TransitionRecipeCategory implements DisplayCategory<SimpleBasicDisplay> {

  @Nonnull
  public static final ResourceLocation UID =
      new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "transition");

  @Nonnull
  private static final ResourceLocation texture =
      new ResourceLocation(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_fluid_transform.png");

  public static final CategoryIdentifier<TransitionRecipeDisplay> TRANSITION = CategoryIdentifier.of(UID);

  @Override
  public CategoryIdentifier<? extends SimpleBasicDisplay> getCategoryIdentifier() {
    return TRANSITION;
  }

  @Override
  public Renderer getIcon() {
    return EntryStacks.of(EXNBlocks.OAK_BARREL);
  }

  @Override
  public List<Widget> setupDisplay(SimpleBasicDisplay display, Rectangle bounds) {
    final Point startPoint = new Point(bounds.getCenterX() - 83, bounds.getCenterY() - 27);
    List<Widget> widgets = Lists.newArrayList();
    widgets.add(Widgets.createTexturedWidget(texture, startPoint.x, startPoint.y, 0, 0, 166, 54));

    widgets.add(Widgets.createSlot(new Point(startPoint.x + 48, startPoint.y + 10))
            .entries(display.getInputEntries().get(1)).markInput());
    widgets.add(Widgets.createSlot(new Point(startPoint.x + 75, startPoint.y + 37))
            .entries(display.getInputEntries().get(0)).markInput());

    widgets.add(Widgets.createSlot(new Point(startPoint.x + 102, startPoint.y + 10))
            .entries(display.getOutputEntries().get(0)).disableBackground().markOutput());

    return widgets;
  }

  @Override
  public int getDisplayHeight() {
    return 56;
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return Component.translatable("jei.category.transition");
  }
}
