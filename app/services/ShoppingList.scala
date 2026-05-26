package services

import models.{ShoppingList, ShoppingListItem}
import repositories.shoppinglist.ShoppingListRepository
import scala.concurrent.Future
import javax.inject.*

trait ShoppingListService {
  def getShoppingList(email: String): Future[Either[String, ShoppingList]]
  def create(email: String, name: String, items: List[ShoppingListItem]): Future[Either[String, ShoppingList]]
}

class ShoppingListServiceImpl @Inject()(
    shoppingListRepository: ShoppingListRepository
) extends ShoppingListService {

  override def getShoppingList(email: String): Future[Either[String, ShoppingList]] = {
    shoppingListRepository.findByIdentifier(email)
  }

  override def create(email: String, name: String, items: List[ShoppingListItem]): Future[Either[String, ShoppingList]] = {
    // TODO: add checks for the items ensuring it is not empty or items have non-falsey value for quantity and name
    shoppingListRepository.create(ShoppingList(email, name, items))
  }
}
