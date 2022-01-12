package recipes.services;

import org.springframework.stereotype.Service;
import recipes.models.Recipe;
import recipes.repositories.RecipesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipesServiceImpl {
    private final RecipesRepository repository;

    public RecipesServiceImpl(RecipesRepository repository) {
        this.repository = repository;
    }

    public List<Recipe> findAllRecipes() {
        List<Recipe> recipeList = new ArrayList<>();
        repository.findAll().forEach(recipeList::add);
        return recipeList;
    }

    public void deleteRecipeById(long id) {
        repository.deleteById(id);
    }

    public Recipe save(Recipe recipe) {
        return repository.save(recipe);
    }

    public Recipe findRecipeById(Long id) {
        if (repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        return null;
    }

    public void updateRecipe(Long id, Recipe recipe) {
        if (repository.findById(id).isPresent()) {
            Recipe recipeFromDB = repository.findById(id).get();
            recipeFromDB.setName(recipe.getName());
            recipeFromDB.setCategory(recipe.getCategory());
            recipeFromDB.setDescription(recipe.getDescription());
            recipeFromDB.setIngredients(recipe.getIngredients());
            recipeFromDB.setDirections(recipe.getDirections());
            repository.save(recipeFromDB);
        }

    }

    public List<Recipe> findByNameContaining(String name) {
        return new ArrayList<>(repository.findByNameContaining(name));
    }

    public List<Recipe> findByCategory(String category) {
        return new ArrayList<>(repository.findByCategory(category));
    }
}


