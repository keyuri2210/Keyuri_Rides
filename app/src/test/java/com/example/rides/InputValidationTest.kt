package com.example.rides

import org.junit.Assert
import org.junit.Test

class InputValidationTest {

    @Test
    fun whenInputIsValid() {
        val max = 100;
        val min = 1;
        val value = 2;
        Assert.assertTrue(VehicleListFragment.isBetween(value, min, max))
    }

    @Test
    fun whenInputIsInValid() {
        val max = 100;
        val min = 1;
        val value = 0;
        Assert.assertFalse(VehicleListFragment.isBetween(value, min, max))
    }
}