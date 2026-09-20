# Mapa del Proyecto - Práctica 3 OOP Kotlin

Este documento sirve como guía técnica para entender la estructura, organización y propósito de cada componente del proyecto. Es ideal para dar contexto rápido a otros desarrolladores o agentes de IA.

## Estructura de Carpetas y Archivos

### 1. Configuración del Proyecto (Raíz)
*   [build.gradle.kts (Raíz)](file:///home/netzarb/AndroidStudioProjects/Dispositivos/build.gradle.kts): Define los plugins que se usarán en todo el proyecto.
*   [settings.gradle.kts](file:///home/netzarb/AndroidStudioProjects/Dispositivos/settings.gradle.kts): Declara los módulos del proyecto (`:app`) y los repositorios de dependencias.
*   [gradle.properties](file:///home/netzarb/AndroidStudioProjects/Dispositivos/gradle.properties): Configuraciones globales de Gradle (JVM, AndroidX habilitado).
*   **gradle/libs.versions.toml**: El **Version Catalog**. Aquí se centralizan las versiones de todas las librerías (Navigation, RecyclerView, Material) para mantener el orden.

---

### 2. Módulo de la Aplicación (`app/`)
*   [app/build.gradle.kts](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/build.gradle.kts): Configuración específica de la app. Habilita **View Binding** y aplica los plugins de Kotlin.

#### Código Fuente (`app/src/main/java/.../`)
*   [MainActivity.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/MainActivity.kt): La actividad principal. Sirve como contenedor para el `NavHostFragment` y gestiona la barra de navegación superior.
*   **`data/model/`**
    *   [Incidencia.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/data/model/Incidencia.kt): La **clase de datos** principal. Representa una incidencia. Implementa `Serializable` para poder enviarse entre pantallas.
*   **`ui/`**
    *   [ListaIncidenciasFragment.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/ui/ListaIncidenciasFragment.kt): Pantalla inicial. Gestiona la lista de incidencias, inicializa el RecyclerView y maneja el evento de clic para navegar.
    *   [DetalleIncidenciaFragment.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/ui/DetalleIncidenciaFragment.kt): Segunda pantalla. Recibe un objeto `Incidencia` como argumento y muestra sus detalles.
*   **`ui/adapter/`**
    *   [IncidenciaAdapter.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/ui/adapter/IncidenciaAdapter.kt): El adaptador del **RecyclerView**. Es el "puente" que convierte la lista de objetos Kotlin en elementos visuales en la pantalla.

---

### 3. Recursos (`app/src/main/res/`)

#### Layouts (XML)
*   [activity_main.xml](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/res/layout/activity_main.xml): Define el contenedor donde se intercambian los Fragments.
*   [fragment_lista_incidencias.xml](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/res/layout/fragment_lista_incidencias.xml): Diseño de la lista principal (RecyclerView).
*   [fragment_detalle_incidencia.xml](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/res/layout/fragment_detalle_incidencia.xml): Diseño detallado de la incidencia.
*   [item_incidencia.xml](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/res/layout/item_incidencia.xml): Diseño de **una sola fila** (tarjeta) de la lista.

#### Navegación
*   [nav_graph.xml](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/res/navigation/nav_graph.xml): El **grafo de navegación**. Define visualmente que se puede ir de "Lista" a "Detalle" y especifica qué datos (argumentos) se pasan.

#### Valores y Estilos
*   `values/colors.xml`: Definición de la paleta de colores.
*   `values/strings.xml`: Textos de la aplicación (internacionalización).
*   `values/themes.xml`: Configuración de estilos de Material Design 3.

---

## Flujo de Datos
1.  **Modelo**: `Incidencia` (Data Class).
2.  **Origen**: Lista estática creada en `ListaIncidenciasFragment`.
3.  **Procesamiento**: El Fragment le entrega la lista al `IncidenciaAdapter`.
4.  **Visualización**: El RecyclerView dibuja los `item_incidencia.xml` usando el adaptador.
5.  **Interacción**: Al tocar un ítem, el adaptador avisa al Fragment, y este usa el `NavController` para viajar al `DetalleIncidenciaFragment` enviando la incidencia.

## Conceptos Técnicos Implementados
*   **OOP**: Clases, Objetos, Encapsulamiento, Delegación.
*   **Android Jetpack**: Navigation Component, Fragments.
*   **Material Design 3**: Uso de Cards y Dividers.
*   **Performance**: RecyclerView para gestión eficiente de memoria.
