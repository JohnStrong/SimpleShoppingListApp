package repositories.customer

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.concurrent.ScalaFutures
import models.Customer

class CustomerRepositorySpec extends AnyWordSpec with Matchers with ScalaFutures {

  val testCustomer = Customer(email = "test@example.com")
  val customerRepository = CustomerRepository()

  "create" should {
    "persist the customer in the repo and return it" in {

      val result = customerRepository.create(testCustomer).futureValue
      result shouldBe Right(testCustomer)
    }

    "return an error message if customer already exists in repo" in {
      customerRepository.create(testCustomer).futureValue

      val result = customerRepository.create(testCustomer).futureValue

      result shouldBe a[Left[_, _]]
      result.left.toOption.get should include("already exists")
    }
  }
}
