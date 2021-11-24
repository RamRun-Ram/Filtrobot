package ru.sberschool.filtrobot.persistence.entity

import javax.persistence.*

@Entity
@Table(name = "channel")
class Channel(
    @Id
    @SequenceGenerator(name = "channel_id_gen", initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "channel_id_gen")
    var id: Long?,

    var name: String,

    var url: String
)
