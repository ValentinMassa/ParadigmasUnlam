# 🚀 Guía de Configuración de Jenkins

Esta guía te ayudará paso a paso a configurar Jenkins para ejecutar tests automáticamente.

## 📋 Prerequisitos

1. ✅ Maven instalado localmente (ya lo tienes)
2. ✅ Java 17 instalado (ya lo tienes)
3. ⏳ Jenkins instalado

---

## 🔧 Paso 1: Instalar Jenkins

### Opción A: Instalación con Instalador (Recomendado para Windows)

1. Descarga Jenkins desde: https://www.jenkins.io/download/
2. Ejecuta el instalador `.msi` para Windows
3. Durante la instalación:
   - Puerto por defecto: `8080`
   - Ejecutar como servicio: Sí
4. Abre tu navegador en: `http://localhost:8080`

### Opción B: Instalación con Docker (Alternativa)

```powershell
docker run -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
```

---

## 🔐 Paso 2: Configuración Inicial de Jenkins

1. **Desbloquear Jenkins:**
   - Copia la contraseña inicial de: `C:\Program Files\Jenkins\secrets\initialAdminPassword`
   - Pégala en la página de desbloqueo

2. **Instalar Plugins:**
   - Selecciona: "Install suggested plugins"
   - Plugins adicionales recomendados:
     - ✅ Pipeline
     - ✅ Git
     - ✅ Maven Integration
     - ✅ HTML Publisher
     - ✅ JUnit

3. **Crear usuario administrador:**
   - Usuario: `admin`
   - Contraseña: (la que prefieras)
   - Email: tu email

---

## ⚙️ Paso 3: Configurar Herramientas Globales

### 3.1 Configurar JDK

1. Ve a: **Manage Jenkins** → **Global Tool Configuration**
2. En la sección **JDK**:
   - Click en **Add JDK**
   - Nombre: `JDK-17`
   - Desmarca "Install automatically"
   - JAVA_HOME: `C:\Program Files\Microsoft\jdk-17.0.16.8-hotspot`
   - Click en **Save**

### 3.2 Configurar Maven

1. En la misma página, sección **Maven**:
   - Click en **Add Maven**
   - Nombre: `Maven`
   - Desmarca "Install automatically"
   - MAVEN_HOME: `C:\Maven\apache-maven-3.9.11`
   - Click en **Save**

**💡 Tip:** Para verificar las rutas en tu PC:
```powershell
# Para Java
echo $env:JAVA_HOME
# o busca:
Get-ChildItem "C:\Program Files\Microsoft" | Where-Object {$_.Name -like "*jdk*"}

# Para Maven
echo "C:\Maven\apache-maven-3.9.11"
```

---

## 📁 Paso 4: Crear el Job en Jenkins

### 4.1 Crear Nuevo Pipeline

1. En el Dashboard de Jenkins, click en **"New Item"**
2. Nombre del proyecto: `CirculosIntersecciones-Pipeline`
3. Selecciona: **Pipeline**
4. Click en **OK**

### 4.2 Configurar el Pipeline

En la página de configuración:

#### General
- ☑️ Marca "GitHub project" (opcional)
- Project url: `https://github.com/ValentinMassa/ParadigmasUnlam`

#### Build Triggers (opcional)
- ☑️ "GitHub hook trigger for GITScm polling" (para builds automáticos)
- O marca "Poll SCM" y pon: `H/5 * * * *` (verifica cada 5 minutos)

#### Pipeline
1. **Definition:** `Pipeline script from SCM`
2. **SCM:** `Git`
3. **Repository URL:** `https://github.com/ValentinMassa/ParadigmasUnlam.git`
4. **Credentials:** (déjalo en blanco si es repo público)
5. **Branch Specifier:** `*/main`
6. **Script Path:** 
   ```
   testing_jenkins/Círculos e intersecciones/CirculosEIntersecciones/Jenkinsfile
   ```
7. Click en **Save**

---

## ▶️ Paso 5: Ejecutar el Pipeline

