# Trabajo Final – Argentina Programa Tramo 2, Desarrollo de Aplicaciones Móviles.

## Alumno: Mauricio Javier Alonso
## App: Comparador de textos
## Link:  
## https://github.com/mauprograma/comparadordetextos.git

## 1 - CONSIGNA 
Tarea a realizar: Desarrollá una aplicación que tenga un solo activity que cumpla con las siguientes premisas:
1	- Contenga una única pantalla (sin importar el layout elegido) con:
•	2 cuadros de textos (EditText) 
•	1 botón con el texto “comparar”
•	1 texto (TextView) que en el que se escriba el resultado de la acción al presionar el botón.
2	- Asegurarse de que:
1.	Utiliza MVVM
2.	Tiene al menos un test unitario
3.	Tiene al menos un test de UI
3	- Función de la aplicación
Cuando el usuario hace clic en el botón “comparar” debe comparar la entrada de ambos cuadros de texto y escribir en el texto (TextView) si ambas cadenas de caracteres son iguales o no. 

## 2 – CONTENIDO DE LA APLICACIÓN
Se desarrolló un Comparador de Textos, en lenguaje Kotlin y con la herramienta Android Studio. 
Esta es la pantalla principal que ve el usuario de la aplicación. A continuación, se detalla qué ítems contiene la app: los que son requeridos por las consignas de este trabajo final y aquellos ítems que fueron agregados para mejorar y/o incrementar la experiencia del usuario.

### 2.1 – Ítems requeridos en la consigna

### 2.1.1 - Dos cuadros de EditText 
Para los cuadros de EditText se utilizó el componente  <com.google.android.material.textfield.TextInputEditText/>. A su vez, dichos componentes están insertos dentro de   <com.google.android.material.textfield.TextInputLayout/> . De esta forma se cuenta con más opciones de configuración y propiedades. 
Los EditText tienen una extensión máxima de 300 caracteres.  La app impide que el usuario pueda ingresar una cantidad de caracteres mayor a 300 (incluidos espacios). 
Asimismo, completar cada cuadro es obligatorio para el funcionamiento de la aplicación, por ello es que tiene el indicador de “obligatorio”. 
### 2.1.2 – Botón Comparar
El botón “Comparar”, que se importa de un archivo res de menú, compara lo ingresado en los dos EditText. 
Cuando se ejecuta este botón, el sistema valida que los dos textos en cada EditText estén completos, caso contrario se impedirá el avance y se resaltará con borde rojo el cuadro que no está completo. 
Si ambos cuadros están completos, entonces se le aplica a los dos textos el método trim() para despojar de espacios vacíos al principio y al final que puedan alterar el resultado de la comparación.  Luego se abre una ventana donde se le muestra los textos a comparar al usuario y se le da la opción de comparar (continuar) o cancelar.
Además, cuando se ejecuta el botón comparar, se oculta el teclado para que se vea sin problemas el TextView  con el resultado de la comparación.
### 2.1.3 – TextView
Si aún no se ejecutó el botón comparar, el texto que el usuario verá en este elemento es “Esperando comparación”.
Si se ejecutó el botón comparar, y los textos son distintos, el texto que se verá es “Textos distintos”. 
Finalmente, si el botón se ejecuta y los textos son iguales, se mostrará “Textos Iguales”. 

 
### 2.2 – Ítems no requeridos en la consigna, pero adicionados para mejorar la experiencia de uso en la app. 

### 2.2.1 – Botón limpiar
El botón “Limpiar”, que se importa de un archivo res de menú, borra lo ingresado en los dos EditText, al TextView lo lleva a su estado predeterminado de “esperando comparación”, borra las alertas en rojo que puedan existir en los EditText vacíos.
### 2.2.2 – Checkbox relevancia de mayúsculas / minúsculas. 
Si este checkbox no está seleccionado, el sistema considerará relevantes las mayúsculas y minúsculas al momento de evaluar la diferencia en los textos.
Por el contrario, si este checkbox se activa, el sistema aplica el método toLowerCase() a los textos ingresados para eliminar las mayúsculas y comparar los textos en minúsculas. 
### 2.2.3 – Botón Acerca de la app. 
Si el usuario selecciona este botón, se despliega una ventana emergente con datos sobre la app. La verdadera importancia de este agregado es el uso la mecánica de INTENT al incorporar un direccionamiento a una página web: para ilustrar este punto elegí la que preparé para la entrega de la primera edición del Argentina Programa. 

