package ru.sberschool.filtrobot.persistence.entity

import javax.persistence.*

/**
 * Описывает предпочтение пользователя для фильтрации пересылаемых постов
 */
@Entity
@Table(name = "preference")
class Preference(
    @Id
    @SequenceGenerator(name = "preference_id_gen", initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preference_id_gen")
    var id: Long?,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: User?,

    var keyword: String?,

    @Enumerated(value = EnumType.STRING)
    var preferenceType: PreferenceType
) {
}

enum class PreferenceType {
    INCLUDE,
    EXCLUDE
}
