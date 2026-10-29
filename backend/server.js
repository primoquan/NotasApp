// Backend de NotasApp - Parcial 2 Desarrollo Movil
// Luis Miguel Quan Carranza - 201927151

const express = require("express");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");
const db = require("./database");

const app = express();
const PUERTO = 3000;
// clave para firmar los tokens
const CLAVE_JWT = "notasapp_parcial2_201927151";

app.use(express.json());

// -----------------------------------------------
// Login
// -----------------------------------------------
app.post("/api/login", (req, res) => {
    const { email, password } = req.body;

    if (!email || !password) {
        return res.status(400).json({ mensaje: "Ingrese el correo y la contraseña" });
    }

    const usuario = db.prepare("SELECT * FROM usuarios WHERE email = ?").get(email);

    // si no existe o la contraseña no coincide
    if (!usuario || !bcrypt.compareSync(password, usuario.password)) {
        return res.status(401).json({ mensaje: "Correo o contraseña incorrectos" });
    }

    // el token guarda el id y el nombre del usuario, dura 8 horas
    const token = jwt.sign({ id: usuario.id, nombre: usuario.nombre }, CLAVE_JWT, { expiresIn: "8h" });

    res.json({
        token: token,
        usuario: { id: usuario.id, nombre: usuario.nombre, email: usuario.email }
    });
});

// -----------------------------------------------
// Middleware que valida el token
// -----------------------------------------------
function validarToken(req, res, next) {
    // el header llega como: Authorization: Bearer <token>
    const header = req.headers.authorization;

    if (!header || !header.startsWith("Bearer ")) {
        return res.status(401).json({ mensaje: "Token no enviado" });
    }

    try {
        const token = header.substring(7);
        req.usuario = jwt.verify(token, CLAVE_JWT);
        next();
    } catch (error) {
        return res.status(401).json({ mensaje: "Token inválido o vencido" });
    }
}

// -----------------------------------------------
// Crear una nota
// -----------------------------------------------
app.post("/api/notas", validarToken, (req, res) => {
    const { titulo, contenido } = req.body;

    if (!titulo || !contenido) {
        return res.status(400).json({ mensaje: "El título y el contenido son obligatorios" });
    }

    // la nota se guarda con el id del usuario que viene en el token
    const resultado = db
        .prepare("INSERT INTO notas (usuario_id, titulo, contenido) VALUES (?, ?, ?)")
        .run(req.usuario.id, titulo, contenido);

    const nota = db.prepare("SELECT * FROM notas WHERE id = ?").get(resultado.lastInsertRowid);

    res.status(201).json({ mensaje: "Nota guardada correctamente", nota: nota });
});

// -----------------------------------------------
// Obtener las notas del usuario
// -----------------------------------------------
app.get("/api/notas", validarToken, (req, res) => {
    // solo las notas del usuario del token, de la mas nueva a la mas vieja
    const notas = db
        .prepare("SELECT * FROM notas WHERE usuario_id = ? ORDER BY fecha_creacion DESC, id DESC")
        .all(req.usuario.id);

    res.json(notas);
});

app.listen(PUERTO, () => {
    console.log("Backend de NotasApp corriendo en http://localhost:" + PUERTO);
});
