# Mapa del Proyecto - S7 Lab: MVP + Layouts Avanzados

Este documento sirve como guía técnica para entender la estructura, organización y propósito de cada componente del proyecto actualizado con el patrón MVP y Material Design 3.

## Estructura de Carpetas y Archivos

### 1. Configuración del Proyecto (Raíz)
*   [build.gradle.kts (Raíz)](file:///home/netzarb/AndroidStudioProjects/Dispositivos/build.gradle.kts): Define los plugins que se usarán en todo el proyecto.
*   [settings.gradle.kts](file:///home/netzarb/AndroidStudioProjects/Dispositivos/settings.gradle.kts): Declara los módulos del proyecto (`:app`).
*   [gradle.properties](file:///home/netzarb/AndroidStudioProjects/Dispositivos/gradle.properties): Configuraciones globales de Gradle.
*   **gradle/libs.versions.toml**: Version Catalog con librerías centralizadas (Navigation, Material, RecyclerView).

---

### 2. Módulo de la Aplicación (`app/`)
*   [app/build.gradle.kts](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/build.gradle.kts): Configuración específica de la app. Habilita View Binding y plugins.

#### Código Fuente (`app/src/main/java/.../`)
*   [DispositivosApp.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/DispositivosApp.kt): Clase Application para inicializar los colores dinámicos de Material Design 3.
*   [MainActivity.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/MainActivity.kt): Actividad principal contenedora. Configura la `MaterialToolbar` superior y el `BottomNavigationView` inferior vinculados al `NavController`.
*   **`data/model/`**
    *   [Incidencia.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/data/model/Incidencia.kt): Data class principal que implementa `Parcelable`.
*   **`ui/lista/` (Patrón MVP - Listado)**
    *   [ListaIncidenciasContract.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/ui/lista/ListaIncidenciasContract.kt): Contrato de interfaces (`View` y `Presenter`).
    *   [ListaIncidenciasPresenter.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/ui/lista/ListaIncidenciasPresenter.kt): Lógica de negocio y gestión de incidencias.
    *   [ListaIncidenciasFragment.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/ui/ListaIncidenciasFragment.kt): Implementación de la Vista.
*   **`ui/detalle/` (Patrón MVP - Detalle)**
    *   [DetalleIncidenciaContract.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/ui/detalle/DetalleIncidenciaContract.kt): Contrato de interfaces para detalle.
    *   [DetalleIncidenciaPresenter.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/ui/detalle/DetalleIncidenciaPresenter.kt): Lógica para el detalle de la incidencia.
    *   [DetalleIncidenciaFragment.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/ui/DetalleIncidenciaFragment.kt): Vista de detalle.
*   **`ui/adapter/`**
    *   [IncidenciaAdapter.kt](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/java/com/example/dispositivos/ui/adapter/IncidenciaAdapter.kt): Adaptador del RecyclerView.

---

### 3. Recursos (`app/src/main/res/`)

#### Layouts (XML)
*   [activity_main.xml](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/res/layout/activity_main.xml): Layout principal con `AppBarLayout`, `MaterialToolbar`, contenedor de Fragments y `BottomNavigationView`.
*   [fragment_lista_incidencias.xml](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/res/layout/fragment_lista_incidencias.xml): Diseño de la lista y FAB.
*   [fragment_detalle_incidencia.xml](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/res/layout/fragment_detalle_incidencia.xml): Diseño de detalles de la incidencia.
*   [item_incidencia.xml](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/res/layout/item_incidencia.xml): Tarjeta de cada elemento.

#### Menús y Navegación
*   `menu/bottom_nav_menu.xml`: Definición de ítems para el `BottomNavigationView`.
*   [nav_graph.xml](file:///home/netzarb/AndroidStudioProjects/Dispositivos/app/src/main/res/navigation/nav_graph.xml): Grafo de navegación entre lista y detalle.

#### Valores y Estilos
*   `values/themes.xml`: Configuración de Material Design 3 y estilos de barra.
*   `values/strings.xml`: Textos y recursos de cadena.

---

## Conceptos Técnicos Implementados
*   **Arquitectura MVP**: Separación estricta de responsabilidades mediante contratos (`View` / `Presenter`).
*   **Material Design 3**: `BottomNavigationView`, `MaterialToolbar`, Colores Dinámicos (`DynamicColors`).
*   **Android Jetpack**: Navigation Component, Fragments, View Binding.
