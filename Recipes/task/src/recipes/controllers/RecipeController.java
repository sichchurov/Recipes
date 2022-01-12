package recipes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.models.Recipe;
import recipes.services.RecipesServiceImpl;

import javax.persistence.Entity;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recipe/")
public class RecipeController {

    private final RecipesServiceImpl recipeService;

    RecipeController(RecipesServiceImpl recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/new")
    public Object create(@Valid @RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.save(recipe);
        return String.format("{\"id\": %d}", newRecipe.getId());
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> findAllRecipes() {
        List<Recipe> recipeList = recipeService.findAllRecipes();
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", params = "name")
    public ResponseEntity<List<Recipe>> nameContaining(String name) {
        List<Recipe> recipeList = recipeService.findByNameContaining(name);
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", params = "category")
    public ResponseEntity<List<Recipe>> category(String category) {
        List<Recipe> recipeList = recipeService.findByCategory(category);
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        if (recipeService.findRecipeById(id) != null) {
            return recipeService.findRecipeById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public void updateRecipe(@Valid @RequestBody Recipe recipe, @PathVariable Long id) {
        if (recipeService.findRecipeById(id) != null) {
            recipeService.updateRecipe(id, recipe);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public Entity deleteRecipe(@PathVariable Long id) {

        if (recipeService.findRecipeById(id) != null) {
            recipeService.deleteRecipeById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}