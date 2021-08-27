package kx

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

interface KxProjectInterface
interface KxSingleProjectInterface
interface KxMultiProjectInterface {
    val modules: List<KxSingleProjectInterface>
}

object KxProject {

    object kool : KxProjectInterface, KxSingleProjectInterface
    object unsigned : KxProjectInterface, KxSingleProjectInterface
    object glm : KxProjectInterface, KxSingleProjectInterface
    object gli : KxProjectInterface, KxSingleProjectInterface
    object gln : KxProjectInterface, KxSingleProjectInterface
    object vkk : KxProjectInterface, KxSingleProjectInterface

    object uno : KxProjectInterface, KxMultiProjectInterface {
        object awt : KxProjectInterface, KxSingleProjectInterface
        object core : KxProjectInterface, KxSingleProjectInterface
        object vk : KxProjectInterface, KxSingleProjectInterface
        override val modules: List<KxSingleProjectInterface> = listOf(awt, core, vk)
    }

    object imgui : KxProjectInterface, KxMultiProjectInterface {
        object core: KxProjectInterface, KxSingleProjectInterface
        object gl: KxProjectInterface, KxSingleProjectInterface
        object glfw: KxProjectInterface, KxSingleProjectInterface
        object openjfx: KxProjectInterface, KxSingleProjectInterface
        object vk: KxProjectInterface, KxSingleProjectInterface
        override val modules: List<KxSingleProjectInterface> = listOf(uno.awt, uno.core, uno.vk)
    }

    object assimp : KxProjectInterface, KxSingleProjectInterface
    object openvr : KxProjectInterface, KxSingleProjectInterface
}

//sealed interface Expr

//enum class KxProject(val subprojects: List<KxProject> = emptyList()) {
//    kool,
//    unsigned,
//    glm,
//    gli,
//    gln,
//    vkk,
//    unoCore,
//    unoAwt,
//    unoVk,
//    uno(listOf(unoAwt, unoCore, unoVk)),
//    imguiCore,
//    imguiGl,
//    imguiGlfw,
//    imguiOpenjfx,
//    imguiVk,
//    imgui(listOf(imguiCore, imguiGl, imguiGlfw, imguiOpenjfx, imguiVk)),
//    assimp,
//    openvr
//}

//object KxBundle {
//    val none = emptyArray<LwjglModules>()
//    val everything = LwjglModules.values()
//    val gettingStarted = arrayOf(assimp, bgfx, glfw, nanovg, nuklear, openal, opengl, par, stb, vulkan)
//    val minimalOpenGL = arrayOf(assimp, glfw, openal, opengl, stb)
//    val minimalOpenGLES = arrayOf(assimp, egl, glfw, openal, opengles, stb)
//    val minimalVulkan = arrayOf(assimp, glfw, openal, stb, vulkan)
//}


//@JvmName("KxImplementation2")
//fun DependencyHandler.kxImplementation(modules: Array<KxProject>) = impl(false, modules)
//
//@JvmName("KxTestImplementation2")
//fun DependencyHandler.kxTestImplementation(modules: Array<KxProject>) = impl(true, modules)

//fun DependencyHandler.kxImplementation(vararg projects: KxProject) = add(false, projects)
//fun DependencyHandler.kxTestImplementation(vararg projects: KxProject) = add(true, projects)
//
//private fun DependencyHandler.add(test: Boolean, projects: Array<out KxProject>) {
//    for (p in projects) {
//        if (p.,p.isEmpty())
//            add(test, p.name.toSnakeCase())
//        else
//            for (subP in p.subprojects)
//                add(test, subP.name.toSnakeCase())
//    }
//}

private fun String.toSnakeCase() = replace(Regex("[A-Z]")) { "-${it.value.decapitalize()}" }

private fun DependencyHandler.add(test: Boolean, artifact: String): Dependency? =
    add(if (test) "testImplementation" else "implementation", "kotlin.graphics:$artifact") // { exclude("kx.platform") }

//private fun DependencyHandler.impl(dependencyNotation: String, dependencyConfiguration: Action<ExternalModuleDependency>): ExternalModuleDependency =
//    addDependencyTo(this, "implementation", dependencyNotation, dependencyConfiguration)
//
//private fun DependencyHandler.testImpl(dependencyNotation: String, dependencyConfiguration: Action<ExternalModuleDependency>): ExternalModuleDependency =
//    addDependencyTo(this, "testImplementation", dependencyNotation, dependencyConfiguration)
//
//private fun <T : ModuleDependency> T.exclude(group: String? = null, module: String? = null): T =
//    uncheckedCast(exclude(excludeMapFor(group, module)))

@Suppress("unchecked_cast", "nothing_to_inline")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T

private fun excludeMapFor(group: String?, module: String?): Map<String, String> =
    mapOfNonNullValuesOf("group" to group, "module" to module)

private fun mapOfNonNullValuesOf(vararg entries: Pair<String, String?>): Map<String, String> =
    mutableMapOf<String, String>().apply {
        for ((k, v) in entries)
            if (v != null)
                put(k, v)
    }