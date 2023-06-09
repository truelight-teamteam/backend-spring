package com.teamteam.backend.domain.room.dto

import com.teamteam.backend.domain.equipment.entity.EquipmentType
import com.teamteam.backend.domain.room.entity.Room

class RoomCreateDTO(
    val name: String,
    val capacity: Long,
    val description: String,
    val equipments: Set<EquipmentType>
) {
    fun toEntity(roomId:String, buildingId: String): Room = Room(
        id = roomId,
        buildingId = buildingId,
        name = name,
        capacity = capacity,
        description = description
    )
}