package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.City;
import rams.app.domain.EventTable;


public interface EventTableRepository extends JpaRepository<EventTable,Long>{
	public EventTable findByIdEvent(Long idEvent) ;
}
