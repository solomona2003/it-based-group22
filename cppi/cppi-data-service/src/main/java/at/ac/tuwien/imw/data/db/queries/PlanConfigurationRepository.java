package at.ac.tuwien.imw.data.db.queries;

import org.springframework.data.repository.CrudRepository;

import at.ac.tuwien.imw.data.dbmodel.PlanConfiguration;

/**
 * Repository for writing and reading plan configuration data from relational
 * DB.
 */
public interface PlanConfigurationRepository extends CrudRepository<PlanConfiguration, Long>
{
}
