package com.tigerspike.refactoring

import java.lang.StringBuilder

class Customer(val name: String) {

    private val rentals: MutableList<Rental> = mutableListOf()

    fun addRental(rental: Rental) {
        this.rentals.add(rental)
    }


    fun statement(): String {
        var totalAmount = 0.0
        var frequentRenterPoints = 0
        val resultString = StringBuilder().appendln("Rental Record for $name")

        rentals.forEach {
            var thisAmount = 0.0

            // determine amounts for each line
            when (it.movie.priceCode) {
                Movie.REGULAR -> {
                    thisAmount += 2.0
                    if (it.daysRented > 2)
                        thisAmount += (it.daysRented - 2) * 1.5
                }
                Movie.NEW_RELEASE -> {
                    thisAmount += (it.daysRented * 3).toDouble()
                    // add bonus for a two day new release rental
                    if (it.daysRented > 1)
                        frequentRenterPoints++
                }
                Movie.CHILDRENS -> {
                    thisAmount += 1.5
                    if (it.daysRented > 3)
                        thisAmount += (it.daysRented - 3) * 1.5
                }
            }

            // add frequent renter points
            frequentRenterPoints++
            //show figures for this rental
            resultString.appendln("${it.movie.title}  $thisAmount")
            totalAmount += thisAmount
        }

        //add footer lines
        resultString
            .appendln("You owed $totalAmount")
            .appendln("You earned $frequentRenterPoints frequent renter points")

        return resultString.toString()
    }
}