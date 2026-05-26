package repositories

import scala.collection.mutable

/**
 * In-memory implementation of [[DataRepository]] backed by a mutable HashMap.
 *
 * Intended for local development and unit testing where no external database is
 * required. Data lives only for the lifetime of the JVM — restarting the service
 * clears all stored entities.
 *
 * Concrete subclasses (e.g. CustomerRepository) inherit the [[repo]] map and
 * implement the CRUD operations using it directly.
 *
 * @tparam KEY the map key type, corresponding to the entity's unique identifier
 * @tparam ENTITY the domain model type stored in the map
 */
trait InMemoryDataRepository[KEY, ENTITY] extends DataRepository[KEY, ENTITY] {

  /**
   * The backing in-memory store shared by all operations in a concrete repository.
   *
   * Declared as `implicit val` so that concrete subclasses can access it without
   * explicit wiring — it is automatically available in scope for any method that
   * needs the store. This keeps subclass implementations concise while making the
   * storage mechanism swappable at the trait level.
   *
   * Note: This store is not thread-safe. For concurrent access in production,
   * use a database-backed [[DataRepository]] implementation instead.
   */
  implicit val repo: mutable.Map[KEY, ENTITY] = mutable.HashMap[KEY, ENTITY]()
}
