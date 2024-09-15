# Lectores Felices - Sistema de Gestión de Préstamos de Libros by Esteban Hirzfeld

Este programa es un sistema simple de gestión de préstamos de libros que permite a los usuarios iniciar sesión, alquilar libros, devolverlos, ver sus libros actuales y revisar su historial de préstamos. El sistema también incluye penalidades por devoluciones tardías.

## Funcionalidades

1. **Sistema de Login y Logout**:
   - Los usuarios pueden seleccionar su cuenta para iniciar sesión y realizar diferentes acciones.
   - También pueden cambiar de cuenta o cerrar sesión en cualquier momento.

2. **Alquiler de Libros**:
   - Los usuarios pueden solicitar el préstamo de libros.
   - Solo se mostrarán los libros disponibles para alquilar.

3. **Devolución de Libros**:
   - Los usuarios pueden devolver libros que hayan alquilado.
   - Solo se mostrarán los libros que el usuario actual tiene alquilados.

4. **Visualización de Libros Alquilados**:
   - Los usuarios pueden ver una lista de los libros que tienen actualmente alquilados.

5. **Historial de Libros Alquilados**:
   - El sistema registra el historial de los libros alquilados y devueltos por el usuario, junto con las fechas correspondientes.

6. **Penalidad por Devoluciones Tardías**:
   - Si un libro no se devuelve dentro de los 7 días desde el momento del alquiler, se aplicará una penalidad de $1000 por cada día extra.

## Ejemplo de Ejecución

1. **Alquilar un Libro**:
   - Al seleccionar la opción de **Alquilar**, el sistema mostrará solo los libros disponibles.
   - Al alquilar un libro, este se marcará como no disponible y se añadirá a los libros alquilados del usuario.

2. **Devolver un Libro**:
   - Al seleccionar la opción de **Devolver**, el sistema mostrará solo los libros que el usuario actual tiene alquilados.
   - El sistema calculará si se aplica alguna penalidad por devolución tardía (más de 7 días) y lo reflejará en el historial del usuario.

3. **Revisar Libros Alquilados**:
   - Los usuarios pueden ver una lista de los libros que tienen alquilados en ese momento.

4. **Revisar Historial de Libros Alquilados**:
   - Los usuarios pueden revisar un registro de los libros que han alquilado y devuelto, junto con las fechas correspondientes y cualquier penalidad aplicada.

## Prueba del Sistema de Penalidades

El libro `book_test`, añadido al usuario "Tony Montana", ha sido alquilado 11 días antes de la fecha actual. Esto significa que, si se devuelve este libro durante la ejecución del programa, se aplicará una penalidad de $4000 (debido a que se excedió el plazo de 7 días en 4 días).

---
