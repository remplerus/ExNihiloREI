package novamachina.exnihilorei.rei.sifting;

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
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.common.registries.ExNihiloRegistries;
import novamachina.exnihilosequentia.common.utility.ExNihiloConstants;
import novamachina.exnihilosequentia.world.item.MeshItem;
import novamachina.exnihilosequentia.world.item.MeshType;
import novamachina.exnihilosequentia.world.item.crafting.SiftingRecipe;
import novamachina.exnihilosequentia.world.level.block.EXNBlocks;
import novamachina.novacore.util.StringUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SiftingRecipeCategory implements DisplayCategory<SimpleBasicDisplay> {
  private static final List<SiftingRecipe> removah = new ArrayList<>();
    @Nonnull
  public static final ResourceLocation UID =
          new ResourceLocation(ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "wet_sifting");
  public static final CategoryIdentifier<SiftingRecipeDisplay> SIFTING = CategoryIdentifier.of(UID);

  @Nonnull
  protected static final ResourceLocation texture =
      new ResourceLocation(
          ExNihiloConstants.ModIds.EX_NIHILO_SEQUENTIA, "textures/gui/jei_mid.png");

  public SiftingRecipeCategory() {

  }

  @Override
  public Renderer getIcon() {
    return EntryStacks.of(EXNBlocks.OAK_SIEVE);
  }

  @Override
  public List<Widget> setupDisplay(SimpleBasicDisplay display, Rectangle bounds) {
    final Point startPoint = new Point(bounds.getCenterX() - 83, bounds.getCenterY() - 27);
    List<Widget> widgets = Lists.newArrayList();
    widgets.add(Widgets.createSlot(new Point(startPoint.x + 11, startPoint.y + 3))
            .entries(display.getInputEntries().get(0)).markInput());
    widgets.add(Widgets.createTexturedWidget(texture, startPoint.x, startPoint.y, 0, 0, 166, 54));
    List<SiftingRecipe> recipes = ExNihiloRegistries.SIEVE_REGISTRY.getRecipeList();
    recipes.removeAll(removah);
    Iterator<SiftingRecipe> recipeIterator = recipes.iterator();
    while (recipeIterator.hasNext()) {
      SiftingRecipe recipeTest1 = recipeIterator.next();
      List<SiftingRecipe> tempList = new ArrayList<>();
      tempList.add(recipeTest1);
      removah.add(recipeTest1);

      for (SiftingRecipe recipeTest2 : recipes) {
        if (!(recipeTest1 == recipeTest2)) {
          if (recipeTest1.getInput().test(recipeTest2.getInput().getItems()[0])) {
            if (recipeTest1.isWaterlogged() == recipeTest2.isWaterlogged()) {
              for (int i = 0; i < recipeTest1.getRolls().size(); i++) {
                for (int j = 0; j < recipeTest2.getRolls().size(); j++) {
                  if (recipeTest1.getRolls().get(i).getMesh() == recipeTest2.getRolls().get(j).getMesh()) {
                    tempList.add(recipeTest2);
                    removah.add(recipeTest2);
                  }
                }
              }
            }
          }
        }
      }

      for (int i = 0; i < tempList.size(); i++) {
        SiftingRecipe recipe = tempList.get(i);
        if (recipeTest1.getInput().test(recipe.getInput().getItems()[0])) {
            @Nonnull final EntryIngredient outputStack = EntryIngredient.of(EntryStacks.of(recipe.getDrop()));
            for (int j = 0; j < recipeTest1.getRolls().size(); j++) {
              //String name = recipeTest1.isWaterlogged() ? "Wet Sifting" : "Dry Sifting";
              //widgets.add(Widgets.createTooltip(new Rectangle(startPoint.x + 21, startPoint.y + 21, 18, 18),
              //        Component.literal(String.format("%s", name))));
              MeshType mesh = recipeTest1.getRolls().get(j).getMesh();
              for (int k = 0; k < recipe.getRolls().size(); k++) {
                if (recipe.getRolls().get(k).getMesh() == mesh) {
                  outputStack.get(0).tooltip(Component.literal(String.format(
                          "Chance: %s", StringUtils.formatPercent(recipe.getRolls().get(k).getChance()))));
                }
              }
              widgets.add(Widgets.createSlot(new Point(startPoint.x + 11, startPoint.y + 38))
                      .entries(EntryIngredients.of(MeshItem.getMesh(mesh))).markInput());
            }

            final int slotX = 39 + (i % 7 * 18);
            final int slotY = 3 + i / 7 * 18;
            widgets.add(Widgets.createSlot(new Point(startPoint.x + slotX, startPoint.y + slotY))
                    .entries(outputStack).disableBackground().markOutput());
          }
        }
      recipeIterator.remove();
    }


    return widgets;
  }

  @Override
  public CategoryIdentifier<? extends SimpleBasicDisplay> getCategoryIdentifier() {
    return SIFTING;
  }

  @Nonnull
  @Override
  public Component getTitle() {
    return Component.translatable("jei.category.dry_sifting");
  }

  @Override
  public int getDisplayHeight() {
    return 56;
  }
}
