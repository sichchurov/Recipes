package recipes.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import recipes.models.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipesRepository extends CrudRepository<Recipe, Long> {

    void deleteById(Long id);

    Optional<Recipe> findById(Long id);

    @Query("SELECT n FROM Recipe n WHERE UPPER(n.name) LIKE UPPER(CONCAT('%', :name, '%')) order by n.date DESC")
    List<Recipe> findByNameContaining(@Param("name") String name);

    @Query("SELECT c FROM Recipe c WHERE UPPER(c.category) = UPPER(:category) order by c.date DESC")
    List<Recipe> findByCategory(@Param("category") String category);

}
