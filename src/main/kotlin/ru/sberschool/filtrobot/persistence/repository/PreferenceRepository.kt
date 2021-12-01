package ru.sberschool.filtrobot.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.sberschool.filtrobot.persistence.entity.Preference

@Repository
interface PreferenceRepository : JpaRepository<Preference, Long>
