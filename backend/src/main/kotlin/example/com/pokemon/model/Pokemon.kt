package example.com.pokemon.model

import jakarta.persistence.*

// pokemon database entity
@Entity
@Table(name = "pokemons")
class Pokemon(
    // primary key identifier
    @Id
    var id: Long = 0,

    // english name mapping
    @Column(name = "name_en")
    var nameEn: String = "",

    // image assets URL
    @Column(name = "image_url")
    var imageUrl: String = "",

    // primary elemental type
    @Column(name = "type")
    var type: String = "",

    // secondary optional type
    @Column(name = "type2")
    var type2: String? = null
) {
    // no-argument fallback constructor
    constructor() : this(0, "", "", "")
}