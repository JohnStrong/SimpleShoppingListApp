import models.Customer
import services.{CustomerService, CustomerServiceImpl, ShoppingListService, ShoppingListServiceImpl}
import com.google.inject.{AbstractModule, TypeLiteral}
import repositories.DataRepository
import repositories.customer.CustomerRepository

class Module extends AbstractModule {
  override def configure(): Unit = {

    /**
     * Customer
     */
    bind(new TypeLiteral[DataRepository[String, Customer]]() {}).to(classOf[CustomerRepository])
    bind(classOf[CustomerService]).to(classOf[CustomerServiceImpl])

    /**
     * Shopping List
     */
    bind(classOf[ShoppingListService]).to(classOf[ShoppingListServiceImpl])
  }
}
