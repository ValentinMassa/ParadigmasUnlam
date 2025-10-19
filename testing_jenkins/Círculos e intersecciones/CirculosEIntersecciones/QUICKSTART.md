# 📝 Resumen de Configuración de Jenkins

## 🎯 ¿Qué necesitas configurar en Jenkins?

### 1️⃣ HERRAMIENTAS GLOBALES (en Jenkins UI)

```
Manage Jenkins → Global Tool Configuration
```

#### JDK
- **Nombre:** `JDK-17`
- **JAVA_HOME:** `C:\Program Files\Microsoft\jdk-17.0.16.8-hotspot`
- ⚠️ Desmarca "Install automatically"

#### Maven  
- **Nombre:** `Maven`
- **MAVEN_HOME:** `C:\Maven\apache-maven-3.9.11`
- ⚠️ Desmarca "Install automatically"

---

### 2️⃣ CREAR PIPELINE JOB

```
New Item → Pipeline → OK
```

**Configuración del Pipeline:**

| Campo | Valor |
|-------|-------|
| **Definition** | `Pipeline script from SCM` |
| **SCM** | `Git` |
| **Repository URL** | `https://github.com/ValentinMassa/ParadigmasUnlam.git` |
| **Branch** | `*/main` |
| **Script Path** | `testing_jenkins/Círculos e intersecciones/CirculosEIntersecciones/Jenkinsfile` |

---

### 3️⃣ ARCHIVOS DISPONIBLES

Tienes 2 versiones del Jenkinsfile:

#### 📄 `Jenkinsfile` (Completo - Recomendado)
✅ Detección automática de SO (Windows/Linux/Mac)  
✅ Reportes HTML de tests  
✅ Cobertura de código  
✅ Quality Gates  
✅ Archivado de JAR  
✅ Resumen detallado

#### 📄 `Jenkinsfile.simple` (Básico)
✅ Solo compilar y testear  
✅ Más fácil de entender  
✅ Menos configuración

**Para usar el simple:** Cambia Script Path a:
```
testing_jenkins/Círculos e intersecciones/CirculosEIntersecciones/Jenkinsfile.simple
```

---

## 🚀 Guía Rápida de 3 Pasos

### Paso 1: Verificar Rutas
```powershell
# Verifica que estas rutas existan:
Test-Path "C:\Maven\apache-maven-3.9.11"
Test-Path "C:\Program Files\Microsoft\jdk-17.0.16.8-hotspot"
```

### Paso 2: Configurar en Jenkins
1. Abre Jenkins: http://localhost:8080
2. Ve a: **Manage Jenkins** → **Global Tool Configuration**
3. Configura JDK y Maven con los nombres exactos de arriba

### Paso 3: Crear y Ejecutar Job
1. **New Item** → Nombre: `CirculosTests` → **Pipeline**
2. Configura según la tabla anterior
3. **Save** → **Build Now**

---

## ✅ Checklist Pre-Ejecución

- [ ] Jenkins corriendo en puerto 8080
- [ ] JDK configurado con nombre **exacto**: `JDK-17`
- [ ] Maven configurado con nombre **exacto**: `Maven`
- [ ] Código subido a GitHub
- [ ] Jenkinsfile existe en la ruta correcta

---

## 📊 Qué hace el Pipeline

```
┌─────────────┐
│  Checkout   │  ← Descarga código de GitHub
└──────┬──────┘
       │
┌──────▼──────┐
│    Build    │  ← Compila el código Java
└──────┬──────┘
       │
┌──────▼──────┐
│    Test     │  ← Ejecuta los 5 tests
└──────┬──────┘
       │
┌──────▼──────┐
│   Package   │  ← Genera el .jar
└──────┬──────┘
       │
┌──────▼──────┐
│   Report    │  ← Muestra resultados
└─────────────┘
```

---

## 🎨 Resultado Esperado

Después de ejecutar, verás:

- ✅ **5 tests ejecutados**
- ✅ **0 fallos**
- ✅ **BUILD SUCCESS**
- ✅ Archivo JAR generado
- ✅ Reportes de test disponibles

---

## 🆘 Ayuda Rápida

### Si Jenkins no inicia:
```powershell
# Verificar servicio
Get-Service jenkins

# Iniciar servicio
Start-Service jenkins
```

### Si el build falla:
1. Click en el número del build
2. Click en "Console Output"
3. Busca la línea con `[ERROR]`
4. Revisa los nombres de JDK y Maven

---

## 📚 Documentos Adicionales

- 📖 `JENKINS_SETUP.md` - Guía completa paso a paso
- 📖 `README_JENKINS.md` - Información del proyecto
- 📄 `Jenkinsfile` - Pipeline completo
- 📄 `Jenkinsfile.simple` - Pipeline básico

---

## 🔗 Enlaces Útiles

- Jenkins Local: http://localhost:8080
- Documentación: https://www.jenkins.io/doc/
- Tu Repo: https://github.com/ValentinMassa/ParadigmasUnlam

---

¿Necesitas ayuda? Revisa `JENKINS_SETUP.md` para instrucciones detalladas.
