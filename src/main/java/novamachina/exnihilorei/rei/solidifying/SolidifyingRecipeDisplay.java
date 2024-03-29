package novamachina.exnihilorei.rei.solidifying;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.world.item.crafting.RecipeHolder;
import novamachina.exnihilorei.rei.SimpleBasicDisplay;
import novamachina.exnihilosequentia.world.item.crafting.SolidifyingRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolidifyingRecipeDisplay extends SimpleBasicDisplay {
    public SolidifyingRecipeDisplay(RecipeHolder<SolidifyingRecipe> recipe) {
        super(getIngredients(recipe.value()), Collections.singletonList(EntryIngredient.of(EntryStacks.of(recipe.value().getResult()))));
    }

    private static List<EntryIngredient> getIngredients(SolidifyingRecipe recipe) {
        List<EntryIngredient> list = new ArrayList<>();
        list.add(EntryIngredients.of(recipe.getFluidOnTop().getFluid()));
        list.add(EntryIngredients.of(recipe.getFluidInTank().getFluid()));
        return list;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return SolidifyingRecipeCategory.SOLIDIFY;
    }
}
