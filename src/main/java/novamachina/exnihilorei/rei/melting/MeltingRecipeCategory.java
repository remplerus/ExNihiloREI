package novamachina.exnihilorei.rei.melting;

import com.google.common.collect.Lists;
import dev.architectury.fluid.FluidStack;
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
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.crafting.MeltingRecipe;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MeltingRecipeCategory implements DisplayCategory<SimpleBasicDisplay> {

  @Nonnull
  public static final ResourceLocation UID =
          new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "melting");
  public static final CategoryIdentifier<MeltingRecipeDisplay> MELTING = CategoryIdentifier.of(UID);
  @Override
  public CategoryIdentifier<? extends SimpleBasicDisplay> getCategoryIdentifier() {
    return MELTING;
  }
  @Nonnull
  @Override
  public Component getTitle() {
    return Component.translatable("jei.category.melting");
  }
  @Nonnull
  private static final ResourceLocation texture =
      new ResourceLocation(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");

  @Nullable
  @Override
  public Renderer getIcon() {
    return EntryStacks.of(EXNBlocks.FIRED_CRUCIBLE);
  }

  @Override
  public List<Widget> setupDisplay(SimpleBasicDisplay display, Rectangle bounds) {
    final Point startPoint = new Point(bounds.getCenterX() - 83, bounds.getCenterY() - 27);
    List<Widget> widgets = Lists.newArrayList();
    widgets.add(Widgets.createTexturedWidget(texture, startPoint.x, startPoint.y, 0, 168, 166, 54));

    List<EntryIngredient> list = display.getInputEntries();
    FluidStack fluid = display.getOutputEntries().get(0).get(0).castValue();
    ItemStack stack = list.get(0).get(0).castValue();
    Optional<MeltingRecipe> recipeOptional = ExNihiloRegistries.CRUCIBLE_REGISTRY.findRecipe(stack.getItem());
    int amount = 0;
    if (recipeOptional.isPresent()) {
      amount = recipeOptional.get().getResultFluid().getAmount();
      String name = recipeOptional.get().getCrucibleType().getName();
      if (name.equals("fired")) {
        name = "Fired crucibles only";
      } else {
        name = "All crucibles";
      }
      widgets.add(Widgets.createTooltip(new Rectangle(startPoint.x + 21, startPoint.y + 21, 18, 18),
        Component.literal(String.format("%s", name))));
    }
    for (int i = 0; i < list.size(); i++) {
      final int slotX = 39 + (i % 7 * 18);
      final int slotY = 3 + i / 7 * 18;
      widgets.add(Widgets.createSlot(new Point(startPoint.x + slotX, startPoint.y + slotY))
        .entries(list.get(i)).disableBackground().markInput());
    }


    widgets.add(Widgets.createSlot(new Point(startPoint.x + 3, startPoint.y + 21))
      .entries(Collections.singletonList(EntryStacks.of(fluid.getFluid(), amount))).markOutput());

    return widgets;
  }

  @Override
  public int getDisplayHeight() {
    return 56;
  }
}