## 3 – ASPECTOS A ASEGURARSE

### 3.1 Utiliza MVVM
Para el desarrollo de esta app implementé la estructura MVVM (Model – View – ViewModel), tal como se recomendó en los videos del curso. 
### 3.1.1 Model (Archivo Comparador)
Aquí se representan los datos principales, las variables textFirst y textSecond, ambas string.
### 3.1.2 View (Archivo MainActivity)
Aquí se maneja la interfaz del usuario, regulando por ejemplo, los OnClickListener, las ventanas emergentes, entre otros. Y tal como lo requiere la consigna, se usó una sola view (MainView).
### 3.1.3 ModelView
Se expone la lógica principal del programa, que es la de comparar las variables textFirst y textSecond. 
### 3.2 - Tiene al menos un test unitario
Se realizó el correspondiente test. 
### 3.3 - Tiene al menos un test de UI.
Se realizó el correspondiente test
### 3.4 Estructura de la carpeta RES
El contenido de la carpeta RES es el siguiente:
El layout principal de la mainActivity se expone en el archivo activiy_main.xml, utilizando para esto un scrollview, ya que mi objetivo es que se pueda hacer scroll en la pantalla si el usuario llegase a ingresar textos en su extensión máxima en un aparato con pantalla pequeña. 
También está codificado un menú de opciones, en menu_main.xml, el cual contiene los botones de comparar y limpiar.
Asimismo, los principales recursos están distribuidos en las carpetas drawable, que contiene íconos que se usan en los EditText y en los botones; values, donde se almacenan todos los strings utilizados, los colores, y las dimensiones. De esta manera, es más fácil editar el contenido visual de la app, ayudando a que el código sea más mantenible. 
### 3.5 Las modificaciones en build.graddle
En graddle se instalaron las depencias para el uso de binding, el cual permite llamar componentes con la lógica.
Y también se instalaron las dependencias necesarias para hacer los test. 
### 3.6 La implementación de GRADIENT
Para agregar un detalle estético a cómo el usuario ve la aplicación. 
### 3.7 Implementación de hidekeyboard
Para que el teclado se oculte en ciertos pasajes de la aplicación, usé la mecánica para ocultarlo,
 

## 4	– FUNCION DE LA APLICACIÓN 
La función principal de la app es la de comparar dos textos. Los textos pueden tener hasta 300 caracteres incluyendo espacios. 
•	El usuario debe ingresar dos textos
Debe sí o sí ingresar los dos textos y no puede superar los 300 caracteres. 
•	Luego puede elegir si desea que el tratamiento de las mayúsculas y minúsculas sea indistinto. 
•	Debe hacer click en el botón comparar 
•	Se abrirá una ventana donde se le muestra los textos al usuario, dándole la posibilidad de avanzar con la comparación o cancelar. 
 

•	La app muestra el resultado de la comparación, siendo dos resultados posibles
•	Finalmente, el usuario puede limpiar el formulario para que todo vuelva el estado previo a la comparación. 
De forma secundaria, el usuario puede explorar más datos sobre la app en el botón más Acerca de  la app. 
 

## 5	– INFORMACIÓN ADICIONAL 
Esta aplicación ha sido desarrollada para que sea complatible con API 21: Android 5.0 (Lollipop), estimando que, a la fecha, sería compatible con el 99.5% de los dispositivos Android existentes. 
Y ha sido testeada en los siguientes dispositivos virtuales:

• Phone Pixel API 21
• Phone Pixel API 30
• Phone Pixel API 34
