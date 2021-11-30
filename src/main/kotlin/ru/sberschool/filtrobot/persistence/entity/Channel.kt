package ru.sberschool.filtrobot.persistence.entity

import javax.persistence.*

@Entity
@Table(name = "channel")
class Channel(
    @Id
    @SequenceGenerator(name = "channel_id_gen", initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "channel_id_gen")
    var id: Long?,

    /**
     * ID канала в Telegram
     */
    @Column(name = "channel_id")
    var channelId: Long,

    var name: String,

    var url: String,

    /**
     * Пользователи, подписанные на уведомления о новых постах в этом канале
     */
    @ManyToMany
    @JoinTable(
        name = "channel_user",
        joinColumns = [JoinColumn(name = "channel_id", referencedColumnName = "channel_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")]
    )
    var subscribers: List<User>?
)
