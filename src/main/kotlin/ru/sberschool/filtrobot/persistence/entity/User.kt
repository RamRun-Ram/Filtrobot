package ru.sberschool.filtrobot.persistence.entity

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Table(name = "users")
class User(

    /**
     * ID пользователя в Telegram
     */
    @Id
    var id: Long,

    /**
     * Имя пользователя в Telegram
     */
    @NaturalId
    var username: String,

    /**
     * ID чата с ботом, куда будет отправляться посты
     */
    var chatId: Long?,

    /**
     * Набор предпочтений клиента, по которым будет выполняться фильтрация отправляемых постов
     */
    @OneToMany(mappedBy = "user")
    var preferences: List<Preference>?,

    /**
     * Маркер, является ли клиент активным и отправлять ли ему сообщения
     */
    var isActive: Boolean,

    /**
     * Каналы, с которых пользователь хочет получать уведомления
     */
    @ManyToMany(mappedBy = "subscribers")
    var channels: List<Channel>?

)
