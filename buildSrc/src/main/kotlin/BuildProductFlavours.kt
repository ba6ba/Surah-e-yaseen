import org.gradle.api.NamedDomainObjectContainer
import com.android.build.gradle.internal.dsl.ProductFlavor

object FlavourDimension {
    const val environment = "env"
}

interface BuildProductFlavours {
    val name : String

    fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor

    fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>
    ): ProductFlavor
}

object DevFlavour : BuildProductFlavours {
    override val name: String
        get() = "dev"

    override fun libraryCreate(namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>) : ProductFlavor =
        namedDomainObjectContainer.create(name) {
            versionNameSuffix = "-$name"
            dimension = FlavourDimension.environment
        }

    override fun appCreate(namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        namedDomainObjectContainer.create(name) {
            applicationIdSuffix = ".$name"
            versionNameSuffix = "-$name"
            dimension = FlavourDimension.environment
        }
}

object ProdFlavour : BuildProductFlavours {
    override val name: String
        get() = "prod"

    override fun libraryCreate(namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>) : ProductFlavor =
        namedDomainObjectContainer.create(name) {
            dimension = FlavourDimension.environment
        }

    override fun appCreate(namedDomainObjectContainer: NamedDomainObjectContainer<ProductFlavor>): ProductFlavor =
        namedDomainObjectContainer.create(name) {
            dimension = FlavourDimension.environment
        }
}