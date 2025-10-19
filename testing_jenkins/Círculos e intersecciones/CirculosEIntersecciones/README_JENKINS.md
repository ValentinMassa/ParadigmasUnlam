# Configuración de Jenkins para Círculos e Intersecciones

Este documento describe cómo configurar Jenkins para ejecutar los tests automáticamente.

## 📋 Prerequisitos

1. **Jenkins instalado** (versión 2.x o superior)
2. **Maven** instalado y configurado en Jenkins
3. **JDK 11** o superior instalado y configurado en Jenkins
4. Plugin de **Pipeline** instalado en Jenkins
5. Plugin de **Git** instalado en Jenkins

## 🚀 Configuración en Jenkins

### Paso 1: Configurar herramientas globales

1. Ve a **Manage Jenkins** → **Global Tool Configuration**

2. **Configurar JDK:**
   - Nombre: `JDK-11`
   - JAVA_HOME: Ruta de tu JDK (ej: `C:\Program Files\Java\jdk-11`)
   - O marca "Install automatically"

3. **Configurar Maven:**
   - Nombre: `Maven`
   - MAVEN_HOME: Ruta de tu Maven (ej: `C:\Program Files\Apache\maven`)
   - O marca "Install automatically"

### Paso 2: Crear un nuevo Job

1. En Jenkins, haz clic en **"New Item"**
2. Nombre: `CirculosIntersecciones-Tests`
3. Tipo: **Pipeline**
4. Click en **OK**

### Paso 3: Configurar el Pipeline

1. En la sección **Pipeline**:
   - Definition: **Pipeline script from SCM**
   - SCM: **Git**
   - Repository URL: Tu URL del repositorio
   - Branch: `*/main` (o tu rama principal)
   - Script Path: `testing_jenkins/Círculos e intersecciones/CirculosEIntersecciones/Jenkinsfile`

2. Guarda la configuración

### Paso 4: Ejecutar el Pipeline

1. Haz clic en **"Build Now"**
2. Observa la ejecución en **Console Output**

## 📊 Estructura del Pipeline

El Jenkinsfile incluye las siguientes etapas:

1. **Checkout**: Obtiene el código del repositorio
2. **Build**: Compila el proyecto con Maven
3. **Test**: Ejecuta los tests de JUnit
4. **Package**: Genera el archivo JAR

## 🧪 Ejecutar tests localmente

Antes de ejecutar en Jenkins, prueba localmente:

```bash
# Navega al directorio del proyecto
cd "testing_jenkins/Círculos e intersecciones/CirculosEIntersecciones"

# Compila el proyecto
mvn clean compile

# Ejecuta los tests
mvn test

# Empaqueta la aplicación
mvn package
```

## 📈 Visualización de resultados

Después de ejecutar el pipeline:

1. Los resultados de los tests aparecerán en la interfaz de Jenkins
2. Puedes ver el reporte detallado en **Test Result**
3. Los gráficos de tendencia mostrarán el historial de tests

## 🔧 Solución de problemas

### Error: Maven no encontrado
- Verifica que Maven esté configurado correctamente en Global Tool Configuration
- Asegúrate que el nombre coincida con el usado en Jenkinsfile (`Maven`)

### Error: JDK no encontrado
- Verifica que JDK esté configurado correctamente
- Asegúrate que el nombre coincida (`JDK-11`)

### Tests fallan
- Ejecuta `mvn test` localmente primero
- Verifica los logs en Console Output de Jenkins
- Revisa que las rutas en el Jenkinsfile sean correctas

## 🔄 Configuración adicional (Opcional)

### Trigger automático con Webhooks

1. En Jenkins, en la configuración del job:
   - Marca **"GitHub hook trigger for GITScm polling"**
2. En GitHub, ve a **Settings** → **Webhooks**
   - URL: `http://tu-jenkins-url/github-webhook/`
   - Content type: `application/json`
   - Events: **Just the push event**

### Notificaciones por email

1. Instala el plugin **Email Extension**
2. Configura SMTP en **Manage Jenkins** → **Configure System**
3. Añade al Jenkinsfile en la sección `post`:

```groovy
post {
    failure {
        emailext (
            subject: "Build Failed: ${env.JOB_NAME}",
            body: "El build ha fallado. Ver detalles en: ${env.BUILD_URL}",
            to: "tu-email@ejemplo.com"
        )
    }
}
```

## 📝 Notas

- El pipeline limpia el workspace después de cada ejecución
- Los reportes de tests se archivan automáticamente
- Puedes modificar el Jenkinsfile según tus necesidades
