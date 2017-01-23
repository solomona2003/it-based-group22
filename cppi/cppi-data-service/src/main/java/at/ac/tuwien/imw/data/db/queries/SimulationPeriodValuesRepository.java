package at.ac.tuwien.imw.data.db.queries;

import org.springframework.data.repository.CrudRepository;

import at.ac.tuwien.imw.data.dbmodel.SimulationPeriodValues;

/**
 * Repository for writing and reading simulated values from relational DB.
 */
public interface SimulationPeriodValuesRepository extends CrudRepository<SimulationPeriodValues, Long>
{
}
