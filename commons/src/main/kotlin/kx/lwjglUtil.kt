package kx

import kx.Lwjgl.Modules.*
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.internal.os.OperatingSystem.*
import org.gradle.kotlin.dsl.accessors.runtime.addExternalModuleDependencyTo

object Lwjgl {

    operator fun invoke(block: Lwjgl.() -> Unit) = Lwjgl.block()

    fun DependencyHandler.implementation(vararg modules: Modules) = implementation(false, modules)
    fun DependencyHandler.testImplementation(vararg modules: Modules) = implementation(true, modules)

    fun DependencyHandler.implementation(preset: Preset) = implementation(false, preset.modules)
    fun DependencyHandler.testImplementation(preset: Preset) = implementation(true, preset.modules)

    private fun DependencyHandler.implementation(test: Boolean, modules: Array<out Modules>) {
        // core
        implementation(test, "", true)
        for (m in modules)
            implementation(test, "-$m", m.hasNative)
    }

    private fun DependencyHandler.implementation(test: Boolean, module: String, native: Boolean) {
        var config = if (test) "testImplementation" else "implementation"
        val group = "org.lwjgl"
        val art = "lwjgl$module"
        add(config, "$group:$art")
        if (native) {
            config = if (test) "testRuntimeOnly" else "runtimeOnly"
            addExternalModuleDependencyTo(this, config, group, art, null, null, natives, null, null)
        }
    }

    enum class Modules(val hasNative: Boolean = true) {
        assimp,
        bgfx,
        bullet,
        cuda(false),
        driftfx,
        egl(false),
        glfw,
        jawt(false),
        jemalloc,
        libdivide,
        llvm,
        lmdb,
        lz4,
        meow,
        meshoptimizer,
        nanovg,
        nfd,
        nuklear,
        odbc(false),
        openal,
        opencl(false),
        opengl,
        opengles,
        openvr,
        opus,
        par,
        remotery,
        rpmalloc,
        shaderc,
        spvc,
        sse,
        stb,
        tinyexr,
        tinyfd,
        tootle,
        vma,
        vulkan(false),
        xxhash,
        yoga,
        zstd
    }

    enum class Preset(val modules: Array<Modules>) {
        none(emptyArray<Modules>()),
        everything(Modules.values()),
        gettingStarted(arrayOf(assimp, bgfx, glfw, nanovg, nuklear, openal, opengl, par, stb, vulkan)),
        minimalOpenGL(arrayOf(assimp, glfw, openal, opengl, stb)),
        minimalOpenGLES(arrayOf(assimp, egl, glfw, openal, opengles, stb)),
        minimalVulkan(arrayOf(assimp, glfw, openal, stb, vulkan))
    }

    val natives = "natives-" + when (current()) {
        WINDOWS -> "windows"
        LINUX -> "linux"
        else -> "macos"
    }
}