1. En la página del proyecto, click en **"Build Now"**
2. Observa el progreso en **"Build History"**
3. Click en el número del build → **"Console Output"** para ver los logs

### 🎯 Resultado Esperado

Deberías ver algo como:

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running test.circuloTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## 📊 Paso 6: Ver Reportes

Después de ejecutar el build:

1. **Test Results:**
   - En la página del build → "Test Result"
   - Verás gráficos de tests pasados/fallados

2. **Artifacts:**
   - En la página del build → "Artifacts"
   - Descarga el `.jar` generado

3. **Trends:**
   - En la página principal del proyecto
   - Verás gráficos de tendencia de tests

---

## 🐛 Solución de Problemas

### Error: "Maven not found"
**Solución:**
- Verifica que el nombre en Global Tool Configuration sea exactamente `Maven`
- Verifica la ruta: `C:\Maven\apache-maven-3.9.11`

### Error: "JDK not found"
**Solución:**
- Verifica que el nombre sea exactamente `JDK-17`
- Cambia en el Jenkinsfile a `JDK-11` si instalaste Java 11

### Error: "SCM checkout failed"
**Solución:**
- Verifica que la URL del repositorio sea correcta
- Si es privado, agrega credenciales en Jenkins

### Error: "bat: command not found"
**Solución:**
- Si usas Linux/Mac, cambia `bat` por `sh` en el Jenkinsfile
- O usa el Jenkinsfile completo que detecta automáticamente el SO

---

## 🔄 Configuración Automática (Opcional)

### Webhooks de GitHub

Para que Jenkins ejecute automáticamente al hacer push:

1. En GitHub, ve a: **Settings** → **Webhooks** → **Add webhook**
2. Payload URL: `http://TU-IP-O-DOMINIO:8080/github-webhook/`
3. Content type: `application/json`
4. Events: `Just the push event`
5. Click en **Add webhook**

**⚠️ Nota:** Necesitas que Jenkins sea accesible desde internet (usa ngrok o similar para pruebas locales)

---

## 📧 Notificaciones por Email (Opcional)

1. Ve a: **Manage Jenkins** → **Configure System**
2. En **E-mail Notification**:
   - SMTP server: `smtp.gmail.com` (por ejemplo)
   - Use SMTP Authentication: ✅
   - Username: tu-email@gmail.com
   - Password: contraseña de aplicación
   - SMTP port: `465`
   - Use SSL: ✅

3. Agrega al Jenkinsfile en la sección `post`:
```groovy
post {
    failure {
        emailext (
            subject: "Build Fallido: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
            body: "El build ha fallado. Ver detalles en: ${env.BUILD_URL}",
            to: "tu-email@ejemplo.com"
        )
    }
}
```

---

## ✅ Checklist de Verificación

Antes de ejecutar el pipeline, verifica:

- [ ] Jenkins está corriendo en http://localhost:8080
- [ ] JDK configurado con nombre `JDK-17`
- [ ] Maven configurado con nombre `Maven`
- [ ] Plugin de Git instalado
- [ ] Plugin de Pipeline instalado
- [ ] Repositorio clonado o accesible
- [ ] Jenkinsfile existe en la ruta correcta

---

## 📚 Recursos Adicionales

- [Documentación oficial de Jenkins](https://www.jenkins.io/doc/)
- [Jenkins Pipeline Syntax](https://www.jenkins.io/doc/book/pipeline/syntax/)
- [Maven en Jenkins](https://www.jenkins.io/doc/book/pipeline/getting-started/#defining-execution-environments)

---

## 🎓 Próximos Pasos

Una vez que funcione:

1. **Agregar más stages:** análisis de código estático (SonarQube)
2. **Deploy automático:** desplegar a un servidor
3. **Parallel tests:** ejecutar tests en paralelo
4. **Docker integration:** crear imágenes Docker del proyecto

---

¿Necesitas ayuda? Revisa la sección de solución de problemas o consulta los logs en Console Output.
