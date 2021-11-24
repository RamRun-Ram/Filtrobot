package ru.sberschool.filtrobot.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.sberschool.filtrobot.persistence.entity.Channel

@Repository
interface ChannelRepository : JpaRepository<Channel, Long>
