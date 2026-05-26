package services

import scala.collection.mutable
import javax.inject.*
import models.Customer
import repositories.DataRepository

import scala.concurrent.Future

type ErrorMessage = String

trait CustomerService {
  def createCustomer(email: String): Future[Either[ErrorMessage, Customer]]
  def findByEmail(email: String): Future[Either[ErrorMessage, Customer]]
}

class CustomerServiceImpl @Inject()(
    val dataRepository: DataRepository[String, Customer]
) extends CustomerService {

  override def createCustomer(email: String): Future[Either[ErrorMessage, Customer]] = {
    dataRepository.create(Customer(email))
  }

  override def findByEmail(email: String): Future[Either[ErrorMessage, Customer]] = {
    dataRepository.findByIdentifier(email)
  }
}
