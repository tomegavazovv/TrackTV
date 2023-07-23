package com.sorsix.backend.domain

import jakarta.persistence.*

@Entity
@Table(name="tracktv_user")
open class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    @Column(name="username")
    open var name: String? = null

    open internal var email: String = ""

    open internal var password: String = ""

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
    open var roles: List<Role>? = null

    constructor(username: String, password: String, email: String): this(){
        this.email = email
        this.password = password
        this.name = username
    }

    constructor(user: User): this()
}
