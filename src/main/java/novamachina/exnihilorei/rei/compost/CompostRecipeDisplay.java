package novamachina.exnihilorei.rei.compost;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.CompostRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompostRecipeDisplay extends SimpleBasicDisplay {
    public CompostRecipeDisplay(RecipeHolder<CompostRecipe> recipe) {
        //TODO: should be changed too if compost recipe will have different outputs!
        super(getIngredients(recipe.value()), Collections.singletonList(EntryIngredient.of(EntryStacks.of(Items.DIRT))));
    }

    private static List<EntryIngredient> getIngredients(CompostRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.ofIngredient(recipe.getInput()));
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CompostRecipeCategory.COMPOSTING;
    }
}
