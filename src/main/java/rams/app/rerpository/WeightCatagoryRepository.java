package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.WeightCatagory;

public interface WeightCatagoryRepository extends JpaRepository<WeightCatagory, Long>{
	public WeightCatagory findByIdWeightCatagory(Long idWeightCatagory);
}
