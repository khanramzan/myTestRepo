package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.MediaTable;;

public interface MediaTableRepository extends JpaRepository<MediaTable,Long>{
	public MediaTable findByIdMedia(Long idMedia);
}
