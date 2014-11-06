package rams.app.service;

import java.lang.Long;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rams.app.domain.Fight;
import rams.app.domain.WeightCatagory;
import rams.app.rerpository.FightRepository;
import rams.app.rerpository.WeightCatagoryRepository;

/**
 * Spring service that handles CRUD requests for WeightCatagory entities
 * 
 */

@Service("WeightCatagoryService")
@Transactional
public class WeightCatagoryServiceImpl implements WeightCatagoryService {

	/**
	 * DAO injected by Spring that manages Fight entities
	 * 
	 */
	@Autowired
	private FightRepository fightDAO;

	/**
	 * DAO injected by Spring that manages WeightCatagory entities
	 * 
	 */
	@Autowired
	private WeightCatagoryRepository weightCatagoryDAO;

	/**
	 * Instantiates a new WeightCatagoryServiceImpl.
	 *
	 */
	public WeightCatagoryServiceImpl() {
	}

	/**
	 * Save an existing WeightCatagory entity
	 * 
	 */
	@Transactional
	public void saveWeightCatagory(WeightCatagory weightcatagory) {
		WeightCatagory existingWeightCatagory = weightCatagoryDAO.findByIdWeightCatagory(weightcatagory.getIdWeightCatagory());

		if (existingWeightCatagory != null) {
			if (existingWeightCatagory != weightcatagory) {
				existingWeightCatagory.setIdWeightCatagory(weightcatagory.getIdWeightCatagory());
				existingWeightCatagory.setWeightCatagory(weightcatagory.getWeightCatagory());
				existingWeightCatagory.setWeightCatagoryComments(weightcatagory.getWeightCatagoryComments());
			}
			weightcatagory = weightCatagoryDAO.save(existingWeightCatagory);
		} else {
			weightcatagory = weightCatagoryDAO.save(weightcatagory);
		}
		weightCatagoryDAO.flush();
	}

	/**
	 * Delete an existing WeightCatagory entity
	 * 
	 */
	@Transactional
	public void deleteWeightCatagory(WeightCatagory weightcatagory) {
		weightCatagoryDAO.delete(weightcatagory);
		weightCatagoryDAO.flush();
	}

	/**
	 */
	@Transactional
	public WeightCatagory findWeightCatagoryByPrimaryKey(Long idWeightCatagory) {
		return weightCatagoryDAO.findByIdWeightCatagory(idWeightCatagory);
	}

	/**
	 * Load an existing WeightCatagory entity
	 * 
	 */
	@Transactional
	public List<WeightCatagory> loadWeightCatagorys() {
		return weightCatagoryDAO.findAll();
	}

	/**
	 * Delete an existing Fight entity
	 * 
	 */
	@Transactional
	public WeightCatagory deleteWeightCatagoryFightCollection(Long weightcatagory_idWeightCatagory, Long related_fightcollection_idFight) {
		Fight related_fightcollection = fightDAO.findByidFight(related_fightcollection_idFight);

		WeightCatagory weightcatagory = weightCatagoryDAO.findByIdWeightCatagory(weightcatagory_idWeightCatagory);

		related_fightcollection.setWeightCatagoryId(null);
		weightcatagory.setFightCollection(null);

		fightDAO.delete(related_fightcollection);
		fightDAO.flush();

		return weightcatagory;
	}

	/**
	 * Return a count of all WeightCatagory entity
	 * 
	 */
	@Transactional
	public Long countWeightCatagorys() {
		return  weightCatagoryDAO.count();
	}

	/**
	 * Return all WeightCatagory entity
	 * 
	 */
	

	/**
	 * Save an existing Fight entity
	 * 
	 */
	@Transactional
	public WeightCatagory saveWeightCatagoryFightCollection(Long idWeightCatagory, Fight related_fightcollection) {
		WeightCatagory weightcatagory = weightCatagoryDAO.findByIdWeightCatagory(idWeightCatagory);
		Fight existingfightCollection = fightDAO.findByidFight(related_fightcollection.getIdFight());

		// copy into the existing record to preserve existing relationships
		if (existingfightCollection != null) {
			existingfightCollection.setIdFight(related_fightcollection.getIdFight());
			existingfightCollection.setFightComments(related_fightcollection.getFightComments());
			related_fightcollection = existingfightCollection;
		} else {
			related_fightcollection = fightDAO.save(related_fightcollection);
			fightDAO.flush();
		}

		related_fightcollection.setWeightCatagoryId(weightcatagory);
		weightcatagory.setFightCollection((Collection<Fight>) related_fightcollection);
		related_fightcollection = fightDAO.save(related_fightcollection);
		fightDAO.flush();

		weightcatagory = weightCatagoryDAO.save(weightcatagory);
		weightCatagoryDAO.flush();

		return weightcatagory;
	}

	@Transactional
	public Page<WeightCatagory> findAllWeightCatagorys(int idx, int elements,
			String dir) {
		// TODO Auto-generated method stub
		return weightCatagoryDAO.findAll(constructPageSpecification(idx, elements, dir));
	}
	
	private Pageable constructPageSpecification(int pageIndex, int elements,String dir) {
		Sort srt;
		if(dir=="ASC")
			srt= new Sort(Sort.Direction.ASC, "cityName");
		else
			srt= new Sort(Sort.Direction.DESC, "cityName");
        Pageable pageSpecification = new PageRequest(pageIndex, elements, srt );
        return pageSpecification;
    }
}
