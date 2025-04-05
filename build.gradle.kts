plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.5")
    implementation("org.projectlombok:lombok:1.18.36")
    annotationProcessor ("org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useJUnitPlatform()
}