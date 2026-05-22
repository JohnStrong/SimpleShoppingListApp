package org.myapps.shoppinglistservice.controller

import org.mockito.Mockito
import org.myapps.shoppinglistservice.service.CustomerService
import org.myapps.shoppinglistservice.model.Customer
import org.scalatest.flatspec.AnyFlatSpec

import java.util.Optional

class CustomerControllerSpec extends AnyFlatSpec with org.scalatest.matchers.should.Matchers {

  "CustomerController" should "return the customer information by customer_id" in {
    // Given
    val mockService = Mockito.mock(classOf[CustomerService])
    val mockCustomer = customer("john_doe@example.com")
    Mockito.when(mockService.getCustomer(1L))
      .thenReturn(Optional.of(mockCustomer))
    val controller = new CustomerController(mockService) // You may need to mock dependencies here
    // When
    val returnedCustomer = controller.getCustomerById(1L)

    // Then
    returnedCustomer.getStatusCode.is2xxSuccessful shouldBe true
    returnedCustomer.getBody.getEmail shouldBe mockCustomer.getEmail
  }

  def customer(email: String): Customer = {
    val customer = new Customer()
    customer.setEmail(email)
    customer
  }
}
