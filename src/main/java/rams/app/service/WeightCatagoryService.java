package rams.app.service;

import java.lang.Long;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import rams.app.domain.Fight;
import rams.app.domain.WeightCatagory;

/**
 * Spring service that handles CRUD requests for WeightCatagory entities
 * 
 */
public interface WeightCatagoryService {

	/**
	 * Save an existing WeightCatagory entity
	 * 
	 */
	public void saveWeightCatagory(WeightCatagory weightcatagory);

	/**
	 * Delete an existing WeightCatagory entity
	 * 
	 */
	public void deleteWeightCatagory(WeightCatagory weightcatagory_1);

	/**
	 */
	public WeightCatagory findWeightCatagoryByPrimaryKey(Long idWeightCatagory);

	/**
	 * Load an existing WeightCatagory entity
	 * 
	 */
	public List<WeightCatagory> loadWeightCatagorys();

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	public WeightCatagory deleteWeightCatagoryFightCollection(Long weightcatagory_idWeightCatagory, Long related_fightcollection_idFight);

	/**
	 * Return a count of all WeightCatagory entity
	 * 
	 */
	public Long countWeightCatagorys();

	/**
	 * Return all WeightCatagory entity
	 * 
	 */
	public Page<WeightCatagory> findAllWeightCatagorys(int idx, int elements, String dir );

	/**
	 * Save an existing Fight entity
	 * 
	 */
	public WeightCatagory saveWeightCatagoryFightCollection(Long idWeightCatagory_1, Fight related_fightcollection);
}