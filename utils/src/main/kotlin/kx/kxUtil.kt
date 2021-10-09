package kx

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

interface KxProjectInterface {
    val name: String
}

interface KxSingleProjectInterface : KxProjectInterface
interface KxMultiProjectInterface : KxProjectInterface {
    val modules: List<KxSingleProjectInterface>
}

object KxProject {

    object kool : KxSingleProjectInterface {
        override val name = "kool"
    }

    object unsigned : KxSingleProjectInterface {
        override val name = "unsigned"
    }

    object glm : KxSingleProjectInterface {
        override val name = "glm"
    }

    object gli : KxSingleProjectInterface {
        override val name = "gli"
    }

    object gln : KxSingleProjectInterface {
        override val name = "gln"
    }

    object vkk : KxSingleProjectInterface {
        override val name = "vkk"
    }

    object uno : KxMultiProjectInterface {
        override val name = "uno"

        object awt : KxSingleProjectInterface {
            override val name = "uno-awt"
        }

        object core : KxSingleProjectInterface {
            override val name = "uno-core"
        }

        object vk : KxSingleProjectInterface {
            override val name = "uno-vk"
        }

        override val modules: List<KxSingleProjectInterface> = listOf(awt, core, vk)
    }

    object imgui : KxMultiProjectInterface {
        override val name = "imgui"

        object core : KxSingleProjectInterface {
            override val name = "imgui-core"
        }

        object gl : KxSingleProjectInterface {
            override val name = "imgui-gl"
        }

        object glfw : KxSingleProjectInterface {
            override val name = "imgui-glfw"
        }

        object openjfx : KxSingleProjectInterface {
            override val name = "imgui-openjfx"
        }

        object vk : KxSingleProjectInterface {
            override val name = "imgui-vk"
        }

        override val modules: List<KxSingleProjectInterface> = listOf(uno.awt, uno.core, uno.vk)
    }

    object assimp : KxSingleProjectInterface {
        override val name = "assimp"
    }

    object openvr : KxSingleProjectInterface {
        override val name = "openvr"
    }
}

//sealed interface Expr

fun DependencyHandler.implementation(vararg projects: KxProjectInterface) = implementation(false, projects)

fun DependencyHandler.testImplementation(vararg projects: KxProjectInterface) = implementation(true, projects)

//fun DependencyHandler.kxImplementation(vararg projects: KxProject) = add(false, projects)
//fun DependencyHandler.kxTestImplementation(vararg projects: KxProject) = add(true, projects)

//private fun DependencyHandler.add(test: Boolean, projects: Array<out KxProject>) {
//    for (p in projects) {
//        if (p., p.isEmpty())
//        add(test, p.name.toSnakeCase())
//        else
//        for (subP in p.subprojects)
//            add(test, subP.name.toSnakeCase())
//    }
//}

//private fun String.toSnakeCase() = replace(Regex("[A-Z]")) { "-${it.value.decapitalize()}" }

//private fun DependencyHandler.implementation(test: Boolean, artifact: String): Dependency? =
//    add(if (test) "testImplementation" else "implementation", "kotlin.graphics:$artifact") // { exclude("kx.platform") }

private fun DependencyHandler.implementation(test: Boolean, projects: Array<out KxProjectInterface>) {
    val config = if (test) "testImplementation" else "implementation"
    val group = "kotlin.graphics"
    for (project in projects)
        when (project) {
            is KxSingleProjectInterface -> add(config, "$group:${project.name}")
            is KxMultiProjectInterface ->
                for (module in project.modules)
                    add(config, "$group:${project.name}-${module.name}")
        }
}

//private fun DependencyHandler.testImpl(dependencyNotation: String, dependencyConfiguration: Action<ExternalModuleDependency>): ExternalModuleDependency =
//    addDependencyTo(this, "testImplementation", dependencyNotation, dependencyConfiguration)

//private fun <T : ModuleDependency> T.exclude(group: String? = null, module: String? = null): T =
//    uncheckedCast(exclude(excludeMapFor(group, module)))
//
//@Suppress("unchecked_cast", "nothing_to_inline")
//private inline fun <T> uncheckedCast(obj: Any?): T = obj as T
//
//private fun excludeMapFor(group: String?, module: String?): Map<String, String> =
//    mapOfNonNullValuesOf("group" to group, "module" to module)
//
//private fun mapOfNonNullValuesOf(vararg entries: Pair<String, String?>): Map<String, String> =
//    mutableMapOf<String, String>().apply {
//        for ((k, v) in entries)
//            if (v != null)
//                put(k, v)
//    }