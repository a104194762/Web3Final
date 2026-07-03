package example.com.pokemon.model

import jakarta.persistence.*

@Entity
@Table(name = "pokemons")
class Pokemon(
    @Id
    var id: Long = 0,

    @Column(name = "name_en")
    var nameEn: String = "",

    @Column(name = "image_url")
    var imageUrl: String = "",

    @Column(name = "type")
    var type: String = "",
    @Column(name = "type2")
    var type2: String? = null
) {
    // Hibernate 需要无参构造函数
    constructor() : this(0, "", "", "")
}