package com.example.rides

import org.junit.Assert
import org.junit.Test

class EstimateCarbonTest {

    @Test
    fun estimatedemision() {
        val kilometerage = 6000
        Assert.assertEquals(6500, VehicleDetailFragment.estimatedemision(kilometerage))
    }

}