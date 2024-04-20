package com.example.androidad.data.labstats

import javax.crypto.Mac

class LabStats {
    data class LabStats(
        var campus: String? = null,
        var building: String? = null,
        var room: String? = null,
        var windows: String? = null,
        var mac: String? = null
    ) {
        var id: String? = null //uuid
        override fun toString(): String = "$campus $building $room $windows, $mac"
    }
}