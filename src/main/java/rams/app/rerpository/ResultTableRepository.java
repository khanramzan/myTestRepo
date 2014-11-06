package rams.app.rerpository;

import org.springframework.data.jpa.repository.JpaRepository;

import rams.app.domain.ResultTable;

public interface ResultTableRepository extends JpaRepository<ResultTable,Long> {
	public ResultTable findByIdResult(Long idResultTable);
}
