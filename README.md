# NotasApp - Parcial II Desarrollo Móvil

Luis Miguel Quan Carranza - 201927151

Aplicación Android de notas personales con login, dashboard, creación y listado de notas, conectada a un backend propio.

## App (carpeta `app`)
- Kotlin con XML Layouts (ConstraintLayout y LinearLayout)
- Arquitectura MVVM con ViewModel, LiveData y Repository
- Retrofit con OkHttp. El token se agrega en el header `Authorization` con un interceptor
- RecyclerView con CardView para el listado de notas

## Backend (carpeta `backend`)
- Node.js con Express
- Base de datos SQLite (`notas.db`, se crea al iniciar)
- Autenticación con token JWT

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/login` | Recibe email y password, devuelve el token y el usuario |
| POST | `/api/notas` | Crea una nota del usuario del token (titulo, contenido) |
| GET | `/api/notas` | Devuelve las notas del usuario del token |

### Cómo ejecutarlo
```
cd backend
npm install
npm start
```
El backend corre en `http://localhost:3000`. La app usa `http://localhost:3000/api/`, y para que el emulador llegue al backend de la PC hay que ejecutar una vez cada vez que se abre el emulador:
```
adb reverse tcp:3000 tcp:3000
```

Usuario de prueba: `luis@testmail.com` / `654321`
