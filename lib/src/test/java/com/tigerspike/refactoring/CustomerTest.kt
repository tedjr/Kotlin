package com.tigerspike.refactoring

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class CustomerTest {

    lateinit var customer: Customer
    var name = "Aziz"

    @Before
    fun setUp() {
        customer = Customer(name)
    }

    @Test
    fun statement() {
        var result = customer.statement()

        assert(result == "Rental Record for Aziz\r\n" + "You owed 0.0\r\n" + "You earned 0 frequent renter points" + "\r\n")
    }

    @Test
    fun statementWithRentals() {
        customer.addRental(
            Rental(
                Movie("Hulk", Movie.NEW_RELEASE), 1
            )
        )

        customer.addRental(
            Rental(
                Movie("IronMan", Movie.REGULAR), 3
            )
        )

        customer.addRental(
            Rental(
                Movie("Tech", Movie.CHILDRENS), 4
            )
        )

        var resultRentals = customer.statement()

        assertTrue(resultRentals == "Rental Record for Aziz\r\n" +
                "Hulk  3.0\r\n" +
                "IronMan  3.5\r\n" +
                "Tech  3.0\r\n" +
                "You owed 9.5\r\n" +
                "You earned 3 frequent renter points\r\n")
    }
}