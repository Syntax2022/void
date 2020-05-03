plugins {
    `java-library`
}

dependencies {
    implementation(project(":cache"))
    implementation(project(":utility"))
    implementation(project(":network"))
    implementation("io.github.classgraph:classgraph:4.8.65")
    implementation(kotlin("script-runtime"))
}